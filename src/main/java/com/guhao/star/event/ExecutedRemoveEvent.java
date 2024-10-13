//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.event;

import net.minecraft.world.entity.LivingEntity;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public class ExecutedRemoveEvent {
    public ExecutedRemoveEvent() {
    }

    public static void execute(LivingEntity livingEntity) {
        LivingEntityPatch<?> ep = (LivingEntityPatch)EpicFightCapabilities.getEntityPatch(livingEntity, LivingEntityPatch.class);
        if (ep != null) {
            ep.playAnimationSynchronized(Animations.BIPED_ROLL_BACKWARD, 0.0F);
        }

    }
}
