package rhymestudio.rhyme.mixin;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rhymestudio.rhyme.mixinauxiliary.ILivingEntity;

import java.util.Objects;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements ILivingEntity {
    @Unique
    public int frozenTime;
    public int rhyme$getFrozenTime() {
        return frozenTime;
    }
    public void rhyme$setFrozenTime(int frozenTime) {
        this.frozenTime = frozenTime;
        ((LivingEntity)(Object)this).getEntityData().set(DATA_FROZEN_TIME, frozenTime);
    }

    @Unique
    private static final EntityDataAccessor<Integer> DATA_FROZEN_TIME = SynchedEntityData.defineId(LivingEntity.class, EntityDataSerializers.INT);


    @Inject(method = "defineSynchedData", at = @At("RETURN"))
    protected void defineSynchedDataMixin(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(DATA_FROZEN_TIME, 0);

    }
    @Inject(method = "onSyncedDataUpdated", at = @At("RETURN"))
    public void onSyncedDataUpdated(EntityDataAccessor<?> key, CallbackInfo ci) {
        if(Objects.equals(key, DATA_FROZEN_TIME)){
            frozenTime = ((LivingEntity)(Object)this).getEntityData().get(DATA_FROZEN_TIME);
        }

    }
    @Inject(method = "tick", at = @At("HEAD"))
    public void tickMixin(CallbackInfo ci) {
        if (frozenTime > 0) {
            frozenTime--;
        }
    }


}
