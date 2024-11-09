package rhymestudio.rhyme.registry.items;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import rhymestudio.rhyme.Rhyme;

public class IconItems {
    public static final DeferredRegister.Items QUALITY_ITEMS = DeferredRegister.createItems(Rhyme.MODID);
    public static final DeferredItem<Item> COPPER   = register("quality/card_quality_0");
    public static final DeferredItem<Item> SILVER   = register("quality/card_quality_1");
    public static final DeferredItem<Item> GOLD     = register("quality/card_quality_2");
    public static final DeferredItem<Item> EMERALD  = register("quality/card_quality_3");
    public static final DeferredItem<Item> DIAMOND  = register("quality/card_quality_4");



    private static DeferredItem<Item> register(String name){
        return QUALITY_ITEMS.register(name, () -> new Item(new Item.Properties()));
    }
}
