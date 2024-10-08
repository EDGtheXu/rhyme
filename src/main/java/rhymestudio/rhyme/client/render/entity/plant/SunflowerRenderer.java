package rhymestudio.rhyme.client.render.entity.plant;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.entity.plants.SunFlower;

public class SunflowerRenderer extends EntityRenderer<SunFlower> {


    public SunflowerRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(SunFlower p_114482_) {
        return Rhyme.space("textures/entity/sun_flower.png");
    }


    public void render(@NotNull SunFlower pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.scale(getScale(), getScale(), getScale());
        pPoseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        PoseStack.Pose posestack$pose = pPoseStack.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        VertexConsumer vertexconsumer = pBuffer.getBuffer(getRenderType());
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 0.0F, 1, 0, 0);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    protected RenderType getRenderType(){
        return RenderType.entityCutoutNoCull(getTextureLocation(null));
    }

    public float getScale(){
        return 1f;
    }

    protected static void vertex(
            VertexConsumer pConsumer, Matrix4f pPose, Matrix3f pNormal, int pLightmapUV, float pX, int pY, int pU, int pV
    ) {
        pConsumer.vertex(pPose, pX - 0.5F, (float)pY - 0.25F, 0.0F)
                .color(255, 255, 255, 255)
                .uv((float)pU, (float)pV)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(pLightmapUV)
                .normal(0.0F, 1.0F, 0.0F)
                .endVertex();
        ;
    }

}
