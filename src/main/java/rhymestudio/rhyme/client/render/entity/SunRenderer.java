package rhymestudio.rhyme.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.world.entity.item.ItemEntity;
import org.jetbrains.annotations.NotNull;

public class SunRenderer extends ItemEntityRenderer {
    public SunRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    //todo
    public void render(@NotNull ItemEntity itemEntity, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int packedLight) {

        super.render(itemEntity, entityYaw, partialTick, poseStack, multiBufferSource, packedLight);
    }

}
