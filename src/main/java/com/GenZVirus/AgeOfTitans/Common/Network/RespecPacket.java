package com.GenZVirus.AgeOfTitans.Common.Network;

import java.util.UUID;
import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.Common.Events.Server.PlayerEventsHandler;
import com.GenZVirus.AgeOfTitans.SpellSystem.ActiveAbility;
import com.GenZVirus.AgeOfTitans.SpellSystem.PassiveAbility;
import com.GenZVirus.AgeOfTitans.SpellSystem.XMLFileJava;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class RespecPacket {

	public UUID uuid;
	public RespecPacket(UUID uuid) {
		this.uuid = uuid;
	}
	
	public static void encode(RespecPacket pkt, PacketBuffer buf) {
		buf.writeUniqueId(pkt.uuid);
	}
	
	public static RespecPacket decode(PacketBuffer buf) {
		return new RespecPacket(buf.readUniqueId());
	}
	
public static void handle(RespecPacket pkt, Supplier<NetworkEvent.Context> ctx) {
		
		ctx.get().enqueueWork(() ->{
			if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
				UUID uuid = pkt.uuid;
				int level = Integer.parseInt(XMLFileJava.readElement(uuid, "PlayerLevel"));
				PlayerEntity player = PlayerEventsHandler.players.get(PlayerEventsHandler.uuids.indexOf(uuid));
				XMLFileJava.checkFileAndMake(uuid, player.getName().getFormattedText());
				XMLFileJava.editElement(uuid, "PlayerLevel", Integer.toString(level));
				XMLFileJava.editElement(uuid, "ApplesEaten", Integer.toString(level));
				XMLFileJava.editElement(uuid, "PlayerPoints", Integer.toString(level));
				
				XMLFileJava.editElement(uuid, "Slot1_Spell_ID", "0");
				XMLFileJava.editElement(uuid, "Slot2_Spell_ID", "0");
				XMLFileJava.editElement(uuid, "Slot3_Spell_ID", "0");
				XMLFileJava.editElement(uuid, "Slot4_Spell_ID", "0");
				
				PacketHandlerCommon.INSTANCE.sendTo(new SpellPacket(Integer.parseInt(XMLFileJava.readElement(uuid, "Slot1_Spell_ID")), 
																	Integer.parseInt(XMLFileJava.readElement(uuid, "Slot2_Spell_ID")), 
																	Integer.parseInt(XMLFileJava.readElement(uuid, "Slot3_Spell_ID")), 
																	Integer.parseInt(XMLFileJava.readElement(uuid, "Slot4_Spell_ID")), 
																	uuid, false), ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
				PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, "PlayerPoints", Integer.parseInt(XMLFileJava.readElement(uuid, "PlayerPoints"))),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
				PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, "ApplesEaten", Integer.parseInt(XMLFileJava.readElement(uuid, "ApplesEaten"))),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
				PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, "PlayerLevel", Integer.parseInt(XMLFileJava.readElement(uuid, "PlayerLevel"))),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);

				for(int i = 1; i < ActiveAbility.getList().size(); i++) {
					String element = "Spell" + "_Level" + i;
					XMLFileJava.editElement(uuid, element, "0");
					PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, element, Integer.parseInt(XMLFileJava.readElement(uuid, element))),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
				}
				
				for(int i = 1; i < PassiveAbility.getList().size(); i++) {
					String element = "Passive" + "_Level" + i;
					XMLFileJava.editElement(uuid, element, "0");
					PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(uuid, element, Integer.parseInt(XMLFileJava.readElement(uuid, element))),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
				}
			}
		});
		
		ctx.get().setPacketHandled(true);
	}

	
}
