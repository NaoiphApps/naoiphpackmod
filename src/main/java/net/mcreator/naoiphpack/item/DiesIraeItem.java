
package net.mcreator.naoiphpack.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.Item;

import net.mcreator.naoiphpack.itemgroup.NaoiphPackItemGroup;
import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class DiesIraeItem extends NaoiphPackElements.ModElement {
	@ObjectHolder("naoiphpack:diesirae")
	public static final Item block = null;
	public DiesIraeItem(NaoiphPackElements instance) {
		super(instance, 76);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}
	public static class MusicDiscItemCustom extends MusicDiscItem {
		public MusicDiscItemCustom() {
			super(0, NaoiphPackElements.sounds.get(new ResourceLocation("naoiphpack:deisirae")),
					new Item.Properties().group(NaoiphPackItemGroup.tab).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("diesirae");
		}
	}
}
