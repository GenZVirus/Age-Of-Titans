package com.GenZVirus.AgeOfTitans.Client.GUI.Character;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class ModButton extends Widget{
	
	public static final ResourceLocation BUTTONS_LOCATION = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/button.png");
	public boolean isPressed = false;
	
	public ModButton(int widthIn, int heightIn, int width, int height, String text) {
		super(widthIn, heightIn, width, height, text);
			}

	@Override
	   public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
	      Minecraft minecraft = Minecraft.getInstance();
	      FontRenderer fontrenderer = minecraft.fontRenderer;
	      minecraft.getTextureManager().bindTexture(BUTTONS_LOCATION);
	      int i = this.getYImage(this.isHovered());
	      RenderSystem.enableBlend();
	      RenderSystem.defaultBlendFunc();
	      RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
	      AbstractGui.blit(this.x, this.y, 0, 0, i * 20, this.width, this.height, 60, 156);
	      //this.blit(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + hoveredVal * 20, this.width / 2, this.height);
	      this.renderBg(minecraft, p_renderButton_1_, p_renderButton_2_);
	      int j = getFGColor();
	      this.drawCenteredString(fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
	   }

	public void onPress() {
		this.active = false;
		this.playDownSound(Minecraft.getInstance().getSoundHandler());
	}
	
}