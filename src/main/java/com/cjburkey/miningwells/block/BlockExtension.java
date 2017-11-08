package com.cjburkey.miningwells.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockExtension extends BlockBase {
	
	public static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.3125d, 0.0d, 0.3125d, 0.6875d, 1.0d, 0.6875d);
	
	public BlockExtension() {
		super(Material.GROUND, "block_well_extension");
		setBlockUnbreakable();
		setSoundType(SoundType.METAL);
		setCreativeTab(null);
	}
	
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!canBlockStay(world, pos)) {
			world.destroyBlock(pos, false);
		}
	}
	
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
		if (!canBlockStay(world, pos)) {
			world.scheduleUpdate(pos, this, 1);
		}
	}
	
	public boolean canBlockStay(World world, BlockPos pos) {
		Block b = world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())).getBlock();
		return b.equals(ModBlocks.blockWellExtension) || b.equals(ModBlocks.blockMiningWell);
	}
	
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		return new ArrayList<>();
	}
	
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return 0;
	}
	
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}
	
	public boolean isBlockNormalCube(IBlockState state) {
		return false;
	}
	
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOUNDING_BOX;
	}
	
}