package rhymestudio.rhyme.entity.plants;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.ai.CircleSkill;

import java.util.Optional;

public class PotatoMine extends AbstractPlant {

    private final AnimationDefinition idle;
    private final AnimationDefinition up;
    private final AnimationDefinition idle_on;
    private final AnimationDefinition bomb;
    private int readyTime;

    public PotatoMine(EntityType<? extends AbstractPlant> type, Level level,
                      AnimationDefinition idle,
                      AnimationDefinition up,
                      AnimationDefinition idle_on,
                      AnimationDefinition bomb,
                      int readyTick, Builder builder) {
        super(type, level);
        this.builder = builder;
        this.readyTime = readyTick;
        this.idle = idle;
        this.up = up;
        this.idle_on = idle_on;
        this.bomb = bomb;
    }


    public void cafeDefineAnimations(){
        super.addSkills();
        this.animState.addAnimation("idle", idle,1);
        this.animState.addAnimation("up", up,1);
        this.animState.addAnimation("idle_on", idle_on,1);
        this.animState.addAnimation("bomb", bomb,1);
    }

    @Override
    public void addSkills() {
        super.addSkills();
        CircleSkill idle = new CircleSkill( "idle",  readyTime, 0);
        CircleSkill on = new CircleSkill( "up",  40, 0);
        CircleSkill up = new CircleSkill( "idle_on",  999999999, 0,
                a->{},
                a-> {
                    if(readyTime<=0){
                        if (!level().getEntities(this, this.getBoundingBox().inflate(0.2f)).isEmpty()) {
                            skills.forceEnd();
                        }
                    }
                },
                a->{}
        );
        CircleSkill boom = new CircleSkill( "bomb",  999999999, 20,
                a->{},
                a-> {
                    if(skills.canTrigger()){
                        this.discard();
                        if (!this.level().isClientSide) {
                            this.explode();
                        }
                    }
                },
                a->{}
        );
        this.addSkill(idle);
        this.addSkill(on);
        this.addSkill(up);
        this.addSkill(boom);
    }
    protected void explode() {
        float f = 4.0F;
        this.level().explode(this, Explosion.getDefaultDamageSource(this.level(), this), USED_PORTAL_DAMAGE_CALCULATOR , this.getX(), this.getY(0.0625), this.getZ(), 4.0F, false, Level.ExplosionInteraction.TNT);
    }

    private static final ExplosionDamageCalculator USED_PORTAL_DAMAGE_CALCULATOR = new ExplosionDamageCalculator() {
        @Override
        public boolean shouldBlockExplode(Explosion p_353087_, BlockGetter p_353096_, BlockPos p_353092_, BlockState p_353086_, float p_353094_) {
            return p_353086_.is(Blocks.NETHER_PORTAL) ? false : super.shouldBlockExplode(p_353087_, p_353096_, p_353092_, p_353086_, p_353094_);
        }

        @Override
        public Optional<Float> getBlockExplosionResistance(
                Explosion p_353090_, BlockGetter p_353088_, BlockPos p_353091_, BlockState p_353093_, FluidState p_353095_
        ) {
            return p_353093_.is(Blocks.NETHER_PORTAL)
                    ? Optional.empty()
                    : super.getBlockExplosionResistance(p_353090_, p_353088_, p_353091_, p_353093_, p_353095_);
        }
    };
    @Override
    public void tick() {
        super.tick();
        if(this.readyTime > 0){
            this.readyTime--;
        }

    }
    public boolean canBeSeenAsEnemy(){
        return false;
    }
}
