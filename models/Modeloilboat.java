// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public static class Modeloilboat extends ModelBase {
	private final ModelRenderer bb_main;

	public Modeloilboat() {
		textureWidth = 256;
		textureHeight = 256;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 112, 120, -8.0F, 0.0F, -24.0F, 16, 8, 16, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 112, 96, -8.0F, 0.0F, -8.0F, 16, 8, 16, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 64, 112, -8.0F, 0.0F, 8.0F, 16, 8, 16, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 80, 56, -8.0F, -11.0F, 24.0F, 16, 16, 16, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 80, 0, -8.0F, -9.0F, 31.0F, 16, 21, 16, -4.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 112, -8.0F, -8.0F, -40.0F, 16, 8, 16, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 56, 8.0F, -8.0F, -24.0F, 16, 8, 48, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -24.0F, -8.0F, -24.0F, 16, 8, 48, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}