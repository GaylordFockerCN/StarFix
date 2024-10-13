//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins;

import java.util.UUID;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.Skill.Resource;
import yesman.epicfight.skill.passive.ForbiddenStrengthSkill;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener.EventType;

@Mixin(
    value = {ForbiddenStrengthSkill.class},
    remap = false
)
public class ForbiddenStrengthSkillMixin extends PassiveSkill {
    @Unique
    float star_new$health;
    @Shadow
    private static final UUID EVENT_UUID = UUID.fromString("0cfd60ba-b900-11ed-afa1-0242ac120002");

    public ForbiddenStrengthSkillMixin(Skill.Builder<? extends Skill> builder) {
        super(builder);
    }

    @Inject(
        method = {"onInitiate"},
        remap = false,
        at = {@At("TAIL")}
    )
    public void onInitiate(SkillContainer container, CallbackInfo ci) {
        super.onInitiate(container);
        container.getExecuter().getEventListener().addEventListener(EventType.SKILL_CONSUME_EVENT, EVENT_UUID, (event) -> {
            if (event.getResourceType() == Resource.STAMINA) {
                float staminaConsume = event.getAmount();
                if (!container.getExecuter().hasStamina(staminaConsume) && !((Player)container.getExecuter().getOriginal()).isCreative()) {
                    event.setResourceType(Resource.HEALTH);
                    event.setAmount(30.0F);
                    if (event.shouldConsume()) {
                        Player player = (Player)container.getExecuter().getOriginal();
                        player.level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), EpicFightSounds.FORBIDDEN_STRENGTH, player.getSoundSource(), 1.0F, 1.0F);
                        ((ServerLevel)player.level).sendParticles(ParticleTypes.DAMAGE_INDICATOR, player.getX(), player.getY(0.5), player.getZ(), (int)staminaConsume, 0.1, 0.0, 0.1, 0.2);
                    }
                }
            }

        });
    }
}
