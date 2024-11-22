package rhymestudio.rhyme.registry.items;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.dataComponent.CardQualityComponent;
import rhymestudio.rhyme.dataComponent.ModRarity;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.plants.WallNut;
import rhymestudio.rhyme.entity.plants.PotatoMine;
import rhymestudio.rhyme.item.AbstractCardItem;
import rhymestudio.rhyme.registry.ModDataComponentTypes;
import rhymestudio.rhyme.registry.ModEntities;

import java.util.ArrayList;
import java.util.List;

public class PlantItems {
    public static final DeferredRegister.Items PLANTS = DeferredRegister.createItems(Rhyme.MODID);

    // tip 植物基类
    public static final DeferredHolder<Item, AbstractCardItem<AbstractPlant>> SUN_FLOWER = registerPlant("sunflower", "向日葵",ModEntities.SUN_FLOWER, 2);
    public static final DeferredHolder<Item, AbstractCardItem<AbstractPlant>> PEA_ITEM = registerPlant("pea_shooter", "豌豆射手",ModEntities.PEA,4);
    public static final DeferredHolder<Item, AbstractCardItem<AbstractPlant>> ICE_PEA_ITEM = registerPlant("ice_pea_shooter", "寒冰射手",ModEntities.ICE_PEA,7,10,ModRarity.BLUE);
    public static final DeferredHolder<Item, AbstractCardItem<AbstractPlant>> REPEATER_ITEM = registerPlant("repeater","双重射手", ModEntities.DOUBLE_PEA,8,15,ModRarity.GREEN);

    // tip 蘑菇类
    public static final DeferredHolder<Item, AbstractCardItem<AbstractPlant>> PUFF_SHROOM_ITEM = registerPlant("puff_shroom", "小喷菇",ModEntities.PUFF_SHROOM,0);

    // tip 土豆雷类
    public static final DeferredHolder<Item, AbstractCardItem<PotatoMine>> POTATO_MINE_ITEM = registerPlant("potato_mine", "土豆雷",ModEntities.POTATO_MINE,1);

    // tip 坚果类
    public static final DeferredHolder<Item, AbstractCardItem<WallNut>> NUT_WALL_ITEM = registerPlant("nut_wall","坚果墙", ModEntities.WALL_NUT,2);


    /**
     * @param en id
     * @param zh 中文名
     * @param entityType 召唤植物类型
     * @param consumeSun 消耗阳光数量
     * @param duration 默认铜卡使用次数 10
     * @param rarity 默认白色
     * @return
     */
    public static <T extends  AbstractPlant> DeferredHolder<Item,AbstractCardItem<T>> registerPlant(String en, String zh,DeferredHolder<EntityType<?>, EntityType<T>> entityType, int consumeSun, int duration, ModRarity rarity) {
        DeferredItem<AbstractCardItem<T>> item =  PLANTS.register("plant_card/"+en, () -> new AbstractCardItem<>(
                new Item.Properties()
                        .component(ModDataComponentTypes.MOD_RARITY, rarity)
                        .component(ModDataComponentTypes.CARD_QUALITY, CardQualityComponent.COPPER)
                        .stacksTo(1)
                        .durability(duration)
                ,entityType,consumeSun));
//        Optional.ofNullable(cardItems).ifPresent(list -> list.add(item));
        Rhyme.chineseProviders.add((c)->c.add(item.get(),zh));
        return item;
    }

    public static <T extends  AbstractPlant> DeferredHolder<Item,AbstractCardItem<T>> registerPlant(String en, String zh,DeferredHolder<EntityType<?>, EntityType<T>> entityType, int consumeSun, int duration) {
        return registerPlant(en, zh,entityType, consumeSun, duration, ModRarity.COMMON);
    }

    public static <T extends  AbstractPlant> DeferredHolder<Item,AbstractCardItem<T>> registerPlant(String en, String zh, DeferredHolder<EntityType<?>, EntityType<T>> entityType, int consumeSun) {
        return registerPlant(en, zh,entityType, consumeSun, 10);
    }

    public static List<DeferredItem<AbstractCardItem<? extends  AbstractPlant>>> cardItems = new ArrayList<>();
}
