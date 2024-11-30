package rhymestudio.rhyme.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

import net.neoforged.neoforge.registries.DeferredRegister;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.core.registry.items.ArmorItems;
import rhymestudio.rhyme.core.registry.items.MaterialItems;
import rhymestudio.rhyme.core.registry.items.PlantItems;

import java.util.function.Supplier;


public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Rhyme.MODID);

    public static final Supplier<CreativeModeTab> CARD = TABS.register("cards",
            () -> CreativeModeTab.builder()
                    .icon(() -> PlantItems.SUN_FLOWER.get().getDefaultInstance())
                    .title(Component.translatable("creativetab.rhyme.cards"))
                    .displayItems((parameters, output) -> {
                        PlantItems.PLANTS.getEntries().forEach(item -> output.accept(item.get()));
                    })
                    .build()
    );

    public static final Supplier<CreativeModeTab> MATERIALS = TABS.register("materials",
            () -> CreativeModeTab.builder()
                    .icon(() -> MaterialItems.SOLID_SUN.get().getDefaultInstance())
                    .title(Component.translatable("creativetab.rhyme.materials"))
                    .displayItems((parameters, output) -> {
                        MaterialItems.MATERIALS.getEntries().forEach(item -> output.accept(item.get()));
                    })
                    .build()
    );

    public static final Supplier<CreativeModeTab> BLOCKS = TABS.register("blocks",
            () -> CreativeModeTab.builder()
                    .icon(() -> ModBlocks.BLOCK_ITEMS.getEntries().stream().filter(i->i.is(Rhyme.space("sun_creator_block"))).findFirst().get().get().getDefaultInstance())
                    .title(Component.translatable("creativetab.rhyme.blocks"))
                    .displayItems((parameters, output) -> {
                        ModBlocks.BLOCKS.getEntries().forEach(item -> output.accept(item.get()));
                    })
                    .build()
    );

    public static final Supplier<CreativeModeTab> ARMOR = TABS.register("armors",
            () -> CreativeModeTab.builder()
                    .icon(() -> ArmorItems.IRON_BUCKET_HELMET.asItem().getDefaultInstance())
                    .title(Component.translatable("creativetab.rhyme.armors"))
                    .displayItems((parameters, output) -> {
                        ArmorItems.ARMORS.getEntries().forEach(item -> output.accept(item.get()));
                    })
                    .build()
    );


}
