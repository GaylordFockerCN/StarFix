//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.units;

import net.minecraft.world.phys.Vec3;

public class MathUnit {
    public static double offsetY;
    public static double A;
    public static boolean LOCK = false;

    public MathUnit() {
    }

    public static double getXRotOfVector(Vec3 vec) {
        Vec3 normalized = vec.normalize();
        return -(Math.atan2(normalized.y, (double)((float)Math.sqrt(normalized.x * normalized.x + normalized.z * normalized.z))) * 57.29577951308232);
    }

    public static double getYRotOfVector(Vec3 vec) {
        Vec3 normalized = vec.normalize();
        return Math.atan2(normalized.z, normalized.x) * 57.29577951308232 - 90.0;
    }
}
