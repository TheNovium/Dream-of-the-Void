package space.novium.dotv.setup;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import space.novium.dotv.DotVMod;

public class ModTags {
    public static void init(){
        Blocks.init();
    }
    
    public static class Blocks{
        private static void init(){}
        
        public static final TagKey<Block> WOOD_ESSENCE = tag("wood_essence");
        public static final TagKey<Block> COBBLE_ESSENCE = tag("cobble_essence");
        
        private static TagKey<Block> tag(String name){
            return BlockTags.create(DotVMod.modResourceLocation(name));
        }
    }
}
