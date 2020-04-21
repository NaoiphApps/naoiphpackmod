package net.mcreator.naoiphpack.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.naoiphpack.item.SmokegrassSeedsItem;
import net.mcreator.naoiphpack.item.SmokegrassFlowerItem;
import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class SmokegrassDropsProcedure extends NaoiphPackElements.ModElement {
	public SmokegrassDropsProcedure(NaoiphPackElements instance) {
		super(instance, 28);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SmokegrassDrops!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity) {
			ItemStack _setstack = new ItemStack(SmokegrassFlowerItem.block, (int) (1));
			_setstack.setCount(3);
			ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
		}
		if (entity instanceof PlayerEntity) {
			ItemStack _setstack = new ItemStack(SmokegrassSeedsItem.block, (int) (1));
			_setstack.setCount(2);
			ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
		}
	}
}
