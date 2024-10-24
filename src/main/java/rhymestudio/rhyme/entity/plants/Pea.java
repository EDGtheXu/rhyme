package rhymestudio.rhyme.entity.plants;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.goal.shootGoal;
import rhymestudio.rhyme.registry.ModEntities;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class Pea extends AbstractPlant {
    static final int internalTime = 1*20;


    public Pea(EntityType<? extends Pea> tEntityType, Level level) {
        super(tEntityType, level);
    }

    public Pea(Vec3 pos, Level level) {
        super(ModEntities.PEA_SHOOTER.get(), level);
        this.setPos(pos);
    }

    public void registerGoals(){
//        this.goalSelector.addGoal(0, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(0,new shootGoal(this, internalTime,()->new Arrow(level(), position().x, position().y+1, position().z)));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Monster.class, 20.0F));


        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Monster.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Slime.class, true));
    }

    public void tick() {
        super.tick();
    }

    RawAnimation idle = RawAnimation.begin().thenPlay("idle_eyes_closed_1")
            .thenPlay("idle_eyes_closed_2").thenPlay("idle_eyes_closed_3").thenPlay("shoot")
            ;


    RawAnimation eye = RawAnimation.begin();
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controller) {
        controller.add(new AnimationController<GeoAnimatable>(this,"body",20,state->{
            state.setAnimation(idle);

            return PlayState.CONTINUE;
        }));
    }


}
