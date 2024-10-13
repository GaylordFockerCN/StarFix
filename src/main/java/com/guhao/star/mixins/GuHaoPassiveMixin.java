//package com.guhao.star.mixins;
//
//import com.guhao.GuHaoAnimations;
//import com.guhao.skills.GuHaoPassive;
//import net.minecraft.world.entity.player.Player;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//import yesman.epicfight.api.animation.types.DodgeAnimation;
//import yesman.epicfight.client.ClientEngine;
//import yesman.epicfight.network.client.CPExecuteSkill;
//import yesman.epicfight.skill.Skill;
//import yesman.epicfight.skill.SkillContainer;
//import yesman.epicfight.skill.SkillDataManager;
//import yesman.epicfight.skill.passive.PassiveSkill;
//import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
//import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;
//
//import java.util.UUID;
//
//@Mixin(value = GuHaoPassive.class, remap = false)
//public class GuHaoPassiveMixin extends PassiveSkill {
//
//    @Final
//    @Shadow
//    public static SkillDataManager.SkillDataKey<Boolean> SHEATH;
//
//    @Shadow
//    @Final
//    private static UUID EVENT_UUID;
//    public GuHaoPassiveMixin(Builder<? extends Skill> builder) {
//        super(builder);
//    }
//
//    @Inject(method = "onInitiate", at = @At("HEAD"), cancellable = true)
//    public void inject(SkillContainer container, CallbackInfo ci){
//        super.onInitiate(container);
//        container.getDataManager().registerData(SHEATH);
//        container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.ACTION_EVENT_SERVER, EVENT_UUID, (event) -> {
//            if (!(event.getAnimation() instanceof DodgeAnimation)) {
//                container.getSkill().setConsumptionSynchronize((ServerPlayerPatch)event.getPlayerPatch(), 0.0F);
//                container.getSkill().setStackSynchronize((ServerPlayerPatch)event.getPlayerPatch(), 0);
//            }
//
//        });
//        container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.SERVER_ITEM_USE_EVENT, EVENT_UUID, (event) -> {
//            this.onReset(container);
//        });
//        container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.BASIC_ATTACK_EVENT, EVENT_UUID, (event) -> {
//            if (((Player)container.getExecuter().getOriginal()).isUsingItem() && container.getExecuter().isLogicalClient()) {
//                CPExecuteSkill cpExecuteSkill = new CPExecuteSkill(container.getExecuter().getSkill(this).getSlotId());
//                ClientEngine.getInstance().controllEngine.addPacketToSend(cpExecuteSkill);
//                ((Player)container.getExecuter().getOriginal()).stopUsingItem();
//                event.setCanceled(true);
//                container.getExecuter().playAnimationSynchronized(GuHaoAnimations.JIANQIE, 0.0F);
//            }
//
//        });
//        ci.cancel();
//    }
//
//}
