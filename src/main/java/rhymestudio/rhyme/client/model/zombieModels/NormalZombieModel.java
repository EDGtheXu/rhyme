package rhymestudio.rhyme.client.model.zombieModels;// Made with Blockbench 4.11.2
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
import rhymestudio.rhyme.client.animation.zombieAnimations.NormalZombieAnimation;
import rhymestudio.rhyme.entity.AbstractMonster;

public class NormalZombieModel<T extends AbstractMonster> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Rhyme.space( "normal_zombie_model"), "main");
	private final ModelPart all;
	private final ModelPart bone;
	public final ModelPart head;
	private final ModelPart body;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;
	private final ModelPart root;

	public NormalZombieModel(ModelPart root) {
		this.root =root;
		this.all = root.getChild("all");
		this.bone = this.all.getChild("bone");
		this.head = this.bone.getChild("head");
		this.body = this.bone.getChild("body");
		this.left_arm = this.bone.getChild("left_arm");
		this.right_arm = this.bone.getChild("right_arm");
		this.left_leg = this.all.getChild("left_leg");
		this.right_leg = this.all.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone = all.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = bone.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -28.0F, 0.0F));

		PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, 0.0F));

		PartDefinition left_arm = bone.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -22.0F, 0.0F));

		PartDefinition right_arm = bone.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, -22.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r1 = right_arm.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition left_leg = all.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -10.0F, 0.0F));

		PartDefinition right_leg = all.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(24, 16).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -10.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}



	@Override
	public void setupAnim(AbstractMonster entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);


		if(entity.tickCount - entity.animState.tick < 10){


//			System.out.println("smooth");
		}



		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		if(entity.animState.curName.equals("attack")){
			this.animate(entity.animState.getAnim("attack"), NormalZombieAnimation.attack, ageInTicks);
			return;
		}

		if(entity.hurtTime>0){
			this.animate(entity.animState.getAnim("hurt"), NormalZombieAnimation.hurt, ageInTicks);
		}


		this.animate(entity.animState.getAnim("idle"), NormalZombieAnimation.idle, ageInTicks);
		this.animate(entity.animState.getAnim("walk"), NormalZombieAnimation.walk, ageInTicks);
		this.animate(entity.animState.getAnim("run"), NormalZombieAnimation.run, ageInTicks);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int c) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, c);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}