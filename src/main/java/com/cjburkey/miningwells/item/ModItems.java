package com.cjburkey.miningwells.item;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.miningwells.LogUtils;
import com.cjburkey.miningwells.block.BlockBase;
import com.cjburkey.miningwells.block.ModBlocks;
import com.cjburkey.miningwells.item.upgrade.EnumUpgradeType;
import com.cjburkey.miningwells.item.upgrade.ItemUpgrade;
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
	
	public static ItemUpgrade itemUpgradeBpo1;
	public static ItemUpgrade itemUpgradeBpo2;
	public static ItemUpgrade itemUpgradeBpo3;
	public static ItemUpgrade itemUpgradeBpo4;
	public static ItemUpgrade itemUpgradeFortune1;
	public static ItemUpgrade itemUpgradeFortune2;
	public static ItemUpgrade itemUpgradeFortune3;
	public static ItemUpgrade itemUpgradeSilkTouch;
	
	private static final void addItems() {
		itemStoneGear = registerItem(new ItemBase("item_gear_stone"));
		itemIronGear = registerItem(new ItemBase("item_gear_iron"));
		itemTinGear = registerItem(new ItemBase("item_gear_tin"));
		itemCopperGear = registerItem(new ItemBase("item_gear_copper"));
		itemGoldGear = registerItem(new ItemBase("item_gear_gold"));
		itemDiamondGear = registerItem(new ItemBase("item_gear_diamond"));
		
		itemUpgradeBpo1 = (ItemUpgrade) registerItem(new ItemUpgrade(EnumUpgradeType.BLOCKS_PER_OPERATION, 1));
		itemUpgradeBpo2 = (ItemUpgrade) registerItem(new ItemUpgrade(EnumUpgradeType.BLOCKS_PER_OPERATION, 2));
		itemUpgradeBpo3 = (ItemUpgrade) registerItem(new ItemUpgrade(EnumUpgradeType.BLOCKS_PER_OPERATION, 3));
		itemUpgradeBpo4 = (ItemUpgrade) registerItem(new ItemUpgrade(EnumUpgradeType.BLOCKS_PER_OPERATION, 4));
		itemUpgradeFortune1 = (ItemUpgrade) registerItem(new ItemUpgrade(EnumUpgradeType.FORTUNE, 1));
		itemUpgradeFortune1 = (ItemUpgrade) registerItem(new ItemUpgrade(EnumUpgradeType.FORTUNE, 2));
		itemUpgradeFortune1 = (ItemUpgrade) registerItem(new ItemUpgrade(EnumUpgradeType.FORTUNE, 3));
		itemUpgradeSilkTouch = (ItemUpgrade) registerItem(new ItemUpgrade(EnumUpgradeType.SILK_TOUCH, 1));
	}
	
	public static final void registerItems(Register<Item> e) {
		itemRegistry = e;
		addItems();
		LogUtils.info("Registered items.");
		
		for (BlockBase block : ModBlocks.getBlocks()) {
			registerItemBlock(block);
		}
		LogUtils.info("Registered block items.");
	}
	
	private static final ItemBase registerItem(ItemBase item) {
		items.add(item);
		itemRegistry.getRegistry().register(item);
		LogUtils.info("Registered item: " + item.getUnlocalizedName().substring(5));
		return item;
	}
	
	private static final ItemBaseBlock registerItemBlock(BlockBase block) {
		ItemBaseBlock item = new ItemBaseBlock(block);
		blocks.add(item);
		itemRegistry.getRegistry().register(item);
		LogUtils.info("Registered block item: " + item.getUnlocalizedName().substring(5));
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