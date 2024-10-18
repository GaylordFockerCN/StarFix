//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.skills;

import com.google.common.collect.Maps;
import com.guhao.star.efmex.StarAnimations;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import yesman.epicfight.api.animation.AnimationPlayer;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener.EventType;

public class YamatoSkill extends WeaponInnateSkill {
    private static final UUID EVENT_UUID = UUID.fromString("f082557a-b2f9-11eb-8529-0242ac130003");
    private final Map<ResourceLocation, Supplier<AttackAnimation>> comboAnimation = Maps.newHashMap();
    private PlayerEventListener listener;
    private static int power3RepeatHits = 0;
    private boolean canExecutePower1 = true;

    public YamatoSkill(Skill.Builder<? extends Skill> builder) {
        super(builder);
        this.comboAnimation.put(StarAnimations.YAMATO_AUTO1.getRegistryName(), () -> {
            return (AttackAnimation)StarAnimations.YAMATO_POWER1;});this.comboAnimation.put(StarAnimations.YAMATO_AUTO2.getRegistryName(), () -> {
            return (AttackAnimation)StarAnimations.YAMATO_POWER2;});this.comboAnimation.put(StarAnimations.YAMATO_AUTO3.getRegistryName(), () -> {return (AttackAnimation)StarAnimations.YAMATO_POWER3;});this.comboAnimation.put(StarAnimations.YAMATO_POWER3.getRegistryName(), () -> {
            return (AttackAnimation)StarAnimations.YAMATO_POWER3_REPEAT;});this.comboAnimation.put(StarAnimations.YAMATO_POWER3_REPEAT.getRegistryName(), () -> {
            return (AttackAnimation)StarAnimations.YAMATO_POWER3_REPEAT;});this.comboAnimation.put(StarAnimations.YAMATO_DASH.getRegistryName(), () -> {
            return (AttackAnimation)StarAnimations.YAMATO_POWER_DASH;
        });
    }

    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        PlayerEventListener listener = container.getExecuter().getEventListener();
        listener.addEventListener(EventType.ACTION_EVENT_CLIENT, EVENT_UUID, (event) -> {
            if (event.getAnimation().getRegistryName().equals(StarAnimations.YAMATO_POWER1.getRegistryName()) || event.getAnimation().getRegistryName().equals(StarAnimations.YAMATO_POWER2.getRegistryName()) || event.getAnimation().getRegistryName().equals(StarAnimations.YAMATO_POWER3_FINISH.getRegistryName())) {
                this.setCanExecutePower1(false);}});
        listener.addEventListener(EventType.ATTACK_ANIMATION_END_EVENT, EVENT_UUID, (event) -> {
            if (event.getAnimation().getRegistryName().equals(StarAnimations.YAMATO_POWER1.getRegistryName()) || event.getAnimation().getRegistryName().equals(StarAnimations.YAMATO_POWER2.getRegistryName()) || event.getAnimation().getRegistryName().equals(StarAnimations.YAMATO_POWER3_FINISH.getRegistryName())
                    || event.getAnimation().getRegistryName().equals(StarAnimations.YAMATO_STEP_LEFT.getRegistryName())  || event.getAnimation().getRegistryName().equals(StarAnimations.YAMATO_STEP_RIGHT.getRegistryName())|| event.getAnimation().getRegistryName().equals(StarAnimations.YAMATO_STEP_BACKWARD.getRegistryName())
                    || event.getAnimation().getRegistryName().equals(StarAnimations.YAMATO_STEP_FORWARD.getRegistryName())
                    || event.getAnimation().getRegistryName().equals(Animations.BIPED_STEP_BACKWARD.getRegistryName())  || event.getAnimation().getRegistryName().equals(Animations.BIPED_STEP_FORWARD.getRegistryName())|| event.getAnimation().getRegistryName().equals(Animations.BIPED_STEP_LEFT.getRegistryName())
                    || event.getAnimation().getRegistryName().equals(Animations.BIPED_STEP_LEFT.getRegistryName())) {
                this.setCanExecutePower1(true);
            }

            int id = event.getAnimation().getId();
            if (id != StarAnimations.YAMATO_POWER3.getId() && id != StarAnimations.YAMATO_POWER3_REPEAT.getId()) {
                if (id == StarAnimations.YAMATO_COUNTER1.getId()) {
                    ((ServerPlayerPatch)event.getPlayerPatch()).reserveAnimation(StarAnimations.YAMATO_COUNTER2);}
                if (id == StarAnimations.YAMATO_STRIKE2.getId()) {((ServerPlayerPatch)event.getPlayerPatch()).reserveAnimation(StarAnimations.YAMATO_AUTO4);}
            } else {
                ((ServerPlayerPatch)event.getPlayerPatch()).reserveAnimation(StarAnimations.YAMATO_POWER3_FINISH);
            }

        });
        listener.addEventListener(EventType.HURT_EVENT_PRE, EVENT_UUID, (event) -> {
            ServerPlayerPatch executer = (ServerPlayerPatch)event.getPlayerPatch();
            AnimationPlayer animationPlayer = executer.getAnimator().getPlayerFor((DynamicAnimation)null);
            float elapsedTime = animationPlayer.getElapsedTime();
            if (elapsedTime <= 0.15F) {
                int animationId = executer.getAnimator().getPlayerFor((DynamicAnimation)null).getAnimation().getId();
                if (animationId == StarAnimations.YAMATO_POWER0_1.getId()) {
                    DamageSource damagesource = (DamageSource)event.getDamageSource();
                    if (damagesource instanceof EntityDamageSource && !damagesource.isExplosion() && !damagesource.isMagic() && !damagesource.isBypassArmor() && !damagesource.isBypassInvul()) {
                        this.POWER2(executer);
                    }
                }
            }

        });
    }

    public void onRemoved(SkillContainer container) {
        container.getExecuter().getEventListener().removeListener(EventType.HURT_EVENT_PRE, EVENT_UUID);}
    private void counter(ServerPlayerPatch executer) {
        executer.playAnimationSynchronized(StarAnimations.YAMATO_COUNTER1, 0.25F);}
    private void INcounter(ServerPlayerPatch executer) {
        executer.playAnimationSynchronized(StarAnimations.YAMATO_STRIKE2, 0.25F);
        float maxStamina = executer.getMaxStamina();
        float currentStamina = executer.getStamina();
        float staminaToConsume = maxStamina * 0.3F;
        executer.setStamina(currentStamina - staminaToConsume);}
    private void POWER2(ServerPlayerPatch executer) {
        executer.playAnimationSynchronized(StarAnimations.YAMATO_POWER0_2, 0.25F);
    }

    private void STRIKE(ServerPlayerPatch executer) {
        executer.playAnimationSynchronized(StarAnimations.YAMATO_STRIKE1, 0.25F);
    }

    private void POWER1(ServerPlayerPatch executer) {
        if (this.canExecutePower1) {
            float currentStamina = executer.getStamina();
            float staminaCost = 3.0F;
            if (currentStamina >= staminaCost) {
                executer.playAnimationSynchronized(StarAnimations.YAMATO_POWER0_1, 0.0F);
                executer.setStamina(currentStamina - staminaCost);
            }
        }

    }
    public static float adddamage() {
        float baseDamage = 5.0F;
        float additionalDamage = power3RepeatHits++ * 2.0f;
        return baseDamage + additionalDamage;
    }
    private void setCanExecutePower1(boolean value) {
        this.canExecutePower1 = value;
    }

    public void executeOnServer(ServerPlayerPatch executer, FriendlyByteBuf args) {
        ResourceLocation rl = executer.getAnimator().getPlayerFor((DynamicAnimation)null).getAnimation().getRegistryName();
        if (this.comboAnimation.containsKey(rl)) {
            executer.playAnimationSynchronized((StaticAnimation)((Supplier)this.comboAnimation.get(rl)).get(), 0.2F);
        } else {
            this.POWER1(executer);
        }
        if (rl.equals(StarAnimations.YAMATO_ACTIVE_GUARD_HIT.getRegistryName()) || rl.equals(StarAnimations.YAMATO_ACTIVE_GUARD_HIT2.getRegistryName())) {
            this.counter(executer);
        }
        if (rl.equals(StarAnimations.YAMATO_COUNTER1.getRegistryName())) {
            this.INcounter(executer);
        }
        if (rl.equals(StarAnimations.YAMATO_POWER0_2.getRegistryName())) {
            this.STRIKE(executer);
        }

        super.executeOnServer(executer, args);
    }

    public WeaponInnateSkill registerPropertiesToAnimation() {
        this.comboAnimation.values().forEach((animation) -> {
            ((AttackAnimation)animation.get()).phases[0].addProperties(((Map)this.properties.get(0)).entrySet());
        });
        return this;
    }
}
