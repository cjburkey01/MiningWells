package com.cjburkey.miningwells.player;

import java.util.UUID;
import com.cjburkey.miningwells.LogUtils;
import com.mojang.authlib.GameProfile;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

public class FakePlayerHandler {
	
	private static FakePlayer player;
	
	public static final FakePlayer getFakePlayer() {
		if (player == null) {
			player = FakePlayerFactory.get(DimensionManager.getWorld(0), new GameProfile(UUID.randomUUID(), "RoyTheMiner"));
			LogUtils.info("Created fake player: " + player.getUniqueID() + " - " + player.getName());
		}
		return player;
	}
	
}