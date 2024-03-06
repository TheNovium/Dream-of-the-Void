package space.novium.dotv.events;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import space.novium.dotv.DotVMod;

@Mod.EventBusSubscriber(modid = DotVMod.MODID)
public class WorldSpawnEvent {
    private static boolean needToLoadSkyBlock = false;
    
    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.CreateSpawnPosition e){
        e.getSettings().setXSpawn(8);
        e.getSettings().setYSpawn(64);
        e.getSettings().setZSpawn(8);
        
        needToLoadSkyBlock = true;
    }
    
    @SubscribeEvent
    public static void onInitialChunkGeneration(LevelEvent.Load e){
        if(needToLoadSkyBlock){
            try {
                MinecraftServer level = e.getLevel().getServer();
                CommandSourceStack cmd = level.createCommandSourceStack()
                        .withLevel(level.overworld())
                        .withPosition(new Vec3(0, 56, 0));
                level.submit(() -> {
                    try {
                        level.getCommands().getDispatcher().execute("place structure dotv:starting_island", cmd);
                    } catch (CommandSyntaxException ex) {
                        DotVMod.LOGGER.warning("Failed to wait!");
                    }
                });
        
            } catch (Exception ex){
                DotVMod.LOGGER.warning("Failed to generate spawn island!");
                DotVMod.LOGGER.info(ex.toString());
            }
            DotVMod.LOGGER.info("Successfully generated spawn island");
        }
    }
    
}
