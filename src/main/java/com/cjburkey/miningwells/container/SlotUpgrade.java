package com.cjburkey.miningwells.container;

import com.cjburkey.miningwells.item.upgrade.EnumUpgradeType;
import com.cjburkey.miningwells.item.upgrade.ItemUpgrade;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotUpgrade extends Slot {
	
	protected EnumUpgradeType type;

	public SlotUpgrade(IInventory inv, int i, int x, int y, EnumUpgradeType type) {
		super(inv, i, x, y);
		this.type = type;
	}
	
	public boolean isItemValid(ItemStack stack) {
		if (!inventory.isItemValidForSlot(getSlotIndex(), stack)) {
			return false;
		}
		ItemUpgrade i = (ItemUpgrade) stack.getItem();
		return i.getUpgradeType().equals(type);
	}
	
}