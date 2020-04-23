package net.mcreator.naoiphpack.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.naoiphpack.item.BloodwoodSaplingitemItem;
import net.mcreator.naoiphpack.block.NaoiphRedBlockBlock;
import net.mcreator.naoiphpack.block.BloodwoodSaplingBlock;
import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class BloodwoodSaplingitemRightClickedOnBlockProcedure extends NaoiphPackElements.ModElement {
	public BloodwoodSaplingitemRightClickedOnBlockProcedure(NaoiphPackElements instance) {
		super(instance, 122);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure BloodwoodSaplingitemRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure BloodwoodSaplingitemRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure BloodwoodSaplingitemRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure BloodwoodSaplingitemRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure BloodwoodSaplingitemRightClickedOnBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.GRASS_BLOCK.getDefaultState().getBlock())
				|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NaoiphRedBlockBlock.block.getDefaultState()
						.getBlock())
						|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.DIRT.getDefaultState()
								.getBlock())))) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).inventory
						.clearMatchingItems(p -> new ItemStack(BloodwoodSaplingitemItem.block, (int) (1)).getItem() == p.getItem(), (int) 1);
			world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), BloodwoodSaplingBlock.block.getDefaultState(), 3);
		}
	}
}
