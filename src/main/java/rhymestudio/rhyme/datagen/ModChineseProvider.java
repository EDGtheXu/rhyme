package rhymestudio.rhyme.datagen;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import rhymestudio.rhyme.registry.items.MaterialItems;
import rhymestudio.rhyme.registry.items.PlantItems;

import static rhymestudio.rhyme.Rhyme.MODID;

public class ModChineseProvider extends LanguageProvider {
    public ModChineseProvider(PackOutput output) {
        super(output, MODID, "zh_cn");
    }

    @Override
    protected void addTranslations() {

        add("creativetab.rhyme.materials", "PVZ 材料");
        add("creativetab.rhyme.cards", "PVZ 卡包");


        add(PlantItems.SUN_FLOWER.get(), "向日葵");
        add(PlantItems.PEA_ITEM.get(), "豌豆射手");
        add(PlantItems.REPEATER_ITEM.get(), "双重射手");
        add(PlantItems.ICE_PEA_ITEM.get(), "寒冰射手");
        add(PlantItems.NUT_WALL_ITEM.get(), "坚果墙");
        add(PlantItems.POTATO_MINE_ITEM.get(), "土豆雷");
        add(PlantItems.PUFF_SHROOM_ITEM.get(), "小喷菇");

        add(MaterialItems.SUN_ITEM.get(), "阳光");
        add(MaterialItems.PLANT_GENE.get(), "植物基因");
        add(MaterialItems.ANGER_GENE.get(), "易怒基因");
        add(MaterialItems.GENERAL_SEED.get(), "万能种子");
        add(MaterialItems.SOLID_SUN.get(), "固态阳光");

    }

}