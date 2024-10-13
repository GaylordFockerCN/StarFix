//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins;

import java.util.Map;
import java.util.UUID;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import reascer.wom.events.WOMLivingEntityEvents;
import reascer.wom.gameasset.WOMEnchantment;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

@EventBusSubscriber(
    modid = "wom",
    bus = Bus.FORGE
)
@Mixin(
    value = {WOMLivingEntityEvents.class},
    remap = false
)
public class WOMLivingEntityEventsMixin {
    @Shadow
    private static final Map<EquipmentSlot, UUID> STAMINAR_ADD = makeUUIDMap("wom_staminar_add");

    public WOMLivingEntityEventsMixin() {
    }

    @Shadow
    private static Map<EquipmentSlot, UUID> makeUUIDMap(String key) {
        return Map.of();
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    @SubscribeEvent
    public static void itemAttributeModifiers(ItemAttributeModifierEvent event) {
        float invigoration = 0.0F;
        ItemStack stack = event.getItemStack();
        EquipmentSlot slot = event.getSlotType();
        if (slot == LivingEntity.getEquipmentSlotForItem(stack)) {
            invigoration += (float)EnchantmentHelper.getItemEnchantmentLevel((Enchantment)WOMEnchantment.INVIGORATION.get(), stack);
        }

        if (invigoration != 0.0F) {
            event.addModifier((Attribute)EpicFightAttributes.MAX_STAMINA.get(), new AttributeModifier((UUID)STAMINAR_ADD.get(slot), "invigoration_stamina_add", (double)(invigoration * 0.1F), Operation.ADDITION));
        }

    }
}
