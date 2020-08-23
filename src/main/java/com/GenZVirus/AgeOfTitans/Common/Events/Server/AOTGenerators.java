package com.GenZVirus.AgeOfTitans.Common.Events.Server;

import java.util.Random;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandlerCommon;
import com.GenZVirus.AgeOfTitans.Common.Network.SendPlayerRagePointsPacket;
import com.GenZVirus.AgeOfTitans.SpellSystem.PlayerStats;
import com.GenZVirus.AgeOfTitans.Util.ForgeEventBusSubscriber;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class AOTGenerators {

	@SubscribeEvent
	public static void AOTOutOfCombat(PlayerEvent event) {
		if (event.getPlayer() == null)
			return;
		if (event.getPlayer().world.isRemote)
			return;
		PlayerEntity player = event.getPlayer();
		Random rand = new Random();
		if (player.getCombatTracker().getCombatDuration() == 0 && rand.nextInt(3) == 1) {
			if (!ForgeEventBusSubscriber.players.contains(player))
				return;
			int index = ForgeEventBusSubscriber.players.indexOf(player);
			int rageAmount = ForgeEventBusSubscriber.rage.get(index);
			if (rageAmount - 1 < 0)
				return;
			else
				rageAmount -= 1;
			ForgeEventBusSubscriber.rage.set(index, rageAmount);
			PacketHandlerCommon.INSTANCE.sendTo(new SendPlayerRagePointsPacket(rageAmount), ((ServerPlayerEntity) player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
	}
	
	@SubscribeEvent
	public static void AOTGenerateRageWhileSprinting(PlayerEvent event) {
		if (event.getPlayer() == null)
			return;
		if (event.getPlayer().world.isRemote)
			return;
		if (event.getPlayer().isSprinting()) {
			PlayerEntity player = event.getPlayer();
			if (!ForgeEventBusSubscriber.players.contains(player))
				return;
			int index = ForgeEventBusSubscriber.players.indexOf(player);
			int rageAmount = ForgeEventBusSubscriber.rage.get(index);
			if (rageAmount + 1 > PlayerStats.MAX_RAGE_POINTS && rageAmount <= PlayerStats.MAX_RAGE_POINTS)
				rageAmount = PlayerStats.MAX_RAGE_POINTS;
			else
				rageAmount += 1;
			ForgeEventBusSubscriber.rage.set(index, rageAmount);
			PacketHandlerCommon.INSTANCE.sendTo(new SendPlayerRagePointsPacket(rageAmount), ((ServerPlayerEntity) player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
	}

	@SubscribeEvent
	public static void AOTGenerateRageOnAttacking(LivingHurtEvent event) {
		if (!(event.getSource().getTrueSource() instanceof PlayerEntity))
			return;
		PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
		if (!ForgeEventBusSubscriber.players.contains(player))
			return;
		int index = ForgeEventBusSubscriber.players.indexOf(player);
		int rageAmount = ForgeEventBusSubscriber.rage.get(index);
		if (rageAmount + 50 > PlayerStats.MAX_RAGE_POINTS && rageAmount <= PlayerStats.MAX_RAGE_POINTS)
			rageAmount = PlayerStats.MAX_RAGE_POINTS;
		else
			rageAmount += 50;
		ForgeEventBusSubscriber.rage.set(index, rageAmount);
		PacketHandlerCommon.INSTANCE.sendTo(new SendPlayerRagePointsPacket(rageAmount), ((ServerPlayerEntity) player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
	}

	@SubscribeEvent
	public static void AOTGenerateRageWhenAttacked(LivingHurtEvent event) {
		if (!(event.getEntity() instanceof PlayerEntity))
			return;
		PlayerEntity player = (PlayerEntity) event.getEntity();
		if (!ForgeEventBusSubscriber.players.contains(player))
			return;
		int index = ForgeEventBusSubscriber.players.indexOf(player);
		int rageAmount = ForgeEventBusSubscriber.rage.get(index);
		if (rageAmount + 50 > PlayerStats.MAX_RAGE_POINTS && rageAmount <= PlayerStats.MAX_RAGE_POINTS)
			rageAmount = PlayerStats.MAX_RAGE_POINTS;
		else
			rageAmount += 50;
		ForgeEventBusSubscriber.rage.set(index, rageAmount);
		PacketHandlerCommon.INSTANCE.sendTo(new SendPlayerRagePointsPacket(rageAmount), ((ServerPlayerEntity) player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
	}
	
}
