package space.novium.dotv.world.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import space.novium.dotv.DotVMod;
import space.novium.dotv.setup.registration.ModStructures;

import java.util.Optional;

public class StartingIsland extends Structure {
    public static final Codec<StartingIsland> CODEC = RecordCodecBuilder.<StartingIsland>mapCodec((codec) -> {
        return codec.group(StartingIsland.settingsCodec(codec),
                StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsaw)
            ).apply(codec, StartingIsland::new);
    }).codec();
    
    private final Holder<StructureTemplatePool> startPool;
    private final Optional<ResourceLocation> startJigsaw;
    
    public StartingIsland(Structure.StructureSettings settings, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsaw){
        super(settings);
        this.startPool = startPool;
        this.startJigsaw = startJigsaw;
    }
    
    private static boolean playerSpawnCheck(Structure.GenerationContext context){
        ChunkPos pos = context.chunkPos();
        return false;
    }
    
    public Optional<GenerationStub> findGenerationPoint(Structure.GenerationContext context){
        if(!playerSpawnCheck(context)){
            return Optional.empty();
        }
        ChunkPos pos = context.chunkPos();
        BlockPos blockPos = new BlockPos(pos.getMinBlockX(), 56, pos.getMinBlockZ());
        
        return JigsawPlacement.addPieces(
                context,
                startPool,
                startJigsaw,
                1,
                blockPos,
                false,
                Optional.empty(),
                0
        );
    }
    
    @Override
    public StructureType<?> type() {
        return ModStructures.STARTING_ISLAND.get();
    }
}
