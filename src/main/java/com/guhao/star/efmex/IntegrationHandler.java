//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.efmex;

import net.minecraftforge.fml.ModList;

public class IntegrationHandler {
    public IntegrationHandler() {
    }

    public static void construct() {
        ModList modlist = ModList.get();
        if (modlist.isLoaded("epicfight")) {
            StarAnimations.init();
        }

    }
}
