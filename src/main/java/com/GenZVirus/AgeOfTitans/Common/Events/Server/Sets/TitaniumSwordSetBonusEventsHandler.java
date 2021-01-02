package com.GenZVirus.AgeOfTitans.Common.Events.Server.Sets;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Init.ItemInit;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class TitaniumSwordSetBonusEventsHandler {

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

		if(attacker.getHeldItemMainhand().getItem().equals(ItemInit.TITANIUM_SWORD.get()) && attacker.getHeldItemOffhand().getItem().equals(ItemInit.TITANIUM_SWORD.get())) {
			event.setAmount(event.getAmount() + event.getAmount() / 2);
		}
	}


}
