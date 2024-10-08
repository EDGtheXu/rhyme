package rhymestudio.rhyme.entity.goal;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import rhymestudio.rhyme.utils.computer;

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
                && computer.angle(mob.getForward(),mob.getTarget().position().subtract(mob.position()))<0.2;
    }

    public void start() {
        internalTick = _internalTick;
    }

    public void tick() {
        if(--internalTick<0){
            internalTick = _internalTick;

            Projectile arrow = projectile.get();
            arrow.setOwner(mob);
            arrow.shoot(mob.getForward().x, mob.getForward().y, mob.getForward().z, 2F, 1.0F);
            level.addFreshEntity(arrow);

            if(mob.getTarget() == null) return;
        }
    }
}
