//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins.ban;

import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import reascer.wom.world.loot.WOMChestLootTables;

@EventBusSubscriber(
    modid = "wom"
)
@Mixin(
    value = {WOMChestLootTables.class},
    remap = false
)
public class WOMChestLootTablesMixin {
    public WOMChestLootTablesMixin() {
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    @SubscribeEvent
    public static void modifyVanillaLootPools(LootTableLoadEvent event) {
    }
}
