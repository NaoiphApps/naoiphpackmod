
package net.mcreator.naoiphpack.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.BipedModel;

import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class MitreItem extends NaoiphPackElements.ModElement {
	@ObjectHolder("naoiphpack:mitrehelmet")
	public static final Item helmet = null;
	@ObjectHolder("naoiphpack:mitrebody")
	public static final Item body = null;
	@ObjectHolder("naoiphpack:mitrelegs")
	public static final Item legs = null;
	@ObjectHolder("naoiphpack:mitreboots")
	public static final Item boots = null;
	public MitreItem(NaoiphPackElements instance) {
		super(instance, 94);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 25;
			}

			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{2, 5, 6, 2}[slot.getIndex()];
			}

			public int getEnchantability() {
				return 9;
			}

			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}

			@OnlyIn(Dist.CLIENT)
			public String getName() {
				return "mitre";
			}

			public float getToughness() {
				return 0f;
			}
		};
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.HEAD, new Item.Properties().group(null)) {
			@Override
			@OnlyIn(Dist.CLIENT)
			public BipedModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlotType slot, BipedModel defaultModel) {
				BipedModel armorModel = new BipedModel();
				armorModel.bipedHead = new ModelMitre().Mitre;
				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "naoiphpack:textures/itreexture.png";
			}
		}.setRegistryName("mitrehelmet"));
	}
	// Made with Blockbench
	// Paste this code into your mod.
	// Make sure to generate all required imports
	public static class ModelMitre extends EntityModel<Entity> {
		private final RendererModel Mitre;
		private final RendererModel bone8;
		private final RendererModel bone6;
		private final RendererModel bone5;
		private final RendererModel bone4;
		private final RendererModel bone3;
		private final RendererModel bone2;
		private final RendererModel bone;
		public ModelMitre() {
			textureWidth = 64;
			textureHeight = 64;
			Mitre = new RendererModel(this);
			Mitre.setRotationPoint(0.0F, 29.0F, 0.0F);
			Mitre.cubeList.add(new ModelBox(Mitre, 34, 29, -4.0F, -17.0F, 0.0F, 1, 3, 1, 0.0F, false));
			Mitre.cubeList.add(new ModelBox(Mitre, 12, 38, 3.0F, -17.0F, 0.0F, 1, 3, 1, 0.0F, false));
			bone8 = new RendererModel(this);
			bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
			Mitre.addChild(bone8);
			bone8.cubeList.add(new ModelBox(bone8, 0, 0, -4.0F, -14.0F, -5.0F, 8, 1, 10, 0.0F, false));
			bone6 = new RendererModel(this);
			bone6.setRotationPoint(-1.0F, 0.0F, 9.0F);
			Mitre.addChild(bone6);
			bone6.cubeList.add(new ModelBox(bone6, 0, 0, -1.0F, -20.0F, -5.0F, 4, 6, 1, 0.0F, false));
			bone6.cubeList.add(new ModelBox(bone6, 0, 35, 0.0F, -26.0F, -5.0F, 2, 6, 1, 0.0F, false));
			bone5 = new RendererModel(this);
			bone5.setRotationPoint(-1.0F, 0.0F, 0.0F);
			Mitre.addChild(bone5);
			bone5.cubeList.add(new ModelBox(bone5, 14, 31, -1.0F, -20.0F, -5.0F, 4, 6, 1, 0.0F, false));
			bone5.cubeList.add(new ModelBox(bone5, 6, 35, 0.0F, -26.0F, -5.0F, 2, 6, 1, 0.0F, false));
			bone4 = new RendererModel(this);
			bone4.setRotationPoint(0.0F, 0.0F, 8.0F);
			Mitre.addChild(bone4);
			bone4.cubeList.add(new ModelBox(bone4, 0, 7, -1.0F, -28.0F, -5.0F, 2, 2, 1, 0.0F, false));
			bone4.cubeList.add(new ModelBox(bone4, 20, 24, -3.0F, -20.0F, -5.0F, 6, 6, 1, 0.0F, false));
			bone4.cubeList.add(new ModelBox(bone4, 24, 31, -2.0F, -26.0F, -5.0F, 4, 6, 1, 0.0F, false));
			bone3 = new RendererModel(this);
			bone3.setRotationPoint(0.0F, 0.0F, 1.0F);
			Mitre.addChild(bone3);
			bone3.cubeList.add(new ModelBox(bone3, 14, 28, -1.0F, -28.0F, -5.0F, 2, 2, 1, 0.0F, false));
			bone3.cubeList.add(new ModelBox(bone3, 0, 28, -3.0F, -20.0F, -5.0F, 6, 6, 1, 0.0F, false));
			bone3.cubeList.add(new ModelBox(bone3, 34, 34, -2.0F, -26.0F, -5.0F, 4, 6, 1, 0.0F, false));
			bone2 = new RendererModel(this);
			bone2.setRotationPoint(0.0F, 0.0F, 2.0F);
			Mitre.addChild(bone2);
			bone2.cubeList.add(new ModelBox(bone2, 30, 6, -2.0F, -29.0F, -5.0F, 4, 3, 1, 0.0F, false));
			bone2.cubeList.add(new ModelBox(bone2, 0, 11, -4.0F, -19.0F, -5.0F, 8, 5, 3, 0.0F, false));
			bone2.cubeList.add(new ModelBox(bone2, 20, 20, -4.0F, -21.0F, -5.0F, 8, 2, 2, 0.0F, false));
			bone2.cubeList.add(new ModelBox(bone2, 30, 0, -3.0F, -26.0F, -5.0F, 6, 5, 1, 0.0F, false));
			bone = new RendererModel(this);
			bone.setRotationPoint(0.0F, 0.0F, 0.0F);
			Mitre.addChild(bone);
			bone.cubeList.add(new ModelBox(bone, 0, 19, -4.0F, -21.0F, 1.0F, 8, 7, 2, 0.0F, false));
			bone.cubeList.add(new ModelBox(bone, 22, 11, -3.0F, -26.0F, 1.0F, 6, 5, 2, 0.0F, false));
			bone.cubeList.add(new ModelBox(bone, 34, 24, -2.0F, -29.0F, 1.0F, 4, 3, 2, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			Mitre.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
}
