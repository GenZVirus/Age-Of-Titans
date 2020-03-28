package com.GenZVirus.AgeOfTitans.Events;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Init.BlockInit;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class TestJumpEvent {

	@SubscribeEvent
	public static void testJumpEvent(LivingJumpEvent event) {
		if(!event.getEntityLiving().getEntityWorld().isRemote) return;
		AgeOfTitans.LOGGER.info("testJumpEvent fired");
		LivingEntity livingEntity = event.getEntityLiving();
		World world = livingEntity.getEntityWorld();
		world.setBlockState(livingEntity.getPosition().add(0, 5, 0), BlockInit.example_block.getDefaultState());
		livingEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 600, 255));
		livingEntity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 255));
		livingEntity.setGlowing(true);
	}
	
}
