package rhymestudio.rhyme.registry;

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
import rhymestudio.rhyme.client.animation.DoublePeaAnimation;
import rhymestudio.rhyme.client.animation.IcePeaAnimation;
import rhymestudio.rhyme.client.animation.PeaAnimation;
import rhymestudio.rhyme.entity.plants.Pea;
import rhymestudio.rhyme.entity.plants.SunFlower;
import rhymestudio.rhyme.entity.proj.LineProj;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.SunItemEntity;


import java.util.function.Supplier;

import static rhymestudio.rhyme.entity.plants.prefabs.PresetAttacks.*;
import static rhymestudio.rhyme.entity.plants.prefabs.PresetBuilders.*;

@EventBusSubscriber(modid = Rhyme.MODID, bus = EventBusSubscriber.Bus.MOD)

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Rhyme.MODID);

    // tip 植物
    public static final DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> SUN_FLOWER = registerPlants("sunflower", (type, level)->
        new SunFlower(level,NORMAL_SUNFLOWER_PLANT.get()));


    public static final DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> PEA = registerPlants("pea_shooter",(type, level)->
            new Pea(type,level, PeaAnimation.idle,PeaAnimation.shoot,
                    PEA_SHOOT, NORMAL_PEA_PLANT.get()));

    public static final DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> ICE_PEA = registerPlants("ice_pea_shooter",(type, level)->
            new Pea(type,level, IcePeaAnimation.idle_normal, IcePeaAnimation.shoot,
                    ICE_PEA_SHOOT, NORMAL_PEA_PLANT.get()));

    public static final DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> DOUBLE_PEA = registerPlants("double_pea_shooter",(type,level)->
            new Pea(type,level, DoublePeaAnimation.idle_normal,DoublePeaAnimation.shoot,
                    DOUBLE_PEA_SHOOT, NORMAL_PEA_PLANT.get()));



    public static <T extends AbstractPlant> DeferredHolder<EntityType<?>, EntityType<T>> registerPlants(String name, EntityType.EntityFactory<T> entityFactory) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(entityFactory , MobCategory.MISC).clientTrackingRange(10).sized(1,1f).build("rhyme:entity."+name));
    }
    // tip 弹幕
//    public static final Supplier<EntityType<LineProj>> LINE_PROJ = ENTITIES.register("line_proj", () -> EntityType.Builder.<LineProj>of(LineProj::new, MobCategory.MISC).build("rhyme:line_proj"));

    public static final DeferredHolder<EntityType<?>, EntityType<LineProj>> PEA_PROJ = registerProj("pea_proj",(e,l)->
            new LineProj(e,l,10,20));
    public static final DeferredHolder<EntityType<?>, EntityType<LineProj>> ICE_PEA_PROJ = registerProj("ice_pea_proj",(e,l)->
            new LineProj(e,l,10,20,new MobEffectInstance(ModEffects.FROZEN_EFFECT,20)));



    public static <T extends LineProj> DeferredHolder<EntityType<?>, EntityType<T>> registerProj(String name, EntityType.EntityFactory<T> entityFactory,float w,float h) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(entityFactory , MobCategory.MISC).clientTrackingRange(10).sized(w,h).build("rhyme:entity.proj."+name));
    }
    public static <T extends LineProj> DeferredHolder<EntityType<?>, EntityType<T>> registerProj(String name, EntityType.EntityFactory<T> entityFactory) {
        return registerProj(name,entityFactory,1,1);
    }
    // tip 阳光
    public static final Supplier<EntityType<SunItemEntity>> SUN_ITEM_ENTITY = ENTITIES.register("sun", () -> EntityType.Builder.<SunItemEntity>of(SunItemEntity::new, MobCategory.MISC).clientTrackingRange(16).updateInterval(20).build("rhyme:entity.sun"));








    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {

        AttributeSupplier.Builder genericPlant = Monster.createMobAttributes()
                .add(Attributes.FOLLOW_RANGE, 16)
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.MOVEMENT_SPEED, 0f)
                .add(Attributes.ATTACK_DAMAGE, 5)
                .add(Attributes.KNOCKBACK_RESISTANCE, 20)

                .add(Attributes.ATTACK_KNOCKBACK, 0.1);


        event.put(ModEntities.SUN_FLOWER.get(), genericPlant.build());
        event.put(ModEntities.PEA.get(), genericPlant.build());
        event.put(ModEntities.ICE_PEA.get(), genericPlant.build());

        event.put(ModEntities.DOUBLE_PEA.get(), genericPlant.build());



    }




}
