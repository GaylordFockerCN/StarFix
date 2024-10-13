//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import org.jetbrains.annotations.NotNull;

public class Really_stun_immunity extends MobEffect {
    public Really_stun_immunity() {
        super(MobEffectCategory.BENEFICIAL, -13261);
    }

    public @NotNull String getDescriptionId() {
        return "effect.star.really_stun_immunity";
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
