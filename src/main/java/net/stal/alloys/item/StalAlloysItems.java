package net.stal.alloys.item;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.stal.alloys.*;
import net.stal.alloys.item.custom.StalAlloysHoeItem;

public class StalAlloysItems {

  public static final Item STEEL_INGOT = registerItem("steel_ingot", new Item(new Item.Settings()));

  public static final Item RAW_NICKEL = registerItem("raw_nickel", new Item(new Item.Settings()));
  public static final Item NICKEL_INGOT = registerItem("nickel_ingot", new Item(new Item.Settings()));

  public static final Item RAW_CHROMIUM = registerItem("raw_chromium", new Item(new Item.Settings()));
  public static final Item CHROMIUM_INGOT = registerItem("chromium_ingot", new Item(new Item.Settings()));

  public static final Item RAW_CARBON = registerItem("raw_carbon", new Item(new Item.Settings()));
  public static final Item CARBON_PLATE = registerItem("carbon_plate", new Item(new Item.Settings()));

  public static final Item RAW_ZINC = registerItem("raw_zinc", new Item(new Item.Settings()));
  public static final Item ZINC_INGOT = registerItem("zinc_ingot", new Item(new Item.Settings()));

  public static final Item RAW_TIN = registerItem("raw_tin", new Item(new Item.Settings()));
  public static final Item TIN_INGOT = registerItem("tin_ingot", new Item(new Item.Settings()));

  public static final Item COPPER_SWORD = registerItem("copper_sword", new SwordItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.IRON, 3, -2.4F))));
  public static final Item COPPER_PICKAXE = registerItem("copper_pickaxe", new PickaxeItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.IRON, 1, -2.8F))));
  public static final Item COPPER_AXE = registerItem("copper_axe", new AxeItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.IRON, 6, -3.1F))));
  public static final Item COPPER_SHOVEL = registerItem("copper_shovel", new ShovelItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.IRON, 1.5F, -3.0F))));
  public static final Item COPPER_HOE = registerItem("copper_hoe", new StalAlloysHoeItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.IRON, 2, -1.0F))));

  public static final Item BRONZE_INGOT = registerItem("bronze_ingot", new Item(new Item.Settings()));
  public static final Item BRONZE_SWORD = registerItem("bronze_sword", new SwordItem(StalAlloysToolMaterials.BRONZE_ALLOY, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(StalAlloysToolMaterials.BRONZE_ALLOY, 3, -2.0F))));
  public static final Item BRONZE_AXE = registerItem("bronze_axe", new AxeItem(StalAlloysToolMaterials.BRONZE_ALLOY, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(StalAlloysToolMaterials.BRONZE_ALLOY, 6, -3.0F))));
  public static final Item BRONZE_DAGGER = registerItem("bronze_dagger", new SwordItem(StalAlloysToolMaterials.BRONZE_ALLOY, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(StalAlloysToolMaterials.BRONZE_ALLOY, 2, -1.0F))));
  public static final Item BRONZE_DIRK = registerItem("bronze_dirk", new SwordItem(StalAlloysToolMaterials.BRONZE_ALLOY, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(StalAlloysToolMaterials.BRONZE_ALLOY, 2, -1.5F))));
  public static final Item BRASS_INGOT = registerItem("brass_ingot", new Item(new Item.Settings()));

  public static final Item STEEL_PICKAXE = registerItem("steel_pickaxe", new PickaxeItem(StalAlloysToolMaterials.STEEL_ALLOY, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(StalAlloysToolMaterials.STEEL_ALLOY, 1, -2.8F))));
  public static final Item STEEL_AXE = registerItem("steel_axe", new AxeItem(StalAlloysToolMaterials.STEEL_ALLOY, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(StalAlloysToolMaterials.STEEL_ALLOY, 6, -3.1F))));
  public static final Item STEEL_SHOVEL = registerItem("steel_shovel", new ShovelItem(StalAlloysToolMaterials.STEEL_ALLOY, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(StalAlloysToolMaterials.STEEL_ALLOY, 1.5F, -3.0F))));
  public static final Item STEEL_HOE = registerItem("steel_hoe", new StalAlloysHoeItem(StalAlloysToolMaterials.STEEL_ALLOY, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(StalAlloysToolMaterials.STEEL_ALLOY, 2, -1.0F))));

  public static final Item BRASS_HELMET = registerItem("brass_helmet", new ArmorItem(StalAlloysArmorMaterials.BRASS, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(7))));
  public static final Item BRASS_CHESTPLATE = registerItem("brass_chestplate", new ArmorItem(StalAlloysArmorMaterials.BRASS, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(7))));
  public static final Item BRASS_LEGGINGS = registerItem("brass_leggings", new ArmorItem(StalAlloysArmorMaterials.BRASS, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(7))));
  public static final Item BRASS_BOOTS = registerItem("brass_boots", new ArmorItem(StalAlloysArmorMaterials.BRASS, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(7))));
  
  public static final Item STAINLESS_STEEL_INGOT = registerItem("stainless_steel_ingot", new Item(new Item.Settings()));

  public static final Item STAINLESS_STEEL_HELMET = registerItem("stainless_steel_helmet", new ArmorItem(StalAlloysArmorMaterials.STAINLESS_STEEL, ArmorItem.Type.HELMET, new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))));
  public static final Item STAINLESS_STEEL_CHESTPLATE = registerItem("stainless_steel_chestplate", new ArmorItem(StalAlloysArmorMaterials.STAINLESS_STEEL, ArmorItem.Type.CHESTPLATE, new Item.Settings().fireproof().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(37))));
  public static final Item STAINLESS_STEEL_LEGGINGS = registerItem("stainless_steel_leggings", new ArmorItem(StalAlloysArmorMaterials.STAINLESS_STEEL, ArmorItem.Type.LEGGINGS, new Item.Settings().fireproof().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(37))));
  public static final Item STAINLESS_STEEL_BOOTS = registerItem("stainless_steel_boots", new ArmorItem(StalAlloysArmorMaterials.STAINLESS_STEEL, ArmorItem.Type.BOOTS, new Item.Settings().fireproof().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(37))));

  private static Item registerItem(String name, Item item) {
    return Registry.register(Registries.ITEM, Identifier.of(StalAlloys.MOD_ID, name), item);
  }

  public static void registerModItems() {
    StalAlloys.LOGGER.debug("Registering Mod Items for " + StalAlloys.MOD_ID);
  }
}
