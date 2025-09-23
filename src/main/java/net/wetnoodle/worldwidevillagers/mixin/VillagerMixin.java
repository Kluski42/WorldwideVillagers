package net.wetnoodle.worldwidevillagers.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ReputationEventHandler;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.Level;
import net.wetnoodle.worldwidevillagers.VillagerBiomeLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Villager.class)
public abstract class VillagerMixin extends AbstractVillager implements ReputationEventHandler, VillagerDataHolder {
    public VillagerMixin(EntityType<? extends AbstractVillager> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "getBreedOffspring(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/npc/Villager;",
            at = @At("HEAD"), cancellable = true)
    private void getBreedOffspringMixin(ServerLevel serverLevel, AgeableMob ageableMob, CallbackInfoReturnable<Villager> cir) {
        if (!VillagerBiomeLogic.isVillagerBiome(serverLevel.getBiome(this.getOnPos()))) {
            double d = this.random.nextDouble();
            VillagerType villagerType;
            if (d < 0.5) {
                villagerType = this.getVillagerData().getType();
            } else {
                villagerType = ((Villager) ageableMob).getVillagerData().getType();
            }
            Villager villagerEntity = new Villager(EntityType.VILLAGER, serverLevel, villagerType);
            villagerEntity.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(villagerEntity.getOnPos()), EntitySpawnReason.BREEDING, null);
            cir.setReturnValue(villagerEntity);
        }
    }
}
