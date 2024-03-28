package space.novium.dotv.plugin.jei;

import mezz.jei.api.recipe.RecipeType;
import space.novium.dotv.DotVMod;
import space.novium.dotv.world.item.crafting.StoneCrafterRecipe;
import space.novium.dotv.world.item.crafting.recipe.IStoneCrafterRecipe;


public abstract class DotVRecipeCategory {
    public static final RecipeType<IStoneCrafterRecipe> STONE_CRAFTER_RECIPE_TYPE = new RecipeType<>(StoneCrafterRecipeCategory.CATEGORY_ID, StoneCrafterRecipe.class);
}
