package space.novium.dotv.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysticalSeeds extends Item {
    private static final Map<String, List<Item>> SEEDS_CAN_PLANT_ON;
    private static final Map<String, List<Item>> WATER_SEEDS_CAN_PLANT_ON;
    
    public MysticalSeeds(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos targetBlock = context.getClickedPos();
        Level level = context.getLevel();
        Map<String, List<Item>> map =  level.getBlockState(targetBlock.above()).is(Blocks.WATER) ? WATER_SEEDS_CAN_PLANT_ON :  SEEDS_CAN_PLANT_ON;
        if(context.getClickedFace() == Direction.UP && map.containsKey(level.getBlockState(targetBlock).getBlock().getDescriptionId())){
            List<Item> possibleItems = map.get(level.getBlockState(targetBlock).getBlock().getDescriptionId());
            Item seed = possibleItems.get((int)Math.floor(Math.random() * (double)possibleItems.size()));
            seed.use(level, context.getPlayer(), context.getHand());
            return seed.useOn(context);
        }
        return InteractionResult.PASS;
    }
    
    public static void registerSeedsByBlock(Block block, boolean requiresAirBlock, Item... items){
        Map<String, List<Item>> map = requiresAirBlock ? SEEDS_CAN_PLANT_ON : WATER_SEEDS_CAN_PLANT_ON;
        List<Item> itemByBlock = map.getOrDefault(block.getDescriptionId(), new ArrayList<>(items.length));
        for(Item item : items){
            if (!itemByBlock.contains(item)){
                itemByBlock.add(item);
            }
        }
        map.put(block.getDescriptionId(), itemByBlock);
    }
    
    public static void registerSeedsByBlock(Block block, Item... items){
        registerSeedsByBlock(block, true, items);
    }
    
    static {
        SEEDS_CAN_PLANT_ON = new HashMap<>();
        WATER_SEEDS_CAN_PLANT_ON = new HashMap<>();
        registerSeedsByBlock(Blocks.FARMLAND, Items.BEETROOT_SEEDS, Items.CARROT, Items.POTATO, Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS);
        registerSeedsByBlock(Blocks.SAND, Items.SUGAR_CANE, Items.CACTUS);
        registerSeedsByBlock(Blocks.DIRT, false, Items.KELP, Items.BRAIN_CORAL_FAN, Items.BUBBLE_CORAL_FAN, Items.FIRE_CORAL_FAN, Items.HORN_CORAL_FAN, Items.TUBE_CORAL_FAN);
    }
}
