package rhymestudio.rhyme.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;

import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.item.AbstructCardItem;
import rhymestudio.rhyme.registry.items.MaterialItems;
import rhymestudio.rhyme.registry.items.PlantItems;


import static rhymestudio.rhyme.Rhyme.MODID;

public class ModItemModelProvider extends ItemModelProvider {


    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        PlantItems.PLANTS.getEntries().forEach(item -> {
            String path = item.getId().getPath().toLowerCase();
            try {
                withExistingParent("item/"+path, "item/generated").texture("layer0", Rhyme.space("item/"+path));
            }catch (Exception e){}
        });
        MaterialItems.MATERIALS.getEntries().forEach(item -> {
            String path = item.getId().getPath().toLowerCase();
            try {
                withExistingParent("item/"+path, "item/generated").texture("layer0", Rhyme.space("item/"+path));
            }catch (Exception e){}
        });

    }
}
