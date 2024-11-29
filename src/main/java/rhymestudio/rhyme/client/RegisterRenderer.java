package rhymestudio.rhyme.client;


import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.client.model.plantModels.*;
import rhymestudio.rhyme.client.model.zombieModels.NormalZombieModel;
import rhymestudio.rhyme.client.render.entity.BasePlantRenderer;

import rhymestudio.rhyme.client.render.entity.SunRenderer;
import rhymestudio.rhyme.client.render.entity.proj.PeaProjRenderer;
import rhymestudio.rhyme.client.render.entity.zombie.NormalZombieRenderer;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.registry.Entities.PlantEntities;
import rhymestudio.rhyme.registry.Entities.Zombies;

import java.lang.reflect.Constructor;
import java.util.function.Function;

import static rhymestudio.rhyme.client.RegisterModel.getModelDefine;
import static rhymestudio.rhyme.registry.Entities.PlantEntities.*;

@EventBusSubscriber(modid = Rhyme.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegisterRenderer {
    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(PlantEntities.SUN_ITEM_ENTITY.get(), SunRenderer::new);
        // tip 植物
//        event.registerEntityRenderer(, dispatcher-> new BasePlantRenderer<SunFlower, HierarchicalModel<SunFlower>>(dispatcher,new SunflowerModel(dispatcher.bakeLayer(SunflowerModel.LAYER_LOCATION))));

        registerOne(event,SUN_FLOWER.get(),getRenderSup(SunflowerModel.class));
        registerOne(event,PEA.get(),getRenderSup(PeaModel.class));
        registerOne(event,ICE_PEA.get(),getRenderSup(IcePeaModel.class));
        registerOne(event,DOUBLE_PEA.get(),getRenderSup(DoublePeaModel.class));
        registerOne(event,WALL_NUT.get(),getRenderSup(WallNutModel.class),0.5f,1f);
        registerOne(event,POTATO_MINE.get(),getRenderSup(PotatoMineModel.class),0,1f);
        registerOne(event,PUFF_SHROOM.get(),getRenderSup(PuffShroomModel.class),0.2f,0.5f);
        registerOne(event,CABBAGE_PULT.get(),getRenderSup(CabbageModel.class));


        // tip 子弹
        event.registerEntityRenderer(PlantEntities.PEA_PROJ.get(), PeaProjRenderer::new);
        event.registerEntityRenderer(PlantEntities.ICE_PEA_PROJ.get(), PeaProjRenderer::new);
        event.registerEntityRenderer(CABBAGE_PROJ.get(), PeaProjRenderer::new);


        // 僵尸
        event.registerEntityRenderer(Zombies.NORMAL_ZOMBIE.get(), c-> new NormalZombieRenderer<>(c, new NormalZombieModel<>(c.bakeLayer(NormalZombieModel.LAYER_LOCATION))));
    }




    public static <T extends AbstractPlant>void registerOne(EntityRenderersEvent.RegisterRenderers event, EntityType<T> entityType, Function<EntityRendererProvider.Context, HierarchicalModel<T>> model){
        registerOne(event,entityType,model,0.35f,1f);
    }

    public static <T extends AbstractPlant>void registerOne(EntityRenderersEvent.RegisterRenderers event, EntityType<T> entityType, Function<EntityRendererProvider.Context, HierarchicalModel<T>> model,float shadowRadius,float scale){
        registerOne(event,entityType,model,shadowRadius,scale,false);
    }

    public static <T extends AbstractPlant>void registerOne(EntityRenderersEvent.RegisterRenderers event, EntityType<T> entityType, Function<EntityRendererProvider.Context, HierarchicalModel<T>> model,float shadowRadius,float scale,boolean rotY){
        event.registerEntityRenderer(entityType, (dispatcher)-> new BasePlantRenderer<>(dispatcher, model.apply(dispatcher), shadowRadius,scale, rotY));
    }


    public static <T extends AbstractPlant>Function<EntityRendererProvider.Context, HierarchicalModel<T>> getRenderSup(Class<? extends HierarchicalModel<T>>  clz){
        Constructor<? extends HierarchicalModel<T>> c;
        try{
            c = clz.getDeclaredConstructor(ModelPart.class);
        }catch (Exception e){ throw new RuntimeException();}

        Function<EntityRendererProvider.Context, HierarchicalModel<T>> res = cnt->
        {
            try {
                return c.newInstance(cnt.bakeLayer(getModelDefine(clz)));
            } catch (Exception e) {throw new RuntimeException(e);}
        };
        return res;
    }
}
