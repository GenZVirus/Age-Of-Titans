package com.GenZVirus.AgeOfTitans.Events;

import java.util.Random;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Init.EffectInit;
import com.GenZVirus.AgeOfTitans.Init.ItemInit;
import com.GenZVirus.AgeOfTitans.Network.PacketHandler;
import com.GenZVirus.AgeOfTitans.Network.ReadElementPacket;
import com.GenZVirus.AgeOfTitans.Network.SyncPlayerMotionPacket;
import com.GenZVirus.AgeOfTitans.SpellSystem.XMLFileJava;
import com.GenZVirus.AgeOfTitans.Util.ForgeEventBusSubscriber;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Items;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class ServerEvents {

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
	         event.getEntityLiving().setMotion(vec3d.x, (double)f, vec3d.z);
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
	public static void berserkerJump(LivingFallEvent event) {
		if(event.getEntityLiving().isPotionActive(EffectInit.BERSERKER.get())) {
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
