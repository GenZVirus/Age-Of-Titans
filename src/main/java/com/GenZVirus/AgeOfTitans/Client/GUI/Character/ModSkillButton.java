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

public class ModSkillButton extends Widget {

	public static final ResourceLocation BUTTONS_LOCATION = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/test.png");
	public ModSkillSlot boundSlot;
	public boolean isBound = false;
	
	public ModSkillButton(int xIn, int yIn, int widthIn, int heightIn, String msg) {
		super(xIn, yIn, widthIn, heightIn, msg);
	}
	
	public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
	      Minecraft minecraft = Minecraft.getInstance();
	      FontRenderer fontrenderer = minecraft.fontRenderer;
	      minecraft.getTextureManager().bindTexture(BUTTONS_LOCATION);
	      int i = this.getYImage(this.isHovered());
	      RenderSystem.enableBlend();
	      RenderSystem.defaultBlendFunc();
	      RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
	      AbstractGui.blit(this.x, this.y, 0, 0, 0, this.width, this.height, 16, 16);
	      if(boundSlot != null) {
	    	  AbstractGui.blit(boundSlot.x + 2, boundSlot.y + 2, 0, 0, 0, this.width, this.height, 16, 16);
	      }
	      this.renderBg(minecraft, p_renderButton_1_, p_renderButton_2_);
	      int j = getFGColor();
	      this.drawCenteredString(fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
	   }
	
	public void onPress() {
		this.playDownSound(Minecraft.getInstance().getSoundHandler());
		for(Widget button : ModScreen.SCREEN.getButtons()) {
			if(button instanceof ModSkillSlot && ((ModSkillSlot) button).isSelected) {
				boundSlot = ((ModSkillSlot) button);
				break;
			}
		}
	}

}
