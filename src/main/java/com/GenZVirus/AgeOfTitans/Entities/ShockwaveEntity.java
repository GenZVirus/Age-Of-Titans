package com.GenZVirus.AgeOfTitans.Entities;

import com.GenZVirus.AgeOfTitans.Init.ModEntityTypes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ShockwaveEntity extends DamagingProjectileEntity {

	public boolean isInvulnerable = true;
	public int timeLife = 120;
	
	public ShockwaveEntity(EntityType<? extends ShockwaveEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public ShockwaveEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
	      super(ModEntityTypes.SHOCKWAVE.get(), shooter, accelX, accelY, accelZ, worldIn);
	   }
	
	   @Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	@Override
	public void tick() {
		timeLife--;
		if(timeLife <= 0)
			this.remove();
		super.tick();
	}
	
	   /**
	    * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
	    */
	   public boolean isBurning() {
	      return false;
	   }
	
	   /**
	    * Called when this EntityFireball hits a block or entity.
	    */
	   protected void onImpact(RayTraceResult result) {
	      super.onImpact(result);
	      if (!this.world.isRemote) {
	         if (result.getType() == RayTraceResult.Type.ENTITY) {
	            Entity entity = ((EntityRayTraceResult)result).getEntity();
	            if (this.shootingEntity != null) {
	               if (entity.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 8.0F)) {
	                  if (entity.isAlive()) {
	                     this.applyEnchantments(this.shootingEntity, entity);
	                  } else {
	                     this.shootingEntity.heal(5.0F);
	                  }
	               }
	            } else {
	               entity.attackEntityFrom(DamageSource.MAGIC, 5.0F);
	            }

	            if (entity instanceof LivingEntity) {
	               int i = 0;
	               if (this.world.getDifficulty() == Difficulty.NORMAL) {
	                  i = 10;
	               } else if (this.world.getDifficulty() == Difficulty.HARD) {
	                  i = 40;
	               }

	               if (i > 0) {
	                  ((LivingEntity)entity).addPotionEffect(new EffectInstance(Effects.WITHER, 20 * i, 1));
	               }
	            }
	         }

	         Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this.shootingEntity) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
	         this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0F, false, explosion$mode);
	         this.remove();
	         
	      }

	   }
	   
	   /**
	    * Returns true if other Entities should be prevented from moving through this Entity.
	    */
	   public boolean canBeCollidedWith() {
	      return false;
	   }
	   
	   protected boolean isFireballFiery() {
		      return false;
		   }
}
