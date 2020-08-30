package com.GenZVirus.AgeOfTitans.Common.Events.Server;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.SpellSystem.PassiveAbility;
import com.GenZVirus.AgeOfTitans.SpellSystem.XMLFileJava;
import com.GenZVirus.AgeOfTitans.Util.ForgeEventBusSubscriber;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class AOTPassiveAbilities {

	private static int timer = 0;
	
	@SubscribeEvent
	public static void AOTForceField(PlayerEvent event) {
		PlayerEntity player = event.getPlayer();
		if(player == null) return;
		if(player.world.isRemote) return;
		if(!ForgeEventBusSubscriber.players.contains(player)) return;
		if(Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "Passive_Level1")) <= 0) return;
		if(ForgeEventBusSubscriber.inCombat.get(ForgeEventBusSubscriber.players.indexOf(player)) > 0) return;
		PassiveAbility.getList().get(1).effect(player.world, player);
	}
	
	@SubscribeEvent
	public static void AOTPOG(ServerTickEvent event) {
		if(event.phase == Phase.START) return;
		timer++;
		if(timer == 100) {
			timer = 0;
			for(PlayerEntity player : ForgeEventBusSubscriber.players) {
				if(Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "Passive_Level2")) > 0) {
					PassiveAbility.getList().get(2).effect(player.world, player);
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void AOTROS(LivingDeathEvent event) {
		if(!(event.getSource().getTrueSource() instanceof PlayerEntity)) return;
		PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
		if(player == null) return;
		if(player.world.isRemote) return;
		if(!ForgeEventBusSubscriber.players.contains(player)) return;
		if(Integer.parseInt(XMLFileJava.readElement(player.getUniqueID(), "Passive_Level3")) <= 0) return;
		PassiveAbility.getList().get(3).effect(player.world, player);
	}
	
}
