package rhymestudio.rhyme.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rhymestudio.rhyme.client.ModRenderTypes;
import rhymestudio.rhyme.client.post.DIYBlitTarget;
import rhymestudio.rhyme.client.post.PostUtil;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {


    @Inject(method = "renderLevel", at = @At("RETURN"))
    public void renderLevelReturnMixin(DeltaTracker deltaTracker, CallbackInfo ci ){

//        PostUtil.bloom.apply();
//        PostUtil.backUp();

    }


}
