package rhymestudio.rhyme.registry;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;

import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.entity.proj.LineProj;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.SunItemEntity;
import rhymestudio.rhyme.entity.plants.Pea;
import rhymestudio.rhyme.entity.plants.SunFlower;

import java.util.function.Supplier;

@EventBusSubscriber(modid = Rhyme.MODID, bus = EventBusSubscriber.Bus.MOD)

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Rhyme.MODID);

// 植物
    public static final Supplier<EntityType<SunFlower>> SUN_FLOWER = registerPlants("sun_flower", SunFlower::new);
    public static final DeferredHolder<EntityType<?>, EntityType<Pea>> PEA_SHOOTER = registerPlants("peashooter_new", Pea::new);
    public static final DeferredHolder<EntityType<?>, EntityType<Pea>> DOUBLE_PEA_SHOOTER = registerPlants("peashooter_new", (type,level)->new Pea(new Vec3(0, 0, 0), level));





    // 弹幕
    public static final Supplier<EntityType<LineProj>> PEA_PROJ = registerProj("pea_proj", LineProj::new);



    // 阳光
    public static final Supplier<EntityType<SunItemEntity>> SUN_ITEM_ENTITY = ENTITIES.register("sun", () -> EntityType.Builder.<SunItemEntity>of(SunItemEntity::new, MobCategory.MISC).clientTrackingRange(16).updateInterval(20).build("rhyme:sun"));


    public static <T extends AbstractPlant> DeferredHolder<EntityType<?>, EntityType<T>> registerPlants(String name, EntityType.EntityFactory<T> entityFactory) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(entityFactory , MobCategory.MISC).clientTrackingRange(10).sized(1,1f).build("rhyme:entity."+name));
    }

    public static <T extends LineProj> DeferredHolder<EntityType<?>, EntityType<T>> registerProj(String name, EntityType.EntityFactory<T> entityFactory) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(entityFactory , MobCategory.MISC).clientTrackingRange(10).sized(1,1f).build("rhyme:entity.proj."+name));
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {

        AttributeSupplier.Builder genericPlant = Monster.createMobAttributes()
                .add(Attributes.FOLLOW_RANGE, 16)
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.MOVEMENT_SPEED, 0f)
                .add(Attributes.ATTACK_DAMAGE, 5)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1);


        event.put(ModEntities.SUN_FLOWER.get(), genericPlant.build());
        event.put(ModEntities.PEA_SHOOTER.get(), genericPlant.build());

    }


}
