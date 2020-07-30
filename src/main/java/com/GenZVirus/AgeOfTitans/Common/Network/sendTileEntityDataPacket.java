package com.GenZVirus.AgeOfTitans.Common.Network;

import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.Client.Container.ContainerBasic;
import com.GenZVirus.AgeOfTitans.Client.Container.ContainerScreenBasic;
import com.GenZVirus.AgeOfTitans.Common.TileEntity.TileEntityInventoryBasic;
import com.GenZVirus.AgeOfTitans.Util.ForgeEventBusSubscriber;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class sendTileEntityDataPacket {
	
	public int posX, posY, posZ, ID;
	public float scroll;
	public String search;
	
	public sendTileEntityDataPacket(int posX, int posY, int posZ, int id, float scroll, String search) {
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.scroll = scroll;
		this.ID = id;
		this.search = search;
	}
	
	public static void encode(sendTileEntityDataPacket pkt, PacketBuffer buf) {
		buf.writeInt(pkt.posX);
		buf.writeInt(pkt.posY);
		buf.writeInt(pkt.posZ);
		buf.writeInt(pkt.ID);
		buf.writeFloat(pkt.scroll);
		buf.writeString(pkt.search);
	}
	
	public static sendTileEntityDataPacket decode(PacketBuffer buf) {
		return new sendTileEntityDataPacket(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt(), buf.readFloat(), buf.readString());
	}
	
	@SuppressWarnings("deprecation")
	public static void handle(sendTileEntityDataPacket pkt, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
				ContainerScreenBasic.POS = new BlockPos(pkt.posX, pkt.posY, pkt.posZ);
				ContainerScreenBasic.ID = pkt.ID;
			}
			if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
				BlockPos pos = new BlockPos(pkt.posX, pkt.posY, pkt.posZ);
				TileEntityInventoryBasic tile = (TileEntityInventoryBasic) ForgeEventBusSubscriber.players.get(0).world.getServer().getWorld(Registry.DIMENSION_TYPE.getByValue(pkt.ID)).getTileEntity(pos);
				((ContainerBasic)tile.container).scrollTo(pkt.scroll);
				((ContainerBasic)tile.container).search = pkt.search;
			}
		});
		ctx.get().setPacketHandled(true);
	}
	
}