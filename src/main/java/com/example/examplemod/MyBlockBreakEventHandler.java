package com.example.examplemod;

        import cpw.mods.fml.common.eventhandler.SubscribeEvent;
        import cpw.mods.fml.relauncher.Side;
        import cpw.mods.fml.relauncher.SideOnly;
        import net.minecraft.block.Block;
        import net.minecraft.block.material.Material;
        import net.minecraft.client.Minecraft;
        import net.minecraft.entity.player.EntityPlayer;
        import net.minecraft.init.Blocks;
        import net.minecraft.init.Items;
        import net.minecraft.item.Item;
        import net.minecraft.util.ChatComponentText;
        import net.minecraft.world.World;
        import net.minecraftforge.event.world.BlockEvent;

/**
 * Created by nui on 16/02/08.
 */
public class MyBlockBreakEventHandler {
    private static int MAX_XZ_DEPTH = 20;
    private static int MAX_Y_DEPTH = 40;
    private static boolean activeness = true;

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!activeness) {
            return;
        }
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
        breakBlock(event.world, event.x, event.y, event.z, 1, 1);
    }

    @SideOnly(Side.CLIENT)
    public void toggleActiveness() {
        Minecraft minecraft = Minecraft.getMinecraft();
        activeness = !activeness;
        if (activeness) {
            minecraft.thePlayer.addChatComponentMessage(new ChatComponentText("Leveling Tool has been enabled."));
        } else {
            minecraft.thePlayer.addChatComponentMessage(new ChatComponentText("Leveling Tool had been disabled."));
        }
    }

    private void breakBlock(World world, int x, int y, int z, int xzDepth, int yDepth) {
        if (xzDepth > MAX_XZ_DEPTH || yDepth > MAX_Y_DEPTH) {
            return;
        }

        Block block = world.getBlock(x, y, z);

        if (block.getMaterial() == Material.sand || block.getMaterial() == Material.ground ||
                block.getMaterial() == Material.grass || block.getMaterial() == Material.rock) {
            block.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, Blocks.air);
            if (block.getMaterial() == Material.sand || block.getMaterial() == Material.ground ||
                    block.getMaterial() == Material.grass || block.getMaterial() == Material.rock) {
                breakBlock(world, x, y + 1, z, xzDepth, yDepth + 1);
            }
            breakBlock(world, x + 1, y, z, xzDepth + 1, yDepth);
            breakBlock(world, x - 1, y, z, xzDepth + 1, yDepth);
            breakBlock(world, x, y, z + 1, xzDepth + 1, yDepth);
            breakBlock(world, x, y, z - 1, xzDepth + 1, yDepth);
        }
    }
}
