package com.cjburkey.miningwells.tile;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTiles {
	
	public static final void commonPreinit() {
		GameRegistry.registerTileEntity(TileEntityMiningWell.class, "tile_mining_well");
		GameRegistry.registerTileEntity(TileEntityUpgradeStation.class, "tile_upgrade_station");
	}
	
}