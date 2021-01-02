package com.GenZVirus.AgeOfTitans.Common.Events.Server;

import java.util.List;
import java.util.UUID;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Config.AOTConfig;
import com.GenZVirus.AgeOfTitans.Common.Init.DimensionInit;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandlerCommon;
import com.GenZVirus.AgeOfTitans.Common.Network.ReadElementPacket;
import com.GenZVirus.AgeOfTitans.Common.Network.SendPlayerPassiveDetailsPacket;
import com.GenZVirus.AgeOfTitans.Common.Network.SendPlayerSpellDetailsPacket;
import com.GenZVirus.AgeOfTitans.Common.Network.SpellPacket;
import com.GenZVirus.AgeOfTitans.SpellSystem.ActiveAbility;
import com.GenZVirus.AgeOfTitans.SpellSystem.PassiveAbility;
import com.GenZVirus.AgeOfTitans.SpellSystem.XMLFileJava;
import com.google.common.collect.Lists;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.util.Hand;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class PlayerEventsHandler {
	
	// This class deals with forge events
	
	public static int AvarageLevel = 0;
	
	// Creating a player list for use outside the class
	
	public static List<PlayerEntity> players = Lists.newArrayList();
	
	// Creating a unique IDs list for use outside the class
	
	public static List<UUID> uuids = Lists.newArrayList();
	
	public static List<Integer> rage = Lists.newArrayList();
	
	public static List<Integer> inCombat = Lists.newArrayList();

	public static List<Hand> hands = Lists.newArrayList();
	
	@SubscribeEvent
	public static void onPlayerDeath(PlayerEvent.Clone e) {
		removePlayer(e.getOriginal());
		addPlayer(e.getPlayer());
		String playerName = e.getPlayer().getName().getFormattedText();
		UUID uuid = e.getPlayer().getUniqueID();
		XMLFileJava.checkFileAndMake(uuid, playerName);
		PacketHandlerCommon.INSTANCE.sendTo(new SpellPacket(Integer.parseInt(XMLFileJava.readElement(uuid, "Slot1_Spell_ID")), 
														Integer.parseInt(XMLFileJava.readElement(uuid, "Slot2_Spell_ID")), 
														Integer.parseInt(XMLFileJava.readElement(uuid, "Slot3_Spell_ID")), 
														Integer.parseInt(XMLFileJava.readElement(uuid, "Slot4_Spell_ID")), 
														e.getPlayer().getUniqueID(), false), ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, "PlayerPoints", Integer.parseInt(XMLFileJava.readElement(uuid, "PlayerPoints"))),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, "ApplesEaten", Integer.parseInt(XMLFileJava.readElement(uuid, "ApplesEaten"))),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, "Balance", Integer.parseInt(XMLFileJava.readElement(uuid, "Balance"))),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		for(int i = 1; i < ActiveAbility.getList().size(); i++) {
			String element = "Spell" + "_Level" + i;
			PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, element, Integer.parseInt(XMLFileJava.readElement(uuid, element))),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
		
		for(int i = 1; i < PassiveAbility.getList().size(); i++) {
			String element = "Passive" + "_Level" + i;
			PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, element, Integer.parseInt(XMLFileJava.readElement(uuid, element))),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
		AgeOfTitans.LOGGER.info("Packets resent to " + playerName);
		calculateAverageLevel();
	}
	
	// When a player leaves the server all lists clear his data from them
	
	@SubscribeEvent
	public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent e) {
		removePlayer(e.getPlayer());
		calculateAverageLevel();
	}
	
	// When a player joins the server all lists add his data to them

	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {		
		addPlayer(e.getPlayer());
		MinecraftServer mcSRV = e.getPlayer().getServer();
		if(!(mcSRV instanceof DedicatedServer)) {
			String folderName = mcSRV.getFolderName();
			XMLFileJava.default_xmlFilePath = "./saves/" + folderName + "/ageoftitans/playerdata/";
		}
		
		String playerName = e.getPlayer().getName().getFormattedText();
		UUID uuid = e.getPlayer().getUniqueID();
		XMLFileJava.checkFileAndMake(uuid, playerName);
		PacketHandlerCommon.INSTANCE.sendTo(new SpellPacket(Integer.parseInt(XMLFileJava.readElement(uuid, "Slot1_Spell_ID")), 
														Integer.parseInt(XMLFileJava.readElement(uuid, "Slot2_Spell_ID")), 
														Integer.parseInt(XMLFileJava.readElement(uuid, "Slot3_Spell_ID")), 
														Integer.parseInt(XMLFileJava.readElement(uuid, "Slot4_Spell_ID")), 
														e.getPlayer().getUniqueID(), false), ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, "PlayerPoints", Integer.parseInt(XMLFileJava.readElement(uuid, "PlayerPoints"))),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, "ApplesEaten", Integer.parseInt(XMLFileJava.readElement(uuid, "ApplesEaten"))),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, "PlayerLevel", Integer.parseInt(XMLFileJava.readElement(uuid, "PlayerLevel"))),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, "Balance", Integer.parseInt(XMLFileJava.readElement(uuid, "Balance"))),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		for(int i = 1; i < ActiveAbility.getList().size(); i++) {
			String element = "Spell" + "_Level" + i;
			PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, element, Integer.parseInt(XMLFileJava.readElement(uuid, element))),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
		for(int i = 1; i < PassiveAbility.getList().size(); i++) {
			String element = "Passive" + "_Level" + i;
			PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, element, Integer.parseInt(XMLFileJava.readElement(uuid, element))),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}		
		PacketHandlerCommon.INSTANCE.sendTo(new SendPlayerSpellDetailsPacket(1, AOTConfig.COMMON.sword_slash_cooldown.get(), AOTConfig.COMMON.sword_slash_cost.get(), AOTConfig.COMMON.sword_slash_damage_ratio.get(), AOTConfig.COMMON.sword_slash_base_damage.get()),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new SendPlayerSpellDetailsPacket(2, AOTConfig.COMMON.shield_bash_cooldown.get(), AOTConfig.COMMON.shield_bash_cost.get(), AOTConfig.COMMON.shield_bash_damage_ratio.get(), AOTConfig.COMMON.shield_bash_base_damage.get()),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new SendPlayerSpellDetailsPacket(3, AOTConfig.COMMON.berserker_cooldown.get(), AOTConfig.COMMON.berserker_cost.get(), AOTConfig.COMMON.berserker_duration_ratio.get(), AOTConfig.COMMON.berserker_punch_damage.get()),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new SendPlayerSpellDetailsPacket(4, AOTConfig.COMMON.chain_cooldown.get(), AOTConfig.COMMON.chain_cost.get(), AOTConfig.COMMON.chain_damage_ratio.get(), AOTConfig.COMMON.chain_base_damage.get()),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new SendPlayerSpellDetailsPacket(5, AOTConfig.COMMON.gravity_bomb_cooldown.get(), AOTConfig.COMMON.gravity_bomb_cost.get(), AOTConfig.COMMON.gravity_bomb_ratio.get(), AOTConfig.COMMON.gravity_bomb_bonus_damage.get()),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new SendPlayerSpellDetailsPacket(6, AOTConfig.COMMON.revitalise_cooldown.get(), AOTConfig.COMMON.revitalise_cost.get(), AOTConfig.COMMON.revitalise_healing_ratio.get(), AOTConfig.COMMON.revitalise_base_amount.get()),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		
		PacketHandlerCommon.INSTANCE.sendTo(new SendPlayerPassiveDetailsPacket(1, 0, 0, AOTConfig.COMMON.force_field_ratio.get(), AOTConfig.COMMON.force_field_base_amount.get()),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new SendPlayerPassiveDetailsPacket(2, 0, 0, AOTConfig.COMMON.pog_ratio.get(), AOTConfig.COMMON.pog_base_amount.get()),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		PacketHandlerCommon.INSTANCE.sendTo(new SendPlayerPassiveDetailsPacket(3, 0, 0, AOTConfig.COMMON.ros_ratio.get(), AOTConfig.COMMON.ros_base_amount.get()),  ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		AgeOfTitans.LOGGER.info("Packets sent to " + playerName);
		calculateAverageLevel();
	}
	
	// This event registers the Eden Dimension

	@SubscribeEvent
	public static void registerDimensions(final RegisterDimensionsEvent event) {
		if (DimensionType.byName(AgeOfTitans.EDEN_DIMENSION_TYPE) == null) {
			DimensionManager.registerDimension(AgeOfTitans.EDEN_DIMENSION_TYPE, DimensionInit.EDEN.get(), null, true);
		}
		AgeOfTitans.LOGGER.info("Dimensions Registered!");
	}
	
	private static void removePlayer(PlayerEntity player) {
		rage.remove(players.indexOf(player));
		hands.remove(players.indexOf(player));
		inCombat.remove(players.indexOf(player));
		players.remove(player);
		uuids.remove(player.getUniqueID());
	}
	
	private static void addPlayer(PlayerEntity player) {
		rage.add(0);
		inCombat.add(0);
		players.add(player);
		uuids.add(player.getUniqueID());
		hands.add(Hand.MAIN_HAND);
	}

	private static void calculateAverageLevel() {
		int sum = 0;
		for(UUID uuid : uuids) {
			sum += Integer.parseInt(XMLFileJava.readElement(uuid, "PlayerLevel"));
		}
		
		AvarageLevel = sum / (uuids.size() == 0 ? 1 : uuids.size());
		
	}
	
}
