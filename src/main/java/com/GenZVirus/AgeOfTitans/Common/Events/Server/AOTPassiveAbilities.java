package com.GenZVirus.AgeOfTitans.Common.Events.Server;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.SpellSystem.PassiveAbility;
import com.GenZVirus.AgeOfTitans.Util.ForgeEventBusSubscriber;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class AOTPassiveAbilities {

	@SubscribeEvent
	public static void AOTForceField(PlayerEvent event) {
		PlayerEntity player = event.getPlayer();
		if(player == null) return;
		if(player.world.isRemote) return;
		if(!ForgeEventBusSubscriber.players.contains(player)) return;
		if(ForgeEventBusSubscriber.inCombat.get(ForgeEventBusSubscriber.players.indexOf(player)) > 0) return;
		PassiveAbility.getList().get(1).effect(player.world, player);
	}
	
}
