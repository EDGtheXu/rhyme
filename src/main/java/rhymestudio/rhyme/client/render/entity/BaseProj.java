package rhymestudio.rhyme.client.render.entity;


import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import rhymestudio.rhyme.registry.ModEntities;

public abstract class BaseProj extends AbstractHurtingProjectile{
    public Vector3f initForward = new Vector3f();
    public abstract float damage();
    public abstract float waveDur();

    //默认构造方法
    public BaseProj(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void tick() {

        if(System.currentTimeMillis()-starttime > waveDur()) discard();
        if(level().isClientSide){
            initForward = this.getEntityData().get(curRot);
        }else{
            //包围盒检测造成伤害
            var entities=level().getEntities(this,this.getBoundingBox());
            if(!entities.isEmpty()){
                for (var e:entities) {
                    if(canHitEntity(e))
                        e.hurt(this.damageSources().mobProjectile(this, getOwner() instanceof LivingEntity livingentity ? livingentity : null), damage());
                }
            }
        }
        super.tick();
    }


    //弹幕设置
    @Override//取消射击惯性
    public void shootFromRotation(Entity pShooter, float pX, float pY, float pZ, float pVelocity, float pInaccuracy) {
        float f = -Mth.sin(pY * (float) (Math.PI / 180.0)) * Mth.cos(pX * (float) (Math.PI / 180.0));
        float f1 = -Mth.sin((pX + pZ) * (float) (Math.PI / 180.0));
        float f2 = Mth.cos(pY * (float) (Math.PI / 180.0)) * Mth.cos(pX * (float) (Math.PI / 180.0));
        this.shoot((double)f, (double)f1, (double)f2, pVelocity, pInaccuracy);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult pResult) {

        if(!this.level().isClientSide()) {
            Entity entity1 = pResult.getEntity();
            Entity entity = this.getOwner();
            entity1.hurt(this.damageSources().mobProjectile(this, entity instanceof LivingEntity livingentity ? livingentity : null), damage());
        }
        super.onHitEntity(pResult);
    }

    @Nullable
    @Override//设置粒子效果
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.DOLPHIN;
    }

    @Override
    protected boolean canHitEntity(@NotNull Entity pTarget) {
        return (pTarget != getOwner()) && super.canHitEntity(pTarget);
    }

    @Override//流体阻力
    protected float getLiquidInertia() {
        return 1;
    }

    @Override//火焰效果
    protected boolean shouldBurn() {
        return false;
    }
    @Override
    public boolean isPickable() {
        return false;
    }

    @Override//空气阻力
    protected float getInertia() {
        return 1;
    }



    //客户端数据交互
    private final long starttime = System.currentTimeMillis();
    private static final EntityDataAccessor<Vector3f> curRot = SynchedEntityData.defineId(BaseProj.class, EntityDataSerializers.VECTOR3);


    //defineSynchedData()：该方法用于定义实体的同步数据，在该方法中，将COUNTER实体数据访问器初始化为0。

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(curRot,new Vector3f());
    }
    public void syncRot(){
        if(!level().isClientSide){
            this.entityData.set(curRot,initForward);
        }
    }
}
