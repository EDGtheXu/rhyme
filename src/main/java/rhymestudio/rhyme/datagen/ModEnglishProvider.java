package rhymestudio.rhyme.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.registry.items.MaterialItems;
import rhymestudio.rhyme.registry.items.PlantItems;

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


        PlantItems.PLANTS.getEntries().forEach(entity -> add(entity.get(), toTitleCase(entity.getId().getPath())));
        MaterialItems.MATERIALS.getEntries().forEach(entity -> add(entity.get(), toTitleCase(entity.getId().getPath())));

    }

}