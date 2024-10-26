package rhymestudio.rhyme.event;


import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import rhymestudio.rhyme.client.hud.CardHUD;

import static rhymestudio.rhyme.Rhyme.MODID;

@EventBusSubscriber(modid = MODID,value = Dist.CLIENT)
public class ModRenderEvent {
    static int time = 0;
    @SubscribeEvent
    public static void RenderGuiEvent(RenderGuiEvent.Post event) {
        CardHUD hud = CardHUD.getInstance();
        hud.render(event.getGuiGraphics());

    }
}