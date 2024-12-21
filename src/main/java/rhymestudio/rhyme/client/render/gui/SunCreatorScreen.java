package rhymestudio.rhyme.client.render.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.core.block.SunCreaterBlock;
import rhymestudio.rhyme.core.menu.SunCreatorMenu;
import rhymestudio.rhyme.core.registry.ModAttachments;

public class SunCreatorScreen extends AbstractContainerScreen<SunCreatorMenu> {
    private static final ResourceLocation BACKGROUND = Rhyme.space("textures/gui/sun_creator.png");
    private boolean upButtonClicked = false;
    private ItemStack upItem = null;
    private boolean downButtonClicked = false;
    private ItemStack downItem = null;
    private SunCreaterBlock.SunCreaterBlockEntity creator;
    private Button fetchSunButton;

    public SunCreatorScreen(SunCreatorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        Player owner = pPlayerInventory.player;
        var data = owner.getData(ModAttachments.PLAYER_STORAGE);
        BlockEntity blockEntity = owner.level().getBlockEntity(new BlockPos( data.x, data.y, data.z));
        if(blockEntity instanceof SunCreaterBlock.SunCreaterBlockEntity entity){
            creator = entity;
        }
        fetchSunButton = Button.builder(Component.literal("fetch"), (p_onPress_1_) -> {
            creator.count = 0;
        }).pos(130,50).size(25,16).build();

    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = imageWidth - font.width(title) - 8;
        this.inventoryLabelX = imageWidth - font.width(playerInventoryTitle) - 8;
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);

        if (isOverUpButton(pMouseX - leftPos, pMouseY - topPos)) {
            if (upItem == null) this.upItem = menu.getUpResult();
            pGuiGraphics.renderFakeItem(upItem, leftPos + 125, topPos + 35);
            this.downItem = null;
        } else if (isOverDownButton(pMouseX - leftPos, pMouseY - topPos)) {
            if (downItem == null) this.downItem = menu.getDownResult();
            pGuiGraphics.renderFakeItem(downItem, leftPos + 125, topPos + 35);
            this.upItem = null;
        } else {
            pGuiGraphics.renderFakeItem(menu.getSlot(0).getItem(), leftPos + 125, topPos + 35);
            this.upItem = null;
            this.downItem = null;
        }

        String text = menu.getRecipesAmount() == 0 ? "0/0" : menu.getCurrentIndex() + 1 + "/" + menu.getRecipesAmount();

        pGuiGraphics.drawString(font, text, leftPos + 144, topPos + 37 + (16 - font.lineHeight) / 2, 4210752, false);
        text = "error";
        if(creator != null){
            text = creator.count + "/" + SunCreaterBlock.SunCreaterBlockEntity.MAX_COUNT;
            pGuiGraphics.drawString(font, text, leftPos + 6, topPos + 30 + (16 - font.lineHeight) / 2, 4210752, false);
        }else{
            pGuiGraphics.drawString(font, text, leftPos + 6, topPos + 30 + (16 - font.lineHeight) / 2, 4210752, false);
        }

        fetchSunButton.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

    }

    @Override
    protected void renderBg(@NotNull GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        pGuiGraphics.blit(BACKGROUND, leftPos, topPos, 0, 0, imageWidth, imageHeight);
        if (upButtonClicked) {
            pGuiGraphics.blit(BACKGROUND, leftPos + 128, topPos + 25, 177, 0, 10, 7);
        } else if (downButtonClicked) {
            pGuiGraphics.blit(BACKGROUND, leftPos + 128, topPos + 54, 177, 8, 10, 7);
        }
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (isOverUpButton((int) pMouseX - leftPos, (int) pMouseY - topPos)) {
            int upIndex = menu.getUpIndex();
            if (menu.clickMenuButton(minecraft.player, upIndex)) {
                minecraft.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                minecraft.gameMode.handleInventoryButtonClick((this.menu).containerId, upIndex);
                this.upButtonClicked = true;
                this.downButtonClicked = false;
                this.upItem = null;
                return true;
            }
            return false;
        } else if (isOverDownButton((int) pMouseX - leftPos, (int) pMouseY - topPos)) {
            int downIndex = menu.getDownIndex();
            if (menu.clickMenuButton(minecraft.player, downIndex)) {
                minecraft.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                minecraft.gameMode.handleInventoryButtonClick((this.menu).containerId, downIndex);
                this.upButtonClicked = false;
                this.downButtonClicked = true;
                this.downItem = null;
                return true;
            }
            return false;
        } else if (fetchSunButton.isHovered()) {
            if (menu.clickMenuButton(minecraft.player, 0)) {
                minecraft.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                minecraft.gameMode.handleInventoryButtonClick((this.menu).containerId, 114);
                fetchSunButton.onPress();
                return true;
            }

            return true;
        }
        else {
            return super.mouseClicked(pMouseX, pMouseY, pButton);
        }
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        this.upButtonClicked = false;
        this.downButtonClicked = false;
        return super.mouseReleased(pMouseX, pMouseY, pButton);
    }

    private static boolean isOverUpButton(int x, int y) {
        return x >= 128 && x <= 138 && y >= 25 && y <= 32;
    }

    private static boolean isOverDownButton(int x, int y) {
        return x >= 128 && x <= 138 && y >= 54 && y <= 61;
    }
}
