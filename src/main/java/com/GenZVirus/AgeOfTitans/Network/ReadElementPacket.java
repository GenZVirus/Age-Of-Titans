package com.GenZVirus.AgeOfTitans.Network;

import java.util.UUID;
import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;
import com.GenZVirus.AgeOfTitans.SpellSystem.XMLFileJava;
import com.GenZVirus.AgeOfTitans.Util.ForgeEventBusSubscriber;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class ReadElementPacket {

	public UUID uuid;
	public String element;
	public int value;
	
	public ReadElementPacket(UUID uuid, String element, int value) {
		this.uuid = uuid;
		this.element = element;
		this.value = value;
	}
	
	public static void encode(ReadElementPacket pkt, PacketBuffer buf) {
		buf.writeUniqueId(pkt.uuid);
		buf.writeString(pkt.element);
		buf.writeInt(pkt.value);
	}
	
	public static ReadElementPacket decode(PacketBuffer buf) {
		return new ReadElementPacket(buf.readUniqueId(), buf.readString(32767), buf.readInt());
	}
	
	public static void handle(ReadElementPacket pkt, Supplier<NetworkEvent.Context> ctx) {
		
		ctx.get().enqueueWork(() ->{
			if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
				for (PlayerEntity player : ForgeEventBusSubscriber.players) {
					if (player.getUniqueID().toString().contentEquals(pkt.uuid.toString())) {
						String playerName = player.getName().getFormattedText();
						UUID uuid = player.getUniqueID();
						XMLFileJava.checkFileAndMake(uuid, playerName);
						PacketHandler.INSTANCE.sendTo(new ReadElementPacket(pkt.uuid, pkt.element, Integer.parseInt(XMLFileJava.readElement(pkt.uuid, pkt.element))), ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
					}
				}
			}
			
			if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
				if(pkt.element.contains("Spell_Level")) {
					int ID = Integer.parseInt(pkt.element.replaceFirst("Spell_Level", ""));
					Spell.SPELL_LIST.get(ID).level = pkt.value;
				}
				
				if(pkt.element.contains("PlayerPoints")) {
					Spell.points = pkt.value;
				}
				
				if(pkt.element.contains("ApplesEaten"))
				{
					Spell.applesEaten = pkt.value;
				}
			}
		});
		
		ctx.get().setPacketHandled(true);
	}
	
}
