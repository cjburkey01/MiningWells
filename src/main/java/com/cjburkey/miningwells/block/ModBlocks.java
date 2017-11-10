package com.cjburkey.miningwells.block;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.miningwells.LogUtils;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent.Register;

public class ModBlocks {
	
	private static final List<BlockBase> blocks = new ArrayList<>();
	private static Register<Block> blockRegistry;
	
	public static BlockMiningWell blockMiningWell;
	public static BlockExtension blockWellExtension;
	public static BlockUpgradeStation blockUpgradeStation;
	
	private static final void addBlocks() {
		blockMiningWell = (BlockMiningWell) registerBlock(new BlockMiningWell());
		blockWellExtension = (BlockExtension) registerBlock(new BlockExtension());
		blockUpgradeStation = (BlockUpgradeStation) registerBlock(new BlockUpgradeStation());
	}
	
	public static final void registerBlocks(Register<Block> e) {
		blockRegistry = e;
		addBlocks();
		LogUtils.info("Registered blocks.");
	}
	
	private static final BlockBase registerBlock(BlockBase block) {
		blocks.add(block);
		blockRegistry.getRegistry().register(block);
		LogUtils.info("Registered block: " + block.getUnlocalizedName().substring(5));
		return block;
	}
	
	public static final BlockBase[] getBlocks() {
		return blocks.toArray(new BlockBase[blocks.size()]);
	}
	
}