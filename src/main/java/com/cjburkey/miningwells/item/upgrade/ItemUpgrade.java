package com.cjburkey.miningwells.item.upgrade;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.miningwells.item.ItemBase;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemUpgrade extends ItemBase {
	
	private EnumUpgradeType upgradeType;
	private int upgradeLevel;
	
	public ItemUpgrade(EnumUpgradeType upgradeType, int level) {
		super("item_upgrade_" + upgradeType.getPublicName() + "_" + level);
		this.upgradeType = upgradeType;
		upgradeLevel = level;
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> info, ITooltipFlag flag) {
		if (stack == null) {
			return;
		}
		if (info == null) {
			info = new ArrayList<>();
		}
		info.add(I18n.format("upgrade." + upgradeType.getPublicName()));
		info.add(I18n.format("upgrade.level_info") + ": " + upgradeLevel);
	}
	
	public EnumUpgradeType getUpgradeType() {
		return upgradeType;
	}
	
	public int getUpgradeLevel() {
		return upgradeLevel;
	}
	
}