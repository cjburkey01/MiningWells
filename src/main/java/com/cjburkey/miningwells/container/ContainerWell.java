package com.cjburkey.miningwells.container;

import com.cjburkey.miningwells.tile.TileEntityMiningWell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ContainerWell extends Container {
	
	private IInventory plyInv;
	private TileEntityMiningWell te;
	
	public ContainerWell(IInventory plyInv, TileEntityMiningWell te) {
		this.plyInv = plyInv;
		this.te = te;
	}
	
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		return ItemStack.EMPTY;
	}
	
	public boolean canInteractWith(EntityPlayer ply) {
		return te.isUsableByPlayer(ply);
	}
	
}