//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.weaponinnate.RushingTempoSkill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;

@Mixin(
    value = {RushingTempoSkill.class},
    remap = false
)
public class RushingTempoSkillMixin extends WeaponInnateSkill {
    @Shadow
    private Map<ResourceLocation, AttackAnimation> comboAnimation = Maps.newHashMap();

    public RushingTempoSkillMixin(Skill.Builder<? extends Skill> builder) {
        super(builder);
    }

    public WeaponInnateSkill registerPropertiesToAnimation() {
        this.comboAnimation.values().forEach((animation) -> {
            animation.phases[0].addProperties(((Map)this.properties.get(0)).entrySet());
        });
        return this;
    }

    @Inject(
        method = {"<init>(Lyesman/epicfight/skill/Skill$Builder;)V"},
        at = {@At("RETURN")}
    )
    public void init(Skill.Builder<? extends Skill> builder, CallbackInfo ci) {
        this.comboAnimation.put(Animations.TACHI_AUTO1.getRegistryName(), (AttackAnimation)Animations.RUSHING_TEMPO1);
        this.comboAnimation.put(Animations.TACHI_AUTO2.getRegistryName(), (AttackAnimation)Animations.RUSHING_TEMPO2);
        this.comboAnimation.put(Animations.TACHI_AUTO3.getRegistryName(), (AttackAnimation)Animations.RUSHING_TEMPO3);
        this.comboAnimation.put(Animations.TACHI_DASH.getRegistryName(), (AttackAnimation)Animations.RUSHING_TEMPO2);
        this.comboAnimation.put(Animations.LONGSWORD_AIR_SLASH.getRegistryName(), (AttackAnimation)Animations.RUSHING_TEMPO3);
    }
}
