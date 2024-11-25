package rhymestudio.rhyme.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import rhymestudio.rhyme.Rhyme;

public final class ModTags {
    public static class Blocks {
//        public static final TagKey<Block> NEEDS_1_LEVEL = register("needs_1_level");

        private static TagKey<Block> register(String id) {
            return BlockTags.create(Rhyme.space(id));
        }
    }

    public static class Items {

        public static final TagKey<Item> MODEL_ARMOR_ITEM_TAG = register("model_armor_item_tag");


        private static TagKey<Item> register(String id) {
            return ItemTags.create(Rhyme.space(id));
        }
    }

}
