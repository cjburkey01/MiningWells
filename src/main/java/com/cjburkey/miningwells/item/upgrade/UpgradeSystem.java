package com.cjburkey.miningwells.item.upgrade;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.cjburkey.miningwells.tile.TileEntityUpgradeStation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UpgradeSystem {
	
	public static final int getUpgradeLevel(BlockPos well, World world, EnumUpgradeType upgrade) {
		List<Integer> levels = Arrays.asList(new Integer[] {
			getUpgradeLevelInUpgradeStation(well.up(), world, upgrade),
			getUpgradeLevelInUpgradeStation(well.north(), world, upgrade),
			getUpgradeLevelInUpgradeStation(well.south(), world, upgrade),
			getUpgradeLevelInUpgradeStation(well.east(), world, upgrade),
			getUpgradeLevelInUpgradeStation(well.west(), world, upgrade),
		});
		return Collections.max(levels);
	}
	
	public static final int getUpgradeLevelInUpgradeStation(BlockPos upgradeStation, World world, EnumUpgradeType upgrade) {
		TileEntity te = world.getTileEntity(upgradeStation);
		if (te == null || !(te instanceof TileEntityUpgradeStation)) {
			return 0;
		}
		TileEntityUpgradeStation station = (TileEntityUpgradeStation) te;
		return station.getUpgradeLevel(upgrade);
	}
	
}