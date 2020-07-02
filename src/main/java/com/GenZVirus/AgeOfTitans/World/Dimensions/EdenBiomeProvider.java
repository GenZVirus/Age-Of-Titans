package com.GenZVirus.AgeOfTitans.World.Dimensions;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.GenZVirus.AgeOfTitans.Init.BiomeInit;
import com.google.common.collect.ImmutableSet;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

public class EdenBiomeProvider extends BiomeProvider{
	
	// This is the biome provider class for Eden Dimension
	
	@SuppressWarnings("unused")
	private Random rand;
	
	// Biome list
	
	private static final Set<Biome> biomeList = ImmutableSet.of(BiomeInit.HOLY_GROUND_PLAINS.get(),
																BiomeInit.HOLY_GROUND_HILLS.get(),
																BiomeInit.HOLY_GROUND_MOUNTAIN.get(),
																BiomeInit.HOLY_WATER_OCEAN.get());
	
	private final double biomeSize = 16.0D;
	private VoronoiGenerator biomeNoise;
	
	public EdenBiomeProvider(EdenBiomeProviderSettings settings) {
		super(biomeList);
		rand = new Random();
		this.biomeNoise = new VoronoiGenerator();
		this.biomeNoise.setSeed((int) settings.getSeed());
	}

	// Biome getter
	
	@Override
	public Biome getNoiseBiome(int x, int y, int z) {
		return getBiome(new LinkedList<Biome>(biomeList),
				biomeNoise.getValue((double) x / biomeSize, (double) y / biomeSize, (double) z / biomeSize));
	}
	
	public Biome getBiome(List<Biome> biomeList, double noiseVal) {
		for (int i = biomeList.size(); i >= 0; i--) {
			if (noiseVal > (2.0f / biomeList.size()) * i - 1)
				return biomeList.get(i);
		}
		return biomeList.get(biomeList.size() - 1);
	}

}
