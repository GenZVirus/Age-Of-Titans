package com.GenZVirus.AgeOfTitans.Util;

import java.util.List;
import java.util.UUID;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Init.DimensionInit;
import com.GenZVirus.AgeOfTitans.Network.PacketHandler;
import com.GenZVirus.AgeOfTitans.Network.ReadElementPacket;
import com.GenZVirus.AgeOfTitans.Network.SpellPacket;
import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;
import com.GenZVirus.AgeOfTitans.SpellSystem.XMLFileJava;
import com.google.common.collect.Lists;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class ForgeEventBusSubscriber {
	
	// This class deals with forge events
	
	// Creating a player list for use outside the class
	
	public static List<PlayerEntity> players = Lists.newArrayList();
	
	// Creating a unique IDs list for use outside the class
	
	public static List<UUID> uuids = Lists.newArrayList();
	
	@SubscribeEvent
	public static void onPlayerDeath(PlayerEvent.Clone e) {
		players.remove(e.getOriginal());
		uuids.remove(e.getOriginal().getUniqueID());
		players.add(e.getPlayer());
		uuids.add(e.getPlayer().getUniqueID());
		String playerName = e.getPlayer().getName().getFormattedText();
		UUID uuid = e.getPlayer().getUniqueID();
		XMLFileJava.checkFileAndMake(uuid, playerName);
		PacketHandler.INSTANCE.sendTo(new SpellPacket(Integer.parseInt(XMLFileJava.readElement(uuid, "Slot1_Spell_ID")), 
														Integer.parseInt(XMLFileJava.readElement(uuid, "Slot2_Spell_ID")), 
														Integer.parseInt(XMLFileJava.readElement(uuid, "Slot3_Spell_ID")), 
														Integer.parseInt(XMLFileJava.readElement(uuid, "Slot4_Spell_ID")), 
														e.getPlayer().getUniqueID(), false), ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandler.INSTANCE.sendTo(new ReadElementPacket(uuid, "PlayerPoints", Integer.parseInt(XMLFileJava.readElement(uuid, "PlayerPoints"))),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandler.INSTANCE.sendTo(new ReadElementPacket(uuid, "ApplesEaten", Integer.parseInt(XMLFileJava.readElement(uuid, "ApplesEaten"))),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		for(int i = 1; i < Spell.SPELL_LIST.size(); i++) {
			String element = "Spell" + "_Level" + i;
			PacketHandler.INSTANCE.sendTo(new ReadElementPacket(uuid, element, Integer.parseInt(XMLFileJava.readElement(uuid, element))),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}	
	}
	
	// When a player leaves the server all lists clear his data from them
	
	@SubscribeEvent
	public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent e) {
		players.remove(e.getPlayer());
		uuids.remove(e.getPlayer().getUniqueID());
	}
	
	// When a player joins the server all lists add his data to them

	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
		players.add(e.getPlayer());
		uuids.add(e.getPlayer().getUniqueID());
		String playerName = e.getPlayer().getName().getFormattedText();
		UUID uuid = e.getPlayer().getUniqueID();
		XMLFileJava.checkFileAndMake(uuid, playerName);
		System.out.println(uuid.toString());
		PacketHandler.INSTANCE.sendTo(new SpellPacket(Integer.parseInt(XMLFileJava.readElement(uuid, "Slot1_Spell_ID")), 
														Integer.parseInt(XMLFileJava.readElement(uuid, "Slot2_Spell_ID")), 
														Integer.parseInt(XMLFileJava.readElement(uuid, "Slot3_Spell_ID")), 
														Integer.parseInt(XMLFileJava.readElement(uuid, "Slot4_Spell_ID")), 
														e.getPlayer().getUniqueID(), false), ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandler.INSTANCE.sendTo(new ReadElementPacket(uuid, "PlayerPoints", Integer.parseInt(XMLFileJava.readElement(uuid, "PlayerPoints"))),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandler.INSTANCE.sendTo(new ReadElementPacket(uuid, "ApplesEaten", Integer.parseInt(XMLFileJava.readElement(uuid, "ApplesEaten"))),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		for(int i = 1; i < Spell.SPELL_LIST.size(); i++) {
			String element = "Spell" + "_Level" + i;
			System.out.println(Integer.parseInt(XMLFileJava.readElement(uuid, element)));
			PacketHandler.INSTANCE.sendTo(new ReadElementPacket(uuid, element, Integer.parseInt(XMLFileJava.readElement(uuid, element))),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
		
	}
	
	// This event registers the Eden Dimension

	@SubscribeEvent
	public static void registerDimensions(final RegisterDimensionsEvent event) {
		if (DimensionType.byName(AgeOfTitans.EDEN_DIMENSION_TYPE) == null) {
			DimensionManager.registerDimension(AgeOfTitans.EDEN_DIMENSION_TYPE, DimensionInit.EDEN.get(), null, true);
		}
		AgeOfTitans.LOGGER.info("Dimensions Registered!");
	}

	
}
