package net.stal.alloys.item.custom;

import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;

public class StalAlloysHoeItem extends HoeItem {

  /** This exists to make the constructor of HoeItem accessible... cuz its private ????
   * @param material
   * @param attackDamage
   * @param attackSpeed
   * @param settings
   */
  public StalAlloysHoeItem(ToolMaterial material, Settings settings) {
    super(material, settings);
  }
}
