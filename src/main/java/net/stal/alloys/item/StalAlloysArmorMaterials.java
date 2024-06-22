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

  public static final RegistryEntry<ArmorMaterial> BRASS;
  public static final RegistryEntry<ArmorMaterial> STAINLESS_STEEL;

  private static RegistryEntry<ArmorMaterial> register(String id, EnumMap<ArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
      List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(new Identifier(id)));
      return register(id, defense, enchantability, equipSound, toughness, knockbackResistance, repairIngredient, list);
   }

   private static RegistryEntry<ArmorMaterial> register(String id, EnumMap<ArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient, List<ArmorMaterial.Layer> layers) {
      EnumMap<ArmorItem.Type, Integer> enumMap = new EnumMap<ArmorItem.Type, Integer>(ArmorItem.Type.class);
      ArmorItem.Type[] var9 = Type.values();
      int var10 = var9.length;

      for(int var11 = 0; var11 < var10; ++var11) {
         ArmorItem.Type type = var9[var11];
         enumMap.put(type, (Integer)defense.get(type));
      }

      return Registry.registerReference(Registries.ARMOR_MATERIAL, new Identifier(id), new ArmorMaterial(enumMap, enchantability, equipSound, repairIngredient, layers, toughness, knockbackResistance));
   }

  static {
    /**
     DIAMOND = register("diamond", (EnumMap)Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
         map.put(Type.BOOTS, 3);
         map.put(Type.LEGGINGS, 6);
         map.put(Type.CHESTPLATE, 8);
         map.put(Type.HELMET, 3);
         map.put(Type.BODY, 11);
      }), 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
         return Ingredient.ofItems(new ItemConvertible[]{Items.DIAMOND});
      });
     */
    BRASS = register("brass", Util.make(new EnumMap<ArmorItem.Type, Integer>(ArmorItem.Type.class), (map) -> {
      map.put(Type.BOOTS, 1);
      map.put(Type.LEGGINGS, 4);
      map.put(Type.CHESTPLATE, 5);
      map.put(Type.HELMET, 2);
      map.put(Type.BODY, 8);
    }), 12, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.0F, 0.1F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{StalAlloysItems.BRASS_INGOT});
    });

    STAINLESS_STEEL = register("brass", Util.make(new EnumMap<ArmorItem.Type, Integer>(ArmorItem.Type.class), (map) -> {
      map.put(Type.BOOTS, 3);
      map.put(Type.LEGGINGS, 6);
      map.put(Type.CHESTPLATE, 8);
      map.put(Type.HELMET, 3);
      map.put(Type.BODY, 12);
    }), 48, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5.0F, 0.2F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{StalAlloysItems.STAINLESS_STEEL_INGOT});
    });
  }

  public static void registerModArmorMaterials() {
    StalAlloys.LOGGER.debug("Registering Armor Materials for " + StalAlloys.MOD_ID);
  }

  // BRASS("brass", 12, new int[]{1, 4, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0f, 0.1f, () -> Ingredient.ofItems(StalAlloysItems.BRASS_INGOT)),
  // STAINLESS_STEEL("stainless_steel", 48, new int[]{4, 7, 9, 4}, 25, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5.0f, 0.2f, () -> Ingredient.ofItems(StalAlloysItems.STAINLESS_STEEL_INGOT));

  // private static final int[] BASE_DURABILITY;
  // private final String name;
  // private final int durabilityMultiplier;
  // private final int[] protectionAmounts;
  // private final int enchantability;
  // private final SoundEvent equipSound;
  // private final float toughness;
  // private final float knockbackResistance;
  // private final Supplier<Ingredient> repairIngredientSupplier;

  // private StalAlloysArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredientSupplier) {
  //   this.name = name;
  //   this.durabilityMultiplier = durabilityMultiplier;
  //   this.protectionAmounts = protectionAmounts;
  //   this.enchantability = enchantability;
  //   this.equipSound = equipSound;
  //   this.toughness = toughness;
  //   this.knockbackResistance = knockbackResistance;
  //   this.repairIngredientSupplier = repairIngredientSupplier;
  // }

  // @Override
  // public int getDurability(Type armorItemType) {
  //   return BASE_DURABILITY[armorItemType.getEquipmentSlot().getEntitySlotId()] * this.durabilityMultiplier;
  // }

  // @Override
  // public int getProtection(Type armorItemType) {
  //   return this.protectionAmounts[armorItemType.getEquipmentSlot().getEntitySlotId()];
  // }

  // @Override
  // public int getEnchantability() {
  //   return this.enchantability;
  // }

  // @Override
  // public SoundEvent getEquipSound() {
  //   return this.equipSound;
  // }

  // @Override
  // public Ingredient getRepairIngredient() {
  //   return this.repairIngredientSupplier.get();
  // }

  // @Override
  // public String getName() {
  //   return StalAlloys.MOD_ID + ":" + this.name;
  // }

  // @Override
  // public float getToughness() {
  //   return this.toughness;
  // }

  // @Override
  // public float getKnockbackResistance() {
  //   return this.knockbackResistance;
  // }

  // static {
  //   BASE_DURABILITY = new int[]{13, 15, 16, 11};
  // }

  
}
