package rhymestudio.rhyme.client.render.post;

import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import rhymestudio.rhyme.client.ModRenderTypes;

import java.util.function.Consumer;

public class DIYBlitTarget extends TextureTarget {
    public DIYBlitTarget(int pWidth, int pHeight, boolean pUseDepth, boolean pClearError, DIYShaderInstance blitShader) {
        super(pWidth, pHeight, pUseDepth, pClearError);
        this.blitShader = blitShader;
    }
    private boolean autoClear = true;
    private Consumer<DIYShaderInstance> createSampler;
    private DIYShaderInstance blitShader;
    public void blitToScreen(int pWidth, int pHeight, boolean disableBlend) {
        this._blitToScreen(pWidth, pHeight, disableBlend);
    }

    private void _blitToScreen(int pWidth, int pHeight, boolean pDisableBlend) {
        RenderSystem.assertOnRenderThread();
        GlStateManager._colorMask(true, true, true, false);
        GlStateManager._disableDepthTest();
        GlStateManager._depthMask(false);
        GlStateManager._viewport(0, 0, pWidth, pHeight);
        if (pDisableBlend) {
            GlStateManager._disableBlend();
        }

        if(createSampler!= null) createSampler.accept(blitShader);
        if(blitShader.COLOR_MODULATOR!= null) {
            float r = (float) Math.sin(System.currentTimeMillis() * 0.0005)* 0.2f + 0.5f;
            blitShader.COLOR_MODULATOR.set(3f, 0.3f, 0.3f, 1.0f);
            System.out.println(r);
        }
        blitShader.apply();

        RenderSystem.setShader(()->ModRenderTypes.Shaders.diy_blit);
        BufferBuilder bufferbuilder = RenderSystem.renderThreadTesselator().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.BLIT_SCREEN);
        bufferbuilder.addVertex(0.0F, 0.0F, 0.0F);
        bufferbuilder.addVertex(1.0F, 0.0F, 0.0F);
        bufferbuilder.addVertex(1.0F, 1.0F, 0.0F);
        bufferbuilder.addVertex(0.0F, 1.0F, 0.0F);
        BufferUploader.draw(bufferbuilder.buildOrThrow());
//        glActiveTexture(GL_TEXTURE5);
//        glBindTexture(GL_TEXTURE_2D, 0);

        blitShader.clear();
        GlStateManager._depthMask(true);
        GlStateManager._colorMask(true, true, true, true);
    }


    public void setBlitShader(DIYShaderInstance blitShader) {
        this.blitShader = blitShader;
    }
    public DIYShaderInstance getBlitShader() {
        return this.blitShader;
    }
    public void setUniforms(Consumer<UniformsMap> consumer) {
        this.blitShader.setUniforms(consumer);
    }
    public void setCreateSampler(Consumer<DIYShaderInstance> blitShader) {
        this.createSampler = blitShader;
    }
    public void setAutoClear(boolean autoClear) {
        this.autoClear = autoClear;
    }
    public void clear(boolean error){
        super.clear(error);
        if( autoClear && (this.viewHeight!=Minecraft.getInstance().getWindow().getHeight() || this.viewWidth!=Minecraft.getInstance().getWindow().getWidth())){
            this.resize(Minecraft.getInstance().getWindow().getWidth(), Minecraft.getInstance().getWindow().getHeight(),error);
        }
    }
}
