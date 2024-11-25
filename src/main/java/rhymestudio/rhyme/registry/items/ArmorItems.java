package rhymestudio.rhyme.registry.items;

import net.minecraft.client.model.TridentModel;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TridentItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.dataComponent.ModRarity;
import rhymestudio.rhyme.item.CustomRarityItem;
import rhymestudio.rhyme.item.armor.NormalArmorItem;
import rhymestudio.rhyme.registry.ModArmorMaterials;
import rhymestudio.rhyme.registry.ModDataComponentTypes;

public class ArmorItems {
    public static final DeferredRegister.Items ARMORS = DeferredRegister.createItems(Rhyme.MODID);

    public static final DeferredItem<Item> CONE_HELMET = register("cone_helmet","路障", ModArmorMaterials.CONE_ARMOR_MATERIALS,ArmorItem.Type.HELMET,50 );

    public static final DeferredItem<Item> IRON_BUCKET_HELMET = register("iron_bucket_helmet","铁桶", ModArmorMaterials.IRON_BUCKET_ARMOR_MATERIALS,ArmorItem.Type.HELMET,100 );


    public static DeferredItem<Item> register(String en, String zh, Holder<ArmorMaterial> material,ArmorItem.Type type, int durability, ModRarity rarity) {
        DeferredItem<Item> item =  ARMORS.register("armor/"+en, () -> new NormalArmorItem(material,type,new Item.Properties().stacksTo(1).durability(durability).component(ModDataComponentTypes.MOD_RARITY,rarity)));
        Rhyme.chineseProviders.add((c)->c.add(item.get(),zh));
        return item;

    }
    public static DeferredItem<Item> register(String en, String zh, Holder<ArmorMaterial> material,ArmorItem.Type type, int durability) {
        return register(en, zh, material,type,durability,ModRarity.COMMON);
    }

}
