package com.GenZVirus.AgeOfTitans.Common.Entities;

import java.util.List;

import com.GenZVirus.AgeOfTitans.Common.Init.ModEntityTypes;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandler;
import com.GenZVirus.AgeOfTitans.Common.Network.SyncPlayerMotionPacket;

import net.minecraft.block.Blocks;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkHooks;

public class ChainEntity extends ThrowableEntity{

	public boolean isInvulnerable = true;
	public int timeLife = 20;
	public PlayerEntity shooter;
	public float borderSize = 0.3F;
	public double accelX = 0;
	public double accelY = 0;
	public double accelZ = 0;
	private Entity ignoreEntity;
	private int ignoreTime;
	public float renderYawOffset;
	public float prevRenderYawOffset;
	@SuppressWarnings("unused")
	private float prevRotationYawHead;
	private float rotationYawHead;
	private double damage = 5.0D;
	
	public ChainEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	public ChainEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
	      super(ModEntityTypes.CHAIN.get(),worldIn);
	      this.accelX = accelX;
	      this.accelY = accelY;
	      this.accelZ = accelZ;
	      this.shooter = (PlayerEntity) shooter;
	   }

	@Override
	public void baseTick() {
		super.baseTick();
		this.prevRenderYawOffset = this.renderYawOffset;
		this.prevRotationYawHead = this.rotationYawHead;
	}
	
	@SuppressWarnings("unused")
	@Override
	public void tick() {
		if(timeLife == 20) {
			AxisAlignedBB CUBE_BOX = VoxelShapes.fullCube().getBoundingBox();
			Vec3d pos_offset = new Vec3d(this.getPosition()).add(0, 0, 0);
			AxisAlignedBB aabb = CUBE_BOX.offset(pos_offset).grow(0.01D);
			List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, aabb);
			for(Entity entity : list) {
				if(entity instanceof PlayerEntity) {
					this.shooter = (PlayerEntity) entity;
				}
			}
		}
		this.timeLife--;
		if(this.timeLife <= 0)
			this.remove();
//		this.world.addParticle(new ChainParticle.ChainParticleData(1.0f, 1.0f, 1.0f, 1.0f), true, this.getPosX(), this.getPosY(), this.getPosZ(), 0, 0, 0);
		if (!this.world.isRemote) {
	         this.setFlag(6, this.isGlowing());
	      }

	      this.baseTick();
	      if (this.throwableShake > 0) {
	         --this.throwableShake;
	      }

	      if (this.inGround) {
	         this.inGround = false;
	         this.setMotion(this.getMotion().mul((double)(this.rand.nextFloat() * 0.2F), (double)(this.rand.nextFloat() * 0.2F), (double)(this.rand.nextFloat() * 0.2F)));
	      }

	      AxisAlignedBB axisalignedbb = this.getBoundingBox().expand(this.getMotion()).grow(1.0D);

	      for(Entity entity : this.world.getEntitiesInAABBexcluding(this, axisalignedbb, (p_213881_0_) -> {
	         return !p_213881_0_.isSpectator() && p_213881_0_.canBeCollidedWith();
	      })) {
	         if (entity == this.ignoreEntity) {
	            ++this.ignoreTime;
	            break;
	         }

	         if (this.owner != null && this.ticksExisted < 2 && this.ignoreEntity == null) {
	            this.ignoreEntity = entity;
	            this.ignoreTime = 3;
	            break;
	         }
	      }

	      RayTraceResult raytraceresult = ProjectileHelper.rayTrace(this, axisalignedbb, (p_213880_1_) -> {
	         return !p_213880_1_.isSpectator() && p_213880_1_.canBeCollidedWith() && p_213880_1_ != this.ignoreEntity;
	      }, RayTraceContext.BlockMode.OUTLINE, true);
	      if (this.ignoreEntity != null && this.ignoreTime-- <= 0) {
	         this.ignoreEntity = null;
	      }

	      if (raytraceresult.getType() != RayTraceResult.Type.MISS) {
	         if (raytraceresult.getType() == RayTraceResult.Type.BLOCK && this.world.getBlockState(((BlockRayTraceResult)raytraceresult).getPos()).getBlock() == Blocks.NETHER_PORTAL) {
	            this.setPortal(((BlockRayTraceResult)raytraceresult).getPos());
	         } else if (!net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)){
	            this.onImpact(raytraceresult);
	         }
	      }

	      Vec3d vec3d = this.getMotion();
	      double d0 = this.getPosX() + vec3d.x;
	      double d1 = this.getPosY() + vec3d.y;
	      double d2 = this.getPosZ() + vec3d.z;
	      float f = MathHelper.sqrt(horizontalMag(vec3d));
	      this.rotationYaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * (double)(180F / (float)Math.PI));

	      for(this.rotationPitch = (float)(MathHelper.atan2(vec3d.y, (double)f) * (double)(180F / (float)Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
	         ;
	      }

	      while(this.rotationPitch - this.prevRotationPitch >= 180.0F) {
	         this.prevRotationPitch += 360.0F;
	      }

	      while(this.rotationYaw - this.prevRotationYaw < -180.0F) {
	         this.prevRotationYaw -= 360.0F;
	      }

	      while(this.rotationYaw - this.prevRotationYaw >= 180.0F) {
	         this.prevRotationYaw += 360.0F;
	      }
	      
	      while(this.renderYawOffset - this.prevRenderYawOffset < -180.0F) {
	          this.prevRenderYawOffset -= 360.0F;
	       }

	       while(this.renderYawOffset - this.prevRenderYawOffset >= 180.0F) {
	          this.prevRenderYawOffset += 360.0F;
	       }
	       
	      this.rotationPitch = MathHelper.lerp(0.2F, this.prevRotationPitch, this.rotationPitch);
	      this.rotationYaw = MathHelper.lerp(0.2F, this.prevRotationYaw, this.rotationYaw);
	      float f1;
	      if (this.isInWater()) {
	         for(int i = 0; i < 4; ++i) {
	            float f2 = 0.25F;
	            this.world.addParticle(ParticleTypes.BUBBLE, d0 - vec3d.x * 0.25D, d1 - vec3d.y * 0.25D, d2 - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
	         }

	         f1 = 0.8F;
	      } else {
	         f1 = 0.99F;
	      }

	      this.setMotion(vec3d.scale((double)f1));
	      if (!this.hasNoGravity()) {
	         Vec3d vec3d1 = this.getMotion();
	         this.setMotion(vec3d1.x, vec3d1.y - (double)this.getGravityVelocity(), vec3d1.z);
	      }

	      this.setPosition(d0, d1, d2);
	}

	protected float updateDistance(float p_110146_1_, float p_110146_2_) {
	      float f = MathHelper.wrapDegrees(p_110146_1_ - this.renderYawOffset);
	      this.renderYawOffset += f * 0.3F;
	      float f1 = MathHelper.wrapDegrees(this.rotationYaw - this.renderYawOffset);
	      boolean flag = f1 < -90.0F || f1 >= 90.0F;
	      if (f1 < -75.0F) {
	         f1 = -75.0F;
	      }

	      if (f1 >= 75.0F) {
	         f1 = 75.0F;
	      }

	      this.renderYawOffset = this.rotationYaw - f1;
	      if (f1 * f1 > 2500.0F) {
	         this.renderYawOffset += f1 * 0.2F;
	      }

	      if (flag) {
	         p_110146_2_ *= -1.0F;
	      }

	      return p_110146_2_;
	   }
	
	public float getRotationYawHead() {
	      return this.rotationYawHead;
	   }

	   /**
	    * Sets the head's yaw rotation of the entity.
	    */
	   public void setRotationYawHead(float rotation) {
	      this.rotationYawHead = rotation;
	   }
	
	   public void setRenderYawOffset(float offset) {
		      this.renderYawOffset = offset;
		   }
	
	   public void lookAt(EntityAnchorArgument.Type p_200602_1_, Vec3d p_200602_2_) {
		      super.lookAt(p_200602_1_, p_200602_2_);
		      this.prevRotationYawHead = this.rotationYawHead;
		      this.renderYawOffset = this.rotationYawHead;
		      this.prevRenderYawOffset = this.renderYawOffset;
		   }

	   
	/**
	    * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
	    */
	   public boolean isBurning() {
	      return false;
	   }
	   @Override
	protected float getGravityVelocity() {
		return 0;
	}
	
	/**
	    * Called when this ChainEntity hits a block or entity.
	    */
	   protected void onImpact(RayTraceResult result) {

		   if (!this.world.isRemote) {
	         if (result.getType() == RayTraceResult.Type.ENTITY) {
	            Entity entity = ((EntityRayTraceResult)result).getEntity();
	            if (this.shooter != null) {
	            	entity.setPosition(this.shooter.getPosX(), this.shooter.getPosY(), this.shooter.getPosZ());
	               entity.attackEntityFrom(DamageSource.MAGIC, (float) this.getDamage());
	            }
	         }
	         if(result.getType() == RayTraceResult.Type.BLOCK && this.shooter != null) {
	        	 BlockPos pos = ((BlockRayTraceResult)result).getPos();
	        	 Vec3d vec = new Vec3d((double)pos.getX() - shooter.getPosX(), (double)pos.getY() - shooter.getPosY(), (double)pos.getZ() - shooter.getPosZ());
	        	 shooter.setMotion(vec.getX() / vec.length() * 3, vec.getY() / vec.length() * 3 + 0.5D, vec.getZ() / vec.length() * 3);
					PacketHandler.INSTANCE.sendTo(new SyncPlayerMotionPacket(this.shooter.getUniqueID(), vec.getX() / vec.length() * 3, vec.getY() / vec.length() * 3 + 0.5D, vec.getZ() / vec.length() * 3), ((ServerPlayerEntity)this.shooter).connection.getNetworkManager(),	NetworkDirection.PLAY_TO_CLIENT);
	         }
	         this.remove();
	      }
	   }
	   
	   public void setDamage(double damage) {
		   this.damage = damage;
	   }
	   
	   public double getDamage() {
		   return this.damage;
	   }
	   
	   @Override
	public float getCollisionBorderSize() {
		return this.borderSize;
	}
	   
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerData() {
		// TODO Auto-generated method stub
		
	}

}
