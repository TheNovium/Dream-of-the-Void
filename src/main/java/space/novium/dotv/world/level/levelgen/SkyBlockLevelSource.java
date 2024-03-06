package space.novium.dotv.world.level.levelgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class SkyBlockLevelSource extends ChunkGenerator {
    public static final Codec<SkyBlockLevelSource> CODEC = RecordCodecBuilder.create((codec) -> {
        return codec.group(RegistryOps.retrieveElement(Biomes.PLAINS)).apply(codec, codec.stable(SkyBlockLevelSource::new));
    });
    
    public SkyBlockLevelSource(Holder.Reference<Biome> biome){
        super(new FixedBiomeSource(biome));
    }
    
    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }
    
    @Override
    public void applyCarvers(WorldGenRegion p_223043_, long p_223044_, RandomState p_223045_, BiomeManager p_223046_, StructureManager p_223047_, ChunkAccess p_223048_, GenerationStep.Carving p_223049_) {
    
    }
    
    @Override
    public void buildSurface(WorldGenRegion p_223050_, StructureManager p_223051_, RandomState p_223052_, ChunkAccess p_223053_) {
    
    }
    
    @Override
    public void spawnOriginalMobs(WorldGenRegion p_62167_) {
    
    }
    
    @Override
    public int getGenDepth() {
        return 0;
    }
    
    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor p_223209_, Blender p_223210_, RandomState p_223211_, StructureManager p_223212_, ChunkAccess p_223213_) {
        return null;
    }
    
    @Override
    public int getSeaLevel() {
        return 0;
    }
    
    @Override
    public int getMinY() {
        return 0;
    }
    
    @Override
    public int getBaseHeight(int p_223032_, int p_223033_, Heightmap.Types p_223034_, LevelHeightAccessor p_223035_, RandomState p_223036_) {
        return 0;
    }
    
    @Override
    public NoiseColumn getBaseColumn(int p_223028_, int p_223029_, LevelHeightAccessor p_223030_, RandomState p_223031_) {
        return null;
    }
    
    @Override
    public void addDebugScreenInfo(List<String> p_223175_, RandomState p_223176_, BlockPos p_223177_) {
    
    }
}
