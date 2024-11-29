package rhymestudio.rhyme.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import rhymestudio.rhyme.Rhyme;

public final class ModDamageTypes {
    public static final ResourceKey<DamageType> PLANT_PROJ = register("proj");
    public static final ResourceKey<DamageType> PLANT_EXPLORE = register("explore");

    private static ResourceKey<DamageType> register(String id) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Rhyme.space(id));
    }

    public static DamageSource of(Level level, ResourceKey<DamageType> key) {
        return of(level, key, null, null);
    }

    public static DamageSource of(Level level, ResourceKey<DamageType> key, Entity causing) {
        return of(level, key, causing, causing);
    }

    public static DamageSource of(Level level, ResourceKey<DamageType> key, Entity causing, Entity direct) {
        return new DamageSource(level.registryAccess().registry(Registries.DAMAGE_TYPE).orElseThrow().getHolderOrThrow(key), causing, direct);
    }

    public static void createDamageTypes(BootstrapContext<DamageType> context) {
        context.register(PLANT_PROJ, new DamageType("plant_proj", 0.1F));
        context.register(PLANT_EXPLORE, new DamageType("plant_explore", 0.2F));

    }
}
