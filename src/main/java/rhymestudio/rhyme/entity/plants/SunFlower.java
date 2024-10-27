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
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;


public class SunFlower extends AbstractPlant {
    int age = 7*20;
    static final int MAX_AGE = 5*20;

    public SunFlower(EntityType<? extends SunFlower> entityType, Level level) {
        super(entityType, level);
    }

    public SunFlower(Level level) {
        super(ModEntities.SUN_FLOWER.get(), level);
    }

    @Override
    public void addSkills() {

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

    RawAnimation idle = RawAnimation.begin().thenPlay("idle");

}
