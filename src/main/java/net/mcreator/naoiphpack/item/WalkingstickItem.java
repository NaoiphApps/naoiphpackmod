
package net.mcreator.naoiphpack.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import net.mcreator.naoiphpack.procedures.WalkingstickItemInHandTickProcedure;
import net.mcreator.naoiphpack.itemgroup.LimestoneAndMountainItemGroup;
import net.mcreator.naoiphpack.NaoiphPackElements;

import java.util.List;

@NaoiphPackElements.ModElement.Tag
public class WalkingstickItem extends NaoiphPackElements.ModElement {
	@ObjectHolder("naoiphpack:walkingstick")
	public static final Item block = null;
	public WalkingstickItem(NaoiphPackElements instance) {
		super(instance, 146);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(LimestoneAndMountainItemGroup.tab).maxStackSize(1));
			setRegistryName("walkingstick");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("helps walk faster"));
		}

		@Override
		public void inventoryTick(ItemStack itemstack, World world, Entity entity, int slot, boolean selected) {
			super.inventoryTick(itemstack, world, entity, slot, selected);
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			if (selected) {
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				WalkingstickItemInHandTickProcedure.executeProcedure($_dependencies);
			}
		}
	}
}
