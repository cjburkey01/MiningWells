package com.cjburkey.miningwells.item;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.miningwells.LogUtils;
import com.cjburkey.miningwells.block.BlockBase;
import com.cjburkey.miningwells.block.ModBlocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;

public class ModItems {
	
	public static ItemBase itemIronGear;
	
	private static final List<ItemBase> items = new ArrayList<>();
	private static final List<ItemBaseBlock> blocks = new ArrayList<>();
	
	public static final void registerItems(Register<Item> e) {
		itemIronGear = registerItem(e, new ItemBase("item_gear_iron"));
		for (BlockBase block : ModBlocks.getBlocks()) {
			registerItemBlock(e, block);
		}
		LogUtils.info("Registered items.");
	}
	
	private static final ItemBase registerItem(Register<Item> e, ItemBase item) {
		items.add(item);
		e.getRegistry().register(item);
		return item;
	}
	
	public static final ItemBaseBlock registerItemBlock(Register<Item> e, BlockBase block) {
		ItemBaseBlock item = new ItemBaseBlock(block);
		blocks.add(item);
		e.getRegistry().register(item);
		return item;
	}
	
	public static final void registerItemRenders(ModelRegistryEvent e) {
		for (ItemBase item : items) {
			registerItemRender(item);
		}
		LogUtils.info("Registered item renders.");
		
		for (ItemBaseBlock block : blocks) {
			registerItemRender(block);
		}
		LogUtils.info("Registered block item renders.");
	}
	
	private static final void registerItemRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
}