package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by frevmet on 16/02/07.
 */
public class BlockRedstoneClock extends Block {
    public BlockRedstoneClock() {
        super(Material.rock);
        setCreativeTab(CreativeTabs.tabRedstone);

        setBlockName(ExampleMod.MODID + "BlockRedstoneClock");
        setBlockTextureName(ExampleMod.MODID + ":redstone_clock");

        setHardness(100F);
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 15, 3);
        } else {
            world.setBlockMetadataWithNotify(x, y, z, 0, 3);
        }

        world.scheduleBlockUpdateWithPriority(x, y, z, this, 5, 100);
    }
    
    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        world.scheduleBlockUpdateWithPriority(x, y, z, this, 5, 100);
    }
}
