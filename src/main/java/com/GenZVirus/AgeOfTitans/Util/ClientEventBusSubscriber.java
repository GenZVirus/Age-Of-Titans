package com.GenZVirus.AgeOfTitans.Util;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Client.Entity.Render.ReaperEntityRender;
import com.GenZVirus.AgeOfTitans.Client.GUI.ExampleChestScreen;
import com.GenZVirus.AgeOfTitans.Init.BlockInit;
import com.GenZVirus.AgeOfTitans.Init.ModContainerTypes;
import com.GenZVirus.AgeOfTitans.Init.ModEntityTypes;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ScreenManager.registerFactory(ModContainerTypes.EXAMPLE_CHEST.get(), ExampleChestScreen::new);
		RenderTypeLookup.setRenderLayer(BlockInit.EDEN_SAPLING.get(), RenderType.getCutout());
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.REAPER.get(), ReaperEntityRender::new);
	}
	
}