package rhymestudio.rhyme.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.registry.ModItems;
import rhymestudio.rhyme.utils.Computer;

import java.lang.reflect.Constructor;

public class AbstructCardItem extends Item {
    Class<? extends AbstractPlant> clazz;
    Constructor<? extends AbstractPlant> constructor = null;
    public int consume;
    public AbstructCardItem(Properties properties, Class<? extends AbstractPlant> clazz,int consume){
        super(properties);
        this.clazz = clazz;
        this.consume = consume;
        try {
            constructor = clazz.getDeclaredConstructor(Vec3.class, Level.class);
            constructor.setAccessible(true);
        }catch (Exception e) {
            throw new Error("No valid constructor found for " + clazz.getName());
        }
    }


    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {

            if(!Computer.tryCombineInventoryItem(player, ModItems.SUN_ITEM.get(), consume))
                return super.use(level, player, hand);

            try {
                var entity = constructor.newInstance(player.position(), level);
                entity.setOwner(player);
                level.addFreshEntity(entity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return super.use(level, player, hand);
    }


}