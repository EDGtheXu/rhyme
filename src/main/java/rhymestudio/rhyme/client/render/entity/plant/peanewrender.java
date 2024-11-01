package rhymestudio.rhyme.client.render.entity.plant;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.BreezeModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BreezeEyesLayer;
import net.minecraft.client.renderer.entity.layers.BreezeWindLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.breeze.Breeze;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.client.model.peaModel;
import rhymestudio.rhyme.entity.plants.PeaNew;

public class peanewrender extends MobRenderer<PeaNew,peaModel> {
    private static final ResourceLocation TEXTURE_LOCATION = Rhyme.space("textures/entity/peanew.png");

    public peanewrender(EntityRendererProvider.Context cnt) {
        super(cnt,new peaModel(cnt.bakeLayer(peaModel.LAYER_LOCATION)), 0.5f);

    }


/*
    public void render(PeaNew entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {

        model.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURE_LOCATION)), packedLight, OverlayTexture.NO_OVERLAY);
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
*/
    @Override
    public ResourceLocation getTextureLocation(PeaNew peaNew) {
        return TEXTURE_LOCATION;
    }

}
