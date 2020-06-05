package com.GenZVirus.AgeOfTitans.Network;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.Client.GUI.Character.ModHUD;
import com.GenZVirus.AgeOfTitans.Client.GUI.Character.ModScreen;
import com.GenZVirus.AgeOfTitans.Events.KeyPressedEvent;
import com.GenZVirus.AgeOfTitans.SpellSystem.FileSystem;
import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;
import com.GenZVirus.AgeOfTitans.Util.ForgeEventBusSubscriber;

import net.minecraft.entity.player.PlayerEntity;
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
					FileSystem.editFile(pkt.uuid.toString(), pkt.slot1, pkt.slot2, pkt.slot3, pkt.slot4);
					List<Integer> list = FileSystem.readFile(pkt.uuid.toString());
					for (PlayerEntity player : ForgeEventBusSubscriber.players) {
						if (player.getUniqueID().toString().contentEquals(pkt.uuid.toString())) {
							PacketHandler.INSTANCE.sendTo(
									new SpellPacket(list.get(0), list.get(1), list.get(2), list.get(3),	player.getUniqueID(), false), ctx.get().getSender().connection.getNetworkManager(),	NetworkDirection.PLAY_TO_CLIENT);
						}
					}
	
	// If the packet is destined to read something from the file	
				
				} else {
					
	// In case the player doesn't exist in the file which shouldn't happen because it is checked in the Login Event Handler
					
					List<Integer> list = FileSystem.readOrWrite(pkt.uuid.toString(), 0, 0, 0, 0);
					for (PlayerEntity player : ForgeEventBusSubscriber.players) {
						if (player.getUniqueID().toString().contentEquals(pkt.uuid.toString())) {
							PacketHandler.INSTANCE.sendTo(
									new SpellPacket(list.get(0), list.get(1), list.get(2), list.get(3),	player.getUniqueID(), false), ctx.get().getSender().connection.getNetworkManager(),	NetworkDirection.PLAY_TO_CLIENT);
						}
					}
				}
				
	// Client side handle
				
			} else if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
				
	// Sending data to the GUI
				
				ModHUD.SPELL1_TEXTURE = Spell.SPELL_LIST.get(pkt.slot1).getIcon();
				ModHUD.SPELL2_TEXTURE = Spell.SPELL_LIST.get(pkt.slot2).getIcon();
				ModHUD.SPELL3_TEXTURE = Spell.SPELL_LIST.get(pkt.slot3).getIcon();
				ModHUD.SPELL4_TEXTURE = Spell.SPELL_LIST.get(pkt.slot4).getIcon();
				
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
