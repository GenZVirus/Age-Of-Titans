package com.GenZVirus.AgeOfTitans.Client.GUI.Character;

import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;
import com.mojang.blaze3d.platform.GlStateManager;
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
public class ModSkillButton extends Widget {

	private ResourceLocation BUTTONS_LOCATION;
	public boolean isSelected = false;
	public Spell spell;
	
	public ModSkillButton(int xIn, int yIn, int widthIn, int heightIn, String msg, Spell spell) {
		super(xIn, yIn, widthIn, heightIn, msg);
		this.BUTTONS_LOCATION = spell.getIcon();
		this.spell = spell;
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
	      
	      this.renderBg(minecraft, p_renderButton_1_, p_renderButton_2_);
	      int j = getFGColor();
	      this.drawCenteredString(fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
	   }
	
	public void onPress() {
		this.playDownSound(Minecraft.getInstance().getSoundHandler());
		for(Widget button : ModScreen.SCREEN.getButtons()) {
			if(button instanceof ModSkillButton) {
				((ModSkillButton) button).isSelected = false;
			}
		}
		this.isSelected = true;
	}

}
