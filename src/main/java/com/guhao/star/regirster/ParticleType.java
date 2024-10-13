//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.regirster;

import com.guhao.star.client.particle.par.Dangers;
import com.guhao.star.client.particle.par.Dangers_Red;
import com.guhao.star.client.particle.par.Fire_Ball;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(
    bus = Bus.MOD,
    value = {Dist.CLIENT}
)
public class ParticleType {
    public static final DeferredRegister<net.minecraft.core.particles.ParticleType<?>> PARTICLES;
    public static final RegistryObject<SimpleParticleType> DANGER;
    public static final RegistryObject<SimpleParticleType> DANGER_RED;
    public static final RegistryObject<SimpleParticleType> FIRE_BALL;

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void RP(ParticleFactoryRegisterEvent event) {
        ParticleEngine PE = Minecraft.getInstance().particleEngine;
        PE.register((net.minecraft.core.particles.ParticleType)DANGER.get(), Dangers.DangerParticleProvider2::new);
        PE.register((net.minecraft.core.particles.ParticleType)DANGER_RED.get(), Dangers_Red.Dangers_RedParticleProvider::new);
        PE.register((net.minecraft.core.particles.ParticleType)FIRE_BALL.get(), Fire_Ball.Provider2::new);
    }

    public ParticleType() {
    }

    static {
        PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, "star");
        DANGER = PARTICLES.register("dangers", () -> {
            return new SimpleParticleType(true);
        });
        DANGER_RED = PARTICLES.register("dangers_red", () -> {
            return new SimpleParticleType(true);
        });
        FIRE_BALL = PARTICLES.register("fire_ball", () -> {
            return new SimpleParticleType(true);
        });
    }
}
