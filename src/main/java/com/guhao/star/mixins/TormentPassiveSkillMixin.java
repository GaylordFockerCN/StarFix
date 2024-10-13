//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins;

import com.mojang.math.Vector3f;
import java.util.Random;
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import reascer.wom.gameasset.WOMAnimations;
import reascer.wom.skill.weaponinnate.TrueBerserkSkill;
import reascer.wom.skill.weaponpassive.TormentPassiveSkill;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillDataManager;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;
import yesman.epicfight.world.level.block.FractureBlockState;

@Mixin(
    value = {TormentPassiveSkill.class},
    remap = false
)
public abstract class TormentPassiveSkillMixin extends PassiveSkill {
    @Shadow
    private static final SkillDataManager.SkillDataKey<Integer> TIMER = null;
    @Shadow
    private static final SkillDataManager.SkillDataKey<Boolean> ACTIVE = null;
    @Shadow
    public static final SkillDataManager.SkillDataKey<Integer> CHARGING_TIME = null;
    @Shadow
    public static final SkillDataManager.SkillDataKey<Integer> SAVED_CHARGE = null;
    @Shadow
    private static final SkillDataManager.SkillDataKey<Boolean> CHARGING = null;
    @Shadow
    private static final SkillDataManager.SkillDataKey<Boolean> SUPER_ARMOR = null;
    @Shadow
    private static final SkillDataManager.SkillDataKey<Boolean> CHARGED = null;
    @Shadow
    private static final SkillDataManager.SkillDataKey<Boolean> CHARGED_ATTACK = null;
    @Shadow
    private static final SkillDataManager.SkillDataKey<Boolean> MOVESPEED = null;
    @Shadow
    private static final UUID EVENT_UUID = null;

    @Shadow
    public void consume_stamina(SkillContainer container) {
    }

    public TormentPassiveSkillMixin(Skill.Builder<? extends Skill> builder) {
        super(builder);
    }

    public void updateContainer(SkillContainer container) {
        PlayerPatch entitypatch;
        float interpolation;
        float stamina;
        float maxStamina;
        if (container.getExecuter().isLogicalClient() && (container.getExecuter().getCurrentLivingMotion() == LivingMotions.WALK || container.getExecuter().getCurrentLivingMotion() == LivingMotions.RUN)) {
            entitypatch = container.getExecuter();
            interpolation = 0.0F;
            OpenMatrix4f transformMatrix = entitypatch.getArmature().getBindedTransformFor(entitypatch.getArmature().getPose(interpolation), Armatures.BIPED.toolR);
            transformMatrix.translate(new Vec3f(0.0F, -0.0F, -1.2F));
            OpenMatrix4f.mul((new OpenMatrix4f()).rotate(-((float)Math.toRadians((double)(((Player)entitypatch.getOriginal()).yBodyRotO + 180.0F))), new Vec3f(0.0F, 1.0F, 0.0F)), transformMatrix, transformMatrix);
            transformMatrix.translate(new Vec3f(0.0F, 0.0F, -((new Random()).nextFloat() * 1.0F)));
            interpolation = transformMatrix.m30 + (float)((Player)entitypatch.getOriginal()).getX();
            stamina = transformMatrix.m31 + (float)((Player)entitypatch.getOriginal()).getY();
            maxStamina = transformMatrix.m32 + (float)((Player)entitypatch.getOriginal()).getZ();
            BlockState blockstate = ((Player)entitypatch.getOriginal()).level.getBlockState(new BlockPos(new Vec3((double)interpolation, (double)stamina, (double)maxStamina)));
            new BlockPos(new Vec3((double)interpolation, (double)stamina, (double)maxStamina));

            while((blockstate.getBlock() instanceof BushBlock || blockstate.isAir()) && !blockstate.is(Blocks.VOID_AIR)) {
                --stamina;
                blockstate = ((Player)entitypatch.getOriginal()).level.getBlockState(new BlockPos(new Vec3((double)interpolation, (double)stamina, (double)maxStamina)));
            }

            while(blockstate instanceof FractureBlockState) {
                BlockPos blockpos = new BlockPos((double)interpolation, (double)(stamina--), (double)maxStamina);
                blockstate = ((Player)entitypatch.getOriginal()).level.getBlockState(blockpos.below());
            }

            if ((double)transformMatrix.m31 + ((Player)entitypatch.getOriginal()).getY() < (double)(stamina + 1.5F)) {
                for(int i = 0; i < 2; ++i) {
                    ((Player)entitypatch.getOriginal()).level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockstate), (double)transformMatrix.m30 + ((Player)entitypatch.getOriginal()).getX(), (double)transformMatrix.m31 + ((Player)entitypatch.getOriginal()).getY() - 0.20000000298023224, (double)transformMatrix.m32 + ((Player)entitypatch.getOriginal()).getZ(), (double)(((new Random()).nextFloat() - 0.5F) * 0.005F), (double)((new Random()).nextFloat() * 0.02F), (double)(((new Random()).nextFloat() - 0.5F) * 0.005F));
                }
            }
        }

        if ((Boolean)container.getDataManager().getDataValue(CHARGED)) {
            entitypatch = container.getExecuter();
            int numberOf = 2;
            float partialScale = 1.0F / (float)(numberOf - 1);
            interpolation = 0.0F;

            for(int i = 0; i < numberOf; ++i) {
                OpenMatrix4f transformMatrix = entitypatch.getArmature().getBindedTransformFor(entitypatch.getArmature().getPose(interpolation), Armatures.BIPED.toolR);
                transformMatrix.translate(new Vec3f(0.0F, 0.0F, -1.0F));
                OpenMatrix4f.mul((new OpenMatrix4f()).rotate(-((float)Math.toRadians((double)(((Player)entitypatch.getOriginal()).yBodyRotO + 180.0F))), new Vec3f(0.0F, 1.0F, 0.0F)), transformMatrix, transformMatrix);
                transformMatrix.translate(new Vec3f(0.0F, 0.0F, -((new Random()).nextFloat() * 1.0F)));
                ((Player)entitypatch.getOriginal()).level.addParticle(new DustParticleOptions(new Vector3f(0.8F, 0.6F, 0.0F), 1.0F), (double)transformMatrix.m30 + ((Player)entitypatch.getOriginal()).getX() + (double)(((new Random()).nextFloat() - 0.5F) * 0.55F), (double)transformMatrix.m31 + ((Player)entitypatch.getOriginal()).getY() + (double)(((new Random()).nextFloat() - 0.5F) * 0.55F), (double)transformMatrix.m32 + ((Player)entitypatch.getOriginal()).getZ() + (double)(((new Random()).nextFloat() - 0.5F) * 0.55F), 0.0, 0.0, 0.0);
                ((Player)entitypatch.getOriginal()).level.addParticle(ParticleTypes.FLAME, (double)transformMatrix.m30 + ((Player)entitypatch.getOriginal()).getX() + (double)(((new Random()).nextFloat() - 0.5F) * 0.75F), (double)transformMatrix.m31 + ((Player)entitypatch.getOriginal()).getY() + (double)(((new Random()).nextFloat() - 0.5F) * 0.75F), (double)transformMatrix.m32 + ((Player)entitypatch.getOriginal()).getZ() + (double)(((new Random()).nextFloat() - 0.5F) * 0.75F), 0.0, 0.0, 0.0);
                interpolation += partialScale;
            }
        }

        if (!container.getExecuter().isLogicalClient()) {
            AttributeModifier charging_Movementspeed = new AttributeModifier(EVENT_UUID, "torment.charging_movespeed", 0.10000000149011612, Operation.MULTIPLY_TOTAL);
            ServerPlayerPatch executer = (ServerPlayerPatch)container.getExecuter();
            int sweeping_edge = EnchantmentHelper.getEnchantmentLevel(Enchantments.SWEEPING_EDGE, (LivingEntity)executer.getOriginal());
            if ((Boolean)container.getDataManager().getDataValue(CHARGING) && !((Player)container.getExecuter().getOriginal()).isUsingItem() && container.getExecuter().getEntityState().canBasicAttack()) {
                container.getDataManager().setDataSync(MOVESPEED, true, (ServerPlayer)((ServerPlayerPatch)container.getExecuter()).getOriginal());
                int animation_timer = (Integer)container.getDataManager().getDataValue(CHARGING_TIME);
                if ((Integer)container.getDataManager().getDataValue(CHARGING_TIME) < 20 && (Integer)container.getDataManager().getDataValue(SAVED_CHARGE) >= 20) {
                    animation_timer = (Integer)container.getDataManager().getDataValue(SAVED_CHARGE);
                }

                if ((Integer)container.getDataManager().getDataValue(CHARGING_TIME) >= 110) {
                    ((Player)container.getExecuter().getOriginal()).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 4, 2, true, false, false));
                    ((Player)container.getExecuter().getOriginal()).level.playSound((Player)null, ((Player)container.getExecuter().getOriginal()).getX(), ((Player)container.getExecuter().getOriginal()).getY(), ((Player)container.getExecuter().getOriginal()).getZ(), EpicFightSounds.WHOOSH_BIG, SoundSource.PLAYERS, 1.0F, 1.2F);
                    if (!((Player)container.getExecuter().getOriginal()).isCreative() && !container.getExecuter().consumeStamina(3.0F)) {
                        container.getExecuter().setStamina(0.0F);
                    }
                } else if (animation_timer >= 80) {
                    container.getExecuter().playAnimationSynchronized(WOMAnimations.TORMENT_CHARGED_ATTACK_3, 0.0F);
                } else if (animation_timer >= 50) {
                    container.getExecuter().playAnimationSynchronized(WOMAnimations.TORMENT_CHARGED_ATTACK_2, 0.0F);
                } else if (animation_timer >= 20) {
                    container.getExecuter().playAnimationSynchronized(WOMAnimations.TORMENT_CHARGED_ATTACK_1, 0.0F);
                } else if (!(Boolean)container.getExecuter().getSkill(SkillSlots.WEAPON_INNATE).getDataManager().getDataValue(TrueBerserkSkill.ACTIVE)) {
                    ((Player)container.getExecuter().getOriginal()).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 4, 1, true, false, false));
                    ((Player)container.getExecuter().getOriginal()).level.playSound((Player)null, ((Player)container.getExecuter().getOriginal()).getX(), ((Player)container.getExecuter().getOriginal()).getY(), ((Player)container.getExecuter().getOriginal()).getZ(), EpicFightSounds.WHOOSH_BIG, SoundSource.PLAYERS, 1.0F, 1.2F);
                    if (!((Player)container.getExecuter().getOriginal()).isCreative() && !container.getExecuter().consumeStamina(3.0F)) {
                        container.getExecuter().setStamina(0.0F);
                    }
                } else {
                    ((Player)container.getExecuter().getOriginal()).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 7, 3, true, false, false));
                    ((Player)container.getExecuter().getOriginal()).level.playSound((Player)null, ((Player)container.getExecuter().getOriginal()).getX(), ((Player)container.getExecuter().getOriginal()).getY(), ((Player)container.getExecuter().getOriginal()).getZ(), EpicFightSounds.WHOOSH_BIG, SoundSource.PLAYERS, 1.0F, 1.2F);
                    stamina = container.getExecuter().getStamina();
                    maxStamina = container.getExecuter().getMaxStamina();
                    float staminaRegen = (float)((Player)container.getExecuter().getOriginal()).getAttributeValue((Attribute)EpicFightAttributes.STAMINA_REGEN.get());
                    int regenStandbyTime = 900 / (int)(30.0F * staminaRegen);
                    if (container.getExecuter().getTickSinceLastAction() > regenStandbyTime) {
                        if (!((Player)container.getExecuter().getOriginal()).isCreative()) {
                            float staminaFactor = 1.0F + (float)Math.pow((double)(stamina / (maxStamina - stamina * 0.5F)), 2.0);
                            if (!container.getExecuter().consumeStamina(2.0F + maxStamina * 0.05F * staminaFactor * staminaRegen)) {
                                container.getExecuter().setStamina(0.0F);
                            }
                        }
                    } else if (!((Player)container.getExecuter().getOriginal()).isCreative() && !container.getExecuter().consumeStamina(2.0F)) {
                        container.getExecuter().setStamina(0.0F);
                    }
                }

                container.getDataManager().setDataSync(CHARGING, false, (ServerPlayer)((ServerPlayerPatch)container.getExecuter()).getOriginal());
                container.getDataManager().setDataSync(CHARGING_TIME, 0, (ServerPlayer)((ServerPlayerPatch)container.getExecuter()).getOriginal());
                container.getDataManager().setDataSync(SAVED_CHARGE, 0, (ServerPlayer)((ServerPlayerPatch)container.getExecuter()).getOriginal());
                ((Player)container.getExecuter().getOriginal()).getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(charging_Movementspeed);
            }

            if ((Boolean)container.getDataManager().getDataValue(CHARGING)) {
                if (((Player)container.getExecuter().getOriginal()).getAttribute(Attributes.MOVEMENT_SPEED).getModifier(EVENT_UUID) == null && (Boolean)container.getDataManager().getDataValue(MOVESPEED)) {
                    ((Player)container.getExecuter().getOriginal()).getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(charging_Movementspeed);
                }

                container.getDataManager().setDataSync(CHARGING_TIME, (Integer)container.getDataManager().getDataValue(CHARGING_TIME) + 1, (ServerPlayer)((ServerPlayerPatch)container.getExecuter()).getOriginal());
                if ((Integer)container.getDataManager().getDataValue(CHARGING_TIME) <= 130) {
                    if ((Integer)container.getDataManager().getDataValue(CHARGING_TIME) == 20) {
                        container.getDataManager().setDataSync(SAVED_CHARGE, 0, (ServerPlayer)((ServerPlayerPatch)container.getExecuter()).getOriginal());
                        ((Player)container.getExecuter().getOriginal()).level.playSound((Player)null, ((Player)container.getExecuter().getOriginal()).getX(), ((Player)container.getExecuter().getOriginal()).getY(), ((Player)container.getExecuter().getOriginal()).getZ(), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1.0F, 0.6F);
                        this.consume_stamina(container);
                    }

                    if ((Integer)container.getDataManager().getDataValue(CHARGING_TIME) == 50) {
                        ((Player)container.getExecuter().getOriginal()).level.playSound((Player)null, ((Player)container.getExecuter().getOriginal()).getX(), ((Player)container.getExecuter().getOriginal()).getY(), ((Player)container.getExecuter().getOriginal()).getZ(), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1.0F, 0.65F);
                        this.consume_stamina(container);
                    }

                    if ((Integer)container.getDataManager().getDataValue(CHARGING_TIME) == 80) {
                        ((Player)container.getExecuter().getOriginal()).level.playSound((Player)null, ((Player)container.getExecuter().getOriginal()).getX(), ((Player)container.getExecuter().getOriginal()).getY(), ((Player)container.getExecuter().getOriginal()).getZ(), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1.0F, 0.7F);
                        this.consume_stamina(container);
                    }

                    if ((Integer)container.getDataManager().getDataValue(CHARGING_TIME) == 110) {
                        container.getDataManager().setDataSync(CHARGED, true, (ServerPlayer)((ServerPlayerPatch)container.getExecuter()).getOriginal());
                        ((Player)container.getExecuter().getOriginal()).level.playSound((Player)null, ((Player)container.getExecuter().getOriginal()).getX(), ((Player)container.getExecuter().getOriginal()).getY(), ((Player)container.getExecuter().getOriginal()).getZ(), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1.0F, 0.5F);
                        ((Player)container.getExecuter().getOriginal()).level.playSound((Player)null, ((Player)container.getExecuter().getOriginal()).getX(), ((Player)container.getExecuter().getOriginal()).getY(), ((Player)container.getExecuter().getOriginal()).getZ(), SoundEvents.BELL_BLOCK, SoundSource.MASTER, 2.5F, 0.5F);
                        this.consume_stamina(container);
                    }

                    if ((Integer)container.getDataManager().getDataValue(CHARGING_TIME) == 130) {
                        container.getDataManager().setDataSync(CHARGING_TIME, 0, (ServerPlayer)((ServerPlayerPatch)container.getExecuter()).getOriginal());
                        ((Player)container.getExecuter().getOriginal()).getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(charging_Movementspeed);
                    }
                }
            } else {
                ((Player)container.getExecuter().getOriginal()).getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(charging_Movementspeed);
            }
        }

    }
}
