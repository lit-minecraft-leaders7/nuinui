package com.example.examplemod;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod {
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";
    public static final Block RAINBOW = new BlockRainbow();
    public static final Block blocksound = new BlockSound();
    public static final KeyBinding LKey = new KeyBinding("key.l", Keyboard.KEY_L, "leveling_switch");
    public static final MyBlockBreakEventHandler myBlockBreakEventHandler = new MyBlockBreakEventHandler();

    @EventHandler
    public void init(FMLInitializationEvent event) {
        //dirt to diamond
        GameRegistry.addRecipe(new ItemStack(Blocks.diamond_block),
                "AAA",
                "AAA",
                "AAA",
                'A', Blocks.dirt);
        GameRegistry.addRecipe(new ItemStack(Items.spawn_egg, 1, 50),
                " A ",
                "CBC",
                "CBC",
                'A', new ItemStack(Items.skull, 1, 4),
                'B', Blocks.tnt,
                'C', Items.gunpowder);

        //myblock
        GameRegistry.registerBlock(new MyBlock(), "myblock");

        GameRegistry.registerBlock(RAINBOW, "rainbow");
        GameRegistry.registerBlock(blocksound, "BlockSound");
        GameRegistry.addShapelessRecipe(new ItemStack(RAINBOW), new ItemStack(Blocks.dirt));

        temporaryStone();

        customSword();
        customFood();
        imprisonmentSword();

        MinecraftForge.EVENT_BUS.register(new BlockBreakEventHandler());
        MinecraftForge.EVENT_BUS.register(myBlockBreakEventHandler);
        ClientRegistry.registerKeyBinding(LKey);
        FMLCommonHandler.instance().bus().register(new MyKeyInputHandler());

        GameRegistry.registerBlock(new BlockRedstoneInput(), "redstone_input");
        GameRegistry.registerBlock(new BlockRedstoneClock(), "redstone_clock");

        GameRegistry.registerItem(new ItemMySnowball(), "snow_ball");

        GameRegistry.registerBlock(new FootprintsSand(), "footprintssand");
    }

    public void temporaryStone() {
        Block stone = new BlockTemporaryStone();

        GameRegistry.registerBlock(stone, "temporary_stone");

        GameRegistry.addRecipe(new ItemStack(stone),
                " A ",
                "AAA",
                " A ",
                'A', Blocks.stone);
    }


    public void customSword() {
        Item sword = new MyItem();

        GameRegistry.registerItem(sword, "my_sword");

        GameRegistry.addRecipe(new ItemStack(sword, 1, 50),
                " B ",
                "BAB",
                " B ",
                'A', new ItemStack(Items.diamond_sword),
                'B', Items.gunpowder);
    }

    public void imprisonmentSword() {
        Item sword = new ImprisonmentSword();

        GameRegistry.registerItem(sword, "imprisonment_sword");

        GameRegistry.addRecipe(new ItemStack(sword, 1, 50),
                " B ",
                "BAB",
                " B ",
                'A', new ItemStack(Items.iron_sword),
                'B', Items.gunpowder);
    }

    public void customFood() {
        PotionEffect[] onigiri = {
                new PotionEffect(Potion.regeneration.id, 1200, 1),
                new PotionEffect(Potion.damageBoost.id, 1200, 1),
                new PotionEffect(Potion.moveSpeed.id, 1200, 1),
                new PotionEffect(Potion.jump.id, 600, 0)
        };

        GameRegistry.registerItem(new ItemModFood("Onigiri", 1, 0.5f, false, onigiri).setAlwaysEdible(), "Onigiri");
    }
}
