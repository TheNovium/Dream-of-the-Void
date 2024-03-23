package space.novium.dotv.setup.registration;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.extensions.IForgeRecipeSerializer;
import space.novium.dotv.world.item.crafting.StoneCrafterRecipe;

public interface ModRecipeSerializer<T extends Recipe<?>> extends IForgeRecipeSerializer<T> {
    static void init(){}
    
    RecipeSerializer<StoneCrafterRecipe> STONE_CRAFTER_RECIPE = register("stone_crafter_recipe", new StoneCrafterRecipe.Serializer());
    
    static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer){
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, id, serializer);
    }
}
