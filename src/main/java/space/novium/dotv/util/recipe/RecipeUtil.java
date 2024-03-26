package space.novium.dotv.util.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class RecipeUtil {
    public static boolean canCraftWith(NonNullList<Ingredient> ingredients, Container container){
        Map<Integer, Integer> itemCount = new HashMap<>();
        for(int i = 0; i < container.getContainerSize(); i++){
            if(container.getItem(i) != ItemStack.EMPTY){
                ItemStack stack = container.getItem(i);
                addToMap(itemCount, Ingredient.of(stack), stack.getCount());
            }
        }
        for(Ingredient ingredient : ingredients){
            if(addToMap(itemCount, ingredient, -1) < 0){
                System.out.println("Cannot craft! Missing " + ingredient.toJson());
                return false;
            }
        }
        return true;
    }
    
    private static int addToMap(Map<Integer, Integer> map, Ingredient ingredient, int quantity){
        return map.compute(ingredient.getStackingIds().getInt(0), (key, val) -> {
            return (val == null) ? quantity : val + quantity;
        });
    }
}
