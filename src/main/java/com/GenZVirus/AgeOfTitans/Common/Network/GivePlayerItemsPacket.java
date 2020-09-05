package com.GenZVirus.AgeOfTitans.Common.Network;

import java.util.UUID;
import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.Util.ForgeEventBusSubscriber;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class GivePlayerItemsPacket {

	public ItemStack stack;
	public UUID uuid;

	public GivePlayerItemsPacket(UUID uuid, ItemStack stack) {
		this.uuid = uuid;
		this.stack = stack;
	}

	public static void encode(GivePlayerItemsPacket pkt, PacketBuffer buf) {
		buf.writeUniqueId(pkt.uuid);
		buf.writeItemStack(pkt.stack);
	}

	public static GivePlayerItemsPacket decode(PacketBuffer buf) {
		return new GivePlayerItemsPacket(buf.readUniqueId(), buf.readItemStack());
	}

	public static void handle(GivePlayerItemsPacket pkt, Supplier<NetworkEvent.Context> ctx) {

		ctx.get().enqueueWork(() -> {
			if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
				PlayerEntity player = ForgeEventBusSubscriber.players.get(ForgeEventBusSubscriber.uuids.indexOf(pkt.uuid));
				if (player.inventory.getFirstEmptyStack() != -1) {
					player.addItemStackToInventory(pkt.stack);
				} else {
					ItemEntity item = new ItemEntity(player.world, player.getPosX(), player.getPosY() + 1, player.getPosZ(), pkt.stack);
					player.world.addEntity(item);
				}
			}
		});

		ctx.get().setPacketHandled(true);
	}

}
