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
                return 2;
            case MEDIUM:
                return 6;
            case LARGE:
                return 18;
            default:
                return 0;
        }
    }

    private static int getMaxYDepth() {
        Minecraft minecraft = Minecraft.getMinecraft();
        switch (modeState) {
            case SMALL:
                return 4;
            case MEDIUM:
                return 16;
            case LARGE:
                return 64;
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
        breakBlock(event, event.x, event.y, event.z, 1);
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

    private void breakBlock(BlockEvent.BreakEvent event, int x, int y, int z, int yDepth) {
        if (!(event.x - getMaxXzDepth() <= x && x <= event.x + getMaxXzDepth())) {
            return;
        }

        if (!(event.z - getMaxXzDepth() <= z && z <= event.z + getMaxXzDepth())) {
            return;
        }

        if (yDepth > getMaxYDepth()) {
            return;
        }

        Block block = event.world.getBlock(x, y, z);

        if (block.getMaterial() == Material.sand || block.getMaterial() == Material.ground ||
                block.getMaterial() == Material.grass || block.getMaterial() == Material.rock) {
            block.dropBlockAsItem(event.world, x, y, z, event.world.getBlockMetadata(x, y, z), 0);
            event.world.setBlock(x, y, z, Blocks.air);
            if (block.getMaterial() == Material.sand || block.getMaterial() == Material.ground ||
                    block.getMaterial() == Material.grass || block.getMaterial() == Material.rock) {
                breakBlock(event, x, y + 1, z, yDepth + 1);
                breakBlock(event, x + 1, y, z, yDepth);
                breakBlock(event, x - 1, y, z, yDepth);
                breakBlock(event, x, y, z + 1, yDepth);
                breakBlock(event, x, y, z - 1, yDepth);
            }
        }
    }
}
