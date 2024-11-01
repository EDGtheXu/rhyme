package rhymestudio.rhyme.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.plants.PeaNew;
import rhymestudio.rhyme.item.AbstructCardItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Rhyme.MODID);

    public static final DeferredItem<Item> SUN_ITEM = ITEMS.register("sun_item", ()->new Item(new Item.Properties()));



    //注册植物
//    public static final DeferredItem<AbstructCardItem> SUN_FLOWER = registerPlant("sun_flower", SunFlower.class, 2);
    public static final DeferredItem<AbstructCardItem> PEA = registerPlant("pea_shooter", PeaNew.class,4);







    public static DeferredItem<AbstructCardItem> registerPlant(String name, Class<? extends AbstractPlant> clazz, int consumeSun) {
        DeferredItem<AbstructCardItem> item =  ITEMS.register("plant_card/"+name, () -> new AbstructCardItem(new Item.Properties(),clazz,consumeSun));
        Optional.ofNullable(cardItems).ifPresent(list -> list.add(item));
        return item;
    }


    public static List<DeferredItem<AbstructCardItem>> cardItems = new ArrayList<>();
}
