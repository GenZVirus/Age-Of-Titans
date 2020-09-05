package com.GenZVirus.AgeOfTitans.Common.Network;

import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.Common.Entities.ReaperEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class ReaperLoadPacket {

	public int id;
	public boolean occupied, outofstock;
	public ItemStack item1, item2, item3;

	public ReaperLoadPacket(int id, boolean occupied, boolean outofstock, ItemStack item1, ItemStack item2, ItemStack item3) {
		this.id = id;
		this.occupied = occupied;
		this.outofstock = outofstock;
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
	}

	public static void encode(ReaperLoadPacket pkt, PacketBuffer buf) {
		buf.writeInt(pkt.id);
		buf.writeBoolean(pkt.occupied);
		buf.writeBoolean(pkt.outofstock);
		buf.writeItemStack(pkt.item1);
		buf.writeItemStack(pkt.item2);
		buf.writeItemStack(pkt.item3);
	}

	public static ReaperLoadPacket decode(PacketBuffer buf) {
		return new ReaperLoadPacket(buf.readInt(), buf.readBoolean(), buf.readBoolean(), buf.readItemStack(), buf.readItemStack(), buf.readItemStack());
	}

	public static void handle(ReaperLoadPacket pkt, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {			
			if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
				setReaperStats(pkt.id, pkt.occupied, pkt.outofstock, pkt.item1, pkt.item2, pkt.item3);
			}
		});

		ctx.get().setPacketHandled(true);
	}
	
	@SuppressWarnings("resource")
	@OnlyIn(Dist.CLIENT)
	public static void setReaperStats(int id, boolean occupied, boolean outofstock, ItemStack item1, ItemStack item2, ItemStack item3) {		
		if(Minecraft.getInstance().player.world.getEntityByID(id) instanceof ReaperEntity) {
			ReaperEntity reaper = (ReaperEntity) Minecraft.getInstance().player.world.getEntityByID(id);
			reaper.occupied = occupied;
			reaper.outofstock = outofstock;
			reaper.item1 = item1.getItem();
			reaper.item2 = item2.getItem();
			reaper.item3 = item3.getItem();
		}
		
	}

}
