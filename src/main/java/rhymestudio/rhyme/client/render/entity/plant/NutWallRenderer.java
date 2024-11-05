package rhymestudio.rhyme.client.render.entity.plant;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.client.model.*;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.plants.NutWall;
import rhymestudio.rhyme.entity.plants.PotatoMine;

public class NutWallRenderer extends MobRenderer<NutWall, HierarchicalModel<NutWall>> {
    NutWallHurt1 model1;
    NutWallHurt2 model2;
    public NutWallRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager,new NutWallModel(renderManager.bakeLayer(NutWallModel.LAYER_LOCATION)), 0.5f);
        this.model1 = new NutWallHurt1(renderManager.bakeLayer(NutWallHurt1.LAYER_LOCATION));
        this.model2 = new NutWallHurt2(renderManager.bakeLayer(NutWallHurt2.LAYER_LOCATION));

    }



    public void render(NutWall entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if(entity.getHealth() / entity.getMaxHealth() < 0.33){
            this.model = this.model2;
        }else if(entity.getHealth() / entity.getMaxHealth() < 0.66){
            this.model = this.model1;
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }



    @Override
    public ResourceLocation getTextureLocation(NutWall entity) {
        String path = entity.namePath;
        if(entity.getHealth() / entity.getMaxHealth() < 0.33){
            path = "nut_wall1";
        }else if(entity.getHealth() / entity.getMaxHealth() < 0.66){
            path = "nut_wall2";
        }
        String s = "textures/entity/"+path+".png";
        return Rhyme.space(s);
    }
}
