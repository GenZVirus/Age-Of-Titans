package com.GenZVirus.AgeOfTitans.Common.Events.Server.Sets;

import java.util.Random;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.Daggers.DaggerItem;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class TitaniumDaggerSetBonusEventsHandler {

	@SubscribeEvent
	public static void SetBonus(LivingDamageEvent event) {
		if (event.getEntityLiving() == null)
			return;
		if (event.getEntityLiving().world.isRemote)
			return;
		if (!(event.getSource().getTrueSource() instanceof LivingEntity))
			return;
		if (event.getSource().isProjectile())
			return;
		LivingEntity attacker = (LivingEntity) event.getSource().getTrueSource();

		if(attacker.getHeldItemMainhand().getItem() instanceof DaggerItem) {
			if(attacker.getHeldItemOffhand().getItem() instanceof DaggerItem) {
				if(new Random().nextInt(4) == 1) {
					event.getEntityLiving().setHealth(event.getEntityLiving().getHealth() - event.getEntityLiving().getMaxHealth() * 10 / 100);
				}
			} else if(new Random().nextInt(10) == 1) {
				event.getEntityLiving().setHealth(event.getEntityLiving().getHealth() - event.getEntityLiving().getMaxHealth() * 10 / 100);
			}
		} else if(attacker.getHeldItemOffhand().getItem() instanceof DaggerItem) {
			if(new Random().nextInt(10) == 1) {
				event.getEntityLiving().setHealth(event.getEntityLiving().getHealth() - event.getEntityLiving().getMaxHealth() * 10 / 100);
			}
		}
	}


}
