package com.GenZVirus.AgeOfTitans.Common.Events.Server;

import java.util.Random;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Init.ItemInit;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class PoisonousDaggerSetBonus {

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

		if(attacker.getHeldItemMainhand().getItem().equals(ItemInit.POISONOUS_TITANIUM_DAGGER.get())) {
			if(attacker.getHeldItemOffhand().getItem().equals(ItemInit.POISONOUS_TITANIUM_DAGGER.get())) {
				if(new Random().nextInt(4) == 1) {
					event.getEntityLiving().setHealth(event.getEntityLiving().getHealth() - event.getEntityLiving().getMaxHealth() * 10 / 100);
				}
				if(event.getEntityLiving().isPotionActive(Effects.POISON)) {
					int amplifier = event.getEntityLiving().getActivePotionEffect(Effects.POISON).getAmplifier();
					if(amplifier < 9) {
						amplifier++;
					}
					event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.POISON, 100, amplifier));
				} else {
					event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.POISON, 100));
				}
				
			} else {
				if(new Random().nextInt(10) == 1) {
					event.getEntityLiving().setHealth(event.getEntityLiving().getHealth() - event.getEntityLiving().getMaxHealth() * 10 / 100);
				}
				if(event.getEntityLiving().isPotionActive(Effects.POISON)) {
					int amplifier = event.getEntityLiving().getActivePotionEffect(Effects.POISON).getAmplifier();
					if(amplifier < 4) {
						amplifier++;
					}
					event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.POISON, 100, amplifier));
				} else {
					event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.POISON, 100));
				}
			}
		} else if(attacker.getHeldItemOffhand().getItem().equals(ItemInit.POISONOUS_TITANIUM_DAGGER.get())) {
			if(new Random().nextInt(10) == 1) {
				event.getEntityLiving().setHealth(event.getEntityLiving().getHealth() - event.getEntityLiving().getMaxHealth() * 10 / 100);
			}
			if(event.getEntityLiving().isPotionActive(Effects.POISON)) {
				int amplifier = event.getEntityLiving().getActivePotionEffect(Effects.POISON).getAmplifier();
				if(amplifier < 4) {
					amplifier++;
				}
				event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.POISON, 100, amplifier));
			} else {
				event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.POISON, 100));
			}
			
		}
	}


}
