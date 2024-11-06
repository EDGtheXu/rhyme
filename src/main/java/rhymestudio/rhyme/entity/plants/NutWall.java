package rhymestudio.rhyme.entity.plants;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.ai.CircleSkill;
import rhymestudio.rhyme.registry.ModEntities;
import rhymestudio.rhyme.utils.Computer;

import java.util.function.BiConsumer;

public class NutWall extends AbstractPlant {

    private final AnimationDefinition idle;

    public NutWall(EntityType<? extends AbstractPlant> type, Level level, AnimationDefinition idle,
                   Builder builder) {
        super(type, level);
        this.builder = builder;
        this.idle = idle;
    }


    public void cafeDefineAnimations(){
        super.cafeDefineAnimations();
        this.animState.addAnimation("idle_on", idle,1);
    }

    private LivingEntity target;
    @Override
    public void addSkills() {
        super.addSkills();
        CircleSkill  idle = new CircleSkill( "idle_on",  999999999, 0,
                // tip刚进入状态
                a->{},
                // tip进入状态触发时间
                a-> {
                    level().getEntities(this, this.getBoundingBox().inflate(5f)).forEach(e -> {
                        if(e instanceof Mob mob) {
                            if(mob instanceof Enemy && mob.isAggressive())
                                mob.setTarget(this);
                        }
                    });


                },
                a->{}
        );

        this.addSkill(idle);
    }
}
