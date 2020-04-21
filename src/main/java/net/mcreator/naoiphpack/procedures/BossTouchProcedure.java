package net.mcreator.naoiphpack.procedures;

import net.minecraft.world.World;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import net.mcreator.naoiphpack.NaoiphPackVariables;
import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class BossTouchProcedure extends NaoiphPackElements.ModElement {
	public BossTouchProcedure(NaoiphPackElements instance) {
		super(instance, 70);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure BossTouch!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure BossTouch!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((NaoiphPackVariables.WorldVariables.get(world).baptised) == (true))) {
			entity.attackEntityFrom(DamageSource.GENERIC, (float) 1);
		} else {
			entity.attackEntityFrom(DamageSource.GENERIC, (float) 4);
		}
	}
}
