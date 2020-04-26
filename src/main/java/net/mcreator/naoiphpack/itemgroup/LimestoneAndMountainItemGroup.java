
package net.mcreator.naoiphpack.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.naoiphpack.block.LimeRockGrassBlock;
import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class LimestoneAndMountainItemGroup extends NaoiphPackElements.ModElement {
	public LimestoneAndMountainItemGroup(NaoiphPackElements instance) {
		super(instance, 142);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tablimestoneandmountain") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(LimeRockGrassBlock.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
