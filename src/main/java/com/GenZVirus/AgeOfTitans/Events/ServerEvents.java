package com.GenZVirus.AgeOfTitans.Events;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Init.EffectInit;
import com.GenZVirus.AgeOfTitans.Util.Helpers.HalfSphereShape;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class ServerEvents {

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
				List<ItemStack> list = (List<ItemStack>) target.getArmorInventoryList();
				if(list.get(3).getEquipmentSlot() == EquipmentSlotType.HEAD) { 
					if(list.get(3).getItem().getMaxDamage() >= 1000) {
						list.get(3).setDamage(list.get(3).getDamage() - 1000);
					} else {
						list.get(3).setDamage(0);
					}
				} else if(target.isNonBoss()) {
					target.attackEntityFrom(new DamageSource("Skull Crasher"){
						@Override 
						public ITextComponent getDeathMessage(LivingEntity entityLivingBaseIn) {
							System.out.println("TEST");
						      LivingEntity livingentity = entityLivingBaseIn.getAttackingEntity();
						      String s = "death.attack." + this.damageType;
						      String s1 = s + ".player";
						      return livingentity != null ? new TranslationTextComponent(s1, entityLivingBaseIn.getDisplayName(), livingentity.getDisplayName()) : new TranslationTextComponent(s, entityLivingBaseIn.getDisplayName());
						   }
					}.setDamageBypassesArmor().setDamageIsAbsolute() , Float.MAX_VALUE);
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void blockBreaker(PlayerTickEvent event) {
		if(event.player.world.isRemote) {
			return;
		}
		if(event.phase == Phase.END) {
			return;
		}
		PlayerEntity player = event.player;
		if(player.getHeldItemMainhand().getItem().equals(Items.AIR) && player.isPotionActive(EffectInit.BERSERKER.get())) {
			if(player.swingProgressInt == -1) {
				double offset = 3.0D;
				double pitch = player.getPitchYaw().x;
				double yaw   = player.getPitchYaw().y;
				Vec3d pos_offset = new Vec3d(player.getPosition()).add(0, 1.6D, 0);		
				double pitchRadian = pitch * (Math.PI / 180); // X rotation
				double yawRadian = yaw * (Math.PI / 180); // Y rotation 
				double newPosX = offset * -Math.sin( yawRadian ) * Math.cos( pitchRadian );
				double newPosY = offset * -Math.sin( pitchRadian );
				double newPosZ = offset *  Math.cos( yawRadian ) * Math.cos( pitchRadian );
				HalfSphereShape halfSphereShape = new HalfSphereShape(pos_offset.add(newPosX, newPosY, newPosZ), pos_offset);
				for(double k = 0.0D; k <= 2 * offset; k += 1.0D) {
					for(double j = 0.0D; j <= 2 * offset; j += 1.0D) {
						for(double i = 0.0D; i <= 2 * offset; i += 1.0D) {
							BlockPos pos = new BlockPos(new Vec3d(player.getPosX() - offset + i, player.getPosY() - offset + k, player.getPosZ() - offset + j));
							if(halfSphereShape.containsPoint(pos.getX(), pos.getY(), pos.getZ()))
									player.world.destroyBlock(pos, true);														
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onImpact(ProjectileImpactEvent.Fireball event) {
		if(event.getEntity().world.isRemote) return;
		System.out.println(event.getFireball().shootingEntity);
		event.getFireball().shootingEntity.setMotion(0, 2, 0);
	}
	
}
