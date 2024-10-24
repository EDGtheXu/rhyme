package rhymestudio.rhyme.client.render.entity.plant;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.client.model.GeoNormalModel;
import rhymestudio.rhyme.client.render.entity.BasePlantRenderer;
import rhymestudio.rhyme.entity.plants.SunFlower;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;

public class SunflowerRenderer extends BasePlantRenderer<SunFlower> {


    public SunflowerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GeoNormalModel<>("sun_flower"));
    }

}

