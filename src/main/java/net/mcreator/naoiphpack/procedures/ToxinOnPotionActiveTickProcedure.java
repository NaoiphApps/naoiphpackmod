package net.mcreator.naoiphpack.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class ToxinOnPotionActiveTickProcedure extends NaoiphPackElements.ModElement {
	public ToxinOnPotionActiveTickProcedure(NaoiphPackElements instance) {
		super(instance, 33);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ToxinOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) > 1)) {
			entity.attackEntityFrom(DamageSource.DROWN, (float) 1);
		}
	}
}
