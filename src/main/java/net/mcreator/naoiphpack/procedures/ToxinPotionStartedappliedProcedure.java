package net.mcreator.naoiphpack.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class ToxinPotionStartedappliedProcedure extends NaoiphPackElements.ModElement {
	public ToxinPotionStartedappliedProcedure(NaoiphPackElements instance) {
		super(instance, 35);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ToxinPotionStartedapplied!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setFire((int) 2);
	}
}
