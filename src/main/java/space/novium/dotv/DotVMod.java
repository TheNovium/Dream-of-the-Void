package space.novium.dotv;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import space.novium.dotv.setup.ModTags;
import space.novium.dotv.setup.registration.ModBlockEntities;
import space.novium.dotv.setup.registration.ModBlocks;
import space.novium.dotv.setup.registration.ModItems;
import space.novium.dotv.setup.registration.ModStructures;

import java.util.logging.Logger;

@Mod(DotVMod.MODID)
public class DotVMod {
    public static final String MODID = "dotv";
    public static final Logger LOGGER = Logger.getLogger(MODID);
    
    public DotVMod(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        ModTags.init();
        
        ModStructures.STRUCTURE_REGISTRY.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
    }
    
    public static ResourceLocation modResourceLocation(String loc){
        return new ResourceLocation(MODID, loc);
    }
}
