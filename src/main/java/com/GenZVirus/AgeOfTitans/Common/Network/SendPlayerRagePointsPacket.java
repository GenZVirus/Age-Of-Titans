package com.GenZVirus.AgeOfTitans.Common.Network;

import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class SendPlayerRagePointsPacket {

	public int ragePoints;
	
	public SendPlayerRagePointsPacket(int ragePoints) {
		this.ragePoints = ragePoints;
	}
	
	public static void encode(SendPlayerRagePointsPacket pkt, PacketBuffer buf) {
		buf.writeInt(pkt.ragePoints);
	}
	
	public static SendPlayerRagePointsPacket decode(PacketBuffer buf) {
		return new SendPlayerRagePointsPacket(buf.readInt());
	}
	
	public static void handle(SendPlayerRagePointsPacket pkt, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() ->{
			if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
				Spell.RAGE_POINTS = pkt.ragePoints;
			}
		});
		ctx.get().setPacketHandled(true);
	}
	
}
