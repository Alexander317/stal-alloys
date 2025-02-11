package net.stal.alloys.screen;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.stal.alloys.StalAlloys;

public class StalAlloysScreenHandlers {
  public static ScreenHandlerType<AlloySmelterScreenHandler> ALLOY_SMELTER_SCREEN_HANDLER;

  static {
    ALLOY_SMELTER_SCREEN_HANDLER = new ScreenHandlerType<>(AlloySmelterScreenHandler::new, FeatureFlags.VANILLA_FEATURES);
  }

  public static void registerModScreenHandlers() {
    StalAlloys.LOGGER.debug("Registering screen handlers for " + StalAlloys.MOD_ID);

    Registry.register(Registries.SCREEN_HANDLER, Identifier.of(StalAlloys.MOD_ID, "alloy_smelter"), ALLOY_SMELTER_SCREEN_HANDLER);
  }
}
