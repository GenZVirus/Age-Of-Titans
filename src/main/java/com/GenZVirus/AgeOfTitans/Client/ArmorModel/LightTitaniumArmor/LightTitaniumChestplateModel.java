package com.GenZVirus.AgeOfTitans.Client.ArmorModel.LightTitaniumArmor;

import com.GenZVirus.AgeOfTitans.Client.ArmorModel.ArmorModelBase;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;

public class LightTitaniumChestplateModel extends ArmorModelBase {

	private float rightArmOffsetX = -0.8F;
	private float rightArmOffsetY = -1.0F;
	private float rightArmOffsetZ = 0.0F;
	private float leftArmOffsetX = 1.0F;
	private float leftArmOffsetY = -1.0F;
	private float leftArmOffsetZ = 0.0F;
//	private float OffsetX = 0;
//	private float OffsetY = -0.3F;
//	private float OffsetZ = 0;

	
	public LightTitaniumChestplateModel() {
		super(EquipmentSlotType.CHEST);
		textureWidth = 128;
		textureHeight = 128;

		bipedLeftArm = new ModelRenderer(this);
		bipedLeftArm.setRotationPoint(6.0F, 25.0F, 0.0F);
		bipedLeftArm.setTextureOffset(57, 2).addBox(-1.8F + leftArmOffsetX, -1.2F + leftArmOffsetY, -2.2F + leftArmOffsetZ, 4.0F, 1.0F, 1.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(44, 14).addBox(-1.8F + leftArmOffsetX, -1.3F + leftArmOffsetY, -1.5F + leftArmOffsetZ, 4.0F, 1.0F, 3.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(18, 56).addBox(1.2F + leftArmOffsetX, -0.3F + leftArmOffsetY, -1.0F + leftArmOffsetZ, 1.0F, 6.0F, 2.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(42, 27).addBox(-2.0F + leftArmOffsetX, -1.3F + leftArmOffsetY, -1.5F + leftArmOffsetZ, 4.0F, 1.0F, 3.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(57, 0).addBox(-2.0F + leftArmOffsetX, -1.2F + leftArmOffsetY, 1.2F + leftArmOffsetZ, 4.0F, 1.0F, 1.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(56, 48).addBox(-1.8F + leftArmOffsetX, -1.2F + leftArmOffsetY, 1.2F + leftArmOffsetZ, 4.0F, 1.0F, 1.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(56, 46).addBox(-2.0F + leftArmOffsetX, -1.2F + leftArmOffsetY, -2.2F + leftArmOffsetZ, 4.0F, 1.0F, 1.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(26, 12).addBox(-1.9F + leftArmOffsetX, -1.1F + leftArmOffsetY, -2.1F + leftArmOffsetZ, 4.0F, 11.0F, 1.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(4, 51).addBox(-2.1F + leftArmOffsetX, -1.1F + leftArmOffsetY, -2.1F + leftArmOffsetZ, 1.0F, 11.0F, 1.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(26, 50).addBox(-2.1F + leftArmOffsetX, -1.1F + leftArmOffsetY, 1.1F + leftArmOffsetZ, 1.0F, 11.0F, 1.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(26, 26).addBox(-1.9F + leftArmOffsetX, -1.1F + leftArmOffsetY, 1.1F + leftArmOffsetZ, 4.0F, 11.0F, 1.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(10, 25).addBox(1.1F + leftArmOffsetX, -1.1F + leftArmOffsetY, -1.6F + leftArmOffsetZ, 1.0F, 11.0F, 3.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(43, 0).addBox(-2.1F + leftArmOffsetX, 1.9F + leftArmOffsetY, -1.6F + leftArmOffsetZ, 1.0F, 7.0F, 3.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(49, 36).addBox(-1.9F + leftArmOffsetX, -1.1F + leftArmOffsetY, -1.4F + leftArmOffsetZ, 3.0F, 1.0F, 3.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(56, 18).addBox(-2.2F + leftArmOffsetX, 6.8F + leftArmOffsetY, 1.2F + leftArmOffsetZ, 4.0F, 3.0F, 1.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(51, 56).addBox(-2.2F + leftArmOffsetX, 6.8F + leftArmOffsetY, -2.2F + leftArmOffsetZ, 4.0F, 3.0F, 1.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(42, 31).addBox(1.2F + leftArmOffsetX, 5.8F + leftArmOffsetY, -2.2F + leftArmOffsetZ, 1.0F, 4.0F, 4.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(22, 12).addBox(1.2F + leftArmOffsetX, 5.8F + leftArmOffsetY, 1.2F + leftArmOffsetZ, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(61, 6).addBox(1.3F + leftArmOffsetX, 5.9F + leftArmOffsetY, -2.3F + leftArmOffsetZ, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(51, 0).addBox(1.3F + leftArmOffsetX, 5.9F + leftArmOffsetY, -1.7F + leftArmOffsetZ, 1.0F, 1.0F, 4.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(35, 59).addBox(1.3F + leftArmOffsetX, -0.2F + leftArmOffsetY, -0.5F + leftArmOffsetZ, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(57, 30).addBox(1.3F + leftArmOffsetX, 6.7F + leftArmOffsetY, 1.0F + leftArmOffsetZ, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bipedLeftArm.setTextureOffset(51, 0).addBox(1.3F + leftArmOffsetX, 6.7F + leftArmOffsetY, -2.0F + leftArmOffsetZ, 1.0F, 3.0F, 1.0F, 0.0F, false);

		bipedRightArm = new ModelRenderer(this);
		bipedRightArm.setRotationPoint(-5.8F, 25.0F, 0.0F);
		bipedRightArm.setTextureOffset(56, 44).addBox(-2.2F + rightArmOffsetX, -1.2F + rightArmOffsetY, -2.2F + rightArmOffsetZ, 4.0F, 1.0F, 1.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(47, 45).addBox(-2.4F + rightArmOffsetX, -1.2F + rightArmOffsetY, 1.2F + rightArmOffsetZ, 4.0F, 1.0F, 1.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(44, 22).addBox(-2.2F + rightArmOffsetX, -1.2F + rightArmOffsetY, 1.2F + rightArmOffsetZ, 4.0F, 1.0F, 1.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(26, 24).addBox(-2.4F + rightArmOffsetX, -1.2F + rightArmOffsetY, -2.2F + rightArmOffsetZ, 4.0F, 1.0F, 1.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(0, 25).addBox(-2.3F + rightArmOffsetX, -1.1F + rightArmOffsetY, -2.1F + rightArmOffsetZ, 4.0F, 11.0F, 1.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(0, 49).addBox(0.9F + rightArmOffsetX, -1.1F + rightArmOffsetY, -2.1F + rightArmOffsetZ, 1.0F, 11.0F, 1.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(36, 47).addBox(0.9F + rightArmOffsetX, -1.1F + rightArmOffsetY, 1.1F + rightArmOffsetZ, 1.0F, 11.0F, 1.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(18, 0).addBox(-2.3F + rightArmOffsetX, -1.1F + rightArmOffsetY, 1.1F + rightArmOffsetZ, 4.0F, 11.0F, 1.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(18, 18).addBox(-2.3F + rightArmOffsetX, -1.1F + rightArmOffsetY, -1.6F + rightArmOffsetZ, 1.0F, 11.0F, 3.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(12, 42).addBox(0.9F + rightArmOffsetX, 1.9F + rightArmOffsetY, -1.6F + rightArmOffsetZ, 1.0F, 7.0F, 3.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(48, 31).addBox(-1.3F + rightArmOffsetX, -1.1F + rightArmOffsetY, -1.4F + rightArmOffsetZ, 3.0F, 1.0F, 3.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(41, 10).addBox(-2.2F + rightArmOffsetX, -1.3F + rightArmOffsetY, -1.5F + rightArmOffsetZ, 4.0F, 1.0F, 3.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(41, 41).addBox(-2.4F + rightArmOffsetX, -1.3F + rightArmOffsetY, -1.5F + rightArmOffsetZ, 4.0F, 1.0F, 3.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(12, 52).addBox(-2.4F + rightArmOffsetX, -0.3F + rightArmOffsetY, -1.0F + rightArmOffsetZ, 1.0F, 6.0F, 2.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(18, 12).addBox(-2.4F + rightArmOffsetX, 5.8F + rightArmOffsetY, 1.2F + rightArmOffsetZ, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(55, 13).addBox(-2.0F + rightArmOffsetX, 6.8F + rightArmOffsetY, 1.2F + rightArmOffsetZ, 4.0F, 3.0F, 1.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(53, 26).addBox(-2.0F + rightArmOffsetX, 6.8F + rightArmOffsetY, -2.2F + rightArmOffsetZ, 4.0F, 3.0F, 1.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(36, 27).addBox(-2.4F + rightArmOffsetX, 5.8F + rightArmOffsetY, -2.2F + rightArmOffsetZ, 1.0F, 4.0F, 4.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(40, 56).addBox(-2.5F + rightArmOffsetX, -0.2F + rightArmOffsetY, -0.5F + rightArmOffsetZ, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(51, 51).addBox(-2.5F + rightArmOffsetX, 5.9F + rightArmOffsetY, -1.7F + rightArmOffsetZ, 1.0F, 1.0F, 4.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(61, 4).addBox(-2.5F + rightArmOffsetX, 5.9F + rightArmOffsetY, -2.3F + rightArmOffsetZ, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(51, 51).addBox(-2.5F + rightArmOffsetX, 6.7F + rightArmOffsetY, 1.0F + rightArmOffsetZ, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bipedRightArm.setTextureOffset(36, 27).addBox(-2.5F + rightArmOffsetX, 6.7F + rightArmOffsetY, -2.0F + rightArmOffsetZ, 1.0F, 3.0F, 1.0F, 0.0F, false);

		bipedBody = new ModelRenderer(this);
		bipedBody.setRotationPoint(0.0F, 24.0F, 0.0F);
		bipedBody.setTextureOffset(0, 13).addBox(-4.0F, 0.2F, 1.2F, 8.0F, 11.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(35, 25).addBox(-4.0F, -0.2F, 1.3F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(35, 0).addBox(-4.1F, -0.1F, -2.1F, 3.0F, 12.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(18, 32).addBox(1.1F, -0.1F, -2.1F, 3.0F, 12.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(36, 13).addBox(3.1F, 2.9F, -1.4F, 1.0F, 9.0F, 3.0F, 0.0F, false);
		bipedBody.setTextureOffset(47, 47).addBox(-4.1F, -0.1F, -1.4F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		bipedBody.setTextureOffset(44, 18).addBox(1.1F, -0.1F, -1.4F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		bipedBody.setTextureOffset(33, 35).addBox(-4.1F, 2.9F, -1.4F, 1.0F, 9.0F, 3.0F, 0.0F, false);
		bipedBody.setTextureOffset(32, 47).addBox(-4.1F, -0.1F, 1.1F, 1.0F, 12.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(0, 0).addBox(-3.9F, -0.1F, 1.1F, 8.0F, 12.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(41, 14).addBox(2.0F, -0.2F, -2.3F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(52, 9).addBox(1.2F, -0.3F, -1.5F, 2.0F, 1.0F, 3.0F, 0.0F, false);
		bipedBody.setTextureOffset(51, 5).addBox(2.0213F, -0.3F, -1.5F, 2.0F, 1.0F, 3.0F, 0.0F, false);
		bipedBody.setTextureOffset(0, 37).addBox(1.2F, 0.2F, -2.2F, 2.0F, 11.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(28, 0).addBox(2.0F, 0.2F, -2.2F, 2.0F, 11.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(12, 39).addBox(1.2F, -0.2F, -2.3F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(52, 40).addBox(-3.2F, -0.3F, -1.5F, 2.0F, 1.0F, 3.0F, 0.0F, false);
		bipedBody.setTextureOffset(57, 51).addBox(-3.2F, -0.2F, -2.3F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(52, 22).addBox(-4.0F, -0.3F, -1.5F, 2.0F, 1.0F, 3.0F, 0.0F, false);
		bipedBody.setTextureOffset(26, 38).addBox(-3.2F, 0.2F, -2.2F, 2.0F, 11.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(41, 39).addBox(-4.0F, -0.2F, -2.3F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(6, 39).addBox(-4.0F, 0.2F, -2.2F, 2.0F, 11.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(47, 51).addBox(2.1F, 1.1F, -2.4F, 1.0F, 10.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(20, 45).addBox(-3.6F, 1.0F, -2.3F, 2.0F, 10.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(8, 51).addBox(-3.1F, 1.1F, -2.4F, 1.0F, 10.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(41, 45).addBox(1.6F, 1.0F, -2.3F, 2.0F, 10.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(44, 61).addBox(-3.5F, 1.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(30, 60).addBox(2.5F, 3.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(51, 60).addBox(0.5F, 3.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(55, 60).addBox(-1.5F, 3.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(59, 60).addBox(-3.5F, 3.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(59, 9).addBox(2.5F, 5.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(59, 22).addBox(0.5F, 5.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(59, 40).addBox(-1.5F, 5.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(12, 60).addBox(-3.5F, 5.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(57, 53).addBox(2.5F, 7.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(58, 5).addBox(0.5F, 7.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(58, 35).addBox(-1.5F, 7.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(58, 37).addBox(-3.5F, 7.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(6, 37).addBox(2.5F, 9.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(38, 35).addBox(0.5F, 9.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(16, 52).addBox(-1.5F, 9.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(22, 56).addBox(-3.5F, 9.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(0, 61).addBox(-1.5F, 1.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(60, 55).addBox(0.5F, 1.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bipedBody.setTextureOffset(60, 33).addBox(2.5F, 1.0F, 1.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bipedLeftArm.render(matrixStack, buffer, packedLight, packedOverlay);
		bipedRightArm.render(matrixStack, buffer, packedLight, packedOverlay);
		bipedBody.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}
