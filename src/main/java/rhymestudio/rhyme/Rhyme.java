package rhymestudio.rhyme;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import rhymestudio.rhyme.registry.ModBlocks;
import rhymestudio.rhyme.registry.ModEntities;
import rhymestudio.rhyme.registry.ModItems;
import rhymestudio.rhyme.registry.ModTabs;

@SuppressWarnings("deprecation")
@Mod(Rhyme.MODID)
public class Rhyme {
    public static final String MODID = "rhyme";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static ResourceLocation space(String path){return new ResourceLocation(MODID, path);}

    public Rhyme() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModTabs.TABS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);

    }

}
