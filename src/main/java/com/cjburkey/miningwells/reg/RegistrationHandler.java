package com.cjburkey.miningwells.reg;

import com.cjburkey.miningwells.ModInfo;
import com.cjburkey.miningwells.block.ModBlocks;
import com.cjburkey.miningwells.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = ModInfo.MODID)
public class RegistrationHandler {
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> e) {
		ModBlocks.registerBlocks(e);
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> e) {
		ModItems.registerItems(e);
	}
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent e) {
		ModItems.registerItemRenders(e);
	}
	
}