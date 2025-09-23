package net.wetnoodle.worldwidevillagers.mixin;

import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.wetnoodle.worldwidevillagers.VillagerBiomeLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ZombieVillager.class)
public abstract class ZombieVillagerMixin extends Zombie implements VillagerDataHolder {
    @Unique
    private static final double OUTSIDER_PROB = 0.10;

    public ZombieVillagerMixin(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "finalizeSpawn", at = @At("HEAD"), cancellable = true)
    public void finalizeSpawnMixin(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance,
                                   EntitySpawnReason spawnReason, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir
    ) {
        double d = new Random().nextDouble();
        if (d < OUTSIDER_PROB) {
            this.setVillagerData(this.getVillagerData().setType(VillagerBiomeLogic.getRandom()));
            cir.setReturnValue(super.finalizeSpawn(serverLevelAccessor, difficultyInstance, spawnReason, spawnGroupData));
        }
    }
}
