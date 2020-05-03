package com.GenZVirus.AgeOfTitans.Client.GUI.Character;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
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

	public static final ModScreen SCREEN = new ModScreen();
	public ResourceLocation BACKGROUND_TEXTURE_HUMAN = new ResourceLocation(AgeOfTitans.MOD_ID,
			"textures/gui/character_background_human.png");
	public ResourceLocation BACKGROUND_TEXTURE_REAPER = new ResourceLocation(AgeOfTitans.MOD_ID,
			"textures/gui/character_background_reaper.png");
	private int xSize = 320;
	private int ySize = 256;
	public ModButton Human;
	public ModButton Reaper;
	public ModSkillButton Test, Test2;
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
		Human.isPressed = true;
		Human.active = false;
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
		
		this.slot1 = new ModSkillSlot((this.width - this.xSize) / 2 + 4, (this.height - this.ySize) / 2 + this.ySize - 4 - 25, 25, 25, I18n.format(""), 1) {
			@Override
			public void onPress() {
				super.onPress();
			}
		};
		
		this.slot2 = new ModSkillSlot((this.width - this.xSize) / 2 + 4 + 25, (this.height - this.ySize) / 2 + this.ySize- 4 - 25, 25, 25, I18n.format(""), 2) {
			@Override
			public void onPress() {
				super.onPress();
			}
		};
		
		this.slot3 = new ModSkillSlot((this.width - this.xSize) / 2 + 4 + 50, (this.height - this.ySize) / 2 + this.ySize- 4 - 25, 25, 25, I18n.format(""), 3) {
			@Override
			public void onPress() {
				super.onPress();
			}
		};
		
		this.slot4 = new ModSkillSlot((this.width - this.xSize) / 2 + 4 + 75, (this.height - this.ySize) / 2 + this.ySize - 4 - 25, 25, 25, I18n.format(""), 4) {
			@Override
			public void onPress() {
				super.onPress();
			}
		};
		
		this.Test = new ModSkillButton((this.width - this.xSize) / 2 + 4 + 156, (this.height - this.ySize) / 2 + 4 + 48, 16, 16, I18n.format(""), Spell.SPELL_LIST.get(1)) {
			@Override
			public void onPress() {
				super.onPress();
			}
		};
		
		this.Test2 = new ModSkillButton((this.width - this.xSize) / 2 + 4 + 156, (this.height - this.ySize) / 2 + 4 + 24, 16, 16, I18n.format(""), Spell.SPELL_LIST.get(2)) {
			@Override
			public void onPress() {
				super.onPress();
			}
		};
		
		buttons.clear();
		
		buttons.add(Test);
		buttons.add(Test2);
		
		buttons.add(slot1);
		buttons.add(slot2);
		buttons.add(slot3);
		buttons.add(slot4);
		
		buttons.add(Human);
		buttons.add(Reaper);
				
		
	}

	@Override
	public void tick() {
		super.tick();
	}
	
	@Override
	public void onClose() {
		sendPacket(slot1.spell.getId(), slot2.spell.getId(), slot3.spell.getId(), slot4.spell.getId());
		super.onClose();
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		
		RenderSystem.disableRescaleNormal();
		RenderSystem.disableDepthTest();
		this.renderBackground();
		if (this.Human.isPressed) {
			this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE_HUMAN);
		} else if (this.Reaper.isPressed) {
			this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE_REAPER);
		} else
			this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE_HUMAN);
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
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
		PacketHandler.INSTANCE.sendToServer(new SpellPacket(slot1, slot2, slot3, slot4, Minecraft.getInstance().player.getUniqueID()));
	}
	
}
