//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins;

import com.guhao.star.regirster.Effect;
import com.guhao.star.regirster.Sounds;
import com.nameless.indestructible.api.animation.types.CustomGuardAnimation;
import com.nameless.indestructible.data.AdvancedMobpatchReloader;
import com.nameless.indestructible.world.capability.AdvancedCustomHumanoidMobPatch;
import java.util.Random;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.utils.AttackResult;
import yesman.epicfight.api.utils.AttackResult.ResultType;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.particle.HitParticleType;
import yesman.epicfight.world.capabilities.entitypatch.Faction;
import yesman.epicfight.world.capabilities.entitypatch.HumanoidMobPatch;
import yesman.epicfight.world.damagesource.EpicFightDamageSource;
import yesman.epicfight.world.damagesource.SourceTags;
import yesman.epicfight.world.damagesource.StunType;
import yesman.epicfight.world.effect.EpicFightMobEffects;

@Mixin(
    value = {AdvancedCustomHumanoidMobPatch.class},
    remap = false
)
public abstract class AdvancedCustomHumanoidMobPatchMixin<T extends PathfinderMob> extends HumanoidMobPatch<T> {
    @Mutable
    @Final
    @Shadow
    private final AdvancedMobpatchReloader.AdvancedCustomHumanoidMobPatchProvider provider;
    @Shadow
    private boolean cancel_block;
    @Shadow
    private int maxParryTimes;
    @Shadow
    private boolean isParry;
    @Shadow
    private int parryCounter = 0;
    @Shadow
    private int parryTimes = 0;
    @Shadow
    private int stun_immunity_time;
    @Shadow
    private boolean isRunning = false;
    @Shadow
    private float lastGetImpact;

    public AdvancedCustomHumanoidMobPatchMixin(Faction faction, AdvancedMobpatchReloader.AdvancedCustomHumanoidMobPatchProvider provider) {
        super(faction);
        this.provider = provider;
    }

    @Shadow
    public boolean canBlockProjectile() {
        return true;
    }

    @Shadow
    public boolean isBlocking() {
        return false;
    }

    @Shadow
    public CustomGuardAnimation getGuardAnimation() {
        return null;
    }

    @Shadow
    public float getParryCostMultiply() {
        return 0.0F;
    }

    @Shadow
    public StaticAnimation getParryAnimation() {
        return null;
    }

    @Shadow
    public float getMaxStamina() {
        return 0.0F;
    }

    @Shadow
    public float getStamina() {
        return 0.0F;
    }

    @Shadow
    public void setStamina(float value) {
    }

    @Shadow
    public void setAttackSpeed(float value) {
    }

    @Shadow
    public float getGuardCostMultiply() {
        return 0.0F;
    }

    @Shadow
    public void setBlocking(boolean blocking) {
    }

    @Shadow
    public StaticAnimation getCounter() {
        return null;
    }

    @Shadow
    public float getCounterChance() {
        return 0.0F;
    }

    @Shadow
    public float getCounterStamina() {
        return 0.0F;
    }

    @Shadow
    public float getCounterSpeed() {
        return 0.0F;
    }

    @Inject(
        method = {"setAIAsInfantry"},
        at = {@At("HEAD")},
        cancellable = true
    )
    public void setAIAsInfantry(boolean holdingRanedWeapon, CallbackInfo ci) {
        if (((PathfinderMob)this.getOriginal()).hasEffect((MobEffect)Effect.EXECUTED.get())) {
            ci.cancel();
        }

    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    private AttackResult tryProcess(DamageSource damageSource, float amount) {
        if (this.isBlocking()) {
            CustomGuardAnimation animation = this.getGuardAnimation();
            StaticAnimation success = animation.successAnimation != null ? EpicFightMod.getInstance().animationManager.findAnimationByPath(animation.successAnimation) : Animations.SWORD_GUARD_HIT;
            boolean isFront = false;
            boolean canBlockSource = !damageSource.isExplosion() && !damageSource.isMagic() && !damageSource.isBypassInvul() && (!damageSource.isProjectile() || this.canBlockProjectile());
            Vec3 sourceLocation = damageSource.getSourcePosition();
            if (sourceLocation != null) {
                Vec3 viewVector = ((PathfinderMob)this.getOriginal()).getViewVector(1.0F);
                Vec3 toSourceLocation = sourceLocation.subtract(((PathfinderMob)this.getOriginal()).position()).normalize();
                if (toSourceLocation.dot(viewVector) > 0.0) {
                    isFront = true;
                }
            }

            if (canBlockSource && isFront) {
                float impact;
                if (damageSource instanceof EpicFightDamageSource) {
                    EpicFightDamageSource efDamageSource = (EpicFightDamageSource)damageSource;
                    impact = amount / 4.0F * (1.0F + efDamageSource.getImpact() / 2.0F);
                    if (efDamageSource.hasTag(SourceTags.GUARD_PUNCTURE)) {
//                        impact = Float.MAX_VALUE;
                        return new AttackResult(ResultType.SUCCESS, amount);
                    }
                } else {
                    impact = amount / 3.0F;
                }

                float knockback = 0.25F + Math.min(impact * 0.1F, 1.0F);
                Entity var11 = damageSource.getDirectEntity();
                if (var11 instanceof LivingEntity) {
                    LivingEntity targetEntity = (LivingEntity)var11;
                    knockback += (float)EnchantmentHelper.getKnockbackBonus(targetEntity) * 0.1F;
                }

                float cost = this.isParry ? this.getParryCostMultiply() : this.getGuardCostMultiply();
                float stamina = this.getStamina() - impact * cost;
                this.setStamina(stamina);
                ((HitParticleType)EpicFightParticles.HIT_BLUNT.get()).spawnParticleWithArgument((ServerLevel)((PathfinderMob)this.getOriginal()).level, HitParticleType.FRONT_OF_EYES, HitParticleType.ZERO, this.getOriginal(), damageSource.getDirectEntity());
                if (!(stamina >= 0.0F)) {
                    this.setBlocking(false);
                    this.applyStun(StunType.NEUTRALIZE, 2.0F);
                    this.playSound(EpicFightSounds.NEUTRALIZE_MOBS, -0.05F, 0.1F);
                    this.setStamina(this.getMaxStamina());
                    return new AttackResult(ResultType.SUCCESS, amount / 2.0F);
                }

                float counter_cost = this.getCounterStamina();
                Random random = ((PathfinderMob)this.getOriginal()).getRandom();
                this.rotateTo(damageSource.getDirectEntity(), 30.0F, true);
                if (random.nextFloat() < this.getCounterChance() && stamina >= counter_cost) {
                    if (this.stun_immunity_time > 0) {
                        ((PathfinderMob)this.getOriginal()).addEffect(new MobEffectInstance((MobEffect)EpicFightMobEffects.STUN_IMMUNITY.get(), this.stun_immunity_time));
                    }

                    this.setAttackSpeed(this.getCounterSpeed());
                    this.playAnimationSynchronized(this.getCounter(), 0.0F);
                    if (this.isParry) {
                        this.playSound(Sounds.BIGBONG, -0.31F, -0.27F);
                    } else {
                        this.playSound(Sounds.BONG, -0.31F, -0.27F);
                    }

                    if (this.cancel_block) {
                        this.setBlocking(false);
                    }

                    this.setStamina(this.getStamina() - counter_cost);
                } else if (this.isParry) {
                    if (this.parryCounter + 1 >= this.maxParryTimes) {
                        this.setBlocking(false);
                        if (this.stun_immunity_time > 0) {
                            ((PathfinderMob)this.getOriginal()).addEffect(new MobEffectInstance((MobEffect)EpicFightMobEffects.STUN_IMMUNITY.get(), this.stun_immunity_time));
                        }
                    }

                    this.playAnimationSynchronized(this.getParryAnimation(), 0.0F);
                    ++this.parryCounter;
                    ++this.parryTimes;
                    if (this.isParry) {
                        this.playSound(Sounds.BIGBONG, -0.31F, -0.27F);
                    } else {
                        this.playSound(Sounds.BONG, -0.31F, -0.27F);
                    }

                    this.knockBackEntity(damageSource.getDirectEntity().position(), 0.4F * knockback);
                } else {
                    this.playAnimationSynchronized(success, 0.1F);
                    if (animation.isShield) {
                        this.playSound(SoundEvents.SHIELD_BLOCK, -0.05F, 0.1F);
                    } else if (this.isParry) {
                        this.playSound(Sounds.BIGBONG, -0.31F, -0.27F);
                    } else {
                        this.playSound(Sounds.BONG, -0.31F, -0.27F);
                    }

                    this.knockBackEntity(damageSource.getDirectEntity().position(), knockback);
                }

                return new AttackResult(ResultType.BLOCKED, amount);
            }
        }

        if (damageSource instanceof EpicFightDamageSource efDamageSource) {
            this.lastGetImpact = efDamageSource.getImpact();
        } else {
            this.lastGetImpact = amount / 3.0F;
        }

        return new AttackResult(ResultType.SUCCESS, amount);
    }
}
