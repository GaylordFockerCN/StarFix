//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins;

import java.util.Set;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackPhaseProperty;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;
import yesman.epicfight.world.damagesource.SourceTags;
import yesman.epicfight.world.damagesource.StunType;

@EventBusSubscriber(
    modid = "epicfight"
)
@Mixin(
    value = {EpicFightSkills.class},
    remap = false
)
public class EpicFightSkillsMixin {
    @Shadow
    public static Skill WRATHFUL_LIGHTING;
    @Shadow
    public static Skill TSUNAMI;
    @Shadow
    public static Skill EVERLASTING_ALLEGIANCE;
    @Shadow
    public static Skill BLADE_RUSH;
    @Shadow
    public static Skill IMPACT_GUARD;

    public EpicFightSkillsMixin() {
    }

    @Inject(
        method = {"buildSkillEvent"},
        at = {@At("TAIL")}
    )
    private static void buildSkillEvent(SkillBuildEvent onBuild, CallbackInfo ci) {
        WeaponInnateSkill wrathfulLighting = (WeaponInnateSkill)onBuild.build("epicfight", "wrathful_lighting");
        wrathfulLighting.newProperty().addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F)).newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(12.0F)).addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(3.0F)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create(new float[0]))).addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(100.0F)).addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(SourceTags.WEAPON_INNATE)).registerPropertiesToAnimation();
        WRATHFUL_LIGHTING = wrathfulLighting;
        WeaponInnateSkill tsunami = (WeaponInnateSkill)onBuild.build("epicfight", "tsunami");
        tsunami.newProperty().addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(100.0F)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create(new float[0]))).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(SourceTags.WEAPON_INNATE)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create(new float[0]))).addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN).registerPropertiesToAnimation();
        TSUNAMI = tsunami;
        WeaponInnateSkill everlastAllegiance = (WeaponInnateSkill)onBuild.build("epicfight", "everlasting_allegiance");
        everlastAllegiance.newProperty().addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(30.0F)).addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.4F)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create(new float[0]))).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(SourceTags.WEAPON_INNATE)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create(new float[0]))).addProperty(AttackPhaseProperty.STUN_TYPE, StunType.HOLD).registerPropertiesToAnimation();
        EVERLASTING_ALLEGIANCE = everlastAllegiance;
        WeaponInnateSkill bladeRush = (WeaponInnateSkill)onBuild.build("epicfight", "blade_rush");
        bladeRush.newProperty().addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create(new float[0]))).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(SourceTags.WEAPON_INNATE)).newProperty().addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(SourceTags.EXECUTION, SourceTags.WEAPON_INNATE)).addProperty(AttackPhaseProperty.STUN_TYPE, StunType.HOLD).addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER).registerPropertiesToAnimation();
        BLADE_RUSH = bladeRush;
        IMPACT_GUARD = null;
    }
}
