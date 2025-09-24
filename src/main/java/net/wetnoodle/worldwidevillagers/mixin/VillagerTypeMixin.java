package net.wetnoodle.worldwidevillagers.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.resources.ResourceKey;
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
    @ModifyExpressionValue(method = "byBiome", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/npc/VillagerType;PLAINS:Lnet/minecraft/resources/ResourceKey;"))
    private static ResourceKey<VillagerType> forBiomeRandomizer(ResourceKey<VillagerType> original) {
        return VillagerBiomeLogic.getRandom();
    }

    @ModifyReturnValue(method = "register", at = @At("RETURN"))
    private static VillagerType createMixin(VillagerType original, @Local(argsOnly = true) ResourceKey<VillagerType> resourceKey) {
        VillagerBiomeLogic.villagerTypes.add(resourceKey);
        return original;
    }
}

