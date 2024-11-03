package rhymestudio.rhyme.entity.plants;

import net.minecraft.world.level.Level;
import rhymestudio.rhyme.client.animation.SunflowerAnimation;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.SunItemEntity;
import rhymestudio.rhyme.entity.ai.CircleSkill;
import rhymestudio.rhyme.registry.ModEntities;


public class SunFlower extends AbstractPlant {

    public SunFlower(Level level, Builder builder) {
        super(ModEntities.SUN_FLOWER.get(), level);
        this.builder = builder;
    }

    @Override
    protected void cafeDefineAnimations() {
        this.animState.addAnimation("idle", SunflowerAnimation.idle,1);
        this.animState.addAnimation("sun", SunflowerAnimation.sun,1);
    }

    @Override
    public void addSkills() {
        CircleSkill idleSkill = new CircleSkill("idle",builder.attackInternalTick,0);
        CircleSkill sunSkill = new CircleSkill("sun",builder.attackAnimTick, builder.attackTriggerTick,
            a->{},
            a->{
                if(skills.canTrigger()){
                    SunItemEntity entity = new SunItemEntity(level(), position().add(0,1,0));
                    level().addFreshEntity(entity);
                }
            },
            a->{}
        );
        addSkill(idleSkill);
        addSkill(sunSkill);
    }
}
