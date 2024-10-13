//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.efmex;

import yesman.epicfight.world.capabilities.item.WeaponCategory;

public enum StarWeaponCategory implements WeaponCategory {
    DRAGONSLAYER,
    YAMATO;

    final int id;

    private StarWeaponCategory() {
        this.id = WeaponCategory.ENUM_MANAGER.assign(this);
    }

    public int universalOrdinal() {
        return this.id;
    }
}
