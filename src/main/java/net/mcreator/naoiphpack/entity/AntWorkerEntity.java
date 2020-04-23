
package net.mcreator.naoiphpack.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.naoiphpack.NaoiphPackElements;

@NaoiphPackElements.ModElement.Tag
public class AntWorkerEntity extends NaoiphPackElements.ModElement {
	public static EntityType entity = null;
	public AntWorkerEntity(NaoiphPackElements instance) {
		super(instance, 103);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("antworker")
						.setRegistryName("antworker");
		elements.entities.add(() -> entity);
		elements.items
				.add(() -> new SpawnEggItem(entity, -10092544, -6697984, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("antworker"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new ModelAntWorker(), 0.5f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("naoiphpack:textures/ntorker.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity {
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
			this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(3, new SwimGoal(this));
			this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, (float) 0.8));
			this.goalSelector.addGoal(5, new PanicGoal(this, 1.2));
			this.targetSelector.addGoal(6, new HurtByTargetGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
		}
	}

	// Made with Blockbench
	// Paste this code into your mod.
	// Make sure to generate all required imports
	public static class ModelAntWorker extends EntityModel<Entity> {
		private final RendererModel bb_main;
		private final RendererModel head;
		private final RendererModel antennae;
		private final RendererModel antennae2;
		private final RendererModel leftlegs;
		private final RendererModel leftlegs2;
		public ModelAntWorker() {
			textureWidth = 64;
			textureHeight = 64;
			bb_main = new RendererModel(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 14, -1.0F, -1.0F, 12.0F, 1, 1, 13, 7.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.0F, -1.0F, -4.0F, 1, 1, 13, 5.0F, false));
			head = new RendererModel(this);
			head.setRotationPoint(0.0F, 24.0F, 0.0F);
			head.cubeList.add(new ModelBox(head, 0, 0, -1.0F, -1.0F, -12.0F, 1, 1, 1, 7.0F, false));
			head.cubeList.add(new ModelBox(head, 0, 6, -6.0F, -5.0F, -24.0F, 1, 1, 5, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 0, 0, 4.0F, -5.0F, -24.0F, 1, 1, 5, 0.0F, false));
			antennae = new RendererModel(this);
			antennae.setRotationPoint(-5.5F, -10.0F, -19.5F);
			setRotationAngle(antennae, 0.6109F, 0.0F, 0.0F);
			head.addChild(antennae);
			antennae.cubeList.add(new ModelBox(antennae, 4, 14, -0.5F, -3.0F, -0.5F, 1, 6, 1, 0.0F, false));
			antennae2 = new RendererModel(this);
			antennae2.setRotationPoint(4.5F, -10.0F, -19.5F);
			setRotationAngle(antennae2, 0.6109F, 0.0F, 0.0F);
			head.addChild(antennae2);
			antennae2.cubeList.add(new ModelBox(antennae2, 0, 14, -0.5F, -3.0F, -0.5F, 1, 6, 1, 0.0F, false));
			leftlegs = new RendererModel(this);
			leftlegs.setRotationPoint(12.0F, 30.5F, 10.5F);
			setRotationAngle(leftlegs, 0.0F, 0.0F, -2.4435F);
			leftlegs.cubeList.add(new ModelBox(leftlegs, 15, 8, -8.234F, -1.1428F, -10.5F, 18, 1, 1, 1.0F, false));
			leftlegs.cubeList.add(new ModelBox(leftlegs, 15, 4, -9.0F, -0.5F, 16.5F, 18, 1, 1, 1.0F, false));
			leftlegs.cubeList.add(new ModelBox(leftlegs, 15, 6, -9.0F, -0.5F, 3.5F, 18, 1, 1, 1.0F, false));
			leftlegs2 = new RendererModel(this);
			leftlegs2.setRotationPoint(-13.0F, 30.5F, 19.1667F);
			setRotationAngle(leftlegs2, 0.0F, 3.1416F, -0.7854F);
			leftlegs2.cubeList.add(new ModelBox(leftlegs2, 15, 2, -9.0F, -0.5F, -9.1667F, 18, 1, 1, 1.0F, false));
			leftlegs2.cubeList.add(new ModelBox(leftlegs2, 15, 0, -9.7071F, 0.2071F, 17.8333F, 18, 1, 1, 1.0F, false));
			leftlegs2.cubeList.add(new ModelBox(leftlegs2, 15, 15, -9.0F, -0.5F, 4.8333F, 18, 1, 1, 1.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			bb_main.render(f5);
			head.render(f5);
			leftlegs.render(f5);
			leftlegs2.render(f5);
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
			this.leftlegs2.rotateAngleZ = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.leftlegs.rotateAngleZ = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		}
	}
}
