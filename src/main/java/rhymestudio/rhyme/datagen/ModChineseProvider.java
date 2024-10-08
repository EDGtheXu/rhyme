package rhymestudio.rhyme.datagen;


import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import static rhymestudio.rhyme.Rhyme.MODID;

public class ModChineseProvider extends LanguageProvider {
    public ModChineseProvider(PackOutput output) {
        super(output, MODID, "zh_cn");
    }

    @Override
    protected void addTranslations() {


        add("rhyme.item.sun", "阳光");


        add("creativetab.rhyme.cards","植物");
        add("rhyme.item.sun_flower", "向日葵");


    }

}