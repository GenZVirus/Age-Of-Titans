package com.GenZVirus.AgeOfTitans.Common.Events.Client;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Init.ItemInit;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.Armors.HeavyTitaniumArmor.HeavyTitaniumBoots;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.Armors.HeavyTitaniumArmor.HeavyTitaniumChestplate;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.Armors.HeavyTitaniumArmor.HeavyTitaniumHelmet;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.Armors.HeavyTitaniumArmor.HeavyTitaniumLeggings;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.Armors.LightTitaniumArmor.LightTitaniumBoots;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.Armors.LightTitaniumArmor.LightTitaniumChestplate;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.Armors.LightTitaniumArmor.LightTitaniumHood;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.Armors.LightTitaniumArmor.LightTitaniumLeggings;
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
public class ItemToolTipEventsHandler {

	@SubscribeEvent
	public static void heavyTitaniumArmorSet(ItemTooltipEvent event) {
		if(event.getPlayer() == null) return;
		if(!(event.getItemStack().getItem() instanceof HeavyTitaniumHelmet)
				&& !(event.getItemStack().getItem() instanceof HeavyTitaniumChestplate)
				&& !(event.getItemStack().getItem() instanceof HeavyTitaniumLeggings)
				&& !(event.getItemStack().getItem() instanceof HeavyTitaniumBoots)) return;
		
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
		
		if(armory.get(3).getItem() instanceof HeavyTitaniumHelmet && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Heavy Titanium Helmet"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Heavy Titanium Helmet"));
		}
		
		if(armory.get(2).getItem() instanceof HeavyTitaniumChestplate && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Heavy Titanium Chestplate"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Heavy Titanium Chestplate"));
		}
		
		if(armory.get(1).getItem() instanceof HeavyTitaniumLeggings && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Heavy Titanium Leggings"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Heavy Titanium Leggings"));
		}
		
		if(armory.get(0).getItem() instanceof HeavyTitaniumBoots && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Heavy Titanium Boots"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Heavy Titanium Boots"));
		}
		
		text.add(new TranslationTextComponent(""));
		
		if(armory.get(3).getItem() instanceof HeavyTitaniumHelmet
				&& armory.get(2).getItem() instanceof HeavyTitaniumChestplate
				&& armory.get(1).getItem() instanceof HeavyTitaniumLeggings
				&& armory.get(0).getItem() instanceof HeavyTitaniumBoots
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
	
	@SubscribeEvent
	public static void LightTitaniumArmorSet(ItemTooltipEvent event) {
		if(event.getPlayer() == null) return;
		if(!(event.getItemStack().getItem() instanceof LightTitaniumHood)
				&& !(event.getItemStack().getItem() instanceof LightTitaniumChestplate)
				&& !(event.getItemStack().getItem() instanceof LightTitaniumLeggings)
				&& !(event.getItemStack().getItem() instanceof LightTitaniumBoots)) return;
		
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
		
		if(armory.get(3).getItem() instanceof LightTitaniumHood && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Light Titanium Helmet"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Light Titanium Helmet"));
		}
		
		if(armory.get(2).getItem() instanceof LightTitaniumChestplate && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Light Titanium Chestplate"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Light Titanium Chestplate"));
		}
		
		if(armory.get(1).getItem() instanceof LightTitaniumLeggings && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Light Titanium Leggings"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Light Titanium Leggings"));
		}
		
		if(armory.get(0).getItem() instanceof LightTitaniumBoots && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Light Titanium Boots"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Light Titanium Boots"));
		}
		
		text.add(new TranslationTextComponent(""));
		
		if(armory.get(3).getItem() instanceof LightTitaniumHood
				&& armory.get(2).getItem() instanceof LightTitaniumChestplate
				&& armory.get(1).getItem() instanceof LightTitaniumLeggings
				&& armory.get(0).getItem() instanceof LightTitaniumBoots
				&& (event.getItemStack().equals(armory.get(3)) || event.getItemStack().equals(armory.get(2)) || event.getItemStack().equals(armory.get(1)) || event.getItemStack().equals(armory.get(0)))) {
			text.add(new TranslationTextComponent("\u00A7a\u00A7nSet bonus:"));
			text.add(new TranslationTextComponent("\u00A7a(1) Increase movement speed by 50%."));
			text.add(new TranslationTextComponent("\u00A7a(2) Grants 33.33% chance to dodge the incoming attack."));
		} else {
			text.add(new TranslationTextComponent("\u00A77\u00A7nSet bonus:"));
			text.add(new TranslationTextComponent("\u00A77(1) Increase movement speed by 50%."));
			text.add(new TranslationTextComponent("\u00A77(2) Grants 33.33% chance to dodge the incoming attack."));
		}
		for(int index = 1; index < event.getToolTip().size(); index++) {
			text.add(event.getToolTip().get(index));
		}
		event.getToolTip().clear();
		event.getToolTip().addAll(text);
	}
	
	@SubscribeEvent
	public static void titaniumSwordSet(ItemTooltipEvent event) {
		if(event.getPlayer() == null) return;
		if(!event.getItemStack().getItem().equals(ItemInit.TITANIUM_SWORD.get())) {
			return;
		}
		
		List<ITextComponent> text = Lists.newArrayList();
		text.add(event.getToolTip().get(0));
		text.add(new TranslationTextComponent(""));
		PlayerEntity player = event.getPlayer();
		boolean itemInSlot = false;
		if(event.getItemStack().equals(player.getHeldItemMainhand()) || event.getItemStack().equals(player.getHeldItemOffhand())) {
			text.add(new TranslationTextComponent("\u00A7b\u00A7nSet:"));
			itemInSlot = true;
		} else {
			text.add(new TranslationTextComponent("\u00A77\u00A7nSet:"));
		}
		
		if(player.getHeldItemMainhand().getItem().equals(ItemInit.TITANIUM_SWORD.get()) && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Titanium Sword (Mainhand)"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Titanium Sword (Mainhand)"));
		}
		
		if(player.getHeldItemOffhand().getItem().equals(ItemInit.TITANIUM_SWORD.get()) && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Titanium Sword (Offhand)"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Titanium Sword (Offhand)"));
		}
		
		if(player.getHeldItemMainhand().getItem().equals(ItemInit.TITANIUM_SWORD.get()) && player.getHeldItemOffhand().getItem().equals(ItemInit.TITANIUM_SWORD.get()) && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7a\u00A7nSet bonus:"));
			text.add(new TranslationTextComponent("\u00A7a While dual wielding a set of Titanium Sword damage dealt with them is increased by 50%."));
		} else {
			text.add(new TranslationTextComponent("\u00A77\u00A7nSet bonus:"));
			text.add(new TranslationTextComponent("\u00A77 While dual wielding a set of Titanium Sword damage dealt with them is increased by 50%."));
		}
		for(int index = 1; index < event.getToolTip().size(); index++) {
			text.add(event.getToolTip().get(index));
		}
		event.getToolTip().clear();
		event.getToolTip().addAll(text);
	}
	
	@SubscribeEvent
	public static void titaniumDaggerSet(ItemTooltipEvent event) {
		if(event.getPlayer() == null) return;
		if(!event.getItemStack().getItem().equals(ItemInit.TITANIUM_DAGGER.get())) {
			return;
		}
		
		List<ITextComponent> text = Lists.newArrayList();
		text.add(event.getToolTip().get(0));
		text.add(new TranslationTextComponent(""));
		PlayerEntity player = event.getPlayer();
		boolean itemInSlot = false;
		if(event.getItemStack().equals(player.getHeldItemMainhand()) || event.getItemStack().equals(player.getHeldItemOffhand())) {
			text.add(new TranslationTextComponent("\u00A7b\u00A7nSet:"));
			itemInSlot = true;
		} else {
			text.add(new TranslationTextComponent("\u00A77\u00A7nSet:"));
		}
		
		if(player.getHeldItemMainhand().getItem().equals(ItemInit.TITANIUM_DAGGER.get()) && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Titanium Dagger (Mainhand)"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Titanium Dagger (Mainhand)"));
		}
		
		if(player.getHeldItemOffhand().getItem().equals(ItemInit.TITANIUM_DAGGER.get()) && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Titanium Dagger (Offhand)"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Titanium Dagger (Offhand)"));
		}
		
		if((player.getHeldItemMainhand().getItem().equals(ItemInit.TITANIUM_DAGGER.get()) || event.getItemStack().equals(player.getHeldItemOffhand())) && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7a\u00A7nSet bonus:"));
			text.add(new TranslationTextComponent("\u00A7a Damaging a target has a 10% chance reduce their health by 10% of target max health. The chance is increased to 25% if dual wielding 2 daggers."));
		} else {
			text.add(new TranslationTextComponent("\u00A77\u00A7nSet bonus:"));
			text.add(new TranslationTextComponent("\u00A77 Damaging a target has a 10% chance reduce their health by 10% of target max health. The chance is increased to 25% if dual wielding 2 daggers."));
		}
		for(int index = 1; index < event.getToolTip().size(); index++) {
			text.add(event.getToolTip().get(index));
		}
		event.getToolTip().clear();
		event.getToolTip().addAll(text);
	}
	
	@SubscribeEvent
	public static void poisonousDaggerSet(ItemTooltipEvent event) {
		if(event.getPlayer() == null) return;
		if(!event.getItemStack().getItem().equals(ItemInit.POISONOUS_TITANIUM_DAGGER.get())) {
			return;
		}
		
		List<ITextComponent> text = Lists.newArrayList();
		text.add(event.getToolTip().get(0));
		text.add(new TranslationTextComponent(""));
		PlayerEntity player = event.getPlayer();
		boolean itemInSlot = false;
		if(event.getItemStack().equals(player.getHeldItemMainhand()) || event.getItemStack().equals(player.getHeldItemOffhand())) {
			text.add(new TranslationTextComponent("\u00A7b\u00A7nSet:"));
			itemInSlot = true;
		} else {
			text.add(new TranslationTextComponent("\u00A77\u00A7nSet:"));
		}
		
		if(player.getHeldItemMainhand().getItem().equals(ItemInit.POISONOUS_TITANIUM_DAGGER.get()) && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Poisonous Titanium Dagger (Mainhand)"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Poisonous Titanium Dagger (Mainhand)"));
		}
		
		if(player.getHeldItemOffhand().getItem().equals(ItemInit.POISONOUS_TITANIUM_DAGGER.get()) && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Poisonous Titanium Dagger (Offhand)"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Poisonous Titanium Dagger (Offhand)"));
		}
		
		if((player.getHeldItemMainhand().getItem().equals(ItemInit.POISONOUS_TITANIUM_DAGGER.get()) || event.getItemStack().equals(player.getHeldItemOffhand()))&& itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7a\u00A7nSet bonus:"));
			text.add(new TranslationTextComponent("\u00A7a (1)Damaging a target has a 10% chance reduce their health by 10% of target max health. The chance is increased to 25% if dual wielding 2 daggers."));
			text.add(new TranslationTextComponent("\u00A7a (2)Damaging a target inflicts poison on it. The effect is amplified for each hit up to 5. Increase amplifier limit to 10 if dual wielding 2 daggers."));
		} else {
			text.add(new TranslationTextComponent("\u00A77\u00A7nSet bonus:"));
			text.add(new TranslationTextComponent("\u00A77 (1)Damaging a target has a 10% chance reduce their health by 10% of target max health. The chance is increased to 25% if dual wielding 2 daggers."));
			text.add(new TranslationTextComponent("\u00A77 (2)Damaging a target inflicts poison on it. The effect is amplified for each hit up to 5. Increase amplifier limit to 10 if dual wielding 2 daggers."));
		}
		for(int index = 1; index < event.getToolTip().size(); index++) {
			text.add(event.getToolTip().get(index));
		}
		event.getToolTip().clear();
		event.getToolTip().addAll(text);
	}
	
	@SubscribeEvent
	public static void witheringDaggerSet(ItemTooltipEvent event) {
		if(event.getPlayer() == null) return;
		if(!event.getItemStack().getItem().equals(ItemInit.WITHERING_TITANIUM_DAGGER.get())) {
			return;
		}
		
		List<ITextComponent> text = Lists.newArrayList();
		text.add(event.getToolTip().get(0));
		text.add(new TranslationTextComponent(""));
		PlayerEntity player = event.getPlayer();
		boolean itemInSlot = false;
		if(event.getItemStack().equals(player.getHeldItemMainhand()) || event.getItemStack().equals(player.getHeldItemOffhand())) {
			text.add(new TranslationTextComponent("\u00A7b\u00A7nSet:"));
			itemInSlot = true;
		} else {
			text.add(new TranslationTextComponent("\u00A77\u00A7nSet:"));
		}
		
		if(player.getHeldItemMainhand().getItem().equals(ItemInit.WITHERING_TITANIUM_DAGGER.get()) && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Withering Titanium Dagger (Mainhand)"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Withering Titanium Dagger (Mainhand)"));
		}
		
		if(player.getHeldItemOffhand().getItem().equals(ItemInit.WITHERING_TITANIUM_DAGGER.get()) && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7b- Withering Titanium Dagger (Offhand)"));
		} else {
			text.add(new TranslationTextComponent("\u00A77- Withering Titanium Dagger (Offhand)"));
		}
		
		if((player.getHeldItemMainhand().getItem().equals(ItemInit.WITHERING_TITANIUM_DAGGER.get()) || event.getItemStack().equals(player.getHeldItemOffhand())) && itemInSlot) {
			text.add(new TranslationTextComponent("\u00A7a\u00A7nSet bonus:"));
			text.add(new TranslationTextComponent("\u00A7a (1)Damaging a target has a 10% chance reduce their health by 10% of target max health. The chance is increased to 25% if dual wielding 2 daggers."));
			text.add(new TranslationTextComponent("\u00A7a (2)Damaging a target inflicts wither effect on it. The effect is amplified for each hit up to 5. Increase amplifier limit to 10 if dual wielding 2 daggers."));
		} else {
			text.add(new TranslationTextComponent("\u00A77\u00A7nSet bonus:"));
			text.add(new TranslationTextComponent("\u00A77 (1)Damaging a target has a 10% chance reduce their health by 10% of target max health. The chance is increased to 25% if dual wielding 2 daggers."));
			text.add(new TranslationTextComponent("\u00A77 (2)Damaging a target inflicts wither effect on it. The effect is amplified for each hit up to 5. Increase amplifier limit to 10 if dual wielding 2 daggers."));
		}
		for(int index = 1; index < event.getToolTip().size(); index++) {
			text.add(event.getToolTip().get(index));
		}
		event.getToolTip().clear();
		event.getToolTip().addAll(text);
	}
	
}
