package com.GenZVirus.AgeOfTitans.Common.Events.Server.Sets;

import java.util.Random;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Events.Server.PlayerEventsHandler;
import com.GenZVirus.AgeOfTitans.Common.Init.EffectInit;
import com.GenZVirus.AgeOfTitans.Common.Init.ItemInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class LightTitaniumArmorSetBonusEventsHandler {

	@SubscribeEvent
	public static void SetBonus(ServerTickEvent event) {
		if (PlayerEventsHandler.players.isEmpty())
			return;
		for (PlayerEntity player : PlayerEventsHandler.players) {
			if (!checkIfFullSet(player)) {
				if (player.isPotionActive(EffectInit.LIGHT_TITANIUM_SET.get())) {
					player.removePotionEffect(EffectInit.LIGHT_TITANIUM_SET.get());
				}
				return;
			}
			player.addPotionEffect(new EffectInstance(EffectInit.LIGHT_TITANIUM_SET.get(), 120));
		}
	}
	
	@SubscribeEvent(receiveCanceled = true)
	public static void DodgeBonus(LivingAttackEvent event) {
		if (event.getEntityLiving() == null)
			return;
		if (event.getEntityLiving().world.isRemote)
			return;
		if(event.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			if(checkIfFullSet(player)) {
				Random rand = new Random();
				if(rand.nextInt(3) == 0) {
					event.setCanceled(true);
				}
			}
		}
	}
	
	private static boolean checkIfFullSet(PlayerEntity player) {
		Iterable<ItemStack> armory = player.getArmorInventoryList();
		for (ItemStack stack : armory) {
			if (stack.isEmpty()) {
				return false;
			}
			if (!stack.getItem().equals(ItemInit.LIGHT_TITANIUM_HOOD.get()) && !stack.getItem().equals(ItemInit.LIGHT_TITANIUM_CHESTPLATE.get()) && !stack.getItem().equals(ItemInit.LIGHT_TITANIUM_LEGGINGS.get()) && !stack.getItem().equals(ItemInit.LIGHT_TITANIUM_BOOTS.get())) {
				return false;
			}
		}
		return true;
	}
	
}
