package com.GenZVirus.AgeOfTitans.Common.Events.Server;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Init.ItemInit;

import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class Drops {

	@SubscribeEvent
	public static void witherSkeletonDrops(LivingDeathEvent event) {
		if(event.getEntityLiving().world.isRemote) return;
		if(!(event.getEntityLiving() instanceof WitherSkeletonEntity)) return;
		event.getEntityLiving().entityDropItem(new ItemStack(ItemInit.WITHER_DUST.get()));
	}
	
}
