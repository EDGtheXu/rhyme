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
import rhymestudio.rhyme.registry.ModEntities;
import rhymestudio.rhyme.utils.Computer;
import software.bernie.geckolib.animation.RawAnimation;

public class Pea extends AbstractPlant {
    public int _internalAttackTime = 100;
    public int _attackTriggerTime = 60;
    public int _attackAnimTime = 80;

    public Pea(EntityType<? extends Pea> tEntityType, Level level) {super(tEntityType, level);}

    public Pea(Vec3 pos, Level level) {
        super(ModEntities.PEA_SHOOTER.get(), level);
        this.setPos(pos);
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
        idle = new CircleSkill( "idle",  200, _internalAttackTime,
                a->{},
                a-> {
                    if(getTarget() != null && getTarget().isAlive()
                            && Computer.angle(this.getForward(), getTarget().getEyePosition().subtract(this.getEyePosition())) < 10){
                        target = getTarget();
                        skills.forceEnd();}
                    },
                a->{}
        );

        shoot = new CircleSkill( "shoot", _attackAnimTime, _attackTriggerTime,
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
        Projectile arrow = new Arrow(level(), this, Items.ARROW.getDefaultInstance(), Items.ARROW.getDefaultInstance());
        arrow.setOwner(this);
        Vec3 dir = tar.subtract(getEyePosition());
        arrow.shoot(dir.x, dir.y, dir.z, 2F, 1.0F);
        level().addFreshEntity(arrow);
    }
}
