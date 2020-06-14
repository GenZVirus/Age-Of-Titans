package com.GenZVirus.AgeOfTitans.Events;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Init.EffectInit;
import com.GenZVirus.AgeOfTitans.Util.Helpers.ConeShape;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
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
				double offset = 2.0D;
				double pitch = player.getPitchYaw().x;
				double yaw   = player.getPitchYaw().y;
				AxisAlignedBB CUBE_BOX = VoxelShapes.fullCube().getBoundingBox();
				Vec3d pos_offset = new Vec3d(player.getPosition()).add(0, 1.6D, 0);
				AxisAlignedBB aabb = CUBE_BOX.offset(pos_offset).grow(offset);
				List<Entity> list = player.world.getEntitiesWithinAABBExcludingEntity(player, aabb);		
				double pitchRadian = pitch * (Math.PI / 180); // X rotation
				double yawRadian = yaw * (Math.PI / 180); // Y rotation 
				double newPosX = offset * -Math.sin( yawRadian ) * Math.cos( pitchRadian );
				double newPosY = offset * -Math.sin( pitchRadian );
				double newPosZ = offset *  Math.cos( yawRadian ) * Math.cos( pitchRadian );
				ConeShape coneShape = new ConeShape(pos_offset.add(newPosX, newPosY, newPosZ), pos_offset, 10.0D, 0.1D);
				for(double k = 0.0D; k <= 2 * (offset - 1); k += 1.0D) {
					for(double j = 0.0D; j <= 2 * (offset - 1); j += 1.0D) {
						for(double i = 0.0D; i <= 2 * (offset - 1); i += 1.0D) {
							BlockPos pos = new BlockPos(new Vec3d(player.getPosX() - (offset - 1) + i, player.getPosY() - (offset - 1) + k, player.getPosZ() - (offset - 1) + j));
							if(coneShape.containsPoint(pos.getX(), pos.getY(), pos.getZ()))
									player.world.destroyBlock(pos, false);														
						}
					}
				}
			}
		}
	}
	
	
}
