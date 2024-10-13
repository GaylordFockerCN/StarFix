//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.client.particle.par;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

public class Dangers_Red$Dangers_RedParticleProvider2 implements ParticleProvider<SimpleParticleType> {
    private final SpriteSet spriteSet;

    public Dangers_Red$Dangers_RedParticleProvider2(SpriteSet spriteSet) {
        this.spriteSet = spriteSet;
    }

    public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        return new Dangers_Red(worldIn, x, y, z, this.spriteSet);
    }
}
