package com.GenZVirus.AgeOfTitans.Client.GUI.Character;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.ChatVisibility;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModHUD {

	public static ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(AgeOfTitans.MOD_ID,"textures/gui/hud.png");
	private static Minecraft mc = Minecraft.getInstance();
	private static final int IMAGE_SIZE = 64;
	private static int i = 0;
	private static int j = 0;
	public static boolean next = false;
	public static boolean previous = false;
	public static int nr = 0;

	public static void renderHUD() {

		Minecraft mc = Minecraft.getInstance();
		if ((mc.currentScreen != null && mc.gameSettings.chatVisibility != ChatVisibility.HIDDEN) || mc.world == null) {
			return;
		}

		assert mc.player != null;

		if (!Minecraft.isGuiEnabled()) {
			return;
		}

		if (mc.gameSettings.showDebugInfo) {
			return;
		}

		renderOverlay();
	}

	public static void renderOverlay() {

		RenderSystem.scalef(1.0F, 1.0F, 1.0F);
		
		RenderSystem.disableRescaleNormal();
		RenderHelper.disableStandardItemLighting();
		RenderSystem.disableLighting();
		RenderSystem.disableDepthTest();

		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
		
		int posX = 0;
		int posY;
		if(mc.gameSettings.guiScale > mc.getMainWindow().getHeight() / 240) mc.gameSettings.guiScale = mc.getMainWindow().getHeight() / 240;
		posY = mc.getMainWindow().getHeight() / (mc.gameSettings.guiScale != 0 ? mc.gameSettings.guiScale : mc.getMainWindow().getHeight() / 240) - IMAGE_SIZE;
		posX = mc.getMainWindow().getWidth() / (mc.gameSettings.guiScale != 0 ? mc.gameSettings.guiScale : mc.getMainWindow().getWidth() / 240) - IMAGE_SIZE;
		posX = posX * 5 / 100;
		posY = posY - posY * 5 / 100;

		AbstractGui.blit(posX, posY, 0, 64 * j, 64 * i, IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE*10, IMAGE_SIZE*4);
		
		if(next) {
			i++;
			nr++;
		}
		
		if(previous) {
			i--;
			nr++;
			}
		
		if(nr==10) {
			next = false;
			previous = false;
			nr = 0;
		}
		
		if(i < 0) {
			i = 9;
			j--;
		}
		
		if(j < 0){
			j = 3;
		}
		
		if(i == 10) {
			i = 0;
			j++;
		}
		
		if(j == 4) {
			j = 0;
		}
	}
}
