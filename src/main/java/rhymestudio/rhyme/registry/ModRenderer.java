package rhymestudio.rhyme.registry;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.client.render.RendererRegister;

@Mod.EventBusSubscriber(modid = Rhyme.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModRenderer {



    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        RendererRegister.register(event);


    }
}
