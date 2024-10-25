package rhymestudio.rhyme.entity.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class shootGoal extends Goal {


    private int internalTick;
    private final Mob mob;
    private final Level level;
    private final int _internalTick;

    private final Consumer<Mob> recall;

    public shootGoal(Mob mob,
                     int internalTick,

                     Consumer<Mob> recall
    ) {
        this.mob = mob;
        this.level = mob.level();
        this.internalTick = internalTick;
        this._internalTick = internalTick;

        this.recall = recall;
    }

    public boolean canUse() {
        LivingEntity livingentity = mob.getTarget();
        if (livingentity != null && livingentity.isAlive()) {
            return true;
        }
        mob.setTarget(null);
        return false;

    }

    public void start() {
        ;
    }

    public void tick() {
        --this.internalTick;
        LivingEntity livingentity = mob.getTarget();
        if (livingentity != null && livingentity.isAlive()) {
            //mob.getLookControl().setLookAt(livingentity, 180.0F, 180.0F);
            mob.lookAt(livingentity,360,360);
            double d0 = mob.distanceToSqr(livingentity);
            if (d0 < 400.0D) {
                if (this.internalTick <= 0) {
                    this.internalTick = (int) (_internalTick + Math.random() * 5) - 5;
                    recall.accept(mob);
                } else mob.setTarget(null);
                super.tick();
            } else mob.setTarget(null);
        }
    }
}