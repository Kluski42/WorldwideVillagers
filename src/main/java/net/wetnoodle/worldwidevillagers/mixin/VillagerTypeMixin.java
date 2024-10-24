package net.wetnoodle.worldwidevillagers.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.npc.VillagerType;
import net.wetnoodle.worldwidevillagers.VillagerBiomeLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = VillagerType.class, priority = 200)
public abstract class VillagerTypeMixin {
    /**
     * If a villager biome is not found, return a random villager biome.
     *
     * @param original the plains villager type. Feel free to ignore
     * @return a random villager type
     */
    @ModifyExpressionValue(method = "byBiome", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/npc/VillagerType;PLAINS:Lnet/minecraft/world/entity/npc/VillagerType;"))
    private static VillagerType forBiomeRandomizer(VillagerType original) {
        return VillagerBiomeLogic.getRandom();
    }

    @ModifyReturnValue(method = "register", at = @At("RETURN"))
    private static VillagerType createMixin(VillagerType original) {
        VillagerBiomeLogic.villagerTypes.add(original);
        return original;
    }
}

