package space.novium.dotv.setup.registration;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import space.novium.dotv.DotVMod;
import space.novium.dotv.world.item.crafting.StoneCrafterRecipe;

public class ModRecipeSerializer {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, DotVMod.MODID);
    
    public static final RegistryObject<RecipeSerializer<StoneCrafterRecipe>> STONE_CRAFTER_RECIPE = SERIALIZERS.register("stone_crafter_recipe", StoneCrafterRecipe.Serializer::new);
}
