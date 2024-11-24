package rhymestudio.rhyme.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import rhymestudio.rhyme.registry.ModDataComponentTypes;

public class NormalArmorItem extends ArmorItem{

    public NormalArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);

    }

    @Override
    public @NotNull MutableComponent getName(@NotNull ItemStack pStack) {
        return Component.translatable(getDescriptionId()).withStyle(style -> style.withColor(pStack.get(ModDataComponentTypes.MOD_RARITY).getColor()));
    }
}
