package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.world.WorldManager;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by nui on 16/02/08.
 */
public class ImprisonmentSword extends ItemSword {

    public ImprisonmentSword() {
        super(EnumHelper.addToolMaterial("Imprisonment_Sword", 4, 2000, 16.0f, 1.0f, 22));
        setUnlocalizedName(ExampleMod.MODID + "_Imprisonment_Sword");
        setTextureName(ExampleMod.MODID + ":Imprisonment_Sword");
    }

    @Override
    public boolean hitEntity(ItemStack item, EntityLivingBase enemy, EntityLivingBase attacker) {
        if (item == null) {
            return true;
        }

        if (!(attacker instanceof EntityPlayer)) {
            return true;
        }


        setIronBar(enemy, -1, 0, -1);
        setIronBar(enemy, -1, 0, 0);
        setIronBar(enemy, -1, 0, 1);
        setIronBar(enemy, 0, 0, -1);
        setIronBar(enemy, 0, 0, 1);
        setIronBar(enemy, 1, 0, -1);
        setIronBar(enemy, 1, 0, 0);
        setIronBar(enemy, 1, 0, 1);

        setIronBar(enemy, -1, 1, -1);
        setIronBar(enemy, -1, 1, 0);
        setIronBar(enemy, -1, 1, 1);
        setIronBar(enemy, 0, 1, -1);
        setIronBar(enemy, 0, 1, 1);
        setIronBar(enemy, 1, 1, -1);
        setIronBar(enemy, 1, 1, 0);
        setIronBar(enemy, 1, 1, 1);

        setIronBar(enemy, -1, 2, -1);
        setIronBar(enemy, -1, 2, 0);
        setIronBar(enemy, -1, 2, 1);
        setIronBar(enemy, 0, 2, -1);
        setIronBar(enemy, 0, 2, 1);
        setIronBar(enemy, 1, 2, -1);
        setIronBar(enemy, 1, 2, 0);
        setIronBar(enemy, 1, 2, 1);

        enemy.setPosition(enemy.posX, enemy.posY + 1, enemy.posZ);

        return true;
    }

    private void setIronBar(EntityLivingBase enemy, int deltaX, int deltaY, int deltaZ) {
        Block block = enemy.worldObj.getBlock((int) enemy.posX + deltaX, (int) enemy.posY + deltaY, (int) enemy.posZ + deltaZ);

        if (block.getMaterial() != Material.air) {
            return;
        }

        enemy.worldObj.setBlock(
                (int) enemy.posX + deltaX, (int) enemy.posY + deltaY, (int) enemy.posZ + deltaZ,
                (Block)Block.blockRegistry.getObject("temporary_iron_bar"));
    }
}
