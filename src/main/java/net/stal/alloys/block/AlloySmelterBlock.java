package net.stal.alloys.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.stal.alloys.block.entity.AlloySmelterEntity;
import net.stal.alloys.block.entity.StalAlloysBlockEntities;

public class AlloySmelterBlock extends BlockWithEntity {
  public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
  public static final BooleanProperty LIT = Properties.LIT;

  protected AlloySmelterBlock(Settings settings) { super(settings); }

  @Override
  public BlockState getPlacementState(ItemPlacementContext ctx) {
    return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(LIT, false);
  }

  @Override
  public BlockState rotate(BlockState state, BlockRotation rotation) {
    return state.with(FACING, rotation.rotate(state.get(FACING)));
  }

  @Override
  public BlockState mirror(BlockState state, BlockMirror mirror) {
    return state.rotate(mirror.getRotation(state.get(FACING)));
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(FACING, LIT);
  }

  @Override
  public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
    if (state.get(LIT).booleanValue()) {
      double d = (double)pos.getX() + 0.5;
      double e = pos.getY();
      double f = (double)pos.getZ() + 0.5;
      if (random.nextDouble() < 0.1) {
          world.playSound(d, e, f, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
      }
      Direction direction = state.get(FACING);
      Direction.Axis axis = direction.getAxis();
      double g = 0.52;
      double h = random.nextDouble() * 0.6 - 0.3;
      double i = axis == Direction.Axis.X ? (double)direction.getOffsetX() * g : h;
      double j = random.nextDouble() * 6.0 / 16.0;
      double k = axis == Direction.Axis.Z ? (double)direction.getOffsetZ() * g : h;
      world.addParticle(ParticleTypes.SMOKE, d + i, e + j, f + k, 0.0, 0.0, 0.0);
      world.addParticle(ParticleTypes.FLAME, d + i, e + j, f + k, 0.0, 0.0, 0.0);
    }
    super.randomDisplayTick(state, world, pos, random);
  }

  /* BLOCK ENTITY METHODS/MEMBERS */

  @Override
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.MODEL;
  }

  @Override
  public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
    if (state.getBlock() != newState.getBlock()) {
      BlockEntity blockEntity = world.getBlockEntity(pos);
      if (blockEntity instanceof AlloySmelterEntity) {
        ItemScatterer.spawn(world, pos, (AlloySmelterEntity)blockEntity);
        world.updateComparators(pos, this);
      }

      super.onStateReplaced(state, world, pos, newState, moved);
    }
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
      BlockHitResult hit) {

        if (!world.isClient()) {
          NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

          if (screenHandlerFactory != null) {
            player.openHandledScreen(screenHandlerFactory);
          }
        }

    return ActionResult.SUCCESS;
  }

  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return new AlloySmelterEntity(pos, state);
  }

  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state,
      BlockEntityType<T> type) {
    return BlockWithEntity.validateTicker(type, StalAlloysBlockEntities.ALLOY_SMELTER_ENTITY, AlloySmelterEntity::tick);
  }

  @Override
  protected MapCodec<? extends BlockWithEntity> getCodec() {
    // ! Currently unused but will need updating in the future
    throw new UnsupportedOperationException("Unimplemented method 'getCodec'");
  }
}
