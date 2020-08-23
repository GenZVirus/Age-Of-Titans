package com.GenZVirus.AgeOfTitans.Common.Events.Server;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Util.ForgeEventBusSubscriber;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class AOTInCombat {

	@SubscribeEvent
	public static void reduceCombatTimer(ServerTickEvent event) {
		if(event.phase == Phase.START) return;
		for(int index = 0; index < ForgeEventBusSubscriber.inCombat.size(); index++) {
			if(ForgeEventBusSubscriber.inCombat.get(index) > 0) {
				ForgeEventBusSubscriber.inCombat.set(index, ForgeEventBusSubscriber.inCombat.get(index) - 1);
			}
		}		
		
	}
	
	@SubscribeEvent
	public static void inCombat(LivingHurtEvent event) {
		if(event.getEntityLiving() == null) return;
		if(event.getEntityLiving().world.isRemote) return;
		if(!(event.getEntityLiving() instanceof PlayerEntity)) return;
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		if(!ForgeEventBusSubscriber.players.contains(player)) return;
		ForgeEventBusSubscriber.inCombat.set(ForgeEventBusSubscriber.players.indexOf(player), 400);
		
	}
	
}
