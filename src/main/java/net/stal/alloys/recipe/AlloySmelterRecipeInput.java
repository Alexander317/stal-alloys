package net.stal.alloys.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;


public record AlloySmelterRecipeInput(ItemStack slotA, ItemStack slotB) implements RecipeInput {


  public AlloySmelterRecipeInput(ItemStack slotA, ItemStack slotB) {
    this.slotA = slotA;
    this.slotB = slotB;
  }

  @Override
  public int getSize() {
    return 2;
  }

  @Override
  public ItemStack getStackInSlot(int slot) {
    ItemStack result;
    switch (slot) {
      case 0:
        result = this.slotA;
        break;
      case 1:
        result = this.slotB;
        break;
      default:
        throw new IllegalArgumentException("Recipe does not contain slot " + slot);
    }

    return result;
  }

  public boolean isEmpty() {
    return this.slotA.isEmpty() && this.slotB.isEmpty();
  }

  public ItemStack slotA() {
    return this.slotA.copy();
  }

  public ItemStack slotB() {
    return this.slotB.copy();
  }
  
}
