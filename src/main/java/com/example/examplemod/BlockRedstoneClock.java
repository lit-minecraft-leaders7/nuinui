package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;

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
        return 15;
    }
}
