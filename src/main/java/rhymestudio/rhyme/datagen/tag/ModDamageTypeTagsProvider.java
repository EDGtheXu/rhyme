package rhymestudio.rhyme.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static rhymestudio.rhyme.Rhyme.MODID;

public class ModDamageTypeTagsProvider extends DamageTypeTagsProvider {
    public ModDamageTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, MODID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.Provider holderLookup) {

        tag(DamageTypeTags.NO_KNOCKBACK)
                .add(ModTags.DamageTypes.PLANT_PROJ)
                .add(ModTags.DamageTypes.PLANT_EXPLORE);

    }



}
