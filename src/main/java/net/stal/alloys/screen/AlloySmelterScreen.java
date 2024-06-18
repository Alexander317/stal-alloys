package net.stal.alloys.screen;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.stal.alloys.StalAlloys;
import net.stal.alloys.block.entity.AlloySmelterEntity;

public class AlloySmelterScreen extends HandledScreen<AlloySmelterScreenHandler> {

  private static final Identifier ALLOY_SMELTER_SCREEN_TEXTURE = new Identifier(StalAlloys.MOD_ID, "textures/gui/container/alloy_smelter.png");

  public AlloySmelterScreen(AlloySmelterScreenHandler handler, PlayerInventory inventory, Text title) {
    super(handler, inventory, title);
  }

  protected void init() {
    super.init();

    // Centers the title
    titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
  }

  @Override
  protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
    RenderSystem.setShader(GameRenderer::getPositionTexProgram);
    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    RenderSystem.setShaderTexture(0, ALLOY_SMELTER_SCREEN_TEXTURE);

    int x = (width - backgroundWidth) / 2;
    int y = (height - backgroundHeight) / 2;

    context.drawTexture(ALLOY_SMELTER_SCREEN_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
    renderFuelGauge(context, x, y);
    renderProgressionArrow(context, x, y);
  }

  private void renderFuelGauge(DrawContext context, int x, int y) {
    final int fuelGaugeXPos = 13;
    final int fuelGaugeYPos = 18;

    if (handler.hasFuel()) {
      context.drawTexture(
        ALLOY_SMELTER_SCREEN_TEXTURE, 
        x + fuelGaugeXPos, 
        y + fuelGaugeYPos + 24 - handler.getScaledFuelGauge(), 
        176, 
        24 - handler.getScaledFuelGauge(), 
        AlloySmelterEntity.mAlloySmelterFuelGaugeWidth,
        handler.getScaledFuelGauge() + 1
      );
    }
  }

  private void renderProgressionArrow(DrawContext context, int x, int y) {
    final int progressBarXPos = 66;
    final int progressBarYPos = 34;
    
    if (handler.isCrafting()) {
      context.drawTexture(
        ALLOY_SMELTER_SCREEN_TEXTURE, 
        x + progressBarXPos, 
        y + progressBarYPos, 
        176, 
        25, 
        handler.getScaledProgressBar(), 
        AlloySmelterEntity.mAlloySmelterProgressBarHeight
      );
    }
  }

  @Override
  public void render(DrawContext context, int mouseX, int mouseY, float delta) {
    renderBackground(context);
    super.render(context, mouseX, mouseY, delta);
    drawMouseoverTooltip(context, mouseX, mouseY);
  }
}
