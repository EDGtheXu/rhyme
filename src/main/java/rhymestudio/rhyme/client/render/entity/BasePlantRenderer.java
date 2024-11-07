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
    private boolean rotY;
    private float scale;
    public BasePlantRenderer(EntityRendererProvider.Context renderManager,U model) {
        this(renderManager, model,0.3f,1);
    }

    public BasePlantRenderer(EntityRendererProvider.Context renderManager,U model,float shadowRadius,float scale) {
        this(renderManager, model,shadowRadius,scale,false);
    }

    public BasePlantRenderer(EntityRendererProvider.Context renderManager,U model,float shadowRadius,float scale,boolean rotY) {
        super(renderManager, model,shadowRadius);
        this.rotY = rotY;
        this.scale = scale;
    }

    protected void setupRotations(T entity, PoseStack poseStack, float bob, float yBodyRot, float partialTick, float scale) {
        if(rotY) poseStack.mulPose(Axis.YP.rotationDegrees(-90));
        poseStack.scale(this.scale, this.scale, this.scale);
        super.setupRotations(entity, poseStack, bob, yBodyRot, partialTick, scale);
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractPlant abstractPlant) {
        String s = "textures/entity/"+abstractPlant.namePath+".png";
        return Rhyme.space(s);
    }
}
