package rhymestudio.rhyme.entity.plants;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.ai.CircleSkill;
import rhymestudio.rhyme.entity.proj.LineProj;
import rhymestudio.rhyme.registry.ModEntities;
import rhymestudio.rhyme.utils.Computer;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Pea extends AbstractPlant {

    private final AnimationDefinition idle;
    private final AnimationDefinition shoot;
    private BiConsumer<AbstractPlant,LivingEntity> attackCallback;

    public Pea(EntityType<? extends AbstractPlant> type,Level level, AnimationDefinition idle,AnimationDefinition shoot, Builder builder) {
        super(type, level);
        this.builder = builder;
        this.idle = idle;
        this.shoot = shoot;
    }

    public Pea(EntityType<? extends AbstractPlant> type, Level level, AnimationDefinition idle, AnimationDefinition shoot, BiConsumer<AbstractPlant,LivingEntity> doAttack, Builder builder) {
        this(type, level, idle, shoot, builder);
        this.attackCallback = doAttack;
    }

    public void cafeDefineAnimations(){
        super.addSkills();
        this.animState.addAnimation("idle_on", idle,1);
        this.animState.addAnimation("shoot", shoot,1);
    }
    private LivingEntity target;
    @Override
    public void addSkills() {
        super.addSkills();
        //tip                                                  idle持续时间        触发攻击时间
        CircleSkill  idle = new CircleSkill( "idle_on",  999999999, builder.attackInternalTick,
                // tip刚进入状态
                a->{},
                // tip进入状态触发时间
                a-> {
                    if(skills.canContinue() &&
                            getTarget() != null && getTarget().isAlive() &&
                            Computer.angle(this.getForward(), getTarget().getEyePosition().subtract(this.getEyePosition())) < 20){
                        target = getTarget();
                        //tip触发攻击，进入射击状态
                        skills.forceEnd();
                    }
                    },
                // tip结束状态
                a->{}
        );
        // tip                                                攻击持续时间        射击触发时间
        CircleSkill  shoot = new CircleSkill( "shoot", builder.attackAnimTick, builder.attackTriggerTick,
                a->{},
                a->{
                    if(skills.canTrigger() && target!= null && target.isAlive()){
                        //tip 触发射击，生成弹幕
                        if(attackCallback!= null) attackCallback.accept(this,target);
                        else doAttack(target);
                    }
                },
                a->{}
        );
        this.addSkill(idle);
        this.addSkill(shoot);
    }

    public void doAttack(LivingEntity tar){
        Vec3 pos = tar.getEyePosition();
        Projectile arrow = ModEntities.PEA_PROJ.get().create(level());
        arrow.setOwner(this);
        arrow.setPos(this.getEyePosition().add(0,0.1F,0));
        Vec3 dir = pos.subtract(getEyePosition());
        arrow.shoot(dir.x, dir.y, dir.z, builder.projSpeed, 1.0F);
        level().addFreshEntity(arrow);
    }

}
