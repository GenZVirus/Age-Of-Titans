package com.GenZVirus.AgeOfTitans.SpellSystem;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Entities.ShockwaveEntity;
import com.google.common.collect.Lists;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

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
	
	public void effect(World worldIn, PlayerEntity playerIn) {
	}
	
	public static final List<Spell> SPELL_LIST = Lists.newArrayList();
	private static final Spell NO_SPELL = new Spell(0, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/noimage.png"), "");
	private static final Spell TEST1 = new Spell(1, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/test.png"), "Test"){
		@Override
		public void effect(World worldIn, PlayerEntity playerIn) {
			double offset = 1.0D;
			double pitch = playerIn.getPitchYaw().x;
			double yaw   = playerIn.getPitchYaw().y;
			double pitchRadian = pitch * (Math.PI / 180); // X rotation
			double yawRadian   = yaw   * (Math.PI / 180); // Y rotation 
			double newPosX = offset * -Math.sin( yawRadian ) * Math.cos( pitchRadian );
			double newPosY = offset * -Math.sin( pitchRadian );
			double newPosZ = offset *  Math.cos( yawRadian ) * Math.cos( pitchRadian );
			ShockwaveEntity shockwaveEntity = new ShockwaveEntity(playerIn.world, playerIn, newPosX, newPosY, newPosZ);
		      double d0 = (double)MathHelper.sqrt(newPosX * newPosX + newPosY * newPosY + newPosZ * newPosZ);
		      shockwaveEntity.accelerationX =  newPosX / d0 * 0.1D;
		      shockwaveEntity.accelerationY =  newPosY / d0 * 0.1D;
		      shockwaveEntity.accelerationZ =  newPosZ / d0 * 0.1D;
		      shockwaveEntity.setRawPosition(playerIn.getPosX(), 1.6 + playerIn.getPosY(), playerIn.getPosZ());
		      playerIn.world.addEntity(shockwaveEntity);
		}
		
	};	
	private static final Spell TEST2 = new Spell(2, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/test2.png"), "Test2");
	
	public static void registerSpells() {
		SPELL_LIST.add(NO_SPELL.getId(), NO_SPELL);
		SPELL_LIST.add(TEST1.getId(), TEST1);
		SPELL_LIST.add(TEST2.getId(), TEST2);
	}
	
}
