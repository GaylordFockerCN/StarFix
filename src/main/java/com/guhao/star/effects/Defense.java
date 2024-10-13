//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import org.jetbrains.annotations.NotNull;

public class Defense extends MobEffect {
    public Defense() {
        super(MobEffectCategory.HARMFUL, -13261);
    }

    public @NotNull String getDescriptionId() {
        return "effect.sword.defense";
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
