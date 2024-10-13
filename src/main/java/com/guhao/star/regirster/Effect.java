//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.regirster;

import com.guhao.star.effects.Defense;
import com.guhao.star.effects.Execute;
import com.guhao.star.effects.Executed;
import com.guhao.star.effects.Really_stun_immunity;
import com.guhao.star.effects.Unstable;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Effect {
    public static final DeferredRegister<MobEffect> REGISTRY;
    public static final RegistryObject<MobEffect> DEFENSE;
    public static final RegistryObject<MobEffect> UNSTABLE;
    public static final RegistryObject<MobEffect> EXECUTE;
    public static final RegistryObject<MobEffect> EXECUTED;
    public static final RegistryObject<MobEffect> REALLY_STUN_IMMUNITY;

    public Effect() {
    }

    static {
        REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "star");
        DEFENSE = REGISTRY.register("defense", Defense::new);
        UNSTABLE = REGISTRY.register("unstable", Unstable::new);
        EXECUTE = REGISTRY.register("execute", Execute::new);
        EXECUTED = REGISTRY.register("executed", Executed::new);
        REALLY_STUN_IMMUNITY = REGISTRY.register("really_stun_immunity", Really_stun_immunity::new);
    }
}
