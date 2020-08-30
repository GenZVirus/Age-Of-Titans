package com.GenZVirus.AgeOfTitans.Common.Objects.Items;

import net.minecraft.item.Item;

public class PricedItem extends Item {

	public int price = 0;
	
	public PricedItem(Properties properties) {
		super(properties);
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
	
}
