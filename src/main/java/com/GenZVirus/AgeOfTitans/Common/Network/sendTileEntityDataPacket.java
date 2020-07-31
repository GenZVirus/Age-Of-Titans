package com.GenZVirus.AgeOfTitans.Common.Network;

import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.Client.Container.ContainerScreenBasic;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class sendTileEntityDataPacket {
	
	public int posX, posY, posZ, ID;
	
	public sendTileEntityDataPacket(int posX, int posY, int posZ, int id) {
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.ID = id;
	}
	
	public static void encode(sendTileEntityDataPacket pkt, PacketBuffer buf) {
		buf.writeInt(pkt.posX);
		buf.writeInt(pkt.posY);
		buf.writeInt(pkt.posZ);
		buf.writeInt(pkt.ID);
	}
	
	public static sendTileEntityDataPacket decode(PacketBuffer buf) {
		return new sendTileEntityDataPacket(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt());
	}
	
	public static void handle(sendTileEntityDataPacket pkt, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
				ContainerScreenBasic.POS = new BlockPos(pkt.posX, pkt.posY, pkt.posZ);
				ContainerScreenBasic.ID = pkt.ID;
			}
		});
		ctx.get().setPacketHandled(true);
	}
	
}
