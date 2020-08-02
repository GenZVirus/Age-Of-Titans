package com.GenZVirus.AgeOfTitans.Client.Container;

import java.awt.Color;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

/**
 * GuiInventoryBasic is a simple gui that does nothing but draw a background
 * image and a line of text on the screen. everything else is handled by the
 * vanilla container code.
 *
 * The Screen is drawn in several layers, most importantly:
 *
 * Background - renderBackground() - eg a grey fill Background texture -
 * drawGuiContainerBackgroundLayer() (eg the frames for the slots) Foreground
 * layer - typically text labels
 *
 */
public class ContainerScreenBasic extends ContainerScreen<ContainerBasic> {

	public static BlockPos POS;
	public static int ID;

	public ContainerScreenBasic(ContainerBasic containerBasic, PlayerInventory playerInventory, ITextComponent title) {
		super(containerBasic, playerInventory, title);
		// Set the width and height of the gui. Should match the size of the texture!
		xSize = 502;
		ySize = 438;
	}



	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the
	 * items) Taken directly from ChestScreen
	 */
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		final float LABEL_XPOS = 8;
		final float FONT_Y_SPACING = 12;
		final float CHEST_LABEL_YPOS = ContainerBasic.TILE_INVENTORY_YPOS - FONT_Y_SPACING;
		font.drawString("Titan's Locker", LABEL_XPOS, CHEST_LABEL_YPOS, Color.darkGray.getRGB());

		final float PLAYER_INV_LABEL_YPOS = ContainerBasic.PLAYER_INVENTORY_YPOS - FONT_Y_SPACING;
		this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), LABEL_XPOS + 160,
				PLAYER_INV_LABEL_YPOS, Color.darkGray.getRGB());
	}

	/**
	 * Draws the background layer of this container (behind the items). Taken
	 * directly from ChestScreen / BeaconScreen
	 */
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);

		// width and height are the size provided to the window when initialised after
		// creation.
		// xSize, ySize are the expected size of the texture-? usually seems to be left
		// as a default.
		// The code below is typical for vanilla containers, so I've just copied that-
		// it appears to centre the texture within
		// the available window
		int edgeSpacingX = (this.width - this.xSize) / 2;
		int edgeSpacingY = (this.height - this.ySize) / 2;
		AbstractGui.blit(edgeSpacingX, edgeSpacingY, 0, 0, 0, this.xSize, this.ySize, this.ySize, this.xSize);
	}
	
	// This is the resource location for the background image for the GUI
	private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(AgeOfTitans.MOD_ID,
			"textures/gui/orb_of_storage.png");
}
