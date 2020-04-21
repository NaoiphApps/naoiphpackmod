
package net.mcreator.naoiphpack.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.naoiphpack.itemgroup.NaoiphPackItemGroup;
import net.mcreator.naoiphpack.item.NaoiphAshItemItem;
import net.mcreator.naoiphpack.NaoiphPackElements;

import java.util.List;
import java.util.Collections;

@NaoiphPackElements.ModElement.Tag
public class NaoiphAshBlock extends NaoiphPackElements.ModElement {
	@ObjectHolder("naoiphpack:naoiphash")
	public static final Block block = null;
	public NaoiphAshBlock(NaoiphPackElements instance) {
		super(instance, 8);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(NaoiphPackItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.SAND).hardnessAndResistance(1f, 6f).lightValue(0).harvestLevel(1)
					.harvestTool(ToolType.SHOVEL));
			setRegistryName("naoiphash");
		}

		@Override
		public MaterialColor getMaterialColor(BlockState state, IBlockReader blockAccess, BlockPos pos) {
			return MaterialColor.STONE;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(NaoiphAshItemItem.block, (int) (4)));
		}
	}
}
