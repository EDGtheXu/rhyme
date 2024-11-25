package rhymestudio.rhyme.registry;

import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.entity.AbstractMonster;
import rhymestudio.rhyme.registry.Entities.PlantEntities;
import rhymestudio.rhyme.registry.Entities.Zombies;

import static rhymestudio.rhyme.registry.Entities.PlantEntities.*;

@EventBusSubscriber(modid = Rhyme.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEntities {



    //tip 注册属性
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {

        AttributeSupplier.Builder genericPlant = Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH)
                .add(Attributes.ATTACK_DAMAGE)
                .add(Attributes.FOLLOW_RANGE, 16)
                .add(Attributes.MOVEMENT_SPEED, 0f)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1)

                ;


        //植物
        event.put(SUN_FLOWER.get(), genericPlant.build());
        event.put(PEA.get(), genericPlant.build());
        event.put(ICE_PEA.get(), genericPlant.build());
        event.put(DOUBLE_PEA.get(), genericPlant.build());
        event.put(PUFF_SHROOM.get(), genericPlant.build());
        event.put(WALL_NUT.get(), genericPlant.build());
        event.put(CABBAGE_PULT.get(), genericPlant.build());


        event.put(PlantEntities.POTATO_MINE.get(), genericPlant.build());

        //僵尸
        event.put(Zombies.NORMAL_ZOMBIE.get(), AbstractMonster.createAttributes().build());

    }

    //tip 生成位置
    @SubscribeEvent
    public static void spawnPlacementRegister(RegisterSpawnPlacementsEvent event) {


    }

    public static void registerEntities(IEventBus modEventBus) {
        PlantEntities.ENTITIES.register(modEventBus);
        Zombies.ZOMBIES.register(modEventBus);
    }
}
