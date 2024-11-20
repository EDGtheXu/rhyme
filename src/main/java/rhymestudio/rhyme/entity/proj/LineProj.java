package rhymestudio.rhyme.entity.proj;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import rhymestudio.rhyme.entity.BaseProj;

public class LineProj extends BaseProj {
    private int existTick;

    @Override
    public int waveDur() {
        return existTick;
    }

    public LineProj(EntityType<? extends LineProj> pEntityType, Level pLevel,int existTick, MobEffectInstance pEffect, ResourceLocation texture) {
        super(pEntityType,pLevel,pEffect);
        this.existTick = existTick;
        this.texture = texture;
    }

    public LineProj(EntityType<? extends LineProj> pEntityType, Level pLevel,int existTick, MobEffectInstance pEffect) {
        this(pEntityType,pLevel,existTick,pEffect,null);
        this.existTick = existTick;
    }

    public LineProj(EntityType<? extends LineProj> pEntityType, Level pLevel, int existTick) {
        this(pEntityType,pLevel,existTick,null);
    }


    @Override
    public void tick(){
        super.tick();
    }
}
