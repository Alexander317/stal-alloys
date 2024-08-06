package net.stal.alloys.item;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.item.ArmorItem.Type;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.stal.alloys.StalAlloys;

public class StalAlloysArmorMaterials extends ArmorMaterials{
  public static final RegistryEntry<ArmorMaterial> BRASS = registerArmorMaterial("brass", Util.make(new EnumMap<ArmorItem.Type, Integer>(ArmorItem.Type.class), (map) -> {
    map.put(Type.BOOTS, 1);
    map.put(Type.LEGGINGS, 4);
    map.put(Type.CHESTPLATE, 5);
    map.put(Type.HELMET, 2);
    map.put(Type.BODY, 12);
  }), 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F, 0.1F, () -> {
    return Ingredient.ofItems(new ItemConvertible[]{StalAlloysItems.BRASS_INGOT});
  });
  
  public static final RegistryEntry<ArmorMaterial> STAINLESS_STEEL = registerArmorMaterial("stainless_steel", Util.make(new EnumMap<ArmorItem.Type, Integer>(ArmorItem.Type.class), (map) -> {
    map.put(Type.BOOTS, 3);
    map.put(Type.LEGGINGS, 6);
    map.put(Type.CHESTPLATE, 8);
    map.put(Type.HELMET, 3);
    map.put(Type.BODY, 48);
  }), 25, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5.0F, 0.2F, () -> {
    return Ingredient.ofItems(new ItemConvertible[]{StalAlloysItems.STAINLESS_STEEL_INGOT});
  });

  private static RegistryEntry<ArmorMaterial> registerArmorMaterial(String id, EnumMap<ArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
    return Registry.registerReference(
      Registries.ARMOR_MATERIAL, 
      Identifier.of(StalAlloys.MOD_ID, id), 
      new ArmorMaterial(
        defense, 
        enchantability, 
        equipSound, 
        repairIngredient, 
        List.of(new ArmorMaterial.Layer(Identifier.of(StalAlloys.MOD_ID, id))), 
        toughness, 
        knockbackResistance
      )
    );
  }

  public static void registerModArmorMaterials() {
    StalAlloys.LOGGER.debug("Registering Armor Materials for " + StalAlloys.MOD_ID);
  }
}
