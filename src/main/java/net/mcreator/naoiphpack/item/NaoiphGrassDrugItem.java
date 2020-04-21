
package net.mcreator.naoiphpack.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;
import net.minecraft.entity.LivingEntity;

import net.mcreator.naoiphpack.procedures.NaoiphDrugKillProcedure;
import net.mcreator.naoiphpack.itemgroup.NaoiphPackItemGroup;
import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class NaoiphGrassDrugItem extends NaoiphPackElements.ModElement {
	@ObjectHolder("naoiphpack:naoiphgrassdrug")
	public static final Item block = null;
	public NaoiphGrassDrugItem(NaoiphPackElements instance) {
		super(instance, 3);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(NaoiphPackItemGroup.tab).maxStackSize(16)
					.food((new Food.Builder()).hunger(4).saturation(0.3f).setAlwaysEdible().meat().build()));
			setRegistryName("naoiphgrassdrug");
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
		}

		@Override
		public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
			ItemStack retval = super.onItemUseFinish(itemStack, world, entity);
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				NaoiphDrugKillProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}
	}
}
