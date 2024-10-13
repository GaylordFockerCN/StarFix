//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.skills;

import net.minecraft.network.FriendlyByteBuf;
import yesman.epicfight.skill.BattojutsuPassive;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.skill.weaponinnate.ConditionalWeaponInnateSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

public class UchigatanaSpikesSkill extends ConditionalWeaponInnateSkill {
    public UchigatanaSpikesSkill(ConditionalWeaponInnateSkill.Builder builder) {
        super(builder);
    }

    public void executeOnServer(ServerPlayerPatch executer, FriendlyByteBuf args) {
        boolean isSheathed = (Boolean)executer.getSkill(SkillSlots.WEAPON_PASSIVE).getDataManager().getDataValue(BattojutsuPassive.SHEATH);
        if (isSheathed) {
            executer.playAnimationSynchronized(this.attackAnimations[this.getAnimationInCondition(executer)], 0.0F);
        } else {
            executer.playAnimationSynchronized(this.attackAnimations[this.getAnimationInCondition(executer)], 0.0F);
        }

        super.executeOnServer(executer, args);
    }
}
