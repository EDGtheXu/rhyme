package rhymestudio.rhyme.mixin;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {


    @Inject(method = "renderLevel", at = @At("RETURN"))
    public void renderLevelReturnMixin(DeltaTracker deltaTracker, CallbackInfo ci ){

//        PostUtil.bloom.apply();
//        PostUtil.backUp();

    }


}
