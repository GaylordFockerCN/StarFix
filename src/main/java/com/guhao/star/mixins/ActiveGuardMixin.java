//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins;

import com.guhao.init.Items;
import com.guhao.star.api.StarAPI;
import com.guhao.star.efmex.StarAnimations;
import com.guhao.star.efmex.StarWeaponCategory;
import com.guhao.star.regirster.Effect;
import com.guhao.star.regirster.Sounds;
import com.guhao.star.units.Guard_Array;
import com.nameless.indestructible.world.capability.AdvancedCustomHumanoidMobPatch;
import com.nameless.toybox.common.attribute.ai.ToyBoxAttributes;
import java.util.Arrays;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.utils.AttackResult.ResultType;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.particle.HitParticleType;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillDataManager;
import yesman.epicfight.skill.SkillDataManager.SkillDataKey;
import yesman.epicfight.skill.SkillDataManager.ValueType;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.skill.guard.ParryingSkill;
import yesman.epicfight.skill.guard.GuardSkill.BlockType;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.CapabilityItem.WeaponCategories;
import yesman.epicfight.world.damagesource.EpicFightDamageSource;
import yesman.epicfight.world.entity.eventlistener.HurtEvent;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener.EventType;

@Mixin(
    value = {ParryingSkill.class},
    remap = false,
    priority = 9999
)
public class ActiveGuardMixin extends GuardSkill {
    private HurtEvent.Pre event;
    @Unique
    Guard_Array star_new$GuardArray = new Guard_Array();
    @Unique
    StaticAnimation[] star_new$GUARD;
    @Unique
    StaticAnimation[] star_new$PARRY;
    @Mutable
    @Final
    @Shadow
    private static final SkillDataManager.SkillDataKey<Integer> LAST_ACTIVE;

    public ActiveGuardMixin(GuardSkill.Builder builder) {
        super(builder);
        this.star_new$GUARD = this.star_new$GuardArray.getGuard();
        this.star_new$PARRY = this.star_new$GuardArray.getParry();
    }

    public void Sta(DamageSource entity) {
        StarAPI starAPI = new StarAPI();
        AdvancedCustomHumanoidMobPatch<?> ep = (AdvancedCustomHumanoidMobPatch)EpicFightCapabilities.getEntityPatch(entity.getDirectEntity(), AdvancedCustomHumanoidMobPatch.class);
        if (ep != null) {
            Entity var5 = entity.getDirectEntity();
            if (var5 instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity)var5;
                ep.setStamina(ep.getStamina() - starAPI.getStamina(livingEntity));
            }
        }

    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public static GuardSkill.Builder createActiveGuardBuilder() {
        return GuardSkill.createGuardBuilder().addAdvancedGuardMotion(WeaponCategories.SWORD, (itemCap, playerpatch) -> {
            return itemCap.getStyle(playerpatch) == Styles.ONE_HAND ? new StaticAnimation[]{Animations.SWORD_GUARD_ACTIVE_HIT1, Animations.SWORD_GUARD_ACTIVE_HIT2} : new StaticAnimation[]{Animations.SWORD_GUARD_ACTIVE_HIT2, Animations.SWORD_GUARD_ACTIVE_HIT3};
        }).addAdvancedGuardMotion(WeaponCategories.LONGSWORD, (itemCap, playerpatch) -> {
            return new StaticAnimation[]{Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2};
        }).addAdvancedGuardMotion(WeaponCategories.UCHIGATANA, (itemCap, playerpatch) -> {
            return new StaticAnimation[]{Animations.SWORD_GUARD_ACTIVE_HIT1, Animations.SWORD_GUARD_ACTIVE_HIT2};
        }).addAdvancedGuardMotion(WeaponCategories.AXE, (itemCap, playerpatch) -> {
            return new StaticAnimation[]{Animations.SWORD_GUARD_ACTIVE_HIT1, Animations.SWORD_GUARD_ACTIVE_HIT2};
        }).addAdvancedGuardMotion(WeaponCategories.TACHI, (itemCap, playerpatch) -> {
            return new StaticAnimation[]{Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2};
        }).addAdvancedGuardMotion(WeaponCategories.SPEAR, (itemCap, playerpatch) -> {
            return new StaticAnimation[]{Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2};
        }).addAdvancedGuardMotion(StarWeaponCategory.DRAGONSLAYER, (itemCap, playerpatch) -> {
            return new StaticAnimation[]{Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2};
        }).addGuardMotion(StarWeaponCategory.YAMATO, (item, player) -> StarAnimations.YAMATO_GUARD_HIT)
                .addAdvancedGuardMotion(StarWeaponCategory.YAMATO, (itemCap, playerpatch) -> {
            return new StaticAnimation[]{StarAnimations.YAMATO_ACTIVE_GUARD_HIT, StarAnimations.YAMATO_ACTIVE_GUARD_HIT2};
        });
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        container.getDataManager().registerData(LAST_ACTIVE);
        container.getDataManager().registerData(ParryingSkill.PARRY_MOTION_COUNTER);
        container.getExecuter().getEventListener().addEventListener(EventType.SERVER_ITEM_USE_EVENT, EVENT_UUID, (event) -> {
            CapabilityItem itemCapability = ((ServerPlayerPatch)event.getPlayerPatch()).getHoldingItemCapability(InteractionHand.MAIN_HAND);
            if (this.isHoldingWeaponAvailable(event.getPlayerPatch(), itemCapability, BlockType.GUARD) && this.isExecutableState(event.getPlayerPatch())) {
                ((ServerPlayer)((ServerPlayerPatch)event.getPlayerPatch()).getOriginal()).startUsingItem(InteractionHand.MAIN_HAND);
            }

            int lastActive = (Integer)container.getDataManager().getDataValue(LAST_ACTIVE);
            if ((float)(((ServerPlayer)((ServerPlayerPatch)event.getPlayerPatch()).getOriginal()).tickCount - lastActive) > 0.1F) {
                container.getDataManager().setData(LAST_ACTIVE, ((ServerPlayer)((ServerPlayerPatch)event.getPlayerPatch()).getOriginal()).tickCount);
            }

        });
    }

    @Inject(
        at = {@At("HEAD")},
        method = {"guard"},
        cancellable = true
    )
    public void guard(SkillContainer container, CapabilityItem itemCapability, HurtEvent.Pre event, float knockback, float impact, boolean advanced, CallbackInfo ci) {
        ci.cancel();
        EpicFightDamageSource EFDamageSource = Guard_Array.getEpicFightDamageSources((DamageSource)event.getDamageSource());
        if (EFDamageSource != null) {
            StaticAnimation an = EFDamageSource.getAnimation();
            if (!Guard_Array.isNoGuard(an) && this.isHoldingWeaponAvailable(event.getPlayerPatch(), itemCapability, BlockType.ADVANCED_GUARD)) {
                DamageSource damageSource = (DamageSource)event.getDamageSource();
                if (this.isBlockableSource(damageSource, true)) {
                    ServerPlayer playerentity = (ServerPlayer)((ServerPlayerPatch)event.getPlayerPatch()).getOriginal();
                    boolean successParrying = playerentity.tickCount - (Integer)container.getDataManager().getDataValue(LAST_ACTIVE) < 10;
                    if (((ServerPlayer)((ServerPlayerPatch)event.getPlayerPatch()).getOriginal()).getMainHandItem().getItem() == Items.GUHAO.get()) {
                        successParrying = true;
                    }

                    float penalty = (Float)container.getDataManager().getDataValue(PENALTY);
                    if (successParrying) {
                        ((ServerPlayerPatch)event.getPlayerPatch()).playSound(Sounds.BIGBONG, -0.31F, -0.27F);
                        if (Guard_Array.isNoParry(an)) {
                            ((ServerPlayerPatch)event.getPlayerPatch()).playSound(Sounds.CHUA, 1.0F, 1.0F);
                        }
                    } else if (!Arrays.asList(new Guard_Array().getParry()).contains(an)) {
                        ((ServerPlayerPatch)event.getPlayerPatch()).playSound(Sounds.BONG, -0.31F, -0.27F);
                    }

                    ((HitParticleType)EpicFightParticles.HIT_BLUNT.get()).spawnParticleWithArgument((ServerLevel)playerentity.level, HitParticleType.FRONT_OF_EYES, HitParticleType.ZERO, playerentity, damageSource.getDirectEntity());
                    Entity L;
                    LivingEntity E;
                    if (successParrying) {
                        this.Sta(damageSource);
                        event.setParried(true);
                        LazyOptional optional;
                        if (((ServerPlayer)((ServerPlayerPatch)event.getPlayerPatch()).getOriginal()).getMainHandItem().getItem() != Items.GUHAO.get()) {
                            penalty = 0.1F;
                            knockback *= 0.4F;
                            container.getDataManager().setData(LAST_ACTIVE, 0);
                            if (((DamageSource)event.getDamageSource()).getDirectEntity() instanceof Monster) {
                                L = ((DamageSource)event.getDamageSource()).getDirectEntity();
                                if (((ServerPlayerPatch)event.getPlayerPatch()).getOriginal() != null && L instanceof LivingEntity) {
                                    E = (LivingEntity)L;
                                    if (E.hasEffect((MobEffect)Effect.DEFENSE.get())) {
                                        E.removeEffect((MobEffect)Effect.DEFENSE.get());
                                        optional = L.getCapability(EpicFightCapabilities.CAPABILITY_ENTITY);
                                        optional.ifPresent((patch) -> {
                                            if (patch instanceof LivingEntityPatch<?> livingEntityPatch) {
                                                livingEntityPatch.playAnimationSynchronized(Animations.BIPED_COMMON_NEUTRALIZED, 0.0F);
                                                livingEntityPatch.playSound(EpicFightSounds.NEUTRALIZE_BOSSES, 3.0F, 1.2F);
                                            }

                                        });
                                    }
                                }
                            }
                        } else {
                            if (((ServerPlayer)((ServerPlayerPatch)event.getPlayerPatch()).getOriginal()).hasEffect((MobEffect)com.guhao.init.Effect.GUHAO.get())) {
                                L = ((DamageSource)event.getDamageSource()).getDirectEntity();
                                if (L != null) {
                                    L.hurt(DamageSource.playerAttack(((ServerPlayer)((ServerPlayerPatch)event.getPlayerPatch()).getOriginal()).getLevel().getNearestPlayer(((ServerPlayerPatch)event.getPlayerPatch()).getOriginal(), -1.0)).setMagic().bypassInvul().bypassMagic().bypassArmor().damageHelmet(), event.getAmount() * 0.25F);
                                }

                                ((HitParticleType)EpicFightParticles.EVISCERATE.get()).spawnParticleWithArgument((ServerLevel)playerentity.level, HitParticleType.FRONT_OF_EYES, HitParticleType.ZERO, playerentity, damageSource.getDirectEntity());
                            }

                            penalty = 0.0F;
                            knockback *= 0.0F;
                            container.getDataManager().setData(LAST_ACTIVE, 0);
                            if (((DamageSource)event.getDamageSource()).getDirectEntity() instanceof Monster) {
                                L = ((DamageSource)event.getDamageSource()).getDirectEntity();
                                if (((ServerPlayerPatch)event.getPlayerPatch()).getOriginal() != null && L instanceof LivingEntity) {
                                    E = (LivingEntity)L;
                                    if (E.hasEffect((MobEffect)Effect.DEFENSE.get())) {
                                        E.removeEffect((MobEffect)Effect.DEFENSE.get());
                                        optional = L.getCapability(EpicFightCapabilities.CAPABILITY_ENTITY);
                                        optional.ifPresent((patch) -> {
                                            if (patch instanceof LivingEntityPatch<?> livingEntityPatch) {
                                                livingEntityPatch.playAnimationSynchronized(Animations.BIPED_COMMON_NEUTRALIZED, 0.0F);
                                                livingEntityPatch.playSound(EpicFightSounds.NEUTRALIZE_BOSSES, 3.0F, 1.2F);
                                            }

                                        });
                                    }
                                }
                            }
                        }
                    } else {
                        if (Arrays.asList(new Guard_Array().getParry()).contains(an)) {
                            event.setParried(false);
                            event.setResult(ResultType.SUCCESS);
                            return;
                        }

                        penalty += this.getPenalizer(itemCapability);
                        container.getDataManager().setDataSync(PENALTY, penalty, playerentity);
                    }

                    L = damageSource.getDirectEntity();
                    if (L instanceof LivingEntity) {
                        E = (LivingEntity)L;
                        knockback += (float)EnchantmentHelper.getKnockbackBonus(E) * 0.1F;
                    }

                    ((ServerPlayerPatch)event.getPlayerPatch()).knockBackEntity(damageSource.getDirectEntity().position(), knockback);
                    float consumeAmount = penalty * impact;
                    ((ServerPlayerPatch)event.getPlayerPatch()).consumeStaminaAlways(consumeAmount);
                    GuardSkill.BlockType blockType = successParrying ? BlockType.ADVANCED_GUARD : (((ServerPlayerPatch)event.getPlayerPatch()).hasStamina(0.0F) ? BlockType.GUARD : BlockType.GUARD_BREAK);
                    StaticAnimation animation = this.getGuardMotion(event.getPlayerPatch(), itemCapability, blockType);
                    if (animation != null) {
                        ((ServerPlayerPatch)event.getPlayerPatch()).playAnimationSynchronized(animation, 0.0F);
                    }

                    if (blockType == BlockType.GUARD_BREAK) {
                        ((ServerPlayerPatch)event.getPlayerPatch()).playSound(EpicFightSounds.NEUTRALIZE_MOBS, 3.0F, 0.0F, 0.1F);
                    }

                    this.dealEvent(event.getPlayerPatch(), event, advanced);
                    return;
                }
            }
        } else if (this.isHoldingWeaponAvailable(event.getPlayerPatch(), itemCapability, BlockType.ADVANCED_GUARD)) {
            DamageSource damageSource = (DamageSource)event.getDamageSource();
            if (this.isBlockableSource(damageSource, true)) {
                ServerPlayer playerentity = (ServerPlayer)((ServerPlayerPatch)event.getPlayerPatch()).getOriginal();
                boolean successParrying = playerentity.tickCount - (Integer)container.getDataManager().getDataValue(LAST_ACTIVE) < 10;
                if (((ServerPlayer)((ServerPlayerPatch)event.getPlayerPatch()).getOriginal()).getMainHandItem().getItem() == Items.GUHAO.get()) {
                    successParrying = true;
                }

                float penalty = (Float)container.getDataManager().getDataValue(PENALTY);
                if (successParrying) {
                    ((ServerPlayerPatch)event.getPlayerPatch()).playSound(Sounds.BIGBONG, -0.31F, -0.27F);
                } else {
                    ((ServerPlayerPatch)event.getPlayerPatch()).playSound(Sounds.BONG, -0.31F, -0.27F);
                }

                ((HitParticleType)EpicFightParticles.HIT_BLUNT.get()).spawnParticleWithArgument((ServerLevel)playerentity.level, HitParticleType.FRONT_OF_EYES, HitParticleType.ZERO, playerentity, damageSource.getDirectEntity());
                Entity E;
                if (successParrying) {
                    if (((ServerPlayer)((ServerPlayerPatch)event.getPlayerPatch()).getOriginal()).getMainHandItem().getItem() != Items.GUHAO.get()) {
                        event.setParried(true);
                        penalty = 0.1F;
                        knockback *= 0.4F;
                        container.getDataManager().setData(LAST_ACTIVE, 0);
                    } else {
                        event.setParried(true);
                        if (((ServerPlayer)((ServerPlayerPatch)event.getPlayerPatch()).getOriginal()).hasEffect((MobEffect)com.guhao.init.Effect.GUHAO.get())) {
                            E = ((DamageSource)event.getDamageSource()).getDirectEntity();
                            if (E != null) {
                                E.hurt(DamageSource.playerAttack(((ServerPlayer)((ServerPlayerPatch)event.getPlayerPatch()).getOriginal()).getLevel().getNearestPlayer(((ServerPlayerPatch)event.getPlayerPatch()).getOriginal(), -1.0)), event.getAmount() * 0.25F);
                            }

                            ((HitParticleType)EpicFightParticles.EVISCERATE.get()).spawnParticleWithArgument((ServerLevel)playerentity.level, HitParticleType.FRONT_OF_EYES, HitParticleType.ZERO, playerentity, damageSource.getDirectEntity());
                        }

                        penalty = 0.0F;
                        knockback *= 0.0F;
                        container.getDataManager().setData(LAST_ACTIVE, 0);
                    }
                } else {
                    penalty += this.getPenalizer(itemCapability);
                    container.getDataManager().setDataSync(PENALTY, penalty, playerentity);
                }

                E = damageSource.getDirectEntity();
                if (E instanceof LivingEntity) {
                    LivingEntity livingentity = (LivingEntity)E;
                    knockback += (float)EnchantmentHelper.getKnockbackBonus(livingentity) * 0.1F;
                }

                ((ServerPlayerPatch)event.getPlayerPatch()).knockBackEntity(damageSource.getDirectEntity().position(), knockback);
                float consumeAmount = penalty * impact;
                ((ServerPlayerPatch)event.getPlayerPatch()).consumeStaminaAlways(consumeAmount);
                GuardSkill.BlockType blockType = successParrying ? BlockType.ADVANCED_GUARD : (((ServerPlayerPatch)event.getPlayerPatch()).hasStamina(0.0F) ? BlockType.GUARD : BlockType.GUARD_BREAK);
                StaticAnimation animation = this.getGuardMotion(event.getPlayerPatch(), itemCapability, blockType);
                if (animation != null) {
                    ((ServerPlayerPatch)event.getPlayerPatch()).playAnimationSynchronized(animation, 0.0F);
                }

                if (blockType == BlockType.GUARD_BREAK) {
                    ((ServerPlayerPatch)event.getPlayerPatch()).playSound(EpicFightSounds.NEUTRALIZE_MOBS, 3.0F, 0.0F, 0.1F);
                }

                this.dealEvent(event.getPlayerPatch(), event, advanced);
                return;
            }
        }

        super.guard(container, itemCapability, event, knockback, impact, false);
    }

    @Inject(
        method = {"guard(Lyesman/epicfight/skill/SkillContainer;Lyesman/epicfight/world/capabilities/item/CapabilityItem;Lyesman/epicfight/world/entity/eventlistener/HurtEvent$Pre;FFZ)V"},
        at = {@At("HEAD")},
        remap = false
    )
    private void getSuccessParry(SkillContainer container, CapabilityItem itemCapability, HurtEvent.Pre event, float knockback, float impact, boolean advanced, CallbackInfo ci) {
        this.event = event;
    }

    @ModifyVariable(
        method = {"guard(Lyesman/epicfight/skill/SkillContainer;Lyesman/epicfight/world/capabilities/item/CapabilityItem;Lyesman/epicfight/world/entity/eventlistener/HurtEvent$Pre;FFZ)V"},
        at = @At("HEAD"),
        ordinal = 1,
        remap = false
    )
    private float setImpact(float impact) {
        if (ModList.get().isLoaded("epicparagliders")/** && (Boolean)CommonConfig.EPIC_PARAGLIDER_COMPAT.get()**/) {
            return impact;
        } else {
            float blockrate = 1.0F - Math.min((float)((ServerPlayer)((ServerPlayerPatch)this.event.getPlayerPatch()).getOriginal()).getAttributeValue((Attribute)ToyBoxAttributes.BLOCK_RATE.get()) / 100.0F, 0.9F);
            Object var4 = this.event.getDamageSource();
            if (var4 instanceof EpicFightDamageSource) {
                EpicFightDamageSource epicdamagesource = (EpicFightDamageSource)var4;
                float k = epicdamagesource.getImpact();
                return this.event.getAmount() / 4.0F * (1.0F + k / 2.0F) * blockrate;
            } else {
                return this.event.getAmount() / 3.0F * blockrate;
            }
        }
    }

    static {
        LAST_ACTIVE = SkillDataKey.createDataKey(ValueType.INTEGER);
    }
}
