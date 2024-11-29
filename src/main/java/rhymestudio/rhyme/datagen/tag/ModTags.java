package rhymestudio.rhyme.datagen.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import rhymestudio.rhyme.Rhyme;

public final class ModTags {
    public static class Blocks {
//        public static final TagKey<Block> NEEDS_1_LEVEL = tag("needs_1_level");

        private static TagKey<Block> tag(String id) {
            return BlockTags.create(Rhyme.space(id));
        }
    }

    public static class Items {
        public static final TagKey<Item> MODEL_ARMOR_ITEM_TAG = tag("model_armor_item_tag");


        private static TagKey<Item> tag(String id) {
            return ItemTags.create(Rhyme.space(id));
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> VAMPIRE = tag("has_spawn/vampire");
        public static final TagKey<Biome> ADVANCED_VAMPIRE = tag("no_spawn/advanced_vampire");


        private static @NotNull TagKey<Biome> tag(@NotNull String name) {
            return TagKey.create(Registries.BIOME, Rhyme.space(name));
        }
    }
    public static class Damages {
        public static final TagKey<DamageType> VAMPIRE = tag("no");


        private static @NotNull TagKey<DamageType> tag(@NotNull String name) {
            return TagKey.create(Registries.DAMAGE_TYPE, Rhyme.space(name));
        }
    }


}
