package com.GenZVirus.AgeOfTitans.Client.GUI.Character;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Events.KeyPressedEvent;
import com.GenZVirus.AgeOfTitans.Network.PacketHandler;
import com.GenZVirus.AgeOfTitans.Network.SpellPacket;
import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModScreen extends Screen {

	// This class handles the GUI that displays the skill tree
	
	// Initializing the class so it is unique
	public static final ModScreen SCREEN = new ModScreen();
	
	// The texture for human skill tree
	
	public ResourceLocation BACKGROUND_TEXTURE_HUMAN = new ResourceLocation(AgeOfTitans.MOD_ID,
			"textures/gui/character_background_human.png");
	
	// The texture for reaper skill tree
	
	public ResourceLocation BACKGROUND_TEXTURE_REAPER = new ResourceLocation(AgeOfTitans.MOD_ID,
			"textures/gui/character_background_reaper.png");
	
	// The size of the GUI
	
	private int xSize = 320;
	private int ySize = 256;
	
	// The buttons for changing the skill tree
	
	public ModButton Human;
	public ModButton Reaper;
	
	// The button for spells in the skill tree
	
	public ModSkillButton SwordSlash, ShieldBash, Berserker, Chain;
	
	// The skill slots in the skill tree
	
	public ModSkillSlot slot1, slot2, slot3, slot4;
	
	

	public ModScreen(ITextComponent titleIn) {
		super(titleIn);
		this.minecraft = Minecraft.getInstance();
	}

	public ModScreen() {
		this(getDefaultName());
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	@Override
	public void init() {
		super.init();
		
		// If you press on the human button the background changes to the Human one
		
		this.Human = new ModButton((this.width - this.xSize) / 2 + 4, (this.height - this.ySize) / 2 + 4, 156, 20,
				I18n.format("gui.human")) {
			@Override
			public void onPress() {
				this.isPressed = true;
				for (Widget button : buttons) {
					if (button instanceof ModButton) {
						button.active = true;
						if (button != this) {
							((ModButton) button).isPressed = false;
						}
					}
				}
				super.onPress();
			}
		};
		
		// By default human background is selected
		
		Human.isPressed = true;
		Human.active = false;
		
		// If you press on the human button the background changes to the Human one
		
		this.Reaper = new ModButton((this.width - this.xSize) / 2 + 4 + 156, (this.height - this.ySize) / 2 + 4, 156,
				20, I18n.format("gui.reaper")) {
			@Override
			public void onPress() {
				this.isPressed = true;
				for (Widget button : buttons) {
					if (button instanceof ModButton) {
						button.active = true;
						if (button != this) {
							((ModButton) button).isPressed = false;
						}
					}
				}
				super.onPress();
			}
		};
		
		// The four spell slots
		
		this.slot1 = new ModSkillSlot((this.width - this.xSize) / 2 + 4, (this.height - this.ySize) / 2 + this.ySize - 4 - 25, 25, 25, I18n.format(""), 1);		
		this.slot2 = new ModSkillSlot((this.width - this.xSize) / 2 + 4 + 25, (this.height - this.ySize) / 2 + this.ySize - 4 - 25, 25, 25, I18n.format(""), 2);		
		this.slot3 = new ModSkillSlot((this.width - this.xSize) / 2 + 4 + 50, (this.height - this.ySize) / 2 + this.ySize - 4 - 25, 25, 25, I18n.format(""), 3);		
		this.slot4 = new ModSkillSlot((this.width - this.xSize) / 2 + 4 + 75, (this.height - this.ySize) / 2 + this.ySize - 4 - 25, 25, 25, I18n.format(""), 4);
		
		// The spell buttons
		
		this.SwordSlash = new ModSkillButton((this.width - this.xSize) / 2 + 4 + 156, (this.height - this.ySize) / 2 + 4 + 60, 20, 20, I18n.format(""), Spell.SPELL_LIST.get(1));		
		this.ShieldBash = new ModSkillButton((this.width - this.xSize) / 2 + 4 + 156, (this.height - this.ySize) / 2 + 4 + 30, 20, 20, I18n.format(""), Spell.SPELL_LIST.get(2));
		this.Berserker = new ModSkillButton((this.width - this.xSize) / 2 + 4 + 156, (this.height - this.ySize) / 2 + 4 + 90, 20, 20, I18n.format(""), Spell.SPELL_LIST.get(3));
		this.Chain = new ModSkillButton((this.width - this.xSize) / 2 + 4 + 156, (this.height - this.ySize) / 2 + 4 + 120, 20, 20, I18n.format(""), Spell.SPELL_LIST.get(4));
		
		this.addButtons();
		
	}

	public void addButtons() {
		buttons.clear();

		this.loadSpells();
		
		buttons.add(slot1);
		buttons.add(slot2);
		buttons.add(slot3);
		buttons.add(slot4);
		
		
		buttons.add(Human);
		buttons.add(Reaper);
	}
	
	public void loadSpells() {
		
		if(this.Human.isPressed) {
			buttons.add(SwordSlash);
			buttons.add(ShieldBash);
			buttons.add(Berserker);
			buttons.add(Chain);
		}
	}
	
	@Override
	public void tick() {
		super.tick();
	}
	
	@Override
	public void onClose() {
		
	// When the keyPressedEvent fires and the screen is already opened it sets wasPRESSED on false to not close other existing screens like chat screen
		
		KeyPressedEvent.wasPRESSED = false;
		
	// Sends a packet to the server to save to the custom playerdata file
		
		sendPacket(slot1.spell.getId(), slot2.spell.getId(), slot3.spell.getId(), slot4.spell.getId());
		super.onClose();
	}

	// Screen renderer
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		
		RenderSystem.disableRescaleNormal();
		RenderSystem.disableDepthTest();
		this.renderBackground();
		
	// Decides if it renders the human background or the reaper background
		
		if (this.Human.isPressed) {
			this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE_HUMAN);
			this.addButtons();
		} else if (this.Reaper.isPressed) {
			this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE_REAPER);
			this.addButtons();
		} else {
			this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE_HUMAN);
		}
		
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		
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
		
		AbstractGui.blit(x, y, 0, 0, 0, this.xSize, this.ySize, this.ySize, this.xSize);
		super.render(mouseX, mouseY, partialTicks);
	}

	public static ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.character");
	}

	public List<Widget> getButtons() {
		return this.buttons;
	}
	
	public void sendPacket(int slot1, int slot2, int slot3, int slot4) {		
		PacketHandler.INSTANCE.sendToServer(new SpellPacket(slot1, slot2, slot3, slot4, Minecraft.getInstance().player.getUniqueID(), false));
	}
	
}
