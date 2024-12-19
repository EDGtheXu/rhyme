package rhymestudio.rhyme.core.entity.plants;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rhymestudio.rhyme.core.entity.AbstractPlant;
import rhymestudio.rhyme.core.entity.ai.CircleSkill;
import rhymestudio.rhyme.core.registry.entities.PlantEntities;
import rhymestudio.rhyme.utils.Computer;

import java.util.function.BiConsumer;

public class Pea extends AbstractPlant {

    private final AnimationDefinition idle;
    private final AnimationDefinition shoot;
    private BiConsumer<AbstractPlant,LivingEntity> attackCallback;

    public Pea(EntityType<? extends AbstractPlant> type, Level level, AnimationDefinition idle, AnimationDefinition shoot, BiConsumer<AbstractPlant,LivingEntity> doAttack, Builder builder) {
        super(type, level, builder);
        this.attackCallback = doAttack;
        this.idle = idle;
        this.shoot = shoot;
    }

    public void cafeDefineAnimations(){
        super.cafeDefineAnimations();
        this.animState.addAnimation("idle_on", idle,1);
        this.animState.addAnimation("shoot", shoot,1);
    }
    private LivingEntity target;
    @Override
    public void addSkills() {
        super.addSkills();
        //tip                                                  idle持续时间        触发攻击时间
        CircleSkill  idle = new CircleSkill( "idle_on",  999999999, builder.attackInternalTick).
                onTick(a-> {
                    if(skills.canContinue() &&
                            getTarget() != null && getTarget().isAlive() &&
                            Computer.angle(this.getForward(), getTarget().getEyePosition().subtract(this.getEyePosition())) < 20){
                        target = getTarget();
                        skills.forceEnd();
                    }
                    });
        // tip                                                攻击持续时间        射击触发时间
        CircleSkill  shoot = new CircleSkill( "shoot", builder.attackAnimTick, builder.attackTriggerTick)
                .onTick(a->{
                    if(skills.canTrigger() && target!= null && target.isAlive()){
                        //tip 触发射击，生成弹幕
                        if(attackCallback!= null) attackCallback.accept(this,target);
                        else doAttack(target);
                    }
                });
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
