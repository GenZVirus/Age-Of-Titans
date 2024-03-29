package com.GenZVirus.AgeOfTitans.Client.GUI.ReaperShop;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.PricedItem;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShopItemButton extends Widget {

	public ResourceLocation BUTTON = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/shop_item_button.png");
	public ResourceLocation BUTTON_HOVER = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/shop_item_button_hover.png");
	public ResourceLocation BUTTON_ACTIVE = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/shop_item_button_active.png");
	public ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
	public FontRenderer font = Minecraft.getInstance().fontRenderer;
	public Minecraft minecraft = Minecraft.getInstance();
	public boolean clickedOnce = false;
	public PricedItem item;
	public ItemStack stack;
	
	public ShopItemButton(int widthIn, int heightIn, PricedItem item) {
		super(widthIn, heightIn, 110, 170, "");
		this.active = false;
		this.item = item;
		this.stack = new ItemStack((IItemProvider) item);
	}

	@Override
	public void renderButton(int mouseX, int mouseY, float partialTicks) {
		ItemRenderer itemRenderer = minecraft.getItemRenderer();
		
		RenderSystem.enableBlend();
		if(this.active) {
			minecraft.getTextureManager().bindTexture(BUTTON_ACTIVE);
		} else if(this.isHovered()) {
			minecraft.getTextureManager().bindTexture(BUTTON_HOVER);
		} else {
			minecraft.getTextureManager().bindTexture(BUTTON);			
		}

		AbstractGui.blit(this.x, this.y, 0, 0, 0, this.width, this.height, this.height, this.width);
		int itemPosX = this.x + this.width / 2 - 8;
		int itemPosY = this.y + this.height / 2 - 6;
		itemRenderer.renderItemAndEffectIntoGUI(stack, itemPosX, itemPosY);
		if(mouseX < itemPosX  + 20 && mouseX > itemPosX && mouseY < itemPosY + 20 && mouseY > itemPosY) {
			ReaperShopScreen.SCREEN.setBlitOffset(300);
			this.renderTooltip(stack, this.x - 5, itemPosY + 32);
			ReaperShopScreen.SCREEN.setBlitOffset(0);
		}
		RenderSystem.disableBlend();
		this.renderBg(minecraft, mouseX, mouseY);
	}

	protected void renderTooltip(ItemStack stack, int posX, int posY) {
	      FontRenderer font = stack.getItem().getFontRenderer(stack);
	      net.minecraftforge.fml.client.gui.GuiUtils.preItemToolTip(stack);
	      drawHoveringText(this.getTooltipFromItem(stack), posX, posY, minecraft.getMainWindow().getWidth(), minecraft.getMainWindow().getHeight(), 100,(font == null ? this.font : font));
	      net.minecraftforge.fml.client.gui.GuiUtils.postItemToolTip();
	   }
	
	public List<String> getTooltipFromItem(ItemStack p_getTooltipFromItem_1_) {
	      List<ITextComponent> list = p_getTooltipFromItem_1_.getTooltip(this.minecraft.player, this.minecraft.gameSettings.advancedItemTooltips ? ITooltipFlag.TooltipFlags.ADVANCED : ITooltipFlag.TooltipFlags.NORMAL);
	      List<String> list1 = Lists.newArrayList();

	      for(ITextComponent itextcomponent : list) {
	         list1.add(itextcomponent.getFormattedText());
	      }

	      list1.add("");
	      list1.add("\u00A7bPrice: " + Integer.toString(((PricedItem)stack.getItem()).getPrice()) + " Souls");
	      
	      return list1;
	   }
	
	public static void drawHoveringText(List<String> textLines, int mouseX, int mouseY, int screenWidth, int screenHeight, int maxTextWidth, FontRenderer font) {
		if (!textLines.isEmpty()) {

			int tooltipTextWidth = 0;

			for (String textLine : textLines) {
				int textLineWidth = font.getStringWidth(textLine);
				if (textLineWidth > tooltipTextWidth)
					tooltipTextWidth = textLineWidth;
			}

			boolean needsWrap = false;

			int titleLinesCount = 1;
			int tooltipX = mouseX + 12;
			if (tooltipX + tooltipTextWidth + 4 > screenWidth) {
				tooltipX = mouseX - 16 - tooltipTextWidth;
				if (tooltipX < 4) // if the tooltip doesn't fit on the screen
				{
					if (mouseX > screenWidth / 2)
						tooltipTextWidth = mouseX - 12 - 8;
					else
						tooltipTextWidth = screenWidth - 16 - mouseX;
					needsWrap = true;
				}
			}

			if (maxTextWidth > 0 && tooltipTextWidth > maxTextWidth) {
				tooltipTextWidth = maxTextWidth;
				needsWrap = true;
			}

			if (needsWrap) {
				int wrappedTooltipWidth = 0;
				List<String> wrappedTextLines = new ArrayList<String>();
				for (int i = 0; i < textLines.size(); i++) {
					String textLine = textLines.get(i);
					List<String> wrappedLine = font.listFormattedStringToWidth(textLine, tooltipTextWidth);
					if (i == 0)
						titleLinesCount = wrappedLine.size();

					for (String line : wrappedLine) {
						int lineWidth = font.getStringWidth(line);
						if (lineWidth > wrappedTooltipWidth)
							wrappedTooltipWidth = lineWidth;
						wrappedTextLines.add(line);
					}
				}
				tooltipTextWidth = wrappedTooltipWidth;
				textLines = wrappedTextLines;

				if (mouseX > screenWidth / 2)
					tooltipX = mouseX - 16 - tooltipTextWidth;
				else
					tooltipX = mouseX + 12;
			}

			int tooltipY = mouseY - 12;
			int tooltipHeight = 8;

			if (textLines.size() > 1) {
				tooltipHeight += (textLines.size() - 1) * 10;
				if (textLines.size() > titleLinesCount)
					tooltipHeight += 2; // gap between title lines and next lines
			}

			if (tooltipY < 4)
				tooltipY = 4;
			else if (tooltipY + tooltipHeight + 4 > screenHeight)
				tooltipY = screenHeight - tooltipHeight - 4;

			final int zLevel = 300;
			int backgroundColor = 0xF0100010;
			int borderColorStart = 0x505000FF;
			int borderColorEnd = (borderColorStart & 0xFEFEFE) >> 1 | borderColorStart & 0xFF000000;

			drawGradientRect(zLevel, tooltipX - 3, tooltipY - 4, tooltipX + tooltipTextWidth + 3, tooltipY - 3, backgroundColor, backgroundColor);
			drawGradientRect(zLevel, tooltipX - 3, tooltipY + tooltipHeight + 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 4, backgroundColor, backgroundColor);
			drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
			drawGradientRect(zLevel, tooltipX - 4, tooltipY - 3, tooltipX - 3, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
			drawGradientRect(zLevel, tooltipX + tooltipTextWidth + 3, tooltipY - 3, tooltipX + tooltipTextWidth + 4, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
			drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3 + 1, tooltipX - 3 + 1, tooltipY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
			drawGradientRect(zLevel, tooltipX + tooltipTextWidth + 2, tooltipY - 3 + 1, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
			drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY - 3 + 1, borderColorStart, borderColorStart);
			drawGradientRect(zLevel, tooltipX - 3, tooltipY + tooltipHeight + 2, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, borderColorEnd, borderColorEnd);

			IRenderTypeBuffer.Impl renderType = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());
			MatrixStack textStack = new MatrixStack();
			textStack.translate(0.0D, 0.0D, (double) zLevel);
			Matrix4f textLocation = textStack.getLast().getMatrix();

			for (int lineNumber = 0; lineNumber < textLines.size(); ++lineNumber) {
				String line = textLines.get(lineNumber);
				if (line != null)
					font.renderString(line, (float) tooltipX, (float) tooltipY, 65280, true, textLocation, renderType, false, 0, 15728880);

				if (lineNumber + 1 == titleLinesCount)
					tooltipY += 2;

				tooltipY += 10;
			}

			renderType.finish();

		}
	}

	public static void drawGradientRect(int zLevel, int left, int top, int right, int bottom, int startColor, int endColor) {
		float startAlpha = (float) (startColor >> 24 & 255) / 255.0F;
		float startRed = (float) (startColor >> 16 & 255) / 255.0F;
		float startGreen = (float) (startColor >> 8 & 255) / 255.0F;
		float startBlue = (float) (startColor & 255) / 255.0F;
		float endAlpha = (float) (endColor >> 24 & 255) / 255.0F;
		float endRed = (float) (endColor >> 16 & 255) / 255.0F;
		float endGreen = (float) (endColor >> 8 & 255) / 255.0F;
		float endBlue = (float) (endColor & 255) / 255.0F;

		RenderSystem.disableTexture();
		RenderSystem.enableBlend();
		RenderSystem.disableAlphaTest();
		RenderSystem.defaultBlendFunc();
		RenderSystem.shadeModel(GL11.GL_SMOOTH);

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buffer = tessellator.getBuffer();
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
		buffer.pos(right, top, zLevel).color(startRed, startGreen, startBlue, startAlpha).endVertex();
		buffer.pos(left, top, zLevel).color(startRed, startGreen, startBlue, startAlpha).endVertex();
		buffer.pos(left, bottom, zLevel).color(endRed, endGreen, endBlue, endAlpha).endVertex();
		buffer.pos(right, bottom, zLevel).color(endRed, endGreen, endBlue, endAlpha).endVertex();
		tessellator.draw();

		RenderSystem.shadeModel(GL11.GL_FLAT);
		RenderSystem.disableBlend();
		RenderSystem.enableAlphaTest();
		RenderSystem.enableTexture();
	}
	
	public void onPress() {
		if(!this.clickedOnce) {
			this.active = true;
			this.clickedOnce = true;
		} else {
			this.active = false;
			this.clickedOnce = false;
		}
		this.playDownSound(Minecraft.getInstance().getSoundHandler());
	}

}
