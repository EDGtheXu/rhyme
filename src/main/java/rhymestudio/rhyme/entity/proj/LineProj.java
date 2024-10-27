package rhymestudio.rhyme.entity.proj;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import rhymestudio.rhyme.entity.BaseProj;
import rhymestudio.rhyme.registry.ModEntities;

public class LineProj extends BaseProj {
    private float damage;
    private float speed;

    @Override
    public float damage() {
        return 10;
    }
    @Override
    public float waveDur() {
        return 2000;
    }


    public LineProj(EntityType<LineProj> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public LineProj(Entity pOwner,int damage, float speed) {
        super(ModEntities.PEA_PROJ.get(),pOwner.level());
        this.setOwner(pOwner);
        this.damage = damage;
        this.speed = speed;
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
