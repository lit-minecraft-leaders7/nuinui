package com.example.examplemod;

import net.minecraft.block.Block;
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
public class BlockTemporaryStone extends Block {
    private IIcon[] icons = new IIcon[4];

    public BlockTemporaryStone() {
        super(Material.rock);
        setBlockName(ExampleMod.MODID + "_temporarystone");
        setBlockTextureName(ExampleMod.MODID + ":temporarystone");
        setCreativeTab(CreativeTabs.tabBlock);
        setHardness(100f);
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        for (int i = 0; i < 4; i++) {
            icons[i] = register.registerIcon(this.textureName + "_" + i);
        }
    }

    @Override
    public IIcon getIcon(int side, int metadata) {
        return icons[metadata];
    }

    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
        if (entity instanceof EntityPlayer && world.getBlockMetadata(x, y, z) != 0) {
            world.setBlockMetadataWithNotify(x, y, z, 0, 2);
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        int next = world.getBlockMetadata(x, y, z) + 1;
         if (next != 4) {
             world.scheduleBlockUpdateWithPriority(x, y, z, this, 100, 100);
             world.setBlockMetadataWithNotify(x, y, z, next, 2);
         } else {
            destroyBlock(world, x, y, z);
        }
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        world.scheduleBlockUpdateWithPriority(x, y, z, this, 100, 100);
    }

    private void destroyBlock(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        block.dropBlockAsItem(world, x, y, z, 0, 0);
        world.setBlock(x, y, z, Blocks.air);
    }


}
