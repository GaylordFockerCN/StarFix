//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import org.jetbrains.annotations.NotNull;

public class Executed extends MobEffect {
    public Executed() {
        super(MobEffectCategory.BENEFICIAL, -13261);
    }

    public @NotNull String getDescriptionId() {
        return "effect.star.executed";
    }

    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
