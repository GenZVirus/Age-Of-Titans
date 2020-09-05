package com.GenZVirus.AgeOfTitans.Common.Commands;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DiscordServerCommand {
	
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("discordAgeofTitans").requires(context -> {
			return context.hasPermissionLevel(1);
		}).executes(source ->{
			return discord(source.getSource());
		}));
	}
	
	private static int discord(CommandSource source) {
		sendMessage("\n\n\u00A7cClick Here >>> \u00A7r\u00A7l\u00A7eAge of Titans: \u00A7r\u00A7n\u00A7bOfficial Discord Server\u00A7r\u00A7c <<< Click Here\n\n");
		
		return 1;
	}
	
	@SuppressWarnings("resource")
	@OnlyIn(Dist.CLIENT)
	public static void sendMessage(String message) {
		TranslationTextComponent link = new TranslationTextComponent(message);
		link.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.com/invite/ty6gQaD"));
		Minecraft.getInstance().ingameGUI.getChatGUI().printChatMessage(link);
	}
}