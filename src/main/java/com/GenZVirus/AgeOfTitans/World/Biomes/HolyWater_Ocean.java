package com.GenZVirus.AgeOfTitans.World.Biomes;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

public class HolyWater_Ocean extends Holy_Base{

	public HolyWater_Ocean(Builder biomeBuilder) {
		super(biomeBuilder);
		DefaultBiomeFeatures.addOceanCarvers(this);
		DefaultBiomeFeatures.addSeagrass(this);
		DefaultBiomeFeatures.addTallSeagrassSparse(this);
		DefaultBiomeFeatures.addTallSeagrassLush(this);
		DefaultBiomeFeatures.addKelp(this);
		DefaultBiomeFeatures.addLakes(this);
	    DefaultBiomeFeatures.addDefaultFlowers(this);
	    DefaultBiomeFeatures.addSparseGrass(this);
		DefaultBiomeFeatures.addSprings(this);
	    this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.DOLPHIN, 1, 1, 2));
	}

}
