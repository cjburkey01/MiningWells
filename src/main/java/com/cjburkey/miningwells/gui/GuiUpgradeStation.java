package com.cjburkey.miningwells.gui;

import com.cjburkey.miningwells.ModInfo;
import com.cjburkey.miningwells.container.ContainerUpgradeStation;
import com.cjburkey.miningwells.packet.ModPackets;
import com.cjburkey.miningwells.packet.PacketWellToServer;
import com.cjburkey.miningwells.tile.TileEntityUpgradeStation;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiUpgradeStation extends GuiContainer {
	
	private TileEntityUpgradeStation te;
	private InventoryPlayer plyInv;

	public GuiUpgradeStation(TileEntityUpgradeStation te, InventoryPlayer plyInv) {
		super(new ContainerUpgradeStation(te, plyInv));
		this.te = te;
		this.plyInv = plyInv;
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}
	
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(new ResourceLocation(ModInfo.MODID, "textures/gui/gui_upgrade_station.png"));
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		super.drawGuiContainerForegroundLayer(x, y);
		String s = te.getDisplayName().getUnformattedText();
		fontRenderer.drawString(s, 88 - fontRenderer.getStringWidth(s) / 2, 6, 0xBFBFBF);
		fontRenderer.drawString(plyInv.getDisplayName().getUnformattedText(), 8, 72, 0xBFBFBF);
	}
	
}