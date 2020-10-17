package com.GenZVirus.AgeOfTitans.Common.Objects.Items.Armors.LightTitaniumArmor;

import com.GenZVirus.AgeOfTitans.Client.ArmorModel.LightTitaniumArmor.LightTitaniumHoodModel;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LightTitaniumHood extends ArmorItem{

	
	public LightTitaniumHood(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@SuppressWarnings("unchecked")
	@OnlyIn(Dist.CLIENT)
	@Override
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
		return (A) new LightTitaniumHoodModel();
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		return "ageoftitans:textures/models/armor/light_titanium_armor_hood.png";
	}
	
	
	
}
