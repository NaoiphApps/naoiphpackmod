// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public static class Modelsprinter extends ModelBase {
	private final ModelRenderer rightleg;
	private final ModelRenderer leftleg;
	private final ModelRenderer head;
	private final ModelRenderer horn;
	private final ModelRenderer horn2;
	private final ModelRenderer torso;
	private final ModelRenderer tail;

	public Modelsprinter() {
		textureWidth = 32;
		textureHeight = 32;

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(-4.5F, 25.0F, 0.5F);
		rightleg.cubeList.add(new ModelBox(rightleg, 12, 12, -0.5F, -12.0F, -0.5F, 1, 10, 1, 1.0F, false));

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(3.5F, 25.0F, 0.5F);
		leftleg.cubeList.add(new ModelBox(leftleg, 8, 8, -0.5F, -12.0F, -0.5F, 1, 10, 1, 1.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(-0.3F, 16.1F, -6.7F);
		head.cubeList.add(new ModelBox(head, 16, 16, -0.7F, -13.1F, -2.3F, 1, 1, 1, 3.0F, false));
		head.cubeList.add(new ModelBox(head, 12, 10, -2.7F, -14.1F, -6.3F, 2, 1, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 12, 8, 0.3F, -14.1F, -6.3F, 2, 1, 1, 0.0F, false));

		horn = new ModelRenderer(this);
		horn.setRotationPoint(-2.2F, -2.6F, -1.8F);
		setRotationAngle(horn, 0.0F, 0.0F, -0.4363F);
		head.addChild(horn);
		horn.cubeList.add(new ModelBox(horn, 12, 0, 4.9937F, -15.2822F, -0.5F, 1, 7, 1, 0.0F, false));

		horn2 = new ModelRenderer(this);
		horn2.setRotationPoint(1.8F, -2.6F, -1.8F);
		setRotationAngle(horn2, 0.0F, 0.0F, 0.4363F);
		head.addChild(horn2);
		horn2.cubeList.add(new ModelBox(horn2, 8, 0, -5.9937F, -15.2822F, -0.5F, 1, 7, 1, 0.0F, false));

		torso = new ModelRenderer(this);
		torso.setRotationPoint(0.0F, 24.0F, 0.0F);
		torso.cubeList.add(new ModelBox(torso, 16, 0, -1.0F, -19.0F, 0.0F, 1, 1, 1, 6.0F, false));

		tail = new ModelRenderer(this);
		tail.setRotationPoint(-1.0F, -7.5F, 10.0F);
		setRotationAngle(tail, -1.2217F, 0.0F, 0.0F);
		torso.addChild(tail);
		tail.cubeList.add(new ModelBox(tail, 0, 0, -1.0F, -11.6046F, -12.2762F, 2, 15, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		rightleg.render(f5);
		leftleg.render(f5);
		head.render(f5);
		torso.render(f5);
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
		this.rightleg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.leftleg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
	}
}