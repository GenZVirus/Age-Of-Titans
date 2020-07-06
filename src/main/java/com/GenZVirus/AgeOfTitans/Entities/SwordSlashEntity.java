package com.GenZVirus.AgeOfTitans.Entities;

import java.util.List;

import com.GenZVirus.AgeOfTitans.Init.ModEntityTypes;
import com.GenZVirus.AgeOfTitans.Particles.SwordSlashParticle;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class SwordSlashEntity extends DamagingProjectileEntity {

	public boolean isInvulnerable = true;
	public int timeLife = 120;
	private int delayParticles = 5;
	private int ticksInAir;
	
	public SwordSlashEntity(EntityType<? extends SwordSlashEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public SwordSlashEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
	      super(ModEntityTypes.SWORD_SLASH.get(), shooter, accelX, accelY, accelZ, worldIn);
	   }
	
	   @Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
	@Override
	public void tick() {
		timeLife--;
		if(timeLife <= 0) {
			delayParticles = 5;
			this.remove();
		}
		if (this.world.isRemote || (this.shootingEntity == null || !this.shootingEntity.removed) && this.world.isBlockLoaded(new BlockPos(this))) {
			if (!this.world.isRemote) {
		         this.setFlag(6, this.isGlowing());
		      }

		      this.baseTick();
	         if (this.isFireballFiery()) {
	            this.setFire(1);
	         }
	         ++this.ticksInAir;
	         RayTraceResult raytraceresult = ProjectileHelper.rayTrace(this, true, this.ticksInAir >= 25, this.shootingEntity, RayTraceContext.BlockMode.COLLIDER);
	         if (raytraceresult.getType() != RayTraceResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
	            this.onImpact(raytraceresult);
	         }

	         Vec3d vec3d = this.getMotion();
	         double d0 = this.getPosX() + vec3d.x;
	         double d1 = this.getPosY() + vec3d.y;
	         double d2 = this.getPosZ() + vec3d.z;
	         float f = this.getMotionFactor();
	         if (this.isInWater()) {
	            for(int i = 0; i < 4; ++i) {
	               float f1 = 0.25F;
	               this.world.addParticle(ParticleTypes.BUBBLE, d0 - vec3d.x * 0.25D, d1 - vec3d.y * 0.25D, d2 - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
	            }

	            f = 0.8F;
	         }

	         this.setMotion(vec3d.add(this.accelerationX, this.accelerationY, this.accelerationZ).scale((double)f));
	         this.setPosition(d0, d1, d2);
	      } else {
	         this.remove();
	      }
		if(delayParticles > 0) {
			delayParticles--;
			return;
		}
		
	         
		
		double offset = 1.0D;
		
		double pitch = this.getPitchYaw().x;
		double yaw   = this.getPitchYaw().y;
		
		double pitchRadian = pitch * (Math.PI / 180); // X rotation
		double yawRadian   = yaw   * (Math.PI / 180); // Y rotation 
		
		double newPosX;
		double newPosY;
		double newPosZ;
		
		double x = this.getPosX();
		double y = this.getPosY() + 1.0D;
		double z = this.getPosZ();
		
		double nr = 0.0D;
		double pitchTop = pitchRadian;
		double pitchBottom = pitchRadian;
		
		while(nr <= 0.3D) {
			
			double offset2 = offset - nr;
			pitchTop = pitchRadian;
			pitchBottom = pitchRadian;
			
			for(double i = 0.1D; i <= 0.5D - nr; i += 0.1D) {
				pitchBottom += i;
				newPosX = offset2 * -Math.sin( yawRadian ) * Math.cos( pitchBottom );
				newPosY = offset2 * -Math.sin( pitchBottom );
				newPosZ = offset2 *  Math.cos( yawRadian ) * Math.cos( pitchBottom );
				
				this.world.addParticle(new SwordSlashParticle.SwordSlashParticleData(1.0f, 1.0f, 1.0f, 1.0f), true, x + newPosX, y + newPosY, z + newPosZ, 0, 0, 0);
					
				pitchTop -= i;
				newPosX = offset2 * -Math.sin( yawRadian ) * Math.cos( pitchTop );
				newPosY = offset2 * -Math.sin( pitchTop );
				newPosZ = offset2 *  Math.cos( yawRadian ) * Math.cos( pitchTop );
				
				this.world.addParticle(new SwordSlashParticle.SwordSlashParticleData(1.0f, 1.0f, 1.0f, 1.0f), true, x + newPosX, y + newPosY, z + newPosZ, 0, 0, 0);
			}
			
			nr += 0.1D;
			
		}
		for(int i = 0; i < 10; i++) {
		pitchTop = pitchRadian - 1.5D + 0.1D * i;
		pitchBottom = pitchRadian + 1.5D - 0.1D * i;
		offset -= 0.1D * i;
		
		newPosX = offset * -Math.sin( yawRadian ) * Math.cos( pitchBottom );
		newPosY = offset * -Math.sin( pitchBottom );
		newPosZ = offset *  Math.cos( yawRadian ) * Math.cos( pitchBottom );
		
		this.world.addParticle(ParticleTypes.FLAME, true, x + newPosX, y + newPosY, z + newPosZ, 0, 0, 0);
		
		newPosX = offset * -Math.sin( yawRadian ) * Math.cos( pitchTop );
		newPosY = offset * -Math.sin( pitchTop );
		newPosZ = offset *  Math.cos( yawRadian ) * Math.cos( pitchTop );
		
		this.world.addParticle(ParticleTypes.FLAME, true, x + newPosX, y + newPosY, z + newPosZ, 0, 0, 0);
		}
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
	            entity.attackEntityFrom(DamageSource.MAGIC, 10.0F);
	         }
	         
	         AxisAlignedBB CUBE_BOX = VoxelShapes.fullCube().getBoundingBox();
	         Vec3d pos_offset = new Vec3d(this.getPosition());
	         AxisAlignedBB aabb = CUBE_BOX.offset(pos_offset).grow(3);
	         List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this.shootingEntity, aabb);	
	         for(Entity entity : list) {
	        	 if(entity instanceof LivingEntity)
	        	 entity.attackEntityFrom(DamageSource.MAGIC, 7.0F);
	         }

	         Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this.shootingEntity) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
	         this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0F, false, explosion$mode);
	         this.remove();
	         
	      }

	   }
	   
	   @Override
	protected IParticleData getParticle() {
		return null;
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
