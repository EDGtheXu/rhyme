package rhymestudio.rhyme.entity.proj;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import rhymestudio.rhyme.entity.BaseProj;
import rhymestudio.rhyme.registry.ModEntities;

public class LineProj extends BaseProj {
    private int damage;
    private int existTick;

    @Override
    public float damage() {
        return damage;
    }
    @Override
    public int waveDur() {
        return existTick;
    }
/*
    public LineProj(EntityType<? extends LineProj> pEntityType, Level pLevel){
        super(pEntityType,pLevel,null);
        this.damage = 1000;
        this.existTick = 100;
    }*/

    public LineProj(EntityType<? extends LineProj> pEntityType, Level pLevel,int damage, int existTick, MobEffectInstance pEffect) {
        super(pEntityType,pLevel,pEffect);
        this.damage = damage;
        this.existTick = existTick;
    }

    public LineProj(EntityType<? extends LineProj> pEntityType, Level pLevel,int damage, int existTick) {
        this(pEntityType,pLevel,damage,existTick,null);
    }


    @Override
    protected void onHitBlock(@NotNull BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if(!this.level().isClientSide()) discard();
    }

    @Override
    public void tick(){
        super.tick();
    }

}
