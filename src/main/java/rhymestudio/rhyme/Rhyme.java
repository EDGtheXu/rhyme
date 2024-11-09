package rhymestudio.rhyme;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.apache.commons.lang3.function.TriConsumer;
import org.slf4j.Logger;
import rhymestudio.rhyme.datagen.ModChineseProvider;
import rhymestudio.rhyme.registry.*;
import rhymestudio.rhyme.registry.items.IconItems;
import rhymestudio.rhyme.registry.items.MaterialItems;
import rhymestudio.rhyme.registry.items.PlantItems;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Mod(Rhyme.MODID)
public class Rhyme {
    public static final String MODID = "rhyme";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static ResourceLocation space(String path){return ResourceLocation.fromNamespaceAndPath(MODID, path);}

    public static List<Consumer<ModChineseProvider>> chineseProviders = new ArrayList<>();

    public Rhyme(IEventBus modEventBus, ModContainer modContainer) {
        PlantItems.PLANTS.register(modEventBus);
        MaterialItems.MATERIALS.register(modEventBus);
        IconItems.QUALITY_ITEMS.register(modEventBus);


        ModBlocks.BLOCKS.register(modEventBus);
        ModTabs.TABS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModDataComponentTypes.TYPES.register(modEventBus);
        ModEffects.EFFECTS.register(modEventBus);
    }

}
