package net.stal.alloys.item;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import com.google.common.base.Suppliers;
import java.util.Objects;

public enum StalAlloysToolMaterials implements ToolMaterial {
  STEEL_ALLOY(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1600, 9.0f, 3.0f, 12, () ->  {
    return Ingredient.ofItems(StalAlloysItems.STEEL_INGOT);
  }),
  BRONZE_ALLOY(BlockTags.INCORRECT_FOR_IRON_TOOL, 500, 6.0f, 2.0f, 16, () -> {
    return Ingredient.ofItems(StalAlloysItems.BRONZE_INGOT);
  }),
  STAINLESS_STEEL_ALLOY(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2048, 9.0f, 4.0f, 15, () -> {
    return Ingredient.ofItems(StalAlloysItems.STEEL_INGOT);
  });
  
  // Currently unused, may use in the future to expand upon existing tools and armor
  // BRASS_ALLOY(MiningLevels.STONE, 768, 12.0f, 0.0f, 22, () -> Ingredient.ofItems(StalAlloysItems.BRASS_INGOT)),
  // FERROCHROME_ALLOY(MiningLevels.DIAMOND, 1600, 8.0f, 3.0f, 10, () -> Ingredient.ofItems(StalAlloysItems.CHROMIUM_INGOT)),

  private final TagKey<Block> inverseTag;
  private final int itemDurability;
  private final float miningSpeed;
  private final float attackDamage;
  private final int enchantability;
  private final Supplier<Ingredient> repairIngredient;

  private StalAlloysToolMaterials(final TagKey<Block> inverseTag, final int itemDurability, final float miningSpeed, final float attackDamage, final int enchantability, final Supplier<Ingredient> repairIngredient) {
    this.inverseTag = inverseTag;
    this.itemDurability = itemDurability;
    this.miningSpeed = miningSpeed;
    this.attackDamage = attackDamage;
    this.enchantability = enchantability;
    Objects.requireNonNull(repairIngredient);
    this.repairIngredient = Suppliers.memoize(repairIngredient::get);
 }

  @Override
  public int getDurability() {
    return this.itemDurability;
  }

  @Override
  public float getMiningSpeedMultiplier() {
    return this.miningSpeed;
  }

  @Override
  public float getAttackDamage() {
    return this.attackDamage;
  }

  @Override
  public TagKey<Block> getInverseTag() {
    return this.inverseTag;
  }

  @Override
  public int getEnchantability() {
    return this.enchantability;
  }

  @Override
  public Ingredient getRepairIngredient() {
    return this.repairIngredient.get();
  }
  
}
