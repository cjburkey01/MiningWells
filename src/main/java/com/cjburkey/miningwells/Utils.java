package com.cjburkey.miningwells;

import java.util.List;
import com.cjburkey.miningwells.config.ModConfig;
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
	
}