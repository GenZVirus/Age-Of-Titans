package com.GenZVirus.AgeOfTitans.Common.Config;

import org.apache.commons.lang3.tuple.Pair;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.MOD)
public abstract class AOTConfig {

	public static class Client {
		
	}
	
	public static class Common {
		
		public final DoubleValue sword_slash_damage_ratio;
		public final DoubleValue sword_slash_base_damage;
		public final IntValue sword_slash_cooldown;
		public final IntValue sword_slash_cost;

		public final DoubleValue shield_bash_damage_ratio;
		public final DoubleValue shield_bash_base_damage;
		public final IntValue shield_bash_cooldown;
		public final IntValue shield_bash_cost;

		public final DoubleValue berserker_duration_ratio;
		public final DoubleValue berserker_punch_damage;
		public final IntValue berserker_cooldown;
		public final IntValue berserker_cost;
		
		public final DoubleValue chain_damage_ratio;
		public final DoubleValue chain_base_damage;
		public final IntValue chain_cooldown;
		public final IntValue chain_cost;
		
		public final DoubleValue time_bomb_ratio;
		public final DoubleValue time_bomb_bonus_damage;
		public final IntValue time_bomb_cooldown;
		public final IntValue time_bomb_cost;
		
		public final IntValue exp_level_up;
		public final IntValue exp_per_advancement;
		
		public final IntValue adjust_hud_animation; 
		
		public Common(ForgeConfigSpec.Builder builder) {
			builder.comment("This config affects all players and contains the stats of the Sword Slash ability")
				   .push("sword_slash");
			
			sword_slash_damage_ratio = builder.comment("This sets the damage multiplier of the Sword Slash ability")
											  .translation("sword_slash.configgui.sword_slash_damage_ratio")
											  .worldRestart()
											  .defineInRange("sword_slash_damage_ratio", 0.5D, 0.0D, 1.0D);
			
			sword_slash_base_damage = builder.comment("This sets the base damage of the Sword Slash ability")
											  .translation("sword_slash.configgui.sword_slash_base_damage")
											  .worldRestart()
											  .defineInRange("sword_slash_base_damage", 7.0D, 0.0D, 1000000.0D);
			
			sword_slash_cooldown = builder.comment("This sets the cooldown in seconds of the Sword Slash ability")
										  .translation("sword_slash.configgui.sword_slash_cooldown")
										  .worldRestart()
										  .defineInRange("sword_slash_cooldown", 10, 0, 3600);
			
			sword_slash_cost = builder.comment("This sets the cost of the Sword Slash ability")
					  				  .translation("sword_slash.configgui.sword_slash_cost")
					  				  .worldRestart()
					  				  .defineInRange("sword_slash_cost", 10, 0, 1000);
			
			builder.pop();
			
			builder.comment("This config affects all players and contains the stats of the Shield Bash ability")
			       .push("shield_bash");
			
			shield_bash_damage_ratio = builder.comment("This sets the damage multiplier of the Shield Bash ability")
					  						  .translation("shield_bash.configgui.shield_bash_damage_ratio")
					  						  .worldRestart()
					  						  .defineInRange("shield_bash_damage_ratio", 0.2D, 0.0D, 1.0D);
			
			shield_bash_base_damage = builder.comment("This sets the base damage of the Shield Bash ability")
					    					  .translation("shield_bash.configgui.shield_bash_base_damage")
					    					  .worldRestart()
					    					  .defineInRange("shield_bash_base_damage", 1.0D, 0.0D, 1000000.0D);
			
			shield_bash_cooldown = builder.comment("This sets the cooldown in seconds of the Shield Bash ability")
					  					  .translation("shield_bash.configgui.shield_bash_cooldown")
					  					  .worldRestart()
					  					  .defineInRange("shield_bash_cooldown", 20, 0, 3600);
			
			shield_bash_cost = builder.comment("This sets the cost of the Shield Bash ability")
					  					  .translation("shield_bash.configgui.shield_bash_cost")
					  					  .worldRestart()
					  					  .defineInRange("shield_bash_cost", 10, 0, 1000);
			
			builder.pop();
			
			builder.comment("This config affects all players and contains the stats of the Berserker ability")
		       	   .push("berserker");

			berserker_duration_ratio = builder.comment("This sets the duration multiplier of the Berserker ability")
											  .translation("berserker.configgui.berserker_duration_ratio")
											  .worldRestart()
											  .defineInRange("berserker_duration_ratio", 0.5D, 0.0D, 1.0D);
			
			berserker_punch_damage = builder.comment("This sets the bonus damage of the Berserker ability")
											.translation("berserker.configgui.berserker_punch_damage")
											.worldRestart()
											.defineInRange("berserker_punch_damage", 10.0D, 0.0D, 1000000.0D);
			
			berserker_cooldown = builder.comment("This sets the cooldown in seconds of the Berserker ability")
  									    .translation("berserker.configgui.berserker_cooldown")
  									    .worldRestart()
  									    .defineInRange("berserker_cooldown", 600, 0, 3600);
			
			berserker_cost = builder.comment("This sets the cost of the Berserker ability")
					    			.translation("berserker.configgui.berserker_cooldown")
					    			.worldRestart()
					    			.defineInRange("berserker_cooldown", 1000, 0, 1000);
			
			builder.pop();
			
			builder.comment("This config affects all players and contains the stats of the Chain ability")
			   	   .push("chain");
		
			chain_damage_ratio = builder.comment("This sets the damage multiplier of the Chain ability")
  					    				.translation("chain.configgui.chain_damage_ratio")
  					    				.worldRestart()
  					    				.defineInRange("chain_damage_ratio", 0.5D, 0.0D, 1.0D);
			
			chain_base_damage = builder.comment("This sets the base damage of the Chain ability")
	    								.translation("chain.configgui.chain_base_damage")
	    								.worldRestart()
	    								.defineInRange("chain_base_damage", 5.0D, 0.0D, 1000000.0D);
			
			chain_cooldown = builder.comment("This sets the cooldown in seconds of the Chain ability")
									.translation("chain.configgui.chain_cooldown")
									.worldRestart()
									.defineInRange("chain_cooldown", 10, 0, 3600);
			
			chain_cost = builder.comment("This sets the cost of the Chain ability")
									.translation("chain.configgui.chain_cost")
									.worldRestart()
									.defineInRange("chain_cost", 10, 0, 1000);
			
			builder.pop();
			
			builder.comment("This config affects all players and contains the stats of the Time Bomb ability")
		   	   .push("time_bomb");
	
			time_bomb_ratio = builder.comment("This sets the damage multiplier of the Time Bomb ability")
					    				.translation("time_bomb.configgui.time_bomb_ratio")
					    				.worldRestart()
					    				.defineInRange("time_bomb_ratio", 0.5D, 0.0D, 1.0D);
			
			time_bomb_bonus_damage = builder.comment("This sets the % base bonus damage of the Time Bomb ability")
	 								   .translation("time_bomb.configgui.time_bomb_bonus_damage")
	 								   .worldRestart()
	 								   .defineInRange("time_bomb_bonus_damage", 0.5D, 0.0D, 1000.0D);
			
			time_bomb_cooldown = builder.comment("This sets the cooldown in seconds of the Time Bomb ability")
									.translation("time_bomb.configgui.time_bomb_cooldown")
									.worldRestart()
									.defineInRange("time_bomb_cooldown", 10, 0, 3600);
			
			time_bomb_cost = builder.comment("This sets the cost of the Time Bomb ability")
								.translation("time_bomb.configgui.time_bomb_cost")
								.worldRestart()
								.defineInRange("time_bomb_cost", 10, 0, 1000);
		
		builder.pop();
			
			builder.comment("This config  affects all players. WARNING! CHANGING THE VALUES HAS TO CHECK THIS EQUATION FOR NO EXPERIENCE LOSES: exp_level_up / exp_per_advancement * exp_per_advancement == exp_level_up")
		   	   	   .push("leveling");
	
			exp_level_up = builder.comment("This sets the amount of experience required to level up")
										  .translation("leveling.configgui.exp_level_up")
										  .worldRestart()
										  .defineInRange("exp_level_up", 100, 100, 1000000);
			
			exp_per_advancement = builder.comment("This sets the amount of experience given when completing an advancement")
									.translation("leveling.configgui.exp_per_advancement")
									.worldRestart()
									.defineInRange("exp_per_advancement", 10, 1, 1000000);
			builder.pop();
			
			builder.comment("This config affects one players. HUD animation adjustments")
	   	   	       .push("hud");
			
			adjust_hud_animation = builder.comment("The higher the number the slower it moves. "
													+ "HINT: the HUD is made out of 40 images, 10 images for each slot, one image is displayed as many frames as it is being set. "
													+ "EXAMPLE: if the animation is set to 1 and the player has 10 FPS the hud will cycle to the next slot in a second, increase the number to 2 and it will take 2 seconds to cycle to the next. "
													+ "The more FPS the faster the animation gets.")
										  .translation("hud.configgui.adjust_hud_animation")
										  .worldRestart()
										  .defineInRange("adjust_hud_animation", 4, 0, 100);
			
			builder.pop();
			
		}
		
	}
	
	public static class Server {
		

		
		public Server(ForgeConfigSpec.Builder builder) {
			
		}
		
	}
	
//	public static final ForgeConfigSpec CLIENT_SPEC;
	public static final ForgeConfigSpec COMMON_SPEC;
//	public static final ForgeConfigSpec SERVER_SPEC;
//	public static final Client CLIENT;
	public static final Common COMMON;
//	public static final Server SERVER;
	
	static {
//		final Pair<Client, ForgeConfigSpec> specPairClient = new ForgeConfigSpec.Builder().configure(Client::new);
//		CLIENT_SPEC = specPairClient.getRight();
//		CLIENT = specPairClient.getLeft();
		final Pair<Common, ForgeConfigSpec> specPairCommon = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPairCommon.getRight();
		COMMON = specPairCommon.getLeft();
//		final Pair<Server, ForgeConfigSpec> specPairServer = new ForgeConfigSpec.Builder().configure(Server::new);
//		SERVER_SPEC = specPairServer.getRight();
//		SERVER = specPairServer.getLeft();
	}
	
	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading event) {
		
	}
	
	@SubscribeEvent
	public static void onFileChange(final ModConfig.Reloading event) {
		
	}
	
}
