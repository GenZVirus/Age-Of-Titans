package com.GenZVirus.AgeOfTitans.Common.Entities;

import java.util.List;
import java.util.Random;

import com.GenZVirus.AgeOfTitans.Common.Init.EffectInit;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandlerCommon;
import com.GenZVirus.AgeOfTitans.Common.Network.SyncPlayerMotionPacket;
import com.GenZVirus.AgeOfTitans.Common.Particles.GravityZoneCenterParticle;
import com.GenZVirus.AgeOfTitans.Common.Particles.GravityZoneParticle;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkHooks;

public class GravityZoneEnity extends Entity {

	private BlockPos permanentPos;
	private int duration = 60;
	private int level = 0;
	
	public GravityZoneEnity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	@Override
	public void setRawPosition(double x, double y, double z) {
		this.permanentPos = new BlockPos(x, y, z);
		super.setRawPosition(x, y, z);
	}
	
	@Override
	public void tick() {
		this.world.addParticle(new GravityZoneCenterParticle.GravityZoneCenterParticleData(1.0F, 1.0F, 1.0F, 1.0F), this.permanentPos.getX(), this.permanentPos.getY(), this.permanentPos.getZ(), 0, 0, 0);
		Random rand = new Random();
		for(int i = 0; i < 40; i++) {
			double x = this.permanentPos.getX() + rand.nextDouble() * (rand.nextInt(10) - 5);
			double y = this.permanentPos.getY() + rand.nextDouble() * (rand.nextInt(10) - 5);
			double z = this.permanentPos.getZ() + rand.nextDouble() * (rand.nextInt(10) - 5);
			Vec3d vec = new Vec3d(this.permanentPos.getX() - x, this.permanentPos.getY() - y, this.permanentPos.getZ() - z);
			this.world.addParticle(new GravityZoneParticle.GravityZoneParticleData(1.0F, 1.0F, 1.0F, 1.0F), x, y, z, vec.x / 4, vec.y / 4, vec.z / 4);
		}
		super.tick();
		if(this.duration <= 0) {
			this.remove();
		}
		this.duration--;
		this.setPositionAndUpdate(this.permanentPos.getX(), this.permanentPos.getY(), this.permanentPos.getZ());
		AxisAlignedBB CUBE_BOX = VoxelShapes.fullCube().getBoundingBox();
		Vec3d pos_offset = new Vec3d(this.getPosition());
		AxisAlignedBB aabb = CUBE_BOX.offset(pos_offset).grow(5.0D);
		List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, aabb);
		
		for(Entity entity : list) {
			if(entity instanceof LivingEntity && entity.isNonBoss()) {
				((LivingEntity)entity).addPotionEffect(new EffectInstance(EffectInit.GRAVITY_FIELD.get(), this.duration, this.level));
			}
			Vec3d vec = new Vec3d(this.permanentPos.getX() - entity.getPosX(), this.permanentPos.getY() - entity.getPosY(), this.permanentPos.getZ() - entity.getPosZ());
			entity.setMotion(vec.x / 4, vec.y / 4, vec.z / 4);
			if(entity.world.isRemote) return;
			if(entity instanceof PlayerEntity) {
				PacketHandlerCommon.INSTANCE.sendTo(new SyncPlayerMotionPacket(entity.getUniqueID(), vec.getX() / 4 , vec.getY() / 4, vec.getZ() / 4), ((ServerPlayerEntity)entity).connection.getNetworkManager(),	NetworkDirection.PLAY_TO_CLIENT);
			}
		}		
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	@Override
	public boolean isInvulnerable() {
		return true;
	}
	
	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return true;
	}
	
	@Override
	protected void registerData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readAdditional(CompoundNBT compound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
