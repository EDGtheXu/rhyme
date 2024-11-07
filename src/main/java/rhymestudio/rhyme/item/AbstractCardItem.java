package rhymestudio.rhyme.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import rhymestudio.rhyme.dataComponent.CardQualityComponent;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.registry.ModDataComponentTypes;
import rhymestudio.rhyme.registry.items.MaterialItems;
import rhymestudio.rhyme.utils.Computer;

public class AbstractCardItem<T extends AbstractPlant> extends Item {
    public DeferredHolder<EntityType<?>, EntityType<T>> entityType;
    public void upLevel(){

    }

    public int consume;
    public AbstractCardItem(Properties properties, DeferredHolder<EntityType<?>, EntityType<T>> entityType, int consume){
        super(properties);
        this.entityType = entityType;
        this.consume = consume;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide()) {

            ItemStack itemstack = player.getItemInHand(hand);

            CardQualityComponent.tryUpLevel(itemstack);

            if(!player.canBeSeenAsEnemy()){ // 创造
                summon(player, level);
                return InteractionResultHolder.success(itemstack);
            }
            if(!Computer.tryCombineInventoryItem(player, MaterialItems.SUN_ITEM.get(), consume)){
                return InteractionResultHolder.fail(itemstack);
            }
            summon(player, level);
            itemstack.setDamageValue(itemstack.getDamageValue() + 1);
            if(itemstack.getDamageValue() >= itemstack.getMaxDamage())
                itemstack.shrink(1);
            return InteractionResultHolder.success(itemstack);
        }
        return super.use(level, player, hand);
    }

    public void summon(Player player, Level level){
        var entity = entityType.get().create(level);
        entity.setOwner(player);
        entity.setPos(player.position());
        level.addFreshEntity(entity);
    }

}