package rhymestudio.rhyme.client.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.client.event.RenderTooltipEvent.GatherComponents;
import rhymestudio.rhyme.client.animate.ExpertColorAnimation;
import rhymestudio.rhyme.client.animate.MasterColorAnimation;
import rhymestudio.rhyme.client.post.PostUtil;

import static rhymestudio.rhyme.Rhyme.MODID;


@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.GAME,value = Dist.CLIENT)
public class DisplayEvent {
    @SubscribeEvent
    public static void onDisplay(GatherComponents event) {
        /*
        ModRarity rarity = event.getItemStack().get(ModDataComponentTypes.MOD_RARITY);
        if(rarity!=null){
            var a =  event.getTooltipElements();
            if(!a.isEmpty()){
                a.getFirst().ifLeft(s-> ((MutableComponent)s).withColor(rarity.getColor()));
            }
        }
        */
    }
    @SubscribeEvent
    public static void onTick(ClientTickEvent.Pre event) {
        MasterColorAnimation.INSTANCE.updateColor();
        ExpertColorAnimation.INSTANCE.updateColor();

    }

    @SubscribeEvent
    public static void onTickEnd(ClientTickEvent.Pre event) {


        PostUtil.clear();

    }

    @SubscribeEvent
    public static void onRenderTooltip(RenderGuiLayerEvent.Pre event) {
        /*
        if(event.getName().toString().equals("minecraft:selected_item_name")){
            ItemStack stack = Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND);
            ModRarity rarity = stack.get(ModDataComponentTypes.MOD_RARITY);
            int color;
            if(rarity==null){
                return;
            }
            color = rarity.getColor();
            float r = (color >> 16 & 255) / 255.0F;

            float g = (color >> 8 & 255) / 255.0F;
            float b = (color & 255) / 255.0F;
            event.getGuiGraphics().setColor(r,g,b,1);
        }else{
            event.getGuiGraphics().setColor(1,1,1,1);
        }
        */
    }

    @SubscribeEvent
    public static void renderLevelStage(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_LEVEL) {
            //PostUtil.postProcess();

        }

    }



}
