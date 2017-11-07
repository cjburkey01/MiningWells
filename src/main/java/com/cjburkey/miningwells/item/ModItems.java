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
	
	private static final List<ItemBase> items = new ArrayList<>();
	private static final List<ItemBaseBlock> blocks = new ArrayList<>();
	private static Register<Item> itemRegistry;

	public static ItemBase itemStoneGear;
	public static ItemBase itemIronGear;
	public static ItemBase itemTinGear;
	public static ItemBase itemCopperGear;
	public static ItemBase itemGoldGear;
	public static ItemBase itemDiamondGear;
	
	private static final void addItems() {
		itemStoneGear = registerItem(new ItemBase("item_gear_stone"));
		itemIronGear = registerItem(new ItemBase("item_gear_iron"));
		itemTinGear = registerItem(new ItemBase("item_gear_tin"));
		itemCopperGear = registerItem(new ItemBase("item_gear_copper"));
		itemGoldGear = registerItem(new ItemBase("item_gear_gold"));
		itemDiamondGear = registerItem(new ItemBase("item_gear_diamond"));
	}
	
	public static final void registerItems(Register<Item> e) {
		itemRegistry = e;
		addItems();
		for (BlockBase block : ModBlocks.getBlocks()) {
			registerItemBlock(block);
		}
		LogUtils.info("Registered items.");
	}
	
	private static final ItemBase registerItem(ItemBase item) {
		items.add(item);
		itemRegistry.getRegistry().register(item);
		return item;
	}
	
	private static final ItemBaseBlock registerItemBlock(BlockBase block) {
		ItemBaseBlock item = new ItemBaseBlock(block);
		blocks.add(item);
		itemRegistry.getRegistry().register(item);
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