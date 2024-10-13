//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.event;

import com.guhao.star.units.Guard_Array;
import java.util.Arrays;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.world.damagesource.EpicFightDamageSource;

@EventBusSubscriber(
    modid = "star"
)
public class ShieldBlockEvent {
    Guard_Array star_new$GuardArray = new Guard_Array();
    StaticAnimation[] star_new$GUARD;

    public ShieldBlockEvent() {
        this.star_new$GUARD = this.star_new$GuardArray.getGuard();
    }

    @SubscribeEvent(
        priority = EventPriority.HIGHEST
    )
    public void shieldEvent(net.minecraftforge.event.entity.living.ShieldBlockEvent event) {
        EpicFightDamageSource epicFightDamageSource = Guard_Array.getEpicFightDamageSources(event.getDamageSource());
        if (epicFightDamageSource != null && !Arrays.asList(this.star_new$GUARD).contains(epicFightDamageSource.getAnimation())) {
            ItemStack itemStack1 = event.getEntityLiving().getItemInHand(InteractionHand.MAIN_HAND);
            ItemStack itemStack2 = event.getEntityLiving().getItemInHand(InteractionHand.OFF_HAND);
            Item var7 = itemStack1.getItem();
            ShieldItem shieldItem2;
            Player player;
            LivingEntity var8;
            if (var7 instanceof ShieldItem) {
                shieldItem2 = (ShieldItem)var7;
                var8 = event.getEntityLiving();
                if (var8 instanceof Player) {
                    player = (Player)var8;
                    player.getCooldowns().addCooldown(shieldItem2, 200);
                }
            }

            var7 = itemStack2.getItem();
            if (var7 instanceof ShieldItem) {
                shieldItem2 = (ShieldItem)var7;
                var8 = event.getEntityLiving();
                if (var8 instanceof Player) {
                    player = (Player)var8;
                    player.getCooldowns().addCooldown(shieldItem2, 200);
                }
            }
        }

    }
}
