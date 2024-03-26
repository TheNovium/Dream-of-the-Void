package space.novium.dotv.world.item.crafting.recipe;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;
import space.novium.dotv.DotVMod;

public interface IStoneCrafterRecipe extends Recipe<Container> {
    ResourceLocation TYPE_ID = DotVMod.modResourceLocation("stone_crafter");
    
    Ingredient getCompleteItem();
    
    Ingredient getOutputItem();
    
    @Override
    default @NotNull RecipeType<?> getType(){
        return BuiltInRegistries.RECIPE_TYPE.get(TYPE_ID);
    }
    
    @Override
    default boolean canCraftInDimensions(int width, int height){
        return false;
    }
    
    @Override
    default boolean isSpecial() {
        return true;
    }
}
