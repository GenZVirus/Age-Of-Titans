package com.GenZVirus.AgeOfTitans.Common.Init;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.World.Biomes.HolyGround_Hills;
import com.GenZVirus.AgeOfTitans.World.Biomes.HolyGround_Mountains;
import com.GenZVirus.AgeOfTitans.World.Biomes.HolyGround_Plains;
import com.GenZVirus.AgeOfTitans.World.Biomes.HolyWater_Ocean;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {

	// This is an initialization class for biomes
	
	// Registering with deferred registries
	
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES,
			AgeOfTitans.MOD_ID);
	
	// Creating the object
	
	public static final RegistryObject<Biome> HOLY_GROUND_PLAINS = BIOMES.register("holy_ground_plains",
			() -> new HolyGround_Plains(new Biome.Builder()
					.precipitation(RainType.RAIN)
					.scale(0.05F)
					.temperature(1.0F)
					.waterColor(0x00FFFF)
					.waterFogColor(0x00FFFF)
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(
							Blocks.GRASS_BLOCK.getDefaultState(),
							Blocks.DIRT.getDefaultState(), 
							Blocks.SAND.getDefaultState()))
					.category(Category.PLAINS)
					.downfall(0.5F)
					.depth(0.12F)
					.parent(null)));
	
	public static final RegistryObject<Biome> HOLY_GROUND_HILLS = BIOMES.register("holy_ground_hills",
			() -> new HolyGround_Hills(new Biome.Builder()
					.precipitation(RainType.RAIN)
					.scale(0.3F)
					.temperature(1.0F)
					.waterColor(0x00FFFF)
					.waterFogColor(0x00FFFF)
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(
							Blocks.GRASS_BLOCK.getDefaultState(),
							Blocks.DIRT.getDefaultState(), 
							Blocks.SAND.getDefaultState()))
					.category(Category.JUNGLE)
					.downfall(0.5F)
					.depth(0.45F)
					.parent(null)));
	
	public static final RegistryObject<Biome> HOLY_GROUND_MOUNTAIN = BIOMES.register("holy_ground_mountain",
			() -> new HolyGround_Mountains(new Biome.Builder()
					.precipitation(RainType.RAIN)
					.scale(0.5F)
					.temperature(1.0F)
					.waterColor(0x00FFFF)
					.waterFogColor(0x00FFFF)
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(
							Blocks.GRASS_BLOCK.getDefaultState(),
							Blocks.DIRT.getDefaultState(), 
							Blocks.SAND.getDefaultState()))
					.category(Category.EXTREME_HILLS)
					.downfall(0.5F)
					.depth(1.0F)
					.parent(null)));
	
	public static final RegistryObject<Biome> HOLY_WATER_OCEAN = BIOMES.register("holy_water_ocean",
			() -> new HolyWater_Ocean(new Biome.Builder()
					.precipitation(RainType.RAIN)
					.scale(0.1F)
					.temperature(1.0F)
					.waterColor(0x00FFFF)
					.waterFogColor(0x00FFFF)
					.surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
					.category(Category.OCEAN)
					.downfall(0.5F)
					.depth(-1.0F)
					.parent(null)));
	
	public static final RegistryObject<Biome> HOLY_WATER_BEACH = BIOMES.register("holy_water_beach",
			() -> new HolyWater_Ocean(new Biome.Builder()
					.precipitation(RainType.RAIN)
					.scale(0.025F)
					.temperature(1.0F)
					.waterColor(0x00FFFF)
					.waterFogColor(0x00FFFF)
					.surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.SAND_SAND_GRAVEL_CONFIG)
					.category(Category.BEACH)
					.downfall(0.4F)
					.depth(0.0F)
					.parent(null)));
	
	// Register all biomes
	
	public static void registerBiomes() {
		registerBiome(HOLY_GROUND_PLAINS.get(), Type.PLAINS, Type.OVERWORLD);
		registerBiome(HOLY_GROUND_HILLS.get(), Type.HILLS, Type.OVERWORLD);
		registerBiome(HOLY_GROUND_MOUNTAIN.get(), Type.MOUNTAIN, Type.OVERWORLD);
		registerBiome(HOLY_WATER_OCEAN.get(), Type.OCEAN, Type.OVERWORLD);
		registerBiome(HOLY_WATER_BEACH.get(), Type.BEACH, Type.OVERWORLD);
	}

	// Register requested biome
	
	private static void registerBiome(Biome biome, Type... types) {
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addSpawnBiome(biome);
	}
	
}
