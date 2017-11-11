package com.cjburkey.miningwells.config;

import java.io.File;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModConfig {
	
	public static boolean displayMiningWellJoke = false;
	public static int ticksBetweenMining = 0;
	public static int energyPerOperation = 0;
	public static int maxEnergy = 0;
	public static float silkRatio = 0;
	public static float fortuneRatio = 0;
	
	public static final void commonPreinit(FMLPreInitializationEvent e) {
		Configuration config = new Configuration(new File(e.getModConfigurationDirectory(), "/miningwells/config.cfg"));
		config.load();
		
		displayMiningWellJoke = config.getBoolean("displayMiningWellJoke", "misc", true, "Displays the a pun on all Mining Well items. Yes, very necessary.");
		ticksBetweenMining = config.getInt("ticksBetweenMining", "miningwell", 0, 0, 35, "The minimum number of ticks per mining operation. 0 means that the next block will be broken in the next tick, if power supply is high enough.");
		energyPerOperation = config.getInt("energyPerOperation", "miningwell", 256, 20, 1200, "The number of energy units per operation (this is multiplied by the hardness of a block + 1). If this number is 20, stone will require 40 to mine. Subtract and extra 20 for exact calculations.");
		maxEnergy = config.getInt("maxEnergy", "miningwell", 16384, 100, 1000000, "The maximum number of energy units that can be stored inside the mining well.");
		silkRatio = config.getFloat("silkRation", "miningwell", 1.0f, 0.0f, 10.0f, "The required energy is multiplied by this number if a silk touch upgrade is present, and then added back to the required energy.");
		fortuneRatio = config.getFloat("fortuneRatio", "miningwell", 0.45f, 0.0f, 3.0f, "The level of fortune present is multiplied by this, then the required energy is multiplied by that, and then added back to the required energy.");
		
		config.save();
	}
	
}