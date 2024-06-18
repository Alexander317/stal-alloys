package net.stal.alloys.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.stal.alloys.StalAlloys;
import net.stal.alloys.block.StalAlloysBlocks;

public class StalAlloysItemGroup {

  public static final RegistryKey<ItemGroup> STAL_ALLOYS = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(StalAlloys.MOD_ID, "stal_alloys"));

  public static ItemGroup STAL_ALLOYS_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, STAL_ALLOYS, 
    FabricItemGroup.builder().displayName(Text.translatable("itemGroup" + "." + StalAlloys.MOD_ID)).icon(() -> new ItemStack(StalAlloysBlocks.ALLOY_SMELTER)).entries((displayContext, entries) -> {
      // Items
      entries.add(StalAlloysItems.STEEL_INGOT);
      entries.add(StalAlloysItems.RAW_NICKEL);
      entries.add(StalAlloysItems.NICKEL_INGOT);
      entries.add(StalAlloysItems.RAW_CHROMIUM);
      entries.add(StalAlloysItems.CHROMIUM_INGOT);
      entries.add(StalAlloysItems.RAW_CARBON);
      entries.add(StalAlloysItems.CARBON_PLATE);
      entries.add(StalAlloysItems.RAW_ZINC);
      entries.add(StalAlloysItems.ZINC_INGOT);
      entries.add(StalAlloysItems.RAW_TIN);
      entries.add(StalAlloysItems.TIN_INGOT);
      entries.add(StalAlloysItems.COPPER_SWORD);
      entries.add(StalAlloysItems.COPPER_PICKAXE);
      entries.add(StalAlloysItems.COPPER_AXE);
      entries.add(StalAlloysItems.COPPER_SHOVEL);
      entries.add(StalAlloysItems.COPPER_HOE);
      entries.add(StalAlloysItems.BRONZE_INGOT);
      entries.add(StalAlloysItems.BRONZE_SWORD);
      entries.add(StalAlloysItems.BRONZE_AXE);
      entries.add(StalAlloysItems.BRONZE_DAGGER);
      entries.add(StalAlloysItems.BRONZE_DIRK);
      entries.add(StalAlloysItems.BRASS_INGOT);
      entries.add(StalAlloysItems.BRASS_HELMET);
      entries.add(StalAlloysItems.BRASS_CHESTPLATE);
      entries.add(StalAlloysItems.BRASS_LEGGINGS);
      entries.add(StalAlloysItems.BRASS_BOOTS);
      entries.add(StalAlloysItems.STEEL_PICKAXE);
      entries.add(StalAlloysItems.STEEL_AXE);
      entries.add(StalAlloysItems.STEEL_SHOVEL);
      entries.add(StalAlloysItems.STEEL_HOE);
      entries.add(StalAlloysItems.STAINLESS_STEEL_INGOT);
      entries.add(StalAlloysItems.STAINLESS_STEEL_HELMET);
      entries.add(StalAlloysItems.STAINLESS_STEEL_CHESTPLATE);
      entries.add(StalAlloysItems.STAINLESS_STEEL_LEGGINGS);
      entries.add(StalAlloysItems.STAINLESS_STEEL_BOOTS);

      // Blocks
      entries.add(StalAlloysBlocks.ALLOY_SMELTER);
      entries.add(StalAlloysBlocks.CARBON_ORE);
      entries.add(StalAlloysBlocks.CHROMIUM_BLOCK);
      entries.add(StalAlloysBlocks.CHROMIUM_ORE);
      entries.add(StalAlloysBlocks.CUT_NICKEL);
      entries.add(StalAlloysBlocks.CUT_NICKEL_SLAB);
      entries.add(StalAlloysBlocks.CUT_NICKEL_STAIRS);
      entries.add(StalAlloysBlocks.DEEPSLATE_CARBON_ORE);
      entries.add(StalAlloysBlocks.DEEPSLATE_CHROMIUM_ORE);
      entries.add(StalAlloysBlocks.DEEPSLATE_NICKEL_ORE);
      entries.add(StalAlloysBlocks.DEEPSLATE_TIN_ORE);
      entries.add(StalAlloysBlocks.DEEPSLATE_ZINC_ORE);
      entries.add(StalAlloysBlocks.NETHERRACK_CHROMIUM_ORE);
      entries.add(StalAlloysBlocks.NETHERRACK_NICKEL_ORE);
      entries.add(StalAlloysBlocks.NICKEL_BLOCK);
      entries.add(StalAlloysBlocks.NICKEL_ORE);
      entries.add(StalAlloysBlocks.STEEL_BLOCK);
      entries.add(StalAlloysBlocks.TIN_ORE);
      entries.add(StalAlloysBlocks.ZINC_BLOCK);
      entries.add(StalAlloysBlocks.ZINC_ORE);
    }).build());

  public static void registerItemGroups() {
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
      entries.add(StalAlloysItems.COPPER_PICKAXE);
      entries.add(StalAlloysItems.COPPER_AXE);
      entries.add(StalAlloysItems.COPPER_SHOVEL);
      entries.add(StalAlloysItems.COPPER_HOE);
      entries.add(StalAlloysItems.BRONZE_INGOT);
      entries.add(StalAlloysItems.BRONZE_AXE);
    });

    ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
      entries.add(StalAlloysItems.COPPER_SWORD);
      entries.add(StalAlloysItems.BRONZE_SWORD);
      entries.add(StalAlloysItems.BRONZE_DAGGER);
      entries.add(StalAlloysItems.BRONZE_DIRK);
      entries.add(StalAlloysItems.STAINLESS_STEEL_HELMET);
      entries.add(StalAlloysItems.STAINLESS_STEEL_CHESTPLATE);
      entries.add(StalAlloysItems.STAINLESS_STEEL_LEGGINGS);
      entries.add(StalAlloysItems.STAINLESS_STEEL_BOOTS);
      entries.add(StalAlloysItems.BRASS_HELMET);
      entries.add(StalAlloysItems.BRASS_CHESTPLATE);
      entries.add(StalAlloysItems.BRASS_LEGGINGS);
      entries.add(StalAlloysItems.BRASS_BOOTS);
    });
  }
}
