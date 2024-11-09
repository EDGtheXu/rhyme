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


    public PotatoMineRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager,new PotatoMineModel(renderManager.bakeLayer(PotatoMineModel.LAYER_LOCATION)), 0,1,false);
    }

    public void render(PotatoMine entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);


    }

    @Override
    public ResourceLocation getTextureLocation(PotatoMine abstractPlant) {
        String s = "textures/entity/"+abstractPlant.namePath+".png";
        return Rhyme.space(s);
    }

}
