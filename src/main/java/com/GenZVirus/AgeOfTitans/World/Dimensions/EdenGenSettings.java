package com.GenZVirus.AgeOfTitans.World.Dimensions;

import net.minecraft.world.gen.GenerationSettings;

public class EdenGenSettings extends GenerationSettings {

	// This class contains the settings of the Dimension returning them through getters
	
	// Biome size getter
	
	public int getBiomeSize() {
		return 4;
	}

	// River size getter	
	
	public int getRiverSize() {
		return 4;
	}
	
	// Biome id getter

	public int getBiomeId() {
		return -1;
	}
	
	// Bedrock Floor Height level getter

	@Override
	public int getBedrockFloorHeight() {
		return 0;
	}
	
}
