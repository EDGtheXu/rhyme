package rhymestudio.rhyme.client.render.entity.proj;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.client.model.proj.PeaProjModel;
import rhymestudio.rhyme.entity.BaseProj;

public class PeaProjRenderer extends EntityRenderer<BaseProj> {
    private EntityModel<BaseProj> model;

    private final EntityModel<BaseProj> bulletModel;

    public PeaProjRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);

        bulletModel = new PeaProjModel<>(pContext.bakeLayer(PeaProjModel.LAYER_LOCATION));

    }


    @Override
    public ResourceLocation getTextureLocation(BaseProj pEntity) {
        if (pEntity.getTexture() == null) return Rhyme.space("textures/entity/pea_bullet.png");
        return pEntity.getTexture();
    }

    @Override
    public void render(BaseProj pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
        pPoseStack.pushPose();
        pPoseStack.scale(1.5F,1.5F,1.5F);
        pPoseStack.translate(0,-0.7,0);
        VertexConsumer buffer = pBuffer.getBuffer(this.bulletModel.renderType(this.getTextureLocation(pEntity)));
        this.bulletModel.renderToBuffer(pPoseStack,buffer,pPackedLight, OverlayTexture.NO_OVERLAY);
        pPoseStack.popPose();
    }

}
