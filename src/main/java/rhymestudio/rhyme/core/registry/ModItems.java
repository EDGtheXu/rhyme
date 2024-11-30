package rhymestudio.rhyme.core.registry;

import net.neoforged.bus.api.IEventBus;
import rhymestudio.rhyme.core.registry.items.ArmorItems;
import rhymestudio.rhyme.core.registry.items.IconItems;
import rhymestudio.rhyme.core.registry.items.MaterialItems;
import rhymestudio.rhyme.core.registry.items.PlantItems;

public class ModItems {



    public static void registerItems(IEventBus modEventBus) {
        PlantItems.PLANTS.register(modEventBus);
        MaterialItems.MATERIALS.register(modEventBus);
        IconItems.QUALITY_ITEMS.register(modEventBus);
        ArmorItems.ARMORS.register(modEventBus);
    }
}
