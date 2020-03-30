package com.GenZVirus.AgeOfTitans.Util.Helpers;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Init.DimensionInit;

import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class ForgeEventBusSubscriber {
	
	@SubscribeEvent
	public static void registerDimensions(final RegisterDimensionsEvent event) {
		if (DimensionType.byName(AgeOfTitans.EDEN_DIMENSION_TYPE) == null) {
			DimensionManager.registerDimension(AgeOfTitans.EDEN_DIMENSION_TYPE, DimensionInit.EDEN.get(), null,
					true);
		}
		AgeOfTitans.LOGGER.info("Dimensions Registered!");
	}
}
