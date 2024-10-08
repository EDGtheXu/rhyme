package rhymestudio.rhyme.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rhymestudio.rhyme.client.hud.CardHUD;

import static rhymestudio.rhyme.Rhyme.MODID;

@Mod.EventBusSubscriber(modid = MODID,value = Dist.CLIENT)
public class ModRenderEvent {
    static int time = 0;
    @SubscribeEvent
    public static void RenderGuiEvent(RenderGuiEvent.Post event) {
        CardHUD hud = CardHUD.getInstance();
        hud.render(event.getGuiGraphics());

    }
}