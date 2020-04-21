
package net.mcreator.naoiphpack.fuel;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.item.ItemStack;

import net.mcreator.naoiphpack.block.NaoiphAshBlock;
import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class AshfuelFuel extends NaoiphPackElements.ModElement {
	public AshfuelFuel(NaoiphPackElements instance) {
		super(instance, 52);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(NaoiphAshBlock.block, (int) (1)).getItem())
			event.setBurnTime(500);
	}
}
