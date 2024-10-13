//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

@Mixin(
    value = {PlayerPatch.class},
    remap = false
)
public abstract class PlayerPatchMixin<T extends Player> extends LivingEntityPatch<T> {
    @Shadow
    protected int tickSinceLastAction;
    @Shadow
    protected double xo;
    @Shadow
    protected double yo;
    @Shadow
    protected double zo;

    public PlayerPatchMixin() {
    }

    @Shadow
    public float getMaxStamina() {
        AttributeInstance maxStamina = ((Player)this.original).getAttribute((Attribute)EpicFightAttributes.MAX_STAMINA.get());
        return (float)(maxStamina == null ? 0.0 : maxStamina.getValue());
    }

    @Shadow
    public float getStamina() {
        return this.getMaxStamina() == 0.0F ? 0.0F : (Float)((Player)this.original).getEntityData().get(PlayerPatch.STAMINA);
    }

    @Shadow
    public void setStamina(float value) {
        float f1 = Math.max(Math.min(value, this.getMaxStamina()), 0.0F);
        ((Player)this.original).getEntityData().set(PlayerPatch.STAMINA, f1);
    }

    public void updateMotion(boolean b) {
    }


}
