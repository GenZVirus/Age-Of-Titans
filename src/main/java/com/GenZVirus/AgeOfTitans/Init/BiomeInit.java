package com.GenZVirus.AgeOfTitans.Init;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.World.Biomes.HolyGround;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {

	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES,
			AgeOfTitans.MOD_ID);

	public static final RegistryObject<Biome> HOLY_GROUND = BIOMES.register("holy_ground",
			() -> new HolyGround(new Biome.Builder()
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

}
