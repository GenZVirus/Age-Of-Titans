package com.GenZVirus.AgeOfTitans.World.Dimensions;

import java.util.Set;

import com.GenZVirus.AgeOfTitans.Init.BiomeInit;
import com.google.common.collect.ImmutableSet;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

public class EdenBiomeProvider extends BiomeProvider{
	
	private static final Set<Biome> biomeList = ImmutableSet.of(BiomeInit.HOLY_GROUND.get());
	
	public EdenBiomeProvider() {
		super(biomeList);
	}

	
	@Override
	public Biome getNoiseBiome(int x, int y, int z) {
		return BiomeInit.HOLY_GROUND.get();
	}

}
