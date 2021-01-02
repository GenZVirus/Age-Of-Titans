package com.GenZVirus.AgeOfTitans.Common.Events.Server;

import java.util.List;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Entities.ReaperEntity;
import com.GenZVirus.AgeOfTitans.Common.Init.BlockInit;
import com.GenZVirus.AgeOfTitans.Common.Init.ModEntityTypes;
import com.GenZVirus.AgeOfTitans.SpellSystem.XMLFileJava;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class ReaperEventsHandler {

	@SubscribeEvent
	public static void reaped(LivingDamageEvent event) {
		if (event.getEntityLiving().world.isRemote)
			return;
		if (!(event.getSource().getTrueSource() instanceof ReaperEntity))
			return;
		if (event.getEntityLiving() instanceof PlayerEntity) {
			if (Integer.parseInt(XMLFileJava.readElement(((PlayerEntity) event.getEntityLiving()).getUniqueID(), "ApplesEaten")) <= 0) {
				event.setAmount(event.getEntityLiving().getMaxHealth() + 1);
			}
		} else {
			event.setAmount(event.getEntityLiving().getMaxHealth() + 1);
		}
	}

	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public static void soulStone(PlayerEvent event) {
		PlayerEntity player = event.getPlayer();
		if (player == null)
			return;
		if (player.world.isRemote)
			return;
		if (player.world.getDayTime() < 17800 || player.world.getDayTime() > 18200)
			return;
		BlockPos pos = player.getPosition();
		AxisAlignedBB area = new AxisAlignedBB(pos.getX() - 10, pos.getY() - 10, pos.getZ() - 10, pos.getX() + 10, pos.getY() + 10, pos.getZ() + 10);
		List<Entity> list = player.world.getEntitiesWithinAABBExcludingEntity(player, area);
		for (Entity entity : list) {
			if (entity instanceof ReaperEntity && entity.isAlive())
				return;
		}
		int i = MathHelper.floor(area.minX);
		int j = MathHelper.ceil(area.maxX);
		int k = MathHelper.floor(area.minY);
		int l = MathHelper.ceil(area.maxY);
		int i1 = MathHelper.floor(area.minZ);
		int j1 = MathHelper.ceil(area.maxZ);
		if (player.world.isAreaLoaded(i, k, i1, j, l, j1)) {
			for (int k1 = i; k1 < j; ++k1) {
				for (int l1 = k; l1 < l; ++l1) {
					for (int i2 = i1; i2 < j1; ++i2) {
						BlockPos blockPos = new BlockPos(k1, l1, i2);
						BlockState blockstate = player.world.getBlockState(blockPos);
						if (blockstate.getBlock() == BlockInit.SOUL_STONE.get()) {
							player.world.removeBlock(blockPos, false);
							ReaperEntity reaper = new ReaperEntity(ModEntityTypes.REAPER.get(), player.world);
							reaper.setPositionAndUpdate(k1, l1, i2);
							player.world.addEntity(reaper);
							return;
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void ReaperInTheDark(LivingDamageEvent event) {
		if(event.getEntityLiving() == null) return;
		if(event.getEntityLiving().world.isRemote) return;
		if(!(event.getEntityLiving() instanceof ReaperEntity)) return;
		if (event.getEntityLiving().world.getLight(event.getEntityLiving().getPosition()) > 0) {
			event.setAmount(0);		
		}
	}

}
