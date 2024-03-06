package space.novium.dotv;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import space.novium.dotv.setup.registration.ModStructures;

import java.util.logging.Logger;

@Mod(DotVMod.MODID)
public class DotVMod {
    public static final String MODID = "dotv";
    public static final Logger LOGGER = Logger.getLogger(MODID);
    
    public DotVMod(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        ModStructures.STRUCTURE_REGISTRY.register(modEventBus);
    }
    
    public static ResourceLocation modResourceLocation(String loc){
        return new ResourceLocation(MODID, loc);
    }
}
