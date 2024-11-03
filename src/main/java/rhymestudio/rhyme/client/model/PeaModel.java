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
import rhymestudio.rhyme.client.animation.PeaAnimation;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.plants.Pea;

public class PeaModel extends HierarchicalModel<AbstractPlant> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Rhyme.space( "pea_new_model"), "main");
	private final ModelPart yezi;
	private final ModelPart genh;
	private final ModelPart head;
	private final ModelPart bone;
	private final ModelPart bone5;
	private final ModelPart eyeclosed;
	private final ModelPart bone6;
	private final ModelPart root;

	public PeaModel(ModelPart root) {
		this.root = root;
		this.yezi = root.getChild("yezi");
		this.genh = root.getChild("genh");
		this.head = root.getChild("head");
		this.bone = this.head.getChild("bone");
		this.bone5 = this.head.getChild("bone5");
		this.eyeclosed = this.head.getChild("eyeclosed");
		this.bone6 = root.getChild("bone6");


	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition yezi = partdefinition.addOrReplaceChild("yezi", CubeListBuilder.create().texOffs(24, 15).addBox(0.0F, -1.0F, -4.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(24, 0).addBox(0.0F, -1.0F, 2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(-6.0F, -1.0F, 2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(12, 24).addBox(-6.0F, -1.0F, -4.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 24.0F, -1.0F));

		PartDefinition genh = partdefinition.addOrReplaceChild("genh", CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -8.0F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 16.0F, 0.0F));

		PartDefinition bone = head.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(14, 16).addBox(5.0F, -13.0F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(16, 18).addBox(4.0F, -12.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 8.0F, 0.0F));

		PartDefinition bone5 = head.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(-12.0F, -3.0F, 0.0F));

		PartDefinition cube_r1 = bone5.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 15).addBox(-4.0F, -1.0F, -3.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -2.0F, 1.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition cube_r2 = bone5.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(24, 5).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition eyeclosed = head.addOrReplaceChild("eyeclosed", CubeListBuilder.create().texOffs(20, 21).addBox(-23.0F, -16.0F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(11.0F, 8.0F, 0.0F));

		PartDefinition bone6 = partdefinition.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 28).addBox(15.8F, 13.7F, -1.3F, 2.5F, 2.6F, 2.6F, new CubeDeformation(0.0F)), PartPose.offset(-17.0F, 16.0F, 0.0F));


		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(AbstractPlant entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.head.yRot = netHeadYaw * 0.017453292F;
		this.head.zRot = headPitch * 0.017453292F;

		this.animate(entity.animState.getAnim("idle"), PeaAnimation.idle_normal, ageInTicks);
		this.animate(entity.animState.getAnim("shoot"), PeaAnimation.shoot, ageInTicks);

	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		super.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		yezi.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		genh.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		bone6.render(poseStack, vertexConsumer, packedLight, packedOverlay,color);
	}

	@Override
	public ModelPart root() {
		return root;
	}



}