package com.GenZVirus.AgeOfTitans.Common.Network;

import java.util.UUID;
import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.Client.GUI.HUD.ModHUD;
import com.GenZVirus.AgeOfTitans.Client.GUI.SpellTree.ModScreen;
import com.GenZVirus.AgeOfTitans.Common.Events.KeyPressedEvent;
import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;
import com.GenZVirus.AgeOfTitans.SpellSystem.XMLFileJava;
import com.GenZVirus.AgeOfTitans.Util.ForgeEventBusSubscriber;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class SpellPacket {

	// This class is the message that gets send between the clients and the server
	
	// These are the slot variables that contain the IDs of the Spells that occupy them
	
	public int slot1, slot2, slot3, slot4;
	
	// This boolean is used to decide whether the packet is destined to read the file or write something in it
	
	public boolean read;
	
	// This is the variable which contain a unique ID and it's use to differentiate the players
	
	private UUID uuid;
	
	// Initialization

	public SpellPacket(int slot1ID, int slot2ID, int slot3ID, int slot4ID, UUID uuid, boolean read) {
		this.slot1 = slot1ID;
		this.slot2 = slot2ID;
		this.slot3 = slot3ID;
		this.slot4 = slot4ID;
		this.read = read;
		this.uuid = uuid;
	}
	
	// Encoding the packet

	public static void encode(SpellPacket pkt, PacketBuffer buf) {
		buf.writeInt(pkt.slot1);
		buf.writeInt(pkt.slot2);
		buf.writeInt(pkt.slot3);
		buf.writeInt(pkt.slot4);
		buf.writeUniqueId(pkt.uuid);
		buf.writeBoolean(pkt.read);
	}

	// Decoding the packet
	
	public static SpellPacket decode(PacketBuffer buf) {
		return new SpellPacket(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt(), buf.readUniqueId(),
				buf.readBoolean());
	}
	
	// Handling both the server side and the client side

	public static void handle(SpellPacket pkt, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			
	// Server side handle
			
			if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
				
	// If the packet is destined to write something in the file
				
				if (!pkt.read) {
					XMLFileJava.editElement(pkt.uuid, "Slot1_Spell_ID", Integer.toString(pkt.slot1));
					XMLFileJava.editElement(pkt.uuid, "Slot2_Spell_ID", Integer.toString(pkt.slot2));
					XMLFileJava.editElement(pkt.uuid, "Slot3_Spell_ID", Integer.toString(pkt.slot3));
					XMLFileJava.editElement(pkt.uuid, "Slot4_Spell_ID", Integer.toString(pkt.slot4));
					for (PlayerEntity player : ForgeEventBusSubscriber.players) {
						if (player.getUniqueID().toString().contentEquals(pkt.uuid.toString())) {
							String playerName = player.getName().getFormattedText();
							UUID uuid = player.getUniqueID();
							XMLFileJava.checkFileAndMake(uuid, playerName);
							PacketHandler.INSTANCE.sendTo(new SpellPacket(Integer.parseInt(XMLFileJava.readElement(uuid, "Slot1_Spell_ID")), 
																			Integer.parseInt(XMLFileJava.readElement(uuid, "Slot2_Spell_ID")), 
																			Integer.parseInt(XMLFileJava.readElement(uuid, "Slot3_Spell_ID")), 
																			Integer.parseInt(XMLFileJava.readElement(uuid, "Slot4_Spell_ID")), 
																			player.getUniqueID(), false), ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
						}
					}
	
	// If the packet is destined to read something from the file	
				
				} else {
					
	// In case the player doesn't exist in the file which shouldn't happen because it is checked in the Login Event Handler
					
					for (PlayerEntity player : ForgeEventBusSubscriber.players) {
						if (player.getUniqueID().toString().contentEquals(pkt.uuid.toString())) {
							String playerName = player.getName().getFormattedText();
							UUID uuid = player.getUniqueID();
							XMLFileJava.checkFileAndMake(uuid, playerName);
							PacketHandler.INSTANCE.sendTo(new SpellPacket(Integer.parseInt(XMLFileJava.readElement(uuid, "Slot1_Spell_ID")), 
																			Integer.parseInt(XMLFileJava.readElement(uuid, "Slot2_Spell_ID")), 
																			Integer.parseInt(XMLFileJava.readElement(uuid, "Slot3_Spell_ID")), 
																			Integer.parseInt(XMLFileJava.readElement(uuid, "Slot4_Spell_ID")), 
																			player.getUniqueID(), false), ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
						}
					}
				}
				
	// Client side handle
				
			} else if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
				
	// Sending data to the GUI
				
				ModHUD.SPELL1 = Spell.SPELL_LIST.get(pkt.slot1);
				ModHUD.SPELL2 = Spell.SPELL_LIST.get(pkt.slot2);
				ModHUD.SPELL3 = Spell.SPELL_LIST.get(pkt.slot3);
				ModHUD.SPELL4 = Spell.SPELL_LIST.get(pkt.slot4);
				
				if(KeyPressedEvent.wasPRESSED) {
					ModScreen.SCREEN.slot1.spell = Spell.SPELL_LIST.get(pkt.slot1);
					ModScreen.SCREEN.slot2.spell = Spell.SPELL_LIST.get(pkt.slot2);
					ModScreen.SCREEN.slot3.spell = Spell.SPELL_LIST.get(pkt.slot3);
					ModScreen.SCREEN.slot4.spell = Spell.SPELL_LIST.get(pkt.slot4);
				}
			}
		});
		
	// Marking the packet as handled
		
		ctx.get().setPacketHandled(true);

	}
}
