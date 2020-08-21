package com.GenZVirus.AgeOfTitans.SpellSystem;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Config.AOTConfig;
import com.GenZVirus.AgeOfTitans.Common.Entities.ChainEntity;
import com.GenZVirus.AgeOfTitans.Common.Entities.GravityBombEntity;
import com.GenZVirus.AgeOfTitans.Common.Entities.SwordSlashEntity;
import com.GenZVirus.AgeOfTitans.Common.Init.EffectInit;
import com.GenZVirus.AgeOfTitans.Common.Init.ModEntityTypes;
import com.GenZVirus.AgeOfTitans.Common.Init.SoundInit;
import com.GenZVirus.AgeOfTitans.Common.Network.PacketHandlerCommon;
import com.GenZVirus.AgeOfTitans.Common.Network.SyncPlayerMotionPacket;
import com.GenZVirus.AgeOfTitans.Util.Helpers.ConeShape;
import com.google.common.collect.Lists;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;

public class Spell {

	public static int POINTS = 0;
	public static int APPLES_EATEN = 0;
	public static int PLAYER_LEVEL = 0;
	public static int RAGE_POINTS = 0;
	public static int MAX_RAGE_POINTS = 1000;

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

	public Spell(int id, ResourceLocation icon, ResourceLocation iconOff, ResourceLocation iconHUD, String name, int level, int cost) {
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

	public static final List<Spell> SPELL_LIST = Lists.newArrayList();
	private static final Spell NO_SPELL = new Spell(0, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/noicon.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/noicon.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/noiconhud.png"), "", 0, 0);
	private static final Spell SWORD_SLASH = new Spell(1, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/swordslashicon.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/swordslashiconoff.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/swordslashiconhud.png"), "Sword Slash", 0, AOTConfig.COMMON.sword_slash_cost.get()) {

		@Override
		public void initRequirements() {
			this.requirements.add(new Requirement("FRUIT OF THE GODS", "\u00A74Requires the user to have eaten atleast 1 FRUIT OF THE GODS!") {
				public boolean meetsRequirement() {
					if (Spell.APPLES_EATEN == 0)
						return false;
					return true;
				};
			});
			this.requirements.add(new Requirement("Level", "\u00A74Requires the user to have level 0 or above!") {
				public boolean meetsRequirement() {
					if (Spell.PLAYER_LEVEL < 0)
						return false;
					return true;
				};
			});
		}

		@Override
		public void effect(World worldIn, PlayerEntity playerIn) {
			double offset = 1.0D;
			double pitch = playerIn.getPitchYaw().x;
			double yaw = playerIn.getPitchYaw().y;
			double pitchRadian = pitch * (Math.PI / 180); // X rotation
			double yawRadian = yaw * (Math.PI / 180); // Y rotation
			double newPosX = offset * -Math.sin(yawRadian) * Math.cos(pitchRadian);
			double newPosY = offset * -Math.sin(pitchRadian);
			double newPosZ = offset * Math.cos(yawRadian) * Math.cos(pitchRadian);
			SwordSlashEntity swordslashentity = new SwordSlashEntity(playerIn.world, playerIn, newPosX, newPosY, newPosZ);
			double d0 = (double) MathHelper.sqrt(newPosX * newPosX + newPosY * newPosY + newPosZ * newPosZ);
			swordslashentity.accelerationX = newPosX / d0 * 0.1D;
			swordslashentity.accelerationY = newPosY / d0 * 0.1D;
			swordslashentity.accelerationZ = newPosZ / d0 * 0.1D;
			swordslashentity.rotationPitch = (float) pitch;
			swordslashentity.rotationYaw = (float) yaw;
			swordslashentity.setRawPosition(playerIn.getPosX(), 1.0D + playerIn.getPosY(), playerIn.getPosZ());
			swordslashentity.setDamage(AOTConfig.COMMON.sword_slash_base_damage.get() + AOTConfig.COMMON.sword_slash_damage_ratio.get() * this.level);
			playerIn.world.addEntity(swordslashentity);
			playerIn.world.playSound(null, playerIn.getPosition(), SoundInit.SWORD_SLASH_LAUNCH.get(), SoundCategory.AMBIENT, 1.0F, 1.0F);
		}

		public List<String> getDescription() {
			List<String> list = Lists.newArrayList();
			list.add("Sword Slash is a powerful ability that empowers the users sword with unending burning flames. " + "Upon swinging the sword the flames will fly towards the target creating an fire explosion on hit and damaging all nearby targets.");
			list.add("");
			list.add("Hold Shift for details");
			return list;
		}

		@Override
		public List<String> getDetails() {
			List<String> list = Lists.newArrayList();
			list.add("Damage " + "(" + Double.toString(this.base_amount + this.ratio * this.level) + "): " + "\u00A73" + Double.toString(this.base_amount) + "\u00A7f" + " + " + "\u00A7e" + Double.toString(this.ratio) + "\u00A7f" + " * " + "\u00A74" + Integer.toString(this.level));
			list.add("");
			list.add("Cooldown: " + Integer.toString(this.cooldown) + " seconds");
			list.add("");
			list.add("Cost: " + this.cost + " rage points");
			list.add("");
			list.add("\u00A73" + "Base " + "\u00A7e" + "Ratio " + "\u00A74" + "Level ");
			return list;
		}

	};
	private static final Spell SHIELD_BASH = new Spell(2, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/shieldbashicon.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/shieldbashiconoff.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/shieldbashiconhud.png"), "Shield Bash", 0, AOTConfig.COMMON.shield_bash_cost.get()) {

		@Override
		public void initRequirements() {
			this.requirements.add(new Requirement("FRUIT OF THE GODS", "\u00A74Requires the user to have eaten atleast 1 FRUIT OF THE GODS!") {
				public boolean meetsRequirement() {
					if (Spell.APPLES_EATEN == 0)
						return false;
					return true;
				};
			});
			this.requirements.add(new Requirement("Level", "\u00A74Requires the user to have level 20 or above!") {
				public boolean meetsRequirement() {
					if (Spell.PLAYER_LEVEL < 20)
						return false;
					return true;
				};
			});
		}

		@SuppressWarnings("deprecation")
		@Override
		public void effect(World worldIn, PlayerEntity playerIn) {
			double offset = 5.0D;
			double pitch = playerIn.getPitchYaw().x;
			double yaw = playerIn.getPitchYaw().y;
			AxisAlignedBB CUBE_BOX = VoxelShapes.fullCube().getBoundingBox();
			Vec3d pos_offset = new Vec3d(playerIn.getPosition()).add(0, 1.6D, 0);
			AxisAlignedBB aabb = CUBE_BOX.offset(pos_offset).grow(offset);
			List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, aabb);
			double pitchRadian = pitch * (Math.PI / 180); // X rotation
			double yawRadian = yaw * (Math.PI / 180); // Y rotation
			double newPosX = offset * -Math.sin(yawRadian) * Math.cos(pitchRadian);
			double newPosY = offset * -Math.sin(pitchRadian);
			double newPosZ = offset * Math.cos(yawRadian) * Math.cos(pitchRadian);
			ConeShape coneShape = new ConeShape(pos_offset.add(newPosX, newPosY, newPosZ), pos_offset, 10.0D, 0.1D);
			for (double k = 0.0D; k <= 2 * (offset - 1); k += 1.0D) {
				for (double j = 0.0D; j <= 2 * (offset - 1); j += 1.0D) {
					for (double i = 0.0D; i <= 2 * (offset - 1); i += 1.0D) {
						BlockPos pos = new BlockPos(new Vec3d(playerIn.getPosX() - (offset - 1) + i, playerIn.getPosY() - (offset - 1) + k, playerIn.getPosZ() - (offset - 1) + j));
						if (coneShape.containsPoint(pos.getX(), pos.getY(), pos.getZ()))
							if (worldIn.getBlockState(pos).getBlock().getExplosionResistance() <= 0.3F) {
								worldIn.destroyBlock(pos, false);
							}
					}
				}
			}
			for (Entity entity : list) {
				if (coneShape.containsPoint(entity.getPosX(), entity.getPosY(), entity.getPosZ())) {
					Vec3d vec = new Vec3d((entity.getPosX() - playerIn.getPosX()) * offset, (entity.getPosY() - playerIn.getPosY()) * offset, (entity.getPosZ() - playerIn.getPosZ()) * offset);
					entity.setMotion(vec.x / 2, vec.y / 2, vec.z / 2);
					entity.attackEntityFrom(DamageSource.MAGIC, (float) (AOTConfig.COMMON.shield_bash_base_damage.get() + AOTConfig.COMMON.shield_bash_damage_ratio.get() * this.level));
					if (entity instanceof PlayerEntity) {
						PacketHandlerCommon.INSTANCE.sendTo(new SyncPlayerMotionPacket(entity.getUniqueID(), vec.getX() / 2, vec.getY() / 2, vec.getZ() / 2), ((ServerPlayerEntity) entity).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
					}
				}
			}

			playerIn.world.playSound(null, playerIn.getPosition(), SoundInit.SWORD_SLASH_LAUNCH.get(), SoundCategory.AMBIENT, 1.0F, 1.0F);
		}

		public List<String> getDescription() {
			List<String> list = Lists.newArrayList();
			list.add("Shield Bash turns a any shield into a deadly weapon. " + "Upon blocking, all targets in front of the shield will be knockbacked. " + "Blocks with weak resistance will colaps when hit by the shockwave.");
			list.add("");
			list.add("Hold Shift for details");
			return list;
		}

		@Override
		public List<String> getDetails() {
			List<String> list = Lists.newArrayList();
			list.add("Damage " + "(" + Double.toString(this.base_amount + this.ratio * this.level) + "): " + "\u00A73" + Double.toString(this.base_amount) + "\u00A7f" + " + " + "\u00A7e" + Double.toString(this.ratio) + "\u00A7f" + " * " + "\u00A74" + Integer.toString(this.level));
			list.add("");
			list.add("Cooldown: " + Integer.toString(this.cooldown) + " seconds");
			list.add("");
			list.add("Cost: " + this.cost + " rage points");
			list.add("");
			list.add("\u00A73" + "Base " + "\u00A7e" + "Ratio " + "\u00A74" + "Level ");
			return list;
		}

	};

	private static final Spell BERSERKER = new Spell(3, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/berserkericon.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/berserkericonoff.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/berserkericonhud.png"), "Berserker", 0, AOTConfig.COMMON.berserker_cost.get()) {

		@Override
		public void initRequirements() {
			this.requirements.add(new Requirement("FRUIT OF THE GODS", "\u00A74Requires the user to have eaten atleast 1 FRUIT OF THE GODS!") {
				public boolean meetsRequirement() {
					if (Spell.APPLES_EATEN == 0)
						return false;
					return true;
				};
			});
			this.requirements.add(new Requirement("Level", "\u00A74Requires the user to have level 40 or above!") {
				public boolean meetsRequirement() {
					if (Spell.PLAYER_LEVEL < 40)
						return false;
					return true;
				};
			});
		}

		@Override
		public void effect(World worldIn, PlayerEntity playerIn) {
			playerIn.addPotionEffect(new EffectInstance(EffectInit.BERSERKER.get(), (int) (400 + 20 * AOTConfig.COMMON.berserker_duration_ratio.get() * this.level)));
		}

		public List<String> getDescription() {
			List<String> list = Lists.newArrayList();
			list.add("Berserker is all or nothing. " + "Empowers the user, giving him strength and speed. Jumping over mountains is an easy fit. " + "Even blocks and enemies can't resist your punches. " + "If the target enemy doesn't have an helmet to protect his skull, a critical hit will be their undoing.");
			list.add("");
			list.add("Hold Shift for details");
			return list;
		}

		@Override
		public List<String> getDetails() {
			List<String> list = Lists.newArrayList();
			list.add("Bonus damage: " + "\u00A73" + Double.toString(this.base_amount));
			list.add("");
			list.add("Duration " + "(" + Double.toString(20.0D + this.ratio * this.level) + "): " + "\u00A73" + "20" + "\u00A7f" + " + " + "\u00A7e" + Double.toString(this.ratio) + "\u00A7f" + " * " + "\u00A74" + Integer.toString(this.level) + "\u00A7f" + " seconds");
			list.add("");
			list.add("Cooldown: " + Integer.toString(this.cooldown) + " seconds");
			list.add("");
			list.add("Cost: " + this.cost + " rage points");
			list.add("");
			list.add("\u00A73" + "Base " + "\u00A7e" + "Ratio " + "\u00A74" + "Level ");
			return list;
		}

	};

	private static final Spell CHAIN = new Spell(4, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/chainicon.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/chainiconoff.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/chainiconhud.png"), "Chain", 0, AOTConfig.COMMON.chain_cost.get()) {

		@Override
		public void initRequirements() {
			this.requirements.add(new Requirement("FRUIT OF THE GODS", "\u00A74Requires the user to have eaten atleast 1 FRUIT OF THE GODS!") {
				public boolean meetsRequirement() {
					if (Spell.APPLES_EATEN == 0)
						return false;
					return true;
				};
			});
			this.requirements.add(new Requirement("Level", "\u00A74Requires the user to have level 20 or above!") {
				public boolean meetsRequirement() {
					if (Spell.PLAYER_LEVEL < 20)
						return false;
					return true;
				};
			});
		}

		@Override
		public void effect(World worldIn, PlayerEntity playerIn) {
			double offset = 1.0D;
			double pitch = playerIn.getPitchYaw().x;
			double yaw = playerIn.getPitchYaw().y;
			double pitchRadian = pitch * (Math.PI / 180); // X rotation
			double yawRadian = yaw * (Math.PI / 180); // Y rotation
			double newPosX = offset * -Math.sin(yawRadian) * Math.cos(pitchRadian);
			double newPosY = offset * -Math.sin(pitchRadian);
			double newPosZ = offset * Math.cos(yawRadian) * Math.cos(pitchRadian);
			ChainEntity chainEntity = new ChainEntity(playerIn.world, playerIn, newPosX, newPosY, newPosZ);
			chainEntity.shoot(playerIn, (float) pitch, (float) yaw, 0.0F, 1.5F, 0.0F);
			chainEntity.setRawPosition(playerIn.getPosX(), 1.0D + playerIn.getPosY(), playerIn.getPosZ());
			chainEntity.setDamage(AOTConfig.COMMON.chain_base_damage.get() + AOTConfig.COMMON.chain_damage_ratio.get() * this.level);
			playerIn.world.addEntity(chainEntity);
			playerIn.world.playSound(null, playerIn.getPosition(), SoundInit.CHAIN.get(), SoundCategory.AMBIENT, 1.0F, 1.0F);
		}

		public List<String> getDescription() {
			List<String> list = Lists.newArrayList();
			list.add("Chain provides high utility. " + "Giving the user mobility by hooking blocks to thrust themselves towards the blocks. " + "Or hook an enemy to pull them towards them.");
			list.add("");
			list.add("Hold Shift for details");
			return list;
		}

		@Override
		public List<String> getDetails() {
			List<String> list = Lists.newArrayList();
			list.add("Damage " + "(" + Double.toString(this.base_amount + this.ratio * this.level) + "): " + "\u00A73" + Double.toString(this.base_amount) + "\u00A7f" + " + " + "\u00A7e" + Double.toString(this.ratio) + "\u00A7f" + " * " + "\u00A74" + Integer.toString(this.level));
			list.add("");
			list.add("Cooldown: " + Integer.toString(this.cooldown) + " seconds");
			list.add("");
			list.add("Cost: " + this.cost + " rage points");
			list.add("");
			list.add("\u00A73" + "Base " + "\u00A7e" + "Ratio " + "\u00A74" + "Level ");
			return list;
		}

	};

	private static final Spell GRAVITY_BOMB = new Spell(5, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/gravitybombicon.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/gravitybombiconoff.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/gravitybombiconhud.png"), "Gravity Bomb", 0, AOTConfig.COMMON.gravity_bomb_cost.get()) {

		@Override
		public void initRequirements() {
			this.requirements.add(new Requirement("FRUIT OF THE GODS", "\u00A74Requires the user to have eaten atleast 1 FRUIT OF THE GODS!") {
				public boolean meetsRequirement() {
					if (Spell.APPLES_EATEN == 0)
						return false;
					return true;
				};
			});
			this.requirements.add(new Requirement("Level", "\u00A74Requires the user to have level 20 or above!") {
				public boolean meetsRequirement() {
					if (Spell.PLAYER_LEVEL < 20)
						return false;
					return true;
				};
			});
		}

		@Override
		public void effect(World worldIn, PlayerEntity playerIn) {
			double pitch = playerIn.getPitchYaw().x;
			double yaw = playerIn.getPitchYaw().y;
			GravityBombEntity gravityBombEntity = new GravityBombEntity(ModEntityTypes.GRAVITY_BOMB.get(), playerIn.world);
			gravityBombEntity.shoot(playerIn, (float) pitch, (float) yaw, 0.0F, 1.5F, 0.0F);
			gravityBombEntity.setRawPosition(playerIn.getPosX(), 1.0D + playerIn.getPosY(), playerIn.getPosZ());
			gravityBombEntity.setBonusDamage(AOTConfig.COMMON.gravity_bomb_bonus_damage.get());
			gravityBombEntity.setLevel(this.level == 0 ? 0 : this.level - 1);
			playerIn.world.addEntity(gravityBombEntity);
		}

		public List<String> getDescription() {
			List<String> list = Lists.newArrayList();
			list.add("Gravity Bomb provides high utility. " + "On impact pulls all entities, including the caster, into the center of the gravitational field increasing the damage taken. Boss Entities do not take increase damage.");
			list.add("");
			list.add("Hold Shift for details");
			return list;
		}

		@Override
		public List<String> getDetails() {
			List<String> list = Lists.newArrayList();
			list.add("Bonus Damage " + "(x" + Double.toString(this.base_amount + this.ratio * this.level) + "): " + "\u00A73" + Double.toString(this.base_amount) + "\u00A7f" + " + " + "\u00A7e" + Double.toString(this.ratio) + "\u00A7f" + " * " + "\u00A74" + Integer.toString(this.level));
			list.add("");
			list.add("Duration: " + "\u00A73" + "3" + "\u00A7f" + " seconds");
			list.add("");
			list.add("Cooldown: " + Integer.toString(this.cooldown) + " seconds");
			list.add("");
			list.add("Cost: " + this.cost + " rage points");
			list.add("");
			list.add("\u00A73" + "Base " + "\u00A7e" + "Ratio " + "\u00A74" + "Level ");
			return list;
		}

	};

	private static final Spell REVITALISE = new Spell(6, new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/revitaliseicon.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/revitaliseiconoff.png"), new ResourceLocation(AgeOfTitans.MOD_ID, "textures/gui/revitaliseiconhud.png"), "Revitalise", 0, AOTConfig.COMMON.revitalise_cost.get()) {

		@Override
		public void initRequirements() {
			this.requirements.add(new Requirement("FRUIT OF THE GODS", "\u00A74Requires the user to have eaten atleast 1 FRUIT OF THE GODS!") {
				public boolean meetsRequirement() {
					if (Spell.APPLES_EATEN == 0)
						return false;
					return true;
				};
			});
			this.requirements.add(new Requirement("Level", "\u00A74Requires the user to have level 0 or above!") {
				public boolean meetsRequirement() {
					if (Spell.PLAYER_LEVEL < 0)
						return false;
					return true;
				};
			});
		}

		@Override
		public void effect(World worldIn, PlayerEntity playerIn) {
			playerIn.addPotionEffect(new EffectInstance(EffectInit.REVITALISE.get(), 100));
			playerIn.world.playSound(null, playerIn.getPosition(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.AMBIENT, 1.0F, 1.0F);
		}

		public List<String> getDescription() {
			List<String> list = Lists.newArrayList();
			list.add("Revitalise heals the user overtime. While the health of the user is below the maximum he has, Revitalise will replenish what is missing as long as the user has rage points.");
			list.add("");
			list.add("Hold Shift for details");
			return list;
		}

		@Override
		public List<String> getDetails() {
			List<String> list = Lists.newArrayList();
			list.add("Healing amount per tick " + "(" + Double.toString(this.base_amount + this.ratio * this.level) + "): " + "\u00A73" + Double.toString(base_amount) + "\u00A7f" + " + " + "\u00A7e" + Double.toString(this.ratio) + "\u00A7f" + " * " + "\u00A74" + Integer.toString(this.level) + "\u00A7f" + " hps");
			list.add("");
			list.add("Cooldown: " + Integer.toString(this.cooldown) + " seconds");
			list.add("");
			list.add("Cost: " + this.cost + " rage points");
			list.add("");
			list.add("\u00A73" + "Base " + "\u00A7e" + "Ratio " + "\u00A74" + "Level ");
			return list;
		}

	};

	public static void registerSpells() {
		SPELL_LIST.add(NO_SPELL.getId(), NO_SPELL);
		SPELL_LIST.add(SWORD_SLASH.getId(), SWORD_SLASH);
		SPELL_LIST.add(SHIELD_BASH.getId(), SHIELD_BASH);
		SPELL_LIST.add(BERSERKER.getId(), BERSERKER);
		SPELL_LIST.add(CHAIN.getId(), CHAIN);
		SPELL_LIST.add(GRAVITY_BOMB.getId(), GRAVITY_BOMB);
		SPELL_LIST.add(REVITALISE.getId(), REVITALISE);
	}

	public class Requirement {

		private String name;
		private String description;

		public Requirement(String name, String description) {
			this.name = name;
			this.description = description;
		}

		public boolean meetsRequirement() {
			return false;
		}

		public String getDescription() {
			return description;
		}

		public String getName() {
			return name;
		}

	}

}
