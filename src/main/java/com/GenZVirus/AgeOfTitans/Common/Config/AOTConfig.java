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

		public final DoubleValue shield_bash_damage_ratio;
		public final DoubleValue shield_bash_base_damage;
		public final IntValue shield_bash_cooldown;

		public final DoubleValue berserker_duration_ratio;
		public final DoubleValue berserker_punch_damage;
		public final IntValue berserker_cooldown;
		
		public final DoubleValue chain_damage_ratio;
		public final DoubleValue chain_base_damage;
		public final IntValue chain_cooldown;
		
		public final IntValue exp_level_up;
		public final IntValue exp_per_advancement;
		
		public Common(ForgeConfigSpec.Builder builder) {
			builder.comment("This config contains the stats of the Sword Slash ability")
				   .push("sword_slash");
			
			sword_slash_damage_ratio = builder.comment("This sets the damage multiplier of the Sword Slash ability")
											  .translation("sword_slash.configgui.sword_slash_damage_ratio")
											  .worldRestart()
											  .defineInRange("sword_slash_damage_ratio", 0.5D, 0.0D, 1.0D);
			
			sword_slash_base_damage = builder.comment("This sets the damage multiplier of the Sword Slash ability")
											  .translation("sword_slash.configgui.sword_slash_base_damage")
											  .worldRestart()
											  .defineInRange("sword_slash_base_damage", 7.0D, 0.0D, 1000000.0D);
			
			sword_slash_cooldown = builder.comment("This sets the cooldown in seconds of the Sword Slash ability")
										  .translation("sword_slash.configgui.sword_slash_cooldown")
										  .worldRestart()
										  .defineInRange("sword_slash_cooldown", 10, 0, 3600);
			
			builder.pop();
			
			builder.comment("This config contains the stats of the Shield Bash ability")
			       .push("shield_bash");
			
			shield_bash_damage_ratio = builder.comment("This sets the damage multiplier of the Shield Bash ability")
					  						  .translation("shield_bash.configgui.shield_bash_damage_ratio")
					  						  .worldRestart()
					  						  .defineInRange("shield_bash_damage_ratio", 0.2D, 0.0D, 1.0D);
			
			shield_bash_base_damage = builder.comment("This sets the damage multiplier of the Shield Bash ability")
					    					  .translation("shield_bash.configgui.shield_bash_base_damage")
					    					  .worldRestart()
					    					  .defineInRange("shield_bash_base_damage", 1.0D, 0.0D, 1000000.0D);
			
			shield_bash_cooldown = builder.comment("This sets the cooldown in seconds of the Shield Bash ability")
					  					  .translation("shield_bash.configgui.shield_bash_cooldown")
					  					  .worldRestart()
					  					  .defineInRange("shield_bash_cooldown", 20, 0, 3600);
			
			builder.pop();
			
			builder.comment("This config contains the stats of the Berserker ability")
		       	   .push("berserker");

			berserker_duration_ratio = builder.comment("This sets the duration multiplier of the Berserker ability")
											  .translation("berserker.configgui.berserker_duration_ratio")
											  .worldRestart()
											  .defineInRange("berserker_duration_ratio", 0.5D, 0.0D, 1.0D);
			
			berserker_punch_damage = builder.comment("This sets the duration multiplier of the Berserker ability")
											.translation("berserker.configgui.berserker_punch_damage")
											.worldRestart()
											.defineInRange("berserker_punch_damage", 10.0D, 0.0D, 1000000.0D);
			
			berserker_cooldown = builder.comment("This sets the cooldown in seconds of the Berserker ability")
  									    .translation("berserker.configgui.berserker_cooldown")
  									    .worldRestart()
  									    .defineInRange("berserker_cooldown", 600, 0, 3600);
			
			builder.pop();
			
			builder.comment("This config contains the stats of the Chain ability")
			   	   .push("chain");
		
			chain_damage_ratio = builder.comment("This sets the damage multiplier of the Chain ability")
  					    				.translation("chain.configgui.chain_damage_ratio")
  					    				.worldRestart()
  					    				.defineInRange("chain_damage_ratio", 0.5D, 0.0D, 1.0D);
			
			chain_base_damage = builder.comment("This sets the damage multiplier of the Chain ability")
	    								.translation("chain.configgui.chain_base_damage")
	    								.worldRestart()
	    								.defineInRange("chain_base_damage", 5.0D, 0.0D, 1000000.0D);
			
			chain_cooldown = builder.comment("This sets the cooldown in seconds of the Chain ability")
									.translation("chain.configgui.chain_cooldown")
									.worldRestart()
									.defineInRange("chain_cooldown", 10, 0, 3600);
			builder.pop();
			
			builder.comment("WARNING! CAHNGING THE VALUES HAS TO CHECK THIS EQUATION FOR NO EXPERIENCE LOSES: exp_level_up / exp_per_advancement * exp_per_advancement == exp_level_up")
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