package rhymestudio.rhyme.client.event;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.client.post.PostUtil;

@EventBusSubscriber(modid = Rhyme.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class ModClientEvent {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

            PostUtil.init();
        });

    }
    @SubscribeEvent
    public static void registerAdditionalModel(ModelEvent.RegisterAdditional event) {

        event.register(ModelResourceLocation.standalone(Rhyme.space("item/armor/cone_helmet_static")));
    }
}
