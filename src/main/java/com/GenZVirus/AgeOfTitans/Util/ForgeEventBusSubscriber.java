package com.GenZVirus.AgeOfTitans.Util;

import java.util.List;
import java.util.UUID;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Init.DimensionInit;
import com.GenZVirus.AgeOfTitans.Network.PacketHandler;
import com.GenZVirus.AgeOfTitans.Network.SpellPacket;
import com.GenZVirus.AgeOfTitans.SpellSystem.FileSystem;
import com.google.common.collect.Lists;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class ForgeEventBusSubscriber {
	
	public static List<PlayerEntity> players = Lists.newArrayList();
	public static List<UUID> uuids = Lists.newArrayList();
	
	@SubscribeEvent
	public static void onAttachPlayer(AttachCapabilitiesEvent<Entity> e) {

	}
	
	@SubscribeEvent
	public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent e) {
		players.remove(e.getPlayer());
		uuids.remove(e.getPlayer().getUniqueID());
	}

	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
		players.add(e.getPlayer());
		uuids.add(e.getPlayer().getUniqueID());
		List<Integer> list = FileSystem.readOrWrite(e.getPlayer().getUniqueID().toString(), 0, 0, 0, 0);
		for(PlayerEntity player : players) {
			if(player.getUniqueID().toString().contentEquals(e.getPlayer().getUniqueID().toString())) {		
				PacketHandler.INSTANCE.sendTo(new SpellPacket(list.get(0), list.get(1), list.get(2), list.get(3), player.getUniqueID()), ((ServerPlayerEntity) e.getPlayer()).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
			}
		}
	}
	
	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone e) {
		
	}

	@SubscribeEvent
	public static void registerDimensions(final RegisterDimensionsEvent event) {
		if (DimensionType.byName(AgeOfTitans.EDEN_DIMENSION_TYPE) == null) {
			DimensionManager.registerDimension(AgeOfTitans.EDEN_DIMENSION_TYPE, DimensionInit.EDEN.get(), null, true);
		}
		AgeOfTitans.LOGGER.info("Dimensions Registered!");
	}

	
}
