package net.stal.alloys.datagen;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

public class StalAlloysRecipeProvider extends FabricRecipeProvider{

  public StalAlloysRecipeProvider(FabricDataOutput output) {
    super(output);
  }

  @Override
  public void generate(Consumer<RecipeJsonProvider> exporter) {}
  
}
