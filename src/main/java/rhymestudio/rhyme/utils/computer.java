package rhymestudio.rhyme.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import rhymestudio.rhyme.registry.ModItems;

import java.util.concurrent.atomic.AtomicInteger;

public class computer {

    public static double angle(Vec3 line1, Vec3 line2){return Math.acos(line1.dot(line2)/line1.length()/line2.length());}

    public static int getInventoryItemCount(Player player, Item item){
        AtomicInteger count = new AtomicInteger();
        player.getInventory().items.forEach(stack -> count.addAndGet(stack.is(item)? stack.getCount():0));
        return count.get();
    }
}
