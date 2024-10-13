//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins;

import com.guhao.star.units.Guard_Array;
import java.util.function.Function;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.EntityDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationEvent.Side;
import yesman.epicfight.api.animation.property.AnimationProperty.ActionAnimationProperty;
import yesman.epicfight.api.animation.property.AnimationProperty.StaticAnimationProperty;
import yesman.epicfight.api.animation.types.ActionAnimation;
import yesman.epicfight.api.animation.types.DodgeAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.model.Armature;
import yesman.epicfight.api.utils.AttackResult;
import yesman.epicfight.api.utils.AttackResult.ResultType;
import yesman.epicfight.gameasset.Animations.ReusableSources;

@Mixin(
    value = {DodgeAnimation.class},
    remap = false
)
public abstract class DodgeAnimationMixin extends ActionAnimation {
    @Unique
    Function<DamageSource, AttackResult.ResultType> star_new$newValue = (damagesource) -> {
        if (damagesource instanceof EntityDamageSource entityDamageSource) {
            if (Guard_Array.getEpicFightDamageSources(damagesource) != null && Guard_Array.isNoDodge(Guard_Array.getEpicFightDamageSources(damagesource).getAnimation())) {
                return ResultType.SUCCESS;
            }

            if (!entityDamageSource.isExplosion() && !entityDamageSource.isMagic() && !entityDamageSource.isBypassArmor() && !entityDamageSource.isBypassInvul()) {
                return ResultType.MISSED;
            }
        }

        return ResultType.SUCCESS;
    };

    public DodgeAnimationMixin(float convertTime, String path, Armature armature) {
        super(convertTime, path, armature);
    }

    @Inject(
        method = {"<init>(FFLjava/lang/String;FFLyesman/epicfight/api/model/Armature;)V"},
        at = {@At("TAIL")},
        cancellable = true
    )
    public void DodgeAnimation(float convertTime, float delayTime, String path, float width, float height, Armature armature, CallbackInfo ci) {
        ci.cancel();
        this.stateSpectrumBlueprint.clear().newTimePair(0.0F, delayTime).addState(EntityState.TURNING_LOCKED, true).addState(EntityState.MOVEMENT_LOCKED, true).addState(EntityState.UPDATE_LIVING_MOTION, false).addState(EntityState.CAN_BASIC_ATTACK, false).addState(EntityState.CAN_SKILL_EXECUTION, false).addState(EntityState.INACTION, true).newTimePair(0.0F, Float.MAX_VALUE).addState(EntityState.ATTACK_RESULT, this.star_new$newValue);
        this.addProperty(ActionAnimationProperty.AFFECT_SPEED, true);
        this.addEvents(StaticAnimationProperty.ON_END_EVENTS, new AnimationEvent[]{AnimationEvent.create(ReusableSources.RESTORE_BOUNDING_BOX, Side.BOTH)});
        this.addEvents(StaticAnimationProperty.EVENTS, new AnimationEvent[]{AnimationEvent.create(ReusableSources.RESIZE_BOUNDING_BOX, Side.BOTH).params(new Object[]{EntityDimensions.scalable(width, height)})});
    }
}