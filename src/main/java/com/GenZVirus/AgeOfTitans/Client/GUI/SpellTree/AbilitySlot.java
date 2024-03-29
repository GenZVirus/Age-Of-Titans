package com.GenZVirus.AgeOfTitans.Client.GUI.SpellTree;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.SpellSystem.ActiveAbility;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class AbilitySlot extends Widget {

	// This class provides the slots icons with the spell inside them
	
	// Address of the slot texture
	
	public static final ResourceLocation BUTTONS_LOCATION = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/slot.png");
	
	// The slot id
	
	public int slot;
	
	// The spell which is inside the slot
	
	public ActiveAbility ability;
	
	// Slot initialization
	
	public AbilitySlot(int xIn, int yIn, int widthIn, int heightIn, String msg, int slot) {
		super(xIn, yIn, widthIn, heightIn, msg);
		this.slot = slot;
		
		// If the slot is empty the spell get's the NoSpell spell which has an ID of 0
		
		this.ability = (ActiveAbility) ActiveAbility.getList().get(0);
	}
	
	// Slot renderer
	
	public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
	      Minecraft minecraft = Minecraft.getInstance();
	      FontRenderer fontrenderer = minecraft.fontRenderer;
	      minecraft.getTextureManager().bindTexture(BUTTONS_LOCATION);
	      
	      int i = 0;
	      if(this.isHovered) {
	    	  i = 1;
	      }
	      
	      RenderSystem.enableBlend();
	      RenderSystem.defaultBlendFunc();
	      RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
	      
	      /*
	       *  First param is the X position
	       *  Second param is the y position
	       *  Third param is the blit offset
	       *  Fourth param is the X position from where it starts to display pixels in the texture image
	       *  Fifth param is the Y position from where it starts to display pixels in the texture image
	       *  Sixth param is the Width of the image that we want to display
	       *  Seventh param is the Height of the image that we want to display
	       *  Eighth param is the Height of the texture image
	       *  Ninth param is the Width of the texture image
	       */
	      
	      AbstractGui.blit(this.x, this.y, 0, 0, i * 25, this.width, this.height, this.height * 2, this.width);
		     
	      // If the current spell is not NoSpell it will get displayed on the slot
	      
	      if(ability.getId() != 0) {
	    	  minecraft.getTextureManager().bindTexture(ability.getIcon());
	    	  AbstractGui.blit(this.x + 2, this.y + 2, 0, 0, 0, 20, 20, 20, 20);
	      }
	      
	      this.renderBg(minecraft, p_renderButton_1_, p_renderButton_2_);
	      int j = getFGColor();
	      this.drawCenteredString(fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
	   }

	@SuppressWarnings("unused")
	public void onPress() {
		this.playDownSound(Minecraft.getInstance().getSoundHandler());
		int slot1 = ActiveAbility.getList().get(0).getId();
		int slot2 = ActiveAbility.getList().get(0).getId();
		int slot3 = ActiveAbility.getList().get(0).getId();
		int slot4 = ActiveAbility.getList().get(0).getId();
		ActiveAbility selectedAbility = (ActiveAbility) ActiveAbility.getList().get(0);
		
		// Checks all widgets for buttons and deselectes them
		
		for(Widget button : AbilityTreeScreen.SCREEN.getButtons()) {
			
			if(button instanceof ActiveSkillButton && ((ActiveSkillButton) button).isSelected) {
				selectedAbility = (ActiveAbility) ((ActiveSkillButton) button).ability;
				((ActiveSkillButton) button).isSelected = false;						
			}
			
		// Searches for all slots that have the same selected spell and sets them NoSpell
			
			if(button instanceof AbilitySlot && ((AbilitySlot) button).ability.getId() == selectedAbility.getId()) {
				if(((AbilitySlot) button).slot == 1) {
					slot1 = ActiveAbility.getList().get(0).getId();
					((AbilitySlot) button).ability = (ActiveAbility) ActiveAbility.getList().get(0);
				}else if(((AbilitySlot) button).slot == 2) {
					slot2 = ActiveAbility.getList().get(0).getId();
					((AbilitySlot) button).ability = (ActiveAbility) ActiveAbility.getList().get(0);
				}else if(((AbilitySlot) button).slot == 3) {
					slot3 = ActiveAbility.getList().get(0).getId();
					((AbilitySlot) button).ability = (ActiveAbility) ActiveAbility.getList().get(0);
				}else if(((AbilitySlot) button).slot == 4) {
					slot4 = ActiveAbility.getList().get(0).getId();
					((AbilitySlot) button).ability = (ActiveAbility) ActiveAbility.getList().get(0);
				}				
			}
		}
		
		// Changes the selected slot spell
		
		if(this.slot == 1) {
			slot1 = selectedAbility.getId();
			this.ability = selectedAbility;
		} else if(this.slot == 2) {
			slot2 = selectedAbility.getId();
			this.ability = selectedAbility;
		} else if(this.slot == 3) {
			slot3 = selectedAbility.getId();
			this.ability = selectedAbility;
		} else if(this.slot == 4) {
			slot4 = selectedAbility.getId();
			this.ability = selectedAbility;
		}
		

		
	}
	
}
