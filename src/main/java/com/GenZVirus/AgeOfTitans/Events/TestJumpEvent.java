package com.GenZVirus.AgeOfTitans.Events;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;

import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class TestJumpEvent {

	@SubscribeEvent
	public static void testJumpEvent(LivingJumpEvent event) {
		if(!event.getEntityLiving().getEntityWorld().isRemote) return;
		AgeOfTitans.LOGGER.info("testJumpEvent fired");
	}
	
}
