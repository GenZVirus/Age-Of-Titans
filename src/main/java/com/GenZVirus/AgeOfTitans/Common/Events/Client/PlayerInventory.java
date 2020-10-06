package com.GenZVirus.AgeOfTitans.Common.Events.Client;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Client.GUI.ReaperShop.ReaperShopScreen;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiContainerEvent.DrawBackground;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class PlayerInventory {

	public static ResourceLocation SOUL_BALANCE = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/soul_balance.png");
	private static int xSize = 176;
	private static int ySize = 18;
	private static int windowXPos;
	private static int windowYPos;
	private static FontRenderer font;
	
	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void SoulBalance(DrawBackground event) {
		if(!(event.getGuiContainer() instanceof InventoryScreen)) return;
		windowXPos = Minecraft.getInstance().getMainWindow().getScaledWidth() / 2 - 88;
		windowYPos = Minecraft.getInstance().getMainWindow().getScaledHeight() / 2 + 77;
		Minecraft.getInstance().getTextureManager().bindTexture(SOUL_BALANCE);
		AbstractGui.blit(windowXPos, windowYPos, 0, 0, 0, xSize, ySize, ySize, xSize);
		
		int balance = ReaperShopScreen.SCREEN.balance;
		font = Minecraft.getInstance().fontRenderer;
		final int zLevel = 300;
		IRenderTypeBuffer.Impl renderType = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());
		MatrixStack textStack = new MatrixStack();
		textStack.translate(0.0D, 0.0D, (double) zLevel);
		Matrix4f textLocation = textStack.getLast().getMatrix();
		int stringWidth = font.getStringWidth("Soul Balance");
		font.renderString("Soul Balance", windowXPos + 95 - stringWidth, windowYPos + 3, 0xFFFFFFFF, true, textLocation, renderType, false, 0, 15728880);
		stringWidth = font.getStringWidth(Integer.toString(balance));
		font.renderString(Integer.toString(balance), windowXPos + 167 - stringWidth, windowYPos + 3, 0xFFFFFFFF, true, textLocation, renderType, false, 0, 15728880);
		renderType.finish();
	}
	
}
