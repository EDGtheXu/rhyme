package rhymestudio.rhyme.client.render.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class ArmorLayerMixinUtil {
    public static void renderModelArmor(PoseStack poseStack, MultiBufferSource bufferSource, LivingEntity livingEntity, EquipmentSlot slot, int packedLight, HumanoidModel<? extends LivingEntity> model, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch, ItemStack stack){
        poseStack.pushPose();
        int pack = OverlayTexture.pack((int) (partialTick*Math.sin(partialTick/10)), (int) (partialTick*Math.cos(partialTick/10)));
        if(slot == EquipmentSlot.HEAD){
            float s = 0.65f;
            poseStack.scale(s,s,s);
            poseStack.translate(0,model.hat.y * 0.09 , 0);
            poseStack.mulPose(Axis.YP.rotationDegrees(180 + netHeadYaw));
            poseStack.mulPose(Axis.XP.rotationDegrees(180 - headPitch));
            poseStack.translate(0,0.35 , 0);
            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.HEAD,packedLight,pack,poseStack,bufferSource,livingEntity.level(),0);
        }


        poseStack.popPose();
    }
}
