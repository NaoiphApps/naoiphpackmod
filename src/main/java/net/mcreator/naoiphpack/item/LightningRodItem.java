
package net.mcreator.naoiphpack.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ActionResult;
import net.minecraft.network.IPacket;
import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.EntityRenderer;

import net.mcreator.naoiphpack.procedures.LightningRodBulletHitsSomethingProcedure;
import net.mcreator.naoiphpack.itemgroup.NaoiphPackItemGroup;
import net.mcreator.naoiphpack.NaoiphPackElements;

import com.mojang.blaze3d.platform.GlStateManager;

@NaoiphPackElements.ModElement.Tag
public class LightningRodItem extends NaoiphPackElements.ModElement {
	@ObjectHolder("naoiphpack:lightningrod")
	public static final Item block = null;
	@ObjectHolder("naoiphpack:entitybulletlightningrod")
	public static final EntityType arrow = null;
	public LightningRodItem(NaoiphPackElements instance) {
		super(instance, 110);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemRanged());
		elements.entities.add(() -> (EntityType.Builder.<ArrowCustomEntity>create(ArrowCustomEntity::new, EntityClassification.MISC)
				.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(ArrowCustomEntity::new)
				.size(0.5f, 0.5f)).build("entitybulletlightningrod").setRegistryName("entitybulletlightningrod"));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void init(FMLCommonSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(ArrowCustomEntity.class, renderManager -> {
			return new CustomRender(renderManager);
		});
	}
	public static class ItemRanged extends Item {
		public ItemRanged() {
			super(new Item.Properties().group(NaoiphPackItemGroup.tab).maxDamage(100));
			setRegistryName("lightningrod");
		}

		@Override
		public UseAction getUseAction(ItemStack stack) {
			return UseAction.BOW;
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
			entity.setActiveHand(hand);
			return new ActionResult(ActionResultType.SUCCESS, entity.getHeldItem(hand));
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 72000;
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
		}

		@Override
		public void onUsingTick(ItemStack itemstack, LivingEntity entityLiving, int count) {
			World world = entityLiving.world;
			if (!world.isRemote && entityLiving instanceof ServerPlayerEntity) {
				ServerPlayerEntity entity = (ServerPlayerEntity) entityLiving;
				int slotID = -1;
				for (int i = 0; i < entity.inventory.mainInventory.size(); i++) {
					ItemStack stack = entity.inventory.mainInventory.get(i);
					if (stack != null && stack.getItem() == new ItemStack(RedquartzItem.block, (int) (1)).getItem()) {
						slotID = i;
						break;
					}
				}
				if (entity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, itemstack) > 0 || slotID != -1) {
					float power = 5f;
					ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, entity, world);
					entityarrow.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, power * 2, 0);
					entityarrow.setSilent(true);
					entityarrow.setIsCritical(false);
					entityarrow.setDamage(1);
					entityarrow.setKnockbackStrength(0);
					entityarrow.setFire(100);
					itemstack.damageItem(1, entity, e -> e.sendBreakAnimation(entity.getActiveHand()));
					int x = (int) entity.posX;
					int y = (int) entity.posY;
					int z = (int) entity.posZ;
					world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.blaze.hurt")),
							SoundCategory.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
					if (entity.abilities.isCreativeMode) {
						entityarrow.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
					} else {
						ItemStack stack = entity.inventory.getStackInSlot(slotID);
						if (new ItemStack(RedquartzItem.block, (int) (1)).isDamageable()) {
							if (stack.attemptDamageItem(1, random, entity)) {
								stack.shrink(1);
								stack.setDamage(0);
								if (stack.isEmpty())
									entity.inventory.deleteStack(stack);
							}
						} else {
							stack.shrink(1);
							if (stack.isEmpty())
								entity.inventory.deleteStack(stack);
						}
					}
					world.addEntity(entityarrow);
				}
				entity.stopActiveHand();
			}
		}
	}

	@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
	public static class ArrowCustomEntity extends AbstractArrowEntity implements IRendersAsItem {
		public ArrowCustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			super(arrow, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, World world) {
			super(type, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, double x, double y, double z, World world) {
			super(type, x, y, z, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, LivingEntity entity, World world) {
			super(type, entity, world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack getItem() {
			return null;
		}

		@Override
		protected ItemStack getArrowStack() {
			return new ItemStack(RedquartzItem.block, (int) (1));
		}

		@Override
		protected void arrowHit(LivingEntity entity) {
			super.arrowHit(entity);
			entity.setArrowCountInEntity(entity.getArrowCountInEntity() - 1);
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			World world = this.world;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				LightningRodBulletHitsSomethingProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		public void tick() {
			super.tick();
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			World world = this.world;
			Entity entity = this.getShooter();
			if (this.inGround) {
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					LightningRodBulletHitsSomethingProcedure.executeProcedure($_dependencies);
				}
				this.remove();
			}
		}
	}

	public static class CustomRender extends EntityRenderer<ArrowCustomEntity> {
		private static final ResourceLocation texture = new ResourceLocation("naoiphpack:textures/slug.png");
		public CustomRender(EntityRendererManager renderManager) {
			super(renderManager);
		}

		@Override
		public void doRender(ArrowCustomEntity bullet, double d, double d1, double d2, float f, float f1) {
			this.bindEntityTexture(bullet);
			GlStateManager.pushMatrix();
			GlStateManager.translatef((float) d, (float) d1, (float) d2);
			GlStateManager.rotatef(f, 0, 1, 0);
			GlStateManager.rotatef(90f - bullet.prevRotationPitch - (bullet.rotationPitch - bullet.prevRotationPitch) * f1, 1, 0, 0);
			EntityModel model = new Modelslug();
			model.render(bullet, 0, 0, 0, 0, 0, 0.0625f);
			GlStateManager.popMatrix();
		}

		@Override
		protected ResourceLocation getEntityTexture(ArrowCustomEntity entity) {
			return texture;
		}
	}

	// Made with Blockbench
	// Paste this code into your mod.
	// Make sure to generate all required imports
	public static class Modelslug extends EntityModel<Entity> {
		private final RendererModel bb_main;
		public Modelslug() {
			textureWidth = 16;
			textureHeight = 16;
			bb_main = new RendererModel(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			setRotationAngle(bb_main, 1.5708F, 0.0F, 0.0F);
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			bb_main.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
}
