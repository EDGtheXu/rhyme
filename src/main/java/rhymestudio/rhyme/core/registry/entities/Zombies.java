package rhymestudio.rhyme.core.registry.entities;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import rhymestudio.rhyme.core.entity.AbstractMonster;
import rhymestudio.rhyme.core.entity.zombies.NormalZombie;
import rhymestudio.rhyme.core.entity.zombies.prefab.LandMonsterPrefab;

import java.util.function.Supplier;

import static rhymestudio.rhyme.Rhyme.MODID;

public class Zombies {
    public static final DeferredRegister<EntityType<?>> ZOMBIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<NormalZombie>> NORMAL_ZOMBIE = registerMonster("normal_zombie", (e,l)->new NormalZombie(e,l,LandMonsterPrefab.NORMAL_ZOMBIE_PREFAB.get()) ,0.6F,1.95F);
    public static final DeferredHolder<EntityType<?>, EntityType<NormalZombie>> CONE_ZOMBIE = registerMonster("cone_zombie", (e,l)->new NormalZombie(e,l,LandMonsterPrefab.CONE_ZOMBIE_PREFAB.get()),0.6F,1.95F);
    public static final DeferredHolder<EntityType<?>, EntityType<NormalZombie>> IRON_BUCKET_ZOMBIE = registerMonster("iron_bucket_zombie", (e,l)->new NormalZombie(e,l,LandMonsterPrefab.IRON_BUCKET_ZOMBIE_PREFAB.get()),0.6F,1.95F);



    public static <T extends AbstractMonster>DeferredHolder<EntityType<?>, EntityType<T>> registerMonster(String name, EntityType.EntityFactory<T> entityFactory, float width, float height) {
        return ZOMBIES.register(name, () -> EntityType.Builder.of(entityFactory, MobCategory.MONSTER).clientTrackingRange(10).setTrackingRange(50).setShouldReceiveVelocityUpdates(true).sized(width,height).build(Key(name)));
    }
    // 用于调整包围盒
    public static DeferredHolder<EntityType<?>, EntityType<AbstractMonster>> registerSimpleMonster(String name, Supplier<AbstractMonster.Builder> builder, float width, float height) {
        return ZOMBIES.register(name, () -> EntityType.Builder.<AbstractMonster>of((type,level)->new AbstractMonster(type,level,builder.get()), MobCategory.MONSTER).clientTrackingRange(10).setTrackingRange(50).setShouldReceiveVelocityUpdates(true).sized(width,height).build(Key(name)));
    }
    public static DeferredHolder<EntityType<?>, EntityType<AbstractMonster>> registerSimpleMonster(String name, Supplier<AbstractMonster.Builder> builder) {
        return registerSimpleMonster(name, builder, 1, 1);
    }
    public static String Key(String key){
        return MODID + ":" + key;
    }
}
