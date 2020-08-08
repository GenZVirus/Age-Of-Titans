package com.GenZVirus.AgeOfTitans.Common.Entities;

import java.util.List;

import com.GenZVirus.AgeOfTitans.Common.Init.EffectInit;
import com.google.common.collect.Lists;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;

public class TimeBombEntity extends ThrowableEntity{

	private double bonusDamage = 0;
	private int duration;
	public static List<Entity> affectedEntities = Lists.newArrayList();
	public static List<BlockPos> affectedEntitiesPos = Lists.newArrayList();
	public static List<Vec3d> affectedEntitiesMotion = Lists.newArrayList();
	
	public TimeBombEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
	      super(type, worldIn);
	   }

	public TimeBombEntity(EntityType<? extends ThrowableEntity> type, double x, double y, double z, World worldIn) {
		super(type, x, y, z, worldIn);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		
		AxisAlignedBB CUBE_BOX = VoxelShapes.fullCube().getBoundingBox();
		Vec3d pos_offset = new Vec3d(this.getPosition()).add(0, 0, 0);
		AxisAlignedBB aabb = CUBE_BOX.offset(pos_offset).grow(10.0D);
		List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, aabb);
		
		for(Entity entity : list) {
			if(entity instanceof LivingEntity) {
				((LivingEntity)entity).addPotionEffect(new EffectInstance(EffectInit.TIME_STOP.get(), duration));
				affectedEntities.add(entity);
				affectedEntitiesPos.add(entity.getPosition());
			}
		}
		
	}
	   public void setBonusDamage(double damage) {
		   this.bonusDamage = damage;
	   }
	   
	   public double getBonusDamage() {
		   return this.bonusDamage;
	   }
	   
	   public void setDuration(int duration) {
		   this.duration = duration;
	   }

	@Override
	protected void registerData() {
		// TODO Auto-generated method stub
		
	}

}
