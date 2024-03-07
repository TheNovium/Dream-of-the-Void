package space.novium.dotv.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import space.novium.dotv.world.level.block.entity.StoneCrafterBlockEntity;

public class StoneCrafter extends Block implements EntityBlock {
    public StoneCrafter(){
        super(BlockBehaviour.Properties.of()
                .strength(0.5f)
                .sound(SoundType.STONE));
    }
    
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state){
        return new StoneCrafterBlockEntity(pos, state);
    }
    
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit){
        BlockEntity block = level.getBlockEntity(pos);
        if(block instanceof StoneCrafterBlockEntity crafter){
            boolean mainHandEmpty = player.getMainHandItem().isEmpty();
            if(mainHandEmpty){
                return crafter.tryRemoveLastItem(player);
            } else {
                return crafter.tryAddItem(player, hand, pos);
            }
        } else {
            return InteractionResult.PASS;
        }
    }
}
