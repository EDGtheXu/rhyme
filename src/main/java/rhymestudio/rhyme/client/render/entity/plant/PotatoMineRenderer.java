package rhymestudio.rhyme.client.render.entity.plant;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.client.model.PotatoMineOnModel;
import rhymestudio.rhyme.client.model.PotatoMineUnderModel;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.plants.PotatoMine;

import static net.minecraft.client.renderer.entity.EntityRenderDispatcher.renderShadow;

public class PotatoMineRenderer extends MobRenderer<PotatoMine, HierarchicalModel<PotatoMine>> {
    private PotatoMineUnderModel model1;
    private PotatoMineOnModel model2;
    private float shadow=0;
    public PotatoMineRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager,new PotatoMineUnderModel(renderManager.bakeLayer(PotatoMineUnderModel.LAYER_LOCATION)), 0);
        this.model2 = new PotatoMineOnModel(renderManager.bakeLayer(PotatoMineOnModel.LAYER_LOCATION));
        model1 = new PotatoMineUnderModel(renderManager.bakeLayer(PotatoMineUnderModel.LAYER_LOCATION));

    }

    public void render(PotatoMine entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        String pose = entity.getEntityData().get(AbstractPlant.DATA_CAFE_POSE_NAME);
        int time =  entity.getEntityData().get(AbstractPlant.DATA_SKILL_TICK);
        this.shadowRadius =0;
        if(!pose.equals("idle") && !pose.equals("up")){
            this.model= model2;
        }else{
            this.model= model1;
            if(pose.equals("up")){
                if(time > 10) shadow = time/60.0f;
                poseStack.translate(0,time/90.0,0);
            }else shadow = 0;
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

        if(pose.equals("up")){
            poseStack.translate(0,-time/90.0,0);
            if (shadow > 0.0F) {
                renderShadow(poseStack, bufferSource, entity, 1, partialTick, entity.level(), Math.min(shadow, 32.0F));
            }
            poseStack.translate(0,time/90.0,0);
        }else if(pose.equals("idle_on") || pose.equals("bomb")){
            renderShadow(poseStack, bufferSource, entity, 1, partialTick, entity.level(), Math.min(shadow, 32.0F));
        }

    }

    @Override
    public ResourceLocation getTextureLocation(PotatoMine abstractPlant) {
        String s = "textures/entity/"+abstractPlant.namePath+".png";
        return Rhyme.space(s);
    }

}
