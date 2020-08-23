package com.GenZVirus.AgeOfTitans.Common.Events.Server;

import java.util.Random;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Config.AOTConfig;
import com.GenZVirus.AgeOfTitans.Common.Init.EffectInit;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandlerCommon;
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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class AOTActiveAbilities {

	@SubscribeEvent
	public static void AOTRevitalise(PlayerEvent event) {
		if (event.getPlayer() == null)
			return;
		if (event.getPlayer().world.isRemote)
			return;
		PlayerEntity player = event.getPlayer();
		if (!player.isPotionActive(EffectInit.REVITALISE.get()) || !(player.getHealth() < player.getMaxHealth()))
			return;
		if (player.getHealth() < player.getMaxHealth()) {
			player.addPotionEffect(new EffectInstance(EffectInit.REVITALISE.get(), 100));
		}
		if (!ForgeEventBusSubscriber.players.contains(player))
			return;
		int index = ForgeEventBusSubscriber.players.indexOf(player);
		int rageAmount = ForgeEventBusSubscriber.rage.get(index);
		if (rageAmount - AOTConfig.COMMON.revitalise_cost.get() > 0) {
			rageAmount -= AOTConfig.COMMON.revitalise_cost.get();
			ForgeEventBusSubscriber.rage.set(index, rageAmount);
			PacketHandlerCommon.INSTANCE.sendTo(new SendPlayerRagePointsPacket(rageAmount), ((ServerPlayerEntity) player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
			double amount = AOTConfig.COMMON.revitalise_base_amount.get() + AOTConfig.COMMON.revitalise_base_amount.get() * AOTConfig.COMMON.revitalise_healing_ratio.get() * Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "Spell_Level6"));
			if (player.getHealth() + amount > player.getMaxHealth())
				amount = player.getMaxHealth() - player.getHealth();
			player.heal((float) amount);
		}
	}

	

	@SubscribeEvent
	public static void AOTGravityBombDamageMultiplier(LivingDamageEvent event) {
		if (event.getEntity().world.isRemote)
			return;
		if (event.getEntityLiving().isPotionActive(EffectInit.GRAVITY_FIELD.get())) {
			event.setAmount((float) (event.getAmount() + event.getAmount() * AOTConfig.COMMON.gravity_bomb_bonus_damage.get() * event.getEntityLiving().getActivePotionEffect(EffectInit.GRAVITY_FIELD.get()).getAmplifier()));
		}
	}
	
	@SubscribeEvent
	public static void AOTJumpEvent(LivingJumpEvent event) {

		LivingEntity entity = event.getEntityLiving();
		if (entity.isPotionActive(EffectInit.BERSERKER.get())) {
			float f = 1.0F;
			f += 0.1F * (float) (2 + 1);

			Vec3d vec3d = entity.getMotion();
			entity.setMotion(vec3d.x, (double) f + vec3d.y, vec3d.z);
			if (entity.isSprinting()) {
				float f1 = entity.rotationYaw * ((float) Math.PI / 180F);
				entity.setMotion(event.getEntityLiving().getMotion().add((double) (-MathHelper.sin(f1) * 0.2F), 0.0D, (double) (MathHelper.cos(f1) * 0.2F)));
			}
		}

		if (event.getEntityLiving().isPotionActive(EffectInit.HOLY_MOUNTAINS.get())) {
			float f = 1.0F;
			f += 0.1F * (float) (2 + 1);

			Vec3d vec3d = entity.getMotion();
			entity.setMotion(vec3d.x, (double) f + vec3d.y, vec3d.z);
			if (entity.isSprinting()) {
				float f1 = entity.rotationYaw * ((float) Math.PI / 180F);
				entity.setMotion(entity.getMotion().add((double) (-MathHelper.sin(f1) * 0.2F), 0.0D, (double) (MathHelper.cos(f1) * 0.2F)));
			}
		}

		if (entity.isPotionActive(EffectInit.HOLY_HILLS.get())) {
			float f = 0.2F;
			f += 0.1F * (float) (2 + 1);

			Vec3d vec3d = entity.getMotion();
			entity.setMotion(vec3d.x, (double) f + vec3d.y, vec3d.z);
			if (entity.isSprinting()) {
				float f1 = entity.rotationYaw * ((float) Math.PI / 180F);
				entity.setMotion(entity.getMotion().add((double) (-MathHelper.sin(f1) * 0.2F), 0.0D, (double) (MathHelper.cos(f1) * 0.2F)));
			}
		}

	}

	@SubscribeEvent
	public static void AOTBerserkerSkullCrasher(LivingAttackEvent event) {
		if (!(event.getSource().getTrueSource() instanceof PlayerEntity)) { return; }
		PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
		LivingEntity target = event.getEntityLiving();
		if (player.isPotionActive(EffectInit.BERSERKER.get())) {
			boolean flag = player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPotionActive(Effects.BLINDNESS);
			if (flag) {
				if (target.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() != Items.AIR) {
					target.getItemStackFromSlot(EquipmentSlotType.HEAD).attemptDamageItem(1000, new Random(), null);
				} else if (target.isNonBoss()) {
					target.attackEntityFrom(new DamageSource("Skull Crasher") {
						@Override
						public ITextComponent getDeathMessage(LivingEntity entityLivingBaseIn) {
							LivingEntity livingentity = entityLivingBaseIn.getAttackingEntity();
							String s = "death.attack." + this.damageType;
							String s1 = s + ".player";
							return livingentity != null ? new TranslationTextComponent(s1, entityLivingBaseIn.getDisplayName(), livingentity.getDisplayName()) : new TranslationTextComponent(s, entityLivingBaseIn.getDisplayName());
						}
					}.setDamageBypassesArmor().setDamageIsAbsolute(), 2000000000.0F);
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public static void AOTFallDamageImmunity(LivingFallEvent event) {
		if (event.getEntityLiving().isPotionActive(EffectInit.BERSERKER.get()) || event.getEntityLiving().isPotionActive(EffectInit.HOLY_HILLS.get()) || event.getEntityLiving().isPotionActive(EffectInit.HOLY_MOUNTAINS.get())) {
			event.setDamageMultiplier(0);
		}

	}

	@SubscribeEvent
	public static void AOTBerserkerPunch(LivingAttackEvent event) {
		if (!(event.getSource().getTrueSource() instanceof PlayerEntity)) { return; }
		PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
		LivingEntity target = event.getEntityLiving();
		if (player.isPotionActive(EffectInit.BERSERKER.get())) {
			if (target.isNonBoss()) {
				double offset = 4.0D;
				double pitch = player.getPitchYaw().x;
				double yaw = player.getPitchYaw().y;
				double pitchRadian = pitch * (Math.PI / 180); // X rotation
				double yawRadian = yaw * (Math.PI / 180); // Y rotation
				double newPosX = offset * -Math.sin(yawRadian) * Math.cos(pitchRadian);
				double newPosY = offset * -Math.sin(pitchRadian);
				double newPosZ = offset * Math.cos(yawRadian) * Math.cos(pitchRadian);
				Vec3d vec = new Vec3d(newPosX, newPosY, newPosZ);
				target.setMotion(vec);
				if (target instanceof PlayerEntity)
					PacketHandlerCommon.INSTANCE.sendTo(new SyncPlayerMotionPacket(target.getUniqueID(), vec.getX(), vec.getY(), vec.getZ()), ((ServerPlayerEntity) target).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
			}
		}
	}
	
}
