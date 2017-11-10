package com.cjburkey.miningwells.block;

import com.cjburkey.miningwells.ModInfo;
import com.cjburkey.miningwells.tab.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBase extends Block {

	public BlockBase(Material material, String unlocName) {
		super(material);
		setUnlocalizedName(unlocName);
		setRegistryName(ModInfo.MODID, unlocName);
		setCreativeTab(ModTabs.tabBlocks);
	}
	
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		if (!world.isRemote) {
			TileEntity atPos = world.getTileEntity(pos);
			if (atPos != null && atPos instanceof IInventory) {
				InventoryHelper.dropInventoryItems(world, pos, (IInventory) atPos);
			}
		}
	}
	
}