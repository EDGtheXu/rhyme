package rhymestudio.rhyme.entity;


import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rhymestudio.rhyme.Rhyme;

public abstract class BaseProj extends AbstractHurtingProjectile{

    //客户端数据交互
    private final long starttime = System.currentTimeMillis();
    public float damage;
    private MobEffectInstance effect;


    //默认构造方法
    public BaseProj(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel, MobEffectInstance pEffect) {
        super(pEntityType, pLevel);
        this.effect = pEffect;
    }

    @Override
    public void tick() {
        if(!level().isClientSide){
            if(System.currentTimeMillis()-starttime > waveDur() * 50L) discard();
            //包围盒检测造成伤害
            var entities=level().getEntities(this,this.getBoundingBox());
            if(!entities.isEmpty()){
                for (var e:entities) {
                    if(canHitEntity(e)) {
                        e.hurt(this.damageSources().mobProjectile(this, getOwner() instanceof LivingEntity livingentity ? livingentity : null), damage());

                        discard();
                        break;
                    }
                }
            }
        }
        super.tick();
    }

    public abstract int waveDur();
    public float damage() {return damage;}
    //弹幕设置
    @Override//取消射击惯性
    public void shootFromRotation(Entity pShooter, float pX, float pY, float pZ, float pVelocity, float pInaccuracy) {
        float f = -Mth.sin(pY * (float) (Math.PI / 180.0)) * Mth.cos(pX * (float) (Math.PI / 180.0));
        float f1 = -Mth.sin((pX + pZ) * (float) (Math.PI / 180.0));
        float f2 = Mth.cos(pY * (float) (Math.PI / 180.0)) * Mth.cos(pX * (float) (Math.PI / 180.0));
        this.shoot(f, f1, f2, pVelocity, pInaccuracy);
    }

    public void onAddedToLevel(){
        super.onAddedToLevel();
        if(!level().isClientSide()){
            this.damage = (int) ((LivingEntity)getOwner()).getAttribute(Attributes.ATTACK_DAMAGE).getValue();
        }
    }
    @Override
    protected void onHitEntity(@NotNull EntityHitResult pResult) {

        if(!this.level().isClientSide()) {
            Entity entity1 = pResult.getEntity();
            Entity entity = this.getOwner();
            if(effect!= null && entity1 != entity && entity1 instanceof LivingEntity living){
                living.addEffect(effect);
            }
            entity1.hurt(this.damageSources().mobProjectile(this, entity instanceof LivingEntity livingentity ? livingentity : null), damage());
            this.discard();

        }
        super.onHitEntity(pResult);
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Nullable
    @Override//设置粒子效果
    protected ParticleOptions getTrailParticle() {
        return null;
    }

    @Override
    protected boolean canHitEntity(@NotNull Entity pTarget) {
        return  pTarget != getOwner() &&
                !(pTarget instanceof AbstractPlant) &&
                !(pTarget instanceof Player) &&
                super.canHitEntity(pTarget);
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
    @Override
    protected void onHitBlock(@NotNull BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if(!this.level().isClientSide()) discard();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);


    }



    public ResourceLocation texture;
    public void setTexture(ResourceLocation texture){this.texture = texture;}
    public ResourceLocation getTexture(){return texture;}
    public static class TextureLib{
        public static final ResourceLocation PEA = Rhyme.space("textures/entity/pea_bullet.png");
        public static final ResourceLocation ICE_PEA = Rhyme.space("textures/entity/ice_pea_bullet.png");
        public static final ResourceLocation FIRE_PEA = Rhyme.space("textures/entity/fire_pea_bullet.png");


    }

}
