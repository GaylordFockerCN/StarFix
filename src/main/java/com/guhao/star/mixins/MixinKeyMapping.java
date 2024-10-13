//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins;

import com.guhao.star.regirster.Effect;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({KeyMapping.class})
public class MixinKeyMapping {
    @Shadow
    boolean isDown;

    public MixinKeyMapping() {
    }

    @Inject(
        at = {@At("HEAD")},
        method = {"isDown"},
        cancellable = true
    )
    public void isDown(CallbackInfoReturnable<Boolean> callback) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player != null && player.hasEffect((MobEffect)Effect.EXECUTE.get())) {
            callback.setReturnValue(false);
            callback.cancel();
            if (this.isDown) {
                this.isDown = false;
            }
        }

    }

    @Inject(
        at = {@At("HEAD")},
        method = {"consumeClick"},
        cancellable = true
    )
    public void consumeClick(CallbackInfoReturnable<Boolean> callback) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player != null && player.hasEffect((MobEffect)Effect.EXECUTE.get())) {
            callback.setReturnValue(false);
            callback.cancel();
        }

    }

    @Inject(
        at = {@At("HEAD")},
        method = {"matches"},
        cancellable = true
    )
    public void matches(int key, int scancode, CallbackInfoReturnable<Boolean> callback) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player != null && player.hasEffect((MobEffect)Effect.EXECUTE.get())) {
            callback.setReturnValue(false);
            callback.cancel();
        }

    }

    @Inject(
        at = {@At("HEAD")},
        method = {"matchesMouse"},
        cancellable = true
    )
    public void matchesMouse(int button, CallbackInfoReturnable<Boolean> callback) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player != null && player.hasEffect((MobEffect)Effect.EXECUTE.get())) {
            callback.setReturnValue(false);
            callback.cancel();
        }

    }
}
