package rhymestudio.rhyme.datagen;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import rhymestudio.rhyme.Rhyme;

import static rhymestudio.rhyme.Rhyme.MODID;

public class ModChineseProvider extends LanguageProvider {
    public ModChineseProvider(PackOutput output) {
        super(output, MODID, "zh_cn");
    }

    @Override
    protected void addTranslations() {

        add("creativetab.rhyme.materials", "开花与终结有限公司：产品");
        add("creativetab.rhyme.cards", "开花与终结有限公司：种子包");
        add("creativetab.rhyme.blocks", "开花与终结有限公司：方块");
        add("creativetab.rhyme.armors", "开花与终结有限公司：护具");

        add("container.rhyme.sun_creator", "光萃台");

        Rhyme.chineseProviders.forEach(a->a.accept(this));

    }

}