// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public static class ModelAntWorker extends ModelBase {
	private final ModelRenderer bb_main;
	private final ModelRenderer head;
	private final ModelRenderer antennae;
	private final ModelRenderer antennae2;
	private final ModelRenderer leftlegs;
	private final ModelRenderer leftlegs2;

	public ModelAntWorker() {
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 14, -1.0F, -1.0F, 12.0F, 1, 1, 13, 7.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.0F, -1.0F, -4.0F, 1, 1, 13, 5.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -1.0F, -1.0F, -12.0F, 1, 1, 1, 7.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 6, -6.0F, -5.0F, -24.0F, 1, 1, 5, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 0, 4.0F, -5.0F, -24.0F, 1, 1, 5, 0.0F, false));

		antennae = new ModelRenderer(this);
		antennae.setRotationPoint(-5.5F, -10.0F, -19.5F);
		setRotationAngle(antennae, 0.6109F, 0.0F, 0.0F);
		head.addChild(antennae);
		antennae.cubeList.add(new ModelBox(antennae, 4, 14, -0.5F, -3.0F, -0.5F, 1, 6, 1, 0.0F, false));

		antennae2 = new ModelRenderer(this);
		antennae2.setRotationPoint(4.5F, -10.0F, -19.5F);
		setRotationAngle(antennae2, 0.6109F, 0.0F, 0.0F);
		head.addChild(antennae2);
		antennae2.cubeList.add(new ModelBox(antennae2, 0, 14, -0.5F, -3.0F, -0.5F, 1, 6, 1, 0.0F, false));

		leftlegs = new ModelRenderer(this);
		leftlegs.setRotationPoint(12.0F, 30.5F, 10.5F);
		setRotationAngle(leftlegs, 0.0F, 0.0F, -2.4435F);
		leftlegs.cubeList.add(new ModelBox(leftlegs, 15, 8, -8.234F, -1.1428F, -10.5F, 18, 1, 1, 1.0F, false));
		leftlegs.cubeList.add(new ModelBox(leftlegs, 15, 4, -9.0F, -0.5F, 16.5F, 18, 1, 1, 1.0F, false));
		leftlegs.cubeList.add(new ModelBox(leftlegs, 15, 6, -9.0F, -0.5F, 3.5F, 18, 1, 1, 1.0F, false));

		leftlegs2 = new ModelRenderer(this);
		leftlegs2.setRotationPoint(-13.0F, 30.5F, 19.1667F);
		setRotationAngle(leftlegs2, 0.0F, 3.1416F, -0.7854F);
		leftlegs2.cubeList.add(new ModelBox(leftlegs2, 15, 2, -9.0F, -0.5F, -9.1667F, 18, 1, 1, 1.0F, false));
		leftlegs2.cubeList.add(new ModelBox(leftlegs2, 15, 0, -9.7071F, 0.2071F, 17.8333F, 18, 1, 1, 1.0F, false));
		leftlegs2.cubeList.add(new ModelBox(leftlegs2, 15, 15, -9.0F, -0.5F, 4.8333F, 18, 1, 1, 1.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
		head.render(f5);
		leftlegs.render(f5);
		leftlegs2.render(f5);
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
		this.leftlegs2.rotateAngleZ = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.leftlegs.rotateAngleZ = MathHelper.cos(f * 1.0F) * -1.0F * f1;
	}
}