package com.GenZVirus.AgeOfTitans.World.Dimensions;

import java.util.Set;

import com.GenZVirus.AgeOfTitans.Init.BiomeInit;
import com.google.common.collect.ImmutableSet;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

public class EdenBiomeProvider extends BiomeProvider{
	
	// This is the biome provider class for Eden Dimension
	
	// Biome list
	
	private static final Set<Biome> biomeList = ImmutableSet.of(BiomeInit.HOLY_GROUND_PLAINS.get(), BiomeInit.HOLY_GROUND_HILLS.get(), BiomeInit.HOLY_GROUND_MOUNTAIN.get());
	
	public EdenBiomeProvider() {
		super(biomeList);
	}

	// Biome getter
	
	@Override
	public Biome getNoiseBiome(int x, int y, int z) {
		return BiomeInit.HOLY_GROUND_PLAINS.get();
	}

}
