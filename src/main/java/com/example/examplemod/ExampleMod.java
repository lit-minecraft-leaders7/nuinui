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

    public static final KeyBinding LKey = new KeyBinding("key.l", Keyboard.KEY_L, "leveling_switch");
    public static final MyBlockBreakEventHandler myBlockBreakEventHandler = new MyBlockBreakEventHandler();

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MyBlocks.myblock = new MyBlock();
        MyBlocks.rainbow = new BlockRainbow();
        MyBlocks.blocksound = new BlockSound();
        MyBlocks.redstone_input = new BlockRedstoneInput();
        MyBlocks.redstone_clock = new BlockRedstoneClock();
        MyBlocks.footprintssand = new FootprintsSand();
        MyBlocks.temporary_stone = new BlockTemporaryStone();

        MyItems.myitem = new MyItem();
        MyItems.imprisonment_sword = new ImprisonmentSword();
        MyItems.mysnowball = new ItemMySnowball();

        GameRegistry.registerBlock(MyBlocks.myblock, "myblock");
        GameRegistry.registerBlock(MyBlocks.rainbow, "rainbow");
        GameRegistry.registerBlock(MyBlocks.blocksound, "BlockSound");
        GameRegistry.registerBlock(MyBlocks.temporary_stone, "temporary_stone");
        GameRegistry.registerBlock(MyBlocks.redstone_input, "redstone_input");
        GameRegistry.registerBlock(MyBlocks.redstone_clock, "redstone_clock");
        GameRegistry.registerBlock(MyBlocks.footprintssand, "footprintssand");

        GameRegistry.registerItem(MyItems.mysnowball, "snow_ball");
        GameRegistry.registerItem(MyItems.myitem, "my_sword");
        PotionEffect[] onigiri = {
                new PotionEffect(Potion.regeneration.id, 1200, 1),
                new PotionEffect(Potion.damageBoost.id, 1200, 1),
                new PotionEffect(Potion.moveSpeed.id, 1200, 1),
                new PotionEffect(Potion.jump.id, 600, 0)
        };
        GameRegistry.registerItem(new ItemModFood("Onigiri", 1, 0.5f, false, onigiri).setAlwaysEdible(), "Onigiri");
        GameRegistry.registerItem(MyItems.imprisonment_sword, "imprisonment_sword");

        GameRegistry.addShapelessRecipe(new ItemStack(MyBlocks.rainbow), new ItemStack(Blocks.dirt));
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
        GameRegistry.addRecipe(new ItemStack(MyBlocks.temporary_stone),
                " A ",
                "AAA",
                " A ",
                'A', Blocks.stone);
        GameRegistry.addRecipe(new ItemStack(MyItems.myitem, 1, 50),
                " B ",
                "BAB",
                " B ",
                'A', new ItemStack(Items.diamond_sword),
                'B', Items.gunpowder);
        GameRegistry.addRecipe(new ItemStack(MyItems.imprisonment_sword, 1, 50),
                " B ",
                "BAB",
                " B ",
                'A', new ItemStack(Items.iron_sword),
                'B', Items.gunpowder);

        MinecraftForge.EVENT_BUS.register(new BlockBreakEventHandler());
        MinecraftForge.EVENT_BUS.register(myBlockBreakEventHandler);
        ClientRegistry.registerKeyBinding(LKey);
        FMLCommonHandler.instance().bus().register(new MyKeyInputHandler());
    }
}
