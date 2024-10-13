//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.client.particle.par;

import com.guhao.star.api.ParticleRenderTypeN;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Dangers extends TextureSheetParticle {
    @OnlyIn(Dist.CLIENT)
    public static DangerParticleProvider2 provider(SpriteSet spriteSet) {
        return new DangerParticleProvider2(spriteSet);
    }

    protected Dangers(ClientLevel world, double x, double y, double z, SpriteSet spriteSet) {
        super(world, x, y, z);
        this.setSize(2.5F, 2.5F);
        this.quadSize *= 2.85F;
        this.lifetime = 25;
        this.gravity = 0.0F;
        this.hasPhysics = false;
        this.setSpriteFromAge(spriteSet);
    }

    public boolean shouldCull() {
        return false;
    }

    public int getLightColor(float partialTick) {
        return 15728880;
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderTypeN.PARTICLE_SHEET_LIT_NO_CULL;
    }

    public void tick() {
        super.tick();
    }

    public static class DangerParticleProvider2 implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public DangerParticleProvider2(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new Dangers(worldIn, x, y, z, this.spriteSet);
        }
    }
}
