package rhymestudio.rhyme.core.dataSaver.attactment;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

public class BuffAttachment implements INBTSerializable<CompoundTag> {
    public int frozenTime = 0;
    public int frozenDuration = 0;

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("frozenTime", frozenTime);
        tag.putInt("frozenDuration", frozenDuration);

        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
        frozenTime = compoundTag.getInt("frozenTime");
        frozenDuration = compoundTag.getInt("frozenDuration");

    }
}