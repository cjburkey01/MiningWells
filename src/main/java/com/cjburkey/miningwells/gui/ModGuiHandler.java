package com.cjburkey.miningwells.gui;

import com.cjburkey.miningwells.container.ContainerUpgradeStation;
import com.cjburkey.miningwells.container.ContainerMiningWell;
import com.cjburkey.miningwells.tile.TileEntityMiningWell;
import com.cjburkey.miningwells.tile.TileEntityUpgradeStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuiHandler implements IGuiHandler {
	
	public static final int GUI_WELL = 0;
	public static final int GUI_UPGRADE_STATION = 1;
	
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		// Switch statements shouldn't be indented because the Java coding convention says so, but I can't stand it; deal with my tabs.
		switch(id) {
			case GUI_WELL:
				return new ContainerMiningWell(player.inventory, (TileEntityMiningWell) world.getTileEntity(new BlockPos(x, y, z)));
			case GUI_UPGRADE_STATION:
				return new ContainerUpgradeStation((TileEntityUpgradeStation) world.getTileEntity(new BlockPos(x, y, z)), player.inventory);
			default:
				return null;
		}
	}
	
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id) {
			case GUI_WELL:
				return new GuiMiningWell(player, (TileEntityMiningWell) world.getTileEntity(new BlockPos(x, y, z)));
			case GUI_UPGRADE_STATION:
				return new GuiUpgradeStation((TileEntityUpgradeStation) world.getTileEntity(new BlockPos(x, y, z)), player.inventory);
			default:
				return null;
		}
	}
	
}