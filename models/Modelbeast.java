// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public static class Modelbeast extends ModelBase {
	private final ModelRenderer Body;
	private final ModelRenderer FrontRightLeg;
	private final ModelRenderer FrontLeftLeg;
	private final ModelRenderer BackRightLeg;
	private final ModelRenderer BackLeftLeg;
	private final ModelRenderer Neck;
	private final ModelRenderer Head;
	private final ModelRenderer FrontEyes;
	private final ModelRenderer Head2;
	private final ModelRenderer FrontEyes2;
	private final ModelRenderer Neck2;

	public Modelbeast() {
		textureWidth = 64;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 0, 0, -3.0F, -28.0F, -3.0F, 6, 2, 23, 5.0F, false));

		FrontRightLeg = new ModelRenderer(this);
		FrontRightLeg.setRotationPoint(-6.5F, 3.0F, -6.5F);
		FrontRightLeg.cubeList.add(new ModelBox(FrontRightLeg, 12, 0, -0.5F, 1.0F, -0.5F, 1, 20, 1, 1.0F, false));

		FrontLeftLeg = new ModelRenderer(this);
		FrontLeftLeg.setRotationPoint(6.5F, 3.0F, -6.5F);
		FrontLeftLeg.cubeList.add(new ModelBox(FrontLeftLeg, 8, 0, -0.5F, 1.0F, -0.5F, 1, 20, 1, 1.0F, false));

		BackRightLeg = new ModelRenderer(this);
		BackRightLeg.setRotationPoint(-6.5F, 3.0F, 23.5F);
		BackRightLeg.cubeList.add(new ModelBox(BackRightLeg, 0, 0, -0.5F, 1.0F, -0.5F, 1, 20, 1, 1.0F, false));

		BackLeftLeg = new ModelRenderer(this);
		BackLeftLeg.setRotationPoint(6.5F, 3.0F, 23.5F);
		BackLeftLeg.cubeList.add(new ModelBox(BackLeftLeg, 4, 0, -0.5F, 1.0F, -0.5F, 1, 20, 1, 1.0F, false));

		Neck = new ModelRenderer(this);
		Neck.setRotationPoint(-0.5F, -13.5F, -11.5F);
		setRotationAngle(Neck, 0.6109F, 0.0F, 0.0F);
		Neck.cubeList.add(new ModelBox(Neck, 0, 25, -0.5F, -8.5F, -0.5F, 1, 17, 1, 1.0F, false));

		Head = new ModelRenderer(this);
		Head.setRotationPoint(-0.9167F, -17.8748F, -15.7032F);
		Head.cubeList.add(new ModelBox(Head, 15, 20, -0.5833F, -5.3742F, -1.5158F, 1, 1, 1, 4.0F, false));
		Head.cubeList.add(new ModelBox(Head, 16, 31, -3.0833F, -17.1252F, -3.2968F, 1, 8, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 12, 31, 1.9167F, -17.1252F, -3.2968F, 1, 8, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 19, 17, -0.0833F, -4.1252F, -6.2968F, 1, 1, 1, 0.0F, false));

		FrontEyes = new ModelRenderer(this);
		FrontEyes.setRotationPoint(-0.0833F, -6.6252F, -5.7968F);
		Head.addChild(FrontEyes);
		FrontEyes.cubeList.add(new ModelBox(FrontEyes, 30, 30, 2.0F, -0.5F, -4.5F, 1, 1, 5, 0.0F, false));
		FrontEyes.cubeList.add(new ModelBox(FrontEyes, 23, 26, -3.0F, -0.5F, -4.5F, 1, 1, 5, 0.0F, false));

		Head2 = new ModelRenderer(this);
		Head2.setRotationPoint(-0.9167F, -17.8748F, 32.2968F);
		setRotationAngle(Head2, 0.0F, 3.1416F, 0.0F);
		Head2.cubeList.add(new ModelBox(Head2, 19, 19, -0.5833F, -5.3742F, -1.5158F, 1, 1, 1, 4.0F, false));
		Head2.cubeList.add(new ModelBox(Head2, 8, 31, -3.0833F, -17.1252F, -3.2968F, 1, 8, 1, 0.0F, false));
		Head2.cubeList.add(new ModelBox(Head2, 4, 31, 1.9167F, -17.1252F, -3.2968F, 1, 8, 1, 0.0F, false));
		Head2.cubeList.add(new ModelBox(Head2, 16, 18, -0.0833F, -4.1252F, -6.2968F, 1, 1, 1, 0.0F, false));

		FrontEyes2 = new ModelRenderer(this);
		FrontEyes2.setRotationPoint(-0.0833F, -6.6252F, -5.7968F);
		Head2.addChild(FrontEyes2);
		FrontEyes2.cubeList.add(new ModelBox(FrontEyes2, 16, 25, 2.0F, -0.5F, -4.5F, 1, 1, 5, 0.0F, false));
		FrontEyes2.cubeList.add(new ModelBox(FrontEyes2, 4, 25, -3.0F, -0.5F, -4.5F, 1, 1, 5, 0.0F, false));

		Neck2 = new ModelRenderer(this);
		Neck2.setRotationPoint(-0.5F, -13.5F, 27.5F);
		setRotationAngle(Neck2, 0.6109F, 3.1416F, 0.0F);
		Neck2.cubeList.add(new ModelBox(Neck2, 16, 0, -0.5F, -8.5F, -0.5F, 1, 17, 1, 1.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Body.render(f5);
		FrontRightLeg.render(f5);
		FrontLeftLeg.render(f5);
		BackRightLeg.render(f5);
		BackLeftLeg.render(f5);
		Neck.render(f5);
		Head.render(f5);
		Head2.render(f5);
		Neck2.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.FrontRightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.FrontEyes2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.FrontEyes2.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.FrontEyes.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.FrontEyes.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.BackLeftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.FrontLeftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.Head2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Head2.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.BackRightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
	}
}