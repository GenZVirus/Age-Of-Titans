package com.GenZVirus.AgeOfTitans.Common.Network;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class DiscordServerMessagePacket {

	public DiscordServerMessagePacket() {
	}

	public static void encode(DiscordServerMessagePacket pkt, PacketBuffer buf) {
	}

	public static DiscordServerMessagePacket decode(PacketBuffer buf) {
		return new DiscordServerMessagePacket();
	}

	public static void handle(DiscordServerMessagePacket pkt, Supplier<NetworkEvent.Context> ctx) {

		ctx.get().enqueueWork(() -> {
			if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
				sendMessage("\n\n\u00A7cClick Here >>> \u00A7r\u00A7l\u00A7eAge of Titans: \u00A7r\u00A7n\u00A7bOfficial Discord Server\u00A7r\u00A7c <<< Click Here\n\n");
			}
		});

		ctx.get().setPacketHandled(true);
	}
	
	@SuppressWarnings("resource")
	@OnlyIn(Dist.CLIENT)
	public static void sendMessage(String message) {
		TranslationTextComponent link = new TranslationTextComponent(message);
		link.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.com/invite/ty6gQaD"));
		Minecraft.getInstance().ingameGUI.getChatGUI().printChatMessage(link);
	}

}
