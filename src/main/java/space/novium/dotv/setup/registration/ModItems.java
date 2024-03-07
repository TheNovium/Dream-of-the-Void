package space.novium.dotv.setup.registration;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import space.novium.dotv.DotVMod;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DotVMod.MODID);
    
    public static final RegistryObject<Item> STONE_CRAFTER_ITEM = ITEMS.register("stone_crafter", () -> new BlockItem(ModBlocks.STONE_CRAFTER.get(), new Item.Properties()));
}
