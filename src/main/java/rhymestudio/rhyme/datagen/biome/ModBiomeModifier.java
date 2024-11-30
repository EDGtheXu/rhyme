package rhymestudio.rhyme.datagen.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.core.registry.entities.Zombies;

public class ModBiomeModifier {
    private static ResourceKey<BiomeModifier> createModifierKey(String name) {return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, Rhyme.space(name));}

    public static final ResourceKey<BiomeModifier> ZOMBIE_SPAWN = createModifierKey("spawn/zombie_spawns");


    public static void createBiomeModifier(BootstrapContext<BiomeModifier> context) {
        HolderGetter<Biome> biomeLookup = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeatureLookup = context.lookup(Registries.PLACED_FEATURE);
        context.register(ZOMBIE_SPAWN, ExtendedAddSpawnsBiomeModifier.singleSpawn(
                biomeLookup.getOrThrow(BiomeTags.IS_OVERWORLD),
                biomeLookup.getOrThrow(BiomeTags.IS_END),
                new ExtendedAddSpawnsBiomeModifier.ExtendedSpawnData(Zombies.NORMAL_ZOMBIE.get(),
                        80, 3, 5,
                        Zombies.NORMAL_ZOMBIE.get().getCategory())
                )
        );




    }
}
