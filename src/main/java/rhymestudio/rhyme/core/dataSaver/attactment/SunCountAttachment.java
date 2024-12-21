package rhymestudio.rhyme.core.dataSaver.attactment;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.UnknownNullability;
import rhymestudio.rhyme.network.s2c.SunCountPacketS2C;

public class SunCountAttachment implements INBTSerializable<CompoundTag> {
    public int sunCount = 0;
    public int coins = 0;
    public int x,y,z;


    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("sunCount", sunCount);
        tag.putInt("coins", coins);
        tag.putInt("x", x);
        tag.putInt("y", y);
        tag.putInt("z", z);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
        sunCount = compoundTag.getInt("sunCount");
        coins = compoundTag.getInt("coins");
        x = compoundTag.getInt("x");
        y = compoundTag.getInt("y");
        z = compoundTag.getInt("z");
    }

    public boolean consumeSun(int amount) {
        if (sunCount < amount) {
            return false;
        }
        sunCount -= amount;
        return true;
    }
}