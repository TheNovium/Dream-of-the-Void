package space.novium.dotv.setup.registration;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import space.novium.dotv.world.item.crafting.recipe.IStoneCrafterRecipe;

public class ModRecipeTypes {
    public static final RecipeType<IStoneCrafterRecipe> STONE_CRAFTER_TYPE = new ModRecipeType<>();
    
    private static class ModRecipeType<T extends Recipe<?>> implements RecipeType<T> {
        private ModRecipeType(){}
    }
}
