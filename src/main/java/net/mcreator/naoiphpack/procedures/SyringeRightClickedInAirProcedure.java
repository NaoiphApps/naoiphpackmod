package net.mcreator.naoiphpack.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.naoiphpack.item.SyringeItem;
import net.mcreator.naoiphpack.item.FilledSyringeItem;
import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class SyringeRightClickedInAirProcedure extends NaoiphPackElements.ModElement {
	public SyringeRightClickedInAirProcedure(NaoiphPackElements instance) {
		super(instance, 56);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SyringeRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity) {
			ItemStack _setstack = new ItemStack(FilledSyringeItem.block, (int) (1));
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
		}
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).inventory.clearMatchingItems(p -> new ItemStack(SyringeItem.block, (int) (1)).getItem() == p.getItem(), (int) 1);
		entity.attackEntityFrom(DamageSource.CACTUS, (float) 6);
	}
}
