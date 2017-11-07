package com.cjburkey.miningwells.config;

import java.io.File;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModConfig {
	
	public static boolean displayMiningWellJoke = false;
	public static int ticksBetweenMining = 0;
	public static int energyPerOperation = 0;
	public static int maxEnergy = 0;
	
	public static final void commonPreinit(FMLPreInitializationEvent e) {
		Configuration config = new Configuration(new File(e.getModConfigurationDirectory(), "/miningwells/config.cfg"));
		config.load();
		
		displayMiningWellJoke = config.getBoolean("displayMiningWellJoke", "misc", true, "Displays the joke: \"Are you MINING WELL?\" on all Mining Well items. Very necessary.");
		ticksBetweenMining = config.getInt("ticksBetweenMining", "miningwell", 0, 0, 20, "The minimum number of ticks per mining operation. 0 means that the next block will be broken in the next tick, if power supply is high enough.");
		energyPerOperation = config.getInt("energyPerOperation", "miningwell", 20, 5, 100, "The number of energy units per operation (this is multiplied by the hardness of a block).");
		maxEnergy = config.getInt("maxEnergy", "miningwell", 100000, 999, 999999, "The maximum number of energy units that can be stored inside the mining well.");
		
		config.save();
	}
	
}