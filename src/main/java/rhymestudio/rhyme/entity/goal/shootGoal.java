package rhymestudio.rhyme.entity.goal;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.Projectile;
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
        return mob.getTarget()!= null
                && Computer.angle(mob.getForward(),mob.getTarget().position().subtract(mob.position()))<0.2;
    }

    public void start() {
        internalTick = _internalTick;
    }

    public void tick() {
        if(--internalTick<0){
            internalTick = _internalTick;

            Projectile arrow = projectile.get();
            arrow.setOwner(mob);
            Vec3 dir = mob.getTarget().position().add(0, 1, 0).subtract(mob.position());
            arrow.shoot(dir.x, dir.y, dir.z, 2F, 1.0F);
            level.addFreshEntity(arrow);

        }
    }
}
