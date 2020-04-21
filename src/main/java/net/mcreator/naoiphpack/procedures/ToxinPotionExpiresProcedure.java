package net.mcreator.naoiphpack.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class ToxinPotionExpiresProcedure extends NaoiphPackElements.ModElement {
	public ToxinPotionExpiresProcedure(NaoiphPackElements instance) {
		super(instance, 34);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ToxinPotionExpires!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.attackEntityFrom(DamageSource.DROWN, (float) 5);
	}
}
