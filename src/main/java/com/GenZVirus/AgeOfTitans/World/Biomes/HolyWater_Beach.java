package com.GenZVirus.AgeOfTitans.World.Biomes;

import net.minecraft.world.biome.DefaultBiomeFeatures;

public class HolyWater_Beach extends Holy_Base{

	public HolyWater_Beach(Builder biomeBuilder) {
		super(biomeBuilder);
	    DefaultBiomeFeatures.addSprings(this);
	    DefaultBiomeFeatures.addDefaultFlowers(this);
	    DefaultBiomeFeatures.addSparseGrass(this);
	    DefaultBiomeFeatures.addLakes(this);
	}

}
