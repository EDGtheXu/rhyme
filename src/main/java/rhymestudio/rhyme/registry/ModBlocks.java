package rhymestudio.rhyme.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import rhymestudio.rhyme.Rhyme;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Rhyme.MODID);



    public static Supplier<Block> register(String name) {
        var block =  BLOCKS.register(name, () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE)));

        if (blocks != null) {
            blocks.add(block);
        }
        return block ;
    }
    public static List<DeferredBlock<Block>> blocks = new ArrayList<>();
}
