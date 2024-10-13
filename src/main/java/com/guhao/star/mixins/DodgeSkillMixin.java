//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins;

import com.guhao.star.Config;
import com.guhao.star.regirster.Sounds;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.dodge.DodgeSkill;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener.EventType;

@Mixin(
    value = {DodgeSkill.class},
    remap = false
)
public class DodgeSkillMixin extends Skill {
    private static final UUID EVENT_UUID = UUID.fromString("99e5c782-fdaf-11eb-9a03-0242ac130005");

    public DodgeSkillMixin(Skill.Builder<? extends Skill> builder) {
        super(builder);
    }

    @Unique
    public void star_new$delayedTask(SkillContainer container) {
        Level var3 = ((Player)container.getExecuter().getOriginal()).getLevel();
        if (var3 instanceof ServerLevel _level) {
            _level.getServer().getCommands().performCommand((new CommandSourceStack(CommandSource.NULL, new Vec3(((Player)container.getExecuter().getOriginal()).getX(), ((Player)container.getExecuter().getOriginal()).getY(), ((Player)container.getExecuter().getOriginal()).getZ()), Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), (Entity)null)).withSuppressedOutput(), "tickrate change 20");
        }

    }

    @Unique
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        container.getExecuter().getEventListener().addEventListener(EventType.DODGE_SUCCESS_EVENT, EVENT_UUID, (event) -> {
            Level patt2621$temp = ((Player)container.getExecuter().getOriginal()).getLevel();
            if (patt2621$temp instanceof ServerLevel _level) {
                _level.addAlwaysVisibleParticle((ParticleOptions)EpicFightParticles.ENTITY_AFTER_IMAGE.get(), ((Player)container.getExecuter().getOriginal()).getX(), ((Player)container.getExecuter().getOriginal()).getY(), ((Player)container.getExecuter().getOriginal()).getZ(), Double.longBitsToDouble((long)((Player)container.getExecuter().getOriginal()).getId()), 0.0, 0.0);
            }

            container.getExecuter().playSound(Sounds.FORESIGHT, 0.8F, 1.2F);
            if (Config.SLOW_TIME.get()) {
                patt2621$temp = ((Player)container.getExecuter().getOriginal()).getLevel();
                if (patt2621$temp instanceof ServerLevel serverLevel) {
                    serverLevel.getServer().getCommands().performCommand((new CommandSourceStack(CommandSource.NULL, new Vec3(((Player)container.getExecuter().getOriginal()).getX(), ((Player)container.getExecuter().getOriginal()).getY(), ((Player)container.getExecuter().getOriginal()).getZ()), Vec2.ZERO, serverLevel, 4, "", new TextComponent(""), serverLevel.getServer(), (Entity)null)).withSuppressedOutput(), "tickrate change 2");
                }

                ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
                scheduledExecutorService.schedule(() -> {
                    this.star_new$delayedTask(container);
                }, 100L, TimeUnit.MILLISECONDS);
            }

        });
    }

    @Unique
    public void onRemoved(SkillContainer container) {
        container.getExecuter().getEventListener().removeListener(EventType.DODGE_SUCCESS_EVENT, EVENT_UUID);
    }
}
