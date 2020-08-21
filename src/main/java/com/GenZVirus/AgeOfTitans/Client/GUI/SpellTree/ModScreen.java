package com.GenZVirus.AgeOfTitans.Client.GUI.SpellTree;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Events.KeyPressedEvent;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandlerCommon;
import com.GenZVirus.AgeOfTitans.Common.Network.SpellPacket;
import com.GenZVirus.AgeOfTitans.SpellSystem.Spell;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
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

	public ResourceLocation BACKGROUND_TEXTURE_TITAN = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/titan_ability_tree.png");

	public ResourceLocation LEVEL_REQUIREMENT = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/level_requirement.png");

	public ResourceLocation CURRENT_LEVEL = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/current_level.png");

	// The size of the GUI

	public int xSize = 320;
	public int ySize = 256;

	public int windowXPos = (this.width - this.xSize) / 2;
	public int windowYPos = (this.height - this.ySize) / 2;

	// The buttons for changing the skill tree
	
	public ModButton activePage;
	public ModButton Active;
	public ModButton Passive;

	// The button for learning spells

	public ModButton LearnSpells;

	// The button for spells in the skill tree

	public ModSkillButton SwordSlash, ShieldBash, Berserker, Chain, TimeBomb, Revitalize;
	public ModSkillButton NoSpell1, NoSpell2, NoSpell3, NoSpell4, NoSpell5, NoSpell6, NoSpell7, NoSpell8, NoSpell9, NoSpell10, NoSpell11, NoSpell12, NoSpell13, NoSpell14, NoSpell15, NoSpell16, NoSpell17, NoSpell18, NoSpell19, NoSpell20, NoSpell21;

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
		this.windowXPos = (this.width - this.xSize) / 2;
		this.windowYPos = (this.height - this.ySize) / 2;

		// If you press on the human button the background changes to the Human one

		this.Active = new ModButton(windowXPos + 4, windowYPos + 4, 156, 20, I18n.format("gui.active")) {
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
					
					if (button instanceof ModADDButton) {
						((ModADDButton) button).isActive = false;
					}

					if (button instanceof ModSubtractButton) {
						((ModSubtractButton) button).isActive = false;
					}
					
				}							
				super.onPress();
			}
		};

		// By default human background is selected

		this.Active.isPressed = true;
		this.Active.active = false;
		
		this.activePage = this.Active;

		// If you press on the human button the background changes to the Human one

		this.Passive = new ModButton(windowXPos + 4 + 156, windowYPos + 4, 156, 20, I18n.format("gui.passive")) {
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
					
					if (button instanceof ModADDButton) {
						((ModADDButton) button).isActive = false;
					}

					if (button instanceof ModSubtractButton) {
						((ModSubtractButton) button).isActive = false;
					}
					
				}				
				super.onPress();
			}
		};

		// The four spell slots

		this.slot1 = new ModSkillSlot(windowXPos + 4, windowYPos + this.ySize - 4 - 25, 25, 25, I18n.format(""), 1);
		this.slot2 = new ModSkillSlot(windowXPos + 4 + 25, windowYPos + this.ySize - 4 - 25, 25, 25, I18n.format(""), 2);
		this.slot3 = new ModSkillSlot(windowXPos + 4 + 50, windowYPos + this.ySize - 4 - 25, 25, 25, I18n.format(""), 3);
		this.slot4 = new ModSkillSlot(windowXPos + 4 + 75, windowYPos + this.ySize - 4 - 25, 25, 25, I18n.format(""), 4);

		// The spell buttons

		List<Integer> four_elements_row = Lists.newArrayList(40, 100, 160, 220);
		List<Integer> three_elements_row = Lists.newArrayList(70, 136, 202);
		List<Integer> rows = Lists.newArrayList(35, 60, 90, 120, 150, 180);

		this.NoSpell1 = new ModSkillButton(windowXPos + 4 + four_elements_row.get(0), windowYPos + 4 + rows.get(0), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell2 = new ModSkillButton(windowXPos + 4 + four_elements_row.get(1), windowYPos + 4 + rows.get(0), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell3 = new ModSkillButton(windowXPos + 4 + four_elements_row.get(2), windowYPos + 4 + rows.get(0), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell4 = new ModSkillButton(windowXPos + 4 + four_elements_row.get(3), windowYPos + 4 + rows.get(0), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell5 = new ModSkillButton(windowXPos + 4 + three_elements_row.get(0), windowYPos + 4 + rows.get(1), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell6 = new ModSkillButton(windowXPos + 4 + three_elements_row.get(1), windowYPos + 4 + rows.get(1), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell7 = new ModSkillButton(windowXPos + 4 + three_elements_row.get(2), windowYPos + 4 + rows.get(1), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell8 = new ModSkillButton(windowXPos + 4 + four_elements_row.get(0), windowYPos + 4 + rows.get(2), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell9 = new ModSkillButton(windowXPos + 4 + four_elements_row.get(1), windowYPos + 4 + rows.get(2), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell10 = new ModSkillButton(windowXPos + 4 + four_elements_row.get(2), windowYPos + 4 + rows.get(2), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell11 = new ModSkillButton(windowXPos + 4 + four_elements_row.get(3), windowYPos + 4 + rows.get(2), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell12 = new ModSkillButton(windowXPos + 4 + three_elements_row.get(0), windowYPos + 4 + rows.get(3), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell13 = new ModSkillButton(windowXPos + 4 + three_elements_row.get(1), windowYPos + 4 + rows.get(3), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell14 = new ModSkillButton(windowXPos + 4 + three_elements_row.get(2), windowYPos + 4 + rows.get(3), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell15 = new ModSkillButton(windowXPos + 4 + four_elements_row.get(0), windowYPos + 4 + rows.get(4), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell16 = new ModSkillButton(windowXPos + 4 + four_elements_row.get(1), windowYPos + 4 + rows.get(4), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell17 = new ModSkillButton(windowXPos + 4 + four_elements_row.get(2), windowYPos + 4 + rows.get(4), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell18 = new ModSkillButton(windowXPos + 4 + four_elements_row.get(3), windowYPos + 4 + rows.get(4), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell19 = new ModSkillButton(windowXPos + 4 + three_elements_row.get(0), windowYPos + 4 + rows.get(5), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell20 = new ModSkillButton(windowXPos + 4 + three_elements_row.get(1), windowYPos + 4 + rows.get(5), 20, 20, "", Spell.SPELL_LIST.get(0));
		this.NoSpell21 = new ModSkillButton(windowXPos + 4 + three_elements_row.get(2), windowYPos + 4 + rows.get(5), 20, 20, "", Spell.SPELL_LIST.get(0));

		this.SwordSlash = new ModSkillButton(windowXPos + 4 + four_elements_row.get(0), windowYPos + 4 + rows.get(0), 20, 20, "", Spell.SPELL_LIST.get(1));
		this.ShieldBash = new ModSkillButton(windowXPos + 4 + three_elements_row.get(0), windowYPos + 4 + rows.get(1), 20, 20, "", Spell.SPELL_LIST.get(2));
		this.Berserker = new ModSkillButton(windowXPos + 4 + four_elements_row.get(0), windowYPos + 4 + rows.get(2), 20, 20, "", Spell.SPELL_LIST.get(3));
		this.Chain = new ModSkillButton(windowXPos + 4 + three_elements_row.get(1), windowYPos + 4 + rows.get(1), 20, 20, "", Spell.SPELL_LIST.get(4));
		this.TimeBomb = new ModSkillButton(windowXPos + 4 + three_elements_row.get(2), windowYPos + 4 + rows.get(1), 20, 20, "", Spell.SPELL_LIST.get(5));
		this.Revitalize = new ModSkillButton(windowXPos + 4 + four_elements_row.get(1), windowYPos + 4 + rows.get(0), 20, 20, "", Spell.SPELL_LIST.get(6));

		this.LearnSpells = new ModButton(windowXPos + 4 + 156, windowYPos + this.ySize - 4 - 20, 156, 20, I18n.format("gui.learn_spells")) {
			@Override
			public void onPress() {
				if (!isPressed) {
					isPressed = true;
					for (Widget button : buttons) {
						if (button instanceof ModADDButton) {
							if (((ModADDButton) button).spell.meetsRequirements()) {
								((ModADDButton) button).isActive = true;
							}
						}

						if (button instanceof ModSubtractButton) {
							if (((ModSubtractButton) button).spell.meetsRequirements()) {
								((ModSubtractButton) button).isActive = true;
							}
						}
					}

				} else {
					isPressed = false;
					for (Widget button : buttons) {
						if (button instanceof ModADDButton) {
							((ModADDButton) button).isActive = false;
						}

						if (button instanceof ModSubtractButton) {
							((ModSubtractButton) button).isActive = false;
						}
					}
				}
				this.playDownSound(Minecraft.getInstance().getSoundHandler());
			}
		};

		this.addButtons();

	}

	public void addButtons() {
		this.buttons.clear();

		this.buttons.add(slot1);
		this.buttons.add(slot2);
		this.buttons.add(slot3);
		this.buttons.add(slot4);

		this.loadSpells();

		this.buttons.add(Active);
		this.buttons.add(Passive);

		this.buttons.add(LearnSpells);
	}

	public void loadSpells() {

		if (this.Active.isPressed) {
			this.buttons.add(Berserker.add);
			this.buttons.add(Berserker.subtract);
			this.buttons.add(ShieldBash.add);
			this.buttons.add(ShieldBash.subtract);
			this.buttons.add(Chain.add);
			this.buttons.add(Chain.subtract);
			this.buttons.add(TimeBomb.add);
			this.buttons.add(TimeBomb.subtract);
			this.buttons.add(Revitalize.add);
			this.buttons.add(Revitalize.subtract);
			this.buttons.add(SwordSlash.add);
			this.buttons.add(SwordSlash.subtract);

			this.buttons.add(NoSpell3);
			this.buttons.add(NoSpell4);
			this.buttons.add(NoSpell9);
			this.buttons.add(NoSpell10);
			this.buttons.add(NoSpell11);
			this.buttons.add(NoSpell12);
			this.buttons.add(NoSpell13);
			this.buttons.add(NoSpell14);
			this.buttons.add(NoSpell15);
			this.buttons.add(NoSpell16);
			this.buttons.add(NoSpell17);
			this.buttons.add(NoSpell18);
			this.buttons.add(NoSpell19);
			this.buttons.add(NoSpell20);
			this.buttons.add(NoSpell21);

			this.buttons.add(Berserker);
			this.buttons.add(ShieldBash);
			this.buttons.add(Chain);
			this.buttons.add(TimeBomb);
			this.buttons.add(Revitalize);
			this.buttons.add(SwordSlash);
		} else {

			this.buttons.add(NoSpell1);
			this.buttons.add(NoSpell2);
			this.buttons.add(NoSpell3);
			this.buttons.add(NoSpell4);
			this.buttons.add(NoSpell5);
			this.buttons.add(NoSpell6);
			this.buttons.add(NoSpell7);
			this.buttons.add(NoSpell8);
			this.buttons.add(NoSpell9);
			this.buttons.add(NoSpell10);
			this.buttons.add(NoSpell11);
			this.buttons.add(NoSpell12);
			this.buttons.add(NoSpell13);
			this.buttons.add(NoSpell14);
			this.buttons.add(NoSpell15);
			this.buttons.add(NoSpell16);
			this.buttons.add(NoSpell17);
			this.buttons.add(NoSpell18);
			this.buttons.add(NoSpell19);
			this.buttons.add(NoSpell20);
			this.buttons.add(NoSpell21);
		}
	}

	@Override
	public void tick() {
		for (int i = 1; i < Spell.SPELL_LIST.size(); i++) {
			if (Spell.SPELL_LIST.get(i).level == 0) {
				if (ModScreen.SCREEN.slot1.spell.getId() == Spell.SPELL_LIST.get(i).getId()) {
					ModScreen.SCREEN.slot1.spell = Spell.SPELL_LIST.get(0);
				}
				if (ModScreen.SCREEN.slot2.spell.getId() == Spell.SPELL_LIST.get(i).getId()) {
					ModScreen.SCREEN.slot2.spell = Spell.SPELL_LIST.get(0);
				}
				if (ModScreen.SCREEN.slot3.spell.getId() == Spell.SPELL_LIST.get(i).getId()) {
					ModScreen.SCREEN.slot3.spell = Spell.SPELL_LIST.get(0);
				}
				if (ModScreen.SCREEN.slot4.spell.getId() == Spell.SPELL_LIST.get(i).getId()) {
					ModScreen.SCREEN.slot4.spell = Spell.SPELL_LIST.get(0);
				}
			}
		}
		
		if(activePage == Passive && Active.isPressed) {
			activePage = Active;
			addButtons();
		}
		
		if(activePage == Active && Passive.isPressed) {
			activePage = Passive;
			addButtons();
		}
		
		
		super.tick();
	}

	@Override
	public void onClose() {

		// When the keyPressedEvent fires and the screen is already opened it sets
		// wasPRESSED on false to not close other existing screens like chat screen

		KeyPressedEvent.wasPRESSED = false;

		// Sends a packet to the server to save to the custom playerdata file

		sendPacket(slot1.spell.getId(), slot2.spell.getId(), slot3.spell.getId(), slot4.spell.getId());
		super.onClose();
	}

	// Screen renderer

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.windowXPos = (this.width - this.xSize) / 2;
		this.windowYPos = (this.height - this.ySize) / 2;
		this.renderBackground();

		this.minecraft.getTextureManager().bindTexture(this.BACKGROUND_TEXTURE_TITAN);

		/*
		 * First param is the X position Second param is the y position Third param is
		 * the blit offset Fourth param is the X position from where it starts to
		 * display pixels in the texture image Fifth param is the Y position from where
		 * it starts to display pixels in the texture image Sixth param is the Width of
		 * the image that we want to display Seventh param is the Height of the image
		 * that we want to display Eighth param is the Height of the texture image Ninth
		 * param is the Width of the texture image
		 */

		// Add background

		AbstractGui.blit(this.windowXPos, this.windowYPos, 0, 0, 0, this.xSize, this.ySize, this.ySize, this.xSize);

		// Add level bar

		GL11.glScalef(0.8F, 0.8F, 0.8F);

		this.minecraft.getTextureManager().bindTexture(this.LEVEL_REQUIREMENT);
		AbstractGui.blit(((int) ((this.windowXPos + 20) * 1.25)), ((int) ((this.windowYPos + 40) * 1.25)), 0, 0, 0, 26, 202, 202, 26);

		this.minecraft.getTextureManager().bindTexture(this.CURRENT_LEVEL);
		int level = Spell.PLAYER_LEVEL;
		if (level > 100)
			level = 100;
		level += 10;
		AbstractGui.blit(((int) ((this.windowXPos + 20) * 1.25)), ((int) ((this.windowYPos + 40) * 1.25)), 0, 0, 0, 26, 202 * level / 110, 202, 26);

		final int zLevel = 300;
		IRenderTypeBuffer.Impl renderType = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());
		MatrixStack textStack = new MatrixStack();
		textStack.translate(0.0D, 0.0D, (double) zLevel);
		Matrix4f textLocation = textStack.getLast().getMatrix();
		font.renderString("0", ((this.windowXPos + 28) * 1.25F), ((this.windowYPos + 44) * 1.25F), 0xFFFFFFFF, true, textLocation, renderType, false, 0, 15728880);
		font.renderString("20", ((this.windowXPos + 26) * 1.25F), ((this.windowYPos + 73) * 1.25F), 0xFFFFFFFF, true, textLocation, renderType, false, 0, 15728880);
		font.renderString("40", ((this.windowXPos + 26) * 1.25F), ((this.windowYPos + 102) * 1.25F), 0xFFFFFFFF, true, textLocation, renderType, false, 0, 15728880);
		font.renderString("60", ((this.windowXPos + 26) * 1.25F), ((this.windowYPos + 130) * 1.25F), 0xFFFFFFFF, true, textLocation, renderType, false, 0, 15728880);
		font.renderString("80", ((this.windowXPos + 26) * 1.25F), ((this.windowYPos + 159) * 1.25F), 0xFFFFFFFF, true, textLocation, renderType, false, 0, 15728880);
		font.renderString("100", ((this.windowXPos + 23) * 1.25F), ((this.windowYPos + 188) * 1.25F), 0xFFFFFFFF, true, textLocation, renderType, false, 0, 15728880);
		renderType.finish();

		GL11.glScalef(1.25F, 1.25F, 1.25F);

		// Add buttons

		super.render(mouseX, mouseY, partialTicks);

		List<String> list = Lists.newArrayList();
		list.add("Points: " + Spell.POINTS);

		drawHoveringText(list, this.windowXPos + this.xSize - 4 - 60, this.windowYPos + this.ySize - 4 - 20, this.width, this.height, -1, font);

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

	public static ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.character");
	}

	public List<Widget> getButtons() {
		return this.buttons;
	}

	@SuppressWarnings("resource")
	public void sendPacket(int slot1, int slot2, int slot3, int slot4) {
		PacketHandlerCommon.INSTANCE.sendToServer(new SpellPacket(slot1, slot2, slot3, slot4, Minecraft.getInstance().player.getUniqueID(), false));
	}

}
