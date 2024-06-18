package net.stal.alloys.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.stal.alloys.StalAlloys;

public class StalAlloysBlocks {

  // Block: Steel

  public static final Block STEEL_BLOCK = registerBlock(
    "steel_block", 
    new Block(
      FabricBlockSettings
        .copyOf(Blocks.IRON_BLOCK)
        .sounds(BlockSoundGroup.METAL)
        .strength(5.0F, 8.0F)
        .requiresTool()
    )
  );

  // Block: Nickel
  public static final Block NICKEL_ORE = registerBlock(
    "nickel_ore", 
    new ExperienceDroppingBlock(
      FabricBlockSettings
        .copyOf(Blocks.STONE)
        .sounds(BlockSoundGroup.STONE)
        .strength(3.0F, 8.0F)
        .requiresTool(),
      UniformIntProvider.create(3, 8)
    )
  );

  public static final Block DEEPSLATE_NICKEL_ORE = registerBlock(
    "deepslate_nickel_ore", 
    new ExperienceDroppingBlock(
      FabricBlockSettings
        .copyOf(Blocks.STONE)
        .sounds(BlockSoundGroup.DEEPSLATE)
        .strength(4.5F, 8.0F)
        .requiresTool(),
      UniformIntProvider.create(4, 9)
    )
  );

  public static final Block NETHERRACK_NICKEL_ORE = registerBlock(
    "netherrack_nickel_ore", 
    new ExperienceDroppingBlock(
      FabricBlockSettings
        .copyOf(Blocks.STONE)
        .sounds(BlockSoundGroup.NETHERRACK)
        .strength(0.4F)
        .requiresTool(),
      UniformIntProvider.create(4, 9)
    )
  );

  public static final Block NICKEL_BLOCK = registerBlock(
    "nickel_block", 
    new Block(
      FabricBlockSettings
        .copyOf(Blocks.IRON_BLOCK)
        .sounds(BlockSoundGroup.COPPER)
        .strength(5.0F, 8.0F)
        .requiresTool()
    )
  );

  public static final Block CUT_NICKEL = registerBlock(
    "cut_nickel", 
    new Block(
      FabricBlockSettings
        .copyOf(Blocks.IRON_BLOCK)
        .sounds(BlockSoundGroup.COPPER)
        .strength(5.0F, 8.0F)
        .requiresTool()
    )
  );

  public static final Block CUT_NICKEL_SLAB = registerBlock(
    "cut_nickel_slab", 
    new SlabBlock(
      FabricBlockSettings
        .copyOf(Blocks.IRON_BLOCK)
        .sounds(BlockSoundGroup.COPPER)
        .strength(5.0F, 8.0F)
        .requiresTool()
    )
  );

  public static final Block CUT_NICKEL_STAIRS = registerBlock(
    "cut_nickel_stairs",
    new StairsBlock(
      CUT_NICKEL.getDefaultState(), 
      FabricBlockSettings
        .copyOf(Blocks.IRON_BLOCK)
        .sounds(BlockSoundGroup.COPPER)
        .strength(5.0F, 8.0F)
        .requiresTool()
    )
  );

  // Block: Chromium
  public static final Block CHROMIUM_ORE = registerBlock(
    "chromium_ore", 
    new ExperienceDroppingBlock(
      FabricBlockSettings
        .copyOf(Blocks.STONE)
        .sounds(BlockSoundGroup.STONE)
        .strength(3.0F, 8.0F)
        .requiresTool(),
      UniformIntProvider.create(3, 8)
    )
  );

  public static final Block DEEPSLATE_CHROMIUM_ORE = registerBlock(
    "deepslate_chromium_ore", 
    new ExperienceDroppingBlock(
      FabricBlockSettings
        .copyOf(Blocks.STONE)
        .sounds(BlockSoundGroup.DEEPSLATE)
        .strength(4.5F, 8.0F)
        .requiresTool(),
      UniformIntProvider.create(4, 9)
    )
  );

  public static final Block NETHERRACK_CHROMIUM_ORE = registerBlock(
    "netherrack_chromium_ore", 
    new ExperienceDroppingBlock(
      FabricBlockSettings
        .copyOf(Blocks.STONE)
        .sounds(BlockSoundGroup.NETHERRACK)
        .strength(0.4F)
        .requiresTool(),
      UniformIntProvider.create(4, 9)
    )
  );

  public static final Block CHROMIUM_BLOCK = registerBlock(
    "chromium_block", 
    new Block(
      FabricBlockSettings
        .copyOf(Blocks.IRON_BLOCK)
        .sounds(BlockSoundGroup.METAL)
        .strength(5.0F, 8.0F)
        .requiresTool()
    )
  );

  // Block: Carbon
  public static final Block CARBON_ORE = registerBlock(
    "carbon_ore", 
    new ExperienceDroppingBlock(
      FabricBlockSettings
        .copyOf(Blocks.STONE)
        .sounds(BlockSoundGroup.STONE)
        .strength(3.0F, 8.0F)
        .requiresTool(),
      UniformIntProvider.create(3, 8)
    )
  );

  public static final Block DEEPSLATE_CARBON_ORE = registerBlock(
    "deepslate_carbon_ore", 
    new ExperienceDroppingBlock(
      FabricBlockSettings
        .copyOf(Blocks.STONE)
        .sounds(BlockSoundGroup.DEEPSLATE)
        .strength(4.5F, 8.0F)
        .requiresTool(),
      UniformIntProvider.create(4, 9)
    )
  );

  // Block: Zinc
  public static final Block ZINC_ORE = registerBlock(
    "zinc_ore", 
    new ExperienceDroppingBlock(
      FabricBlockSettings
        .copyOf(Blocks.STONE)
        .sounds(BlockSoundGroup.STONE)
        .strength(3.0F, 8.0F)
        .requiresTool(),
      UniformIntProvider.create(3, 8)
    )
  );

  public static final Block DEEPSLATE_ZINC_ORE = registerBlock(
    "deepslate_zinc_ore", 
    new ExperienceDroppingBlock(
      FabricBlockSettings
        .copyOf(Blocks.STONE)
        .sounds(BlockSoundGroup.DEEPSLATE)
        .strength(4.5F, 8.0F)
        .requiresTool(),
      UniformIntProvider.create(4, 9)
    )
  );

  public static final Block ZINC_BLOCK = registerBlock(
    "zinc_block", 
    new Block(
      FabricBlockSettings
        .copyOf(Blocks.IRON_BLOCK)
        .sounds(BlockSoundGroup.METAL)
        .strength(5.0F, 8.0F)
        .requiresTool()
    )
  );

  // Block: Tin
  public static final Block TIN_ORE = registerBlock(
    "tin_ore", 
    new ExperienceDroppingBlock(
      FabricBlockSettings
        .copyOf(Blocks.STONE)
        .sounds(BlockSoundGroup.STONE)
        .strength(3.0F, 8.0F)
        .requiresTool(),
      UniformIntProvider.create(3, 8)
    )
  );

  public static final Block DEEPSLATE_TIN_ORE = registerBlock(
    "deepslate_tin_ore", 
    new ExperienceDroppingBlock(
      FabricBlockSettings
        .copyOf(Blocks.STONE)
        .sounds(BlockSoundGroup.DEEPSLATE)
        .strength(4.5F, 8.0F)
        .requiresTool(),
      UniformIntProvider.create(4, 9)
    )
  );

  // Blocks for Block Entities
  public static final Block ALLOY_SMELTER = registerBlock(
    "alloy_smelter",
    new AlloySmelterBlock(
      FabricBlockSettings
        .copyOf(Blocks.STONE)
        .sounds(BlockSoundGroup.DEEPSLATE)
        .strength(4.5F, 8.0F)
        .requiresTool()
        .luminance((state) -> state.get(AlloySmelterBlock.LIT) ? 15 : 0)
    )
  );

  private static Block registerBlock(String name, Block block) {
    registerBlockItem(name, block);

    return Registry.register(Registries.BLOCK, new Identifier(StalAlloys.MOD_ID, name), block);
  }

  private static Item registerBlockItem(String name, Block block) {
    Item item = Registry.register(Registries.ITEM, new Identifier(StalAlloys.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));

    return item;
  }

  public static void registerModBlocks() {
    StalAlloys.LOGGER.debug("Registering Blocks for " + StalAlloys.MOD_ID);
  }
}
