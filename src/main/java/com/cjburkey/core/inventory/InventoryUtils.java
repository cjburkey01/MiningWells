package com.cjburkey.core.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryUtils {
	
	public static final int getOpenSlot(IInventory inv) {
		for (int i = 0; i < inv.getSizeInventory(); i ++) {
			if (inv.getStackInSlot(i).isEmpty()) {
				return i;
			}
		}
		return -1;
	}
	
	public static final int addStackToSlot(final ItemStack inSlot, final ItemStack stack, IInventory inv) {
		int ableToReceive = Math.min(inSlot.getMaxStackSize() - inSlot.getCount(), stack.getCount());
		inSlot.setCount(inSlot.getCount() + ableToReceive);
		return ableToReceive;
	}
	
	public static final boolean addStackToInventory(final ItemStack stack, IInventory inv) {
		int openSlot = getOpenSlot(inv);
		if (openSlot < 0) {
			return false;
		}
		if (inv.isItemValidForSlot(openSlot, stack) && (stack.isItemDamaged() || stack.getMaxStackSize() == 1)) {
			inv.setInventorySlotContents(openSlot, stack.copy());
			stack.setCount(0);
			return true;
		}
		for (int i = 0; i < inv.getSizeInventory(); i ++) {
			if (!inv.isItemValidForSlot(i, stack)) {
				continue;
			}
			ItemStack inSlot = inv.getStackInSlot(i);
			if (inSlot.isEmpty()) {
				inv.setInventorySlotContents(i, stack.copy());
				stack.setCount(0);
				return true;
			}
			if (inSlot.getItem().equals(stack.getItem())) {
				int remaining = stack.getCount() - addStackToSlot(inSlot, stack, inv);
				stack.setCount(remaining);
				if (remaining <= 0) {
					return true;
				}
			}
		}
		return false;
	}
	
}