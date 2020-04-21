// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public static class Modelcustom_model extends ModelBase {
	private final ModelRenderer torso;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer head;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;

	public Modelcustom_model() {
		textureWidth = 16;
		textureHeight = 16;

		torso = new ModelRenderer(this);
		torso.setRotationPoint(0.0F, 24.0F, 0.0F);
		torso.cubeList.add(new ModelBox(torso, 0, 0, -2.0F, -8.0F, 0.0F, 4, 5, 1, 0.0F, false));

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-2.5F, 16.0F, 0.5F);
		rightArm.cubeList.add(new ModelBox(rightArm, 0, 10, -0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F, false));

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(2.5F, 16.0F, 0.5F);
		leftArm.cubeList.add(new ModelBox(leftArm, 8, 8, -0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 16.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 6, -1.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F, false));

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(-1.5F, 20.5F, 0.5F);
		rightLeg.cubeList.add(new ModelBox(rightLeg, 10, 0, -0.5F, 0.5F, -0.5F, 1, 3, 1, 0.0F, false));

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(1.5F, 20.5F, 0.5F);
		leftLeg.cubeList.add(new ModelBox(leftLeg, 4, 10, -0.5F, 0.5F, -0.5F, 1, 3, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		torso.render(f5);
		rightArm.render(f5);
		leftArm.render(f5);
		head.render(f5);
		rightLeg.render(f5);
		leftLeg.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.rightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.rightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.leftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.leftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
	}
}