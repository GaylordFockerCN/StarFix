//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins.ban;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import reascer.wom.world.loot.WOMLootDropTables;
import yesman.epicfight.api.forgeevent.SkillLootTableRegistryEvent;

@EventBusSubscriber(
    modid = "wom",
    bus = Bus.MOD
)
@Mixin(
    value = {WOMLootDropTables.class},
    remap = false
)
public class WOMLootDropTablesMixin {
    public WOMLootDropTablesMixin() {
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    @SubscribeEvent
    public static void SkillLootDrop(SkillLootTableRegistryEvent event) {
    }
}
