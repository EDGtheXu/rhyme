package rhymestudio.rhyme.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.entity.anim.CafeAnimationState;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class AbstractMonster extends Monster {
    public String namePath;
    private int attackInternal = 0;
    private int _attackInternal = 20;
    public Builder builder;
    public CafeAnimationState animState = new CafeAnimationState();
    public AbstractMonster(EntityType<? extends Monster> type, Level level, Builder builder) {
        super(type, level);
        this.builder = builder;
        if (!level.isClientSide)
            this.registerGoals();
        else{
            this.namePath = getName().getString().split("\\.")[2];
        }
        this.navigation = createNavigation(level);
        this.setDiscardFriction(builder.noFriction);
        this.builder.animation.accept(animState);
        this.animState.playAnim("idle",0);

        this.setHealth(builder.MAX_HEALTH);
        this.getAttribute(Attributes.ARMOR).setBaseValue(builder.ARMOR);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(builder.ATTACK_DAMAGE);
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(builder.MOVEMENT_SPEED);
        this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(builder.FOLLOW_RANGE);
        this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).setBaseValue(builder.SPAWN_REINFORCEMENTS_CHANCE);
        this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(builder.KNOCKBACK_RESISTANCE);
        this.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(builder.ATTACK_KNOCKBACK);
        this.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(builder.ATTACK_SPEED);
        this.getAttribute(Attributes.FLYING_SPEED).setBaseValue(builder.FLYING_SPEED);
        this.getAttribute(Attributes.SAFE_FALL_DISTANCE).setBaseValue(builder.SAFE_FALL);
        this.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(builder.JUMP_STRENGTH);
        this.getAttribute(Attributes.STEP_HEIGHT).setBaseValue(builder.STEP_HEIGHT);

    }

    public static final EntityDataAccessor<String> DATA_CAFE_POSE_NAME = SynchedEntityData.defineId(AbstractMonster.class, EntityDataSerializers.STRING);

    // 动画数据同步
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_CAFE_POSE_NAME, "idle");
    }


    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (this.level().isClientSide() && DATA_CAFE_POSE_NAME.equals(key)) {
            this.animState.resetAnim();
            String name = entityData.get(DATA_CAFE_POSE_NAME);
            this.animState.playAnim(name,this.tickCount);
        }
        super.onSyncedDataUpdated(key);
    }


    protected void registerGoals() {
        if(builder!= null) {
            super.registerGoals();
            builder.goals.forEach(g->g.accept(goalSelector,this));
            builder.targets.forEach(t->t.accept(targetSelector,this));
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE)
                .add(Attributes.MAX_HEALTH)
                .add(Attributes.ARMOR)
                .add(Attributes.MOVEMENT_SPEED)
                .add(Attributes.FOLLOW_RANGE)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE)
                .add(Attributes.KNOCKBACK_RESISTANCE)
                .add(Attributes.ATTACK_KNOCKBACK)
                .add(Attributes.ATTACK_SPEED)
                .add(Attributes.FLYING_SPEED)
                ;
    }

    public void aiStep() {
        super.aiStep();

        if(!level().isClientSide) {
            if (navigation.getPath() != null) {
                if (!animState.curName.equals("walk")) {
                    this.animState.playAnim("walk", this.tickCount);
                    this.entityData.set(DATA_CAFE_POSE_NAME, "walk");
                }

            } else if (navigation.getPath() == null) {
                if (!animState.curName.equals("idle")) {
                    this.animState.playAnim("idle", this.tickCount);
                    this.entityData.set(DATA_CAFE_POSE_NAME, "idle");
                }

            }
        }
    }
    /*
    public static boolean checkBloodCrawlerSpawn(EntityType<? extends CrimsonKemera> type, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        if (!(pLevel instanceof Level level)) {
            return false; // 如果 pLevel 不是 Level 的实例，返回 false
        }

        if (!checkMobSpawnRules(type, pLevel, pSpawnType, pPos, pRandom)) {
            return false; // 如果不满足基本生成规则，返回 false
        }

        int y = pPos.getY();
        if (y >= 260) {
            return false; // 不能生成在 y = 260 或更高的位置
        }

        return true;
    }*/
    @Override
    protected SoundEvent getDeathSound() {
        if(builder.deathSound == null) return super.getDeathSound();
        return builder.deathSound.get();
    }
    @Override
    protected SoundEvent getAmbientSound() {
        if(builder.ambientSound == null) return super.getAmbientSound();
        return builder.ambientSound.get();
    }
    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        if(builder.hurtSound == null) return super.getHurtSound(pDamageSource);
        return builder.hurtSound.get();
    }


    @Override
    protected PathNavigation createNavigation(Level level) {
        if(builder != null && builder.navigation != null)
            return builder.navigation.apply(this);
        return super.createNavigation(level);
    }

    public boolean isNoGravity() {
        if(builder == null)return true;
        return builder.noGravity;
    }

    public void tick(){
        super.tick();
    }

    public boolean canAttack(LivingEntity entity) {
        return attackInternal < 0 &&
                (entity instanceof Player && !entity.isInvulnerable() && !((Player) entity).isCreative()||
                        getTarget() != null && getTarget().is(entity) && entity != this);
    }



    public static class Builder {
        public int ATTACK_DAMAGE = 15;
        public int MAX_HEALTH = 31;
        public int ARMOR = 2;
        public float MOVEMENT_SPEED = 0.38f;
        public int FOLLOW_RANGE = 32;
        public float SPAWN_REINFORCEMENTS_CHANCE = 0.01f;
        public float KNOCKBACK_RESISTANCE = 0.8f;
        public float ATTACK_KNOCKBACK = 0.5f;
        public float ATTACK_SPEED = 0.6f;
        public float FLYING_SPEED = 0.4f;
        public float SAFE_FALL = 5f;
        public float JUMP_STRENGTH = 0.41999998688697815f;
        public float STEP_HEIGHT = 0.6f;
        public boolean attachAttack = true;
        public boolean noGravity = false;
        public boolean noFriction = false;


        public Supplier<SoundEvent> deathSound;
        public Supplier<SoundEvent> ambientSound;
        public Supplier<SoundEvent> hurtSound;

        public List<BiConsumer<GoalSelector,AbstractMonster>> goals = new ArrayList<>();
        public List<BiConsumer<GoalSelector,AbstractMonster>> targets = new ArrayList<>();
        public Function<AbstractMonster,PathNavigation> navigation;
        public Consumer<CafeAnimationState> animation;


        public Builder modify(Function<Builder, Builder> modifier){
            return modifier.apply(this);
        }

        public Builder setAttackDamage(int attackDamage) {
            this.ATTACK_DAMAGE = attackDamage;
            return this;
        }

        public Builder setHealth(int maxHealth) {
            this.MAX_HEALTH = maxHealth;
            return this;
        }

        public Builder setArmor(int defense) {
            this.ARMOR = defense;
            return this;
        }
        public Builder setMovementSpeed(float movementSpeed) {
            this.MOVEMENT_SPEED = movementSpeed;
            return this;
        }

        public Builder setFollowRange(int followRange) {
            this.FOLLOW_RANGE = followRange;
            return this;
        }

        public Builder setKnockbackResistance(float knockbackResistance) {
            this.KNOCKBACK_RESISTANCE = knockbackResistance;
            return this;
        }

        public Builder setDeathSound(Supplier<SoundEvent> deathSound) {
            this.deathSound = deathSound;
            return this;
        }

        public Builder setAmbientSound(Supplier<SoundEvent> ambientSound) {
            this.ambientSound = ambientSound;
            return this;
        }

        public Builder setHurtSound(Supplier<SoundEvent> hurtSound) {
            this.hurtSound = hurtSound;
            return this;
        }


        public Builder addGoal(BiConsumer<GoalSelector,AbstractMonster> goal) {
            this.goals.add(goal) ;
            return this;
        }

        public Builder addTarget(BiConsumer<GoalSelector,AbstractMonster> target) {
            this.targets.add(target);
            return this;
        }

        public Builder setNavigation(Function<AbstractMonster,PathNavigation> navigation) {
            this.navigation = navigation;
            return this;
        }

        public Builder setNoGravity() {
            this.noGravity = true;
            return this;
        }
        public Builder setKnockBack(float knockBack) {
            this.ATTACK_KNOCKBACK = knockBack;
            return this;
        }

        public Builder setSafeFall(float value) {
            this.SAFE_FALL = value;
            return this;
        }
        public Builder setNoAttackAttack() {
            this.attachAttack = false;
            return this;
        }
        public Builder setNoFriction() {
            this.noFriction = true;
            return this;
        }
        public Builder setJumpStrength(float jumpStrength) {
            this.JUMP_STRENGTH = jumpStrength;
            return this;
        }
        public Builder setStepHeight(float stepHeight) {
            this.STEP_HEIGHT = stepHeight;
            return this;
        }
        public Builder addAnimation(Consumer<CafeAnimationState> state){
            this.animation = state;
            return this;
        }
    }



    public static Builder copyFrom(Supplier<Builder> supplier) {
        return supplier.get();
    }


}