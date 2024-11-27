package rhymestudio.rhyme.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rhymestudio.rhyme.entity.SunItemEntity;
import rhymestudio.rhyme.registry.ModBlocks;
import rhymestudio.rhyme.registry.items.MaterialItems;
import rhymestudio.rhyme.registry.items.PlantItems;

import static net.minecraft.world.level.block.BarrelBlock.FACING;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED;

public class SunCreaterBlock extends BaseEntityBlock  {

    public SunCreaterBlock(Properties properties) {
        super(properties);
    }

    public static final MapCodec<SunCreaterBlock> CODEC = simpleCodec(SunCreaterBlock::new);


    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    public void fallOn(@NotNull Level level, @NotNull BlockState state, @NotNull BlockPos pos, net.minecraft.world.entity.Entity entity, float fallDistance) {
        if (entity.isSuppressingBounce()) {
            super.fallOn(level, state, pos, entity, fallDistance);
        }
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker getTicker(@NotNull Level pLevel, @NotNull BlockState pState, @NotNull BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? null : createTickerHelper(pBlockEntityType, ModBlocks.SUN_CREATOR_BLOCK_ENTITY.get(), (level, pos, state, blockEntity)->{
            if(!level.isClientSide){
                if(!level.isNight()) {
                    blockEntity.time++;
                    if(blockEntity.time >= blockEntity.interval){
                        if(blockEntity.count < blockEntity.MAX_COUNT){
                            blockEntity.count++;
                        }
                        blockEntity.time = 0;
                        Player nearestPlayer = pLevel.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10, false);
                        Component component = Component.literal("当前产能："+blockEntity.count +"/" + blockEntity.MAX_COUNT);

                        if(nearestPlayer!=null){
                            nearestPlayer.sendSystemMessage(component);
                        }
                    }
                }
            }
        });}
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!level.isClientSide && state.hasBlockEntity()){
            BlockEntity blockEntity = level.getBlockEntity(pos);

            if(blockEntity instanceof SunCreaterBlockEntity entity){
                SunItemEntity sun = new SunItemEntity(level, entity.getBlockPos().getCenter().add(0,0.5f,0));
                sun.setItem(new ItemStack(MaterialItems.SUN_ITEM.get(),entity.count));
                Vec3 dir = sun.position().subtract(player.position().add(0, player.getEyeHeight(), 0));

                sun.setDeltaMovement(dir.normalize().scale(-0.5f));
                level.addFreshEntity(sun);
                entity.count = 0;
            }

            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    public static final class SunCreaterBlockEntity extends BlockEntity {
        public int MAX_COUNT = 64;
        public int interval = 20 * 30;
        public int time = 0;
        public int count = 0;
        public SunCreaterBlockEntity(BlockEntityType<SunCreaterBlockEntity> type, BlockPos pos, BlockState state) {
            super(type, pos, state);
        }



        public SunCreaterBlockEntity(BlockPos pos, BlockState state) {
            this(ModBlocks.SUN_CREATOR_BLOCK_ENTITY.get(), pos, state);
        }


    }



    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new SunCreaterBlockEntity(pPos, pState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(WATERLOGGED);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
        FluidState fluidstate = placeContext.getLevel().getFluidState(placeContext.getClickedPos());
        return defaultBlockState()
                .setValue(FACING, placeContext.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
    @Override
    public @NotNull BlockState updateShape(BlockState pState, @NotNull Direction pDirection, @NotNull BlockState pNeighborState, @NotNull LevelAccessor pLevel, @NotNull BlockPos pPos, @NotNull BlockPos pNeighborPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }
        return pState;
    }

    public @NotNull FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
    }

}
