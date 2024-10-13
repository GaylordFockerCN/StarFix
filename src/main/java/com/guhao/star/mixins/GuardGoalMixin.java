//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins;

import com.guhao.star.regirster.Effect;
import com.nameless.indestructible.world.ai.goal.GuardGoal;
import com.nameless.indestructible.world.capability.AdvancedCustomHumanoidMobPatch;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
    value = {GuardGoal.class},
    remap = false
)
public class GuardGoalMixin<T extends AdvancedCustomHumanoidMobPatch<?>> extends Goal {
    @Mutable
    @Final
    @Shadow
    private final T mobpatch;

    public GuardGoalMixin(T mobpatch) {
        this.mobpatch = mobpatch;
    }

    @Shadow
    private boolean checkTargetValid() {
        return false;
    }

    public boolean canUse() {
        return this.checkTargetValid() && this.mobpatch.isBlocking();
    }

    @Inject(
        method = {"stop"},
        at = {@At("HEAD")},
        cancellable = true
    )
    public void stop(CallbackInfo ci) {
        if (((PathfinderMob)this.mobpatch.getOriginal()).hasEffect((MobEffect)Effect.EXECUTED.get())) {
            ci.cancel();
        }

    }
}
