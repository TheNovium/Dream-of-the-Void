package space.novium.dotv.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;
import space.novium.dotv.setup.registration.ModBlockEntities;
import space.novium.dotv.setup.registration.ModRecipeTypes;
import space.novium.dotv.world.item.crafting.recipe.IStoneCrafterRecipe;

import java.util.Optional;

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
            Optional<IStoneCrafterRecipe> recipe = getLevel().getRecipeManager().getRecipeFor(ModRecipeTypes.STONE_CRAFTER_TYPE, getItems(), getLevel());
            if(recipe.isPresent() && recipe.get().matches(items, getLevel()) && recipe.get().getCompleteItem().getItems()[0].is(stack.getItem())){
                stack.split(1);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, getBlockState()));
                items.clearContent();
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY() + 1, pos.getZ(), recipe.get().getOutputItem().getItems()[0]));
                setChanged();
                return InteractionResult.SUCCESS;
            }
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
        setChanged();
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
    
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        loadClientData(tag);
    }
    
    private void loadClientData(CompoundTag tag){
        NonNullList<ItemStack> loadItems = NonNullList.withSize(items.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, loadItems);
        for(ItemStack stack : loadItems){
            items.addItem(stack);
        }
    }
    
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        saveClientData(tag);
    }
    
    private void saveClientData(CompoundTag tag){
        NonNullList<ItemStack> saveItems = NonNullList.withSize(items.getContainerSize(), ItemStack.EMPTY);
        for(int i = 0; i < itemCount(); i++){
            saveItems.set(i, items.getItem(i));
        }
        ContainerHelper.saveAllItems(tag, saveItems);
    }
    
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveClientData(tag);
        return tag;
    }
    
    @Override
    public void handleUpdateTag(CompoundTag tag) {
        if(tag != null){
            loadClientData(tag);
        }
    }
    
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    
    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();
        if(tag != null){
            handleUpdateTag(tag);
        }
    }
    
    public SimpleContainer getItems() {
        return items;
    }
}
