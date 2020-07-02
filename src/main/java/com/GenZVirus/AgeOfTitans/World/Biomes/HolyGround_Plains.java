package com.GenZVirus.AgeOfTitans.World.Biomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class HolyGround_Plains extends Holy_Base{

	public HolyGround_Plains(Builder biomeBuilder) {
		super(biomeBuilder);
		addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.14285715F)));
		DefaultBiomeFeatures.addGrass(this);
		DefaultBiomeFeatures.addLakes(this);
		DefaultBiomeFeatures.addVeryDenseGrass(this);
		DefaultBiomeFeatures.addExtraDefaultFlowers(this);
	}
}
