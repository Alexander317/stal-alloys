package net.stal.alloys.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.ArmorItem;
import net.stal.alloys.item.StalAlloysItems;

public class StalAlloysModelProvider extends FabricModelProvider{

  public StalAlloysModelProvider(FabricDataOutput output) {
    super(output);
  }

  @Override
  public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

  @Override
  public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    itemModelGenerator.registerArmor((ArmorItem)StalAlloysItems.STAINLESS_STEEL_HELMET);
    itemModelGenerator.registerArmor((ArmorItem)StalAlloysItems.STAINLESS_STEEL_CHESTPLATE);
    itemModelGenerator.registerArmor((ArmorItem)StalAlloysItems.STAINLESS_STEEL_LEGGINGS);
    itemModelGenerator.registerArmor((ArmorItem)StalAlloysItems.STAINLESS_STEEL_BOOTS);
    itemModelGenerator.registerArmor((ArmorItem)StalAlloysItems.BRASS_HELMET);
    itemModelGenerator.registerArmor((ArmorItem)StalAlloysItems.BRASS_CHESTPLATE);
    itemModelGenerator.registerArmor((ArmorItem)StalAlloysItems.BRASS_LEGGINGS);
    itemModelGenerator.registerArmor((ArmorItem)StalAlloysItems.BRASS_BOOTS);
  }
  
}
