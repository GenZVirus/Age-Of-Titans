package com.GenZVirus.AgeOfTitans.Common.Events;

import java.util.Random;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Init.BiomeInit;
import com.GenZVirus.AgeOfTitans.Common.Init.EffectInit;
import com.GenZVirus.AgeOfTitans.Common.Init.ItemInit;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandler;
import com.GenZVirus.AgeOfTitans.Common.Network.ReadElementPacket;
import com.GenZVirus.AgeOfTitans.Common.Network.SendPlayerRagePointsPacket;
import com.GenZVirus.AgeOfTitans.Common.Network.SyncPlayerMotionPacket;
import com.GenZVirus.AgeOfTitans.SpellSystem.XMLFileJava;
import com.GenZVirus.AgeOfTitans.Util.ForgeEventBusSubscriber;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class ServerEvents {

	@SubscribeEvent
	public static void serverChat(ServerChatEvent event) {
		if(Integer.parseInt(XMLFileJava.readElement(event.getPlayer().getUniqueID(), "ApplesEaten")) > 0)
		event.setComponent(new StringTextComponent( "\u00A7f" +"[" + "\u00A74" + XMLFileJava.readElement(event.getPlayer().getUniqueID(), "PlayerLevel") + "\u00A7r" + "\u00A7f" + "] " + "\u00A7b" + "\u00A7l" + event.getUsername() + "\u00A7f" + "\u00A7l" + ": " + "\u00A7r" + "\u00A7e" + "\u00A7o" + event.getMessage()));
		else event.setComponent(new StringTextComponent( "\u00A7f" +"[" + XMLFileJava.readElement(event.getPlayer().getUniqueID(), "PlayerLevel") + "] " + event.getUsername() + "\u00A7l" + ": " + "\u00A7r" + event.getMessage()));
	}
	
	@SubscribeEvent
	public static void generateRageOnAttacking(LivingHurtEvent event) {
		if(!(event.getSource().getTrueSource() instanceof PlayerEntity)) return;
		PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
		if(!ForgeEventBusSubscriber.players.contains(player)) return;
		int index = ForgeEventBusSubscriber.players.indexOf(player);
		int rageAmount = ForgeEventBusSubscriber.rage.get(index);
		if(rageAmount + 5 > 100 && rageAmount < 100) rageAmount = 100;
		else rageAmount += 5;
		ForgeEventBusSubscriber.rage.set(index, rageAmount);
		PacketHandler.INSTANCE.sendTo(new SendPlayerRagePointsPacket(rageAmount),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
	}
	
	@SubscribeEvent
	public static void generateRageWhenAttacked(LivingHurtEvent event) {
		if(!(event.getEntity() instanceof PlayerEntity)) return;
		PlayerEntity player = (PlayerEntity) event.getEntity();
		if(!ForgeEventBusSubscriber.players.contains(player)) return;
		int index = ForgeEventBusSubscriber.players.indexOf(player);
		int rageAmount = ForgeEventBusSubscriber.rage.get(index);
		if(rageAmount + 5 > 100 && rageAmount < 100) rageAmount = 100;
		else rageAmount += 5;
		ForgeEventBusSubscriber.rage.set(index, rageAmount);
		PacketHandler.INSTANCE.sendTo(new SendPlayerRagePointsPacket(rageAmount),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
	}
	
	@SubscribeEvent
	public static void insideBiome(PlayerEvent event) {
		PlayerEntity player = event.getPlayer();
		if(player == null) return;
		Biome biome = player.world.getBiome(player.getPosition());
		
		// Active effects
		
		if (player.getHealth() < player.getMaxHealth() && player.isPotionActive(EffectInit.HOLY_PLAINS.get())) {
			player.heal(1.0F);
		}
		
		// Add effects
		
		if(biome.equals(BiomeInit.HOLY_GROUND_PLAINS.get()) && !player.isPotionActive(EffectInit.HOLY_PLAINS.get())) {
			removeAllBiomeEffects(player);
			player.addPotionEffect(new EffectInstance(EffectInit.HOLY_PLAINS.get(), 100));
		}
		
		if(biome.equals(BiomeInit.HOLY_GROUND_HILLS.get()) && !player.isPotionActive(EffectInit.HOLY_HILLS.get())) {
			removeAllBiomeEffects(player);
			player.addPotionEffect(new EffectInstance(EffectInit.HOLY_HILLS.get(), 100));
		}
		
		if(biome.equals(BiomeInit.HOLY_GROUND_MOUNTAIN.get()) && !player.isPotionActive(EffectInit.HOLY_MOUNTAINS.get())) {
			removeAllBiomeEffects(player);
			player.addPotionEffect(new EffectInstance(EffectInit.HOLY_MOUNTAINS.get(), 100));
		}	
	}
	
	private static void removeAllBiomeEffects(PlayerEntity player) {
		
		if(player.isPotionActive(EffectInit.HOLY_PLAINS.get()))
		player.removePotionEffect(EffectInit.HOLY_PLAINS.get());
		
		if(player.isPotionActive(EffectInit.HOLY_HILLS.get()))
		player.removePotionEffect(EffectInit.HOLY_HILLS.get());
		
		if(player.isPotionActive(EffectInit.HOLY_MOUNTAINS.get()))
		player.removePotionEffect(EffectInit.HOLY_MOUNTAINS.get());
	}
	
	@SubscribeEvent
	public static void eatingApples(LivingEntityUseItemEvent.Finish event) {
		if(event.getEntityLiving().world.isRemote) return;
		if(!(event.getEntityLiving() instanceof PlayerEntity)) return;
		if(event.getItem().getItem().equals(ItemInit.FRUIT_OF_THE_GODS.get())) {
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			for(PlayerEntity playerMSG : ForgeEventBusSubscriber.players) {
				playerMSG.sendMessage(new TranslationTextComponent(player.getName().getFormattedText() + " has eaten the Fruit of the Gods"));
			}
		XMLFileJava.editElement(player.getUniqueID(), "ApplesEaten", Integer.toString((Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "ApplesEaten")) + 1)));
		PacketHandler.INSTANCE.sendTo(new ReadElementPacket(player.getUniqueID(), "ApplesEaten", Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "ApplesEaten"))), ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		XMLFileJava.editElement(player.getUniqueID(), "PlayerLevel", Integer.toString((Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerLevel")) + 1)));	
		PacketHandler.INSTANCE.sendTo(new ReadElementPacket(player.getUniqueID(), "PlayerLevels", Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerLevel"))), ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		XMLFileJava.editElement(player.getUniqueID(), "PlayerPoints", Integer.toString((Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerPoints")) + 1)));	
		PacketHandler.INSTANCE.sendTo(new ReadElementPacket(player.getUniqueID(), "PlayerPoints", Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerPoints"))), ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
	}
	
	@SubscribeEvent
	public static void jumpEvent(LivingJumpEvent event) {
	      if (event.getEntityLiving().isPotionActive(EffectInit.BERSERKER.get())) {
	    	  float f = 1.0F;
	         f += 0.1F * (float)(2 + 1);

	         Vec3d vec3d = event.getEntityLiving().getMotion();
	         event.getEntityLiving().setMotion(vec3d.x, (double)f + vec3d.y, vec3d.z);
	      	if (event.getEntityLiving().isSprinting()) {
	      		float f1 = event.getEntityLiving().rotationYaw * ((float)Math.PI / 180F);
	         	event.getEntityLiving().setMotion(event.getEntityLiving().getMotion().add((double)(-MathHelper.sin(f1) * 0.2F), 0.0D, (double)(MathHelper.cos(f1) * 0.2F)));
	      	}
	      }
	  
	      if (event.getEntityLiving().isPotionActive(EffectInit.HOLY_MOUNTAINS.get())) {
	    	  float f = 1.0F;
	         f += 0.1F * (float)(2 + 1);

	         Vec3d vec3d = event.getEntityLiving().getMotion();
	         event.getEntityLiving().setMotion(vec3d.x, (double)f + vec3d.y, vec3d.z);
	      	if (event.getEntityLiving().isSprinting()) {
	      		float f1 = event.getEntityLiving().rotationYaw * ((float)Math.PI / 180F);
	         	event.getEntityLiving().setMotion(event.getEntityLiving().getMotion().add((double)(-MathHelper.sin(f1) * 0.2F), 0.0D, (double)(MathHelper.cos(f1) * 0.2F)));
	      	}
	      }
	      
	      if (event.getEntityLiving().isPotionActive(EffectInit.HOLY_HILLS.get())) {
	    	  float f = 0.2F;
	         f += 0.1F * (float)(2 + 1);

	         Vec3d vec3d = event.getEntityLiving().getMotion();
	         event.getEntityLiving().setMotion(vec3d.x, (double)f + vec3d.y, vec3d.z);
	      	if (event.getEntityLiving().isSprinting()) {
	      		float f1 = event.getEntityLiving().rotationYaw * ((float)Math.PI / 180F);
	         	event.getEntityLiving().setMotion(event.getEntityLiving().getMotion().add((double)(-MathHelper.sin(f1) * 0.2F), 0.0D, (double)(MathHelper.cos(f1) * 0.2F)));
	      	}
	      }
	}
	
	@SubscribeEvent
	public static void skullCrasher(LivingAttackEvent event) {
		if(!(event.getSource().getTrueSource() instanceof PlayerEntity)) {
			return;
		}
		PlayerEntity player = (PlayerEntity)event.getSource().getTrueSource();
		LivingEntity target =  event.getEntityLiving();
		if(player.isPotionActive(EffectInit.BERSERKER.get())) {
			boolean flag = player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPotionActive(Effects.BLINDNESS);
			if(flag) {
				if(target.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() != Items.AIR) { 
					target.getItemStackFromSlot(EquipmentSlotType.HEAD).attemptDamageItem(1000, new Random(), null);
				} else if(target.isNonBoss()) {
					target.attackEntityFrom(new DamageSource("Skull Crasher"){
						@Override 
						public ITextComponent getDeathMessage(LivingEntity entityLivingBaseIn) {
						      LivingEntity livingentity = entityLivingBaseIn.getAttackingEntity();
						      String s = "death.attack." + this.damageType;
						      String s1 = s + ".player";
						      return livingentity != null ? new TranslationTextComponent(s1, entityLivingBaseIn.getDisplayName(), livingentity.getDisplayName()) : new TranslationTextComponent(s, entityLivingBaseIn.getDisplayName());
						   }
					}.setDamageBypassesArmor().setDamageIsAbsolute() , 2000000000.0F);
					event.setCanceled(true);
				}
			}
		}	
	}
	
	@SubscribeEvent
	public static void fallDamageImmunity(LivingFallEvent event) {
		if(event.getEntityLiving().isPotionActive(EffectInit.BERSERKER.get()) 
				|| event.getEntityLiving().isPotionActive(EffectInit.HOLY_HILLS.get()) 
				|| event.getEntityLiving().isPotionActive(EffectInit.HOLY_MOUNTAINS.get())) {
			event.setDamageMultiplier(0);
		}

	}
		
	@SubscribeEvent
	public static void berserkerPunch(LivingAttackEvent event) {
		if(!(event.getSource().getTrueSource() instanceof PlayerEntity)) {
			return;
		}
		PlayerEntity player = (PlayerEntity)event.getSource().getTrueSource();
		LivingEntity target =  event.getEntityLiving();
		if(player.isPotionActive(EffectInit.BERSERKER.get())) {
			if(target.isNonBoss()) {
				double offset = 4.0D;
				double pitch = player.getPitchYaw().x;
				double yaw   = player.getPitchYaw().y;
				double pitchRadian = pitch * (Math.PI / 180); // X rotation
				double yawRadian   = yaw   * (Math.PI / 180); // Y rotation 
				double newPosX = offset * -Math.sin( yawRadian ) * Math.cos( pitchRadian );
				double newPosY = offset * -Math.sin( pitchRadian );
				double newPosZ = offset *  Math.cos( yawRadian ) * Math.cos( pitchRadian );
				Vec3d vec = new Vec3d(newPosX, newPosY, newPosZ);
				target.setMotion(vec);
				if(target instanceof PlayerEntity)
				PacketHandler.INSTANCE.sendTo(new SyncPlayerMotionPacket(target.getUniqueID(), vec.getX(), vec.getY(), vec.getZ()), ((ServerPlayerEntity)target).connection.getNetworkManager(),	NetworkDirection.PLAY_TO_CLIENT);
			}
		}	
	}
}
