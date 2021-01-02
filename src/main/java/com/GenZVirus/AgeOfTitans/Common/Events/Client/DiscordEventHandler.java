package com.GenZVirus.AgeOfTitans.Common.Events.Client;

import java.net.URI;
import java.net.URISyntaxException;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.ConfirmOpenLinkScreen;
import net.minecraft.client.gui.screen.IngameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class DiscordEventHandler {

	private static Minecraft mc = Minecraft.getInstance();
	private static ResourceLocation DISCORD = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/discord/discord.png");
	private static ResourceLocation DISCORD_BACKGROUND = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/discord/discord_background.png");
	@SubscribeEvent
	public static void Discord(DrawScreenEvent.Post event) {
		if (event.getGui() instanceof IngameMenuScreen && mc.world != null) {
			mc.getTextureManager().bindTexture(DISCORD);
			RenderSystem.scalef(0.1F, 0.1F, 0.1F);
			RenderSystem.enableBlend();
			int posX = mc.getMainWindow().getScaledWidth() - 22;
			int posY = mc.getMainWindow().getScaledHeight() - 28;
			AbstractGui.blit(posX * 10, posY * 10, 0, 0, 0, 160, 160, 160, 160);
			RenderSystem.disableBlend();
			RenderSystem.scalef(10.0F, 10.0F, 10.0F);
			mc.fontRenderer.drawString("Age of Titans", posX - 88, posY + 4, 0xFFFFFFFF);
		}
	}

	@SubscribeEvent
	public static void DiscordBackground(DrawScreenEvent.Pre event) {
		if (event.getGui() instanceof IngameMenuScreen && mc.world != null) {
			int posX = mc.getMainWindow().getScaledWidth() - 120;
			int posY = mc.getMainWindow().getScaledHeight() - 40;
			mc.getTextureManager().bindTexture(DISCORD_BACKGROUND);
			RenderSystem.enableBlend();
			AbstractGui.blit(posX, posY, 0, 0, 0, 120, 40, 40, 120);
			RenderSystem.disableBlend();
		}
	}
	
	@SubscribeEvent
	public static void MenuOptions(GuiScreenEvent.InitGuiEvent.Post event) {
		if (event.getGui() instanceof IngameMenuScreen && mc.world != null) {
			event.addWidget(new Button(mc.getMainWindow().getScaledWidth() - 24, mc.getMainWindow().getScaledHeight() - 30, 20, 20, " ", (x) -> {
				mc.displayGuiScreen(new ConfirmOpenLinkScreen(DiscordEventHandler::confirmLink, new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.com/invite/ty6gQaD").getValue(), false));
			}));
		}
	}
	
	public static void confirmLink(boolean p_confirmLink_1_) {
		if (p_confirmLink_1_) {
			try {
				openLink(new URI(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.com/invite/ty6gQaD").getValue()));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		mc.displayGuiScreen((Screen) null);
	}

	private static void openLink(URI p_openLink_1_) {
		Util.getOSType().openURI(p_openLink_1_);
	}
	
}
