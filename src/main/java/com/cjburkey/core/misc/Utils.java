package com.cjburkey.core.misc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import com.cjburkey.miningwells.LogUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class Utils {
	
	private static Method silkTouchMethod;
	
	public static final List<ItemStack> getBlockDrops(BlockPos pos, World world, int fortune) {
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		return block.getDrops(world, pos, state, fortune);
	}
	
	public static final ItemStack getSilkTouchDrop(BlockPos pos, World world) {
		if (silkTouchMethod == null) {
			silkTouchMethod = ReflectionHelper.findMethod(Block.class, "getSilkTouchDrop", "func_180643_i", IBlockState.class);
		}
		IBlockState state = world.getBlockState(pos);
		try {
			return (ItemStack) silkTouchMethod.invoke(state.getBlock(), state);
		} catch (Exception e) {
			LogUtils.error("Unable to determine silk stack of: " + state);
			return ItemStack.EMPTY;
		}
	}
	
}