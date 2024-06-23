package net.stal.alloys.block.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Lists;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.stal.alloys.screen.AlloySmelterScreenHandler;
import net.stal.alloys.StalAlloys;
import net.stal.alloys.block.AlloySmelterBlock;
import net.stal.alloys.recipe.*;

public class AlloySmelterEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
  public static final int mAlloySmelterProgressBarWidth = 44;
  public static final int mAlloySmelterProgressBarHeight = 17;
  public static final int mAlloySmelterFuelGaugeWidth = 14;
  public static final int mAlloySmelterFuelGaugeHeight = 25;
  public static final int mAlloySmelterInventorySize = 4;
  public static final int mAlloySmelterPropertyDelegateSize = 4;

  private final DefaultedList<ItemStack> mInventory = DefaultedList.ofSize(mAlloySmelterInventorySize, ItemStack.EMPTY);

  private final Object2IntOpenHashMap<Identifier> recipesUsed = new Object2IntOpenHashMap<Identifier>();

  protected final PropertyDelegate mPropertyDelegate;
  private int mProgress = 0;
  private int mMaxProgress = 200; // Number of ticks it takes to smelt
  private int mFuel = 0;
  private int mMaxFuel = 24000;
  private static int mAddFuelAmount = 3000;

  private static final String mProgressPropertyNBTKey = "alloy_smelter.progress";
  private static final String mFuelPropertyNBTKey = "alloy_smelter.fuel";
  private static final String mRecipesUsedNBTKey = "alloy_smelter.RecipesUsed";

  public static enum AlloySmelterInventorySlots {
    FIRST(0),  // Input slot A
    SECOND(1), // Input slot B
    THIRD(2),  // Output slot
    FOURTH(3); // Fuel slot

    private final int value;
    private AlloySmelterInventorySlots(int value) {
      this.value = value;
    }
  }

  public AlloySmelterEntity(BlockPos pos, BlockState state) {
    super(StalAlloysBlockEntities.ALLOY_SMELTER_ENTITY, pos, state);

    mPropertyDelegate = new PropertyDelegate() {
      public int get(int index) {
        switch (index) {
          case 0: return AlloySmelterEntity.this.mProgress;
          case 1: return AlloySmelterEntity.this.mMaxProgress;
          case 2: return AlloySmelterEntity.this.mFuel;
          case 3: return AlloySmelterEntity.this.mMaxFuel;
          default: return 0;
        }
      }

      public void set(int index, int value) {
        switch (index) {
          case 0: AlloySmelterEntity.this.mProgress = value; break;
          case 1: AlloySmelterEntity.this.mMaxProgress = value; break;
          case 2: AlloySmelterEntity.this.mFuel = value; break;
          case 3: AlloySmelterEntity.this.mMaxFuel = value; break;
        }
      }

      public int size() {
        return mAlloySmelterPropertyDelegateSize;
      }
    };
  }

  private void resetProgress() {
    mProgress = 0;
  }

  @Override
  public DefaultedList<ItemStack> getItems() {
    return mInventory;
  }

  @Override
  public Text getDisplayName() {
    return Text.translatable("container." + StalAlloys.MOD_ID + ".alloy_smelter");
  }

  @Override
  public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
    return new AlloySmelterScreenHandler(syncId, playerInventory, this, mPropertyDelegate);
  }

  @Override
  protected void writeNbt(NbtCompound nbt, WrapperLookup registryLookup) {
    super.writeNbt(nbt, registryLookup);

    nbt.putInt(mProgressPropertyNBTKey, mProgress);
    nbt.putInt(mFuelPropertyNBTKey, mFuel);
    Inventories.writeNbt(nbt, mInventory, registryLookup);

    NbtCompound nbtCompound = new NbtCompound();
    this.recipesUsed.forEach((identifier, count) -> nbtCompound.putInt(identifier.toString(), (int)count));
    nbt.put(mRecipesUsedNBTKey, nbtCompound);
  }

  @Override
  public void readNbt(NbtCompound nbt, WrapperLookup registryLookup) {
    super.readNbt(nbt, registryLookup);

    Inventories.readNbt(nbt, mInventory, registryLookup);

    mProgress = nbt.getInt(mProgressPropertyNBTKey);
    mFuel = nbt.getInt(mFuelPropertyNBTKey);

    NbtCompound nbtCompound = nbt.getCompound(mRecipesUsedNBTKey);
    for (String string : nbtCompound.getKeys()) {
      this.recipesUsed.put(Identifier.of(string), nbtCompound.getInt(string));
    }
  }

  public void setLastRecipe(@Nullable RecipeEntry<AlloySmelterRecipe> recipe) {
    if (recipe != null) {
        Identifier identifier = recipe.id();
        this.recipesUsed.addTo(identifier, 1);
    }
  }

  public static <E extends BlockEntity> void tick(World world, BlockPos blockPos, BlockState blockState, AlloySmelterEntity entity) {
    if (world.isClient()) return;

    // If progress is ticking then the block is LIT
    blockState = blockState.with(AlloySmelterBlock.LIT, entity.mFuel > 0 || entity.mProgress > 0);
    world.setBlockState(blockPos, blockState, Block.NOTIFY_ALL);

    checkBucketSlot(entity);

    if (entity.mFuel > 0) {
      // decrement fuel counter
      entity.mFuel--;
      // handle overflow
      if (entity.mFuel < 0) entity.mFuel = 0;
    }

    if (hasRecipe(entity) && entity.mFuel > 0) {
      entity.mProgress++;
      markDirty(world, blockPos, blockState);

      if (entity.mProgress >= entity.mMaxProgress) {
        craftItem(entity);
      }
    } else {
      entity.resetProgress();
      markDirty(world, blockPos, blockState);
    }
  }

  private static void checkBucketSlot(AlloySmelterEntity entity) {
    ItemStack fuelStack = entity.mInventory.get(AlloySmelterInventorySlots.FOURTH.value);

    if (!fuelStack.isEmpty()) {
      if (fuelStack.isOf(Items.LAVA_BUCKET)) {
        fuelStack.decrement(1);
        entity.mInventory.set(AlloySmelterInventorySlots.FOURTH.value, new ItemStack(Items.BUCKET));

        if (entity.mFuel + mAddFuelAmount > entity.mMaxFuel) {
          entity.mFuel = entity.mMaxFuel;
        } else {
          entity.mFuel = entity.mFuel + mAddFuelAmount;
        }
      }
    }
  }

  private static boolean hasRecipe(AlloySmelterEntity entity) {
    SimpleInventory inventory = new SimpleInventory(entity.size());

    for (int i = 0; i < entity.size(); i++) {
      inventory.setStack(i, entity.getStack(i));
    }
    
    Optional<RecipeEntry<AlloySmelterRecipe>> recipeFromInventoryOpt = entity.getWorld()
      .getRecipeManager()
      .getFirstMatch(
        AlloySmelterRecipe.AlloySmelterRecipeType.INSTANCE, 
        new AlloySmelterRecipeInput(
          inventory.getStack(AlloySmelterInventorySlots.FIRST.value), 
          inventory.getStack(AlloySmelterInventorySlots.SECOND.value)
        ), 
        entity.getWorld()
      );
    
    if (recipeFromInventoryOpt.isPresent()) {
      AlloySmelterRecipe recipe = recipeFromInventoryOpt.get().value();
      
      entity.mMaxProgress = recipe.getCookingTime();

      return canInsertAmountIntoOutputSlot(inventory) &&
             canInsertItemIntoOutputSlot(inventory, recipe.getResult(null).getItem());
    }
    
    return false;
  }

  private static void craftItem(AlloySmelterEntity entity) {
    SimpleInventory inventory = new SimpleInventory(entity.size());

    for (int i = 0; i < entity.size(); i++) {
      inventory.setStack(i, entity.getStack(i));
    }

    Optional<RecipeEntry<AlloySmelterRecipe>> recipeFromInventoryOpt = entity.getWorld()
      .getRecipeManager()
      .getFirstMatch(
        AlloySmelterRecipe.AlloySmelterRecipeType.INSTANCE, 
        new AlloySmelterRecipeInput(
          inventory.getStack(AlloySmelterInventorySlots.FIRST.value), 
          inventory.getStack(AlloySmelterInventorySlots.SECOND.value)
        ), 
        entity.getWorld()
      );

    if (recipeFromInventoryOpt.isPresent() && hasRecipe(entity)) {
      AlloySmelterRecipe recipe = recipeFromInventoryOpt.get().value();

      entity.removeStack(AlloySmelterInventorySlots.FIRST.value, 1);
      entity.removeStack(AlloySmelterInventorySlots.SECOND.value, 1);

      entity.setStack(
        AlloySmelterInventorySlots.THIRD.value, 
        new ItemStack(
          recipe.getResult(null).getItem(), 
          entity.getStack(AlloySmelterInventorySlots.THIRD.value).getCount() + 1
        )
      );

      entity.setLastRecipe(recipeFromInventoryOpt.get());

      entity.resetProgress();
    }
  }

  private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, Item output) {
    return inventory.getStack(AlloySmelterInventorySlots.THIRD.value).getItem() == output || 
           inventory.getStack(AlloySmelterInventorySlots.THIRD.value).isEmpty();
  }

  private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
    return inventory.getStack(AlloySmelterInventorySlots.THIRD.value).getMaxCount() > 
           (inventory.getStack(AlloySmelterInventorySlots.THIRD.value).getCount());
  }

  public void dropExperienceForRecipesUsed(ServerPlayerEntity player) {
    List<RecipeEntry<?>> recipes = this.getRecipesUsedAndDropExperience(player.getServerWorld(), player.getPos());
    player.unlockRecipes(recipes);
    this.recipesUsed.clear();
}

@SuppressWarnings("unchecked")
public List<RecipeEntry<?>> getRecipesUsedAndDropExperience(ServerWorld world, Vec3d pos) {
    ArrayList<RecipeEntry<?>> list = Lists.newArrayList();
    for (Object2IntMap.Entry<Identifier> entry : this.recipesUsed.object2IntEntrySet()) {
        world.getRecipeManager().get((Identifier)entry.getKey()).ifPresent(recipe -> {
            list.add((RecipeEntry<?>)recipe);
            AlloySmelterEntity.dropExperience(world, pos, entry.getIntValue(), ((RecipeEntry<AlloySmelterRecipe>)recipe).value().getExperience());
        });
    }
    return list;
}

  private static void dropExperience(ServerWorld world, Vec3d pos, int multiplier, float experience) {
    int amount = MathHelper.floor((float)multiplier * experience);
    float f = MathHelper.fractionalPart((float)multiplier * experience);
    if (f != 0.0f && Math.random() < (double)f) {
        ++amount;
    }
    ExperienceOrbEntity.spawn(world, pos, amount);
}

}
