
package net.mcreator.naoiphpack.block;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.naoiphpack.itemgroup.LimestoneAndMountainItemGroup;
import net.mcreator.naoiphpack.NaoiphPackElements;

import java.util.List;
import java.util.Collections;

@NaoiphPackElements.ModElement.Tag
public class LimeRockBlock extends NaoiphPackElements.ModElement {
	@ObjectHolder("naoiphpack:limerock")
	public static final Block block = null;
	public LimeRockBlock(NaoiphPackElements instance) {
		super(instance, 130);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(
				() -> new BlockItem(block, new Item.Properties().group(LimestoneAndMountainItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.EARTH).sound(SoundType.STONE).hardnessAndResistance(8f, 10f).lightValue(0));
			setRegistryName("limerock");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
