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
import net.minecraft.world.level.gameevent.GameEvent;
import space.novium.dotv.setup.registration.ModBlockEntities;

public class StoneCrafterBlockEntity extends BlockEntity {
    private final SimpleContainer items = new SimpleContainer(8){
        public int getMaxStackSize(){
            return 1;
        }
    };
    
    public StoneCrafterBlockEntity(BlockPos pos, BlockState state){
        super(ModBlockEntities.STONE_CRAFTER_ENTITY.get(), pos, state);
    }
    
    public InteractionResult tryRemoveLastItem(Player player){
        if(!isEmpty()  && player.getMainHandItem().isEmpty()){
            for(int i = items.getContainerSize() - 1; i >= 0; --i){
                ItemStack item = items.getItem(i);
                if(!item.isEmpty()){
                    player.getInventory().placeItemBackInInventory(item.copy());
                    items.setItem(i, ItemStack.EMPTY);
                    setChanged();
                    return InteractionResult.sidedSuccess(level.isClientSide());
                }
            }
        }
        return InteractionResult.PASS;
    }
    
    public InteractionResult tryAddItem(Player player, InteractionHand hand, BlockPos pos){
        ItemStack stack = player.getItemInHand(hand);
        if(!stack.isEmpty()){
            player.level().playSound(player, pos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.0f, 1.0f);
            for(int i = 0; i < items.getContainerSize(); ++i){
                ItemStack currentSlot = items.getItem(i);
                if(currentSlot.isEmpty()){
                    items.setItem(i, stack.split(1));
                    level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, getBlockState()));
                    setChanged();
                    return InteractionResult.SUCCESS;
                }
            }
            return InteractionResult.PASS;
        }
        return tryRemoveLastItem(player);
    }
    
    public boolean isEmpty(){
        for(int i = 0; i < items.getContainerSize(); i++){
            if(!items.getItem(i).isEmpty()){
                return false;
            }
        }
        return true;
    }
    
    public ItemStack getItemAt(int location){
        return location < items.getContainerSize() && location >= 0 ? items.getItem(location) : ItemStack.EMPTY;
    }
    
    public int itemCount(){
        int count = 0;
        for(int i = 0; i < items.getContainerSize(); ++i){
            if(items.getItem(i).isEmpty()){
                break;
            }
            ++count;
        }
        return count;
    }
    
    
    
    public SimpleContainer getItems() {
        return items;
    }
}
