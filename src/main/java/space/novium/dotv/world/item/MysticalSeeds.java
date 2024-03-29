package space.novium.dotv.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysticalSeeds extends Item {
    private static final Map<String, List<Item>> SEEDS_CAN_PLANT_ON;
    
    public MysticalSeeds(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos targetBlock = context.getClickedPos();
        Level level = context.getLevel();
        if(context.getClickedFace() == Direction.UP && SEEDS_CAN_PLANT_ON.containsKey(level.getBlockState(targetBlock).getBlock().getDescriptionId()) && level.getBlockState(targetBlock.above()).is(Blocks.AIR)){
            List<Item> possibleItems = SEEDS_CAN_PLANT_ON.get(level.getBlockState(targetBlock).getBlock().getDescriptionId());
            Item seed = possibleItems.get((int)Math.floor(Math.random() * (double)possibleItems.size()));
            seed.use(level, context.getPlayer(), context.getHand());
            return seed.useOn(context);
        }
        return InteractionResult.PASS;
    }
    
    public static void registerSeedsByBlock(Block block, Item... items){
        List<Item> itemByBlock = SEEDS_CAN_PLANT_ON.getOrDefault(block.getDescriptionId(), new ArrayList<>(items.length));
        for(Item item : items){
            if (!itemByBlock.contains(item)){
                itemByBlock.add(item);
            }
        }
        SEEDS_CAN_PLANT_ON.put(block.getDescriptionId(), itemByBlock);
    }
    
    static {
        SEEDS_CAN_PLANT_ON = new HashMap<>();
        registerSeedsByBlock(Blocks.FARMLAND, Items.BEETROOT_SEEDS, Items.CARROT, Items.POTATO, Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS);
        registerSeedsByBlock(Blocks.SAND, Items.SUGAR_CANE, Items.CACTUS);
    }
}
