package com.GenZVirus.AgeOfTitans.Client.Container;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.items.ItemStackHandler;

public class BHItemStackHandler extends ItemStackHandler {

	public BHItemStackHandler(int size) {
		super(size);
	}

	public NonNullList<ItemStack> getStacks() {
		return this.stacks;
	}

	@Override
	public CompoundNBT serializeNBT() {
		ListNBT nbtTagList = new ListNBT();
		for (int i = 0; i < stacks.size(); i++) {
			if (!stacks.get(i).isEmpty()) {
				CompoundNBT itemTag = new CompoundNBT();
				itemTag.putInt("Slot", i);
				ResourceLocation resourcelocation = Registry.ITEM.getKey(stacks.get(i).getItem());
				itemTag.putString("id", resourcelocation == null ? "minecraft:air" : resourcelocation.toString());
				itemTag.putInt("Count", stacks.get(i).count);
				if (stacks.get(i).tag != null) {
					itemTag.put("tag", stacks.get(i).tag.copy());
				}

				CompoundNBT cnbt;
				try {
					ItemStack stack = stacks.get(i);
					Method method = ObfuscationReflectionHelper.findMethod(stack.getClass().getSuperclass(), "serializeCaps");
					cnbt = (CompoundNBT) method.invoke(stack);
					if (cnbt != null && !cnbt.isEmpty()) {
						itemTag.put("ForgeCaps", cnbt);
					}
					nbtTagList.add(itemTag);
				} catch (Throwable e) {
					e.printStackTrace();
				}

			}
		}
		CompoundNBT nbt = new CompoundNBT();
		nbt.put("Items", nbtTagList);
		nbt.putInt("Size", stacks.size());
		System.out.println(nbt.getInt("Count"));
		return nbt;
	}
	
	@Override
    public void deserializeNBT(CompoundNBT nbt)
    {
        setSize(nbt.contains("Size", Constants.NBT.TAG_INT) ? nbt.getInt("Size") : stacks.size());
        ListNBT tagList = nbt.getList("Items", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < tagList.size(); i++)
        {
            CompoundNBT itemTags = tagList.getCompound(i);
            int slot = itemTags.getInt("Slot");

            if (slot >= 0 && slot < stacks.size())
            {
            	ItemStack stack = new ItemStack(Registry.ITEM.getOrDefault(new ResourceLocation(itemTags.getString("id"))), itemTags.getInt("Count"));
//            	stack.capNBT = itemTags.contains("ForgeCaps") ? itemTags.getCompound("ForgeCaps") : null;
            	try {
            	Field field = ObfuscationReflectionHelper.findField(stack.getClass(), "capNBT");
            	field.set(stack, itemTags.contains("ForgeCaps") ? itemTags.getCompound("ForgeCaps") : null);
            	} catch(Exception e) {
            		e.printStackTrace();
            	}
            	
            	if(itemTags.contains("tag", 10)) {
            		stack.tag = itemTags.getCompound("tag");
            		stack.getItem().updateItemStackNBT(itemTags);
            	}
            	if (stack.getItem().isDamageable()) {
            		stack.setDamage(stack.getDamage());
                 }

            	stack.updateEmptyState();

            	try {
            		Method method = ObfuscationReflectionHelper.findMethod(stack.getClass(), "forgeInit");
            		method.invoke(stack);
                	} catch(Exception e) {
                		e.printStackTrace();
                	}
            	
//            	stack.forgeInit();
                stacks.set(slot, stack);
            }
        }
        onLoad();
    }
	@Override
	public int getSlotLimit(int slot) {
		return Integer.MAX_VALUE;
	}

}
