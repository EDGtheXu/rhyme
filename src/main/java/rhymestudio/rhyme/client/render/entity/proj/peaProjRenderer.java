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
import rhymestudio.rhyme.client.model.peaProjModel;
import rhymestudio.rhyme.entity.BaseProj;

public class peaProjRenderer extends EntityRenderer<BaseProj> {
    // 存储我们的模型。
    private final EntityModel<BaseProj> flyingSwordModel;

    public peaProjRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        //这里使用pContext.bakeLayer(FlyingSwordModel.LAYER_LOCATION)来准备ModelPart，这里的获得ModelPart是等会我们需要去注册的，通过LAYER_LOCATION注册我们的ModelPart
        flyingSwordModel = new peaProjModel<>(pContext.bakeLayer(peaProjModel.LAYER_LOCATION));

    }


    @Override
    public ResourceLocation getTextureLocation(BaseProj pEntity) {
        return ResourceLocation.fromNamespaceAndPath(Rhyme.MODID, "textures/entity/wave/tai_la_ren_wave_model_entity.png");
    }

    @Override
    public void render(BaseProj pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
        //你的渲染应该在posh和pop之间，避免污染其他的渲染。
        pPoseStack.pushPose();
        // 绕y轴旋转45°

        // 构建顶点
        VertexConsumer buffer = pBuffer.getBuffer(this.flyingSwordModel.renderType(this.getTextureLocation(pEntity)));
        // 调用模型的render方法进行渲染，这里的OverlayTexture下有很多类型，自己选用。
        this.flyingSwordModel.renderToBuffer(pPoseStack,buffer,pPackedLight, OverlayTexture.NO_OVERLAY);

        pPoseStack.popPose();
    }

}
