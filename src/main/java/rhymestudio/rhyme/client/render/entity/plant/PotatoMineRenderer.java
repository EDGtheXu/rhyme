package rhymestudio.rhyme.client.render.entity.plant;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import rhymestudio.rhyme.Rhyme;

import rhymestudio.rhyme.client.model.PotatoMineModel;
import rhymestudio.rhyme.client.render.entity.BasePlantRenderer;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.plants.PotatoMine;

import static net.minecraft.client.renderer.entity.EntityRenderDispatcher.renderShadow;

public class PotatoMineRenderer extends BasePlantRenderer<PotatoMine, PotatoMineModel> {

    private float shadow=0;
    private float offset=0;
    public PotatoMineRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager,new PotatoMineModel(renderManager.bakeLayer(PotatoMineModel.LAYER_LOCATION)), 0,false);
    }

    public void render(PotatoMine entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        String pose = entity.getEntityData().get(AbstractPlant.DATA_CAFE_POSE_NAME);
        int time =  entity.getEntityData().get(AbstractPlant.DATA_SKILL_TICK);

        if(!pose.equals("idle") && !pose.equals("up")){
//            this.offset =30/90.0f;
            poseStack.translate(0,offset,0);
        }else{
            if(pose.equals("up")){

                this.shadow = time/60.0f;
                this.offset = time/90.0f;

                poseStack.translate(0,offset,0);
            }else this.shadow = 0;
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);


        poseStack.translate(0,-this.offset,0);
        if (this.shadow > 0.0F) {
            renderShadow(poseStack, bufferSource, entity, 1, partialTick, entity.level(), Math.min(this.shadow, 32.0F));
        }
        poseStack.translate(0,this.offset,0);


    }

    @Override
    public ResourceLocation getTextureLocation(PotatoMine abstractPlant) {
        String s = "textures/entity/"+abstractPlant.namePath+".png";
        return Rhyme.space(s);
    }

}
