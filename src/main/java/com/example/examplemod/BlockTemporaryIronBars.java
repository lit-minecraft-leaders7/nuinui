package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;


/**
 * Created by nui on 16/02/09.
 */
public class BlockTemporaryIronBars extends BlockPane {

    public BlockTemporaryIronBars() {
        super("iron", "temporaryironbars", Material.iron, false);
        setBlockName(ExampleMod.MODID + "_temporaryironbars");
        setBlockTextureName(ExampleMod.MODID + ":temporaryironbars");
        this.setCreativeTab(CreativeTabs.tabDecorations);
        setHardness(10.0f);
        setResistance(30.0f);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        destroyBlock(world, x, y, z);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        world.scheduleBlockUpdateWithPriority(x, y, z, this, 10000, 100);
    }

    private void destroyBlock(World world, int x, int y, int z) {
        world.setBlock(x, y, z, Blocks.air);
    }

}
