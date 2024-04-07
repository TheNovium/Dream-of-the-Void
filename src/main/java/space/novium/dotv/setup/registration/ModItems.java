package space.novium.dotv.setup.registration;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import space.novium.dotv.DotVMod;
import space.novium.dotv.world.item.MysticalSeeds;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DotVMod.MODID);
    
    //Items
    public static final RegistryObject<Item> MANA_INFUSED_STONE = ITEMS.register("mana_infused_stone", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WOODEN_HOE_TOOL = ITEMS.register("wooden_hoe_tool", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> WOODEN_PICKAXE_TOOL = ITEMS.register("wooden_pickaxe_tool", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> WOODEN_TOOL_BINDING = ITEMS.register("wooden_tool_binding", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> VILE_BLEND = ITEMS.register("vile_blend", () -> new Item(new Item.Properties()));
    
    //Use Items
    public static final RegistryObject<Item> MYSTICAL_SEEDS = ITEMS.register("mystical_seeds", () -> new MysticalSeeds(new Item.Properties()));
    
    //Block Items
    public static final RegistryObject<Item> STONE_CRAFTER_ITEM = ITEMS.register("stone_crafter", () -> new BlockItem(ModBlocks.STONE_CRAFTER.get(), new Item.Properties()));
}
