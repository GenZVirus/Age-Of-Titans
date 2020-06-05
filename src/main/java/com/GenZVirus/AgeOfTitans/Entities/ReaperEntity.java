package com.GenZVirus.AgeOfTitans.Entities;

import com.GenZVirus.AgeOfTitans.Init.ItemInit;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ReaperEntity extends CreatureEntity{
	
	private EatGrassGoal eatGrassGoal;
	private int exampleTimer;
	public boolean isInvulnerable = false;

	public ReaperEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.eatGrassGoal = new EatGrassGoal(this);
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.fromItems(ItemInit.FRUIT_OF_THE_GODS.get()), false));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	}	
	
	@Override
	protected void updateAITasks() {
		this.exampleTimer = this.eatGrassGoal.getEatingGrassTimer();
		super.updateAITasks();
	}

	@Override
	public void livingTick() {
		if(this.world.isRemote) {
			this.exampleTimer = Math.max(0, this.exampleTimer - 1);
		}
		super.livingTick();
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
	}
	 @OnlyIn(Dist.CLIENT)
	   public void handleStatusUpdate(byte id) {
	      if (id == 10) {
	         this.exampleTimer = 40;
	      } else {
	         super.handleStatusUpdate(id);
	      }

	   }

	   @OnlyIn(Dist.CLIENT)
	   public float getHeadRotationPointY(float p_70894_1_) {
	      if (this.exampleTimer <= 0) {
	         return 0.0F;
	      } else if (this.exampleTimer >= 4 && this.exampleTimer <= 36) {
	         return 1.0F;
	      } else {
	         return this.exampleTimer < 4 ? ((float)this.exampleTimer - p_70894_1_) / 4.0F : -((float)(this.exampleTimer - 40) - p_70894_1_) / 4.0F;
	      }
	   }

	   @OnlyIn(Dist.CLIENT)
	   public float getHeadRotationAngleX(float p_70890_1_) {
	      if (this.exampleTimer > 4 && this.exampleTimer <= 36) {
	         float f = ((float)(this.exampleTimer - 4) - p_70890_1_) / 32.0F;
	         return ((float)Math.PI / 5F) + 0.21991149F * MathHelper.sin(f * 28.7F);
	      } else {
	         return this.exampleTimer > 0 ? ((float)Math.PI / 5F) : this.rotationPitch * ((float)Math.PI / 180F);
	      }
	   }
	   
	   @Override
	public void onStruckByLightning(LightningBoltEntity lightningBolt) {
		this.setGlowing(true);
	}
	
	   @Override
	public boolean isInvulnerable() {
		if(this.isInvulnerable)
			return true;
		return super.isInvulnerable();
	}
	   
	   @Override
	public boolean isInvulnerableTo(DamageSource source) {
			if(this.isInvulnerable)
				return true;
		return super.isInvulnerableTo(source);
	}
	   
}
