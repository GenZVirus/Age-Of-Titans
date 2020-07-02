package com.GenZVirus.AgeOfTitans.World.Biomes;

import com.GenZVirus.AgeOfTitans.Init.BlockInit;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;

public class Holy_Base extends Biome {

	// This is the Holy Ground biome class
	
	public Holy_Base(Builder biomeBuilder) {
		super(biomeBuilder);
		addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.BEE, 20, 2, 10));
		DefaultBiomeFeatures.addOres(this);
		DefaultBiomeFeatures.addStoneVariants(this);
		addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE
				.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.TITANIUM_ORE.get().getDefaultState(), 10))
				.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 5, 50))));
	}

}
