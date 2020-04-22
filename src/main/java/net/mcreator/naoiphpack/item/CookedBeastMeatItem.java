
package net.mcreator.naoiphpack.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

import net.mcreator.naoiphpack.itemgroup.NaoiphPackItemGroup;
import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class CookedBeastMeatItem extends NaoiphPackElements.ModElement {
	@ObjectHolder("naoiphpack:cookedbeastmeat")
	public static final Item block = null;
	public CookedBeastMeatItem(NaoiphPackElements instance) {
		super(instance, 91);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(NaoiphPackItemGroup.tab).maxStackSize(64)
					.food((new Food.Builder()).hunger(6).saturation(0.3f).build()));
			setRegistryName("cookedbeastmeat");
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
		}
	}
}
