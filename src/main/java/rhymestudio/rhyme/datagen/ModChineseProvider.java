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

        add("creativetab.rhyme.materials", "PVZ 材料");
        add("creativetab.rhyme.cards", "PVZ 卡包");
        add("creativetab.rhyme.blocks", "pvz 方块");
        add("creativetab.rhyme.armors", "pvz 护甲");

        Rhyme.chineseProviders.forEach(a->a.accept(this));

    }

}