package com.GenZVirus.AgeOfTitans.Common.Init;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Particles.ChainParticle;
import com.GenZVirus.AgeOfTitans.Common.Particles.ChainParticle.ChainParticleData;
import com.GenZVirus.AgeOfTitans.Common.Particles.GravityBombParticle;
import com.GenZVirus.AgeOfTitans.Common.Particles.GravityBombParticle.GravityBombParticleData;
import com.GenZVirus.AgeOfTitans.Common.Particles.GravityZoneCenterParticle;
import com.GenZVirus.AgeOfTitans.Common.Particles.GravityZoneCenterParticle.GravityZoneCenterParticleData;
import com.GenZVirus.AgeOfTitans.Common.Particles.GravityZoneParticle;
import com.GenZVirus.AgeOfTitans.Common.Particles.GravityZoneParticle.GravityZoneParticleData;
import com.GenZVirus.AgeOfTitans.Common.Particles.SwordSlashParticle;
import com.GenZVirus.AgeOfTitans.Common.Particles.SwordSlashParticle.SwordSlashParticleData;

import net.minecraft.client.Minecraft;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ParticleInit {

	public static final DeferredRegister<ParticleType<?>> PARTICLES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, AgeOfTitans.MOD_ID);

	public static final RegistryObject<ParticleType<ChainParticleData>> CHAIN = PARTICLES.register("chain", () -> new ParticleType<ChainParticleData>(false, ChainParticleData.DESERIALIZER));
	public static final RegistryObject<ParticleType<SwordSlashParticleData>> SWORD_SLASH = PARTICLES.register("swordslash", () -> new ParticleType<SwordSlashParticleData>(false, SwordSlashParticleData.DESERIALIZER));
	public static final RegistryObject<ParticleType<GravityZoneCenterParticleData>> GRAVITY_ZONE_CENTER = PARTICLES.register("gravity_zone_center", () -> new ParticleType<GravityZoneCenterParticleData>(false, GravityZoneCenterParticleData.DESERIALIZER));
	public static final RegistryObject<ParticleType<GravityZoneParticleData>> GRAVITY_ZONE = PARTICLES.register("gravity_zone", () -> new ParticleType<GravityZoneParticleData>(false, GravityZoneParticleData.DESERIALIZER));
	public static final RegistryObject<ParticleType<GravityBombParticleData>> GRAVITY_BOMB = PARTICLES.register("gravity_bomb", () -> new ParticleType<GravityBombParticleData>(false, GravityBombParticleData.DESERIALIZER));
	
	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void registerParticleFactory(ParticleFactoryRegisterEvent event) {
		Minecraft.getInstance().particles.registerFactory(ParticleInit.CHAIN.get(), ChainParticle.Factory::new);
		Minecraft.getInstance().particles.registerFactory(ParticleInit.SWORD_SLASH.get(), SwordSlashParticle.Factory::new);
		Minecraft.getInstance().particles.registerFactory(ParticleInit.GRAVITY_ZONE_CENTER.get(), GravityZoneCenterParticle.Factory::new);
		Minecraft.getInstance().particles.registerFactory(ParticleInit.GRAVITY_ZONE.get(), GravityZoneParticle.Factory::new);
		Minecraft.getInstance().particles.registerFactory(ParticleInit.GRAVITY_BOMB.get(), GravityBombParticle.Factory::new);
	}
}
