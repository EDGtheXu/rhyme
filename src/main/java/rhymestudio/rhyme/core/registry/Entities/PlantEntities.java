package rhymestudio.rhyme.core.registry.Entities;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.client.animation.plantAnimations.*;
import rhymestudio.rhyme.core.entity.BaseProj;
import rhymestudio.rhyme.core.entity.plants.*;
import rhymestudio.rhyme.core.entity.proj.LineProj;
import rhymestudio.rhyme.core.entity.AbstractPlant;
import rhymestudio.rhyme.core.entity.SunItemEntity;
import rhymestudio.rhyme.core.entity.proj.ThrowableProj;
import rhymestudio.rhyme.core.registry.ModEffects;

import java.util.function.Supplier;

import static rhymestudio.rhyme.core.entity.plants.prefabs.PresetAttacks.*;
import static rhymestudio.rhyme.core.entity.plants.prefabs.PresetBuilders.*;



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
    public static final DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> WALL_NUT = registerPlants("wall_nut",(type, level)->
            new WallNut(type,level, WallNutAnimation.idle1,WallNutAnimation.idle2,WallNutAnimation.idle3,
                    DEFENSE_PLANT.apply(150)));

    //      tip 土豆雷类
    public static final DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> POTATO_MINE = registerPlants("potato_mine",(type,level)->
            new PotatoMine(type,level, PotatoMineAnimation.idle, PotatoMineAnimation.up, PotatoMineAnimation.idle_on, PotatoMineAnimation.bomb,
                    15 * 20,2,EXPLORE_PLANT.apply(50)),1f,0.5f);

    //      tip 蘑菇类
    public static final DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> PUFF_SHROOM = registerPlants("puff_shroom",(type, level)->
            new PuffShroom(type,level, PuffShroomAnimation.sleeping, PuffShroomAnimation.idle, PuffShroomAnimation.attack ,
                    SPORE_SHOOT, NORMAL_PEA_PLANT.get()),0.75f,0.75f);

    //      tip 投手类
    public static final DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> CABBAGE_PULT = registerPlants("cabbage_pult",(type,level)->
            new Pea(type,level, CabbageAnimation.idle, CabbageAnimation.attack,
                    THROWN_PEA_SHOOT, NORMAL_PEA_PLANT.get().setAttackDamage(10)));



    public static <T extends AbstractPlant> DeferredHolder<EntityType<?>, EntityType<T>> registerPlants(String name, EntityType.EntityFactory<T> entityFactory) {
        return registerPlants(name,entityFactory,0.9F,1);
    }
    public static <T extends AbstractPlant> DeferredHolder<EntityType<?>, EntityType<T>> registerPlants(String name, EntityType.EntityFactory<T> entityFactory, float w,float h) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(entityFactory , MobCategory.MISC).clientTrackingRange(10).sized(w,h).build("rhyme:entity."+name));
    }



    // tip 弹幕
         // tip 直线
    public static final DeferredHolder<EntityType<?>, EntityType<LineProj>> PEA_PROJ = registerProj("pea_proj",(e,l)->
                new LineProj(e,l,BaseProj.TextureLib.PEA),0.25F,0.25F);
    public static final DeferredHolder<EntityType<?>, EntityType<LineProj>> ICE_PEA_PROJ = registerProj("ice_pea_proj",(e,l)->
                new LineProj(e,l,BaseProj.TextureLib.ICE_PEA, new MobEffectInstance(ModEffects.FROZEN_EFFECT,20 * 5)),0.25F,0.25F);

        // tip 投掷
    public static final DeferredHolder<EntityType<?>, EntityType<ThrowableProj>> CABBAGE_PROJ = registerProj("cabbage_proj",(e,l)->
                new ThrowableProj(e,l,ThrowableProj.TextureLib.CABBAGE_TEXTURE),0.5F,0.5F);




    public static <T extends BaseProj> DeferredHolder<EntityType<?>, EntityType<T>> registerProj(String name, EntityType.EntityFactory<T> entityFactory,float w,float h) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(entityFactory , MobCategory.MISC).clientTrackingRange(10).sized(w,h).build("rhyme:entity.proj."+name));
    }
    public static <T extends BaseProj> DeferredHolder<EntityType<?>, EntityType<T>> registerProj(String name, EntityType.EntityFactory<T> entityFactory) {
        return registerProj(name,entityFactory,1,1);
    }
    // tip 阳光
    public static final Supplier<EntityType<SunItemEntity>> SUN_ITEM_ENTITY = ENTITIES.register("sun", () -> EntityType.Builder.<SunItemEntity>of(SunItemEntity::new, MobCategory.MISC).clientTrackingRange(16).updateInterval(20).build("rhyme:entity.sun"));



}
