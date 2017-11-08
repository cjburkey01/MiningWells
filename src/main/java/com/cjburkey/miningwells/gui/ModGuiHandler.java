package com.cjburkey.miningwells.gui;

import com.cjburkey.miningwells.container.ContainerWell;
import com.cjburkey.miningwells.tile.TileEntityMiningWell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuiHandler implements IGuiHandler {
	
	public static final int GUI_WELL = 0;
	
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		// Switch statements shouldn't be indented because the Java coding convention said so, but I can't stand it; deal with tabs.
		switch(id) {
			case GUI_WELL:
				return new ContainerWell(player.inventory, (TileEntityMiningWell) world.getTileEntity(new BlockPos(x, y, z)));
			default:
				return null;
		}
	}
	
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id) {
			case GUI_WELL:
				return new GuiWell(player, (TileEntityMiningWell) world.getTileEntity(new BlockPos(x, y, z)));
			default:
				return null;
		}
	}
	
}