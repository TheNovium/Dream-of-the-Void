package space.novium.dotv.setup.registration;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import space.novium.dotv.DotVMod;
import space.novium.dotv.world.level.block.StoneCrafter;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DotVMod.MODID);
    
    public static final RegistryObject<StoneCrafter> STONE_CRAFTER = BLOCKS.register("stone_crafter", StoneCrafter::new);
}
