package net.stal.alloys.screen.slot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.stal.alloys.block.entity.AlloySmelterEntity;

public class AlloySmelterOutputSlot extends Slot {
  private final PlayerEntity player;
  private int amount;


  public AlloySmelterOutputSlot(PlayerEntity player, Inventory inventory, int index, int x, int y) {
    super(inventory, index, x, y);
    this.player = player;
  }

  @Override
  public boolean canInsert(ItemStack stack) {
    return false;
  }

  @Override
  public ItemStack takeStack(int amount) {
    if (this.hasStack()) {
      this.amount += Math.min(amount, this.getStack().getCount());
    }

    return super.takeStack(amount);
  }

  @Override
  public void onTakeItem(PlayerEntity player, ItemStack stack) {
    this.onCrafted(stack);
    super.onTakeItem(player, stack);
  }

  @Override
  protected void onCrafted(ItemStack stack, int amount) {
    this.amount += amount;
    super.onCrafted(stack);
  }

  @Override
  protected void onCrafted(ItemStack stack) {
    stack.onCraftByPlayer(this.player.getWorld(), this.player, this.amount);
    if (this.player instanceof ServerPlayerEntity && this.inventory instanceof AlloySmelterEntity) {
      ((AlloySmelterEntity)this.inventory).dropExperienceForRecipesUsed((ServerPlayerEntity)this.player);
    }
    super.onCrafted(stack, amount);
  }

  // @Override
  // public void onQuickTransfer(ItemStack newItem, ItemStack original) {
  //   stack.onCraft(this.player.world, this.player, this.amount);
  //   if (this.player instanceof ServerPlayerEntity && this.inventory instanceof AlloySmelterEntity) {
  //     ((AlloySmelterEntity)this.inventory).dropExperienceForRecipesUsed((ServerPlayerEntity)this.player);
  //   }
  //   super.onQuickTransfer(newItem, original);
  // }
  
}
