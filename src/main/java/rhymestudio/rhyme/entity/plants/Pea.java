package rhymestudio.rhyme.entity.plants;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.ai.CircleSkill;
import rhymestudio.rhyme.entity.proj.LineProj;
import rhymestudio.rhyme.registry.ModEntities;
import rhymestudio.rhyme.utils.Computer;
import software.bernie.geckolib.animation.RawAnimation;

public class Pea extends AbstractPlant {

    Builder builder = new Builder();

    public Pea(EntityType<? extends Pea> tEntityType, Level level) {
        super(tEntityType, level);
        this.setHealth(builder.health);
    }

    public Pea(Level level) {
        super(ModEntities.PEA_SHOOTER.get(), level);
        //this.setPos(pos);
    }

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
        idle = new CircleSkill( "idle",  99999, builder.attackInternalTime,
                a->{},
                a-> {
                    if(getTarget() != null && getTarget().isAlive()
                            && Computer.angle(this.getForward(), getTarget().getEyePosition().subtract(this.getEyePosition())) < 10){
                        target = getTarget();
                        skills.forceEnd();}
                    },
                a->{}
        );

        shoot = new CircleSkill( "shoot", builder.attackAnimTime, builder.attackTriggerTime,
                a->{},
                a->{
                    if(skills.canTrigger() && target!= null && target.isAlive()){
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


    public static class Builder{
        int attackInternalTime = 60;
        int attackTriggerTime = 20;
        int attackAnimTime = 30;
        int attackDamage = 1;
        float projSpeed = 0.5f;
        int health = 20;

        public Builder setHealth(int health) {
            this.health = health;
            return this;
        }
        public Builder setSpeed(int speed) {
            this.projSpeed = speed;
            return this;
        }

        public Builder setAttackDamage(int damage) {
            this.attackDamage = damage;
            return this;
        }

        public Builder setAttackInternalTime(int attackInternalTime) {
            this.attackInternalTime = attackInternalTime;
            return this;
        }

        public Builder setAttackTriggerTime(int attackTriggerTime) {
            this.attackTriggerTime = attackTriggerTime;
            return this;
        }

        public Builder setAttackAnimTime(int attackAnimTime) {
            this.attackAnimTime = attackAnimTime;
            return this;
        }



    }

}
