package rhymestudio.rhyme.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;
import rhymestudio.rhyme.entity.ai.CircleSkills;
import rhymestudio.rhyme.entity.ai.CircleSkill;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPlant extends Mob implements GeoEntity {
    /*
    public AbstractPlant(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }*/
    public String namePath;
    public Player owner;
    public void setOwner(Player player) {
        this.owner = player;
    }

    public <T extends AbstractPlant> AbstractPlant(EntityType<T> tEntityType, Level level) {
        super(tEntityType, level);
        this.namePath = getName().getString().split("\\.")[2];

    }
    public abstract void addSkills();

    public void registerGoals(){
        //this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, LivingEntity.class, 20.0F));

        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10,true,true, this::canAttack));
        //this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Slime.class, true));
    }
    public void onAddedToLevel(){
        super.onAddedToLevel();
        this.addSkills();
    }

    @Override
    public boolean hurt(DamageSource source, float damage) {
        if(source.getEntity() instanceof Player || source.getEntity() instanceof AbstractPlant){
            return false;
        }
        return super.hurt(source, damage);
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        if(target.isAlive() && (
                target instanceof Monster
                        || target instanceof Slime
                        || (target instanceof NeutralMob  && !(target instanceof IronGolem))
                )
        ) return true;
        if(target instanceof AbstractPlant || target instanceof Player)return false;
        return false;
    }

    public void tick(){
        if (level().isClientSide) {
            skills.index = this.entityData.get(DATA_SKILL_INDEX);
            skills.tick = this.entityData.get(DATA_SKILL_TICK);

        } else {
            skills.tick();
            this.entityData.set(DATA_SKILL_INDEX, skills.index);
            this.entityData.set(DATA_SKILL_TICK, skills.tick);
        }
        super.tick();

        if(this.getTarget()!=null && getTarget().isAlive()){
            this.lookControl.setLookAt(getTarget());
        }
    }
    public void addSkill(CircleSkill bossSkill, RawAnimation anim) {
        this.skills.pushSkill(bossSkill);
        skillMap.put(bossSkill.skill, anim);
    }
    public void addSkillNoAnim(CircleSkill bossSkill) {
        this.skills.pushSkill(bossSkill);
    }


    public CircleSkills skills = new CircleSkills(this);
    private final Map<String, RawAnimation> skillMap = new HashMap<>();
    private int lastAnimIndex = -1;
    private static final EntityDataAccessor<Integer> DATA_SKILL_INDEX = SynchedEntityData.defineId(AbstractPlant.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_SKILL_TICK = SynchedEntityData.defineId(AbstractPlant.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Vector3f> DATA_ROTATE = SynchedEntityData.defineId(AbstractPlant.class, EntityDataSerializers.VECTOR3);
    // 动画数据同步
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_SKILL_INDEX, 0);
        builder.define(DATA_SKILL_TICK, 0);
        builder.define(DATA_ROTATE, new Vector3f());
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, 20, state -> {
            AbstractPlant entity = (AbstractPlant) state.getData(DataTickets.ENTITY);
            if (!entity.isAlive()) return PlayState.STOP;
            if (skills.count() == 0) return PlayState.STOP;
            String skillString = entity.skills.getCurSkill();
            if (skillString == null) return PlayState.STOP;
            RawAnimation skill = skillMap.get(skillString);

            if (skill != null) {
                state.setAnimation(skill);
                if (lastAnimIndex != skills.index) {
                    lastAnimIndex = skills.index;
                    state.resetCurrentAnimation();
                    return PlayState.STOP;
                }
                if(state.getController().hasAnimationFinished())
                    state.resetCurrentAnimation();
                return PlayState.CONTINUE;
            }
            return PlayState.STOP;
        }).setAnimationSpeed(2f));
    }



    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
    public boolean canBeCollidedWith(){return false;}
    public boolean canCollideWith(Entity entity){return false;}
}
