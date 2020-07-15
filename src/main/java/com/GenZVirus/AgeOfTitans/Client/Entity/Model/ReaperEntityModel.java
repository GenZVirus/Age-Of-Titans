package com.GenZVirus.AgeOfTitans.Client.Entity.Model;

import com.GenZVirus.AgeOfTitans.Common.Entities.ReaperEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ReaperEntityModel extends EntityModel<ReaperEntity> {
	private final ModelRenderer Body;
	private final ModelRenderer Head;
	private final ModelRenderer HoodBack1;
	private final ModelRenderer HoodBack2;
	private final ModelRenderer HoodBack3;
	private final ModelRenderer Chest;
	private final ModelRenderer Amulet;
	private final ModelRenderer ChestPlate;
	private final ModelRenderer Cape;
	private final ModelRenderer Arms;
	private final ModelRenderer ShoulderLeft;
	private final ModelRenderer ArmLeft;
	private final ModelRenderer GauntletLeft;
	private final ModelRenderer ShoulderDecorationLeft;
	private final ModelRenderer Scythe;
	private final ModelRenderer Handle;
	private final ModelRenderer HandleRing;
	private final ModelRenderer HandleTop;
	private final ModelRenderer Blade;
	private final ModelRenderer ShoulderRight;
	private final ModelRenderer ArmRight;
	private final ModelRenderer GauntletRight;
	private final ModelRenderer ShoulderDecorationRight;
	private final ModelRenderer Scythe2;
	private final ModelRenderer Handle2;
	private final ModelRenderer HandleRing2;
	private final ModelRenderer HandleTop2;
	private final ModelRenderer Blade2;
	private final ModelRenderer Leggs;
	private final ModelRenderer Skirt;
	private final ModelRenderer ClothLeft1;
	private final ModelRenderer ClothLeft2;
	private final ModelRenderer ClothLeft3;
	private final ModelRenderer ClothLeft4;
	private final ModelRenderer ClothLeft5;
	private final ModelRenderer ClothLeft6;
	private final ModelRenderer ClothLeft7;
	private final ModelRenderer ClothLeft8;
	private final ModelRenderer ClothLeft9;
	private final ModelRenderer ClothLeft10;
	private final ModelRenderer ClothRight1;
	private final ModelRenderer ClothRight2;
	private final ModelRenderer ClothRight3;
	private final ModelRenderer ClothRight4;
	private final ModelRenderer ClothRight5;
	private final ModelRenderer ClothRight6;
	private final ModelRenderer ClothRight7;
	private final ModelRenderer ClothRight8;
	private final ModelRenderer ClothRight9;
	private final ModelRenderer ClothRight10;

	public ReaperEntityModel() {
		textureWidth = 128;
		textureHeight = 128;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(Head);
		Head.setTextureOffset(0, 0).addBox(-6.0F, -44.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		HoodBack1 = new ModelRenderer(this);
		HoodBack1.setRotationPoint(0.0F, -32.0F, 6.0F);
		Head.addChild(HoodBack1);
		setRotationAngle(HoodBack1, -0.0873F, 0.0F, 0.0F);
		HoodBack1.setTextureOffset(0, 51).addBox(-5.0F, -11.0F, -1.0F, 10.0F, 11.0F, 2.0F, 0.0F, false);

		HoodBack2 = new ModelRenderer(this);
		HoodBack2.setRotationPoint(0.0F, -32.0F, 6.0F);
		Head.addChild(HoodBack2);
		setRotationAngle(HoodBack2, -0.1745F, 0.0F, 0.0F);
		HoodBack2.setTextureOffset(64, 24).addBox(-4.0F, -10.0F, 0.0F, 8.0F, 10.0F, 2.0F, 0.0F, false);

		HoodBack3 = new ModelRenderer(this);
		HoodBack3.setRotationPoint(0.0F, -32.0F, 6.0F);
		Head.addChild(HoodBack3);
		setRotationAngle(HoodBack3, -0.1745F, 0.0F, 0.0F);
		HoodBack3.setTextureOffset(44, 70).addBox(-3.0F, -9.0F, 1.0F, 6.0F, 9.0F, 2.0F, 0.0F, false);

		Chest = new ModelRenderer(this);
		Chest.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(Chest);
		Chest.setTextureOffset(0, 24).addBox(-5.0F, -32.0F, -5.0F, 10.0F, 17.0F, 10.0F, 0.0F, false);

		Amulet = new ModelRenderer(this);
		Amulet.setRotationPoint(0.0F, 0.0F, 0.0F);
		Chest.addChild(Amulet);
		Amulet.setTextureOffset(0, 68).addBox(-2.0F, -32.0F, -8.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		Amulet.setTextureOffset(0, 10).addBox(-2.0F, -31.0F, -9.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		Amulet.setTextureOffset(24, 59).addBox(-1.0F, -32.0F, -9.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Amulet.setTextureOffset(64, 10).addBox(-1.5F, -30.0F, -9.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		Amulet.setTextureOffset(30, 30).addBox(-1.0F, -29.0F, -8.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Amulet.setTextureOffset(72, 13).addBox(-6.0F, -32.0F, -7.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		Amulet.setTextureOffset(79, 0).addBox(-5.0F, -31.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		Amulet.setTextureOffset(22, 51).addBox(-4.0F, -30.0F, -7.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		Amulet.setTextureOffset(24, 53).addBox(-3.0F, -29.0F, -7.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);

		ChestPlate = new ModelRenderer(this);
		ChestPlate.setRotationPoint(0.0F, 0.0F, 0.0F);
		Chest.addChild(ChestPlate);
		ChestPlate.setTextureOffset(93, 2).addBox(-3.0F, -21.0F, 5.0F, 6.0F, 3.0F, 1.0F, 0.0F, false);
		ChestPlate.setTextureOffset(53, 0).addBox(-6.0F, -32.0F, -6.0F, 12.0F, 5.0F, 1.0F, 0.0F, false);
		ChestPlate.setTextureOffset(52, 43).addBox(-6.0F, -32.0F, 5.0F, 12.0F, 5.0F, 1.0F, 0.0F, false);
		ChestPlate.setTextureOffset(40, 25).addBox(5.0F, -32.0F, -5.0F, 1.0F, 5.0F, 10.0F, 0.0F, false);
		ChestPlate.setTextureOffset(0, 64).addBox(5.0F, -27.0F, -5.0F, 1.0F, 3.0F, 10.0F, 0.0F, false);
		ChestPlate.setTextureOffset(14, 75).addBox(5.0F, -24.0F, -4.0F, 1.0F, 3.0F, 8.0F, 0.0F, false);
		ChestPlate.setTextureOffset(68, 82).addBox(5.0F, -21.0F, -3.0F, 1.0F, 3.0F, 6.0F, 0.0F, false);
		ChestPlate.setTextureOffset(40, 40).addBox(-6.0F, -32.0F, -5.0F, 1.0F, 5.0F, 10.0F, 0.0F, false);
		ChestPlate.setTextureOffset(38, 57).addBox(-6.0F, -27.0F, -5.0F, 1.0F, 3.0F, 10.0F, 0.0F, false);
		ChestPlate.setTextureOffset(72, 2).addBox(-6.0F, -24.0F, -4.0F, 1.0F, 3.0F, 8.0F, 0.0F, false);
		ChestPlate.setTextureOffset(81, 57).addBox(-6.0F, -21.0F, -3.0F, 1.0F, 3.0F, 6.0F, 0.0F, false);
		ChestPlate.setTextureOffset(76, 53).addBox(-5.0F, -27.0F, -6.0F, 10.0F, 3.0F, 1.0F, 0.0F, false);
		ChestPlate.setTextureOffset(76, 49).addBox(-5.0F, -27.0F, 5.0F, 10.0F, 3.0F, 1.0F, 0.0F, false);
		ChestPlate.setTextureOffset(84, 72).addBox(-4.0F, -24.0F, -6.0F, 8.0F, 3.0F, 1.0F, 0.0F, false);
		ChestPlate.setTextureOffset(84, 30).addBox(-4.0F, -24.0F, 5.0F, 8.0F, 3.0F, 1.0F, 0.0F, false);
		ChestPlate.setTextureOffset(94, 41).addBox(-3.0F, -21.0F, -6.0F, 6.0F, 3.0F, 1.0F, 0.0F, false);

		Cape = new ModelRenderer(this);
		Cape.setRotationPoint(0.0F, -32.0F, 6.0F);
		Chest.addChild(Cape);
		setRotationAngle(Cape, 0.0873F, 0.0F, 0.0F);
		Cape.setTextureOffset(64, 65).addBox(2.0F, 0.0F, 0.0F, 2.0F, 25.0F, 0.0F, 0.0F, false);
		Cape.setTextureOffset(6, 77).addBox(1.0F, 0.0F, 0.0F, 1.0F, 24.0F, 0.0F, 0.0F, false);
		Cape.setTextureOffset(4, 77).addBox(4.0F, 0.0F, 0.0F, 1.0F, 24.0F, 0.0F, 0.0F, false);
		Cape.setTextureOffset(20, 93).addBox(0.0F, 0.0F, 0.0F, 1.0F, 7.0F, 0.0F, 0.0F, false);
		Cape.setTextureOffset(0, 77).addBox(-2.0F, 0.0F, 0.0F, 1.0F, 24.0F, 0.0F, 0.0F, false);
		Cape.setTextureOffset(2, 77).addBox(-5.0F, 0.0F, 0.0F, 1.0F, 24.0F, 0.0F, 0.0F, false);
		Cape.setTextureOffset(24, 101).addBox(-6.0F, 0.0F, 0.0F, 1.0F, 12.0F, 0.0F, 0.0F, false);
		Cape.setTextureOffset(10, 0).addBox(5.0F, 0.0F, 0.0F, 1.0F, 12.0F, 0.0F, 0.0F, false);
		Cape.setTextureOffset(60, 65).addBox(-4.0F, 0.0F, 0.0F, 2.0F, 25.0F, 0.0F, 0.0F, false);
		Cape.setTextureOffset(12, 82).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 7.0F, 0.0F, 0.0F, false);

		Arms = new ModelRenderer(this);
		Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(Arms);
		

		ShoulderLeft = new ModelRenderer(this);
		ShoulderLeft.setRotationPoint(9.0F, -30.0F, 0.0F);
		Arms.addChild(ShoulderLeft);
		ShoulderLeft.setTextureOffset(24, 55).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		ArmLeft = new ModelRenderer(this);
		ArmLeft.setRotationPoint(-9.0F, 30.0F, 0.0F);
		ShoulderLeft.addChild(ArmLeft);
		ArmLeft.setTextureOffset(78, 40).addBox(7.0F, -27.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);

		GauntletLeft = new ModelRenderer(this);
		GauntletLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		ArmLeft.addChild(GauntletLeft);
		GauntletLeft.setTextureOffset(28, 70).addBox(7.0F, -22.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);
		GauntletLeft.setTextureOffset(90, 34).addBox(6.0F, -22.0F, 2.0F, 6.0F, 6.0F, 1.0F, 0.0F, false);
		GauntletLeft.setTextureOffset(30, 101).addBox(11.0F, -23.0F, 1.0F, 1.0F, 8.0F, 1.0F, 0.0F, false);
		GauntletLeft.setTextureOffset(96, 76).addBox(11.0F, -24.0F, -1.0F, 1.0F, 10.0F, 2.0F, 0.0F, false);
		GauntletLeft.setTextureOffset(26, 101).addBox(11.0F, -23.0F, -2.0F, 1.0F, 8.0F, 1.0F, 0.0F, false);
		GauntletLeft.setTextureOffset(36, 90).addBox(6.0F, -22.0F, -3.0F, 6.0F, 6.0F, 1.0F, 0.0F, false);
		GauntletLeft.setTextureOffset(0, 24).addBox(6.0F, -22.0F, -2.0F, 1.0F, 6.0F, 4.0F, 0.0F, false);

		ShoulderDecorationLeft = new ModelRenderer(this);
		ShoulderDecorationLeft.setRotationPoint(-9.0F, 30.0F, 0.0F);
		ShoulderLeft.addChild(ShoulderDecorationLeft);
		ShoulderDecorationLeft.setTextureOffset(50, 84).addBox(6.0F, -34.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		ShoulderDecorationLeft.setTextureOffset(102, 25).addBox(6.0F, -34.0F, 3.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		ShoulderDecorationLeft.setTextureOffset(102, 78).addBox(6.0F, -34.0F, -4.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		ShoulderDecorationLeft.setTextureOffset(100, 96).addBox(7.0F, -28.0F, -4.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationLeft.setTextureOffset(100, 10).addBox(7.0F, -28.0F, 3.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationLeft.setTextureOffset(99, 58).addBox(7.0F, -29.0F, -4.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationLeft.setTextureOffset(98, 54).addBox(7.0F, -29.0F, 3.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationLeft.setTextureOffset(99, 56).addBox(8.0F, -30.0F, -4.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationLeft.setTextureOffset(98, 6).addBox(8.0F, -30.0F, 3.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationLeft.setTextureOffset(100, 76).addBox(10.0F, -31.0F, -4.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationLeft.setTextureOffset(100, 8).addBox(10.0F, -31.0F, 3.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationLeft.setTextureOffset(34, 101).addBox(11.0F, -34.0F, -4.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
		ShoulderDecorationLeft.setTextureOffset(80, 67).addBox(11.0F, -34.0F, 3.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
		ShoulderDecorationLeft.setTextureOffset(104, 37).addBox(10.0F, -35.0F, -4.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationLeft.setTextureOffset(102, 86).addBox(10.0F, -35.0F, 3.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationLeft.setTextureOffset(106, 93).addBox(10.0F, -36.0F, -4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationLeft.setTextureOffset(106, 51).addBox(10.0F, -36.0F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationLeft.setTextureOffset(106, 84).addBox(9.0F, -37.0F, -4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationLeft.setTextureOffset(106, 29).addBox(9.0F, -37.0F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Scythe = new ModelRenderer(this);
		Scythe.setRotationPoint(0.0F, 15.0F, 0.0F);
		ShoulderLeft.addChild(Scythe);
		

		Handle = new ModelRenderer(this);
		Handle.setRotationPoint(-9.0F, 15.0F, 28.0F);
		Scythe.addChild(Handle);
		Handle.setTextureOffset(64, 49).addBox(8.0F, -16.0F, -33.0F, 2.0F, 2.0F, 8.0F, 0.0F, false);

		HandleRing = new ModelRenderer(this);
		HandleRing.setRotationPoint(0.0F, 0.0F, 0.0F);
		Handle.addChild(HandleRing);
		HandleRing.setTextureOffset(106, 81).addBox(8.5F, -14.0F, -24.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		HandleRing.setTextureOffset(106, 78).addBox(8.5F, -17.0F, -24.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		HandleRing.setTextureOffset(76, 84).addBox(8.5F, -16.0F, -25.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		HandleRing.setTextureOffset(82, 2).addBox(8.5F, -16.0F, -22.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		HandleTop = new ModelRenderer(this);
		HandleTop.setRotationPoint(0.0F, 0.0F, 0.0F);
		Handle.addChild(HandleTop);
		HandleTop.setTextureOffset(40, 81).addBox(7.0F, -17.0F, -37.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		HandleTop.setTextureOffset(16, 86).addBox(7.0F, -13.0F, -34.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);
		HandleTop.setTextureOffset(42, 55).addBox(7.0F, -12.0F, -33.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		HandleTop.setTextureOffset(99, 13).addBox(7.5F, -18.0F, -36.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		HandleTop.setTextureOffset(104, 34).addBox(8.0F, -19.0F, -35.75F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		Blade = new ModelRenderer(this);
		Blade.setRotationPoint(-9.0F, 15.0F, 28.0F);
		Scythe.addChild(Blade);
		Blade.setTextureOffset(96, 88).addBox(7.5F, -13.0F, -38.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		Blade.setTextureOffset(97, 50).addBox(7.5F, -11.0F, -39.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		Blade.setTextureOffset(67, 91).addBox(7.5F, -10.0F, -41.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		Blade.setTextureOffset(84, 24).addBox(7.5F, -9.0F, -43.0F, 3.0F, 1.0F, 5.0F, 0.0F, false);
		Blade.setTextureOffset(37, 13).addBox(7.5F, -8.0F, -50.0F, 3.0F, 1.0F, 11.0F, 0.0F, false);
		Blade.setTextureOffset(68, 59).addBox(7.5F, -7.0F, -48.0F, 3.0F, 1.0F, 7.0F, 0.0F, false);
		Blade.setTextureOffset(27, 84).addBox(7.5F, -9.0F, -51.0F, 3.0F, 1.0F, 5.0F, 0.0F, false);
		Blade.setTextureOffset(90, 8).addBox(7.5F, -10.0F, -52.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		Blade.setTextureOffset(32, 97).addBox(7.5F, -11.0F, -53.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		Blade.setTextureOffset(94, 63).addBox(7.5F, -13.0F, -54.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		Blade.setTextureOffset(40, 45).addBox(7.5F, -16.0F, -54.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);
		Blade.setTextureOffset(40, 40).addBox(7.5F, -19.0F, -53.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);
		Blade.setTextureOffset(106, 48).addBox(8.0F, -21.0F, -52.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		Blade.setTextureOffset(82, 24).addBox(8.0F, -12.0F, -39.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Blade.setTextureOffset(104, 31).addBox(8.0F, -11.0F, -41.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Blade.setTextureOffset(103, 63).addBox(8.0F, -10.0F, -43.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Blade.setTextureOffset(0, 64).addBox(8.0F, -9.0F, -46.0F, 2.0F, 1.0F, 3.0F, 0.0F, false);
		Blade.setTextureOffset(103, 45).addBox(8.0F, -10.0F, -48.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Blade.setTextureOffset(102, 98).addBox(8.0F, -11.0F, -50.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Blade.setTextureOffset(106, 26).addBox(8.0F, -13.0F, -51.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		Blade.setTextureOffset(81, 59).addBox(8.0F, -16.0F, -52.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
		Blade.setTextureOffset(68, 81).addBox(8.0F, -19.0F, -51.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
		Blade.setTextureOffset(8, 77).addBox(8.5F, -22.0F, -51.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		Blade.setTextureOffset(90, 15).addBox(8.5F, -13.0F, -39.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Blade.setTextureOffset(106, 20).addBox(8.5F, -12.0F, -41.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Blade.setTextureOffset(106, 17).addBox(8.5F, -11.0F, -43.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Blade.setTextureOffset(64, 49).addBox(8.5F, -10.0F, -46.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Blade.setTextureOffset(106, 106).addBox(8.5F, -11.0F, -48.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Blade.setTextureOffset(38, 106).addBox(8.5F, -12.0F, -50.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Blade.setTextureOffset(90, 10).addBox(8.5F, -13.0F, -50.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Blade.setTextureOffset(68, 68).addBox(8.5F, -16.0F, -51.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		Blade.setTextureOffset(6, 24).addBox(8.5F, -19.0F, -50.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		Blade.setTextureOffset(43, 25).addBox(8.5F, -23.0F, -50.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
		Blade.setTextureOffset(82, 5).addBox(8.5F, -24.0F, -49.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		ShoulderRight = new ModelRenderer(this);
		ShoulderRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		Arms.addChild(ShoulderRight);
		ShoulderRight.setTextureOffset(54, 12).addBox(-12.0F, -33.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		ArmRight = new ModelRenderer(this);
		ArmRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		ShoulderRight.addChild(ArmRight);
		ArmRight.setTextureOffset(78, 15).addBox(-11.0F, -27.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);

		GauntletRight = new ModelRenderer(this);
		GauntletRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		ArmRight.addChild(GauntletRight);
		GauntletRight.setTextureOffset(68, 68).addBox(-11.0F, -22.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);
		GauntletRight.setTextureOffset(8, 82).addBox(-12.0F, -23.0F, 1.0F, 1.0F, 8.0F, 1.0F, 0.0F, false);
		GauntletRight.setTextureOffset(96, 96).addBox(-12.0F, -24.0F, -1.0F, 1.0F, 10.0F, 2.0F, 0.0F, false);
		GauntletRight.setTextureOffset(22, 90).addBox(-12.0F, -22.0F, 2.0F, 6.0F, 6.0F, 1.0F, 0.0F, false);
		GauntletRight.setTextureOffset(24, 74).addBox(-12.0F, -23.0F, -2.0F, 1.0F, 8.0F, 1.0F, 0.0F, false);
		GauntletRight.setTextureOffset(82, 88).addBox(-12.0F, -22.0F, -3.0F, 6.0F, 6.0F, 1.0F, 0.0F, false);
		GauntletRight.setTextureOffset(0, 0).addBox(-7.0F, -22.0F, -2.0F, 1.0F, 6.0F, 4.0F, 0.0F, false);

		ShoulderDecorationRight = new ModelRenderer(this);
		ShoulderDecorationRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		ShoulderRight.addChild(ShoulderDecorationRight);
		ShoulderDecorationRight.setTextureOffset(8, 86).addBox(-7.0F, -34.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		ShoulderDecorationRight.setTextureOffset(102, 102).addBox(-7.0F, -34.0F, -4.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		ShoulderDecorationRight.setTextureOffset(102, 17).addBox(-7.0F, -34.0F, 3.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		ShoulderDecorationRight.setTextureOffset(99, 70).addBox(-11.0F, -28.0F, -4.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationRight.setTextureOffset(72, 15).addBox(-11.0F, -28.0F, 3.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationRight.setTextureOffset(95, 94).addBox(-12.0F, -29.0F, -4.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationRight.setTextureOffset(94, 68).addBox(-12.0F, -29.0F, 3.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationRight.setTextureOffset(82, 8).addBox(-13.0F, -30.0F, -4.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationRight.setTextureOffset(50, 60).addBox(-13.0F, -30.0F, 3.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationRight.setTextureOffset(0, 72).addBox(-14.0F, -31.0F, -4.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationRight.setTextureOffset(50, 65).addBox(-14.0F, -31.0F, 3.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationRight.setTextureOffset(12, 68).addBox(-14.0F, -34.0F, -4.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
		ShoulderDecorationRight.setTextureOffset(64, 53).addBox(-14.0F, -34.0F, 3.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
		ShoulderDecorationRight.setTextureOffset(101, 0).addBox(-13.0F, -35.0F, -4.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationRight.setTextureOffset(24, 70).addBox(-13.0F, -35.0F, 3.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationRight.setTextureOffset(105, 101).addBox(-12.0F, -36.0F, -4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationRight.setTextureOffset(104, 39).addBox(-12.0F, -36.0F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationRight.setTextureOffset(26, 86).addBox(-11.0F, -37.0F, -4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		ShoulderDecorationRight.setTextureOffset(84, 34).addBox(-11.0F, -37.0F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Scythe2 = new ModelRenderer(this);
		Scythe2.setRotationPoint(-9.0F, -15.0F, 0.0F);
		ShoulderRight.addChild(Scythe2);
		

		Handle2 = new ModelRenderer(this);
		Handle2.setRotationPoint(-9.0F, 15.0F, 28.0F);
		Scythe2.addChild(Handle2);
		Handle2.setTextureOffset(12, 64).addBox(8.0F, -16.0F, -33.0F, 2.0F, 2.0F, 8.0F, 0.0F, false);

		HandleRing2 = new ModelRenderer(this);
		HandleRing2.setRotationPoint(0.0F, 0.0F, 0.0F);
		Handle2.addChild(HandleRing2);
		HandleRing2.setTextureOffset(105, 66).addBox(8.5F, -14.0F, -24.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		HandleRing2.setTextureOffset(105, 23).addBox(8.5F, -17.0F, -24.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		HandleRing2.setTextureOffset(18, 77).addBox(8.5F, -16.0F, -25.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		HandleRing2.setTextureOffset(62, 49).addBox(8.5F, -16.0F, -22.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		HandleTop2 = new ModelRenderer(this);
		HandleTop2.setRotationPoint(0.0F, 0.0F, 0.0F);
		Handle2.addChild(HandleTop2);
		HandleTop2.setTextureOffset(80, 80).addBox(7.0F, -17.0F, -37.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		HandleTop2.setTextureOffset(24, 67).addBox(7.0F, -13.0F, -34.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);
		HandleTop2.setTextureOffset(40, 33).addBox(7.0F, -12.0F, -33.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		HandleTop2.setTextureOffset(20, 97).addBox(7.5F, -18.0F, -36.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		HandleTop2.setTextureOffset(102, 72).addBox(8.0F, -19.0F, -35.75F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		Blade2 = new ModelRenderer(this);
		Blade2.setRotationPoint(-9.0F, 15.0F, 28.0F);
		Scythe2.addChild(Blade2);
		Blade2.setTextureOffset(50, 55).addBox(7.5F, -13.0F, -38.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		Blade2.setTextureOffset(94, 45).addBox(7.5F, -11.0F, -39.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		Blade2.setTextureOffset(89, 57).addBox(7.5F, -10.0F, -41.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		Blade2.setTextureOffset(83, 66).addBox(7.5F, -9.0F, -43.0F, 3.0F, 1.0F, 5.0F, 0.0F, false);
		Blade2.setTextureOffset(36, 0).addBox(7.5F, -8.0F, -50.0F, 3.0F, 1.0F, 11.0F, 0.0F, false);
		Blade2.setTextureOffset(30, 25).addBox(7.5F, -7.0F, -48.0F, 3.0F, 1.0F, 7.0F, 0.0F, false);
		Blade2.setTextureOffset(82, 2).addBox(7.5F, -9.0F, -51.0F, 3.0F, 1.0F, 5.0F, 0.0F, false);
		Blade2.setTextureOffset(8, 77).addBox(7.5F, -10.0F, -52.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		Blade2.setTextureOffset(84, 76).addBox(7.5F, -11.0F, -53.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		Blade2.setTextureOffset(52, 25).addBox(7.5F, -13.0F, -54.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		Blade2.setTextureOffset(36, 5).addBox(7.5F, -16.0F, -54.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);
		Blade2.setTextureOffset(36, 0).addBox(7.5F, -19.0F, -53.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);
		Blade2.setTextureOffset(61, 106).addBox(8.0F, -21.0F, -52.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		Blade2.setTextureOffset(40, 70).addBox(8.0F, -12.0F, -39.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Blade2.setTextureOffset(101, 60).addBox(8.0F, -11.0F, -41.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Blade2.setTextureOffset(76, 81).addBox(8.0F, -10.0F, -43.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Blade2.setTextureOffset(52, 30).addBox(8.0F, -9.0F, -46.0F, 2.0F, 1.0F, 3.0F, 0.0F, false);
		Blade2.setTextureOffset(52, 81).addBox(8.0F, -10.0F, -48.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Blade2.setTextureOffset(105, 88).addBox(8.0F, -13.0F, -51.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		Blade2.setTextureOffset(74, 40).addBox(8.0F, -11.0F, -50.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Blade2.setTextureOffset(42, 57).addBox(8.0F, -16.0F, -52.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
		Blade2.setTextureOffset(24, 55).addBox(8.0F, -19.0F, -51.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
		Blade2.setTextureOffset(0, 24).addBox(8.5F, -22.0F, -51.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		Blade2.setTextureOffset(78, 17).addBox(8.5F, -13.0F, -39.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Blade2.setTextureOffset(34, 105).addBox(8.5F, -12.0F, -41.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Blade2.setTextureOffset(29, 97).addBox(8.5F, -11.0F, -43.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Blade2.setTextureOffset(12, 64).addBox(8.5F, -10.0F, -46.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Blade2.setTextureOffset(16, 89).addBox(8.5F, -11.0F, -48.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Blade2.setTextureOffset(68, 85).addBox(8.5F, -12.0F, -50.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Blade2.setTextureOffset(40, 72).addBox(8.5F, -13.0F, -50.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Blade2.setTextureOffset(6, 0).addBox(8.5F, -16.0F, -51.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		Blade2.setTextureOffset(0, 0).addBox(8.5F, -19.0F, -50.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		Blade2.setTextureOffset(30, 24).addBox(8.5F, -23.0F, -50.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
		Blade2.setTextureOffset(34, 67).addBox(8.5F, -24.0F, -49.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Leggs = new ModelRenderer(this);
		Leggs.setRotationPoint(2.0F, 2.0F, 0.0F);
		Body.addChild(Leggs);
		Leggs.setTextureOffset(64, 36).addBox(-8.0F, -20.0F, -6.0F, 12.0F, 3.0F, 1.0F, 0.0F, false);
		Leggs.setTextureOffset(53, 6).addBox(-8.0F, -20.0F, 5.0F, 12.0F, 3.0F, 1.0F, 0.0F, false);
		Leggs.setTextureOffset(52, 30).addBox(3.0F, -20.0F, -5.0F, 1.0F, 3.0F, 10.0F, 0.0F, false);
		Leggs.setTextureOffset(52, 52).addBox(-8.0F, -20.0F, -5.0F, 1.0F, 3.0F, 10.0F, 0.0F, false);
		Leggs.setTextureOffset(54, 12).addBox(-3.0F, -21.0F, -7.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		Leggs.setTextureOffset(89, 57).addBox(-4.0F, -20.0F, -7.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Leggs.setTextureOffset(84, 26).addBox(-1.0F, -20.0F, -7.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		Skirt = new ModelRenderer(this);
		Skirt.setRotationPoint(0.0F, 0.0F, 0.0F);
		Leggs.addChild(Skirt);
		Skirt.setTextureOffset(71, 96).addBox(-3.0F, -17.0F, -5.0F, 2.0F, 14.0F, 0.0F, 0.0F, false);
		Skirt.setTextureOffset(84, 95).addBox(-3.0F, -17.0F, 5.0F, 2.0F, 14.0F, 0.0F, 0.0F, false);
		Skirt.setTextureOffset(22, 101).addBox(-4.0F, -17.0F, -5.0F, 1.0F, 13.0F, 0.0F, 0.0F, false);
		Skirt.setTextureOffset(48, 97).addBox(-4.0F, -17.0F, 5.0F, 1.0F, 13.0F, 0.0F, 0.0F, false);
		Skirt.setTextureOffset(20, 101).addBox(-1.0F, -17.0F, -5.0F, 1.0F, 13.0F, 0.0F, 0.0F, false);
		Skirt.setTextureOffset(100, 17).addBox(-1.0F, -17.0F, 5.0F, 1.0F, 13.0F, 0.0F, 0.0F, false);
		Skirt.setTextureOffset(38, 83).addBox(-3.0F, -16.0F, -6.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Skirt.setTextureOffset(64, 59).addBox(-4.0F, -17.0F, -6.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);

		ClothLeft1 = new ModelRenderer(this);
		ClothLeft1.setRotationPoint(2.0F, -17.0F, -4.0F);
		Skirt.addChild(ClothLeft1);
		setRotationAngle(ClothLeft1, -0.0873F, 0.0F, 0.0F);
		ClothLeft1.setTextureOffset(58, 91).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 15.0F, 1.0F, 0.0F, false);

		ClothLeft2 = new ModelRenderer(this);
		ClothLeft2.setRotationPoint(3.0F, -17.0F, -3.0F);
		Skirt.addChild(ClothLeft2);
		setRotationAngle(ClothLeft2, -0.0873F, 0.0F, 0.0F);
		ClothLeft2.setTextureOffset(80, 95).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 14.0F, 1.0F, 0.0F, false);

		ClothLeft3 = new ModelRenderer(this);
		ClothLeft3.setRotationPoint(4.0F, -17.0F, -2.0F);
		Skirt.addChild(ClothLeft3);
		setRotationAngle(ClothLeft3, -0.0873F, 0.0F, 0.0F);
		ClothLeft3.setTextureOffset(67, 96).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 13.0F, 1.0F, 0.0F, false);

		ClothLeft4 = new ModelRenderer(this);
		ClothLeft4.setRotationPoint(4.0F, -17.0F, -1.0F);
		Skirt.addChild(ClothLeft4);
		setRotationAngle(ClothLeft4, -0.0873F, 0.0F, 0.0F);
		ClothLeft4.setTextureOffset(46, 96).addBox(0.0F, 0.0F, -1.0F, 0.0F, 13.0F, 1.0F, 0.0F, false);

		ClothLeft5 = new ModelRenderer(this);
		ClothLeft5.setRotationPoint(4.0F, -17.0F, 0.0F);
		Skirt.addChild(ClothLeft5);
		setRotationAngle(ClothLeft5, -0.0873F, 0.0F, 0.0F);
		ClothLeft5.setTextureOffset(6, 100).addBox(0.0F, 0.0F, -1.0F, 0.0F, 12.0F, 1.0F, 0.0F, false);

		ClothLeft6 = new ModelRenderer(this);
		ClothLeft6.setRotationPoint(4.0F, -17.0F, 0.0F);
		Skirt.addChild(ClothLeft6);
		setRotationAngle(ClothLeft6, 0.0873F, 0.0F, 0.0F);
		ClothLeft6.setTextureOffset(4, 100).addBox(0.0F, 0.0F, 0.0F, 0.0F, 12.0F, 1.0F, 0.0F, false);

		ClothLeft7 = new ModelRenderer(this);
		ClothLeft7.setRotationPoint(4.0F, -17.0F, 1.0F);
		Skirt.addChild(ClothLeft7);
		setRotationAngle(ClothLeft7, 0.0873F, 0.0F, 0.0F);
		ClothLeft7.setTextureOffset(44, 96).addBox(0.0F, 0.0F, 0.0F, 0.0F, 13.0F, 1.0F, 0.0F, false);

		ClothLeft8 = new ModelRenderer(this);
		ClothLeft8.setRotationPoint(4.0F, -17.0F, 2.0F);
		Skirt.addChild(ClothLeft8);
		setRotationAngle(ClothLeft8, 0.0873F, 0.0F, 0.0F);
		ClothLeft8.setTextureOffset(95, 15).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 13.0F, 1.0F, 0.0F, false);

		ClothLeft9 = new ModelRenderer(this);
		ClothLeft9.setRotationPoint(2.0F, -17.0F, 2.0F);
		Skirt.addChild(ClothLeft9);
		setRotationAngle(ClothLeft9, 0.0873F, 0.0F, 0.0F);
		ClothLeft9.setTextureOffset(16, 93).addBox(0.0F, 0.0F, 1.0F, 1.0F, 14.0F, 1.0F, 0.0F, false);

		ClothLeft10 = new ModelRenderer(this);
		ClothLeft10.setRotationPoint(1.0F, -17.0F, 4.0F);
		Skirt.addChild(ClothLeft10);
		setRotationAngle(ClothLeft10, 0.0873F, 0.0F, 0.0F);
		ClothLeft10.setTextureOffset(54, 91).addBox(0.0F, 0.0F, 0.0F, 1.0F, 15.0F, 1.0F, 0.0F, false);

		ClothRight1 = new ModelRenderer(this);
		ClothRight1.setRotationPoint(-5.0F, -17.0F, -4.0F);
		Skirt.addChild(ClothRight1);
		setRotationAngle(ClothRight1, -0.0873F, 0.0F, 0.0F);
		ClothRight1.setTextureOffset(50, 91).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 15.0F, 1.0F, 0.0F, false);

		ClothRight2 = new ModelRenderer(this);
		ClothRight2.setRotationPoint(-6.0F, -17.0F, -3.0F);
		Skirt.addChild(ClothRight2);
		setRotationAngle(ClothRight2, -0.0873F, 0.0F, 0.0F);
		ClothRight2.setTextureOffset(12, 93).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 14.0F, 1.0F, 0.0F, false);

		ClothRight3 = new ModelRenderer(this);
		ClothRight3.setRotationPoint(-7.0F, -17.0F, -2.0F);
		Skirt.addChild(ClothRight3);
		setRotationAngle(ClothRight3, -0.0873F, 0.0F, 0.0F);
		ClothRight3.setTextureOffset(92, 95).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 13.0F, 1.0F, 0.0F, false);

		ClothRight4 = new ModelRenderer(this);
		ClothRight4.setRotationPoint(-7.0F, -17.0F, -1.0F);
		Skirt.addChild(ClothRight4);
		setRotationAngle(ClothRight4, -0.0873F, 0.0F, 0.0F);
		ClothRight4.setTextureOffset(77, 95).addBox(-1.0F, 0.0F, -1.0F, 0.0F, 13.0F, 1.0F, 0.0F, false);

		ClothRight5 = new ModelRenderer(this);
		ClothRight5.setRotationPoint(-7.0F, -17.0F, 0.0F);
		Skirt.addChild(ClothRight5);
		setRotationAngle(ClothRight5, -0.0873F, 0.0F, 0.0F);
		ClothRight5.setTextureOffset(2, 100).addBox(-1.0F, 0.0F, -1.0F, 0.0F, 12.0F, 1.0F, 0.0F, false);

		ClothRight6 = new ModelRenderer(this);
		ClothRight6.setRotationPoint(-7.0F, -17.0F, 0.0F);
		Skirt.addChild(ClothRight6);
		setRotationAngle(ClothRight6, 0.0873F, 0.0F, 0.0F);
		ClothRight6.setTextureOffset(0, 100).addBox(-1.0F, 0.0F, 0.0F, 0.0F, 12.0F, 1.0F, 0.0F, false);

		ClothRight7 = new ModelRenderer(this);
		ClothRight7.setRotationPoint(-7.0F, -17.0F, 1.0F);
		Skirt.addChild(ClothRight7);
		setRotationAngle(ClothRight7, 0.0873F, 0.0F, 0.0F);
		ClothRight7.setTextureOffset(75, 95).addBox(-1.0F, 0.0F, 0.0F, 0.0F, 13.0F, 1.0F, 0.0F, false);

		ClothRight8 = new ModelRenderer(this);
		ClothRight8.setRotationPoint(-7.0F, -17.0F, 2.0F);
		Skirt.addChild(ClothRight8);
		setRotationAngle(ClothRight8, 0.0873F, 0.0F, 0.0F);
		ClothRight8.setTextureOffset(88, 95).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 13.0F, 1.0F, 0.0F, false);

		ClothRight9 = new ModelRenderer(this);
		ClothRight9.setRotationPoint(-6.0F, -17.0F, 3.0F);
		Skirt.addChild(ClothRight9);
		setRotationAngle(ClothRight9, 0.0873F, 0.0F, 0.0F);
		ClothRight9.setTextureOffset(8, 93).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 14.0F, 1.0F, 0.0F, false);

		ClothRight10 = new ModelRenderer(this);
		ClothRight10.setRotationPoint(-5.0F, -17.0F, 4.0F);
		Skirt.addChild(ClothRight10);
		setRotationAngle(ClothRight10, 0.0873F, 0.0F, 0.0F);
		ClothRight10.setTextureOffset(63, 90).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 15.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(ReaperEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}