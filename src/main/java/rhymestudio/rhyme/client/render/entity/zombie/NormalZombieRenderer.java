package rhymestudio.rhyme.client.render.entity.zombie;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.client.model.layerModels.ZombieArmorLayer;
import rhymestudio.rhyme.client.model.zombieModels.NormalZombieModel;
import rhymestudio.rhyme.core.entity.AbstractMonster;


public class NormalZombieRenderer<T extends AbstractMonster, M extends NormalZombieModel<T>> extends MobRenderer<T, M> {
    private boolean rotY;
    private float scale;
    public NormalZombieRenderer(EntityRendererProvider.Context renderManager, M model) {
        this(renderManager, model,0.3f,1);
    }

    public NormalZombieRenderer(EntityRendererProvider.Context renderManager, M model, float shadowRadius, float scale) {
        this(renderManager, model,shadowRadius,scale,false);
    }

    public NormalZombieRenderer(EntityRendererProvider.Context context, M model, float shadowRadius, float scale, boolean rotY) {
        super(context, model,shadowRadius);
        this.rotY = rotY;
        this.scale = scale;

        this.addLayer(new ZombieArmorLayer<>(this,
                new HumanoidArmorModel<>(context.bakeLayer(ModelLayers.ZOMBIE)),
//                new HumanoidArmorModel<>(context.bakeLayer(ModelLayers.ZOMBIE)),
                context.getModelManager()
        ));

//        this.addLayer(new CustomHeadLayer(this, context.getModelSet(), scale, scale, scale, context.getItemInHandRenderer()));
//        this.addLayer(new ElytraLayer(this, context.getModelSet()));
//        this.addLayer(new ItemInHandLayer(this, context.getItemInHandRenderer()));
    }

    protected void setupRotations(T entity, PoseStack poseStack, float bob, float yBodyRot, float partialTick, float scale) {
        if(rotY) poseStack.mulPose(Axis.YP.rotationDegrees(-90));
        poseStack.scale(this.scale, this.scale, this.scale);
        super.setupRotations(entity, poseStack, bob, yBodyRot, partialTick, scale);
    }
    public void render(T entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
/*
        ItemStack helmet = entity.getItemBySlot(EquipmentSlot.HEAD);
        if(!helmet.isEmpty())
        {
            int pack = OverlayTexture.pack((int) (partialTick*Math.sin(partialTick/10)), (int) (partialTick*Math.cos(partialTick/10)));
            float s = 0.6f;
            poseStack.pushPose();
            poseStack.scale(s,s,s);

            float roty = Mth.lerp(partialTick, entity.yBodyRotO, entity.yBodyRot);
            poseStack.mulPose(Axis.YP.rotationDegrees(-roty + 180));

            poseStack.translate(0,2.8,0);
            Minecraft.getInstance().getItemRenderer().renderStatic(helmet,ItemDisplayContext.HEAD,packedLight,pack,poseStack,buffer,entity.level(),0);

            poseStack.popPose();
        }
        */

        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }


        @Override
    public ResourceLocation getTextureLocation(T entity) {
        String s = "textures/entity/zombies/"+entity.getNamePath()+".png";
        return Rhyme.space(s);
    }
/*
    @Nullable
    protected RenderType getRenderType(T livingEntity, boolean bodyVisible, boolean translucent, boolean glowing) {
        ResourceLocation resourcelocation = this.getTextureLocation(livingEntity);
        RenderType.CompositeRenderType rendertype = (RenderType.CompositeRenderType) ModRenderTypes.cthRenderType(resourcelocation);
        ShaderInstance shader = rendertype.state.shaderState.shader.get().get();
//        RenderSystem._setShaderTexture(3, Minecraft.getInstance().getMainRenderTarget().getColorTextureId());
//        ((IShaderInstance)shader).getRhyme$TEST().set((float) Math.sin(livingEntity.tickCount / 10f) * 0.2f + 0.8f);
        ((IShaderInstance)shader).getRhyme$TEST().set(0.5F);
        return rendertype;
    }
    */
}
