package com.GenZVirus.AgeOfTitans.Common.Objects.Items.Daggers;

import java.util.List;

import com.GenZVirus.AgeOfTitans.Common.Objects.Items.PricedItem;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class DemonicDagger extends DaggerItem implements PricedItem{

	private int price = 0;
	
	public DemonicDagger(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
		this.price = 2000;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent(""));
		tooltip.add(new StringTextComponent("\u00A75Dark forces grant the user the power to shatter the unfortunate soul that meets the blade. Gain 1% chances to inflict lethal damage. (This effect does not change for dual wielding)"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int getPrice() {
		return this.price;
	}

}
