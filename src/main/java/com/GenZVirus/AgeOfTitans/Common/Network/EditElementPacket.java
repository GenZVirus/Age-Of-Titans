package com.GenZVirus.AgeOfTitans.Common.Network;

import java.util.UUID;
import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.Common.Events.Server.PlayerEventsHandler;
import com.GenZVirus.AgeOfTitans.SpellSystem.XMLFileJava;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class EditElementPacket {

	public UUID uuid;
	public String element;
	public int value;
	
	public EditElementPacket(UUID uuid, String element, int value) {
		this.uuid = uuid;
		this.element = element;
		this.value = value;
	}
	
	public static void encode(EditElementPacket pkt, PacketBuffer buf) {
		buf.writeUniqueId(pkt.uuid);
		buf.writeString(pkt.element);
		buf.writeInt(pkt.value);
	}
	
	public static EditElementPacket decode(PacketBuffer buf) {
		return new EditElementPacket(buf.readUniqueId(), buf.readString(32767), buf.readInt());
	}
	
	public static void handle(EditElementPacket pkt, Supplier<NetworkEvent.Context> ctx) {
		
		ctx.get().enqueueWork(() ->{
			if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
				for (PlayerEntity player : PlayerEventsHandler.players) {
					if (player.getUniqueID().toString().contentEquals(pkt.uuid.toString())) {
						String playerName = player.getName().getFormattedText();
						UUID uuid = player.getUniqueID();
						XMLFileJava.checkFileAndMake(uuid, playerName);
						XMLFileJava.editElement(player.getUniqueID(), pkt.element, Integer.toString((Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), pkt.element)) + pkt.value)));
						PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(pkt.uuid, pkt.element, Integer.parseInt(XMLFileJava.readElement(pkt.uuid, pkt.element))), ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
					}
				}
			}
		});
		
		ctx.get().setPacketHandled(true);
	}
	
}
