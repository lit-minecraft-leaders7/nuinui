package com.example.examplemod;

        import cpw.mods.fml.common.eventhandler.SubscribeEvent;
        import net.minecraft.block.Block;
        import net.minecraft.block.material.Material;
        import net.minecraft.entity.player.EntityPlayer;
        import net.minecraft.init.Blocks;
        import net.minecraft.init.Items;
        import net.minecraft.item.Item;
        import net.minecraft.world.World;
        import net.minecraftforge.event.world.BlockEvent;

/**
 * Created by nui on 16/02/08.
 */
public class MyBlockBreakEventHandler {
    private static int MAX_DEPTH = 20;

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        EntityPlayer player = event.getPlayer();
        if (player == null) {
            return;
        }
        if (player.getHeldItem() == null) {
            return;
        }
        Item item = player.getHeldItem().getItem();
        if (!(item == Items.wooden_shovel || item == Items.stone_shovel ||
                item == Items.iron_shovel || item == Items.golden_shovel || item == Items.diamond_shovel)) {
            return;
        }
        Block block = event.block;
        if (!(block.getMaterial() == Material.sand || block.getMaterial() == Material.ground ||
                block.getMaterial() == Material.grass || block.getMaterial() == Material.rock)) {
            return;
        }
        event.setCanceled(true);
        breakBlock(event.world, event.x, event.y, event.z, 1);
    }

    private void breakBlock(World world, int x, int y, int z, int depth) {
        if (depth > MAX_DEPTH) {
            return;
        }

        Block block = world.getBlock(x, y, z);

        if (block.getMaterial() == Material.sand || block.getMaterial() == Material.ground ||
                block.getMaterial() == Material.grass || block.getMaterial() == Material.rock) {
            block.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, Blocks.air);
            if (block.getMaterial() == Material.sand || block.getMaterial() == Material.ground ||
                    block.getMaterial() == Material.grass || block.getMaterial() == Material.rock) {
                breakBlock(world, x, y + 1, z, depth);
            }
            breakBlock(world, x + 1, y, z, depth + 1);
            breakBlock(world, x - 1, y, z, depth + 1);
            breakBlock(world, x, y, z + 1, depth + 1);
            breakBlock(world, x, y, z - 1, depth + 1);
        }
    }

}