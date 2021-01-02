package com.GenZVirus.AgeOfTitans.Common.Entities;

import java.util.List;
import java.util.Random;

import com.GenZVirus.AgeOfTitans.Common.Events.Server.PlayerEventsHandler;
import com.GenZVirus.AgeOfTitans.Common.Init.ItemInit;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandlerCommon;
import com.GenZVirus.AgeOfTitans.Common.Network.ReaperInteractionPacket;
import com.GenZVirus.AgeOfTitans.Common.Network.ReaperLoadPacket;
import com.GenZVirus.AgeOfTitans.Common.Objects.Items.PricedItem;
import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombieVillagerEntity;
import net.minecraft.entity.passive.horse.ZombieHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;

public class ReaperEntity extends CreatureEntity {

	public boolean isInvulnerable = false;
	public boolean occupied = false;
	public boolean outofstock = false;
	public Item item1, item2, item3;

	@SuppressWarnings("unlikely-arg-type")
	public ReaperEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		if(worldIn.isRemote) return;
		List<PricedItem> itemList = Lists.newArrayList();
		itemList.add((PricedItem) ItemInit.ORB_OF_EDEN.get());
		itemList.add((PricedItem) ItemInit.ORB_OF_DISLOCATION.get());
		itemList.add((PricedItem) ItemInit.ORB_OF_END.get());
		itemList.add((PricedItem) ItemInit.ORB_OF_NETHER.get());
		itemList.add((PricedItem) ItemInit.ORB_OF_STORAGE.get());
		itemList.add((PricedItem) ItemInit.ORB_OF_SUMMONING.get());
		itemList.add((PricedItem) ItemInit.FRUIT_OF_THE_GODS.get());
		itemList.add((PricedItem) ItemInit.DEMONIC_DAGGER.get());

		Random rand = new Random();
		item1 = (Item) itemList.get(rand.nextInt(itemList.size()));
		itemList.remove(item1);
		item2 = (Item) itemList.get(rand.nextInt(itemList.size()));
		itemList.remove(item2);
		item3 = (Item) itemList.get(rand.nextInt(itemList.size()));
		itemList.remove(item3);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, ZombieEntity.class, true));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, SkeletonEntity.class, true));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, ZombieHorseEntity.class, true));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, ZombieVillagerEntity.class, true));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}

	@Override
	public ActionResultType applyPlayerInteraction(PlayerEntity player, Vec3d vec, Hand hand) {
		if (player.world.isRemote && !occupied) {
			displayGUI();
		}
		return super.applyPlayerInteraction(player, vec, hand);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		item1 = Item.getItemById(compound.getInt("item1"));
		item2 = Item.getItemById(compound.getInt("item2"));
		item3 = Item.getItemById(compound.getInt("item3"));
		occupied = compound.getBoolean("occupied");
		outofstock = compound.getBoolean("outofstock");
		super.readAdditional(compound);
		for(PlayerEntity player : PlayerEventsHandler.players) {
			PacketHandlerCommon.INSTANCE.sendTo(new ReaperLoadPacket(this.getEntityId(), occupied, outofstock, new ItemStack(item1), new ItemStack(item2), new ItemStack(item3)), ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		System.out.println("Writen to NBT: " + this.outofstock);
		compound.putInt("item1", Item.getIdFromItem(item1));
		compound.putInt("item2", Item.getIdFromItem(item2));
		compound.putInt("item3", Item.getIdFromItem(item3));
		compound.putBoolean("occupied", this.occupied);
		compound.putBoolean("outofstock", this.outofstock);
		super.writeAdditional(compound);
	}

	@SuppressWarnings("resource")
	@OnlyIn(Dist.CLIENT)
	private void displayGUI() {
		this.occupied = true;
		PacketHandlerCommon.INSTANCE.sendToServer(new ReaperInteractionPacket(Minecraft.getInstance().player.getUniqueID(), this.getEntityId(), occupied, outofstock, new ItemStack(item1), new ItemStack(item2), new ItemStack(item3)));
	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();
	}

	@Override
	public void livingTick() {
		super.livingTick();
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(512.0D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(50.0D);
	}

	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		super.handleStatusUpdate(id);
	}

	@Override
	public void onStruckByLightning(LightningBoltEntity lightningBolt) {
		this.setGlowing(true);
	}

	@Override
	public boolean isInvulnerable() {
		if (this.isInvulnerable)
			return true;
		return super.isInvulnerable();
	}

	@Override
	public boolean canBeCollidedWith() {
		if (isInvisible())
			return false;
		return true;
	}

	@Override
	public boolean isInvisible() {
		if (this.world.getLight(this.getPosition()) > 0) { return true; }
		return false;
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		if (this.isInvulnerable)
			return true;
		return super.isInvulnerableTo(source);
	}

}
