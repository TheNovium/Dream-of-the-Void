package space.novium.dotv.setup.registration;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import space.novium.dotv.DotVMod;
import space.novium.dotv.world.structure.StartingIsland;

public class ModStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_REGISTRY = DeferredRegister.create(Registries.STRUCTURE_TYPE, DotVMod.MODID);
    
    public static final RegistryObject<StructureType<?>> STARTING_ISLAND = STRUCTURE_REGISTRY.register("starting_island", () -> explicitStructureType(StartingIsland.CODEC));
    
    private static <T extends Structure> StructureType<T> explicitStructureType(Codec<T> codec){
        return () -> codec;
    }
}
