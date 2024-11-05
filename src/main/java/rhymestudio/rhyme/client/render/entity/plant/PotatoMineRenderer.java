package rhymestudio.rhyme.client.render.entity.plant;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.client.model.PotatoMineOnModel;
import rhymestudio.rhyme.client.model.PotatoMineUnderModel;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.plants.PotatoMine;


public class PotatoMineRenderer extends MobRenderer<PotatoMine, HierarchicalModel<PotatoMine>> {
    PotatoMineOnModel model2;
    public PotatoMineRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager,new PotatoMineUnderModel(renderManager.bakeLayer(PotatoMineUnderModel.LAYER_LOCATION)), 0.5f);
        this.model2 = new PotatoMineOnModel(renderManager.bakeLayer(PotatoMineOnModel.LAYER_LOCATION));

    }



    public void render(PotatoMine entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        String pose = entity.getEntityData().get(AbstractPlant.DATA_CAFE_POSE_NAME);
        System.out.println(pose);
        if(!pose.equals("idle") && !pose.equals("up")){
            System.out.println(pose);
            this.model= model2;
        }


        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }



    @Override
    public ResourceLocation getTextureLocation(PotatoMine abstractPlant) {
        String s = "textures/entity/"+abstractPlant.namePath+".png";
        return Rhyme.space(s);
    }
}
