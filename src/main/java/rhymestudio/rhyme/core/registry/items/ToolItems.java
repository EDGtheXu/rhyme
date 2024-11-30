package rhymestudio.rhyme.core.registry.items;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.core.dataSaver.dataComponent.ModRarity;
import rhymestudio.rhyme.core.item.CustomRarityItem;
import rhymestudio.rhyme.core.registry.ModDataComponentTypes;

public class ToolItems {
    public static final DeferredRegister.Items TOOLS = DeferredRegister.createItems(Rhyme.MODID);

    //public static final DeferredHolder<Item, Item> CONE = register("cone", "路障");


    public static DeferredItem<Item> register(String en, String zh, ModRarity rarity) {
        DeferredItem<Item> item =  TOOLS .register("tool/"+en, () -> new CustomRarityItem(new Item.Properties().component(ModDataComponentTypes.MOD_RARITY,rarity)));
        Rhyme.chineseProviders.add((c)->c.add(item.get(),zh));
        return item;
    }
    public static DeferredItem<Item> register(String en, String zh) {
        return register(en, zh, ModRarity.COMMON);
    }

}
