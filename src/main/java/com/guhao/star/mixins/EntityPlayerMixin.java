//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins;

import com.guhao.star.regirster.Effect;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Player.class})
public class EntityPlayerMixin {
    public EntityPlayerMixin() {
    }

    @Inject(
        method = {"hurt"},
        at = {@At("HEAD")},
        cancellable = true
    )
    public void hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity var5 = source.getEntity();
        if (var5 instanceof LivingEntity livingEntity) {
            if (livingEntity.hasEffect((MobEffect)Effect.EXECUTE.get()) || livingEntity.hasEffect((MobEffect)Effect.EXECUTED.get())) {
                cir.setReturnValue(false);
            }
        }

    }
}
