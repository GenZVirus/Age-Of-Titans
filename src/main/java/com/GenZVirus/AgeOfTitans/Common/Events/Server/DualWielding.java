package com.GenZVirus.AgeOfTitans.Common.Events.Server;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class DualWielding {
	
	@SubscribeEvent
	public static void resetPlayerAttackCD(PlayerTickEvent event) {
		if(event.phase == Phase.START) return;
		if(event.player == null) return;
		PlayerEntity player = event.player;
		if(!(player.getHeldItemOffhand().getItem() instanceof SwordItem)) {
			return;
		}
		if(player.getHeldItemMainhand().getItem() instanceof SwordItem) {
			player.ticksSinceLastSwing = 20000;
		}
	}
	
}
