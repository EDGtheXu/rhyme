package rhymestudio.rhyme.client.model;// Made with Blockbench 4.11.1
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
import rhymestudio.rhyme.client.animation.DoublePeaAnimation;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.plants.DoublePea;
import rhymestudio.rhyme.entity.plants.Pea;

public class DoublePeaModel extends HierarchicalModel<AbstractPlant> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Rhyme.space( "repeater"), "main");
	private final ModelPart Yezi;
	private final ModelPart Genh;
	private final ModelPart head;
	private final ModelPart bone3;
	private final ModelPart bone;
	private final ModelPart eyeclosed;
	private final ModelPart bone6;
	private final ModelPart bone2;
	private final ModelPart root;

	public DoublePeaModel(ModelPart root) {
		this.root = root;
		this.Yezi = root.getChild("Yezi");
		this.Genh = root.getChild("Genh");
		this.head = root.getChild("head");
		this.bone3 = this.head.getChild("bone3");
		this.bone = this.head.getChild("bone");
		this.eyeclosed = this.head.getChild("eyeclosed");
		this.bone6 = root.getChild("bone6");
		this.bone2 = root.getChild("bone2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Yezi = partdefinition.addOrReplaceChild("Yezi", CubeListBuilder.create().texOffs(24, 15).addBox(0.0F, -1.0F, -4.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(24, 0).addBox(0.0F, -1.0F, 2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(-6.0F, -1.0F, 2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(12, 24).addBox(-6.0F, -1.0F, -4.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 24.0F, -1.0F));

		PartDefinition Genh = partdefinition.addOrReplaceChild("Genh", CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(35, 3).addBox(-15.0F, -8.0F, -3.0F, 3.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-12.0F, -8.0F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 16.0F, 0.0F));

		PartDefinition bone3 = head.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(40, 12).addBox(9.3F, -4.8F, -3.0F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-13.0F, -3.0F, 0.0F));

		PartDefinition bone = head.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(14, 16).addBox(5.0F, -13.0F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(16, 18).addBox(4.0F, -12.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 8.0F, 0.0F));

		PartDefinition eyeclosed = head.addOrReplaceChild("eyeclosed", CubeListBuilder.create().texOffs(20, 21).addBox(-23.0F, -16.0F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(11.0F, 8.0F, 0.0F));

		PartDefinition bone6 = partdefinition.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 25).addBox(20.8F, 13.7F, -1.3F, 2.5F, 2.6F, 2.6F, new CubeDeformation(0.0F)), PartPose.offset(-22.0F, 16.0F, 0.0F));

		PartDefinition bone2 = partdefinition.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 30).addBox(15.8F, 13.7F, -1.3F, 2.5F, 2.6F, 2.6F, new CubeDeformation(0.0F)), PartPose.offset(-17.0F, 16.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(AbstractPlant entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.head.yRot = netHeadYaw * 0.017453292F;
		this.head.zRot = headPitch * 0.017453292F;

		this.animate(entity.animState.getAnim("idle"), DoublePeaAnimation.idle_normal, ageInTicks);
		this.animate(entity.animState.getAnim("shoot"), DoublePeaAnimation.shoot, ageInTicks);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		Yezi.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		Genh.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		bone6.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		bone2.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}