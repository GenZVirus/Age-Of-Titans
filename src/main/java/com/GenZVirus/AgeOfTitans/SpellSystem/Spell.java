package com.GenZVirus.AgeOfTitans.SpellSystem;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Entities.SwordSlashEntity;
import com.GenZVirus.AgeOfTitans.Init.EffectInit;
import com.GenZVirus.AgeOfTitans.Util.Helpers.ConeShape;
import com.google.common.collect.Lists;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
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
	private static final Spell SWORD_SLASH = new Spell(1, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/swordslashicon.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/swordslashiconhud.png"), "Sword Slash"){
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
			SwordSlashEntity shockwaveEntity = new SwordSlashEntity(playerIn.world, playerIn, newPosX, newPosY, newPosZ);
		      double d0 = (double)MathHelper.sqrt(newPosX * newPosX + newPosY * newPosY + newPosZ * newPosZ);
		      shockwaveEntity.accelerationX =  newPosX / d0 * 0.1D;
		      shockwaveEntity.accelerationY =  newPosY / d0 * 0.1D;
		      shockwaveEntity.accelerationZ =  newPosZ / d0 * 0.1D;
		      shockwaveEntity.setRawPosition(playerIn.getPosX(), 1.6 + playerIn.getPosY(), playerIn.getPosZ());
		      playerIn.world.addEntity(shockwaveEntity);
		}
		
	};	
	private static final Spell SHIELD_BASH = new Spell(2, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/shieldbashicon.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/shieldbashiconhud.png"), "Shield Bash") {
		@Override
		public void effect(World worldIn, PlayerEntity playerIn) {
			double offset = 5.0D;
			double pitch = playerIn.getPitchYaw().x;
			double yaw   = playerIn.getPitchYaw().y;
			AxisAlignedBB CUBE_BOX = VoxelShapes.fullCube().getBoundingBox();
			Vec3d pos_offset = new Vec3d(playerIn.getPosition()).add(0, 1.6D, 0);
			AxisAlignedBB aabb = CUBE_BOX.offset(pos_offset).grow(offset);
			List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, aabb);		
			double pitchRadian = pitch * (Math.PI / 180); // X rotation
			double yawRadian = yaw * (Math.PI / 180); // Y rotation 
			double newPosX = offset * -Math.sin( yawRadian ) * Math.cos( pitchRadian );
			double newPosY = offset * -Math.sin( pitchRadian );
			double newPosZ = offset *  Math.cos( yawRadian ) * Math.cos( pitchRadian );
			ConeShape coneShape = new ConeShape(pos_offset.add(newPosX, newPosY, newPosZ), pos_offset, 10.0D, 0.1D);
			for(double k = 0.0D; k <= 2 * (offset - 1); k += 1.0D) {
				for(double j = 0.0D; j <= 2 * (offset - 1); j += 1.0D) {
					for(double i = 0.0D; i <= 2 * (offset - 1); i += 1.0D) {
						BlockPos pos = new BlockPos(new Vec3d(playerIn.getPosX() - (offset - 1) + i, playerIn.getPosY() - (offset - 1) + k, playerIn.getPosZ() - (offset - 1) + j));
						if(coneShape.containsPoint(pos.getX(), pos.getY(), pos.getZ()))
							if(worldIn.getBlockState(pos).getBlock().getExplosionResistance() <= 0.3F) {
								worldIn.destroyBlock(pos, false);														
							}
					}
				}
			}
			for(Entity entity : list) {
				if(coneShape.containsPoint(entity.getPosX(), entity.getPosY(), entity.getPosZ())) {
					Vec3d vec = new Vec3d((entity.getPosX() - playerIn.getPosX()) * offset, (entity.getPosY() - playerIn.getPosY()) * offset, (entity.getPosZ() - playerIn.getPosZ()) * offset);
					entity.setVelocity(vec.x / 2, vec.y / 2, vec.z / 2);
				}
			}
			
//			ShockwaveEntity entity = new ShockwaveEntity(ModEntityTypes.SHOCKWAVE.get(), worldIn);
//			double x = playerIn.getPosX();
//			double y = playerIn.getPosY();
//			double z = playerIn.getPosZ();
//			entity.setPositionAndRotation(x, y, z,(float) yaw,(float) pitch);
//			worldIn.addEntity(entity);
		}
	};
	
	private static final Spell BERSERKER = new Spell(3, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/test2.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/test2.png"), "Berserker") {
		@Override
		public void effect(World worldIn, PlayerEntity playerIn) {
			playerIn.addPotionEffect(new EffectInstance(EffectInit.BERSERKER.get(), 600));
		}
	};
	
	public static void registerSpells() {
		SPELL_LIST.add(NO_SPELL.getId(), NO_SPELL);
		SPELL_LIST.add(SWORD_SLASH.getId(), SWORD_SLASH);
		SPELL_LIST.add(SHIELD_BASH.getId(), SHIELD_BASH);
		SPELL_LIST.add(BERSERKER.getId(), BERSERKER);
	}
	
}
