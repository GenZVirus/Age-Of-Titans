package com.GenZVirus.AgeOfTitans.Common.Entities;

import com.GenZVirus.AgeOfTitans.Common.Init.ModEntityTypes;
import com.GenZVirus.AgeOfTitans.Common.Particles.GravityBombParticle;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class GravityBombEntity extends ThrowableEntity {

	private double bonusDamage = 0;
	private int level = 0;

	public GravityBombEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public GravityBombEntity(EntityType<? extends ThrowableEntity> type, double x, double y, double z, World worldIn) {
		super(type, x, y, z, worldIn);
	}

	@Override
	public void tick() {
		super.tick();
		this.world.addParticle(new GravityBombParticle.GravityBombParticleData(1.0F, 1.0F, 1.0F, 1.0F), this.getPosX(), this.getPosY(), this.getPosZ(), 0, 0, 0);
	}
	
	@Override
	protected void onImpact(RayTraceResult result) {
		GravityZoneEnity entity = new GravityZoneEnity(ModEntityTypes.GRAVITY_ZONE.get(), this.world);
		entity.setRawPosition(this.getPosX(), this.getPosY(), this.getPosZ());
		entity.setLevel(this.level);
		this.world.addEntity(entity);
		this.remove();

	}

	public void setBonusDamage(double damage) {
		this.bonusDamage = damage;
	}

	public double getBonusDamage() {
		return this.bonusDamage;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	protected void registerData() {
		// TODO Auto-generated method stub

	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
