package com.GenZVirus.AgeOfTitans.Init;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {
	
	public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, AgeOfTitans.MOD_ID);
	
	public static final RegistryObject<SoundEvent> CHAIN = SOUNDS.register("entity.chain.ambient", () -> new SoundEvent(new ResourceLocation(AgeOfTitans.MOD_ID, "entity.chain.ambient")));
	public static final RegistryObject<SoundEvent> SWORD_SLASH_LAUNCH = SOUNDS.register("entity.sword_slash.launch", () -> new SoundEvent(new ResourceLocation(AgeOfTitans.MOD_ID, "entity.sword_slash.launch")));
	public static final RegistryObject<SoundEvent> SWORD_SLASH_AMBIENT = SOUNDS.register("entity.sword_slash.ambient", () -> new SoundEvent(new ResourceLocation(AgeOfTitans.MOD_ID, "entity.sword_slash.ambient")));

}
