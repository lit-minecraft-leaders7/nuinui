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
 * Created by nui on 16/02/07.
 */
public class BlockBreakEventHandler {
    private static final int MAX_DEPTH = 5;

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
        if (!(item == Items.wooden_axe || item == Items.stone_axe ||
                item == Items.iron_axe || item == Items.golden_axe || item == Items.diamond_axe)) {
            return;
        }
        Block block = event.block;
        if (block.getMaterial() != Material.wood) {
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

        if (block.getMaterial() == Material.wood || block.getMaterial() == Material.leaves) {
            block.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, Blocks.air);
            if (block.getMaterial() == Material.wood) {
                breakBlock(world, x, y + 1, z, depth);
                breakBlock(world, x, y - 1, z, depth);
            }
            breakBlock(world, x + 1, y, z, depth + 1);
            breakBlock(world, x - 1, y, z, depth + 1);
            breakBlock(world, x, y, z + 1, depth + 1);
            breakBlock(world, x, y, z - 1, depth + 1);
        }
    }
}
