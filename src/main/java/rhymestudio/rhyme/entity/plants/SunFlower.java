package rhymestudio.rhyme.entity.plants;

import net.minecraft.world.level.Level;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.SunItemEntity;
import rhymestudio.rhyme.entity.ai.CircleSkill;
import rhymestudio.rhyme.registry.ModEntities;
import software.bernie.geckolib.animation.RawAnimation;


public class SunFlower extends AbstractPlant {

    public SunFlower(Level level, Builder builder) {
        super(ModEntities.SUN_FLOWER.get(), level);
        this.builder = builder;
    }

    static RawAnimation idle = RawAnimation.begin().thenPlay("idle");
    static RawAnimation sun = RawAnimation.begin().thenPlay("sun");

    CircleSkill idleSkill;
    CircleSkill sunSkill;
    @Override
    public void addSkills() {
        idleSkill = new CircleSkill("idle",builder.attackInternalTick,0);
        sunSkill = new CircleSkill("sun",builder.attackAnimTick, builder.attackTriggerTick,
            a->{},
            a->{
                if(skills.canTrigger()){
                    SunItemEntity entity = new SunItemEntity(level(), position().add(0,1,0));
                    level().addFreshEntity(entity);
                }
            },
            a->{}
        );
        addSkill(idleSkill,idle);
        addSkill(sunSkill,sun);
    }
}
