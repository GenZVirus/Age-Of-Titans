package com.GenZVirus.AgeOfTitans.Common.Config;

import org.apache.commons.lang3.tuple.Pair;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
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
		public final DoubleValue chain_damage_ratio;
		public final DoubleValue shield_bash_damage_ratio;
		public final DoubleValue berserker_duration_ratio;
		
		public Common(ForgeConfigSpec.Builder builder) {
			builder.comment("This config contains the multipliers of the spells")
				   .push("spell_ratio");
			
			sword_slash_damage_ratio = builder.comment("This sets the damage multiplier of the Sword Slash ability")
											  .translation("spell_ratio.configgui.sword_slash_damage_ratio")
											  .worldRestart()
											  .defineInRange("sword_slash_damage_ratio", 0.5D, 0.0D, 1.0D);
			
			shield_bash_damage_ratio = builder.comment("This sets the damage multiplier of the Shield Bash ability")
					  						  .translation("spell_ratio.configgui.shield_bash_damage_ratio")
					  						  .worldRestart()
					  						  .defineInRange("shield_bash_damage_ratio", 0.2D, 0.0D, 1.0D);
			
			chain_damage_ratio = builder.comment("This sets the damage multiplier of the Chain ability")
					  					.translation("spell_ratio.configgui.chain_damage_ratio")
					  					.worldRestart()
					  					.defineInRange("chain_damage_ratio", 0.5D, 0.0D, 1.0D);
			
			berserker_duration_ratio = builder.comment("This sets the duration multiplier of the Berserker ability")
											  .translation("spell_ratio.configgui.berserker_duration_ratio")
											  .worldRestart()
											  .defineInRange("berserker_duration_ratio", 0.5D, 0.0D, 1.0D);
			builder.pop();
		}
		
	}
	
	public static class Server {
		
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
