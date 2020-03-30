package com.GenZVirus.AgeOfTitans.Init;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.World.Biomes.HolyGround;

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

	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES,
			AgeOfTitans.MOD_ID);

	public static final RegistryObject<Biome> HOLY_GROUND = BIOMES.register("holy_ground",
			() -> new HolyGround(new Biome.Builder()
					.precipitation(RainType.RAIN)
					.scale(1.2F)
					.temperature(0.5F)
					.waterColor(0x00FFFF)
					.waterFogColor(0x00FFFF)
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(
							BlockInit.TITANIUM_BLOCK.get().getDefaultState(),
							BlockInit.TITANIUM_ORE.get().getDefaultState(), 
							Blocks.DIRT.getDefaultState()))
					.category(Category.PLAINS)
					.downfall(0.5F)
					.depth(0.12F)
					.parent(null)));
	
	public static void registeBiomes() {
		registerBiome(HOLY_GROUND.get(), Type.PLAINS, Type.OVERWORLD);
	}

	private static void registerBiome(Biome biome, Type... types) {
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addSpawnBiome(biome);
	}
	
}
