//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.event;

import java.util.Iterator;
import java.util.List;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

public class MimicTearsSpawnEvent {
    public MimicTearsSpawnEvent() {
    }

    public static void execute(Monster mimicTears, Level level) {
        if (mimicTears != null) {
            Player player = level.getNearestPlayer(mimicTears, -1.0);
            if (player != null) {
                ItemStack main_setstack = player.getMainHandItem();
                ItemStack off_setstack = player.getOffhandItem();
                ItemStack feet = player.getItemBySlot(EquipmentSlot.FEET);
                ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
                ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
                ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
                main_setstack.setCount(player.getMainHandItem().getCount());
                off_setstack.setCount(player.getOffhandItem().getCount());
                feet.setCount(player.getItemBySlot(EquipmentSlot.FEET).getCount());
                legs.setCount(player.getItemBySlot(EquipmentSlot.LEGS).getCount());
                chest.setCount(player.getItemBySlot(EquipmentSlot.CHEST).getCount());
                head.setCount(player.getItemBySlot(EquipmentSlot.HEAD).getCount());
                mimicTears.setItemInHand(InteractionHand.MAIN_HAND, main_setstack);
                mimicTears.setItemInHand(InteractionHand.OFF_HAND, off_setstack);
                mimicTears.setItemSlot(EquipmentSlot.FEET, feet);
                mimicTears.setItemSlot(EquipmentSlot.LEGS, legs);
                mimicTears.setItemSlot(EquipmentSlot.CHEST, chest);
                mimicTears.setItemSlot(EquipmentSlot.HEAD, head);
                mimicTears.getAttribute(Attributes.MAX_HEALTH).setBaseValue(player.getAttribute(Attributes.MAX_HEALTH).getValue() * 4.0);
                mimicTears.getAttribute(Attributes.ARMOR).setBaseValue(player.getAttribute(Attributes.ARMOR).getBaseValue());
                mimicTears.getAttribute(Attributes.ARMOR_TOUGHNESS).setBaseValue(player.getAttribute(Attributes.ARMOR_TOUGHNESS).getBaseValue());
                mimicTears.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(player.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue());
                mimicTears.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(player.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getBaseValue());
                mimicTears.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(player.getAttribute(Attributes.ATTACK_KNOCKBACK).getBaseValue());
                if (mimicTears.getAttribute((Attribute)EpicFightAttributes.OFFHAND_IMPACT.get()) != null) {
                    mimicTears.getAttribute((Attribute)EpicFightAttributes.OFFHAND_IMPACT.get()).setBaseValue(player.getAttribute((Attribute)EpicFightAttributes.OFFHAND_IMPACT.get()).getBaseValue());
                }

                if (mimicTears.getAttribute((Attribute)EpicFightAttributes.OFFHAND_ATTACK_SPEED.get()) != null) {
                    mimicTears.getAttribute((Attribute)EpicFightAttributes.OFFHAND_ATTACK_SPEED.get()).setBaseValue(player.getAttribute((Attribute)EpicFightAttributes.OFFHAND_ATTACK_SPEED.get()).getBaseValue());
                }

                if (mimicTears.getAttribute((Attribute)EpicFightAttributes.ARMOR_NEGATION.get()) != null) {
                    mimicTears.getAttribute((Attribute)EpicFightAttributes.ARMOR_NEGATION.get()).setBaseValue(player.getAttribute((Attribute)EpicFightAttributes.ARMOR_NEGATION.get()).getBaseValue());
                }

                if (mimicTears.getAttribute((Attribute)EpicFightAttributes.STUN_ARMOR.get()) != null) {
                    mimicTears.getAttribute((Attribute)EpicFightAttributes.STUN_ARMOR.get()).setBaseValue(player.getAttribute((Attribute)EpicFightAttributes.STUN_ARMOR.get()).getBaseValue());
                }

                if (mimicTears.getAttribute((Attribute)EpicFightAttributes.MAX_STAMINA.get()) != null) {
                    mimicTears.getAttribute((Attribute)EpicFightAttributes.MAX_STAMINA.get()).setBaseValue(player.getAttribute((Attribute)EpicFightAttributes.MAX_STAMINA.get()).getBaseValue());
                }

                if (mimicTears.getAttribute((Attribute)EpicFightAttributes.IMPACT.get()) != null) {
                    mimicTears.getAttribute((Attribute)EpicFightAttributes.IMPACT.get()).setBaseValue(player.getAttribute((Attribute)EpicFightAttributes.MAX_STAMINA.get()).getBaseValue());
                }

                mimicTears.setHealth(mimicTears.getMaxHealth());
                List<MobEffectInstance> effects = player.getActiveEffects().stream().toList();
                Iterator var10 = effects.iterator();

                while(var10.hasNext()) {
                    MobEffectInstance effect = (MobEffectInstance)var10.next();
                    if (effect.getEffect().isBeneficial()) {
                        mimicTears.addEffect(new MobEffectInstance(effect.getEffect(), effect.getDuration() + 1200, effect.getAmplifier()));
                    }
                }
            }

        }
    }
}