package com.GenZVirus.AgeOfTitans.Util;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Client.Container.ContainerScreenBasic;
import com.GenZVirus.AgeOfTitans.Client.Entity.Render.ChainRender;
import com.GenZVirus.AgeOfTitans.Client.Entity.Render.GravityBombRender;
import com.GenZVirus.AgeOfTitans.Client.Entity.Render.GravityZoneRender;
import com.GenZVirus.AgeOfTitans.Client.Entity.Render.ReaperEntityRender;
import com.GenZVirus.AgeOfTitans.Client.Entity.Render.ShockwaveRender;
import com.GenZVirus.AgeOfTitans.Client.Entity.Render.SwordSlashRender;
import com.GenZVirus.AgeOfTitans.Common.Entities.GravityZoneEnity;
import com.GenZVirus.AgeOfTitans.Common.Init.BlockInit;
import com.GenZVirus.AgeOfTitans.Common.Init.ModContainerTypes;
import com.GenZVirus.AgeOfTitans.Common.Init.ModEntityTypes;
import com.GenZVirus.AgeOfTitans.Common.Particles.GravityZoneCenterParticle;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	// Client Side Events
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ScreenManager.registerFactory(ModContainerTypes.BLACK_HOLE.get(), ContainerScreenBasic::new);
		RenderTypeLookup.setRenderLayer(BlockInit.EDEN_SAPLING.get(), RenderType.getCutout());
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.REAPER.get(), ReaperEntityRender::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SWORD_SLASH.get(), SwordSlashRender::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SHOCKWAVE.get(), ShockwaveRender::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CHAIN.get(), ChainRender::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GRAVITY_BOMB.get(), GravityBombRender::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GRAVITY_ZONE.get(), GravityZoneRender::new);
	}
	
	@SubscribeEvent(receiveCanceled = true)
	public static void onHealthBar(RenderGameOverlayEvent.Pre event) {
		event.setCanceled(true);
		if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
		}
	}
	
	@SubscribeEvent
	public static void particles(EntityEvent event) {
		Entity entity = event.getEntity();
		if(entity instanceof GravityZoneEnity) {
			World world = entity.world;
			BlockPos pos = entity.getPosition();
			world.addParticle(new GravityZoneCenterParticle.GravityZoneCenterParticleData(1.0F, 1.0F, 1.0F, 1.0F), pos.getX(), pos.getY() + 2, pos.getZ(), 0, 0, 0);
		}
	}
}
