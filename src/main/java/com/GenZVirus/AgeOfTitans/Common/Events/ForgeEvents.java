package com.GenZVirus.AgeOfTitans.Common.Events;

import java.util.Random;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Config.AOTConfig;
import com.GenZVirus.AgeOfTitans.Common.Init.BiomeInit;
import com.GenZVirus.AgeOfTitans.Common.Init.BlockInit;
import com.GenZVirus.AgeOfTitans.Common.Init.EffectInit;
import com.GenZVirus.AgeOfTitans.Common.Init.ItemInit;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandlerCommon;
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
import net.minecraftforge.event.entity.item.ItemEvent;
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
public class ForgeEvents {
	
	@SubscribeEvent
	public static void entityHurt(LivingHurtEvent event) {
		if(event.getEntity().world.isRemote) return;
		if(event.getEntityLiving().isPotionActive(EffectInit.GRAVITY_FIELD.get())) {
			event.setAmount((float) (event.getAmount() + event.getAmount() * AOTConfig.COMMON.gravity_bomb_bonus_damage.get() * event.getEntityLiving().getActivePotionEffect(EffectInit.GRAVITY_FIELD.get()).getAmplifier()));
		}
	}
	
	@SubscribeEvent
	public static void lavaImmunity(ItemEvent event) {
		if(event.getEntityItem().getItem().getItem().equals(ItemInit.FRUIT_OF_THE_GODS.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(ItemInit.ORB_OF_DISLOCATION.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(ItemInit.ORB_OF_EDEN.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(ItemInit.ORB_OF_END.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(ItemInit.ORB_OF_NETHER.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(ItemInit.ORB_OF_STORAGE.get())) {
			event.getEntityItem().setInvulnerable(true); 
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(ItemInit.ORB_OF_SUMMONING.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_AXE.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_PICKAXE.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_HOE.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_SHOVEL.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_SWORD.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_HELMET.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_CHESTPLATE.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_LEGGINGS.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_BOOTS.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(ItemInit.TITANIUM_INGOT.get())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(BlockInit.TITANIUM_ORE.get().asItem())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(BlockInit.TITANIUM_BLOCK.get().asItem())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		} else if(event.getEntityItem().getItem().getItem().equals(BlockInit.TITAN_LOCKER.get().asItem())) {
			event.getEntityItem().setInvulnerable(true);
			event.getEntityItem().lifespan = 72000;
		}
	}
	
	@SubscribeEvent
	public static void generateRageWhileSprinting(PlayerEvent event) {
		if(event.getPlayer() == null) return;
		if(event.getPlayer().world.isRemote) return;
		if(event.getPlayer().isSprinting()) {
			PlayerEntity player = event.getPlayer();
			if(!ForgeEventBusSubscriber.players.contains(player)) return;
			int index = ForgeEventBusSubscriber.players.indexOf(player);
			int rageAmount = ForgeEventBusSubscriber.rage.get(index);
			if(rageAmount + 1 > 1000 && rageAmount <= 1000) rageAmount = 1000;
			else rageAmount += 1;
			ForgeEventBusSubscriber.rage.set(index, rageAmount);
			PacketHandlerCommon.INSTANCE.sendTo(new SendPlayerRagePointsPacket(rageAmount),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
	}
	
	@SubscribeEvent
	public static void outOfCombat(PlayerEvent event) {
		if(event.getPlayer() == null) return;
		if(event.getPlayer().world.isRemote) return;
			PlayerEntity player = event.getPlayer();
			Random rand = new Random();
			if(player.getCombatTracker().getCombatDuration() == 0 && rand.nextInt(100) % 3 == 0) {
				if(!ForgeEventBusSubscriber.players.contains(player)) return;
				int index = ForgeEventBusSubscriber.players.indexOf(player);
				int rageAmount = ForgeEventBusSubscriber.rage.get(index);
				if(rageAmount - 1 < 0) return;
				else rageAmount -= 1;
				ForgeEventBusSubscriber.rage.set(index, rageAmount);
				PacketHandlerCommon.INSTANCE.sendTo(new SendPlayerRagePointsPacket(rageAmount),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
			}
	}
	
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
		if(rageAmount + 50 > 1000 && rageAmount <= 1000) rageAmount = 1000;
		else rageAmount += 50;
		ForgeEventBusSubscriber.rage.set(index, rageAmount);
		PacketHandlerCommon.INSTANCE.sendTo(new SendPlayerRagePointsPacket(rageAmount),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
	}
	
	@SubscribeEvent
	public static void generateRageWhenAttacked(LivingHurtEvent event) {
		if(!(event.getEntity() instanceof PlayerEntity)) return;
		PlayerEntity player = (PlayerEntity) event.getEntity();
		if(!ForgeEventBusSubscriber.players.contains(player)) return;
		int index = ForgeEventBusSubscriber.players.indexOf(player);
		int rageAmount = ForgeEventBusSubscriber.rage.get(index);
		if(rageAmount + 50 > 1000 && rageAmount <= 1000) rageAmount = 1000;
		else rageAmount += 50;
		ForgeEventBusSubscriber.rage.set(index, rageAmount);
		PacketHandlerCommon.INSTANCE.sendTo(new SendPlayerRagePointsPacket(rageAmount),  ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
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
		PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(player.getUniqueID(), "ApplesEaten", Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "ApplesEaten"))), ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		XMLFileJava.editElement(player.getUniqueID(), "PlayerLevel", Integer.toString((Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerLevel")) + 1)));	
		PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(player.getUniqueID(), "PlayerLevels", Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerLevel"))), ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		XMLFileJava.editElement(player.getUniqueID(), "PlayerPoints", Integer.toString((Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerPoints")) + 1)));	
		PacketHandlerCommon.INSTANCE.sendTo(new ReadElementPacket(player.getUniqueID(), "PlayerPoints", Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "PlayerPoints"))), ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
	}
	
	@SubscribeEvent
	public static void jumpEvent(LivingJumpEvent event) {
		
		LivingEntity entity = event.getEntityLiving();
	      if (entity.isPotionActive(EffectInit.BERSERKER.get())) {
	    	  float f = 1.0F;
	         f += 0.1F * (float)(2 + 1);

	         Vec3d vec3d = entity.getMotion();
	         entity.setMotion(vec3d.x, (double)f + vec3d.y, vec3d.z);
	      	if (entity.isSprinting()) {
	      		float f1 = entity.rotationYaw * ((float)Math.PI / 180F);
	      		entity.setMotion(event.getEntityLiving().getMotion().add((double)(-MathHelper.sin(f1) * 0.2F), 0.0D, (double)(MathHelper.cos(f1) * 0.2F)));
	      	}
	      }
	  
	      if (event.getEntityLiving().isPotionActive(EffectInit.HOLY_MOUNTAINS.get())) {
	    	  float f = 1.0F;
	         f += 0.1F * (float)(2 + 1);

	         Vec3d vec3d = entity.getMotion();
	         entity.setMotion(vec3d.x, (double)f + vec3d.y, vec3d.z);
	      	if (entity.isSprinting()) {
	      		float f1 = entity.rotationYaw * ((float)Math.PI / 180F);
	      		entity.setMotion(entity.getMotion().add((double)(-MathHelper.sin(f1) * 0.2F), 0.0D, (double)(MathHelper.cos(f1) * 0.2F)));
	      	}
	      }
	      
	      if (entity.isPotionActive(EffectInit.HOLY_HILLS.get())) {
	    	  float f = 0.2F;
	         f += 0.1F * (float)(2 + 1);

	         Vec3d vec3d = entity.getMotion();
	         entity.setMotion(vec3d.x, (double)f + vec3d.y, vec3d.z);
	      	if (entity.isSprinting()) {
	      		float f1 = entity.rotationYaw * ((float)Math.PI / 180F);
	      		entity.setMotion(entity.getMotion().add((double)(-MathHelper.sin(f1) * 0.2F), 0.0D, (double)(MathHelper.cos(f1) * 0.2F)));
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
				PacketHandlerCommon.INSTANCE.sendTo(new SyncPlayerMotionPacket(target.getUniqueID(), vec.getX(), vec.getY(), vec.getZ()), ((ServerPlayerEntity)target).connection.getNetworkManager(),	NetworkDirection.PLAY_TO_CLIENT);
			}
		}	
	}
}
