package rhymestudio.rhyme.client;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import rhymestudio.rhyme.client.model.*;
import rhymestudio.rhyme.client.model.proj.PeaProjModel;
import rhymestudio.rhyme.client.render.entity.SunCreatorBlockRenderer;
import rhymestudio.rhyme.registry.ModBlocks;

import static rhymestudio.rhyme.registry.ModEntities.registerContainers;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class RegisterModel {
    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterLayerDefinitions evt) {
        evt.registerLayerDefinition(PeaProjModel.LAYER_LOCATION, PeaProjModel::createBodyLayer);
        evt.registerLayerDefinition(SunflowerModel.LAYER_LOCATION, SunflowerModel::createBodyLayer);
//        evt.registerLayerDefinition(PeaModel.LAYER_LOCATION, PeaModel::createBodyLayer);
//        evt.registerLayerDefinition(IcePeaModel.LAYER_LOCATION, IcePeaModel::createBodyLayer);
//        evt.registerLayerDefinition(DoublePeaModel.LAYER_LOCATION, DoublePeaModel::createBodyLayer);

        registerContainers.forEach(r->evt.registerLayerDefinition(r.getModelDefine(), r.getLayerDefinition()));

        evt.registerLayerDefinition(PotatoMineModel.LAYER_LOCATION, PotatoMineModel::createBodyLayer);
//        evt.registerLayerDefinition(PotatoMineUnderModel.LAYER_LOCATION, PotatoMineUnderModel::createBodyLayer);


        evt.registerLayerDefinition(NutWallModel.LAYER_LOCATION, NutWallModel::createBodyLayer);
        evt.registerLayerDefinition(NutWallHurt1.LAYER_LOCATION, NutWallHurt1::createBodyLayer);
        evt.registerLayerDefinition(NutWallHurt2.LAYER_LOCATION, NutWallHurt2::createBodyLayer);





    }


    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event){
        event.enqueueWork(()->{
//            BlockEntityRenderers.register(ModBlocks.SUN_CREATOR_BLOCK_ENTITY.get(), SunCreatorBlockRenderer::new);
        });
    }

}
