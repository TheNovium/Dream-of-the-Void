package space.novium.dotv.setup;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import space.novium.dotv.DotVMod;

public class ModTags {
    public static void init(){
        Blocks.init();
        Items.init();
    }
    
    public static class Blocks{
        private static void init(){}
        
        public static final TagKey<Block> WOOD_ESSENCE = tag("wood_essence");
        public static final TagKey<Block> COBBLE_ESSENCE = tag("cobble_essence");
        
        private static TagKey<Block> tag(String name){
            return BlockTags.create(DotVMod.modResourceLocation(name));
        }
    }
    
    public static class Items{
        private static void init(){}
        
        public static final TagKey<Item> FIBER = tag("fiber");
        
        private static TagKey<Item> tag(String name){
            return ItemTags.create(DotVMod.modResourceLocation(name));
        }
    }
}
