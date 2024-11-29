package rhymestudio.rhyme.datagen.dataPack;

import net.minecraft.core.RegistrySetBuilder;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import rhymestudio.rhyme.datagen.biome.ModBiomeModifier;

public class RegisterDataPack {
    public static final RegistrySetBuilder DATA_BUILDER = new RegistrySetBuilder()

            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifier::createBiomeModifier)

            ;


}
