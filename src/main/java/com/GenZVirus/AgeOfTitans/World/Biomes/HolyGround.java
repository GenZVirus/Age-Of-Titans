package com.GenZVirus.AgeOfTitans.World.Biomes;

import com.GenZVirus.AgeOfTitans.Init.BlockInit;
import com.GenZVirus.AgeOfTitans.World.Feature.EdenTree;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;

public class HolyGround extends Biome {

	// This is the Holy Ground biome class
	
	public HolyGround(Builder biomeBuilder) {
		super(biomeBuilder);
		addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.BEE, 20, 2, 10));
		addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.14285715F)));
		addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.NORMAL_TREE.withConfiguration(EdenTree.EDEN_TREE_CONFIG).withPlacement(
						Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(1, 0.01F, 1))));
		DefaultBiomeFeatures.addOres(this);
		DefaultBiomeFeatures.addStoneVariants(this);
		DefaultBiomeFeatures.addGrass(this);
		DefaultBiomeFeatures.addLakes(this);
		DefaultBiomeFeatures.addVeryDenseGrass(this);
		DefaultBiomeFeatures.addExtraDefaultFlowers(this);
		addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE
				.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.TITANIUM_ORE.get().getDefaultState(), 10))
				.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 5, 50))));
	}

}
