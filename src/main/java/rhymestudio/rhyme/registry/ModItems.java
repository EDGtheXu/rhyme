package rhymestudio.rhyme.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.plants.Pea;
import rhymestudio.rhyme.entity.plants.SunFlower;
import rhymestudio.rhyme.item.AbstructCardItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Rhyme.MODID);

    public static final DeferredItem<Item> SUN_ITEM = ITEMS.register("sun_item", ()->new Item(new Item.Properties()));



    //注册植物
    public static final DeferredItem<AbstructCardItem> SUN_FLOWER = registerPlant("sun_flower", ModEntities.SUN_FLOWER, 2);
    public static final DeferredItem<AbstructCardItem> PEA_ITEM = registerPlant("pea_shooter", ModEntities.PEA,4);
    public static final DeferredItem<AbstructCardItem> DOUBLE_PEA_ITEM = registerPlant("double_pea_shooter", ModEntities.DOUBLE_PEA,8);







    public static DeferredItem<AbstructCardItem> registerPlant(String name, DeferredHolder<EntityType<?>, EntityType<AbstractPlant>> entityType, int consumeSun) {
        DeferredItem<AbstructCardItem> item =  ITEMS.register("plant_card/"+name, () -> new AbstructCardItem(new Item.Properties(),entityType,consumeSun));
        Optional.ofNullable(cardItems).ifPresent(list -> list.add(item));
        return item;
    }


    public static List<DeferredItem<AbstructCardItem>> cardItems = new ArrayList<>();
}
