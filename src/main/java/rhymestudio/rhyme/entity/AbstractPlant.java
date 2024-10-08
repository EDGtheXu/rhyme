package rhymestudio.rhyme.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rhymestudio.rhyme.registry.ModEntities;

public abstract class AbstractPlant extends Mob {
    /*
    public AbstractPlant(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }*/

    public abstract ResourceLocation getTexture();

    public <T extends AbstractPlant> AbstractPlant(EntityType<T> tEntityType, Level level) {
        super(tEntityType, level);
    }




}
