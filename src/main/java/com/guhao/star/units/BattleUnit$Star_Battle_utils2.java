//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.units;

import com.guhao.star.regirster.Sounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.particle.HitParticleType;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.damagesource.StunType;

public class BattleUnit$Star_Battle_utils2 {
    public BattleUnit$Star_Battle_utils2() {
    }

    public static void ex(LivingEntityPatch<?> ep) {
        if (!ep.isLogicalClient() && !ep.getCurrenltyAttackedEntities().isEmpty()) {
            ep.getCurrenltyAttackedEntities().forEach((entity) -> {
                if (entity instanceof LivingEntity) {
                    if (entity.equals(ep.getOriginal())) {
                        return;
                    }

                    LivingEntityPatch<?> lep = (LivingEntityPatch)EpicFightCapabilities.getEntityPatch(entity, LivingEntityPatch.class);
                    if (lep != null) {
                        lep.applyStun(StunType.KNOCKDOWN, 5.0F);
                        ((HitParticleType)EpicFightParticles.EVISCERATE.get()).spawnParticleWithArgument((ServerLevel)((LivingEntity)lep.getOriginal()).getLevel(), HitParticleType.MIDDLE_OF_ENTITIES, HitParticleType.ZERO, lep.getOriginal(), entity);
                        lep.playSound(Sounds.DUANG2, 1.0F, 1.0F);
                    }
                }

            });
        }

    }

    public static void duang(LivingEntityPatch<?> ep) {
        if (!ep.isLogicalClient() && !ep.getCurrenltyAttackedEntities().isEmpty()) {
            ep.getCurrenltyAttackedEntities().forEach((entity) -> {
                if (entity instanceof LivingEntity) {
                    if (entity.equals(ep.getOriginal())) {
                        return;
                    }

                    LivingEntityPatch<?> lep = (LivingEntityPatch)EpicFightCapabilities.getEntityPatch(entity, LivingEntityPatch.class);
                    if (lep != null) {
                        lep.playSound(Sounds.DUANG1, 1.0F, 1.0F);
                    }
                }

            });
        }

    }

    public static void duang2(LivingEntityPatch<?> ep) {
        ep.playSound(Sounds.DUANG1, 1.0F, 1.0F);
    }

    public static void duang3(LivingEntityPatch<?> ep) {
        ep.playSound(Sounds.DUANG2, 1.0F, 1.0F);
    }
}
