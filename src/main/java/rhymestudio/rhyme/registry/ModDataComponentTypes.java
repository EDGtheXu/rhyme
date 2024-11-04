package rhymestudio.rhyme.registry;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;
import rhymestudio.rhyme.dataComponent.ModRarity;

import java.util.function.Supplier;

import static rhymestudio.rhyme.Rhyme.MODID;

public final class ModDataComponentTypes {
    public static final DeferredRegister<DataComponentType<?>> TYPES = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, MODID);

    public static final Supplier<DataComponentType<ModRarity>> MOD_RARITY = TYPES.register("mod_rarity", () -> DataComponentType.<ModRarity>builder().persistent(ModRarity.CODEC).networkSynchronized(ModRarity.STREAM_CODEC).cacheEncoding().build());
}
