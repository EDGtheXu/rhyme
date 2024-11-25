package rhymestudio.rhyme.registry.Entities;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.client.animation.plantAnimations.*;
import rhymestudio.rhyme.client.model.plantModels.*;
import rhymestudio.rhyme.entity.BaseProj;
import rhymestudio.rhyme.entity.plants.*;
import rhymestudio.rhyme.entity.proj.LineProj;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.SunItemEntity;
import rhymestudio.rhyme.entity.proj.ThrowableProj;
import rhymestudio.rhyme.registry.ModEffects;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static rhymestudio.rhyme.entity.plants.prefabs.PresetAttacks.*;
import static rhymestudio.rhyme.entity.plants.prefabs.PresetBuilders.*;



public class PlantEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Rhyme.MODID);

    // tip 植物
    //      tip 生产类
    public static final DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> SUN_FLOWER = registerPlants("sunflower", (type, level)->
            new SunFlower(level,NORMAL_SUNFLOWER_PLANT.get()));

    //      tip 豌豆类
    public static final DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> PEA = registerPlants("pea_shooter",(type, level)->
            new Pea(type,level, PeaAnimation.idle,PeaAnimation.shoot,
                    PEA_SHOOT, NORMAL_PEA_PLANT.get()));

    public static final DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> ICE_PEA = registerPlants("ice_pea_shooter",(type, level)->
            new Pea(type,level, IcePeaAnimation.idle_normal, IcePeaAnimation.shoot,
                    ICE_PEA_SHOOT, NORMAL_PEA_PLANT.get()));

    public static final DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> DOUBLE_PEA = registerPlants("double_pea_shooter",(type,level)->
            new Pea(type,level, DoublePeaAnimation.idle_normal,DoublePeaAnimation.shoot,
                    DOUBLE_PEA_SHOOT, NORMAL_PEA_PLANT.get()));

    //      tip 坚果类
    public static final DeferredHolder<EntityType<?>, EntityType<WallNut>> WALL_NUT = registerPlants("wall_nut",(type, level)->
            new WallNut(type,level, WallNutAnimation.idle1,WallNutAnimation.idle2,WallNutAnimation.idle3, DEFENSE_PLANT.apply(150)));

    //      tip 土豆雷类
    public static final DeferredHolder<EntityType<?>, EntityType<PotatoMine>> POTATO_MINE = registerPlants("potato_mine",(type,level)->
            new PotatoMine(type,level, PotatoMineAnimation.idle, PotatoMineAnimation.up, PotatoMineAnimation.idle_on, PotatoMineAnimation.bomb,
                    300 * 2,2,DEFENSE_PLANT.apply(50)),1f,0.5f);

    //      tip 蘑菇类
    public static final DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> PUFF_SHROOM = registerPlants("puff_shroom",(type, level)->
            new PuffShroom(type,level, PuffShroomAnimation.sleeping, PuffShroomAnimation.idle, PuffShroomAnimation.attack , SPORE_SHOOT, NORMAL_PEA_PLANT.get()),0.75f,0.75f);

    //      tip 投手类
    public static final DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> CABBAGE_PULT = registerPlants("cabbage_pult",(type,level)->
            new Pea(type,level, PeaAnimation.idle, PeaAnimation.shoot,
                    THROWN_PEA_SHOOT, NORMAL_PEA_PLANT.get()));

    public static <T extends AbstractPlant> DeferredHolder<EntityType<?>, EntityType<T>> registerPlants(String name, EntityType.EntityFactory<T> entityFactory) {
        return registerPlants(name,entityFactory,0.9F,1);
    }
    public static <T extends AbstractPlant> DeferredHolder<EntityType<?>, EntityType<T>> registerPlants(String name, EntityType.EntityFactory<T> entityFactory, float w,float h) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(entityFactory , MobCategory.MISC).clientTrackingRange(10).sized(w,h).build("rhyme:entity."+name));
    }

    // tip 弹幕
         // tip 直线
    public static final DeferredHolder<EntityType<?>, EntityType<LineProj>> PEA_PROJ = registerProj("pea_proj",(e,l)->
            new LineProj(e,l,20));
    public static final DeferredHolder<EntityType<?>, EntityType<LineProj>> ICE_PEA_PROJ = registerProj("ice_pea_proj",(e,l)->
            new LineProj(e,l,20,new MobEffectInstance(ModEffects.FROZEN_EFFECT,20 * 5), BaseProj.TextureLib.ICE_PEA));

        // tip 投掷
    public static final DeferredHolder<EntityType<?>, EntityType<ThrowableProj>> THROWN_PEA_PROJ = registerProj("thrown_pea_proj",
                ThrowableProj::new);




    public static <T extends BaseProj> DeferredHolder<EntityType<?>, EntityType<T>> registerProj(String name, EntityType.EntityFactory<T> entityFactory,float w,float h) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(entityFactory , MobCategory.MISC).clientTrackingRange(10).sized(w,h).build("rhyme:entity.proj."+name));
    }
    public static <T extends BaseProj> DeferredHolder<EntityType<?>, EntityType<T>> registerProj(String name, EntityType.EntityFactory<T> entityFactory) {
        return registerProj(name,entityFactory,1,1);
    }
    // tip 阳光
    public static final Supplier<EntityType<SunItemEntity>> SUN_ITEM_ENTITY = ENTITIES.register("sun", () -> EntityType.Builder.<SunItemEntity>of(SunItemEntity::new, MobCategory.MISC).clientTrackingRange(16).updateInterval(20).build("rhyme:entity.sun"));







    // tip 快速注册简单实体的模型、属性、模型、渲染器
    public static List<EntityRegisterContainer<AbstractPlant>> registerAbstractPlants = List.of(
            create(SUN_FLOWER, SunflowerModel.class),
            create(PEA,  PeaModel.class),
            create(ICE_PEA, IcePeaModel.class),
            create(DOUBLE_PEA, DoublePeaModel.class),
            create(PUFF_SHROOM, PuffShroomModel.class),
            create(CABBAGE_PULT, PeaModel.class)
    );
    public static List<EntityRegisterContainer<WallNut>> registerNutWalls = List.of(
            create(WALL_NUT, WallNutModel.class)
    );





    public static <T extends AbstractPlant>EntityRegisterContainer<T> create(DeferredHolder<EntityType<?>, EntityType<T>> entity, Class<? extends HierarchicalModel<T>> clz){
        return new EntityRegisterContainer<>(entity,clz);}
    public record EntityRegisterContainer<T extends AbstractPlant>(DeferredHolder<EntityType<?>, EntityType<T>> entity, Class<? extends HierarchicalModel<T>> clz) {
        public Function<EntityRendererProvider.Context, HierarchicalModel<T>> getRenderSup(){
            Constructor<? extends HierarchicalModel<T>> c;
            try{
                c = clz.getDeclaredConstructor(ModelPart.class);
            }catch (Exception e){ throw new RuntimeException();}

            Function<EntityRendererProvider.Context, HierarchicalModel<T>> res = cnt->
            {
                try {
                    return c.newInstance(cnt.bakeLayer(getModelDefine()));
                } catch (Exception e) {throw new RuntimeException(e);}
            };
            return res;
        }
        public ModelLayerLocation getModelDefine(){
            Field field2;
            try{
                field2  = clz.getDeclaredField("LAYER_LOCATION");
            }catch (Exception e){ throw new RuntimeException();}
            field2.setAccessible(true);

            try{
                return (ModelLayerLocation) field2.get(null);
            }catch (Exception e){ throw new RuntimeException();}

        }
        public Supplier<LayerDefinition> getLayerDefinition(){
            return  ()-> {
                try {
                    return (LayerDefinition) clz.getMethod("createBodyLayer").invoke(null);
                } catch (Exception e) {throw new RuntimeException(e);}
            };
        }
    }
}
