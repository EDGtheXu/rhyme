package rhymestudio.rhyme.entity.plants;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.SunItemEntity;
import rhymestudio.rhyme.registry.ModEntities;
import rhymestudio.rhyme.registry.ModItems;

public class SunFlower extends AbstractPlant {
    int age = 7*20;
    static final int MAX_AGE = 5*20;

    public SunFlower(EntityType<? extends SunFlower> entityType, Level level) {
        super(entityType, level);
    }

    public SunFlower(Vec3 pos, Level level) {
        super(ModEntities.SUN_FLOWER.get(), level);
        this.setPos(pos);
    }

    public void tick() {
        super.tick();
        if(!level().isClientSide()){
            if(--age <= 0){
                age = MAX_AGE;
                SunItemEntity entity = new SunItemEntity(level(), position().add(0,1,0));

                level().addFreshEntity(entity);
            }

        }
    }


    @Override
    public ResourceLocation getTexture() {
        return Rhyme.space("textures/item/plant_card/sun_flower.png");
    }
}
