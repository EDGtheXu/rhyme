package rhymestudio.rhyme.client;


import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.client.model.plantModels.SunflowerModel;
import rhymestudio.rhyme.client.model.zombieModels.NormalZombieModel;
import rhymestudio.rhyme.client.render.entity.BasePlantRenderer;

import rhymestudio.rhyme.client.render.entity.SunRenderer;
import rhymestudio.rhyme.client.render.entity.plant.PotatoMineRenderer;
import rhymestudio.rhyme.client.render.entity.proj.PeaProjRenderer;
import rhymestudio.rhyme.client.render.entity.zombie.NormalZombieRenderer;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.registry.Entities.PlantEntities;
import rhymestudio.rhyme.registry.Entities.Zombies;

import java.util.function.Function;
import static rhymestudio.rhyme.registry.Entities.PlantEntities.*;

@EventBusSubscriber(modid = Rhyme.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegisterRenderer {
    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(PlantEntities.SUN_ITEM_ENTITY.get(), SunRenderer::new);
        // tip 植物
//        event.registerEntityRenderer(, dispatcher-> new BasePlantRenderer<SunFlower, HierarchicalModel<SunFlower>>(dispatcher,new SunflowerModel(dispatcher.bakeLayer(SunflowerModel.LAYER_LOCATION))));
        registerOne(event,SUN_FLOWER.get(),c->new SunflowerModel(c.bakeLayer(SunflowerModel.LAYER_LOCATION)));
//        registerOne(event,PEA.get(),c->new PeaModel(c.bakeLayer(PeaModel.LAYER_LOCATION)));
//        registerOne(event,ICE_PEA.get(),c->new IcePeaModel(c.bakeLayer(IcePeaModel.LAYER_LOCATION)));
//        registerOne(event,DOUBLE_PEA.get(),c->new DoublePeaModel(c.bakeLayer(DoublePeaModel.LAYER_LOCATION)));

        registerAbstractPlants.forEach(r->registerOne(event,r.entity().get(),r.getRenderSup()));
        registerNutWalls.forEach(r->registerOne(event,r.entity().get(),r.getRenderSup()));


        event.registerEntityRenderer(POTATO_MINE.get(), PotatoMineRenderer::new);

        // tip 子弹
        event.registerEntityRenderer(PlantEntities.PEA_PROJ.get(), PeaProjRenderer::new);
        event.registerEntityRenderer(PlantEntities.ICE_PEA_PROJ.get(), PeaProjRenderer::new);
        event.registerEntityRenderer(THROWN_PEA_PROJ.get(), PeaProjRenderer::new);


        // 僵尸
        event.registerEntityRenderer(Zombies.NORMAL_ZOMBIE.get(), c-> new NormalZombieRenderer<>(c, new NormalZombieModel(c.bakeLayer(NormalZombieModel.LAYER_LOCATION))));
    }




    public static <T extends AbstractPlant>void registerOne(EntityRenderersEvent.RegisterRenderers event, EntityType<T> entityType, Function<EntityRendererProvider.Context, HierarchicalModel<T>> model){
        registerOne(event,entityType,model,1.0f,0.3f);
    }

    public static <T extends AbstractPlant>void registerOne(EntityRenderersEvent.RegisterRenderers event, EntityType<T> entityType, Function<EntityRendererProvider.Context, HierarchicalModel<T>> model,float shadowRadius,float scale){
        registerOne(event,entityType,model,scale,shadowRadius,false);
    }

    public static <T extends AbstractPlant>void registerOne(EntityRenderersEvent.RegisterRenderers event, EntityType<T> entityType, Function<EntityRendererProvider.Context, HierarchicalModel<T>> model,float shadowRadius,float scale,boolean rotY){
        event.registerEntityRenderer(entityType, (dispatcher)-> new BasePlantRenderer<>(dispatcher, model.apply(dispatcher), shadowRadius,scale, rotY));
    }

}
