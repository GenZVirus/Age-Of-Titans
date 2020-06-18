package com.GenZVirus.AgeOfTitans.Network;

import java.util.UUID;
import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class SyncPlayerMotionPacket {

	public double x, y, z;
	public UUID uuid;
	
	public SyncPlayerMotionPacket(UUID uuid, double x, double y, double z) {
		this.uuid = uuid;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static void encode(SyncPlayerMotionPacket pkt, PacketBuffer buf) {
		buf.writeUniqueId(pkt.uuid);
		buf.writeDouble(pkt.x);
		buf.writeDouble(pkt.y);
		buf.writeDouble(pkt.z);
	}
	
	public static SyncPlayerMotionPacket decode(PacketBuffer buf) {
		return new SyncPlayerMotionPacket(buf.readUniqueId(), buf.readDouble(), buf.readDouble(), buf.readDouble());
	}
	
	public static void handle(SyncPlayerMotionPacket pkt, Supplier<NetworkEvent.Context> ctx) {
		
		ctx.get().enqueueWork(() ->{
			if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
				@SuppressWarnings("resource")
				PlayerEntity player = Minecraft.getInstance().world.getPlayerByUuid(pkt.uuid);
				player.setMotion(pkt.x, pkt.y, pkt.z);
			}
		});
		
		ctx.get().setPacketHandled(true);
	}
	
}
