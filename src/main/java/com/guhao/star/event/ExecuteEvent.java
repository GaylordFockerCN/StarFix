//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.event;

import com.guhao.star.efmex.StarAnimations;
import com.guhao.star.regirster.Effect;
import com.guhao.star.regirster.Sounds;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.api.animation.types.LongHitAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.network.server.SPPlayAnimation;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.CapabilityItem.WeaponCategories;

@EventBusSubscriber
public class ExecuteEvent {
    private static final ArrayList<CapabilityItem.WeaponCategories> sekiro = new ArrayList();

    public ExecuteEvent() {
    }

    @SubscribeEvent(
        priority = EventPriority.HIGHEST
    )
    public static void onRightClickEntity(PlayerInteractEvent.RightClickItem event) {
        if (event.getHand() == event.getPlayer().getUsedItemHand()) {
            execute(event, event.getPlayer(), event.getPlayer().getLevel());
        }
    }

    public static void execute(Player player, Level level) {
        execute((Event)null, player, level);
    }

    private static void execute(@Nullable Event event, Player player, Level level) {
        if (player != null) {
            Vec3 _center = new Vec3(player.getX(), player.getEyeY(), player.getZ());
            List<LivingEntity> _entfound = level.getEntitiesOfClass(LivingEntity.class, (new AABB(_center, _center)).inflate(3.0), (e) -> {
                return true;
            }).stream().sorted(Comparator.comparingDouble((_entcnd) -> {
                return _entcnd.distanceToSqr(_center);
            })).toList();
            Iterator var5 = _entfound.iterator();

            while(var5.hasNext()) {
                LivingEntity entityiterator = (LivingEntity)var5.next();
                LivingEntityPatch<?> ep = (LivingEntityPatch)EpicFightCapabilities.getEntityPatch(entityiterator, LivingEntityPatch.class);
                if (ep != null && entityiterator != player) {
                    PlayerPatch pp;
                    DynamicAnimation var11;
                    boolean var10000;
                    label77: {
                        pp = (PlayerPatch)EpicFightCapabilities.getEntityPatch(player, PlayerPatch.class);
                        var11 = ep.getAnimator().getPlayerFor((DynamicAnimation)null).getAnimation();
                        if (var11 instanceof StaticAnimation) {
                            StaticAnimation staticAnimation = (StaticAnimation)var11;
                            if (staticAnimation == Animations.BIPED_KNEEL) {
                                var10000 = true;
                                break label77;
                            }
                        }

                        var10000 = false;
                    }

                    boolean var10001;
                    label72: {
                        var11 = ep.getAnimator().getPlayerFor((DynamicAnimation)null).getAnimation();
                        if (var11 instanceof LongHitAnimation) {
                            LongHitAnimation longHitAnimation = (LongHitAnimation)var11;
                            if (longHitAnimation == Animations.WITHER_NEUTRALIZED | longHitAnimation == Animations.VEX_NEUTRALIZED | longHitAnimation == Animations.SPIDER_NEUTRALIZED | longHitAnimation == Animations.DRAGON_NEUTRALIZED | longHitAnimation == Animations.ENDERMAN_NEUTRALIZED | longHitAnimation == Animations.BIPED_COMMON_NEUTRALIZED | longHitAnimation == Animations.GREATSWORD_GUARD_BREAK) {
                                var10001 = true;
                                break label72;
                            }
                        }

                        var10001 = false;
                    }

                    if (var10000 | var10001) {
                        Vec3 viewVec = ((LivingEntity)ep.getOriginal()).getViewVector(1.0F);
                        Vec3 viewVec_r = ((LivingEntity)ep.getOriginal()).getViewVector(1.0F).reverse();
                        ep.playSound(Sounds.SEKIRO, 1.0F, 1.0F);
                        player.teleportTo(((LivingEntity)ep.getOriginal()).getX() + viewVec.x() * 1.85, ((LivingEntity)ep.getOriginal()).getY(), ((LivingEntity)ep.getOriginal()).getZ() + viewVec.z() * 1.85);
                        player.addEffect(new MobEffectInstance((MobEffect)Effect.EXECUTE.get(), 100, 0));
                        ((LivingEntity)ep.getOriginal()).addEffect(new MobEffectInstance((MobEffect)Effect.EXECUTED.get(), 100, 0));
                        pp.playAnimationSynchronized(StarAnimations.EXECUTE_SEKIRO, 0.0F);
                        if (!level.isClientSide()) {
                            ep.playAnimationSynchronized(StarAnimations.EXECUTED_SEKIRO, 0.0F, SPPlayAnimation::new);
                        }
                        break;
                    }
                }
            }

        }
    }

    static {
        sekiro.add(WeaponCategories.SWORD);
        sekiro.add(WeaponCategories.DAGGER);
        sekiro.add(WeaponCategories.SHIELD);
        sekiro.add(WeaponCategories.TACHI);
        sekiro.add(WeaponCategories.UCHIGATANA);
    }
}
