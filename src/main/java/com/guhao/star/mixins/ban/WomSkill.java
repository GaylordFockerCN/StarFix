//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins.ban;

import java.util.Set;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import reascer.wom.gameasset.WOMSkills;
import reascer.wom.skill.weaponinnate.AgonyPlungeSkill;
import reascer.wom.skill.weaponinnate.CharybdisSkill;
import reascer.wom.skill.weaponinnate.DemonicAscensionSkill;
import reascer.wom.skill.weaponinnate.EnderBlastSkill;
import reascer.wom.skill.weaponinnate.EnderFusionSkill;
import reascer.wom.skill.weaponinnate.LunarEclipseSkill;
import reascer.wom.skill.weaponinnate.RegierungSkill;
import reascer.wom.skill.weaponinnate.SakuraStateSkill;
import reascer.wom.skill.weaponinnate.SolarArcanaSkill;
import reascer.wom.skill.weaponinnate.SoulSnatchSkill;
import reascer.wom.skill.weaponinnate.TrueBerserkSkill;
import reascer.wom.skill.weaponpassive.DemonMarkPassiveSkill;
import reascer.wom.skill.weaponpassive.LunarEchoPassiveSkill;
import reascer.wom.skill.weaponpassive.RuinePassive;
import reascer.wom.skill.weaponpassive.SatsujinPassive;
import reascer.wom.skill.weaponpassive.SolarPassiveSkill;
import reascer.wom.skill.weaponpassive.TormentPassiveSkill;
import reascer.wom.world.damagesources.WOMExtraDamageInstance;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackPhaseProperty;
import yesman.epicfight.api.data.reloader.SkillManager;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.skill.Skill.ActivateType;
import yesman.epicfight.skill.Skill.Resource;
import yesman.epicfight.skill.dodge.DodgeSkill;
import yesman.epicfight.skill.weaponinnate.ConditionalWeaponInnateSkill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;

@Mixin(
    value = {WOMSkills.class},
    remap = false
)
public class WomSkill {
    @Shadow
    public static Skill KNIGHT_ROLL;
    @Shadow
    public static Skill CHARYBDIS;
    @Shadow
    public static Skill AGONY_PLUNGE;
    @Shadow
    public static Skill TRUE_BERSERK;
    @Shadow
    public static Skill DEMONIC_ASCENSION;
    @Shadow
    public static Skill SOUL_SNATCH;
    @Shadow
    public static Skill REGIERUNG;
    @Shadow
    public static Skill SAKURA_STATE;
    @Shadow
    public static Skill ENDER_BLAST;
    @Shadow
    public static Skill ENDER_FUSION;
    @Shadow
    public static Skill lUNAR_ECLIPSE;
    @Shadow
    public static Skill SOLAR_ARCANO;
    @Shadow
    public static Skill SATSUJIN_PASSIVE;
    @Shadow
    public static Skill DEMON_MARK_PASSIVE;
    @Shadow
    public static Skill RUINE_PASSIVE;
    @Shadow
    public static Skill TORMENT_PASSIVE;
    @Shadow
    public static Skill LUNAR_ECHO_PASSIVE;
    @Shadow
    public static Skill SOLAR_PASSIVE;

    public WomSkill() {
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public static void registerSkills() {
        SkillManager.register(DodgeSkill::new, DodgeSkill.createDodgeBuilder().setAnimations(new ResourceLocation[]{new ResourceLocation("wom", "biped/skill/roll_forward"), new ResourceLocation("wom", "biped/skill/roll_backward"), new ResourceLocation("wom", "biped/skill/roll_left"), new ResourceLocation("wom", "biped/skill/roll_right")}), "wom", "precise_roll");
        SkillManager.register(DemonMarkPassiveSkill::new, Skill.createBuilder().setCategory(SkillCategories.WEAPON_PASSIVE).setActivateType(ActivateType.DURATION_INFINITE).setResource(Resource.COOLDOWN), "wom", "demon_mark_passive");
        SkillManager.register(TormentPassiveSkill::new, Skill.createBuilder().setCategory(SkillCategories.WEAPON_PASSIVE), "wom", "torment_passive");
        SkillManager.register(RuinePassive::new, Skill.createBuilder().setCategory(SkillCategories.WEAPON_PASSIVE), "wom", "ruine_passive");
        SkillManager.register(LunarEchoPassiveSkill::new, Skill.createBuilder().setCategory(SkillCategories.WEAPON_PASSIVE), "wom", "lunar_echo_passive");
        SkillManager.register(SatsujinPassive::new, Skill.createBuilder().setCategory(SkillCategories.WEAPON_PASSIVE).setActivateType(ActivateType.ONE_SHOT).setResource(Resource.COOLDOWN), "wom", "satsujin_passive");
        SkillManager.register(SolarPassiveSkill::new, Skill.createBuilder().setCategory(SkillCategories.WEAPON_PASSIVE), "wom", "solar_passive");
        SkillManager.register(CharybdisSkill::new, WeaponInnateSkill.createWeaponInnateBuilder(), "wom", "charybdis");
        SkillManager.register(AgonyPlungeSkill::new, WeaponInnateSkill.createWeaponInnateBuilder(), "wom", "agony_plunge");
        SkillManager.register(TrueBerserkSkill::new, WeaponInnateSkill.createWeaponInnateBuilder(), "wom", "true_berserk");
        SkillManager.register(SoulSnatchSkill::new, WeaponInnateSkill.createWeaponInnateBuilder(), "wom", "plunder_perdition");
        SkillManager.register(SakuraStateSkill::new, ConditionalWeaponInnateSkill.createConditionalWeaponInnateBuilder().setSelector((executer) -> {
            if (((ServerPlayer)executer.getOriginal()).isSprinting()) {
                executer.getSkill(SkillSlots.WEAPON_INNATE).getDataManager().setDataSync(SakuraStateSkill.SECOND_DRAW, false, (ServerPlayer)executer.getOriginal());
                return 2;
            } else if ((Boolean)executer.getSkill(SkillSlots.WEAPON_INNATE).getDataManager().getDataValue(SakuraStateSkill.ACTIVE)) {
                if ((Boolean)executer.getSkill(SkillSlots.WEAPON_INNATE).getDataManager().getDataValue(SakuraStateSkill.SECOND_DRAW)) {
                    executer.getSkill(SkillSlots.WEAPON_INNATE).getDataManager().setDataSync(SakuraStateSkill.SECOND_DRAW, false, (ServerPlayer)executer.getOriginal());
                    return 1;
                } else {
                    executer.getSkill(SkillSlots.WEAPON_INNATE).getDataManager().setDataSync(SakuraStateSkill.SECOND_DRAW, true, (ServerPlayer)executer.getOriginal());
                    return 0;
                }
            } else {
                executer.getSkill(SkillSlots.WEAPON_INNATE).getDataManager().setDataSync(SakuraStateSkill.SECOND_DRAW, true, (ServerPlayer)executer.getOriginal());
                return 0;
            }
        }).setAnimations(new ResourceLocation[]{new ResourceLocation("wom", "biped/skill/katana_fatal_draw"), new ResourceLocation("wom", "biped/skill/katana_fatal_draw_second"), new ResourceLocation("wom", "biped/skill/katana_fatal_draw_dash")}), "wom", "sakura_state");
        SkillManager.register(EnderBlastSkill::new, WeaponInnateSkill.createWeaponInnateBuilder(), "wom", "ender_blast");
        SkillManager.register(EnderFusionSkill::new, WeaponInnateSkill.createWeaponInnateBuilder(), "wom", "ender_fusion");
        SkillManager.register(DemonicAscensionSkill::new, WeaponInnateSkill.createWeaponInnateBuilder().setActivateType(ActivateType.DURATION_INFINITE), "wom", "demonic_ascension");
        SkillManager.register(RegierungSkill::new, WeaponInnateSkill.createWeaponInnateBuilder().setActivateType(ActivateType.DURATION_INFINITE), "wom", "regierung");
        SkillManager.register(LunarEclipseSkill::new, WeaponInnateSkill.createWeaponInnateBuilder(), "wom", "lunar_eclipse");
        SkillManager.register(SolarArcanaSkill::new, WeaponInnateSkill.createWeaponInnateBuilder().setActivateType(ActivateType.DURATION_INFINITE), "wom", "solar_arcano");
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    @SubscribeEvent
    public static void buildSkillEvent(SkillBuildEvent onBuild) {
        KNIGHT_ROLL = onBuild.build("wom", "precise_roll");
        WeaponInnateSkill charybdisSkill = (WeaponInnateSkill)onBuild.build("wom", "charybdis");
        charybdisSkill.newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35F));
        CHARYBDIS = charybdisSkill;
        WeaponInnateSkill AgonyPlungeSkill = (WeaponInnateSkill)onBuild.build("wom", "agony_plunge");
        AgonyPlungeSkill.newProperty().addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(10.0F)).addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(1.0F)).newProperty().addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(10.0F)).addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(WOMExtraDamageInstance.WOM_SWEEPING_EDGE_ENCHANTMENT.create(new float[]{1.2F})));
        AGONY_PLUNGE = AgonyPlungeSkill;
        TORMENT_PASSIVE = onBuild.build("wom", "torment_passive");
        WeaponInnateSkill trueBerserkSkill = (WeaponInnateSkill)onBuild.build("wom", "true_berserk");
        trueBerserkSkill.newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F)).addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(8.0F)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create(new float[0]))).newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.0F)).addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(10.0F)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create(new float[0])));
        TRUE_BERSERK = trueBerserkSkill;
        RUINE_PASSIVE = onBuild.build("wom", "ruine_passive");
        WeaponInnateSkill soulSnatchSkill = (WeaponInnateSkill)onBuild.build("wom", "plunder_perdition");
        soulSnatchSkill.newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.7F)).addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(20.0F)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create(new float[0]))).newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.3F)).addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(20.0F)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create(new float[0])));
        SOUL_SNATCH = soulSnatchSkill;
        SATSUJIN_PASSIVE = onBuild.build("wom", "satsujin_passive");
        WeaponInnateSkill sakuraStateskill = (WeaponInnateSkill)onBuild.build("wom", "sakura_state");
        sakuraStateskill.newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F)).addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(20.0F)).addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(30.0F)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create(new float[0]))).registerPropertiesToAnimation();
        SAKURA_STATE = sakuraStateskill;
        WeaponInnateSkill enderblastSkill = (WeaponInnateSkill)onBuild.build("wom", "ender_blast");
        enderblastSkill.newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.7F)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(WOMExtraDamageInstance.WOM_SWEEPING_EDGE_ENCHANTMENT.create(new float[]{0.7F}))).newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.6F)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(WOMExtraDamageInstance.WOM_SWEEPING_EDGE_ENCHANTMENT.create(new float[]{0.6F}))).newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.1F)).addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.multiplier(4.0F)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(WOMExtraDamageInstance.WOM_SWEEPING_EDGE_ENCHANTMENT.create(new float[]{1.05F})));
        ENDER_BLAST = enderblastSkill;
        WeaponInnateSkill enderfusionSkill = (WeaponInnateSkill)onBuild.build("wom", "ender_fusion");
        enderfusionSkill.newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.7F)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(WOMExtraDamageInstance.WOM_SWEEPING_EDGE_ENCHANTMENT.create(new float[]{0.7F}))).newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.6F)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(WOMExtraDamageInstance.WOM_SWEEPING_EDGE_ENCHANTMENT.create(new float[]{0.6F}))).newProperty().addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(4.2F)).addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.multiplier(4.0F)).addProperty(AttackPhaseProperty.EXTRA_DAMAGE, Set.of(WOMExtraDamageInstance.WOM_SWEEPING_EDGE_ENCHANTMENT.create(new float[]{2.1F})));
        ENDER_FUSION = enderfusionSkill;
        REGIERUNG = onBuild.build("wom", "regierung");
        lUNAR_ECLIPSE = onBuild.build("wom", "lunar_eclipse");
        LUNAR_ECHO_PASSIVE = onBuild.build("wom", "lunar_echo_passive");
        DEMONIC_ASCENSION = onBuild.build("wom", "demonic_ascension");
        DEMON_MARK_PASSIVE = onBuild.build("wom", "demon_mark_passive");
        SOLAR_ARCANO = onBuild.build("wom", "solar_arcano");
        SOLAR_PASSIVE = onBuild.build("wom", "solar_passive");
    }
}
