package com.cjburkey.miningwells.tile;

import com.cjburkey.miningwells.item.upgrade.ItemUpgrade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;

public class TileEntityUpgradeStation extends TileEntity implements IInventory {
	
	private final int size = 5;
	
	private final NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(size, ItemStack.EMPTY);
	
	public String getName() {
		return "tile_upgrade_station";
	}
	
	public boolean hasCustomName() {
		return false;
	}
	
	public int getSizeInventory() {
		return size;
	}
	
	public boolean isEmpty() {
		for (int i = 0; i < inv.size(); i ++) {
			if (!inv.get(i).isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	public ItemStack getStackInSlot(int index) {
		if (index >= 0 && index < size) {
			return inv.get(index);
		}
		return ItemStack.EMPTY;
	}
	
	public ItemStack decrStackSize(int index, int count) {
		ItemStack inSlot = getStackInSlot(index);
		if (inSlot.isEmpty()) {
			return ItemStack.EMPTY;
		}
		if (inSlot.getCount() > count) {
			ItemStack out = inSlot.copy();
			out.setCount(count);
			inSlot.setCount(inSlot.getCount() - count);
			return out;
		}
		setInventorySlotContents(index, ItemStack.EMPTY);
		inSlot.setCount(count);
		return inSlot;
	}
	
	public ItemStack removeStackFromSlot(int index) {
		ItemStack inSlot = getStackInSlot(index);
		setInventorySlotContents(index, ItemStack.EMPTY);
		return inSlot;
	}
	
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index >= 0 && index < size) {
			inv.set(index, stack);
		}
	}
	
	public int getInventoryStackLimit() {
		return 1;
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(getPos()) <= 64;
	}
	
	public void openInventory(EntityPlayer player) {
	}
	
	public void closeInventory(EntityPlayer player) {
	}
	
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return stack.getItem() instanceof ItemUpgrade;
	}
	
	public int getField(int id) {
		return 0;
	}
	
	public void setField(int id, int value) {
	}
	
	public int getFieldCount() {
		return 0;
	}
	
	public void clear() {
		for (int i = 0; i < size; i ++) {
			setInventorySlotContents(i, ItemStack.EMPTY);
		}
	}
	
}