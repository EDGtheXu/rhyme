package rhymestudio.rhyme.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rhymestudio.rhyme.item.AbstractCardItem;
import rhymestudio.rhyme.registry.ModDataComponentTypes;
import rhymestudio.rhyme.registry.items.PlantItems;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Shadow public abstract void renderStatic(@Nullable LivingEntity entity, ItemStack itemStack, ItemDisplayContext diplayContext, boolean leftHand, PoseStack poseStack, MultiBufferSource bufferSource, @Nullable Level level, int combinedLight, int combinedOverlay, int seed);

    @Inject(method = "renderStatic(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/level/Level;III)V", at = @At(value = "HEAD"))
    private void renderStaticMixin(LivingEntity entity, ItemStack itemStack, ItemDisplayContext diplayContext, boolean leftHand, PoseStack poseStack, MultiBufferSource bufferSource, Level level, int combinedLight, int combinedOverlay, int seed, CallbackInfo ci) {
        var data = itemStack.getComponents().get(ModDataComponentTypes.CARD_QUALITY.get());
        if(data != null){
            ItemStack qualityItem = data.getQualityItem().getDefaultInstance();
            renderStatic(entity, qualityItem, diplayContext, leftHand, poseStack, bufferSource, level, combinedLight, combinedOverlay, seed);
        }

    }
}
