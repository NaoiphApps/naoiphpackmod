package net.mcreator.naoiphpack.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;

import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class BossAttackProcedure extends NaoiphPackElements.ModElement {
	public BossAttackProcedure(NaoiphPackElements instance) {
		super(instance, 71);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure BossAttack!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure BossAttack!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure BossAttack!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure BossAttack!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((Math.random() < 6)) {
			if (world instanceof ServerWorld)
				((ServerWorld) world).addLightningBolt(new LightningBoltEntity(world, (int) (x + 8), (int) y, (int) z, false));
			if (world instanceof ServerWorld)
				((ServerWorld) world).addLightningBolt(new LightningBoltEntity(world, (int) (x - 8), (int) y, (int) z, false));
			if (world instanceof ServerWorld)
				((ServerWorld) world).addLightningBolt(new LightningBoltEntity(world, (int) x, (int) y, (int) (z + 8), false));
			if (world instanceof ServerWorld)
				((ServerWorld) world).addLightningBolt(new LightningBoltEntity(world, (int) x, (int) y, (int) (z - 8), false));
		} else {
			if (!world.isRemote) {
				Entity entityToSpawn = new TNTEntity(EntityType.TNT, world);
				entityToSpawn.setLocationAndAngles((x + 8), y, z, world.rand.nextFloat() * 360F, 0);
				world.addEntity(entityToSpawn);
			}
			if (!world.isRemote) {
				Entity entityToSpawn = new TNTEntity(EntityType.TNT, world);
				entityToSpawn.setLocationAndAngles((x - 8), y, z, world.rand.nextFloat() * 360F, 0);
				world.addEntity(entityToSpawn);
			}
			if (!world.isRemote) {
				Entity entityToSpawn = new TNTEntity(EntityType.TNT, world);
				entityToSpawn.setLocationAndAngles(x, y, (z + 8), world.rand.nextFloat() * 360F, 0);
				world.addEntity(entityToSpawn);
			}
			if (!world.isRemote) {
				Entity entityToSpawn = new TNTEntity(EntityType.TNT, world);
				entityToSpawn.setLocationAndAngles(x, y, (z - 8), world.rand.nextFloat() * 360F, 0);
				world.addEntity(entityToSpawn);
			}
		}
	}
}
