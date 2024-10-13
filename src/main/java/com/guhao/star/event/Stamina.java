//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
    modid = "star",
    bus = Bus.FORGE
)
public class Stamina {
    private static final float B = 3.2F;

    public Stamina() {
    }

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        Entity var2 = event.getEntity();
        if (var2 instanceof LivingEntity entity) {
            if (!(entity instanceof Player)) {
                entity.getPersistentData().putFloat("stamina", 3.2F);
            }
        }

    }
}
