package com.GenZVirus.AgeOfTitans.SpellSystem;

import java.util.List;
import java.util.Random;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Config.AOTConfig;
import com.google.common.collect.Lists;

import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class PassiveAbility implements Ability {

	private int id;
	private String name;
	private ResourceLocation icon;
	private ResourceLocation iconOff;
	private ResourceLocation iconHUD;
	public int level = 0;
	public double ratio = 0;
	public int cooldown = 0;
	public double base_amount = 0;
	public int cost = 0;
	public List<Requirement> requirements = Lists.newArrayList();

	public PassiveAbility(int id, ResourceLocation icon, ResourceLocation iconOff, ResourceLocation iconHUD, String name, int level, int cost) {
		this.id = id;
		this.icon = icon;
		this.iconHUD = iconHUD;
		this.name = name;
		this.level = level;
		this.iconOff = iconOff;
		this.cost = cost;
		this.initRequirements();
	}

	public void initRequirements() {
	}

	public boolean meetsRequirements() {
		for (Requirement requirement : requirements) {
			if (!requirement.meetsRequirement())
				return false;
		}
		return true;
	}

	public void effect(World worldIn, PlayerEntity playerIn) {
	}

	public int getLevel() {
		return this.level;
	}

	public void setBaseAmount(double amount) {
		this.base_amount = amount;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public void setCooldown(int cd) {
		this.cooldown = 0;
	}

	public void setCost(int cost) {
		this.cost = 0;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getBaseAmount() {
		return this.base_amount;
	}

	public double getRatio() {
		return this.ratio;
	}

	public int getCooldown() {
		return 0;
	}

	public int getCost() {
		return 0;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public ResourceLocation getIcon() {
		return this.icon;
	}

	public ResourceLocation getIconOff() {
		return this.iconOff;
	}

	public ResourceLocation getIconHUD() {
		return this.iconHUD;
	}

	public List<String> getDescription() {
		List<String> list = Lists.newArrayList();
		return list;
	}

	public List<String> getDetails() {
		List<String> list = Lists.newArrayList();
		return list;
	}

	public static List<Ability> getList() {
		return PASSIVE_LIST;
	}

	public List<Requirement> getRequirements() {
		return this.requirements;
	}

	private static final List<Ability> PASSIVE_LIST = Lists.newArrayList();
	private static final PassiveAbility NO_SPELL = new PassiveAbility(0, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/noicon.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/noicon.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/noiconhud.png"), "", 0, 0);
	private static final PassiveAbility FORCE_FIELD = new PassiveAbility(1, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/forcefieldicon.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/forcefieldiconoff.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/forcefieldiconhud.png"), "Force Field", 0, 0) {
		@Override
		public void initRequirements() {
			this.requirements.add(new Requirement("FRUIT OF THE GODS", "\u00A74Requires the user to have eaten atleast 1 FRUIT OF THE GODS!") {
				public boolean meetsRequirement() {
					if (PlayerStats.APPLES_EATEN == 0)
						return false;
					return true;
				};
			});
			this.requirements.add(new Requirement("Level", "\u00A74Requires the user to have level 0 or above!") {
				public boolean meetsRequirement() {
					if (PlayerStats.PLAYER_LEVEL < 0)
						return false;
					return true;
				};
			});
		}

		@Override
		public void effect(World worldIn, PlayerEntity playerIn) {
			float currentShield = playerIn.getAbsorptionAmount();
			if (currentShield < this.base_amount + this.base_amount * this.ratio * this.level) {
				playerIn.setAbsorptionAmount(currentShield + 1.0F);
			}
		}

		public List<String> getDescription() {
			List<String> list = Lists.newArrayList();
			list.add("\u00A7b\u00A7n\u00A7l" + "Force Field");
			list.add("");
			list.add("While out of combat, regenerate shield points up to " + "\u00A73" + Double.toString(this.base_amount + this.base_amount * this.ratio * this.level));
			return list;
		}

		@Override
		public List<String> getDetails() {
			List<String> list = Lists.newArrayList();
			list.add("\u00A7b\u00A7n\u00A7l" + "Force Field");
			list.add("");
			list.add("While out of combat, regenerate shield points up to " + "\u00A73" + Double.toString(this.base_amount + this.base_amount * this.ratio * this.level));
			return list;
		}
	};
	private static final PassiveAbility PRESENCE_OF_A_GOD = new PassiveAbility(2, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/pogicon.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/pogiconoff.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/pogiconhud.png"), "Presence of a God", 0, 0) {
		@Override
		public void initRequirements() {
			this.requirements.add(new Requirement("FRUIT OF THE GODS", "\u00A74Requires the user to have eaten atleast 1 FRUIT OF THE GODS!") {
				public boolean meetsRequirement() {
					if (PlayerStats.APPLES_EATEN == 0)
						return false;
					return true;
				};
			});
			this.requirements.add(new Requirement("Level", "\u00A74Requires the user to have level 0 or above!") {
				public boolean meetsRequirement() {
					if (PlayerStats.PLAYER_LEVEL < 0)
						return false;
					return true;
				};
			});
		}

		@Override
		public void effect(World worldIn, PlayerEntity playerIn) {
			double offset = 5.0D;
			List<IGrowable> list = Lists.newArrayList();
			List<BlockPos> posList = Lists.newArrayList();
			Random rand = new Random();
			for (double k = 0.0D; k <= 2 * offset; k += 1.0D) {
				for (double j = 0.0D; j <= 2 * offset; j += 1.0D) {
					for (double i = 0.0D; i <= 2 * offset; i += 1.0D) {
						BlockPos pos = new BlockPos(new Vec3d(playerIn.getPosX() - offset + i, playerIn.getPosY() - offset + k, playerIn.getPosZ() - offset + j));
						BlockState blockstate = worldIn.getBlockState(pos);
						if (blockstate.getBlock() instanceof IGrowable) {
							IGrowable igrowable = (IGrowable) blockstate.getBlock();
							if (igrowable.canGrow(worldIn, pos, blockstate, worldIn.isRemote)) {
								if (worldIn instanceof ServerWorld) {
									if (igrowable.canUseBonemeal(worldIn, worldIn.rand, pos, blockstate)) {
										list.add(igrowable);
										posList.add(pos);
									}
								}
							}
						}
					}
				}
			}
			for(int i = 0; i < AOTConfig.COMMON.pog_base_amount.get() + AOTConfig.COMMON.pog_ratio.get() * this.level; i++) {
				if(list.isEmpty()) return;
				int index = list.size() > 1 ? rand.nextInt(list.size() - 1) : 0;
				list.get(index).grow((ServerWorld) worldIn, worldIn.rand, posList.get(index), worldIn.getBlockState(posList.get(index)));
			}
			
		}

		public List<String> getDescription() {
			List<String> list = Lists.newArrayList();
			list.add("\u00A7b\u00A7n\u00A7l" + "Presence of a God");
			list.add("");
			list.add("Every 5 seconds life will grow in a radius of 5 blocks. Number of plants affected: " + "\u00A73" + Integer.toString((int) (this.base_amount + this.base_amount * this.ratio * this.level)));
			return list;
		}

		@Override
		public List<String> getDetails() {
			List<String> list = Lists.newArrayList();
			list.add("\u00A7b\u00A7n\u00A7l" + "Presence of a God");
			list.add("");
			list.add("Every 5 seconds life will grow in a radius of 5 blocks. Number of plants affected: " + "\u00A73" + Double.toString(this.base_amount + this.base_amount * this.ratio * this.level));
			return list;
		}
	};

	public static void registerPassives() {
		PASSIVE_LIST.add(NO_SPELL.getId(), NO_SPELL);
		PASSIVE_LIST.add(FORCE_FIELD.getId(), FORCE_FIELD);
		PASSIVE_LIST.add(PRESENCE_OF_A_GOD.getId(), PRESENCE_OF_A_GOD);
	}
}
