package rhymestudio.rhyme.registry;

import net.neoforged.bus.api.IEventBus;
import rhymestudio.rhyme.registry.items.ArmorItems;
import rhymestudio.rhyme.registry.items.IconItems;
import rhymestudio.rhyme.registry.items.MaterialItems;
import rhymestudio.rhyme.registry.items.PlantItems;

public class ModItems {



    public static void registerItems(IEventBus modEventBus) {
        PlantItems.PLANTS.register(modEventBus);
        MaterialItems.MATERIALS.register(modEventBus);
        IconItems.QUALITY_ITEMS.register(modEventBus);
        ArmorItems.ARMORS.register(modEventBus);
    }
}
