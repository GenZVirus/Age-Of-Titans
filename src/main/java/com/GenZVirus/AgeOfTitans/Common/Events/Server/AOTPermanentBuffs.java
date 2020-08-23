package com.GenZVirus.AgeOfTitans.Common.Events.Server;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Init.EffectInit;
import com.GenZVirus.AgeOfTitans.SpellSystem.XMLFileJava;
import com.GenZVirus.AgeOfTitans.Util.ForgeEventBusSubscriber;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.SpecialSpawn;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class AOTPermanentBuffs {

	@SubscribeEvent
	public static void AOTAnimalTamedBuff(AnimalTameEvent event) {
		LivingEntity entity = event.getEntityLiving();
		int level = ForgeEventBusSubscriber.AvarageLevel / 10;
		Effect effect;
		int difficulty = entity.world.getDifficulty().getId();
		level = Integer.parseInt(XMLFileJava.readElement(event.getTamer().getUniqueID(), "PlayerLevel")) / 10;
		if (difficulty == 0 || difficulty == 1) {
			effect = EffectInit.TAMEABLE_EASY.get();
		} else if (difficulty == 2) {
			effect = EffectInit.TAMEABLE_NORMAL.get();
		} else {
			effect = EffectInit.TAMEABLE_HARD.get();
		}
		entity.addPotionEffect(new EffectInstance(effect, Integer.MAX_VALUE - 1, level));
		entity.setHealth(entity.getMaxHealth());
	}
	
	@SubscribeEvent
	public static void AOTMonsterBuff(SpecialSpawn event) {
		LivingEntity entity = event.getEntityLiving();
		if (entity == null)
			return;
		if (entity.world.isRemote)
			return;
		if (entity instanceof PlayerEntity)
			return;
		if (ForgeEventBusSubscriber.players.isEmpty())
			return;
		if (entity instanceof TameableEntity) {
			if (((TameableEntity) entity).getOwnerId() != null)
				return;
		}
		int level = ForgeEventBusSubscriber.AvarageLevel / 10;
		Effect effect;
		int difficulty = entity.world.getDifficulty().getId();
		if (difficulty == 0 || difficulty == 1) {
			effect = EffectInit.MONSTER_EASY.get();
		} else if (difficulty == 2) {
			effect = EffectInit.MONSTER_NORMAL.get();
		} else {
			effect = EffectInit.MONSTER_HARD.get();
		}
			entity.addPotionEffect(new EffectInstance(effect, Integer.MAX_VALUE - 1, level));
			entity.setHealth(entity.getMaxHealth());
	}

	@SubscribeEvent
	public static void AOTPetBuff(SpecialSpawn event) {
		LivingEntity entity = event.getEntityLiving();
		if (entity == null)
			return;
		if (entity.world.isRemote)
			return;
		if (entity instanceof PlayerEntity)
			return;
		if (ForgeEventBusSubscriber.players.isEmpty())
			return;
		if (!(entity instanceof TameableEntity))
			return;
		int level = ForgeEventBusSubscriber.AvarageLevel / 10;
		Effect effect;
		int difficulty = entity.world.getDifficulty().getId();

		if (((TameableEntity) entity).getOwnerId() != null) {
			level = Integer.parseInt(XMLFileJava.readElement(((TameableEntity) entity).getOwnerId(), "PlayerLevel")) / 10;
			if (difficulty == 0 || difficulty == 1) {
				effect = EffectInit.TAMEABLE_EASY.get();
			} else if (difficulty == 2) {
				effect = EffectInit.TAMEABLE_NORMAL.get();
			} else {
				effect = EffectInit.TAMEABLE_HARD.get();
			}
		} else {
			if (difficulty == 0 || difficulty == 1) {
				effect = EffectInit.MONSTER_EASY.get();
			} else if (difficulty == 2) {
				effect = EffectInit.MONSTER_NORMAL.get();
			} else {
				effect = EffectInit.MONSTER_HARD.get();
			}
		}
			entity.addPotionEffect(new EffectInstance(effect, Integer.MAX_VALUE - 1, level));
			entity.setHealth(entity.getMaxHealth());
	}

	@SubscribeEvent
	public static void AOTTitanBuff(PlayerEvent event) {
		if (event.getPlayer() == null)
			return;
		if (event.getPlayer().world.isRemote)
			return;
		PlayerEntity player = event.getPlayer();
		if (player instanceof ServerPlayerEntity) {
			if (((ServerPlayerEntity) player).connection == null)
				return;
		}
		if (!ForgeEventBusSubscriber.players.contains(player))
			return;
//		XMLFileJava.checkFileAndMake(player.getUniqueID(), player.getName().getFormattedText());
		if (Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "ApplesEaten")) <= 0)
			return;
		int playerLevel = Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerLevel"));
		if (player.isPotionActive(EffectInit.TITAN.get())) {
			if (player.getActivePotionEffect(EffectInit.TITAN.get()).getDuration() <= 100 || player.getActivePotionEffect(EffectInit.TITAN.get()).getAmplifier() < (int) (playerLevel / 10)) {
				player.addPotionEffect(new EffectInstance(EffectInit.TITAN.get(), 200, playerLevel / 10));
			}
		} else {
			player.addPotionEffect(new EffectInstance(EffectInit.TITAN.get(), 200, playerLevel / 10));
		}
	}
	
}
