package space.novium.dotv.setup.registration;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import space.novium.dotv.world.item.crafting.recipe.IStoneCrafterRecipe;

public class ModRecipeTypes {
    public static final RecipeType<IStoneCrafterRecipe> STONE_CRAFTER_TYPE = new ModRecipeType<>("stone_crafter_recipe");
    
    private record ModRecipeType<T extends Recipe<?>>(String ID) implements RecipeType<T> {
    }
}
