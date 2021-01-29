package com.GenZVirus.AgeOfTitans.Common.Init;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Client.Container.ContainerBasic;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes {

	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, AgeOfTitans.MOD_ID);
	
	public static final RegistryObject<ContainerType<ContainerBasic>> TITANS_LOCKER = CONTAINER_TYPES.register("black_hole", () -> IForgeContainerType.create(ContainerBasic::createContainerClientSide));

	
}
