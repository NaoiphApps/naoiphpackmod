package net.mcreator.naoiphpack.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.World;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.naoiphpack.NaoiphPackVariables;
import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class BaptismProcedure extends NaoiphPackElements.ModElement {
	public BaptismProcedure(NaoiphPackElements instance) {
		super(instance, 59);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Baptism!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure Baptism!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure Baptism!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure Baptism!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Baptism!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((!((NaoiphPackVariables.WorldVariables.get(world).baptised) == (true)))) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).addExperienceLevel((int) 3);
			world.playSound((PlayerEntity) null, x, y, z,
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.dispenser.fail")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1);
			NaoiphPackVariables.WorldVariables.get(world).baptised = (boolean) (true);
			NaoiphPackVariables.WorldVariables.get(world).syncData(world);
		}
	}
}
