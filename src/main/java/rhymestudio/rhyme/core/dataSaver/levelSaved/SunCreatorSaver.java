package rhymestudio.rhyme.core.dataSaver.levelSaved;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

import java.util.ArrayList;
import java.util.List;

public class SunCreatorSaver extends SavedData {

    public static final String NAME = "sun_creator_data";

    public record Tuple(BlockPos pos, int sunCount){}
    public static final List<Tuple> recorders = new ArrayList<>();

    public static SunCreatorSaver create() {
        System.out.println("new");
        return new SunCreatorSaver();
    }

    public void putItem(Tuple tp) {
        recorders.add(tp);
        setDirty();
    }

    public Tuple removeItem(BlockPos pos) {
        if (recorders.isEmpty()) {
            return null;
        }
        setDirty();
        for(Tuple it : recorders){
            if(it.pos.equals(pos)){
                recorders.remove(it);
                return it;
            }
        }
        return null;
    }

    @Override
    public CompoundTag save(CompoundTag pCompoundTag, HolderLookup.Provider registries) {
        ListTag listTag = new ListTag();
        recorders.forEach((stack) -> {
            if(stack.sunCount > 0){
                CompoundTag compoundTag = new CompoundTag();
                compoundTag.putInt("count", stack.sunCount);
                compoundTag.putInt("x", stack.pos.getX());
                compoundTag.putInt("y", stack.pos.getY());
                compoundTag.putInt("z", stack.pos.getZ());
                listTag.add(compoundTag);
            }
        });
        pCompoundTag.put("list", listTag);
        System.out.println("save");
        return pCompoundTag;
    }

    public SunCreatorSaver load(CompoundTag nbt, HolderLookup.Provider registries) {
        SunCreatorSaver data = this.create();
        ListTag listNBT = (ListTag) nbt.get("list");
        if (listNBT != null) {
            for (Tag value : listNBT) {
                CompoundTag tag = (CompoundTag) value;
                int count = tag.getInt("count");
                int x = tag.getInt("x");
                int y = tag.getInt("y");
                int z = tag.getInt("z");
                BlockPos pos = new BlockPos(x, y, z);
                Tuple itemStack = new Tuple(pos, count);
                recorders.add(itemStack);
            }
        }
        System.out.println("load");
        return data;

    }

    public static SunCreatorSaver decode(CompoundTag tag, HolderLookup.Provider registries){
        SunCreatorSaver modLevelSaveData = SunCreatorSaver.create();
        modLevelSaveData.load(tag,registries);
        return modLevelSaveData;
    }

    public static SunCreatorSaver get(Level worldIn) {
        if (!(worldIn instanceof ServerLevel)) {
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }
        ServerLevel world = worldIn.getServer().getLevel(ServerLevel.OVERWORLD);
        DimensionDataStorage dataStorage = world.getDataStorage();

        SunCreatorSaver t = dataStorage.computeIfAbsent(new Factory<SunCreatorSaver>(SunCreatorSaver::create, SunCreatorSaver::decode), SunCreatorSaver.NAME);
        System.out.println(t);
        return t;
    }


}