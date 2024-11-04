package rhymestudio.rhyme.item.quality;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.registry.items.IconItems;

import java.util.List;
import java.util.function.Supplier;

public class CardQuality {
    final int level;
    final DeferredItem<Item> qualityItem;
    static int maxLevel = 5;

    CardQuality(int level, DeferredItem<Item> qualityItem){
        if(level < 0) this.level = 0;
        else if(level > maxLevel) this.level = maxLevel;
        else this.level = level;
        this.qualityItem = qualityItem;
    }
    public int getLevel(){
        return level;
    }
    public void setLevel(int level){

    }
    public CardQuality increaseLevel(){
        if(level < maxLevel) return levels().get(level+1);
        return levels().get(level);
    }
    public CardQuality decreaseLevel(){
        if(level > 0) return levels().get(level-1);
        return levels().get(level);
    }

    public Item getQualityItem(){
        return qualityItem.asItem();
    }

    public static List<CardQuality> levels(){
        return List.of(COPPER, SILVER, GOLD, EMERALD, DIAMOND);
    }

    public static final CardQuality COPPER  = new CardQuality(0,IconItems.COPPER);//铜
    public static final CardQuality SILVER  = new CardQuality(1, IconItems.SILVER);//银
    public static final CardQuality GOLD    = new CardQuality(2, IconItems.GOLD);//金
    public static final CardQuality EMERALD = new CardQuality(3, IconItems.EMERALD);//翡翠
    public static final CardQuality DIAMOND = new CardQuality(4, IconItems.DIAMOND);//钻石

}
