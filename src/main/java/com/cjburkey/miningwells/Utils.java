package com.cjburkey.miningwells;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Utils {
	
	public static final List<ItemStack> getBlockDrops(BlockPos pos, World world) {
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		return block.getDrops(world, pos, state, 0);
	}
	
	public static final boolean addStackToInv(ItemStack stack, IInventory inv) {
		for(int i = 0; i < inv.getSizeInventory(); i ++) {
			// TODO: REWRITE THIS METHOD, IT's BROKEN IN 1.12 FOR SOME REASON.
		}
		return false;
	}
	
}