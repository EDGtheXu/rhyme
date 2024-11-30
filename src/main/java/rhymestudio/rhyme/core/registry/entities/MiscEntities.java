package rhymestudio.rhyme.core.registry.entities;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import rhymestudio.rhyme.core.entity.misc.HelmetEntity;

import static rhymestudio.rhyme.Rhyme.MODID;

public class MiscEntities {
    public static final DeferredRegister<EntityType<?>> Entities = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<HelmetEntity>> HELMET_ENTITY = registerMonster("helmet_entity", HelmetEntity::new, 1,1);

    public static <T extends Entity>DeferredHolder<EntityType<?>, EntityType<T>> registerMonster(String name, EntityType.EntityFactory<T> entityFactory, float width, float height) {
        return Entities.register(name, () -> EntityType.Builder.of(entityFactory, MobCategory.MONSTER).clientTrackingRange(10).updateInterval(1).setTrackingRange(50).setShouldReceiveVelocityUpdates(true).sized(width,height).build(Key(name)));
    }

    public static String Key(String key){
        return MODID + ":" + key;
    }
}
