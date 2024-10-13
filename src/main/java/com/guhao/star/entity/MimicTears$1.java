//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import org.jetbrains.annotations.NotNull;

class MimicTears$1 extends MeleeAttackGoal {
    MimicTears$1(MimicTears this$0, PathfinderMob p_25552_, double p_25553_, boolean p_25554_) {
        super(p_25552_, p_25553_, p_25554_);
//        this.this$0 = this$0;
    }

    protected double getAttackReachSqr(@NotNull LivingEntity entity) {
        return (double)(this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth());
    }
}
