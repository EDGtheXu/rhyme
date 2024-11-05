package rhymestudio.rhyme.client;


import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import rhymestudio.rhyme.client.model.DoublePeaModel;
import rhymestudio.rhyme.client.model.IcePeaModel;
import rhymestudio.rhyme.client.model.PeaModel;
import rhymestudio.rhyme.client.model.SunflowerModel;
import rhymestudio.rhyme.client.render.entity.BasePlantRenderer;

import rhymestudio.rhyme.client.render.entity.SunRenderer;
import rhymestudio.rhyme.client.render.entity.proj.PeaProjRenderer;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.registry.ModEntities;

import java.util.function.Function;

import static rhymestudio.rhyme.registry.ModEntities.*;

public class RegisterRenderer {
    public static void register(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.SUN_ITEM_ENTITY.get(), SunRenderer::new);
        // tip 植物
//        event.registerEntityRenderer(, dispatcher-> new BasePlantRenderer<SunFlower, HierarchicalModel<SunFlower>>(dispatcher,new SunflowerModel(dispatcher.bakeLayer(SunflowerModel.LAYER_LOCATION))));
        registerOne(event,SUN_FLOWER.get(),c->new SunflowerModel(c.bakeLayer(SunflowerModel.LAYER_LOCATION)));
        registerOne(event,PEA.get(),c->new PeaModel(c.bakeLayer(PeaModel.LAYER_LOCATION)));
        registerOne(event,ICE_PEA.get(),c->new IcePeaModel(c.bakeLayer(IcePeaModel.LAYER_LOCATION)));
        registerOne(event,DOUBLE_PEA.get(),c->new DoublePeaModel(c.bakeLayer(DoublePeaModel.LAYER_LOCATION)));




//        event.registerEntityRenderer(ModEntities.PEA.get(), (dispatcher)-> new BasePlantRenderer<AbstractPlant, HierarchicalModel<AbstractPlant>>(dispatcher,new PeaModel(dispatcher.bakeLayer(PeaModel.LAYER_LOCATION))));
//        event.registerEntityRenderer(ModEntities.DOUBLE_PEA.get(), (dispatcher)-> new BasePlantRenderer<AbstractPlant, HierarchicalModel<AbstractPlant>>(dispatcher,new DoublePeaModel(dispatcher.bakeLayer(DoublePeaModel.LAYER_LOCATION))));
        // tip 子弹
        event.registerEntityRenderer(ModEntities.PEA_PROJ.get(), PeaProjRenderer::new);
        event.registerEntityRenderer(ModEntities.ICE_PEA_PROJ.get(), PeaProjRenderer::new);


    }
    public static void registerOne(EntityRenderersEvent.RegisterRenderers event, EntityType<AbstractPlant> entityType, Function<EntityRendererProvider.Context, HierarchicalModel<AbstractPlant>> model){
        event.registerEntityRenderer(entityType, (dispatcher)-> new BasePlantRenderer<>(dispatcher, model.apply(dispatcher)));
    }
}
