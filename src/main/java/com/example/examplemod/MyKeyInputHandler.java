package com.example.examplemod;

        import cpw.mods.fml.common.eventhandler.SubscribeEvent;
        import cpw.mods.fml.common.gameevent.InputEvent;


/**
 * Created by nui on 16/02/08.
 */
public class MyKeyInputHandler {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (ExampleMod.LKey.isPressed()) {
            ExampleMod.myBlockBreakEventHandler.changeModeState();
        }
    }
}