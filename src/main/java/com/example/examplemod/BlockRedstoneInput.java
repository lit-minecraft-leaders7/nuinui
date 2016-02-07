
package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by nui on 16/02/07.
 */
public class BlockRedstoneInput extends Block {
    public  IIcon[] icons = new IIcon[4];

    public BlockRedstoneInput() {
        super(Material.rock);
        setCreativeTab(CreativeTabs.tabRedstone);

        setBlockName(ExampleMod.MODID + "BlockRedstoneInput");
        setBlockTextureName(ExampleMod.MODID + ":redstone_input");

        setHardness(100F);
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        for (int i = 0; i < 4; i++) {
            this.icons[i] = register.registerIcon(textureName + "_" + i);
        }
    }

    @Override
    public IIcon getIcon(int size, int metadata) {
        return icons[metadata];
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        int sum = world.getBlockPowerInput(x, y, z);

        if (sum != 0) {
            System.out.println("Start Input");
            System.out.println("input " + sum);
        } else {
            System.out.println("End Input");
        }
        world.setBlockMetadataWithNotify(x, y, z, sum / 4, 2);
    }
}