package rhymestudio.rhyme.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.plants.SunFlower;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import java.util.Map;

public class BasePlantRenderer<T extends AbstractPlant> extends GeoEntityRenderer<T> {

    public BasePlantRenderer(EntityRendererProvider.Context renderManager, GeoModel<T> model) {
        super(renderManager, model);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(T  entity) {
        String s = "textures/entity/"+entity.namePath+".png";
        return Rhyme.space(s);
    }
    public void preRender(PoseStack poseStack, T animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.last().pose().rotate(Axis.YP.rotationDegrees( -90));


    }
}
