package net.stal.alloys.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.recipe.*;
import net.minecraft.world.World;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;

import java.util.List;

public class AlloySmelterRecipe implements Recipe<SimpleInventory> {

  private final ItemStack mOutput;
  private final int mCookingTime;
  private final int mExperience;
  private final List<Ingredient> mRecipeItems;

  private enum AlloySmelterRecipeAttributes {
    INGREDIENTS("ingredients"),
    COOKINGTIME("cookingtime"),
    EXPERIENCE("experience"),
    RESULT("result");

    private String value;
    private AlloySmelterRecipeAttributes(String value) {
      this.value = value;
    }
  }

  private static final int mNumberOfInputs = 2;
  // private static final int mNumberOfOutputs = 1;

  public AlloySmelterRecipe(ItemStack output, List<Ingredient> recipeItems, int cookingtime, int experience) {
    mOutput = output;
    mRecipeItems = recipeItems;
    mCookingTime = cookingtime;
    mExperience = experience;
  }

  @Override
  public boolean matches(SimpleInventory inventory, World world) {
    if (world.isClient()) return false;

    boolean matchA = mRecipeItems.get(0).test(inventory.getStack(0 /* 0 is the first slot */)) && 
                     mRecipeItems.get(1).test(inventory.getStack(1 /* 1 is the second slot */));

    // This is here because the inputs are slot agnostic
    boolean matchB = mRecipeItems.get(1).test(inventory.getStack(0 /* 0 is the first slot */)) && 
                     mRecipeItems.get(0).test(inventory.getStack(1 /* 1 is the second slot */));

    return matchA || matchB;
  }

  @Override
  public ItemStack craft(SimpleInventory inventory, WrapperLookup lookup) {
    return mOutput;
  }

  @Override
  public boolean fits(int width, int height) {
    return true;
  }

  @Override
  public ItemStack getResult(WrapperLookup registriesLookup) {
    return mOutput.copy();
  }

  public int getCookingTime() {
    return mCookingTime;
  }

  public int getExperience() {
    return mExperience;
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return Serializer.INSTANCE;
  }

  @Override
  public RecipeType<?> getType() {
    return Type.INSTANCE;
  }

  public static class Type implements RecipeType<AlloySmelterRecipe> {
    private Type() { }
    public static final Type INSTANCE = new Type();
    public static final String ID = "alloy_smelter";
  }

  public static class Serializer implements RecipeSerializer<AlloySmelterRecipe> {
    public static final Serializer INSTANCE = new Serializer();
    public static final String ID = "alloy_smelter"; // name given in the json file

    public static final MapCodec<AlloySmelterRecipe> CODEC = RecordCodecBuilder.mapCodec(in -> in.group(
      ItemStack.VALIDATED_CODEC.fieldOf(AlloySmelterRecipeAttributes.RESULT.value).forGetter(r -> r.mOutput),
      validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, mNumberOfInputs).fieldOf(AlloySmelterRecipeAttributes.INGREDIENTS.value).forGetter(AlloySmelterRecipe::getIngredients),
      Codecs.NONNEGATIVE_INT.fieldOf(AlloySmelterRecipeAttributes.COOKINGTIME.value).forGetter(AlloySmelterRecipe::getCookingTime),
      Codecs.NONNEGATIVE_INT.fieldOf(AlloySmelterRecipeAttributes.EXPERIENCE.value).forGetter(AlloySmelterRecipe::getExperience)
    ).apply(in, AlloySmelterRecipe::new));

    private static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
      return Ingredient.DISALLOW_EMPTY_CODEC.listOf().flatXmap((ingredients) -> {
        Ingredient[] copyOfIngredients = (Ingredient[])ingredients.stream().filter((ingredient) -> {
           return !ingredient.isEmpty();
        }).toArray((i) -> {
           return new Ingredient[i];
        });
        if (copyOfIngredients.length == 0) {
           return DataResult.error(() -> {
              return "No ingredients for shapeless recipe";
           });
        } else {
           return copyOfIngredients.length > max ? DataResult.error(() -> {
              return "Too many ingredients for shapeless recipe";
           }) : DataResult.success(DefaultedList.copyOf(Ingredient.EMPTY, copyOfIngredients));
        }
     }, DataResult::success);
    }

    public static final PacketCodec<RegistryByteBuf, AlloySmelterRecipe> PACKET_CODEC = PacketCodec.ofStatic(Serializer::write, Serializer::read);

    private static AlloySmelterRecipe read(RegistryByteBuf buf) {
      DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);
      int cookingtime = buf.readInt();
      int experience = buf.readInt();

      for (int i = 0; i < inputs.size(); i++) {
        inputs.set(i, Ingredient.PACKET_CODEC.decode(buf));
      }

      return new AlloySmelterRecipe(/* output --> */ ItemStack.PACKET_CODEC.decode(buf), inputs, cookingtime, experience);
    }

    private static void write(RegistryByteBuf buf, AlloySmelterRecipe recipe) {
      buf.writeInt(recipe.getIngredients().size());

      for (Ingredient ingredient : recipe.getIngredients()) {
        Ingredient.PACKET_CODEC.encode(buf, ingredient);
      }

      ItemStack.PACKET_CODEC.encode(buf, recipe.getResult(null));

      buf.writeInt(recipe.getCookingTime());
      buf.writeInt(recipe.getExperience());
    }

    @Override
    public MapCodec<AlloySmelterRecipe> codec() {
      return CODEC;
    }

    @Override
    public PacketCodec<RegistryByteBuf, AlloySmelterRecipe> packetCodec() {
      return PACKET_CODEC;
    }
  }
}
