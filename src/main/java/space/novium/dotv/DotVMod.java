package space.novium.dotv;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DotVMod.MODID)
public class DotVMod {
    public static final String MODID = "dotv";
    
    public DotVMod(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }
    
    public static ResourceLocation modResourceLocation(String loc){
        return new ResourceLocation(MODID, loc);
    }
}
