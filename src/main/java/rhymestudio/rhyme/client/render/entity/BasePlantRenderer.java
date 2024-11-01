package rhymestudio.rhyme.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.entity.AbstractPlant;


public class BasePlantRenderer<T extends AbstractPlant,U extends EntityModel<T>> extends MobRenderer<T, U> {
    public BasePlantRenderer(EntityRendererProvider.Context renderManager,U model) {
        super(renderManager, model,0.5f);
    }
    

    protected void setupRotations(T entity, PoseStack poseStack, float bob, float yBodyRot, float partialTick, float scale) {
        poseStack.mulPose(Axis.YP.rotationDegrees(-90));

        super.setupRotations(entity, poseStack, bob, yBodyRot, partialTick, scale);

    }

        @Override
    public ResourceLocation getTextureLocation(AbstractPlant abstractPlant) {
        String s = "textures/entity/"+abstractPlant.namePath+".png";
        return Rhyme.space(s);
    }
}
