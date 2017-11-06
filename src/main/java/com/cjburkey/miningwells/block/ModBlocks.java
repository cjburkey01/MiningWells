package com.cjburkey.miningwells.block;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.miningwells.LogUtils;
import com.cjburkey.miningwells.item.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;

public class ModBlocks {
	
	private static final List<BlockBase> blocks = new ArrayList<>();
	
	public static final void registerBlocks(Register<Block> e) {
		LogUtils.info("Registered blocks.");
	}
	
	public static final BlockBase[] getBlocks() {
		return blocks.toArray(new BlockBase[blocks.size()]);
	}
	
}