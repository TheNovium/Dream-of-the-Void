package space.novium.dotv.events;

import com.blakebr0.mysticalagriculture.lib.ModCrops;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import space.novium.dotv.DotVMod;
import space.novium.dotv.setup.ModTags;

@Mod.EventBusSubscriber(modid = DotVMod.MODID)
public class PlayerUseEvents {
    @SubscribeEvent
    public static void playerRightClickBlock(PlayerInteractEvent.RightClickBlock e){
        if(e.getSide().isServer() && e.getUseBlock() != Event.Result.ALLOW && e.getEntity().isCrouching()){
            Level level = e.getLevel();
            BlockPos pos = e.getPos();
            BlockState block = level.getBlockState(pos);
            Item item = null;
            if(block.is(ModTags.Blocks.COBBLE_ESSENCE)){
                item = ModCrops.STONE.getEssenceItem();
            }
            if(block.is(ModTags.Blocks.WOOD_ESSENCE)){
                item = ModCrops.WOOD.getEssenceItem();
            }
            if(block.is(Blocks.CLAY)){
                item = ModCrops.EARTH.getEssenceItem();
            }
            if(item != null){
                level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(item, 1)));
                e.setUseItem(Event.Result.ALLOW);
                level.playSound(e.getEntity(), pos, SoundEvents.BIG_DRIPLEAF_TILT_DOWN, SoundSource.BLOCKS);
            }
        }
    }
}
