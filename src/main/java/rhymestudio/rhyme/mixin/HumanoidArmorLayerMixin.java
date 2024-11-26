package rhymestudio.rhyme.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rhymestudio.rhyme.client.render.util.ArmorLayerMixinUtil;
import rhymestudio.rhyme.item.armor.IModelArmor;
import rhymestudio.rhyme.registry.items.ArmorItems;

@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> {

    @Unique
    protected RenderLayerParent<T, M> Rhyme$renderer;
    @Inject(method = "<init>", at = @At("RETURN"))
    public void initMixin(RenderLayerParent<T, M> renderer, HumanoidModel<T> innerModel, HumanoidModel<T> outerModel, ModelManager modelManager, CallbackInfo ci) {
        this.Rhyme$renderer = renderer;
    }

    @Inject(method = "renderArmorPiece(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;ILnet/minecraft/client/model/HumanoidModel;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/client/extensions/common/IClientItemExtensions;getDefaultDyeColor(Lnet/minecraft/world/item/ItemStack;)I"), cancellable = true)
    public void renderMixin(PoseStack poseStack, MultiBufferSource bufferSource, T livingEntity, EquipmentSlot slot, int packedLight, A p_model, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {

        ItemStack itemstack = livingEntity.getItemBySlot(slot);
        if(itemstack.getItem() instanceof IModelArmor){
            ArmorLayerMixinUtil.renderModelArmor(poseStack, bufferSource, livingEntity, slot, packedLight, p_model, limbSwing, limbSwingAmount, partialTick, ageInTicks, netHeadYaw, headPitch,itemstack,
                    Rhyme$renderer.getModel().head
            );
            ci.cancel();
        }
    }
}
