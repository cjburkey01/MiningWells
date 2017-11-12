package com.cjburkey.miningwells.block;

import com.cjburkey.core.inventory.InventoryUtils;
import com.cjburkey.miningwells.MiningWells;
import com.cjburkey.miningwells.gui.ModGuiHandler;
import com.cjburkey.miningwells.item.upgrade.ItemUpgrade;
import com.cjburkey.miningwells.tile.TileEntityUpgradeStation;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUpgradeStation extends BlockBase implements ITileEntityProvider {
	
	public BlockUpgradeStation() {
		super(Material.IRON, "block_upgrade_station");
	}
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing dir, float x, float y, float z) {
		if (!world.isRemote) {
			ItemStack stack = player.getHeldItem(hand);
			if (stack.getItem() instanceof ItemUpgrade) {
				if (InventoryUtils.addStackToInventory(stack, (TileEntityUpgradeStation) world.getTileEntity(pos))) {
					stack.setCount(0);
					return true;
				}
			}
			player.openGui(MiningWells.instance, ModGuiHandler.GUI_UPGRADE_STATION, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	public TileEntity createNewTileEntity(World world, int data) {
		return new TileEntityUpgradeStation();
	}
	
}