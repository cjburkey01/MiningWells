package com.cjburkey.miningwells.item.upgrade;

public enum EnumUpgradeType {
	
	FORTUNE("fortune"),
	SILK_TOUCH("silk"),
	BLOCKS_PER_OPERATION("bpo");
	
	private String publicName;
	
	EnumUpgradeType(String pn) {
		publicName = pn;
	}
	
	public String getPublicName() {
		return publicName;
	}
	
}