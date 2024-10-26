package rhymestudio.rhyme.entity.plants;

import net.minecraft.client.Minecraft;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.goal.shootGoal;
import rhymestudio.rhyme.registry.ModEntities;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DefaultAnimations;


import java.util.LinkedList;
import java.util.Queue;

public class Pea extends AbstractPlant {
    int lastAttackTime = -20;
    int attackAnimTick = 80;

    public int internalAttackTime = 100;
    public int _internalAttackTime = 100;
    int state = 0;

    Queue<Tuple> attackProps = new LinkedList<>();
    private static final EntityDataAccessor<Integer> DATA_STATE = SynchedEntityData.defineId(Pea.class, EntityDataSerializers.INT);
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_STATE, 0);

    }



    record Tuple(Integer time, Vec3 dir){}

    public Pea(EntityType<? extends Pea> tEntityType, Level level) {
        super(tEntityType, level);
    }

    public Pea(Vec3 pos, Level level) {
        super(ModEntities.PEA_SHOOTER.get(), level);
        this.setPos(pos);
    }

    public void registerGoals(){
//        this.goalSelector.addGoal(0, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(0, new shootGoal(this,
            a->{attackProps.add(new Tuple(this.tickCount, getTarget().getEyePosition()));}));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Monster.class, 20.0F));

        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Monster.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Slime.class, true));
    }

    public void tick() {
        super.tick();
        internalAttackTime--;
        if(!level().isClientSide){
            if(inFiring()) entityData.set(DATA_STATE, 1);
            else entityData.set(DATA_STATE, 0);

            var tp =attackProps.peek();
            if(tp!=null && tp.time-attackAnimTick <= this.tickCount ){
                attackProps.poll();
                doAttack(tp.dir);
                lastAttackTime = tickCount;
            }

        }
    }

    public boolean inFiring() {
        return lastAttackTime >= this.tickCount - attackAnimTick;
    }

    public void doAttack(Vec3 tar){
        Projectile arrow = new Arrow(level(), this, Items.ARROW.getDefaultInstance(), Items.ARROW.getDefaultInstance());
        arrow.setOwner(this);
        Vec3 dir = tar.subtract(getEyePosition());
        arrow.shoot(dir.x, dir.y, dir.z, 2F, 1.0F);
        level().addFreshEntity(arrow);
    }

    RawAnimation idle = RawAnimation.begin()
            .thenPlay("idle_eyes_closed_1")
            .thenPlay("idle_eyes_closed_2")
            .thenLoop("idle_eyes_closed_3")
            ;

    RawAnimation attack = RawAnimation.begin().thenPlay("shoot");

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controller) {

        controller.add(new AnimationController<GeoAnimatable>(this,"body",20, state->{
            int st = entityData.get(DATA_STATE);
            System.out.println(st);
            if(state.isCurrentAnimation(idle)){
                if(st == 1){
                    state.resetCurrentAnimation();
                    state.setAnimation(attack);
                    return PlayState.STOP;
                }
                return PlayState.CONTINUE;

            }
            else if(state.isCurrentAnimation(attack) ){
                if(st == 0){
                    state.resetCurrentAnimation();
                    state.setAnimation(idle);
                    return PlayState.STOP;
                }

                return PlayState.CONTINUE;
            }
            state.setAnimation(idle);
            return PlayState.CONTINUE;
        }));
/*
        controller.add(new AnimationController<GeoAnimatable>(this,"attack",20,state->{
            if(entityData.get(DATA_STATE) == 0) return PlayState.STOP;
            state.setAnimation(attack);
            return PlayState.CONTINUE;
        }));
*/
    }


}
