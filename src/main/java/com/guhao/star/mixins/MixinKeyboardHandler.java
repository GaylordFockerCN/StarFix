//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins;

import com.guhao.star.regirster.Effect;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
    value = {KeyboardHandler.class},
    remap = false
)
public class MixinKeyboardHandler {
    public MixinKeyboardHandler() {
    }

    @Inject(
        at = {@At("HEAD")},
        method = {"keyPress"},
        cancellable = true
    )
    public void keyPress(long screen, int key, int scanCode, int action, int modifier, CallbackInfo callback) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen == null) {
            LocalPlayer player = mc.player;
            if (player != null && player.hasEffect((MobEffect)Effect.EXECUTE.get())) {
                callback.cancel();
            }
        }

    }
}
