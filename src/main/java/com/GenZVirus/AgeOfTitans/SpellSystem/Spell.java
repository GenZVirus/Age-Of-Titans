package com.GenZVirus.AgeOfTitans.SpellSystem;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.google.common.collect.Lists;

import net.minecraft.util.ResourceLocation;

public class Spell {
	
	private int id;
	private String name;
	private ResourceLocation icon;
	
	public Spell(int id, ResourceLocation icon, String name) {
		this.id = id;
		this.icon = icon;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}
	
	public ResourceLocation getIcon() {
		return this.icon;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static final List<Spell> SPELL_LIST = Lists.newArrayList();
	private static final Spell NO_SPELL = new Spell(0, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/noimage.png"), "");
	private static final Spell TEST1 = new Spell(1, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/test.png"), "Test");	
	private static final Spell TEST2 = new Spell(2, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/test2.png"), "Test2");
	
	public static void registerSpells() {
		SPELL_LIST.add(NO_SPELL.getId(), NO_SPELL);
		SPELL_LIST.add(TEST1.getId(), TEST1);
		SPELL_LIST.add(TEST2.getId(), TEST2);
	}
	
}
