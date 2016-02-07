package com.example.examplemod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;


/**
 * Created by nui on 16/02/07.
 */

public class ItemModFood extends ItemFood {

    private PotionEffect[] effects;

    public ItemModFood(String unlocalizedName, int healAmount, float saturationModfire, boolean wovesFavourite, PotionEffect[] effects) {
        super(healAmount, saturationModfire, wovesFavourite);
        this.setUnlocalizedName(unlocalizedName);
        this.setTextureName(ExampleMod.MODID + ":" + unlocalizedName);
        this.setCreativeTab(CreativeTabs.tabFood);
        this.effects = effects;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        super.onFoodEaten(stack, world, player);

        for (int i = 0; i < effects.length; i++) {
            if (!world.isRemote && effects[i] != null && effects[i].getPotionID() > 0) {
                player.addPotionEffect(new PotionEffect(this.effects[i].getPotionID(),
                        this.effects[i].getDuration(), this.effects[i].getAmplifier(), this.effects[i].getIsAmbient()));
            }
        }
    }
}
