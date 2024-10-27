package rhymestudio.rhyme;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import rhymestudio.rhyme.registry.ModBlocks;
import rhymestudio.rhyme.registry.ModEntities;
import rhymestudio.rhyme.registry.ModItems;
import rhymestudio.rhyme.registry.ModTabs;

@SuppressWarnings("removal")
@Mod(Rhyme.MODID)
public class Rhyme {
    public static final String MODID = "rhyme";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static ResourceLocation space(String path){return ResourceLocation.fromNamespaceAndPath(MODID, path);}

    public Rhyme(IEventBus modEventBus, ModContainer modContainer) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModTabs.TABS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
    }

}
