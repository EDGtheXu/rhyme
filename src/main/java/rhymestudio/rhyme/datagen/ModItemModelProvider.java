package rhymestudio.rhyme.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;

import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.item.AbstructCardItem;
import rhymestudio.rhyme.registry.ModItems;


import static rhymestudio.rhyme.Rhyme.MODID;

public class ModItemModelProvider extends ItemModelProvider {


    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        ModItems.ITEMS.getEntries().forEach(item -> {
            Item value = item.get();
            String path = item.getId().getPath().toLowerCase();
            if(value instanceof AbstructCardItem){
                withExistingParent("item/"+path, "item/generated").texture("layer0", Rhyme.space("item/"+path));
            }else{
                withExistingParent("item/"+path, "item/generated").texture("layer0", Rhyme.space("item/"+path));
            }

        });
    }


}
