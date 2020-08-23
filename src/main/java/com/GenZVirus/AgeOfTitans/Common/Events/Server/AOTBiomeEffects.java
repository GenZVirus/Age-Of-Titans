package com.GenZVirus.AgeOfTitans.Common.Events.Server;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Init.BiomeInit;
import com.GenZVirus.AgeOfTitans.Common.Init.EffectInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class AOTBiomeEffects {
	@SubscribeEvent
	public static void AOTPlayerInsideBiome(PlayerEvent event) {
		PlayerEntity player = event.getPlayer();
		if (player == null)
			return;
		Biome biome = player.world.getBiome(player.getPosition());

		// Active effects

		if (player.getHealth() < player.getMaxHealth() && player.isPotionActive(EffectInit.HOLY_PLAINS.get())) {
			player.heal(1.0F);
		}

		// Add effects

		if (biome.equals(BiomeInit.HOLY_GROUND_PLAINS.get()) && !player.isPotionActive(EffectInit.HOLY_PLAINS.get())) {
			removeAllBiomeEffects(player);
			player.addPotionEffect(new EffectInstance(EffectInit.HOLY_PLAINS.get(), 100));
		}

		if (biome.equals(BiomeInit.HOLY_GROUND_HILLS.get()) && !player.isPotionActive(EffectInit.HOLY_HILLS.get())) {
			removeAllBiomeEffects(player);
			player.addPotionEffect(new EffectInstance(EffectInit.HOLY_HILLS.get(), 100));
		}

		if (biome.equals(BiomeInit.HOLY_GROUND_MOUNTAIN.get()) && !player.isPotionActive(EffectInit.HOLY_MOUNTAINS.get())) {
			removeAllBiomeEffects(player);
			player.addPotionEffect(new EffectInstance(EffectInit.HOLY_MOUNTAINS.get(), 100));
		}
	}

	private static void removeAllBiomeEffects(PlayerEntity player) {

		if (player.isPotionActive(EffectInit.HOLY_PLAINS.get()))
			player.removePotionEffect(EffectInit.HOLY_PLAINS.get());

		if (player.isPotionActive(EffectInit.HOLY_HILLS.get()))
			player.removePotionEffect(EffectInit.HOLY_HILLS.get());

		if (player.isPotionActive(EffectInit.HOLY_MOUNTAINS.get()))
			player.removePotionEffect(EffectInit.HOLY_MOUNTAINS.get());
	}
}
