package net.stal.alloys.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.ItemTags;
import net.stal.alloys.item.StalAlloysItems;

public class StalAlloysItemTagProvider extends FabricTagProvider.ItemTagProvider {

  public StalAlloysItemTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
    super(output, completableFuture);
  }

  @Override
  protected void configure(WrapperLookup arg) {
    getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
      .add(
        StalAlloysItems.STAINLESS_STEEL_HELMET, 
        StalAlloysItems.STAINLESS_STEEL_CHESTPLATE, 
        StalAlloysItems.STAINLESS_STEEL_LEGGINGS, 
        StalAlloysItems.STAINLESS_STEEL_BOOTS,
        StalAlloysItems.BRASS_HELMET,
        StalAlloysItems.BRASS_CHESTPLATE,
        StalAlloysItems.BRASS_LEGGINGS,
        StalAlloysItems.BRASS_BOOTS
      );

    getOrCreateTagBuilder(ItemTags.SWORDS)
      .add(
        StalAlloysItems.BRONZE_SWORD,
        StalAlloysItems.BRONZE_DIRK,
        StalAlloysItems.BRONZE_DAGGER,
        StalAlloysItems.COPPER_SWORD
      );
    
    getOrCreateTagBuilder(ItemTags.AXES)
      .add(
        StalAlloysItems.STEEL_AXE,
        StalAlloysItems.BRONZE_AXE,
        StalAlloysItems.COPPER_AXE
      );
    
    getOrCreateTagBuilder(ItemTags.PICKAXES)
      .add(
        StalAlloysItems.STEEL_PICKAXE,
        StalAlloysItems.COPPER_PICKAXE
      );
    
    getOrCreateTagBuilder(ItemTags.SHOVELS)
      .add(
        StalAlloysItems.STEEL_SHOVEL,
        StalAlloysItems.COPPER_SHOVEL
      );
    
    getOrCreateTagBuilder(ItemTags.HOES)
      .add(
        StalAlloysItems.STEEL_HOE,
        StalAlloysItems.COPPER_HOE
      );

    getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
      .add(
        StalAlloysItems.STAINLESS_STEEL_HELMET,
        StalAlloysItems.BRASS_HELMET
      );

    getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
      .add(
        StalAlloysItems.STAINLESS_STEEL_CHESTPLATE,
        StalAlloysItems.BRASS_CHESTPLATE
      );

    getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
      .add(
        StalAlloysItems.STAINLESS_STEEL_LEGGINGS,
        StalAlloysItems.BRASS_LEGGINGS
      );

    getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
      .add(
        StalAlloysItems.STAINLESS_STEEL_BOOTS,
        StalAlloysItems.BRASS_BOOTS
      );
  }
  
}
