package rhymestudio.rhyme.datagen.lang;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.core.registry.ModBlocks;
import rhymestudio.rhyme.core.registry.items.MaterialItems;
import rhymestudio.rhyme.core.registry.items.PlantItems;

import java.util.Arrays;
import java.util.stream.Collectors;


public class ModEnglishProvider extends LanguageProvider {

    public ModEnglishProvider(PackOutput output) {
        super(output, Rhyme.MODID, "en_us");
    }
    private static String toTitleCase(String raw) {
        return Arrays.stream(raw.split("_"))
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
    @Override
    protected void addTranslations() {



        add("rhyme.item.sun", "sun");
        add("creativetab.rhyme.materials", "pvz materials");
        add("creativetab.rhyme.cards", "pvz cards");
        add("creativetab.rhyme.blocks", "pvz blocks");
        add("creativetab.rhyme.armors", "pvz armors");

        add("container.rhyme.sun_creator", "Sun Creator");
        add("plantcard.tooltip.consumed_sun","consume sun");
        add("plantcard.tooltip.card_quality","quality");
        add("plantcard.tooltip.card_quality.card_quality_0","copper");
        add("plantcard.tooltip.card_quality.card_quality_1","silver");
        add("plantcard.tooltip.card_quality.card_quality_2","gold");
        add("plantcard.tooltip.card_quality.card_quality_3","diamond");
        add("plantcard.tooltip.card_quality.card_quality_4","emerald");



        PlantItems.PLANTS.getEntries().forEach(entity -> add(entity.get(), toTitleCase(entity.getId().getPath())));
        MaterialItems.MATERIALS.getEntries().forEach(entity -> add(entity.get(), toTitleCase(entity.getId().getPath())));
        ModBlocks.BLOCK_ITEMS.getEntries().forEach(entity -> add(entity.get(), toTitleCase(entity.getId().getPath())));
    }

}