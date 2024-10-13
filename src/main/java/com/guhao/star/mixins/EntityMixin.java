//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins;

import com.guhao.star.regirster.Effect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LivingEntity.class})
public abstract class EntityMixin extends CapabilityProvider<Entity> {
    protected EntityMixin(Class<Entity> baseClass) {
        super(baseClass);
    }

    @Inject(
        at = {@At("HEAD")},
        method = {"push"},
        cancellable = true
    )
    private void collide(Entity p_21294_, CallbackInfo ci) {
        if (p_21294_ instanceof LivingEntity livingEntity) {
            if (livingEntity.hasEffect((MobEffect)Effect.EXECUTE.get()) | livingEntity.hasEffect((MobEffect)Effect.EXECUTED.get())) {
                ci.cancel();
            }

        }
    }
}
