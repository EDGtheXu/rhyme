package rhymestudio.rhyme.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;

import java.util.concurrent.atomic.AtomicInteger;

public class Computer {

    public static double angle(Vec3 line1, Vec3 line2){return Math.acos(line1.dot(line2)/line1.length()/line2.length());}

    public static int getInventoryItemCount(Player player, Item item){
        AtomicInteger count = new AtomicInteger();
        player.getInventory().items.forEach(stack -> count.addAndGet(stack.is(item)? stack.getCount():0));
        return count.get();
    }

    public static void consumeInventoryItemCount(Player player, Item item, int consumeCount){
        AtomicInteger count = new AtomicInteger();
        player.getInventory().items.forEach(stack -> {
            if(stack.is(item) && count.get() < consumeCount){
                int toConsume = Math.min(stack.getCount(), consumeCount - count.get());
                stack.shrink(toConsume);
                count.addAndGet(toConsume);
            }
        });
    }

    public static boolean tryCombineInventoryItem(Player player, Item item, int count){
        int have = getInventoryItemCount(player, item);
        if(have < count) return false;
        consumeInventoryItemCount(player, item, count);
        return true;
    }

}
