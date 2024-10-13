//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.skills;

import com.mojang.logging.LogUtils;
import java.util.Set;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import org.slf4j.Logger;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackPhaseProperty;
import yesman.epicfight.api.data.reloader.SkillManager;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.Skill.Resource;
import yesman.epicfight.skill.dodge.StepSkill;
import yesman.epicfight.skill.weaponinnate.ConditionalWeaponInnateSkill;
import yesman.epicfight.skill.weaponinnate.SimpleWeaponInnateSkill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;
import yesman.epicfight.world.damagesource.SourceTags;
import yesman.epicfight.world.damagesource.StunType;

@EventBusSubscriber(
    modid = "star",
    bus = Bus.FORGE
)
public class StarSkill {
    public static Skill DOUBLE_JUMP;
    public static Skill SEE_THROUGH;
    public static Skill YAMATO_STEP;
    public static Skill UCHIGATANA_SPIKES;
    public static Skill MORTAL_BLADE;
    public static Skill LETHAL_SLICING;
    public static Skill LORD_OF_THE_STORM;
    public static Skill BODYATTACKFIST;
    public static Skill BODYATTACKSHANK;
    public static Skill LION_CLAW;
    public static Skill SLASH;
    public static Skill YAMATOSKILL;

    public StarSkill() {
    }

    public static void registerSkills() {
        SkillManager.register(DoubleJumpSkill::new, Skill.createMoverBuilder().setResource(Resource.COOLDOWN), "star", "double_jump");
        SkillManager.register(StepSkill::new, StepSkill.createDodgeBuilder().setAnimations(new ResourceLocation[]{new ResourceLocation("star", "biped/yamato_step_forward"), new ResourceLocation("star", "biped/yamato_step_backward"), new ResourceLocation("star", "biped/yamato_step_left"), new ResourceLocation("star", "biped/yamato_step_right")}), "star", "yamato_step");
        SkillManager.register(UchigatanaSpikesSkill::new, ConditionalWeaponInnateSkill.createConditionalWeaponInnateBuilder().setSelector((executer) -> {
            return ((ServerPlayer)executer.getOriginal()).isSprinting() ? 1 : 0;
        }).setAnimations(new ResourceLocation[]{new ResourceLocation("star", "biped/new/blade_rush_finisher"), new ResourceLocation("star", "biped/skill/battojutsu_dash")}), "star", "uchigatanaspikes");
        SkillManager.register(SimpleWeaponInnateSkill::new, SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder().setAnimations(new ResourceLocation("star", "biped/new/tachi_auto2")), "star", "mortalblade");
        SkillManager.register(LethalSlicingSkill::new, WeaponInnateSkill.createWeaponInnateBuilder(), "star", "lethalslicing");
        SkillManager.register(YamatoSkill::new, WeaponInnateSkill.createWeaponInnateBuilder(), "star", "yamatoskill");
        SkillManager.register(SimpleWeaponInnateSkill::new, SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder().setAnimations(new ResourceLocation("star", "biped/new/mob_greatsword1")), "star", "lordofthestorm");
        SkillManager.register(SimpleWeaponInnateSkill::new, SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder().setAnimations(new ResourceLocation("star", "biped/new/fist_auto1")), "star", "bodyattackfist");
        SkillManager.register(SimpleWeaponInnateSkill::new, SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder().setAnimations(new ResourceLocation("star", "biped/new/vanilla_lethal_slicing_start")), "star", "bodyattackshank");
        SkillManager.register(SimpleWeaponInnateSkill::new, SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder().setAnimations(new ResourceLocation("star", "biped/new/lion_claw")), "star", "lionclaw");
        SkillManager.register(SimpleWeaponInnateSkill::new, SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder().setAnimations(new ResourceLocation("star", "biped/new/lethal_slicing_third")), "star", "slash");
        SkillManager.register(SeeThroughSkill::new, SeeThroughSkill.createSeeThroughSkillBuilder(), "star", "see_through");
    }

    @SubscribeEvent
    public static void BuildSkills(SkillBuildEvent event) {
        Logger LOGGER = LogUtils.getLogger();
        LOGGER.info("Build Star Skill");
        DOUBLE_JUMP = event.build("star", "double_jump");
        YAMATO_STEP = event.build("star", "yamato_step");
        SEE_THROUGH = event.build("star", "see_through");
        WeaponInnateSkill mortalBlade = (WeaponInnateSkill)event.build("star", "mortalblade");
        mortalBlade.newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.0F)).addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(50.0F)).addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(6.0F)).addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create(new float[0]))).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(SourceTags.WEAPON_INNATE)).registerPropertiesToAnimation();
        MORTAL_BLADE = mortalBlade;
        WeaponInnateSkill lethalSlicing = (WeaponInnateSkill)event.build("star", "lethalslicing");
        lethalSlicing.newProperty().addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG).addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(6.0F)).addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(0.0F)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create(new float[0]))).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(SourceTags.WEAPON_INNATE)).newProperty().addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(6.0F)).addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(50.0F)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create(new float[0]))).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(SourceTags.WEAPON_INNATE)).registerPropertiesToAnimation();
        LETHAL_SLICING = lethalSlicing;
        WeaponInnateSkill lordOfTheStorm = (WeaponInnateSkill)event.build("star", "lordofthestorm");
        lordOfTheStorm.newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.0F)).addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(50.0F)).addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(6.0F)).addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create(new float[0]))).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(SourceTags.WEAPON_INNATE)).registerPropertiesToAnimation();
        LORD_OF_THE_STORM = lordOfTheStorm;
        WeaponInnateSkill bodyAttackFist = (WeaponInnateSkill)event.build("star", "bodyattackfist");
        bodyAttackFist.newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(1.0F)).addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(50.0F)).addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F)).addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(SourceTags.WEAPON_INNATE)).registerPropertiesToAnimation();
        BODYATTACKFIST = bodyAttackFist;
        WeaponInnateSkill bodyAttackShank = (WeaponInnateSkill)event.build("star", "bodyattackshank");
        bodyAttackShank.newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(1.0F)).addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(50.0F)).addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1.0F)).addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(7.0F)).addProperty(AttackPhaseProperty.STUN_TYPE, StunType.HOLD).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(SourceTags.WEAPON_INNATE)).registerPropertiesToAnimation();
        BODYATTACKSHANK = bodyAttackShank;
        WeaponInnateSkill lionClaw = (WeaponInnateSkill)event.build("star", "lionclaw");
        lionClaw.newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.0F)).addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(100.0F)).addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(10.0F)).addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(10.0F)).addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(SourceTags.WEAPON_INNATE)).registerPropertiesToAnimation();
        LION_CLAW = lionClaw;
        WeaponInnateSkill YamatoSkill = (WeaponInnateSkill)event.build("star", "yamatoskill");
        lionClaw.newProperty().addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(15.0F)).addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(3.0F)).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(SourceTags.WEAPON_INNATE)).registerPropertiesToAnimation();
        YAMATOSKILL = YamatoSkill;
        WeaponInnateSkill slash = (WeaponInnateSkill)event.build("star", "slash");
        slash.newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F)).addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(25.0F)).addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(5.0F)).addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(1.5F)).addProperty(AttackPhaseProperty.STUN_TYPE, StunType.HOLD).addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(SourceTags.WEAPON_INNATE)).registerPropertiesToAnimation();
        SLASH = slash;
    }
}
