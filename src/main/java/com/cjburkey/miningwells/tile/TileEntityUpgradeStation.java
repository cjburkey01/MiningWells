package com.cjburkey.miningwells.tile;

import com.cjburkey.miningwells.item.upgrade.EnumUpgradeType;
import com.cjburkey.miningwells.item.upgrade.ItemUpgrade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityUpgradeStation extends TileEntity implements IInventory {
	
	private final int size = 3;
	private final NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(size, ItemStack.EMPTY);
	
	public int getUpgradeLevel(EnumUpgradeType type) {
		for (int i = 0; i < size; i ++) {
			ItemStack stack = getStackInSlot(i);
			if (stack.isEmpty()) {
				continue;
			}
			Item item = stack.getItem();
			if (!(item instanceof ItemUpgrade)) {
				continue;
			}
			ItemUpgrade upgrade = (ItemUpgrade) item;
			if (!upgrade.getUpgradeType().equals(type)) {
				continue;
			}
			return upgrade.getUpgradeLevel();
		}
		return 0;
	}
	
	public String getName() {
		return "tile.block_upgrade_station.name";
	}
	
	public boolean hasCustomName() {
		return false;
	}
	
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation(getName());
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
		if (!(stack.getItem() instanceof ItemUpgrade)) {
			return false;
		}
		ItemUpgrade upgrade = (ItemUpgrade) stack.getItem();
		if (index == 0 && upgrade.getUpgradeType().equals(EnumUpgradeType.BLOCKS_PER_OPERATION)) {
			return true;
		}
		if (index == 1 && upgrade.getUpgradeType().equals(EnumUpgradeType.FORTUNE)) {
			return true;
		}
		if (index == 2 && upgrade.getUpgradeType().equals(EnumUpgradeType.SILK_TOUCH)) {
			return true;
		}
		return false;
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
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		if (nbt == null) {
			nbt = new NBTTagCompound();
		}
		NBTTagList items = new NBTTagList();
		for (int i = 0; i < size; i ++) {
			items.appendTag(inv.get(i).writeToNBT(new NBTTagCompound()));
		}
		nbt.setTag("contents", items);
		return super.writeToNBT(nbt);
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		if (nbt != null && nbt.hasKey("contents")) {
			NBTTagList list = nbt.getTagList("contents", 10);
			for (int i = 0; i < list.tagCount(); i ++) {
				NBTTagCompound item = list.getCompoundTagAt(i);
				if (item != null) {
					setInventorySlotContents(i, new ItemStack(item));
				}
			}
		}
		super.readFromNBT(nbt);
	}
	
}