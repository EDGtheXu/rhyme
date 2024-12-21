package rhymestudio.rhyme;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import rhymestudio.rhyme.datagen.lang.ModChineseProvider;
import rhymestudio.rhyme.datagen.biome.ModBiomes;
import rhymestudio.rhyme.datagen.recipe.ModRecipes;
import rhymestudio.rhyme.core.registry.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Mod(Rhyme.MODID)
public class Rhyme {
    public static final String MODID = "rhyme";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static ResourceLocation space(String path){return ResourceLocation.fromNamespaceAndPath(MODID, path);}

    public static List<Consumer<ModChineseProvider>> chineseProviders = new ArrayList<>();

    public Rhyme(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.registerItems(modEventBus);
        ModEntities.registerEntities(modEventBus);
        ModArmorMaterials.ARMOR_MATERIALS.register(modEventBus);

        ModBlocks.BLOCK_ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.BLOCK_ENTITIES.register(modEventBus);

        ModTabs.TABS.register(modEventBus);
        ModDataComponentTypes.TYPES.register(modEventBus);
        ModEffects.EFFECTS.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModMenus.TYPES.register(modEventBus);
        ModBiomes.register(modEventBus);
        ModAttachments.TYPES.register(modEventBus);
        ModParticles.PARTICLES.register(modEventBus);
        ModSounds.SOUNDS.register(modEventBus);
    }

}
