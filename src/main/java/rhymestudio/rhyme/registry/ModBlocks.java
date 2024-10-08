package rhymestudio.rhyme.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import rhymestudio.rhyme.Rhyme;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Rhyme.MODID);



    public static RegistryObject<Block> register(String name) {
        var block =  BLOCKS.register(name, () -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));

        if (blocks != null) {
            blocks.add(block);
        }
        return block ;
    }
    public static List<RegistryObject<Block>> blocks = new ArrayList<>();
}
