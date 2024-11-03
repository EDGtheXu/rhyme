package rhymestudio.rhyme.registry;


import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import rhymestudio.rhyme.client.RegisterModel;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ModModels {
    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions evt) {
        RegisterModel.register(evt);


    }
}
