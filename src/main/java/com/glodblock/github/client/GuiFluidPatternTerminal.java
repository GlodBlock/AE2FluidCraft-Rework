package com.glodblock.github.client;

import appeng.api.storage.ITerminalHost;
import appeng.client.gui.implementations.GuiPatternTerm;
import appeng.client.gui.widgets.GuiTabButton;
import appeng.client.render.StackSizeRenderer;
import appeng.container.slot.SlotFake;
import com.glodblock.github.client.container.ContainerFluidPatternTerminal;
import com.glodblock.github.client.render.FluidRenderUtils;
import com.glodblock.github.inventory.GuiType;
import com.glodblock.github.inventory.InventoryHandler;
import com.glodblock.github.util.Ae2ReflectClient;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class GuiFluidPatternTerminal extends GuiPatternTerm {

    private final StackSizeRenderer stackSizeRenderer = Ae2ReflectClient.getStackSizeRenderer(this);
    private GuiTabButton craftingStatusBtn;

    public GuiFluidPatternTerminal(InventoryPlayer inventoryPlayer, ITerminalHost te) {
        super(inventoryPlayer, te);
        ContainerFluidPatternTerminal container = new ContainerFluidPatternTerminal(inventoryPlayer, te);
        container.setGui(this);
        this.inventorySlots = container;
        Ae2ReflectClient.setGuiContainer(this, container);
    }

    @Override
    public void initGui() {
        super.initGui();
        craftingStatusBtn = Ae2ReflectClient.getCraftingStatusButton(this);
    }

    @Override
    public void drawSlot(Slot slot) {
        if (!(slot instanceof SlotFake && FluidRenderUtils.renderFluidPacketIntoGuiSlot(
                slot, slot.getStack(), stackSizeRenderer, fontRenderer))) {
            super.drawSlot(slot);
        }
    }

    @Override
    protected void actionPerformed(final GuiButton btn) {
        if (btn == craftingStatusBtn) {
            InventoryHandler.switchGui(GuiType.FLUID_PAT_TERM_CRAFTING_STATUS);
        } else {
            super.actionPerformed(btn);
        }
    }

}