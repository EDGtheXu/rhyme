package rhymestudio.rhyme.client;


import net.minecraft.client.model.HierarchicalModel;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import rhymestudio.rhyme.client.model.peaModel;
import rhymestudio.rhyme.client.render.entity.BasePlantRenderer;
import rhymestudio.rhyme.client.render.entity.SunRenderer;

import rhymestudio.rhyme.client.render.entity.plant.peanewrender;
import rhymestudio.rhyme.client.render.entity.proj.peaProjRenderer;
import rhymestudio.rhyme.entity.plants.PeaNew;
import rhymestudio.rhyme.registry.ModEntities;

public class RegisterRenderer {
    public static void register(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.SUN_ITEM_ENTITY.get(), SunRenderer::new);
// 植物
//        event.registerEntityRenderer(ModEntities.SUN_FLOWER.get(), (dispatcher, context)-> new BasePlantRenderer<SunFlower, HierarchicalModel<SunFlower>>(context,new Sum()));
//        event.registerEntityRenderer(ModEntities.PEA_SHOOTER.get(), PeaShooterRenderer::new);
//        event.registerEntityRenderer(ModEntities.DOUBLE_PEA_SHOOTER.get(), PeaShooterRenderer::new);

//        event.registerEntityRenderer(ModEntities.PEA_NEW.get(), peanewrender::new);

        event.registerEntityRenderer(ModEntities.PEA_NEW.get(), (dispatcher)-> new BasePlantRenderer<PeaNew, HierarchicalModel<PeaNew>>(dispatcher,new peaModel(dispatcher.bakeLayer(peaModel.LAYER_LOCATION))));
// 子弹
        event.registerEntityRenderer(ModEntities.PEA_PROJ.get(), peaProjRenderer::new);

    }
}
