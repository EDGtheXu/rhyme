package rhymestudio.rhyme.core.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import rhymestudio.rhyme.core.registry.ModDataComponentTypes;

public class CustomRarityItem extends Item {
    public CustomRarityItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull MutableComponent getName(@NotNull ItemStack pStack) {
        return Component.translatable(getDescriptionId()).withStyle(style -> style.withColor(pStack.get(ModDataComponentTypes.MOD_RARITY).getColor()));
    }
}
