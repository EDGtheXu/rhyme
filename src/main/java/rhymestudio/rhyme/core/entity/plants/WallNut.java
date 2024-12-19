package rhymestudio.rhyme.core.entity.plants;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import rhymestudio.rhyme.core.entity.AbstractPlant;
import rhymestudio.rhyme.core.entity.ai.CircleSkill;

public class WallNut extends AbstractPlant {
    public WallNut(EntityType<? extends AbstractPlant> type, Level level,
                   AnimationDefinition idle1, AnimationDefinition idle2, AnimationDefinition idle3,
                   Builder builder) {
        super(type, level,builder);
        this.animState.addAnimation("idle1", idle1,1);
        this.animState.addAnimation("idle2", idle2,1);
        this.animState.addAnimation("idle3", idle3,1);
    }

    private LivingEntity target;
    @Override
    public void addSkills() {
        super.addSkills();
        CircleSkill idle1 = new CircleSkill( "idle1",  999999999, 0)
                .onTick(a-> {
                    doSmth();
                    if(this.getHealth() / this.getMaxHealth() < 0.666){
                        skills.forceEnd();
                    }
                });
        CircleSkill idle2 = new CircleSkill( "idle2",  999999999, 0)
                .onTick(a-> {
                    doSmth();
                    if(this.getHealth() / this.getMaxHealth() < 0.333){
                        skills.forceEnd();
                    }
                });
        CircleSkill idle3 = new CircleSkill( "idle3",  999999999, 0)
                .onTick(a-> doSmth());
        this.addSkill(idle1);
        this.addSkill(idle2);
        this.addSkill(idle3);
    }
    private void doSmth(){
        level().getEntities(this, this.getBoundingBox().inflate(5f)).forEach(e -> {
            if(e instanceof Mob mob) {
                if(mob instanceof Enemy && mob.isAggressive())
                    mob.setTarget(this);
            }
        });
    }

}
