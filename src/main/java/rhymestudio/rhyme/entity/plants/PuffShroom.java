package rhymestudio.rhyme.entity.plants;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.ai.CircleSkill;
import rhymestudio.rhyme.registry.Entities.PlantEntities;
import rhymestudio.rhyme.utils.Computer;

import java.util.function.BiConsumer;

public class PuffShroom extends AbstractPlant {

    private BiConsumer<AbstractPlant,LivingEntity> attackCallback;

    public PuffShroom(EntityType<? extends AbstractPlant> type, Level level, AnimationDefinition sleep, AnimationDefinition idle, AnimationDefinition shoot, Builder builder) {
        super(type, level);
        this.builder = builder;
        this.animState.addAnimation("sleep", sleep,1);
        this.animState.addAnimation("idle", idle,1);
        this.animState.addAnimation("shoot", shoot,1);
    }

    public PuffShroom(EntityType<? extends AbstractPlant> type, Level level, AnimationDefinition sleep, AnimationDefinition idle, AnimationDefinition shoot, BiConsumer<AbstractPlant,LivingEntity> doAttack, Builder builder) {
        this(type, level, sleep,idle, shoot, builder);
        this.attackCallback = doAttack;
    }

    public void cafeDefineAnimations(){
        super.addSkills();


    }
    private LivingEntity target;
    @Override
    public void addSkills() {
        super.addSkills();
//        this.animState.playAnim("sleep",tickCount);
        CircleSkill sleep = new CircleSkill( "sleep",  999999999, 0,
                a->{},
                a->{
                    if(!level().isClientSide() && level().isNight())
                        skills.forceEnd();
                },
                a->{}
                );
        CircleSkill idle = new CircleSkill( "idle",  999999999, builder.attackInternalTick,
                a->{},
                a-> {
                    if(!level().isClientSide() && !level().isNight())
                        skills.forceStartIndex(0);
                    if(skills.canContinue() &&
                            getTarget() != null && getTarget().isAlive() &&
                            Computer.angle(this.getForward(), getTarget().getEyePosition().subtract(this.getEyePosition())) < 20){
                        target = getTarget();
                        skills.forceEnd();
                    }
                    },
                a->{}
        );
        CircleSkill  shoot = new CircleSkill( "shoot", builder.attackAnimTick, builder.attackTriggerTick,
                a->{},
                a->{
                    if(skills.canTrigger() && target!= null && target.isAlive()){
                        if(attackCallback!= null) attackCallback.accept(this,target);
                        else doAttack(target);
                    }
                },
                a->{skills.forceStartIndex(1);}
        );
        this.addSkill(sleep);
        this.addSkill(idle);
        this.addSkill(shoot);
    }

    public void doAttack(LivingEntity tar){
        Vec3 pos = tar.getEyePosition();
        Projectile arrow = PlantEntities.PEA_PROJ.get().create(level());
        arrow.setOwner(this);
        arrow.setPos(this.getEyePosition().add(0,0.1F,0));
        Vec3 dir = pos.subtract(getEyePosition());
        arrow.shoot(dir.x, dir.y, dir.z, builder.projSpeed, 1.0F);
        level().addFreshEntity(arrow);

    }

}
