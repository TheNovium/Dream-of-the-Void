package space.novium.dotv.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import space.novium.dotv.setup.registration.ModBlockEntities;

public class StoneCrafterBlockEntity extends BlockEntity {
    public static int MAX_ITEMS = 8;
    
    public StoneCrafterBlockEntity(BlockPos pos, BlockState state){
        super(ModBlockEntities.STONE_CRAFTER_ENTITY.get(), pos, state);
    }
}
