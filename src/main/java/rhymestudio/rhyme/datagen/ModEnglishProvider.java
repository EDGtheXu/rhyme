package rhymestudio.rhyme.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import rhymestudio.rhyme.Rhyme;

public class ModEnglishProvider extends LanguageProvider {

    public ModEnglishProvider(PackOutput output) {
        super(output, Rhyme.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {


        add("rhyme.item.sun", "sun");

        add("creativetab.rhyme.cards","plants");
        add("rhyme.item.sun_flower", "sun flower");

    }

}