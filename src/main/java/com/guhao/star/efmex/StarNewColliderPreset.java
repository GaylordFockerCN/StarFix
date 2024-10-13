//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.efmex;

import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.api.collider.MultiOBBCollider;
import yesman.epicfight.api.collider.OBBCollider;

public class StarNewColliderPreset {
    public static final Collider BLADE_RUSH_FINISHER = new OBBCollider(1.2, 0.8, 2.0, 0.0, 1.0, -1.2);
    public static final Collider MORTAL_BLADE = new MultiOBBCollider(3, 0.4, 2.0, 4.0, 0.0, 0.5, -5.0);
    public static final Collider LETHAL_SLICING = new OBBCollider(2.0, 0.25, 1.5, 0.0, 1.0, -1.0);
    public static final Collider LETHAL_SLICING1 = new OBBCollider(2.0, 0.25, 1.5, 0.0, 0.5, -1.0);
    public static final Collider LORD_OF_THE_STORM = new MultiOBBCollider(3, 0.05, 0.7, 9.5, 0.0, 0.0, -10.0);
    public static final Collider YAMATO_SHEATH = new MultiOBBCollider(3, 0.5, 0.5, 1.0, 0.0, 0.0, 0.5);
    public static final Collider YAMATO_P = new MultiOBBCollider(3, 0.4, 0.4, 1.5, 0.0, 0.0, -0.5);
    public static final Collider YAMATO_DASH = new OBBCollider(1.7, 1.0, 2.0, 0.0, 1.0, -1.0);
    public static final Collider YAMATO_P0 = new OBBCollider(1.7, 1.0, -3.5, 0.0, 1.0, -2.5);
    public static final Collider YAMATO_DASH_FINISH = new OBBCollider(1.7, 1.0, 3.5, 0.0, 1.0, 1.0);
    public static final Collider YAMATO = new MultiOBBCollider(3, 0.4, 0.4, 1.0, 0.0, 0.0, -0.5);

    public StarNewColliderPreset() {
    }
}
