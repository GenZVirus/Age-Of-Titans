package com.GenZVirus.AgeOfTitans.SpellSystem;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Entities.ShockwaveEntity;
import com.GenZVirus.AgeOfTitans.Util.Helpers.ConeShape;
import com.google.common.collect.Lists;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;

public class Spell {
	
	private int id;
	private String name;
	private ResourceLocation icon;
	private ResourceLocation iconHUD;
	
	public Spell(int id, ResourceLocation icon, ResourceLocation iconHUD, String name) {
		this.id = id;
		this.icon = icon;
		this.iconHUD = iconHUD;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}
	
	public ResourceLocation getIcon() {
		return this.icon;
	}
	
	public ResourceLocation getIconHUD() {
		return this.iconHUD;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void effect(World worldIn, PlayerEntity playerIn) {
	}
	
	public static final List<Spell> SPELL_LIST = Lists.newArrayList();
	private static final Spell NO_SPELL = new Spell(0, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/noimage.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/noimage.png"), "");
	private static final Spell SHOCKWAVE = new Spell(1, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/shockwaveicon.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/shockwaveiconhud.png"), "Shockwave"){
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
	private static final Spell TEST2 = new Spell(2, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/test2.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/test2.png"), "Test2") {
		@Override
		public void effect(World worldIn, PlayerEntity playerIn) {
			double offset = 10.0D;
			double pitch = playerIn.getPitchYaw().x;
			double yaw   = playerIn.getPitchYaw().y;
			double pitchRadian = pitch * (Math.PI / 180); // X rotation
			double yawRadian   = yaw   * (Math.PI / 180); // Y rotation 
			double newPosX = offset * -Math.sin( yawRadian ) * Math.cos( pitchRadian );
			double newPosY = offset * -Math.sin( pitchRadian );
			double newPosZ = offset *  Math.cos( yawRadian ) * Math.cos( pitchRadian );
			AxisAlignedBB CUBE_BOX = VoxelShapes.fullCube().getBoundingBox();
			Vec3d pos_offset = new Vec3d(playerIn.getPosition()).add(0, 1.6D, 0);
			AxisAlignedBB aabb = CUBE_BOX.offset(pos_offset).grow(10.0D);
			ConeShape coneShape = new ConeShape(pos_offset.add(newPosX, newPosY, newPosZ), pos_offset, 10.0D, 0.1D);
			List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, aabb);
			for(Entity entity : list) {
				if(coneShape.containsPoint(entity.getPosX(), entity.getPosY(), entity.getPosZ())) {
					double distance = Math.sqrt((entity.getPosX() - playerIn.getPosX()) * (entity.getPosX() - playerIn.getPosX()) + (entity.getPosY() - playerIn.getPosY()) * (entity.getPosY() - playerIn.getPosY()) + (entity.getPosZ() - playerIn.getPosZ()) * (entity.getPosZ() - playerIn.getPosZ()));
					Vec3d vec = new Vec3d((entity.getPosX() - playerIn.getPosX()) * 10.0D, (entity.getPosY() - playerIn.getPosY()) * 10.0D, (entity.getPosZ() - playerIn.getPosZ()) * 10.0D);
					entity.setVelocity(vec.x / distance / 2, vec.y / distance / 2, vec.z / distance / 2);
					
				}
			}
			System.out.println(newPosX);
			System.out.println(newPosY);
			System.out.println(newPosZ);
			for(double k = 1.0D; Math.abs(newPosY / 10.0D) * k <= 10.0D; k += 0.5D) {
			for(double j = 1.0D; Math.abs(newPosZ / 10.0D) * j <= 10.0D; j += 0.5D) {
			for(double i = 1.0D; Math.abs(newPosX / 10.0D) * i <= 10.0D; i += 0.5D) {
			BlockPos pos = new BlockPos(new Vec3d(playerIn.getPosX() + newPosX / i, playerIn.getPosY() + newPosY / k, playerIn.getPosZ() + newPosZ / j));
			worldIn.setBlockState(pos, Blocks.GLASS.getDefaultState());
			}
			}
			}
		}
	};
	
	public static void registerSpells() {
		SPELL_LIST.add(NO_SPELL.getId(), NO_SPELL);
		SPELL_LIST.add(SHOCKWAVE.getId(), SHOCKWAVE);
		SPELL_LIST.add(TEST2.getId(), TEST2);
	}
	
}
