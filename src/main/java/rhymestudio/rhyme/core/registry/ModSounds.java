package rhymestudio.rhyme.core.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import rhymestudio.rhyme.Rhyme;

import static rhymestudio.rhyme.Rhyme.MODID;


public final class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MODID);

    public static final DeferredHolder<SoundEvent,SoundEvent> PROJ_HIT = register("proj_hit");
    public static final DeferredHolder<SoundEvent,SoundEvent> SNOW_PROJ_HIT = register("snow_proj_hit");
    public static final DeferredHolder<SoundEvent,SoundEvent> GRASS_WALK = register("grass_walk");

    private static DeferredHolder<SoundEvent,SoundEvent> register(String id) {
        return SOUNDS.register(id, () -> SoundEvent.createVariableRangeEvent(Rhyme.space(id)));
    }

}
