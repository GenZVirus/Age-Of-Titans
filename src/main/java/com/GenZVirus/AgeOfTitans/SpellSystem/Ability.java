package com.GenZVirus.AgeOfTitans.SpellSystem;

import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public interface Ability {

	public void initRequirements();
	public boolean meetsRequirements();
	public int getLevel();
	public int getCooldown();
	public int getCost();
	public double getBaseAmount();
	public double getRatio();
	public void setLevel(int level);
	public void setCooldown(int cd);
	public void setCost(int cost);
	public void setBaseAmount(double amount);
	public void setRatio(double ratio);
	public void effect(World worldIn, PlayerEntity playerIn);
	public int getId();
	public String getName();
	public ResourceLocation getIcon();
	public ResourceLocation getIconOff();
	public List<String> getDescription();
	public List<String> getDetails();
	public static List<Ability> getList() {
		return null;
	}
	public List<Requirement> getRequirements();
}
