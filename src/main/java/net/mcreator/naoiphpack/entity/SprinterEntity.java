
package net.mcreator.naoiphpack.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.naoiphpack.itemgroup.NaoiphPackItemGroup;
import net.mcreator.naoiphpack.item.JimmyHeadItem;
import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class SprinterEntity extends NaoiphPackElements.ModElement {
	public static EntityType entity = null;
	public SprinterEntity(NaoiphPackElements instance) {
		super(instance, 79);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("sprinter")
						.setRegistryName("sprinter");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -3355648, -16737946, new Item.Properties().group(NaoiphPackItemGroup.tab))
				.setRegistryName("sprinter"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("naoiphpack:naoiphmaple")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(entity, 20, 3, 10));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				AnimalEntity::func_223315_a);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new Modelsprinter(), 0.5f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("naoiphpack:textures/sprinter.png");
				}
			};
		});
	}
	public static class CustomEntity extends CreatureEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 5;
			setNoAI(false);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1,
					new TemptGoal(this, 1, Ingredient.fromItems(new ItemStack(JimmyHeadItem.block, (int) (1)).getItem()), false));
			this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(4, new SwimGoal(this));
			this.goalSelector.addGoal(5, new LeapAtTargetGoal(this, (float) 0.8));
			this.goalSelector.addGoal(6, new PanicGoal(this, 1.2));
			this.targetSelector.addGoal(7, new HurtByTargetGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Items.PORKCHOP, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.cow.ambient"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.donkey.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.cat.hiss"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof PotionEntity)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public boolean processInteract(PlayerEntity entity, Hand hand) {
			super.processInteract(entity, hand);
			entity.startRiding(this);
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			ItemStack itemstack = entity.getHeldItem(hand);
			return true;
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.5);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1);
		}

		@Override
		public void travel(Vec3d dir) {
			Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
			if (this.isBeingRidden()) {
				this.rotationYaw = entity.rotationYaw;
				this.prevRotationYaw = this.rotationYaw;
				this.rotationPitch = entity.rotationPitch * 0.5F;
				this.setRotation(this.rotationYaw, this.rotationPitch);
				this.jumpMovementFactor = this.getAIMoveSpeed() * 0.15F;
				this.renderYawOffset = entity.rotationYaw;
				this.rotationYawHead = entity.rotationYaw;
				this.stepHeight = 1.0F;
				if (entity instanceof LivingEntity) {
					this.setAIMoveSpeed((float) this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
					float forward = ((LivingEntity) entity).moveForward;
					float strafe = ((LivingEntity) entity).moveStrafing;
					super.travel(new Vec3d(strafe, 0, forward));
				}
				this.prevLimbSwingAmount = this.limbSwingAmount;
				double d1 = this.posX - this.prevPosX;
				double d0 = this.posZ - this.prevPosZ;
				float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
				if (f1 > 1.0F)
					f1 = 1.0F;
				this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
				this.limbSwing += this.limbSwingAmount;
				return;
			}
			this.stepHeight = 0.5F;
			this.jumpMovementFactor = 0.02F;
			super.travel(dir);
		}
	}

	// Made with Blockbench
	// Paste this code into your mod.
	// Make sure to generate all required imports
	public static class Modelsprinter extends EntityModel<Entity> {
		private final RendererModel rightleg;
		private final RendererModel leftleg;
		private final RendererModel head;
		private final RendererModel horn;
		private final RendererModel horn2;
		private final RendererModel torso;
		private final RendererModel tail;
		public Modelsprinter() {
			textureWidth = 32;
			textureHeight = 32;
			rightleg = new RendererModel(this);
			rightleg.setRotationPoint(-4.5F, 25.0F, 0.5F);
			rightleg.cubeList.add(new ModelBox(rightleg, 12, 12, -0.5F, -12.0F, -0.5F, 1, 10, 1, 1.0F, false));
			leftleg = new RendererModel(this);
			leftleg.setRotationPoint(3.5F, 25.0F, 0.5F);
			leftleg.cubeList.add(new ModelBox(leftleg, 8, 8, -0.5F, -12.0F, -0.5F, 1, 10, 1, 1.0F, false));
			head = new RendererModel(this);
			head.setRotationPoint(-0.3F, 16.1F, -6.7F);
			head.cubeList.add(new ModelBox(head, 16, 16, -0.7F, -13.1F, -2.3F, 1, 1, 1, 3.0F, false));
			head.cubeList.add(new ModelBox(head, 12, 10, -2.7F, -14.1F, -6.3F, 2, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 12, 8, 0.3F, -14.1F, -6.3F, 2, 1, 1, 0.0F, false));
			horn = new RendererModel(this);
			horn.setRotationPoint(-2.2F, -2.6F, -1.8F);
			setRotationAngle(horn, 0.0F, 0.0F, -0.4363F);
			head.addChild(horn);
			horn.cubeList.add(new ModelBox(horn, 12, 0, 4.9937F, -15.2822F, -0.5F, 1, 7, 1, 0.0F, false));
			horn2 = new RendererModel(this);
			horn2.setRotationPoint(1.8F, -2.6F, -1.8F);
			setRotationAngle(horn2, 0.0F, 0.0F, 0.4363F);
			head.addChild(horn2);
			horn2.cubeList.add(new ModelBox(horn2, 8, 0, -5.9937F, -15.2822F, -0.5F, 1, 7, 1, 0.0F, false));
			torso = new RendererModel(this);
			torso.setRotationPoint(0.0F, 24.0F, 0.0F);
			torso.cubeList.add(new ModelBox(torso, 16, 0, -1.0F, -19.0F, 0.0F, 1, 1, 1, 6.0F, false));
			tail = new RendererModel(this);
			tail.setRotationPoint(-1.0F, -7.5F, 10.0F);
			setRotationAngle(tail, -1.2217F, 0.0F, 0.0F);
			torso.addChild(tail);
			tail.cubeList.add(new ModelBox(tail, 0, 0, -1.0F, -11.6046F, -12.2762F, 2, 15, 2, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			rightleg.render(f5);
			leftleg.render(f5);
			head.render(f5);
			torso.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.rightleg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.leftleg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		}
	}
}
