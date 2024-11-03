package rhymestudio.rhyme.registry;

import net.minecraft.core.registries.BuiltInRegistries;
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
import rhymestudio.rhyme.client.animation.PeaAnimation;
import rhymestudio.rhyme.entity.plants.DoublePea;
import rhymestudio.rhyme.entity.plants.Pea;
import rhymestudio.rhyme.entity.plants.SunFlower;
import rhymestudio.rhyme.entity.proj.LineProj;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.SunItemEntity;


import java.util.function.Supplier;

@EventBusSubscriber(modid = Rhyme.MODID, bus = EventBusSubscriber.Bus.MOD)

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Rhyme.MODID);

// 植物
    public static final DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> SUN_FLOWER = registerPlants("sunflower", (type, level)->new SunFlower(level,new SunFlower
        .Builder()
        .setAnimSpeed(5)//动画倍速
        .setAttackInternalTick(100)//产阳光间隔/tick

        .setAttackTriggerTick(10)//攻击动画触发时间
        .setAttackAnimTick(20)//攻击动画持续时间

    ));

    public static final DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> PEA = registerPlants("pea_shooter",(type, level)->new Pea(type,level, PeaAnimation.idle_normal,PeaAnimation.shoot,new Pea
            .Builder()
            .setAnimSpeed(2)
            .setAttackDamage(5)//子弹伤害
            .setAttackInternalTick(20)//idle

            .setAttackTriggerTick(15)
            .setAttackAnimTick((int) (1.5 * 20))//shoot

    ));

    public static final DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> DOUBLE_PEA = registerPlants("double_pea_shooter",(type,level)->new Pea(type,level, DoublePeaAnimation.idle_normal,DoublePeaAnimation.shoot,new Pea
            .Builder()
            .setAnimSpeed(2)
            .setAttackDamage(5)//子弹伤害
            .setAttackInternalTick(10)//idle

            .setAttackTriggerTick(10)
            .setAttackAnimTick(20)//shoot

    ));




    // 弹幕
    public static final Supplier<EntityType<LineProj>> PEA_PROJ = registerProj("pea_proj", LineProj::new,0.3f,0.3f);



    // 阳光
    public static final Supplier<EntityType<SunItemEntity>> SUN_ITEM_ENTITY = ENTITIES.register("sun", () -> EntityType.Builder.<SunItemEntity>of(SunItemEntity::new, MobCategory.MISC).clientTrackingRange(16).updateInterval(20).build("rhyme:entity.sun"));



    public static <T extends AbstractPlant> DeferredHolder<EntityType<?>, EntityType<T>> registerPlants(String name, EntityType.EntityFactory<T> entityFactory) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(entityFactory , MobCategory.MISC).clientTrackingRange(10).sized(1,1f).build("rhyme:entity."+name));
    }
    public static <T extends LineProj> DeferredHolder<EntityType<?>, EntityType<T>> registerProj(String name, EntityType.EntityFactory<T> entityFactory,float w,float h) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(entityFactory , MobCategory.MISC).clientTrackingRange(10).sized(w,h).build("rhyme:entity.proj."+name));
    }
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
        event.put(ModEntities.DOUBLE_PEA.get(), genericPlant.build());

    }




}
