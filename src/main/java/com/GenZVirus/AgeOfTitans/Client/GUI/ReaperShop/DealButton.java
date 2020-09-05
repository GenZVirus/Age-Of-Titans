package com.GenZVirus.AgeOfTitans.Client.GUI.ReaperShop;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DealButton extends Widget {

	public ResourceLocation BUTTON = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/deal_button.png");
	public ResourceLocation BUTTON_HOVER = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/deal_button_hover.png");

	public DealButton(int widthIn, int heightIn) {
		super(widthIn, heightIn, 26, 26, "Deal");
	}

	@Override
	public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
		RenderSystem.enableBlend();
		Minecraft minecraft = Minecraft.getInstance();
		FontRenderer fontrenderer = minecraft.fontRenderer;
		if (this.isHovered()) {
			minecraft.getTextureManager().bindTexture(BUTTON_HOVER);
		} else {
			minecraft.getTextureManager().bindTexture(BUTTON);
		}
		AbstractGui.blit(this.x, this.y, 0, 0, 0, this.width, this.height, this.height, this.width);
		this.renderBg(minecraft, p_renderButton_1_, p_renderButton_2_);
		int j = getFGColor();
		this.drawCenteredString(fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
		RenderSystem.disableBlend();
	}

	public void onPress() {
		this.playDownSound(Minecraft.getInstance().getSoundHandler());
	}

}
