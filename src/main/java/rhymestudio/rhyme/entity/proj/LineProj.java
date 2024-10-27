package rhymestudio.rhyme.entity.proj;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import rhymestudio.rhyme.client.render.entity.BaseProj;
import rhymestudio.rhyme.registry.ModEntities;

public class LineProj extends BaseProj {

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

    public LineProj(Entity pOwner, Level pLevel) {
        super(ModEntities.PEA_PROJ.get(),pLevel);
        this.setOwner(pOwner);
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
