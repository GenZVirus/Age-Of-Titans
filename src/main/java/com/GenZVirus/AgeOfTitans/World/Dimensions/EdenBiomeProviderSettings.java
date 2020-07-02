package com.GenZVirus.AgeOfTitans.World.Dimensions;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.storage.WorldInfo;

public class EdenBiomeProviderSettings implements IBiomeProviderSettings {

	private final long seed;
	private final WorldType worldType;
	private EdenGenSettings generatorSettings = new EdenGenSettings();

	public EdenBiomeProviderSettings(WorldInfo info) {
		this.seed = info.getSeed();
		this.worldType = info.getGenerator();
	}

	public EdenBiomeProviderSettings setGeneratorSettings(EdenGenSettings settings) {
		this.generatorSettings = settings;
		return this;
	}

	public long getSeed() {
		return this.seed;
	}

	public WorldType getWorldType() {
		return this.worldType;
	}

	public EdenGenSettings getGeneratorSettings() {
		return this.generatorSettings;
	}
}