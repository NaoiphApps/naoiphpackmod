// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public static class ModelMitre extends ModelBase {
	private final ModelRenderer Mitre;
	private final ModelRenderer bone8;
	private final ModelRenderer bone6;
	private final ModelRenderer bone5;
	private final ModelRenderer bone4;
	private final ModelRenderer bone3;
	private final ModelRenderer bone2;
	private final ModelRenderer bone;

	public ModelMitre() {
		textureWidth = 64;
		textureHeight = 64;

		Mitre = new ModelRenderer(this);
		Mitre.setRotationPoint(0.0F, 29.0F, 0.0F);
		Mitre.cubeList.add(new ModelBox(Mitre, 34, 29, -4.0F, -17.0F, 0.0F, 1, 3, 1, 0.0F, false));
		Mitre.cubeList.add(new ModelBox(Mitre, 12, 38, 3.0F, -17.0F, 0.0F, 1, 3, 1, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
		Mitre.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 0, 0, -4.0F, -14.0F, -5.0F, 8, 1, 10, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(-1.0F, 0.0F, 9.0F);
		Mitre.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 0, 0, -1.0F, -20.0F, -5.0F, 4, 6, 1, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 0, 35, 0.0F, -26.0F, -5.0F, 2, 6, 1, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-1.0F, 0.0F, 0.0F);
		Mitre.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 14, 31, -1.0F, -20.0F, -5.0F, 4, 6, 1, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 6, 35, 0.0F, -26.0F, -5.0F, 2, 6, 1, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 0.0F, 8.0F);
		Mitre.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 0, 7, -1.0F, -28.0F, -5.0F, 2, 2, 1, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 20, 24, -3.0F, -20.0F, -5.0F, 6, 6, 1, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 24, 31, -2.0F, -26.0F, -5.0F, 4, 6, 1, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 1.0F);
		Mitre.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 14, 28, -1.0F, -28.0F, -5.0F, 2, 2, 1, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 28, -3.0F, -20.0F, -5.0F, 6, 6, 1, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 34, 34, -2.0F, -26.0F, -5.0F, 4, 6, 1, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 2.0F);
		Mitre.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 30, 6, -2.0F, -29.0F, -5.0F, 4, 3, 1, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 11, -4.0F, -19.0F, -5.0F, 8, 5, 3, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 20, 20, -4.0F, -21.0F, -5.0F, 8, 2, 2, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 30, 0, -3.0F, -26.0F, -5.0F, 6, 5, 1, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		Mitre.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 0, 19, -4.0F, -21.0F, 1.0F, 8, 7, 2, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 22, 11, -3.0F, -26.0F, 1.0F, 6, 5, 2, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 34, 24, -2.0F, -29.0F, 1.0F, 4, 3, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Mitre.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}