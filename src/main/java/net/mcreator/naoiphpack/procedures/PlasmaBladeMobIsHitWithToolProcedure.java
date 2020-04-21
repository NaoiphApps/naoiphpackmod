package net.mcreator.naoiphpack.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class PlasmaBladeMobIsHitWithToolProcedure extends NaoiphPackElements.ModElement {
	public PlasmaBladeMobIsHitWithToolProcedure(NaoiphPackElements instance) {
		super(instance, 45);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure PlasmaBladeMobIsHitWithTool!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setFire((int) 5);
	}
}
