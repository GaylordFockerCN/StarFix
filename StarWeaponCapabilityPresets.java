//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.efmex;

import com.google.common.collect.Maps;
import com.guhao.star.skills.StarSkill;
import com.mojang.datafixers.util.Pair;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.particle.HitParticleType;
import yesman.epicfight.skill.BattojutsuPassive;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;
import yesman.epicfight.world.capabilities.item.WeaponCategory;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.CapabilityItem.WeaponCategories;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

public class StarWeaponCapabilityPresets {
    public static final Function<Item, CapabilityItem.Builder> UCHIGATANA_1 = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder().category(WeaponCategories.UCHIGATANA).styleProvider((entitypatch) -> {
            if (entitypatch instanceof PlayerPatch<?> playerpatch) {
                if (playerpatch.getSkill(SkillSlots.WEAPON_PASSIVE).getDataManager().hasData(BattojutsuPassive.SHEATH) && (Boolean)playerpatch.getSkill(SkillSlots.WEAPON_PASSIVE).getDataManager().getDataValue(BattojutsuPassive.SHEATH)) {
                    return Styles.SHEATH;
                }
            }

            return Styles.TWO_HAND;
        }).passiveSkill(EpicFightSkills.BATTOJUTSU_PASSIVE).hitSound(EpicFightSounds.BLADE_HIT).collider(ColliderPreset.UCHIGATANA).canBePlacedOffhand(false).newStyleCombo(Styles.SHEATH, new StaticAnimation[]{Animations.UCHIGATANA_SHEATHING_AUTO, Animations.UCHIGATANA_SHEATHING_DASH, Animations.UCHIGATANA_SHEATH_AIR_SLASH}).newStyleCombo(Styles.TWO_HAND, new StaticAnimation[]{Animations.UCHIGATANA_AUTO1, Animations.UCHIGATANA_AUTO2, Animations.UCHIGATANA_AUTO3, Animations.UCHIGATANA_DASH, Animations.UCHIGATANA_AIR_SLASH}).newStyleCombo(Styles.MOUNT, new StaticAnimation[]{Animations.SWORD_MOUNT_ATTACK}).innateSkill(Styles.SHEATH, (itemstack) -> {
            return StarSkill.UCHIGATANA_SPIKES;
        }).innateSkill(Styles.TWO_HAND, (itemstack) -> {
            return StarSkill.UCHIGATANA_SPIKES;
        }).livingMotionModifier(Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_UCHIGATANA).livingMotionModifier(Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_HOLD_UCHIGATANA).livingMotionModifier(Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_UCHIGATANA).livingMotionModifier(Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_WALK_UCHIGATANA).livingMotionModifier(Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_UCHIGATANA).livingMotionModifier(Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_WALK_UCHIGATANA).livingMotionModifier(Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_UCHIGATANA).livingMotionModifier(Styles.TWO_HAND, LivingMotions.FLOAT, Animations.BIPED_HOLD_UCHIGATANA).livingMotionModifier(Styles.TWO_HAND, LivingMotions.FALL, Animations.BIPED_HOLD_UCHIGATANA).livingMotionModifier(Styles.SHEATH, LivingMotions.IDLE, Animations.BIPED_HOLD_UCHIGATANA_SHEATHING).livingMotionModifier(Styles.SHEATH, LivingMotions.KNEEL, Animations.BIPED_HOLD_UCHIGATANA_SHEATHING).livingMotionModifier(Styles.SHEATH, LivingMotions.WALK, Animations.BIPED_WALK_UCHIGATANA_SHEATHING).livingMotionModifier(Styles.SHEATH, LivingMotions.CHASE, Animations.BIPED_HOLD_UCHIGATANA_SHEATHING).livingMotionModifier(Styles.SHEATH, LivingMotions.RUN, Animations.BIPED_RUN_UCHIGATANA_SHEATHING).livingMotionModifier(Styles.SHEATH, LivingMotions.SNEAK, Animations.BIPED_HOLD_UCHIGATANA_SHEATHING).livingMotionModifier(Styles.SHEATH, LivingMotions.SWIM, Animations.BIPED_HOLD_UCHIGATANA_SHEATHING).livingMotionModifier(Styles.SHEATH, LivingMotions.FLOAT, Animations.BIPED_HOLD_UCHIGATANA_SHEATHING).livingMotionModifier(Styles.SHEATH, LivingMotions.FALL, Animations.BIPED_HOLD_UCHIGATANA_SHEATHING).livingMotionModifier(Styles.TWO_HAND, LivingMotions.BLOCK, Animations.UCHIGATANA_GUARD);
        return builder;
    };
    public static final Function<Item, CapabilityItem.Builder> TACHI_1 = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder().category(WeaponCategories.TACHI).styleProvider((playerpatch) -> {
            return Styles.TWO_HAND;
        }).collider(ColliderPreset.TACHI).hitSound(EpicFightSounds.BLADE_HIT).canBePlacedOffhand(false).newStyleCombo(Styles.TWO_HAND, new StaticAnimation[]{Animations.TACHI_AUTO1, Animations.TACHI_AUTO2, Animations.TACHI_AUTO3, Animations.TACHI_DASH, Animations.LONGSWORD_AIR_SLASH}).newStyleCombo(Styles.MOUNT, new StaticAnimation[]{Animations.SWORD_MOUNT_ATTACK}).innateSkill(Styles.TWO_HAND, (itemstack) -> {
            return StarSkill.MORTAL_BLADE;
        }).livingMotionModifier(Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.FLOAT, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.FALL, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);
        return builder;
    };
    public static final Function<Item, CapabilityItem.Builder> TACHI_2 = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder().category(WeaponCategories.TACHI).styleProvider((playerpatch) -> {
            return Styles.TWO_HAND;
        }).collider(ColliderPreset.TACHI).hitSound(EpicFightSounds.BLADE_HIT).canBePlacedOffhand(false).newStyleCombo(Styles.TWO_HAND, new StaticAnimation[]{Animations.TACHI_AUTO1, Animations.TACHI_AUTO2, Animations.TACHI_AUTO3, Animations.TACHI_DASH, Animations.LONGSWORD_AIR_SLASH}).newStyleCombo(Styles.MOUNT, new StaticAnimation[]{Animations.SWORD_MOUNT_ATTACK}).innateSkill(Styles.TWO_HAND, (itemstack) -> {
            return StarSkill.LETHAL_SLICING;
        }).livingMotionModifier(Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.FLOAT, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.FALL, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);
        return builder;
    };
    public static final Function<Item, CapabilityItem.Builder> STORM_RULER = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder().category(WeaponCategories.GREATSWORD).styleProvider((playerpatch) -> {
            return Styles.TWO_HAND;
        }).collider(ColliderPreset.GREATSWORD).swingSound(EpicFightSounds.WHOOSH_BIG).hitSound(EpicFightSounds.BLADE_HIT).canBePlacedOffhand(false).newStyleCombo(Styles.TWO_HAND, new StaticAnimation[]{Animations.GREATSWORD_AUTO1, Animations.GREATSWORD_AUTO2, Animations.GREATSWORD_DASH, Animations.GREATSWORD_AIR_SLASH}).innateSkill(Styles.TWO_HAND, (itemstack) -> {
            return StarSkill.LORD_OF_THE_STORM;
        }).livingMotionModifier(Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_WALK_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.JUMP, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.FLY, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.CREATIVE_FLY, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.CREATIVE_IDLE, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.BLOCK, Animations.GREATSWORD_GUARD);
        return builder;
    };
    public static final Function<Item, CapabilityItem.Builder> SWORD_1 = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder().category(WeaponCategories.SWORD).styleProvider((playerpatch) -> {
            return playerpatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == WeaponCategories.SWORD ? Styles.TWO_HAND : Styles.ONE_HAND;
        }).collider(ColliderPreset.SWORD).newStyleCombo(Styles.ONE_HAND, new StaticAnimation[]{Animations.SWORD_AUTO1, Animations.SWORD_AUTO2, Animations.SWORD_AUTO3, Animations.SWORD_DASH, Animations.SWORD_AIR_SLASH}).newStyleCombo(Styles.TWO_HAND, new StaticAnimation[]{Animations.SWORD_DUAL_AUTO1, Animations.SWORD_DUAL_AUTO2, Animations.SWORD_DUAL_AUTO3, Animations.SWORD_DUAL_DASH, Animations.SWORD_DUAL_AIR_SLASH}).newStyleCombo(Styles.MOUNT, new StaticAnimation[]{Animations.SWORD_MOUNT_ATTACK}).innateSkill(Styles.ONE_HAND, (itemstack) -> {
            return StarSkill.BODYATTACKFIST;
        }).innateSkill(Styles.TWO_HAND, (itemstack) -> {
            return EpicFightSkills.DANCING_EDGE;
        }).livingMotionModifier(Styles.ONE_HAND, LivingMotions.BLOCK, Animations.SWORD_GUARD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_DUAL).livingMotionModifier(Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(Styles.TWO_HAND, LivingMotions.FLOAT, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(Styles.TWO_HAND, LivingMotions.FALL, Animations.BIPED_HOLD_DUAL_WEAPON).weaponCombinationPredicator((entitypatch) -> {
            return EpicFightCapabilities.getItemStackCapability(((LivingEntity)entitypatch.getOriginal()).getOffhandItem()).getWeaponCategory() == WeaponCategories.SWORD;
        });
        if (item instanceof TieredItem tieredItem) {
            int harvestLevel = tieredItem.getTier().getLevel();
            builder.addStyleAttibutes(Styles.COMMON, Pair.of((Attribute)EpicFightAttributes.IMPACT.get(), EpicFightAttributes.getImpactModifier(0.5 + 0.2 * (double)harvestLevel)));
            builder.addStyleAttibutes(Styles.COMMON, Pair.of((Attribute)EpicFightAttributes.MAX_STRIKES.get(), EpicFightAttributes.getMaxStrikesModifier(1)));
            builder.hitSound(tieredItem.getTier() == Tiers.WOOD ? EpicFightSounds.BLUNT_HIT : EpicFightSounds.BLADE_HIT);
            builder.hitParticle(tieredItem.getTier() == Tiers.WOOD ? (HitParticleType)EpicFightParticles.HIT_BLUNT.get() : (HitParticleType)EpicFightParticles.HIT_BLADE.get());
        }

        return builder;
    };
    public static final Function<Item, CapabilityItem.Builder> ZWEI = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder().category(WeaponCategories.GREATSWORD).styleProvider((playerpatch) -> {
            return Styles.TWO_HAND;
        }).collider(ColliderPreset.GREATSWORD).swingSound(EpicFightSounds.WHOOSH_BIG).hitSound(EpicFightSounds.BLADE_HIT).canBePlacedOffhand(false).newStyleCombo(Styles.TWO_HAND, new StaticAnimation[]{StarAnimations.ZWEI_AUTO1, StarAnimations.ZWEI_AUTO2, StarAnimations.ZWEI_AUTO3, StarAnimations.ZWEI_DASH, StarAnimations.ZWEI_AIRSLASH}).innateSkill(Styles.TWO_HAND, (itemstack) -> {
            return StarSkill.BODYATTACKSHANK;
        }).livingMotionModifier(Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_WALK_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.JUMP, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.FLY, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.CREATIVE_FLY, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.CREATIVE_IDLE, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.BLOCK, Animations.GREATSWORD_GUARD);
        return builder;
    };
    public static final Function<Item, CapabilityItem.Builder> DRAGONSLAYER = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder().category(StarWeaponCategory.DRAGONSLAYER).styleProvider((playerpatch) -> {
            return Styles.TWO_HAND;
        }).collider(ColliderPreset.GREATSWORD).swingSound(EpicFightSounds.WHOOSH_BIG).hitSound(EpicFightSounds.BLADE_HIT).canBePlacedOffhand(false).newStyleCombo(Styles.TWO_HAND, new StaticAnimation[]{StarAnimations.OLD_GREATSWORD_AUTO1, StarAnimations.OLD_GREATSWORD_AUTO2, Animations.BIPED_MOB_LONGSWORD2, StarAnimations.OLD_GREATSWORD_DASH, StarAnimations.OLD_GREATSWORD_AIRSLASH}).innateSkill(Styles.TWO_HAND, (itemstack) -> {
            return StarSkill.LION_CLAW;
        }).livingMotionModifier(Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_LONGSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_LONGSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_WALK_LONGSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.JUMP, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.FLY, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.CREATIVE_FLY, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.CREATIVE_IDLE, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.BLOCK, Animations.GREATSWORD_GUARD);
        return builder;
    };
    public static final Function<Item, CapabilityItem.Builder> YAMATO = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .category((WeaponCategory)YamatoColliderPreset.YAMATO)
                .styleProvider((playerpatch) -> Styles.TWO_HAND)
                .collider(YamatoColliderPreset.YAMATO)
                .swingSound(EpicFightSounds.BLADE_HIT)
                .hitSound(EpicFightSounds.BLADE_HIT)
                .canBePlacedOffhand(false)
                .newStyleCombo(Styles.TWO_HAND, StarAnimations.YAMATO_AUTO1, StarAnimations.YAMATO_AUTO2, StarAnimations.YAMATO_AUTO3, StarAnimations.YAMATO_AUTO4, StarAnimations.YAMATO_DASH, StarAnimations.YAMATO_AIRSLASH)
                .innateSkill(Styles.TWO_HAND, (itemstack) -> StarSkill.YAMATOSKILL)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.IDLE, StarAnimations.YAMATO_IDLE)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.KNEEL, StarAnimations.YAMATO_IDLE)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.WALK, StarAnimations.YAMATO_WALK)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.CHASE, StarAnimations.YAMATO_RUN)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.RUN, StarAnimations.YAMATO_RUN)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.SNEAK, StarAnimations.YAMATO_IDLE)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.SWIM, StarAnimations.YAMATO_IDLE)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.FLOAT, StarAnimations.YAMATO_IDLE)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.FALL, StarAnimations.YAMATO_IDLE)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.BLOCK, StarAnimations.YAMATO_GUARD);
        return builder;
    };
    public static final Function<Item, CapabilityItem.Builder> SLASH = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder().category(WeaponCategories.TACHI).styleProvider((playerpatch) -> {
            return Styles.TWO_HAND;
        }).collider(ColliderPreset.TACHI).swingSound(EpicFightSounds.WHOOSH).hitSound(EpicFightSounds.BLADE_HIT).canBePlacedOffhand(false).newStyleCombo(Styles.TWO_HAND, new StaticAnimation[]{Animations.LONGSWORD_LIECHTENAUER_AUTO1, Animations.TACHI_AUTO2, Animations.TACHI_AUTO3, Animations.TACHI_DASH, Animations.LONGSWORD_AIR_SLASH}).innateSkill(Styles.TWO_HAND, (itemstack) -> {
            return StarSkill.SLASH;
        }).livingMotionModifier(Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_LONGSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_WALK_LONGSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD).livingMotionModifier(Styles.TWO_HAND, LivingMotions.JUMP, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.FLY, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.CREATIVE_FLY, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.CREATIVE_IDLE, Animations.BIPED_HOLD_TACHI).livingMotionModifier(Styles.TWO_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);
        return builder;
    };
    private static final Map<String, Function<Item, CapabilityItem.Builder>> PRESETS = Maps.newHashMap();

    public StarWeaponCapabilityPresets() {
    }

    public static void register(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put("uchigatana1", UCHIGATANA_1);
        event.getTypeEntry().put("tachi1", TACHI_1);
        event.getTypeEntry().put("tachi2", TACHI_2);
        event.getTypeEntry().put("stormruler", STORM_RULER);
        event.getTypeEntry().put("sword1", SWORD_1);
        event.getTypeEntry().put("zwei", ZWEI);
        event.getTypeEntry().put("dragonslayer", DRAGONSLAYER);
        event.getTypeEntry().put("slash", SLASH);
        event.getTypeEntry().put("yamato", YAMATO);
    }

    public static Function<Item, CapabilityItem.Builder> get(String typeName) {
        ResourceLocation rl = new ResourceLocation(typeName);
        if (!PRESETS.containsKey(rl.getPath())) {
            throw new IllegalArgumentException("Can't find weapon type: " + typeName);
        } else {
            return (Function)PRESETS.get(rl.getPath());
        }
    }
}
