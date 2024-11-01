package rhymestudio.rhyme.entity.plants;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.ai.CircleSkill;
import rhymestudio.rhyme.entity.proj.LineProj;
import rhymestudio.rhyme.registry.ModEntities;
import rhymestudio.rhyme.utils.Computer;

public class PeaNew extends AbstractPlant {

    public PeaNew(Level level,Builder builder) {
        super(ModEntities.PEA_NEW.get(), level);
        this.builder = builder;
    }

    public void cafeDefineAnimations(){
        this.cafeAddAnimation("idle",100,1,true);
        this.cafeAddAnimation("shoot",100,1,true);
    }
    LivingEntity target;
    @Override
    public void addSkills() {
        //tip                                                  idle持续时间        触发攻击时间
        CircleSkill  idle = new CircleSkill( "idle",  999999999, builder.attackInternalTick - builder.attackAnimTick,
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
                        doAttack(target.getEyePosition());
                    }
                },
                a->{}
        );
        this.addSkill(idle);
        this.addSkill(shoot);
    }

    public void doAttack(Vec3 tar){
//        Projectile arrow = new Arrow(level(), this, Items.ARROW.getDefaultInstance(), Items.ARROW.getDefaultInstance());
        Projectile arrow =new LineProj(this,builder.attackDamage,builder.projSpeed);
        arrow.setOwner(this);
        arrow.setPos(this.getEyePosition());
        Vec3 dir = tar.subtract(getEyePosition());
        arrow.shoot(dir.x, dir.y, dir.z, builder.projSpeed, 1.0F);
        level().addFreshEntity(arrow);
    }


    public void tick() {


        super.tick();
    }
}
