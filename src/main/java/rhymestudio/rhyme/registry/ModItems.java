package rhymestudio.rhyme.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.plants.Pea;
import rhymestudio.rhyme.entity.plants.SunFlower;
import rhymestudio.rhyme.item.AbstructCardItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Rhyme.MODID);

    public static final RegistryObject<Item> SUN_ITEM = ITEMS.register("sun", ()->new Item(new Item.Properties()));



    //注册植物
    public static final RegistryObject<AbstructCardItem> SUN_FLOWER = registerPlant("sun_flower", SunFlower.class, 2);
    public static final RegistryObject<AbstructCardItem> PEA = registerPlant("pea_shooter", Pea.class,4);







    public static RegistryObject<AbstructCardItem> registerPlant(String name, Class<? extends AbstractPlant> clazz, int consumeSun) {
        var item =  ITEMS.register("plant_card/"+name, () -> new AbstructCardItem(new Item.Properties(),clazz,consumeSun));
        Optional.ofNullable(cardItems).ifPresent(list -> list.add(item));
        return item;
    }


    public static List<RegistryObject<AbstructCardItem>> cardItems = new ArrayList<>();
}
