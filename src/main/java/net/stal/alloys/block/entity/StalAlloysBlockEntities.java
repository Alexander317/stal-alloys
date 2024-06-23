package net.stal.alloys.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.stal.alloys.StalAlloys;
import net.stal.alloys.block.StalAlloysBlocks;

public class StalAlloysBlockEntities {
  public static BlockEntityType<AlloySmelterEntity> ALLOY_SMELTER_ENTITY;

  public static void registerModBlockEntities() {
    StalAlloys.LOGGER.debug("Registering Block Entities for " + StalAlloys.MOD_ID);
    
    ALLOY_SMELTER_ENTITY = Registry.register(
      Registries.BLOCK_ENTITY_TYPE, 
      Identifier.of(
        StalAlloys.MOD_ID, 
        "alloy_smelter"
      ), 
      BlockEntityType.Builder.create(AlloySmelterEntity::new, StalAlloysBlocks.ALLOY_SMELTER).build(null)
    );
  }
}
