//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.event;

import com.guhao.star.Config;
import com.guhao.star.Star;
import com.guhao.star.client.RenderCustomKatana;
import com.guhao.star.regirster.Items;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import yesman.epicfight.api.client.forgeevent.PatchedRenderersEvent;
import yesman.epicfight.client.renderer.patched.entity.PDrownedRenderer;

@EventBusSubscriber(modid = Star.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModBusEvent {
    public ClientModBusEvent() {
    }

    @SubscribeEvent
    public void removeDrownedPatchRenderer(PatchedRenderersEvent.Modify event) {
        if (event.get(EntityType.DROWNED) instanceof PDrownedRenderer) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(
        priority = EventPriority.LOWEST
    )
    public static void RenderRegistry(PatchedRenderersEvent.Add event) {
        List<? extends String> katanaitem = (List)Config.KATANA_ITEM.get();
        Iterator var2 = katanaitem.iterator();

        while(var2.hasNext()) {
            String katana = (String)var2.next();
            String[] entry = katana.split(" ");
            Item katanaItem = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation(entry[0]));
            event.addItemRenderer(katanaItem, new RenderCustomKatana());
        }

    }

    @SubscribeEvent
    public static void propertyOverrideRegistry(FMLClientSetupEvent event) {
        Config.load();
        event.enqueueWork(() -> {
            ItemProperties.register((Item)Items.CUSTOM_SHEATH.get(), new ResourceLocation("star", "custom"), (Stack, World, Entity, i) -> {
                return Stack.getOrCreateTag().getFloat("custom_sheath");
            });
        });
        event.enqueueWork(() -> {
            ItemProperties.register((Item)Items.DEFENSE.get(), new ResourceLocation("star", "defense"), (Stack, World, Entity, i) -> {
                return Stack.getOrCreateTag().getFloat("enable_defense");
            });
        });
    }

}
