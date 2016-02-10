package com.example.examplemod;

        import cpw.mods.fml.common.eventhandler.SubscribeEvent;
        import cpw.mods.fml.relauncher.Side;
        import cpw.mods.fml.relauncher.SideOnly;
        import net.minecraft.block.Block;
        import net.minecraft.block.material.Material;
        import net.minecraft.client.Minecraft;
        import net.minecraft.entity.player.EntityPlayer;
        import net.minecraft.init.Blocks;
        import net.minecraft.init.Items;
        import net.minecraft.item.Item;
        import net.minecraft.util.ChatComponentText;
        import net.minecraft.world.World;
        import net.minecraftforge.event.world.BlockEvent;

/**
 * Created by nui on 16/02/08.
 */
public class MyBlockBreakEventHandler {
    private enum LevelingModeState {
        DISABLED,
        SMALL,
        MEDIUM,
        LARGE;
    }

    private static LevelingModeState modeState = LevelingModeState.SMALL;

    private static int getMaxXzDepth() {
        Minecraft minecraft = Minecraft.getMinecraft();
        switch (modeState) {
            case SMALL:
                return 5;
            case MEDIUM:
                return 15;
            case LARGE:
                return 45;
            default:
                return 0;
        }
    }

    private static int getMaxYDepth() {
        Minecraft minecraft = Minecraft.getMinecraft();
        switch (modeState) {
            case SMALL:
                return 5;
            case MEDIUM:
                return 20;
            case LARGE:
                return 80;
            default:
                return 0;
        }
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if (modeState == LevelingModeState.DISABLED) {
            return;
        }
        EntityPlayer player = event.getPlayer();
        if (player == null) {
            return;
        }
        if (player.getHeldItem() == null) {
            return;
        }
        Item item = player.getHeldItem().getItem();
        if (!(item == Items.wooden_shovel || item == Items.stone_shovel ||
                item == Items.iron_shovel || item == Items.golden_shovel || item == Items.diamond_shovel)) {
            return;
        }
        Block block = event.block;
        if (!(block.getMaterial() == Material.sand || block.getMaterial() == Material.ground ||
                block.getMaterial() == Material.grass || block.getMaterial() == Material.rock)) {
            return;
        }
        event.setCanceled(true);
        breakBlock(event.world, event.x, event.y, event.z, 1, 1);
    }

    @SideOnly(Side.CLIENT)
    public void changeModeState() {
        Minecraft minecraft = Minecraft.getMinecraft();
        switch (modeState) {
            case DISABLED:
                modeState = LevelingModeState.SMALL;
                minecraft.thePlayer.addChatComponentMessage(new ChatComponentText("Leveling Tool: small mode"));
                break;
            case SMALL:
                modeState = LevelingModeState.MEDIUM;
                minecraft.thePlayer.addChatComponentMessage(new ChatComponentText("Leveling Tool: medium mode"));
                break;
            case MEDIUM:
                modeState = LevelingModeState.LARGE;
                minecraft.thePlayer.addChatComponentMessage(new ChatComponentText("Leveling Tool: large mode"));
                break;
            default:
                modeState = LevelingModeState.DISABLED;
                minecraft.thePlayer.addChatComponentMessage(new ChatComponentText("Leveling Tool: disabled"));
                break;
        }
    }

    private void breakBlock(World world, int x, int y, int z, int xzDepth, int yDepth) {
        if (xzDepth > getMaxXzDepth() || yDepth > getMaxYDepth()) {
            return;
        }

        Block block = world.getBlock(x, y, z);

        if (block.getMaterial() == Material.sand || block.getMaterial() == Material.ground ||
                block.getMaterial() == Material.grass || block.getMaterial() == Material.rock) {
            block.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, Blocks.air);
            if (block.getMaterial() == Material.sand || block.getMaterial() == Material.ground ||
                    block.getMaterial() == Material.grass || block.getMaterial() == Material.rock) {
                breakBlock(world, x, y + 1, z, xzDepth, yDepth + 1);
            }
            breakBlock(world, x + 1, y, z, xzDepth + 1, yDepth);
            breakBlock(world, x - 1, y, z, xzDepth + 1, yDepth);
            breakBlock(world, x, y, z + 1, xzDepth + 1, yDepth);
            breakBlock(world, x, y, z - 1, xzDepth + 1, yDepth);
        }
    }
}
