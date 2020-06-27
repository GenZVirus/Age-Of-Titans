package com.GenZVirus.AgeOfTitans.World.Gen;

import com.GenZVirus.AgeOfTitans.Init.BlockInit;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class ModOreGen {

	// This class generates the custom ores
	
	// Ore generation method
	
	@SuppressWarnings("rawtypes")
	public static void generateOre() {
		for (Biome biome : ForgeRegistries.BIOMES) {
				ConfiguredPlacement customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 5, 50));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE
								.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.TITANIUM_ORE.get().getDefaultState(), 10))
								.withPlacement(customConfig));
		}
	}

}
