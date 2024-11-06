package rhymestudio.rhyme.registry;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.client.animation.*;
import rhymestudio.rhyme.client.model.*;
import rhymestudio.rhyme.entity.BaseProj;
import rhymestudio.rhyme.entity.plants.*;
import rhymestudio.rhyme.entity.proj.LineProj;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.SunItemEntity;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static rhymestudio.rhyme.entity.plants.prefabs.PresetAttacks.*;
import static rhymestudio.rhyme.entity.plants.prefabs.PresetBuilders.*;

@EventBusSubscriber(modid = Rhyme.MODID, bus = EventBusSubscriber.Bus.MOD)

public class ModEntities {
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
    public static final DeferredHolder<EntityType<?>, EntityType<NutWall>> NUT_WALL = registerPlants("nut_wall",(type,level)->
            new NutWall(type,level, null, DEFENSE_PLANT.apply(200)));

    //      tip 土豆雷类
    public static final DeferredHolder<EntityType<?>, EntityType<PotatoMine>> POTATO_MINE = registerPlants("potato_mine",(type,level)->
            new PotatoMine(type,level, PotatoMineUnderAnimation.idle, PotatoMineUnderAnimation.up, PotatoMineOnAnimation.idle_on,PotatoMineOnAnimation.bomb,20 * 10,DEFENSE_PLANT.apply(50)));

    //      tip 蘑菇类
    public static final DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> PUFF_SHROOM = registerPlants("puff_shroom",(type, level)->
            new PuffShroom(type,level, PuffShroomAnimation.sleeping, PuffShroomAnimation.idle, PuffShroomAnimation.attack , PEA_SHOOT, DEFENSE_PLANT.apply(50)));



    public static <T extends AbstractPlant> DeferredHolder<EntityType<?>, EntityType<T>> registerPlants(String name, EntityType.EntityFactory<T> entityFactory) {
        return registerPlants(name,entityFactory,1,1);
    }
    public static <T extends AbstractPlant> DeferredHolder<EntityType<?>, EntityType<T>> registerPlants(String name, EntityType.EntityFactory<T> entityFactory, float w,float h) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(entityFactory , MobCategory.MISC).clientTrackingRange(10).sized(w,h).build("rhyme:entity."+name));
    }

    // tip 弹幕
    public static final DeferredHolder<EntityType<?>, EntityType<LineProj>> PEA_PROJ = registerProj("pea_proj",(e,l)->
            new LineProj(e,l,10,20));
    public static final DeferredHolder<EntityType<?>, EntityType<LineProj>> ICE_PEA_PROJ = registerProj("ice_pea_proj",(e,l)->
            new LineProj(e,l,10,20,new MobEffectInstance(ModEffects.FROZEN_EFFECT,20 * 5), BaseProj.TextureLib.ICE_PEA));



    public static <T extends LineProj> DeferredHolder<EntityType<?>, EntityType<T>> registerProj(String name, EntityType.EntityFactory<T> entityFactory,float w,float h) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(entityFactory , MobCategory.MISC).clientTrackingRange(10).sized(w,h).build("rhyme:entity.proj."+name));
    }
    public static <T extends LineProj> DeferredHolder<EntityType<?>, EntityType<T>> registerProj(String name, EntityType.EntityFactory<T> entityFactory) {
        return registerProj(name,entityFactory,1,1);
    }
    // tip 阳光
    public static final Supplier<EntityType<SunItemEntity>> SUN_ITEM_ENTITY = ENTITIES.register("sun", () -> EntityType.Builder.<SunItemEntity>of(SunItemEntity::new, MobCategory.MISC).clientTrackingRange(16).updateInterval(20).build("rhyme:entity.sun"));







    //tip 注册属性
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {

        AttributeSupplier.Builder genericPlant = Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH)
                .add(Attributes.ATTACK_DAMAGE)
                .add(Attributes.FOLLOW_RANGE, 16)
                .add(Attributes.MOVEMENT_SPEED, 0f)
                .add(Attributes.KNOCKBACK_RESISTANCE, 20)

                ;

//        event.put(ModEntities.SUN_FLOWER.get(), genericPlant.build());
//        event.put(ModEntities.PEA.get(), genericPlant.build());
//        event.put(ModEntities.ICE_PEA.get(), genericPlant.build());
//        event.put(ModEntities.DOUBLE_PEA.get(), genericPlant.build());

        registerContainers.forEach(r->event.put(r.entity.get(), genericPlant.build()));

        event.put(ModEntities.POTATO_MINE.get(), genericPlant.build());
        event.put(ModEntities.NUT_WALL.get(), genericPlant.build());

    }

    // tip 快速注册简单实体的模型、属性、模型、渲染器
    public static List<EntityRegisterContainer> registerContainers = List.of(
            create(SUN_FLOWER, SunflowerModel.class),
            create(PEA,  PeaModel.class),
            create(ICE_PEA, IcePeaModel.class),
            create(DOUBLE_PEA, DoublePeaModel.class),
            create(PUFF_SHROOM, PuffShroomModel.class)

//            create(NUT_WALL,NutWallModel.class)

    );

    public static EntityRegisterContainer create(DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> entity, Class<? extends HierarchicalModel<AbstractPlant>> clz){
        return new EntityRegisterContainer(entity,clz);}
    public record EntityRegisterContainer(DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> entity, Class<? extends HierarchicalModel<AbstractPlant>> clz) {
        public Function<EntityRendererProvider.Context, HierarchicalModel<AbstractPlant>> getRenderSup(){
            Constructor<? extends HierarchicalModel<AbstractPlant>> c;
            try{
                c = clz.getDeclaredConstructor(ModelPart.class);
            }catch (Exception e){ throw new RuntimeException();}

            Function<EntityRendererProvider.Context, HierarchicalModel<AbstractPlant>> res = cnt->
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
        public Supplier<LayerDefinition> getCreate(){
            return  ()-> {
                try {
                    return (LayerDefinition) clz.getMethod("createBodyLayer").invoke(null);
                } catch (Exception e) {throw new RuntimeException(e);}
            };
        }
    }
}
