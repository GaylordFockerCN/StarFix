//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.units;

import L_Ender.cataclysm.entity.projectile.Ignis_Abyss_Fireball_Entity;
import L_Ender.cataclysm.init.ModEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

class BattleUnit$2 {
    LivingEntityPatch val$livingEntityPatch;
    BattleUnit$2(LivingEntityPatch var1) {
        this.val$livingEntityPatch = var1;
    }

    public Projectile getProjectile() {
        Level level = ((LivingEntity)this.val$livingEntityPatch.getOriginal()).level;
        Entity shooter = this.val$livingEntityPatch.getOriginal();
        Ignis_Abyss_Fireball_Entity entityToSpawn = new Ignis_Abyss_Fireball_Entity((EntityType)ModEntities.IGNIS_ABYSS_FIREBALL.get(), level);
        entityToSpawn.setOwner(shooter);
        return entityToSpawn;
    }
}
