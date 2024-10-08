package rhymestudio.rhyme.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import rhymestudio.rhyme.Rhyme;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Rhyme.MODID);

    public static final RegistryObject<CreativeModeTab> CARD = TABS.register("cards",
            () -> CreativeModeTab.builder()
                    .icon(() -> ModItems.SUN_FLOWER.get().getDefaultInstance())
                    .title(Component.translatable("creativetab.rhyme.cards"))
                    .displayItems((parameters, output) -> {
                        ModItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()));

                    })
                    .build()
    );



}
