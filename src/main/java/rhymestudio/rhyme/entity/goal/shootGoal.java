package rhymestudio.rhyme.entity.goal;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rhymestudio.rhyme.utils.Computer;

import java.util.function.Supplier;

public class shootGoal extends Goal {

    private int internalTick;
    private final Mob mob;
    private final Level level;
    private final int _internalTick;
    private final Supplier<? extends Projectile> projectile;

    public shootGoal(Mob mob, int internalTick, Supplier<? extends Projectile> projectile) {
        this.mob = mob;
        this.level = mob.level();
        this.internalTick = internalTick;
        this._internalTick = internalTick;
        this.projectile = projectile;
    }

    public boolean canUse() {
        LivingEntity livingentity = mob.getTarget();
        if (livingentity != null && livingentity.isAlive()) {
            return true;
        }
        return false;

        /*
        if(mob.getTarget() == null) return false;
        double angle = Computer.angle(mob.getForward(),mob.getTarget().position().subtract(mob.position()));
        return mob.isAlive() &&(
                angle< 0.2 ||
                angle < 1 && mob.distanceToSqr(mob.getTarget()) < 2
        );
        */
    }

    public void start() {
        internalTick = _internalTick;
    }

    public void tick() {
        /*
        if(--internalTick<0){
            internalTick = _internalTick;

            Projectile arrow = projectile.get();
            arrow.setOwner(mob);
            Vec3 dir = mob.getTarget().position().add(0, 1, 0).subtract(mob.position());
            arrow.shoot(dir.x, dir.y, dir.z, 2F, 1.0F);
            level.addFreshEntity(arrow);

        }*/

        --this.internalTick;
        LivingEntity livingentity = mob.getTarget();
        if (livingentity != null && livingentity.isAlive()) {
            mob.getLookControl().setLookAt(livingentity, 180.0F, 180.0F);
            double d0 = mob.distanceToSqr(livingentity);
            if (d0 < 400.0D) {
                if (this.internalTick <= 0) {
                    this.internalTick = (int) (_internalTick + Math.random() * 20);
                    Projectile arrow = projectile.get();
                    arrow.setOwner(mob);
                    Vec3 dir = mob.getTarget().position().add(0, 1, 0).subtract(mob.position());
                    arrow.shoot(dir.x, dir.y, dir.z, 2F, 1.0F);
                    level.addFreshEntity(arrow);
                }
            } else {
                mob.setTarget(null);
            }
            super.tick();
        }else{
            mob.setTarget(null);
        }
    }
}
