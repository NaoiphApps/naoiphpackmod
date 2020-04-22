
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
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.EatGrassGoal;
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
import net.mcreator.naoiphpack.item.RawBeastMeatItem;
import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class BeastEntity extends NaoiphPackElements.ModElement {
	public static EntityType entity = null;
	public BeastEntity(NaoiphPackElements instance) {
		super(instance, 93);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(1.8f, 2.5f)).build("beast")
						.setRegistryName("beast");
		elements.entities.add(() -> entity);
		elements.items
				.add(() -> new SpawnEggItem(entity, -1, -16711783, new Item.Properties().group(NaoiphPackItemGroup.tab)).setRegistryName("beast"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("naoiphpack:naoiphslatebiome")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("naoiphpack:naoiphashbiome")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("naoiphpack:naoiphmaple")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(entity, 20, 3, 30));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::func_223315_a);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new Modelbeast(), 0.8f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("naoiphpack:textures/beast.png");
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
			this.goalSelector.addGoal(1, new SwimGoal(this));
			this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.8));
			this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(4, new EatGrassGoal(this));
			this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(6, new LeapAtTargetGoal(this, (float) 0.8));
			this.goalSelector.addGoal(7, new PanicGoal(this, 1.2));
			this.targetSelector.addGoal(8, new HurtByTargetGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(RawBeastMeatItem.block, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.cow.ambient"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.enderman.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.skeleton_horse.death"));
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
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
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
					float strafe = 0;
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
	public static class Modelbeast extends EntityModel<Entity> {
		private final RendererModel Body;
		private final RendererModel FrontRightLeg;
		private final RendererModel FrontLeftLeg;
		private final RendererModel BackRightLeg;
		private final RendererModel BackLeftLeg;
		private final RendererModel Neck;
		private final RendererModel Head;
		private final RendererModel FrontEyes;
		private final RendererModel Head2;
		private final RendererModel FrontEyes2;
		private final RendererModel Neck2;
		public Modelbeast() {
			textureWidth = 64;
			textureHeight = 64;
			Body = new RendererModel(this);
			Body.setRotationPoint(0.0F, 24.0F, 0.0F);
			Body.cubeList.add(new ModelBox(Body, 0, 0, -3.0F, -28.0F, -3.0F, 6, 2, 23, 5.0F, false));
			FrontRightLeg = new RendererModel(this);
			FrontRightLeg.setRotationPoint(-6.5F, 3.0F, -6.5F);
			FrontRightLeg.cubeList.add(new ModelBox(FrontRightLeg, 12, 0, -0.5F, 1.0F, -0.5F, 1, 20, 1, 1.0F, false));
			FrontLeftLeg = new RendererModel(this);
			FrontLeftLeg.setRotationPoint(6.5F, 3.0F, -6.5F);
			FrontLeftLeg.cubeList.add(new ModelBox(FrontLeftLeg, 8, 0, -0.5F, 1.0F, -0.5F, 1, 20, 1, 1.0F, false));
			BackRightLeg = new RendererModel(this);
			BackRightLeg.setRotationPoint(-6.5F, 3.0F, 23.5F);
			BackRightLeg.cubeList.add(new ModelBox(BackRightLeg, 0, 0, -0.5F, 1.0F, -0.5F, 1, 20, 1, 1.0F, false));
			BackLeftLeg = new RendererModel(this);
			BackLeftLeg.setRotationPoint(6.5F, 3.0F, 23.5F);
			BackLeftLeg.cubeList.add(new ModelBox(BackLeftLeg, 4, 0, -0.5F, 1.0F, -0.5F, 1, 20, 1, 1.0F, false));
			Neck = new RendererModel(this);
			Neck.setRotationPoint(-0.5F, -13.5F, -11.5F);
			setRotationAngle(Neck, 0.6109F, 0.0F, 0.0F);
			Neck.cubeList.add(new ModelBox(Neck, 0, 25, -0.5F, -8.5F, -0.5F, 1, 17, 1, 1.0F, false));
			Head = new RendererModel(this);
			Head.setRotationPoint(-0.9167F, -17.8748F, -15.7032F);
			Head.cubeList.add(new ModelBox(Head, 15, 20, -0.5833F, -5.3742F, -1.5158F, 1, 1, 1, 4.0F, false));
			Head.cubeList.add(new ModelBox(Head, 16, 31, -3.0833F, -17.1252F, -3.2968F, 1, 8, 1, 0.0F, false));
			Head.cubeList.add(new ModelBox(Head, 12, 31, 1.9167F, -17.1252F, -3.2968F, 1, 8, 1, 0.0F, false));
			Head.cubeList.add(new ModelBox(Head, 19, 17, -0.0833F, -4.1252F, -6.2968F, 1, 1, 1, 0.0F, false));
			FrontEyes = new RendererModel(this);
			FrontEyes.setRotationPoint(-0.0833F, -6.6252F, -5.7968F);
			Head.addChild(FrontEyes);
			FrontEyes.cubeList.add(new ModelBox(FrontEyes, 30, 30, 2.0F, -0.5F, -4.5F, 1, 1, 5, 0.0F, false));
			FrontEyes.cubeList.add(new ModelBox(FrontEyes, 23, 26, -3.0F, -0.5F, -4.5F, 1, 1, 5, 0.0F, false));
			Head2 = new RendererModel(this);
			Head2.setRotationPoint(-0.9167F, -17.8748F, 32.2968F);
			setRotationAngle(Head2, 0.0F, 3.1416F, 0.0F);
			Head2.cubeList.add(new ModelBox(Head2, 19, 19, -0.5833F, -5.3742F, -1.5158F, 1, 1, 1, 4.0F, false));
			Head2.cubeList.add(new ModelBox(Head2, 8, 31, -3.0833F, -17.1252F, -3.2968F, 1, 8, 1, 0.0F, false));
			Head2.cubeList.add(new ModelBox(Head2, 4, 31, 1.9167F, -17.1252F, -3.2968F, 1, 8, 1, 0.0F, false));
			Head2.cubeList.add(new ModelBox(Head2, 16, 18, -0.0833F, -4.1252F, -6.2968F, 1, 1, 1, 0.0F, false));
			FrontEyes2 = new RendererModel(this);
			FrontEyes2.setRotationPoint(-0.0833F, -6.6252F, -5.7968F);
			Head2.addChild(FrontEyes2);
			FrontEyes2.cubeList.add(new ModelBox(FrontEyes2, 16, 25, 2.0F, -0.5F, -4.5F, 1, 1, 5, 0.0F, false));
			FrontEyes2.cubeList.add(new ModelBox(FrontEyes2, 4, 25, -3.0F, -0.5F, -4.5F, 1, 1, 5, 0.0F, false));
			Neck2 = new RendererModel(this);
			Neck2.setRotationPoint(-0.5F, -13.5F, 27.5F);
			setRotationAngle(Neck2, 0.6109F, 3.1416F, 0.0F);
			Neck2.cubeList.add(new ModelBox(Neck2, 16, 0, -0.5F, -8.5F, -0.5F, 1, 17, 1, 1.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			Body.render(f5);
			FrontRightLeg.render(f5);
			FrontLeftLeg.render(f5);
			BackRightLeg.render(f5);
			BackLeftLeg.render(f5);
			Neck.render(f5);
			Head.render(f5);
			Head2.render(f5);
			Neck2.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.FrontRightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.FrontEyes2.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.FrontEyes2.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.FrontEyes.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.FrontEyes.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.BackLeftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.FrontLeftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.Head2.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Head2.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.BackRightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}
}
