package net.wetnoodle.worldwidevillagers;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.biome.Biome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VillagerBiomeLogic {
    public static List<VillagerType> villagerTypes = new ArrayList<>();

    public static VillagerType getRandom() {
        Random random = new Random();
        return villagerTypes.get(random.nextInt(villagerTypes.size()));
    }

    public static boolean isVillagerBiome(Holder<Biome> biomeEntry) {
        for (ResourceKey<Biome> biome : VillagerType.BY_BIOME.keySet()) {
            if (biomeEntry.is(biome)) {
                return true;
            }
        }
        return false;
    }
}
