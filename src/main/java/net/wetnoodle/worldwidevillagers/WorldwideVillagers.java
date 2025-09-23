package net.wetnoodle.worldwidevillagers;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.biome.Biomes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.data.worldgen.WinterDropBiomes;

public class WorldwideVillagers implements ModInitializer {
    public static final String MOD_ID = "worldwidevillagers";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        VillagerType.BY_BIOME.put(Biomes.BIRCH_FOREST, VillagerType.PLAINS);
        VillagerType.BY_BIOME.put(Biomes.OLD_GROWTH_BIRCH_FOREST, VillagerType.PLAINS);
        VillagerType.BY_BIOME.put(Biomes.DARK_FOREST, VillagerType.PLAINS);
        VillagerType.BY_BIOME.put(WinterDropBiomes.PALE_GARDEN, VillagerType.PLAINS);
        VillagerType.BY_BIOME.put(Biomes.FOREST, VillagerType.PLAINS);
        VillagerType.BY_BIOME.put(Biomes.FLOWER_FOREST, VillagerType.PLAINS);
        VillagerType.BY_BIOME.put(Biomes.MEADOW, VillagerType.PLAINS);
        VillagerType.BY_BIOME.put(Biomes.CHERRY_GROVE, VillagerType.PLAINS);
        VillagerType.BY_BIOME.put(Biomes.PLAINS, VillagerType.PLAINS);
        VillagerType.BY_BIOME.put(Biomes.SUNFLOWER_PLAINS, VillagerType.PLAINS);
        VillagerType.BY_BIOME.put(Biomes.LUSH_CAVES, VillagerType.SWAMP);
    }
}