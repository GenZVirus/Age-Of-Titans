package com.GenZVirus.AgeOfTitans.Common.Events.Server.Sets;

import java.util.Random;

import com.GenZVirus.AgeOfTitans.AgeOfTitans;
import com.GenZVirus.AgeOfTitans.Common.Init.ItemInit;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AgeOfTitans.MOD_ID, bus = Bus.FORGE)
public class HeavyTitaniumArmorSetBonusEventsHandler {

	@SubscribeEvent
	public static void SetBonus(LivingDamageEvent event) {
		if (event.getEntityLiving() == null)
			return;
		if (event.getEntityLiving().world.isRemote)
			return;
		LivingEntity entity = event.getEntityLiving();
		Iterable<ItemStack> armory = entity.getArmorInventoryList();
		for (ItemStack stack : armory) {
			if (stack.isEmpty()) { return; }
			if (!stack.getItem().equals(ItemInit.HEAVY_TITANIUM_HELMET.get()) && !stack.getItem().equals(ItemInit.HEAVY_TITANIUM_CHESTPLATE.get()) && !stack.getItem().equals(ItemInit.HEAVY_TITANIUM_LEGGINGS.get()) && !stack.getItem().equals(ItemInit.HEAVY_TITANIUM_BOOTS.get())) { return; }
		}

		if (!(event.getSource().getTrueSource() instanceof LivingEntity))
			return;
		if (event.getSource().isProjectile())
			return;
		LivingEntity attacker = (LivingEntity) event.getSource().getTrueSource();
		destroyOrDropItem(attacker, event);

	}

	private static void destroyOrDropItem(LivingEntity attacker, LivingDamageEvent event) {
		if ((attacker.getHeldItemMainhand().getItem() instanceof ToolItem)) {
			for (AttributeModifier att : ((ToolItem) attacker.getHeldItemMainhand().getItem()).getAttributeModifiers(EquipmentSlotType.MAINHAND).get(SharedMonsterAttributes.ATTACK_DAMAGE.getName())) {
				if (att.getAmount() + 1 < 7 && EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, attacker.getHeldItemMainhand()) == 0) {
					attacker.getHeldItemMainhand().setDamage(0);
					event.setAmount(0);
					return;
				} else if (new Random().nextInt(10) == 1 && att.getAmount() + 1 < 10) {
					event.setAmount(0);
					attacker.entityDropItem(attacker.getHeldItemMainhand());
				}
			}
			if (attacker.getHeldItemMainhand().getItem() instanceof SwordItem) {
				if (((SwordItem) attacker.getHeldItemMainhand().getItem()).getAttackDamage() + 1 < 7 && EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, attacker.getHeldItemMainhand()) == 0) { 
					attacker.getHeldItemMainhand().setDamage(0);
					event.setAmount(0);
				}else if (new Random().nextInt(10) == 1 && ((SwordItem) attacker.getHeldItemMainhand().getItem()).getAttackDamage() + 1 < 10) {
					event.setAmount(0);
					attacker.entityDropItem(attacker.getHeldItemMainhand());					
				}
			}
		}
	}

}
