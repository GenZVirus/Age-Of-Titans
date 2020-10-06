package com.GenZVirus.AgeOfTitans.Client.GUI.HUD;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.ModCompatibility.VampirismComp;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.CombatRules;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.fml.ModList;

@OnlyIn(Dist.CLIENT)
public class BetterOverlay {

	private static Minecraft mc = Minecraft.getInstance();
	private static int delay = 0;
	public static ResourceLocation BAR_LEFT = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/betteroverlay/bar_left.png");
	public static ResourceLocation BAR_RIGHT = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/betteroverlay/bar_right.png");
	public static ResourceLocation EXP_BAR = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/betteroverlay/exp_bar.png");
	public static ResourceLocation EXP_BAR_FILL = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/betteroverlay/exp_bar_fill.png");
	public static ResourceLocation HEALTH_BAR_FILL = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/betteroverlay/health_bar_fill.png");
	public static ResourceLocation POISONED_HEALTH_BAR_FILL = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/betteroverlay/poisoned_health_bar_fill.png");
	public static ResourceLocation WITHERING_HEALTH_BAR_FILL = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/betteroverlay/withering_health_bar_fill.png");
	public static ResourceLocation FOOD_BAR_FILL = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/betteroverlay/food_bar_fill.png");
	public static ResourceLocation SATURATION_BAR_FILL = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/betteroverlay/saturation_bar_fill.png");
	public static ResourceLocation SHIELD_BAR = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/betteroverlay/shield_bar.png");
	public static ResourceLocation ARMOR_LEFT = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/betteroverlay/shield_armor_left.png");
	public static ResourceLocation ARMOR_RIGHT = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/betteroverlay/shield_armor_right.png");
	public static ResourceLocation FIRE = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/betteroverlay/fire.png");
	public static ResourceLocation WATER_BREATHING_BAR = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/betteroverlay/water_breathing_bar.png");
	public static ResourceLocation WATER_BREATHING_BAR_FILL = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/betteroverlay/water_breathing_bar_fill.png");
	public static ResourceLocation BLOOD_BAR_FILL = new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/betteroverlay/blood_bar_fill.png");
	public static int fire_offset = 0;

	private static void checkInGame() {
		if (mc.world == null) { return; }

		assert mc.player != null;

		if (!Minecraft.isGuiEnabled()) { return; }
	}

	private static void incrementOverlayMovement() {
		if (Minecraft.debugFPS / 10 <= delay) {
			fire_offset++;
			delay = 0;
		}
		delay++;

		if (fire_offset > 31) {
			fire_offset = 0;
		}
	}

	public static void renderHealth() {
		checkInGame();
		incrementOverlayMovement();
		if (mc.player.isCreative()) { return; }

		mc.getProfiler().startSection("BUXHealth");
		RenderSystem.scalef(1.0F, 1.0F, 1.0F);
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		int posX;
		int posY;

		// Health Bar

		RenderSystem.enableBlend();
		posY = mc.getMainWindow().getScaledHeight() - ForgeIngameGui.left_height - 11 - (ModHUD.locked ? 0 : 17);
		posX = mc.getMainWindow().getScaledWidth() / 2 - 91;
		mc.getTextureManager().bindTexture(BAR_LEFT);
		AbstractGui.blit(posX, posY, 0, 0, 0, 90, 10, 10, 90);
		int percentage = (int) (88 * mc.player.getHealth() / mc.player.getMaxHealth());
		if (percentage > 88)
			percentage = 88;
		mc.getTextureManager().bindTexture(HEALTH_BAR_FILL);
		if (mc.player.isPotionActive(Effects.POISON)) {
			mc.getTextureManager().bindTexture(POISONED_HEALTH_BAR_FILL);
		}
		if (mc.player.isPotionActive(Effects.WITHER)) {
			mc.getTextureManager().bindTexture(WITHERING_HEALTH_BAR_FILL);
		}
		AbstractGui.blit(posX + 1, posY + 1, 0, 0, 0, percentage, 8, 8, 88);
		if (mc.player.getAbsorptionAmount() > 0) {
			mc.getTextureManager().bindTexture(SHIELD_BAR);
			AbstractGui.blit(posX, posY, 0, 0, 0, 90, 10, 10, 90);
		}

		if (mc.player.isBurning()) {
			mc.getTextureManager().bindTexture(FIRE);
			AbstractGui.blit(posX - 9, posY - 32, 0, 0, fire_offset * 32, 200, 32, 5792, 200);
		}
		RenderSystem.disableBlend();

		// Health Text

		String health = Integer.toString((int) mc.player.getHealth()) + " / " + Integer.toString((int) mc.player.getMaxHealth());
		String swidth = Integer.toString((int) mc.player.getMaxHealth()) + " / " + Integer.toString((int) mc.player.getMaxHealth());
		if (mc.player.getAbsorptionAmount() > 0) {
			health += " | " + Integer.toString((int) mc.player.getAbsorptionAmount());
			swidth += " | " + Integer.toString((int) mc.player.getAbsorptionAmount());
		}
		int stringWidth = mc.fontRenderer.getStringWidth(swidth);
		int offset = mc.fontRenderer.getStringWidth(health);
		mc.fontRenderer.drawString(health, posX + 45 - stringWidth / 2 + (stringWidth - offset), posY + 1, 0xFFFFFFFF);
		mc.getProfiler().endSection();
	}

	public static void renderFood() {
		checkInGame();
		if (mc.player.isCreative()) { return; }

		mc.getProfiler().startSection("BUXFood");
		mc.getTextureManager().bindTexture(Screen.GUI_ICONS_LOCATION);
		RenderSystem.enableBlend();
		int posY = mc.getMainWindow().getScaledHeight() - ForgeIngameGui.left_height - 11 - (ModHUD.locked ? 0 : 17);
		int posX = mc.getMainWindow().getScaledWidth() / 2 + 91;
		mc.getTextureManager().bindTexture(BAR_RIGHT);
		AbstractGui.blit(posX - 90, posY, 0, 0, 0, 90, 10, 10, 90);
		if (ModList.get().isLoaded("vampirism")) {
			VampirismComp.bloodOverlay();
		} else {
			FoodStats stats = mc.player.getFoodStats();
			int level = stats.getFoodLevel();
			int maxLevel = 20;
			int percentage = (int) (88 * level / maxLevel);
			if (percentage > 88)
				percentage = 88;
			mc.getTextureManager().bindTexture(FOOD_BAR_FILL);
			AbstractGui.blit(posX - 1, posY + 1, 0, 0, 0, -percentage, 8, 8, 88);
			level = (int) stats.getSaturationLevel();
			maxLevel = 20;
			percentage = (int) (88 * level / maxLevel);
			if (percentage > 88)
				percentage = 88;
			mc.getTextureManager().bindTexture(SATURATION_BAR_FILL);
			AbstractGui.blit(posX - 1, posY + 1, 0, 0, 0, -percentage, 8, 8, 88);
			String food = ((int) stats.getFoodLevel()) + " | " + ((int) stats.getSaturationLevel());
			int stringWidth = mc.fontRenderer.getStringWidth(food);
			mc.fontRenderer.drawString(food, posX - 45 - stringWidth / 2, posY + 1, 0xFFFFFFFF);
			RenderSystem.disableBlend();
			mc.getProfiler().endSection();
		}
	}

	public static void renderArmor() {
		checkInGame();
		if (mc.player.isCreative()) { return; }
		if (mc.player.getTotalArmorValue() <= 0)
			return;

		mc.getProfiler().startSection("BUXArmor");
		mc.getTextureManager().bindTexture(ARMOR_LEFT);
		RenderSystem.enableBlend();
		int posX = mc.getMainWindow().getScaledWidth() / 2 - 91;
		int posY = mc.getMainWindow().getScaledHeight() - ForgeIngameGui.left_height - 11;

		float totalArmor = mc.player.getTotalArmorValue();
		float toughness = (float) mc.player.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getValue();
		RenderSystem.scalef(0.5F, 0.5F, 0.5F);
		AbstractGui.blit((posX - 33) * 2, posY * 2, 0, 0, 0, 64, 64, 64, 64);
		mc.getTextureManager().bindTexture(ARMOR_RIGHT);
		AbstractGui.blit((posX + 183) * 2, posY * 2, 0, 0, 0, 64, 64, 64, 64);
		RenderSystem.scalef(2.0F, 2.0F, 2.0F);
		float damageReduction = Math.round((100.0F - CombatRules.getDamageAfterAbsorb(100, totalArmor, toughness)) * 10) / 10.0F;
		for (ItemStack stack : mc.player.getArmorInventoryList()) {
			damageReduction += EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION, stack);
		}
		if (damageReduction > 100)
			damageReduction = 100;
		String s = damageReduction + "%";
		mc.fontRenderer.drawString(s, posX - 16 - mc.fontRenderer.getStringWidth(s) / 2, posY + 10, 0xFFFFFFFF);
		damageReduction = Math.round(4.0 * totalArmor * 10) / 10;
		for (ItemStack stack : mc.player.getArmorInventoryList()) {
			damageReduction += EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION, stack);
		}
		if (damageReduction > 100)
			damageReduction = 100;
		s = damageReduction + "%";
		mc.fontRenderer.drawString(s, posX + 200 - mc.fontRenderer.getStringWidth(s) / 2, posY + 10, 0xFFFFFFFF);
		RenderSystem.disableBlend();
		mc.getProfiler().endSection();
	}

	public static void renderExpBar() {
		checkInGame();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getProfiler().startSection("BUXExpBar");
		mc.getTextureManager().bindTexture(EXP_BAR);
		RenderSystem.enableBlend();
		int k = (int) (mc.player.experience * 180.0F);
		int posX = mc.getMainWindow().getScaledWidth() / 2 - 91;
		int posY = mc.getMainWindow().getScaledHeight() - ForgeIngameGui.left_height;
		AbstractGui.blit(posX, posY, 0, 0, 0, 182, 16, 16, 182);
		if (k > 0) {
			mc.getTextureManager().bindTexture(EXP_BAR_FILL);
			AbstractGui.blit(posX + 1, posY + 1, 0, 0, 0, k, 14, 14, 180);
		}
		mc.getProfiler().endSection();
		RenderSystem.enableBlend();
		if (mc.player.experienceLevel > 0) {
			mc.getProfiler().startSection("BUXExpLevel");
			String s = "" + mc.player.experienceLevel;
			RenderSystem.scalef(0.8F, 0.8F, 0.8F);
			mc.fontRenderer.drawString(s, (posX + 93 - mc.fontRenderer.getStringWidth(s) / 2) * 1.25F, (posY + 5) * 1.25F, 0xFFFFFFFF);
			RenderSystem.scalef(1.25F, 1.25F, 1.25F);
			mc.getProfiler().endSection();
		}
		RenderSystem.disableBlend();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	public static void renderWaterBreathing() {
		checkInGame();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		if (mc.player.areEyesInFluid(FluidTags.WATER) || mc.player.getAir() < mc.player.getMaxAir()) {
			mc.getProfiler().startSection("BUXWaterBreathingBar");
			mc.getTextureManager().bindTexture(WATER_BREATHING_BAR);
			RenderSystem.enableBlend();
			int k = (int) (((float) mc.player.getAir() / mc.player.getMaxAir()) * 180.0F);
			int posX = mc.getMainWindow().getScaledWidth() / 2 - 91;
			int posY = mc.getMainWindow().getScaledHeight() - ForgeIngameGui.left_height - 25 - (ModHUD.locked ? 0 : 17);
			AbstractGui.blit(posX, posY, 0, 0, 0, 182, 16, 16, 182);
			if (k > 0) {
				mc.getTextureManager().bindTexture(WATER_BREATHING_BAR_FILL);
				AbstractGui.blit(posX + 1, posY + 1, 0, 0, 0, k, 14, 14, 180);
			}
			RenderSystem.disableBlend();
			mc.getProfiler().endSection();
			RenderSystem.enableBlend();
		}
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

}
