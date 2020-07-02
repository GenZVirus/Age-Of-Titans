package com.GenZVirus.AgeOfTitans.World.Biomes;

import com.GenZVirus.AgeOfTitans.World.Feature.EdenTree;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;

public class HolyGround_Hills extends Holy_Base{

	public HolyGround_Hills(Builder biomeBuilder) {
		super(biomeBuilder);
		addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.14285715F)));
		DefaultBiomeFeatures.addGrass(this);
		DefaultBiomeFeatures.addLakes(this);
		DefaultBiomeFeatures.addVeryDenseGrass(this);
		DefaultBiomeFeatures.addExtraDefaultFlowers(this);
		addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.NORMAL_TREE.withConfiguration(EdenTree.EDEN_TREE_CONFIG).withPlacement(
						Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(1, 1.0F, 1))));
	}

}
