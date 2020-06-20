package com.GenZVirus.AgeOfTitans.Entities;

import com.GenZVirus.AgeOfTitans.Init.ModEntityTypes;
import com.GenZVirus.AgeOfTitans.Network.PacketHandler;
import com.GenZVirus.AgeOfTitans.Network.SyncPlayerMotionPacket;
import com.GenZVirus.AgeOfTitans.Particles.ChainParticle;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkHooks;

public class ChainEntity extends DamagingProjectileEntity{

	public boolean isInvulnerable = true;
	public int timeLife = 20;
	public PlayerEntity shooter;
	public float borderSize = 0.1F;
	
	public ChainEntity(EntityType<? extends DamagingProjectileEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	public ChainEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
	      super(ModEntityTypes.CHAIN.get(), shooter, accelX, accelY, accelZ, worldIn);
	      this.shooter = (PlayerEntity) shooter;
	   }

	@Override
	public void tick() {
		this.timeLife--;
		if(this.timeLife <= 0)
			this.remove();
        Vec3d vec3d = this.getMotion();
        double d0 = this.getPosX() + vec3d.x * 2;
        double d1 = this.getPosY() + vec3d.y * 2;
        double d2 = this.getPosZ() + vec3d.z * 2;
		this.world.addParticle(this.getParticle(), d0, d1 + 0.5D, d2, 0, 0, 0);
		d0 = this.getPosX() + vec3d.x * 3;
        d1 = this.getPosY() + vec3d.y * 3;
        d2 = this.getPosZ() + vec3d.z * 3;
		this.world.addParticle(this.getParticle(), d0, d1 + 0.5D, d2, 0, 0, 0);
		if(this.timeLife < 18)
		super.tick();
		
	}
	
	/**
	    * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
	    */
	   public boolean isBurning() {
	      return false;
	   }
	
	/**
	    * Called when this ChainEntity hits a block or entity.
	    */
	   protected void onImpact(RayTraceResult result) {

		   if (!this.world.isRemote) {
	         if (result.getType() == RayTraceResult.Type.ENTITY) {
	            Entity entity = ((EntityRayTraceResult)result).getEntity();
	            if (this.shootingEntity != null) {
	            	entity.setPosition(this.shootingEntity.getPosX(), this.shootingEntity.getPosY(), this.shootingEntity.getPosZ());
	               entity.attackEntityFrom(DamageSource.MAGIC, 5.0F);
	            }
	         }
	         if(result.getType() == RayTraceResult.Type.BLOCK && this.shooter != null) {
	        	 BlockPos pos = ((BlockRayTraceResult)result).getPos();
	        	 Vec3d vec = new Vec3d((double)pos.getX() - shooter.getPosX(), (double)pos.getY() - shooter.getPosY(), (double)pos.getZ() - shooter.getPosZ());
	        	 shooter.setMotion(vec);
					PacketHandler.INSTANCE.sendTo(new SyncPlayerMotionPacket(this.shooter.getUniqueID(), vec.getX() / vec.length() * 3, vec.getY() / vec.length() * 3 + 0.5D, vec.getZ() / vec.length() * 3), ((ServerPlayerEntity)this.shooter).connection.getNetworkManager(),	NetworkDirection.PLAY_TO_CLIENT);
	         }
	         this.remove();
	      }
	      super.onImpact(result);
	   }

	   protected boolean isFireballFiery() {
		      return false;
		   }
	   
	   protected IParticleData getParticle() {
		      return new ChainParticle.ChainParticleData(1.0f, 1.0f, 1.0f, 1.0f);
		   }
	   
	   @Override
	public float getCollisionBorderSize() {
		return this.borderSize;
	}
	   
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
