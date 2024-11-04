package rhymestudio.rhyme.registry.items;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.dataComponent.ModRarity;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.item.AbstractCardItem;
import rhymestudio.rhyme.registry.ModDataComponentTypes;
import rhymestudio.rhyme.registry.ModEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlantItems {
    public static final DeferredRegister.Items PLANTS = DeferredRegister.createItems(Rhyme.MODID);

    //注册植物
    public static final DeferredItem<AbstractCardItem> SUN_FLOWER = registerPlant("sun_flower", ModEntities.SUN_FLOWER, 2);
    public static final DeferredItem<AbstractCardItem> PEA_ITEM = registerPlant("pea_shooter", ModEntities.PEA,4);
    public static final DeferredItem<AbstractCardItem> DOUBLE_PEA_ITEM = registerPlant("double_pea_shooter", ModEntities.DOUBLE_PEA,8,15,ModRarity.GREEN);


    /**
     * @param name 注册名
     * @param entityType 召唤植物类型
     * @param consumeSun 消耗阳光数量
     * @param duration 默认10
     * @param rarity 默认白色
     * @return
     */
    public static DeferredItem<AbstractCardItem> registerPlant(String name, DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> entityType, int consumeSun, int duration, ModRarity rarity) {
        DeferredItem<AbstractCardItem> item =  PLANTS.register("plant_card/"+name, () -> new AbstractCardItem(
                new Item.Properties()
                        .component(ModDataComponentTypes.MOD_RARITY, rarity)
                        .stacksTo(1)
                        .durability(duration)
                ,entityType,consumeSun));
        Optional.ofNullable(cardItems).ifPresent(list -> list.add(item));
        return item;
    }

    public static DeferredItem<AbstractCardItem> registerPlant(String name, DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> entityType, int consumeSun, int duration) {
        return registerPlant(name, entityType, consumeSun, duration, ModRarity.COMMON);
    }

    public static DeferredItem<AbstractCardItem> registerPlant(String name, DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> entityType, int consumeSun) {
        return registerPlant(name, entityType, consumeSun, 10);
    }

    public static List<DeferredItem<AbstractCardItem>> cardItems = new ArrayList<>();
}
