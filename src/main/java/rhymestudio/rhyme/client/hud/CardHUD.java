package rhymestudio.rhyme.client.hud;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.registry.ModItems;
import rhymestudio.rhyme.utils.Computer;

import javax.smartcardio.Card;
import java.awt.*;

public class CardHUD {

    //阳光位置
    static int x = 20;
    static int y = 5;
    static int itemInternalX0 = 40;
    static int itemInternalX = 40;

    public Minecraft mc;
    public Player player;


    public CardHUD(Minecraft mc,Player player) {
        this.mc = mc;
        this.player = player;
    }

    private static CardHUD instance;
    public static CardHUD getInstance() {
        if(instance == null || instance.player!= Minecraft.getInstance().player){
            instance = new CardHUD(Minecraft.getInstance(),Minecraft.getInstance().player);
        }
        return instance;
    }

    public void render(GuiGraphics guiGraphics) {

        guiGraphics.pose().pushPose();

        guiGraphics.pose().translate(x,y,0);
        drawSunCard(guiGraphics,0,0,20,20);

        guiGraphics.pose().translate(itemInternalX0,0,0);
//        drawCard(guiGraphics,ModItems.SUN_FLOWER.get(),0,0,2);

        guiGraphics.pose().translate(itemInternalX,0,0);
//        drawCard(guiGraphics,ModItems.PEA_ITEM.get(),0,0,2);

//        var bf =Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(RenderType.gui());

/*
        //装备
        for(int i=0;i<4;i++){
            int x2 = x + 30 + 18 * i;
            int y2 = y;
            //guiGraphics.vLine(x2-2,y2, y2 + 18,Color.MAGENTA.getRGB());
            ItemStack stack = player.getInventory().getArmor(3-i);
            guiGraphics.renderItem(stack,x2, y2 );
            drawItemBar(stack,guiGraphics,x2, y2 );
        }

        //右手物品
        guiGraphics.pose().translate(x,y,0);
        guiGraphics.pose().scale(1.1f,1.1f,1.1f);
        ItemStack stack = player.getMainHandItem();
        guiGraphics.renderItem(stack,0,0);

        //名称
        String str = stack.getDisplayName().getString();
        guiGraphics.drawString(minecraft.font,str.substring(1,str.length()-1),0,-10, stack.getRarity().getStyleModifier().apply(Style.EMPTY).getColor().getValue());

        //耐久条
        if (stack.isBarVisible()) {
            drawItemBar(stack, guiGraphics, 0, 0);
            int dur = stack.getDamageValue();
            int full = stack.getMaxDamage();
            guiGraphics.pose().translate(3, 15, 0);
            guiGraphics.pose().scale(0.7f, 0.7f, 0.7f);
            guiGraphics.drawString(minecraft.font, String.valueOf(full - dur), 1, 1, stack.getBarColor());
        }
*/
        guiGraphics.pose().popPose();
    }

    public static void drawItemBar(ItemStack stack,GuiGraphics g,int x,int y){
        if (stack.isBarVisible()) {
            int l = stack.getBarWidth();
            int i = stack.getBarColor();
            int i1 = x + 2;
            int j1 = y + 13;
            g.fill(RenderType.guiOverlay(), i1, j1, i1 + 13, j1 + 2, -16777216);
            g.fill(RenderType.guiOverlay(), i1, j1, i1 + l, j1 + 1, i | -16777216);
        }
    }

    public static void drawIcon(GuiGraphics guiGraphics, String icon, int x, int y, int w, int h){
        guiGraphics.blit(Rhyme.space(icon),x, y, 0, 0, w, h, w, h);
    }

    public void drawSunCard(GuiGraphics guiGraphics, int x, int y,int w,int h){
        drawIcon(guiGraphics,"textures/item/sun_item.png",0, 0, w, h);
        int count = Computer.getInventoryItemCount(player, ModItems.SUN_ITEM.get());
        String countStr =String.valueOf(count) ;
        guiGraphics.drawString(mc.font,countStr,w/2-3,h+2, Color.yellow.getRGB());
    }

    public void drawCard(GuiGraphics guiGraphics, Item item, int x, int y, float scale){
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale,scale,scale);
        guiGraphics.renderItem(item.getDefaultInstance(),x, y);

//        String countStr =String.valueOf(count.get()) ;
//        guiGraphics.drawString(mc.font,countStr,10,35, Color.yellow.getRGB());

        guiGraphics.pose().popPose();
    }

/*
    private void drawBar(GuiGraphics guiGraphics, int x, int y, BossEvent bossEvent, int progress, ResourceLocation[] barProgressSprites, ResourceLocation[] overlayProgressSprites) {
        RenderSystem.enableBlend();
        guiGraphics.blitSprite(barProgressSprites[bossEvent.getColor().ordinal()], 182, 5, 0, 0, x, y, progress, 5);
        if (bossEvent.getOverlay() != BossEvent.BossBarOverlay.PROGRESS) {
            guiGraphics.blitSprite(overlayProgressSprites[bossEvent.getOverlay().ordinal() - 1], 182, 5, 0, 0, x, y, progress, 5);
        }

        RenderSystem.disableBlend();
    }
*/

}