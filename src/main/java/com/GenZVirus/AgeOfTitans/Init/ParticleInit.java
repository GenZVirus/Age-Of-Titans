package com.GenZVirus.AgeOfTitans.Init;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Particles.ChainParticle;
import com.GenZVirus.AgeOfTitans.Particles.ChainParticle.ChainParticleData;
import com.GenZVirus.AgeOfTitans.Particles.SwordSlashParticle;
import com.GenZVirus.AgeOfTitans.Particles.SwordSlashParticle.SwordSlashParticleData;

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
	
	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void registerParticleFactory(ParticleFactoryRegisterEvent event) {
		Minecraft.getInstance().particles.registerFactory(ParticleInit.CHAIN.get(), ChainParticle.Factory::new);
		Minecraft.getInstance().particles.registerFactory(ParticleInit.SWORD_SLASH.get(), SwordSlashParticle.Factory::new);
	}
}
