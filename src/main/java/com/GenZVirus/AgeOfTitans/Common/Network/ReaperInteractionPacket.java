package com.GenZVirus.AgeOfTitans.Common.Network;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

import com.GenZVirus.AgeOfTitans.Client.GUI.ReaperShop.ReaperShopScreen;
import com.GenZVirus.AgeOfTitans.Common.Entities.ReaperEntity;
import com.GenZVirus.AgeOfTitans.Common.Events.Server.PlayerEventsHandler;
import com.GenZVirus.AgeOfTitans.Common.Init.ItemInit;
import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ChatVisibility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class ReaperInteractionPacket {

	public UUID uuid;
	public int id;
	public boolean occupied, outofstock;
	public ItemStack item1, item2, item3;

	public ReaperInteractionPacket(UUID uuid, int id, boolean occupied, boolean outofstock, ItemStack item1, ItemStack item2, ItemStack item3) {
		this.uuid = uuid;
		this.id = id;
		this.occupied = occupied;
		this.outofstock = outofstock;
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
	}

	public static void encode(ReaperInteractionPacket pkt, PacketBuffer buf) {
		buf.writeUniqueId(pkt.uuid);
		buf.writeInt(pkt.id);
		buf.writeBoolean(pkt.occupied);
		buf.writeBoolean(pkt.outofstock);
		buf.writeItemStack(pkt.item1);
		buf.writeItemStack(pkt.item2);
		buf.writeItemStack(pkt.item3);
	}

	public static ReaperInteractionPacket decode(PacketBuffer buf) {
		return new ReaperInteractionPacket(buf.readUniqueId(), buf.readInt(), buf.readBoolean(), buf.readBoolean(), buf.readItemStack(), buf.readItemStack(), buf.readItemStack());
	}

	public static void handle(ReaperInteractionPacket pkt, Supplier<NetworkEvent.Context> ctx) {

		ctx.get().enqueueWork(() -> {
			if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
				World world = PlayerEventsHandler.players.get(PlayerEventsHandler.uuids.indexOf(pkt.uuid)).world;
				if (world.getEntityByID(pkt.id) instanceof ReaperEntity) {
					ReaperEntity reaper = (ReaperEntity) world.getEntityByID(pkt.id);
					reaper.occupied = pkt.occupied;
					if(!reaper.outofstock)
					reaper.outofstock = pkt.outofstock;
					List<Item> itemList = Lists.newArrayList();
					itemList.add(ItemInit.ORB_OF_EDEN.get());
					itemList.add(ItemInit.ORB_OF_DISLOCATION.get());
					itemList.add(ItemInit.ORB_OF_END.get());
					itemList.add(ItemInit.ORB_OF_NETHER.get());
					itemList.add(ItemInit.ORB_OF_STORAGE.get());
					itemList.add(ItemInit.ORB_OF_SUMMONING.get());
					itemList.add(ItemInit.FRUIT_OF_THE_GODS.get());
					Random rand = new Random();
					if (reaper.item1.equals(Items.AIR)) {
						reaper.item1 = itemList.get(rand.nextInt(itemList.size()));
						itemList.remove(reaper.item1);
					}
					if (reaper.item2.equals(Items.AIR)) {
						reaper.item2 = itemList.get(rand.nextInt(itemList.size()));
						itemList.remove(reaper.item2);
					}
					if (reaper.item3.equals(Items.AIR)) {
						reaper.item3 = itemList.get(rand.nextInt(itemList.size()));
						itemList.remove(reaper.item3);
					}
					for (PlayerEntity player : PlayerEventsHandler.players) {
						PacketHandlerCommon.INSTANCE.sendTo(new ReaperInteractionPacket(pkt.uuid, pkt.id, reaper.occupied, reaper.outofstock, new ItemStack(reaper.item1), new ItemStack(reaper.item2), new ItemStack(reaper.item3)), ((ServerPlayerEntity) player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
					}
				}
			}

			if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
				setReaperStats(pkt.uuid, pkt.id, pkt.occupied, pkt.outofstock, pkt.item1, pkt.item2, pkt.item3);
			}

		});

		ctx.get().setPacketHandled(true);
	}

	@SuppressWarnings("resource")
	@OnlyIn(Dist.CLIENT)
	public static void setReaperStats(UUID uuid, int id, boolean occupied, boolean outofstock, ItemStack item1, ItemStack item2, ItemStack item3) {
		if (Minecraft.getInstance().player.world.getEntityByID(id) instanceof ReaperEntity) {
			ReaperEntity reaper = (ReaperEntity) Minecraft.getInstance().player.world.getEntityByID(id);
			reaper.occupied = occupied;
			reaper.outofstock = outofstock;

			reaper.item1 = item1.getItem();
			reaper.item2 = item2.getItem();
			reaper.item3 = item3.getItem();
			ReaperShopScreen.SCREEN.reaper = reaper;
			Minecraft mc = Minecraft.getInstance();

			// checks for others screens or chat screen, if there are not hidden, the
			// character screen will not show up

			if (!mc.player.getUniqueID().equals(uuid))
				return;
			if (!occupied || outofstock)
				return;
			if ((mc.currentScreen != null && mc.gameSettings.chatVisibility != ChatVisibility.HIDDEN) || mc.world == null) { return; }
			mc.displayGuiScreen(ReaperShopScreen.SCREEN);
		}

	}

}
