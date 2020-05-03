package com.GenZVirus.AgeOfTitans.Client.GUI.Character;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class ModSkillSlot extends Widget {

	public static final ResourceLocation BUTTONS_LOCATION = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/slot.png");
	public int slot;
	public Spell spell;
	
	public ModSkillSlot(int xIn, int yIn, int widthIn, int heightIn, String msg, int slot) {
		super(xIn, yIn, widthIn, heightIn, msg);
		this.slot = slot;
		this.spell = Spell.SPELL_LIST.get(0);
	}
	
	public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
	      Minecraft minecraft = Minecraft.getInstance();
	      FontRenderer fontrenderer = minecraft.fontRenderer;
	      minecraft.getTextureManager().bindTexture(BUTTONS_LOCATION);
	      
	      int i = this.getYImage(this.isHovered());
	      
	      RenderSystem.enableBlend();
	      RenderSystem.defaultBlendFunc();
	      RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
	      
	      i = 0;
	      
	      AbstractGui.blit(this.x, this.y, 0, 0, i * 25, this.width, this.height, this.height * 2, this.width);
		     
	      if(spell.getId() != 0) {
	    	  minecraft.getTextureManager().bindTexture(spell.getIcon());
	    	  AbstractGui.blit(this.x + 2, this.y + 2, 0, 0, 0, 16, 16, 16, 16);
	      }
	      
	      this.renderBg(minecraft, p_renderButton_1_, p_renderButton_2_);
	      int j = getFGColor();
	      this.drawCenteredString(fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
	   }

	public void onPress() {
		this.playDownSound(Minecraft.getInstance().getSoundHandler());
		int slot1 = Spell.SPELL_LIST.get(0).getId();
		int slot2 = Spell.SPELL_LIST.get(0).getId();
		int slot3 = Spell.SPELL_LIST.get(0).getId();
		int slot4 = Spell.SPELL_LIST.get(0).getId();
		Spell selectedSpell = Spell.SPELL_LIST.get(0);
		
		for(Widget button : ModScreen.SCREEN.getButtons()) {
			
			if(button instanceof ModSkillButton && ((ModSkillButton) button).isSelected) {
				selectedSpell = ((ModSkillButton) button).spell;
				((ModSkillButton) button).isSelected = false;						
			}
			
			if(button instanceof ModSkillSlot && ((ModSkillSlot) button).spell.getId() == selectedSpell.getId()) {
				if(((ModSkillSlot) button).slot == 1) {
					slot1 = Spell.SPELL_LIST.get(0).getId();
					((ModSkillSlot) button).spell = Spell.SPELL_LIST.get(0);
				}else if(((ModSkillSlot) button).slot == 2) {
					slot2 = Spell.SPELL_LIST.get(0).getId();
					((ModSkillSlot) button).spell = Spell.SPELL_LIST.get(0);
				}else if(((ModSkillSlot) button).slot == 3) {
					slot3 = Spell.SPELL_LIST.get(0).getId();
					((ModSkillSlot) button).spell = Spell.SPELL_LIST.get(0);
				}else if(((ModSkillSlot) button).slot == 4) {
					slot4 = Spell.SPELL_LIST.get(0).getId();
					((ModSkillSlot) button).spell = Spell.SPELL_LIST.get(0);
				}				
			}
		}
		
		if(this.slot == 1) {
			slot1 = selectedSpell.getId();
			this.spell = selectedSpell;
		} else if(this.slot == 2) {
			slot2 = selectedSpell.getId();
			this.spell = selectedSpell;
		} else if(this.slot == 3) {
			slot3 = selectedSpell.getId();
			this.spell = selectedSpell;
		} else if(this.slot == 4) {
			slot4 = selectedSpell.getId();
			this.spell = selectedSpell;
		}
		

		
	}
	
}
