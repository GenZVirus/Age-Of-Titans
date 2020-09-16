package com.GenZVirus.AgeOfTitans.Common.Events.Client;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.TitaniumBoots;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.TitaniumChestplate;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.TitaniumHelmet;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.TitaniumLeggings;
import com.google.common.collect.Lists;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class ItemToolTip {

	@SubscribeEvent
	public static void titaniumSet(ItemTooltipEvent event) {
		if(event.getPlayer() == null) return;
		if(!(event.getItemStack().getItem() instanceof TitaniumHelmet)
				&& !(event.getItemStack().getItem() instanceof TitaniumChestplate)
				&& !(event.getItemStack().getItem() instanceof TitaniumLeggings)
				&& !(event.getItemStack().getItem() instanceof TitaniumBoots)) return;
		
		List<ITextComponent> text = Lists.newArrayList();
		text.add(event.getToolTip().get(0));
		text.add(new TranslationTextComponent(""));
		PlayerEntity player = event.getPlayer();
		List<ItemStack> armory = (List<ItemStack>) player.getArmorInventoryList();
		boolean itemInSlot = false;
		if(event.getItemStack().equals(armory.get(3)) || event.getItemStack().equals(armory.get(2)) || event.getItemStack().equals(armory.get(1)) || event.getItemStack().equals(armory.get(0))) {
			text.add(new TranslationTextComponent("\u00A7b\u00A7nSet:"));
			itemInSlot = true;
		} else {
			text.add(new TranslationTextComponent("\u00A77\u00A7nSet:"));
		}
		
		if(armory.get(3).getItem() instanceof TitaniumHelmet && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Titanium Helmet"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Titanium Helmet"));
		}
		
		if(armory.get(2).getItem() instanceof TitaniumChestplate && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Titanium Chestplate"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Titanium Chestplate"));
		}
		
		if(armory.get(1).getItem() instanceof TitaniumLeggings && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Titanium Leggings"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Titanium Leggings"));
		}
		
		if(armory.get(0).getItem() instanceof TitaniumBoots && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Titanium Boots"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Titanium Boots"));
		}
		
		text.add(new TranslationTextComponent(""));
		
		if(armory.get(3).getItem() instanceof TitaniumHelmet
				&& armory.get(2).getItem() instanceof TitaniumChestplate
				&& armory.get(1).getItem() instanceof TitaniumLeggings
				&& armory.get(0).getItem() instanceof TitaniumBoots
				&& (event.getItemStack().equals(armory.get(3)) || event.getItemStack().equals(armory.get(2)) || event.getItemStack().equals(armory.get(1)) || event.getItemStack().equals(armory.get(0)))) {
			text.add(new TranslationTextComponent("\u00A7a\u00A7nSet bonus:"));
			text.add(new TranslationTextComponent("\u00A7a(1) Getting hit by an item that has less than 7 damage or that does not have any level of unbreaking will destroy the item."));
			text.add(new TranslationTextComponent("\u00A7a(2) Getting hit by an item that has less than 10 damage has a 10% chance to disarm the attacker."));
		} else {
			text.add(new TranslationTextComponent("\u00A77\u00A7nSet bonus:"));
			text.add(new TranslationTextComponent("\u00A77(1) Getting hit by an item that has less than 7 damage or that does not have any level of unbreaking will destroy the item."));
			text.add(new TranslationTextComponent("\u00A77(2) Getting hit by an item that has less than 10 damage has a 10% chance to disarm the attacker."));
		}
		for(int index = 1; index < event.getToolTip().size(); index++) {
			text.add(event.getToolTip().get(index));
		}
		event.getToolTip().clear();
		event.getToolTip().addAll(text);
	}
	
}
