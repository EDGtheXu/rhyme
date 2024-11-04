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


    /**
     *     public static final CardQuality COPPER  = new CardQuality(0);//铜
     *     public static final CardQuality SILVER  = new CardQuality(1);//银
     *     public static final CardQuality GOLD    = new CardQuality(2);//金
     *     public static final CardQuality EMERALD = new CardQuality(3);//翡翠
     *     public static final CardQuality DIAMOND = new CardQuality(4);//钻石

     */

    private static DeferredItem<Item> register(String name){
        return QUALITY_ITEMS.register(name, () -> new Item(new Item.Properties()));
    }
}
