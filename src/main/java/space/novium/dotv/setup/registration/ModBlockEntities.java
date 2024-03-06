package space.novium.dotv.setup.registration;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import space.novium.dotv.DotVMod;
import space.novium.dotv.world.level.block.entity.StoneCrafterBlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DotVMod.MODID);
    
    public static final RegistryObject<BlockEntityType<StoneCrafterBlockEntity>> STONE_CRAFTER_ENTITY = BLOCK_ENTITIES.register("stone_crafter", () -> BlockEntityType.Builder.of(StoneCrafterBlockEntity::new, ModBlocks.STONE_CRAFTER.get()).build(null));
}
