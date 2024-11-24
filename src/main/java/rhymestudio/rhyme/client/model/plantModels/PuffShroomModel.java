package rhymestudio.rhyme.client.model.plantModels;// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.client.animation.plantAnimations.PuffShroomAnimation;
import rhymestudio.rhyme.entity.AbstractPlant;

public class PuffShroomModel extends HierarchicalModel<AbstractPlant> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Rhyme.space( "puff_shroom_model"), "main");
	private final ModelPart head;
	private final ModelPart bone3;
	private final ModelPart bone2;
	private final ModelPart root;
	public PuffShroomModel(ModelPart root) {
		this.root = root;
		this.head = root.getChild("head");
		this.bone3 = this.head.getChild("bone3");
		this.bone2 = this.head.getChild("bone2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 14).addBox(-3.8738F, -2.0238F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.0238F, 1.1262F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -4.0F, -5.0F, 10.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1262F, -0.1238F, 0.0F, 0.0F, 0.0F, -0.1309F));

		PartDefinition bone3 = head.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 30).addBox(-1.0F, -3.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.7262F, 4.8762F, 0.0F));

		PartDefinition bone2 = head.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(12, 30).addBox(-5.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(1.1262F, 5.9762F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(AbstractPlant entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.head.yRot = netHeadYaw * 0.017453292F +1.5708F;
//		this.head.xRot = headPitch * 0.017453292F;

		this.animate(entity.animState.getAnim("sleep"), PuffShroomAnimation.sleeping, ageInTicks);
		this.animate(entity.animState.getAnim("idle"), PuffShroomAnimation.idle, ageInTicks);
		this.animate(entity.animState.getAnim("shoot"), PuffShroomAnimation.attack, ageInTicks);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int c) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, c);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}