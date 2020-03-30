package com.GenZVirus.AgeOfTitans.Init;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.World.Dimensions.EdenModDimension;

import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DimensionInit {

	public static final DeferredRegister<ModDimension> MOD_DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, AgeOfTitans.MOD_ID);
	
	public static final RegistryObject<ModDimension> EDEN = MOD_DIMENSIONS.register("eden", () -> new EdenModDimension());
}
