package rhymestudio.rhyme.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.plants.SunFlower;

import java.util.Map;

public class BasePlantRenderer<T extends AbstractPlant> extends EntityRenderer<T> {

    public BasePlantRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(T  entity) {
        return entity.getTexture();
    }

    public void render(@NotNull T  pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.scale(getScale(), getScale(), getScale());
        pPoseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        PoseStack.Pose posestack$pose = pPoseStack.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        VertexConsumer vertexconsumer = pBuffer.getBuffer(getRenderType(pEntity));
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 0.0F, 1, 0, 0);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    protected RenderType getRenderType(T entity){
        return RenderType.entityCutoutNoCull(getTextureLocation(entity));
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
