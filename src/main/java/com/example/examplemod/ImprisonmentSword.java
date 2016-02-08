package com.example.examplemod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
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
        enemy.setPosition(enemy.posX, enemy.posY + 1, enemy.posZ);
        enemy.worldObj.setBlock((int) enemy.posX, (int) enemy.posY, (int) enemy.posZ + 1, Blocks.iron_bars);
        enemy.worldObj.setBlock((int) enemy.posX + 1, (int) enemy.posY, (int) enemy.posZ, Blocks.iron_bars);
        enemy.worldObj.setBlock((int) enemy.posX, (int) enemy.posY, (int) enemy.posZ - 1, Blocks.iron_bars);
        enemy.worldObj.setBlock((int) enemy.posX - 1, (int) enemy.posY, (int) enemy.posZ, Blocks.iron_bars);
        enemy.worldObj.setBlock((int) enemy.posX + 1, (int) enemy.posY, (int) enemy.posZ + 1, Blocks.iron_bars);
        enemy.worldObj.setBlock((int) enemy.posX + 1, (int) enemy.posY, (int) enemy.posZ - 1, Blocks.iron_bars);
        enemy.worldObj.setBlock((int) enemy.posX - 1, (int) enemy.posY, (int) enemy.posZ - 1, Blocks.iron_bars);
        enemy.worldObj.setBlock((int) enemy.posX - 1, (int) enemy.posY, (int) enemy.posZ + 1, Blocks.iron_bars);

        enemy.worldObj.setBlock((int) enemy.posX, (int) enemy.posY + 1, (int) enemy.posZ + 1, Blocks.iron_bars);
        enemy.worldObj.setBlock((int) enemy.posX + 1, (int) enemy.posY + 1, (int) enemy.posZ, Blocks.iron_bars);
        enemy.worldObj.setBlock((int) enemy.posX, (int) enemy.posY + 1, (int) enemy.posZ - 1, Blocks.iron_bars);
        enemy.worldObj.setBlock((int) enemy.posX - 1, (int) enemy.posY + 1, (int) enemy.posZ, Blocks.iron_bars);
        enemy.worldObj.setBlock((int) enemy.posX + 1, (int) enemy.posY + 1, (int) enemy.posZ + 1, Blocks.iron_bars);
        enemy.worldObj.setBlock((int) enemy.posX + 1, (int) enemy.posY + 1, (int) enemy.posZ - 1, Blocks.iron_bars);
        enemy.worldObj.setBlock((int) enemy.posX - 1, (int) enemy.posY + 1, (int) enemy.posZ - 1, Blocks.iron_bars);
        enemy.worldObj.setBlock((int) enemy.posX - 1, (int) enemy.posY + 1, (int) enemy.posZ + 1, Blocks.iron_bars);
        enemy.setPosition(enemy.posX, enemy.posY + 1, enemy.posZ);
        return true;
    }
}
