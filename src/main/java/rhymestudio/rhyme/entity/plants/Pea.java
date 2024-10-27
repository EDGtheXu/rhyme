package rhymestudio.rhyme.entity.plants;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.ai.CircleSkill;
import rhymestudio.rhyme.entity.proj.LineProj;
import rhymestudio.rhyme.registry.ModEntities;
import rhymestudio.rhyme.utils.Computer;
import software.bernie.geckolib.animation.RawAnimation;

public class Pea extends AbstractPlant {


    public Pea(Level level,Builder builder) {
        super(ModEntities.PEA_SHOOTER.get(), level);
        this.builder = builder;
    }


    static RawAnimation idle_normal = RawAnimation.begin()
            .thenPlay("idle_normal")
            .thenPlay("idle_eyes_closed_1")
            .thenPlay("idle_eyes_closed_2")
            .thenPlay("idle_eyes_closed_3")
            ;

    static RawAnimation attack = RawAnimation.begin().thenPlay("shoot");

    CircleSkill idle;
    CircleSkill shoot;

    Entity target;
    @Override
    public void addSkills() {
        //tip                                                  idle持续时间        触发攻击时间
        idle = new CircleSkill( "idle",  builder.attackInternalTick *2, builder.attackInternalTick - builder.attackAnimTick,
                // tip刚进入状态
                a->{},
                // tip进入状态触发时间
                a-> {
                    if(getTarget() != null && getTarget().isAlive()
                            && Computer.angle(this.getForward(), getTarget().getEyePosition().subtract(this.getEyePosition())) < 10){
                        target = getTarget();
                        //tip触发攻击，进入射击状态
                        skills.forceEnd();}
                    },
                // tip结束状态
                a->{}
        );
        // tip                                                攻击持续时间        射击触发时间
        shoot = new CircleSkill( "shoot", builder.attackAnimTick, builder.attackTriggerTick,
                a->{},
                a->{
                    if(skills.canTrigger() && target!= null && target.isAlive()){
                        //tip 触发射击，生成弹幕
                        doAttack(target.getEyePosition());
                    }
                },
                a->{}
        );
        this.addSkill(idle, idle_normal);
        this.addSkill(shoot, attack);
    }

    public void doAttack(Vec3 tar){
//        Projectile arrow = new Arrow(level(), this, Items.ARROW.getDefaultInstance(), Items.ARROW.getDefaultInstance());
        Projectile arrow =new LineProj(this,builder.attackDamage,builder.projSpeed);
        arrow.setOwner(this);
        arrow.setPos(this.position());
        Vec3 dir = tar.subtract(getEyePosition());
        arrow.shoot(dir.x, dir.y, dir.z, builder.projSpeed, 1.0F);
        level().addFreshEntity(arrow);
    }




}
