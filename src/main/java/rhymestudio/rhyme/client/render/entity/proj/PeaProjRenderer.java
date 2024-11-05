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
    // 存储我们的模型。
    private final EntityModel<BaseProj> bulletModel;

    public PeaProjRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        bulletModel = new PeaProjModel<>(pContext.bakeLayer(PeaProjModel.LAYER_LOCATION));

    }


    @Override
    public ResourceLocation getTextureLocation(BaseProj pEntity) {
        return ResourceLocation.fromNamespaceAndPath(Rhyme.MODID, "textures/entity/pea_bullet.png");
    }

    @Override
    public void render(BaseProj pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
        //你的渲染应该在posh和pop之间，避免污染其他的渲染。
        pPoseStack.pushPose();
        pPoseStack.scale(1.5F,1.5F,1.5F);
        // 绕y轴旋转45°
        pPoseStack.translate(0,-0.7,0);
        // 构建顶点
        VertexConsumer buffer = pBuffer.getBuffer(this.bulletModel.renderType(this.getTextureLocation(pEntity)));
        // 调用模型的render方法进行渲染，这里的OverlayTexture下有很多类型，自己选用。
        this.bulletModel.renderToBuffer(pPoseStack,buffer,pPackedLight, OverlayTexture.NO_OVERLAY);

        pPoseStack.popPose();
    }

}
