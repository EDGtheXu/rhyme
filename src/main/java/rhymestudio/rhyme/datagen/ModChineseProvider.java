package rhymestudio.rhyme.datagen;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import static rhymestudio.rhyme.Rhyme.MODID;

public class ModChineseProvider extends LanguageProvider {
    public ModChineseProvider(PackOutput output) {
        super(output, MODID, "zh_cn");
    }

    @Override
    protected void addTranslations() {


        add("rhyme.item.sun", "阳光");
        add("creativetab.rhyme.materials", "PVZ 材料");
        add("creativetab.rhyme.cards", "PVZ 卡包");


        add("rhyme.item.sun_flower", "向日葵");


    }

}