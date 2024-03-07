package space.novium.dotv.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import space.novium.dotv.setup.registration.ModBlockEntities;

public class StoneCrafterBlockEntity extends BlockEntity {
    private final SimpleContainer items = new SimpleContainer(1){
        public int getMaxStackSize(){
            return 1;
        }
    };
    
    public StoneCrafterBlockEntity(BlockPos pos, BlockState state){
        super(ModBlockEntities.STONE_CRAFTER_ENTITY.get(), pos, state);
    }
    
    public InteractionResult tryRemoveLastItem(Player player){
        return InteractionResult.PASS;
    }
    
    public InteractionResult tryAddItem(Player player, InteractionHand hand, BlockPos pos){
        //TODO check if the crafter is full
        ItemStack stack = player.getItemInHand(hand);
        if(!stack.isEmpty()){
            player.level().playSound(player, pos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.0f, 1.0f);
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        return tryRemoveLastItem(player);
    }
}
