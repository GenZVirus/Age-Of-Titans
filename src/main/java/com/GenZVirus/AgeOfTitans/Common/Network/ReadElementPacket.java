package com.GenZVirus.AgeOfTitans.Common.Network;

import java.util.UUID;
import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.Client.GUI.ReaperShop.ReaperShopScreen;
import com.GenZVirus.AgeOfTitans.Common.Events.Server.PlayerEventsHandler;
import com.GenZVirus.AgeOfTitans.SpellSystem.ActiveAbility;
import com.GenZVirus.AgeOfTitans.SpellSystem.PassiveAbility;
import com.GenZVirus.AgeOfTitans.SpellSystem.PlayerStats;
import com.GenZVirus.AgeOfTitans.SpellSystem.XMLFileJava;

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
				for (PlayerEntity player : PlayerEventsHandler.players) {
					if (player.getUniqueID().toString().contentEquals(pkt.uuid.toString())) {
						String playerName = player.getName().getFormattedText();
						UUID uuid = player.getUniqueID();
						XMLFileJava.checkFileAndMake(uuid, playerName);
						PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(pkt.uuid, pkt.element, Integer.parseInt(XMLFileJava.readElement(pkt.uuid, pkt.element))), ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
					}
				}
			}
			
			if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
				if(pkt.element.contains("Spell_Level")) {
					int ID = Integer.parseInt(pkt.element.replaceFirst("Spell_Level", ""));
					ActiveAbility.getList().get(ID).setLevel(pkt.value);
				}
				
				if(pkt.element.contains("Passive_Level")) {
					int ID = Integer.parseInt(pkt.element.replaceFirst("Passive_Level", ""));
					PassiveAbility.getList().get(ID).setLevel(pkt.value);
				}
				
				if(pkt.element.contains("PlayerPoints")) {
					PlayerStats.POINTS = pkt.value;
				}
				
				if(pkt.element.contains("ApplesEaten"))
				{
					PlayerStats.APPLES_EATEN = pkt.value;
				}
				
				if(pkt.element.contains("PlayerLevel"))
				{
					PlayerStats.PLAYER_LEVEL = pkt.value;
				}
				
				if(pkt.element.contains("Balance")) {
					ReaperShopScreen.SCREEN.balance = pkt.value;
				}
			}
		});
		
		ctx.get().setPacketHandled(true);
	}
	
}
