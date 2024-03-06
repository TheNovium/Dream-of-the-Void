package space.novium.dotv.world.level.levelgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import space.novium.dotv.DotVMod;

public class SkyBlockPreset {
    public static final ResourceKey<WorldPreset> SKYBLOCK = register("skyblock");
    
    private static ResourceKey<WorldPreset> register(String id){
        return ResourceKey.create(Registries.WORLD_PRESET, DotVMod.modResourceLocation(id));
    }
}
