//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.skills;

import com.guhao.star.efmex.StarAnimations;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener.EventType;

public class LethalSlicingSkill extends WeaponInnateSkill {
    private static final UUID EVENT_UUID = UUID.fromString("f082557a-b2f9-11eb-8529-0242ac130003");
    private final StaticAnimation start;
    private final StaticAnimation once;
    private final StaticAnimation twice;

    public LethalSlicingSkill(Skill.Builder<? extends Skill> builder) {
        super(builder);
        this.start = StarAnimations.LETHAL_SLICING_START;
        this.once = StarAnimations.LETHAL_SLICING_ONCE;
        this.twice = StarAnimations.LETHAL_SLICING_TWICE;
    }

    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        container.getExecuter().getEventListener().addEventListener(EventType.ATTACK_ANIMATION_END_EVENT, EVENT_UUID, (event) -> {
            if (StarAnimations.LETHAL_SLICING_START.equals(event.getAnimation())) {
                List<LivingEntity> hurtEntities = ((ServerPlayerPatch)event.getPlayerPatch()).getCurrenltyHurtEntities();
                if (hurtEntities.size() <= 1) {
                    ((ServerPlayerPatch)event.getPlayerPatch()).reserveAnimation(this.once);
                    ((ServerPlayerPatch)event.getPlayerPatch()).getServerAnimator().getPlayerFor((DynamicAnimation)null).reset();
                    ((ServerPlayerPatch)event.getPlayerPatch()).getCurrenltyHurtEntities().clear();
                    this.once.tick(event.getPlayerPatch());
                }

                if (hurtEntities.size() > 1) {
                    ((ServerPlayerPatch)event.getPlayerPatch()).reserveAnimation(this.twice);
                    ((ServerPlayerPatch)event.getPlayerPatch()).getServerAnimator().getPlayerFor((DynamicAnimation)null).reset();
                    ((ServerPlayerPatch)event.getPlayerPatch()).getCurrenltyHurtEntities().clear();
                    this.twice.tick(event.getPlayerPatch());
                }
            }

        });
    }

    public void onRemoved(SkillContainer container) {
        container.getExecuter().getEventListener().removeListener(EventType.ATTACK_ANIMATION_END_EVENT, EVENT_UUID);
    }

    public void executeOnServer(ServerPlayerPatch executer, FriendlyByteBuf args) {
        executer.playAnimationSynchronized(this.start, 0.0F);
        super.executeOnServer(executer, args);
    }

    public List<Component> getTooltipOnItem(ItemStack itemStack, CapabilityItem cap, PlayerPatch<?> playerCap) {
        List<Component> list = super.getTooltipOnItem(itemStack, cap, playerCap);
        this.generateTooltipforPhase(list, itemStack, cap, playerCap, (Map)this.properties.get(0), "Start Strike:");
        this.generateTooltipforPhase(list, itemStack, cap, playerCap, (Map)this.properties.get(1), "Once Strike:");
        return list;
    }

    public WeaponInnateSkill registerPropertiesToAnimation() {
        AttackAnimation _start = (AttackAnimation)this.start;
        AttackAnimation _once = (AttackAnimation)this.once;
        _start.phases[0].addProperties(((Map)this.properties.get(0)).entrySet());
        _once.phases[0].addProperties(((Map)this.properties.get(1)).entrySet());
        return this;
    }
}
