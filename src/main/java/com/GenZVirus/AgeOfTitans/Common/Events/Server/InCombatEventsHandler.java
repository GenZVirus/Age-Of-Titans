package com.GenZVirus.AgeOfTitans.Common.Events.Server;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class InCombatEventsHandler {

	@SubscribeEvent
	public static void reduceCombatTimer(ServerTickEvent event) {
		if(event.phase == Phase.START) return;
		for(int index = 0; index < PlayerEventsHandler.inCombat.size(); index++) {
			if(PlayerEventsHandler.inCombat.get(index) > 0) {
				PlayerEventsHandler.inCombat.set(index, PlayerEventsHandler.inCombat.get(index) - 1);
			}
		}		
		
	}
	
	@SubscribeEvent
	public static void inCombat(LivingHurtEvent event) {
		if(event.getEntityLiving() == null) return;
		if(event.getEntityLiving().world.isRemote) return;
		if(!(event.getEntityLiving() instanceof PlayerEntity)) return;
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		if(!PlayerEventsHandler.players.contains(player)) return;
		PlayerEventsHandler.inCombat.set(PlayerEventsHandler.players.indexOf(player), 400);
		
	}
	
}
