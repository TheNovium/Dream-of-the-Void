package space.novium.dotv.world.item.crafting;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import space.novium.dotv.DotVMod;
import space.novium.dotv.setup.registration.ModRecipeSerializer;
import space.novium.dotv.world.item.crafting.recipe.IStoneCrafterRecipe;

import java.util.ArrayList;
import java.util.List;

public class StoneCrafterRecipe implements IStoneCrafterRecipe {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> inputs;
    private final ItemStack complete;
    
    public StoneCrafterRecipe(ResourceLocation id, ItemStack output, ItemStack complete, Ingredient... inputs){
        Preconditions.checkArgument(inputs.length <= 8, "Cannot have more than 8 ingredients!");
        this.id = id;
        this.output = output;
        this.inputs = NonNullList.of(Ingredient.EMPTY, inputs);
        this.complete = complete;
    }
    
    @Override
    public Ingredient getCompleteItem() {
        return Ingredient.of(complete);
    }
    
    @Override
    public boolean matches(Container container, Level level) {
        StackedContents contents = new StackedContents();
        List<ItemStack> inputs = new ArrayList<>();
        int i = 0;
        for(int j = 0; j < container.getContainerSize(); ++j){
            ItemStack itemStack = container.getItem(j);
            if(!itemStack.isEmpty()){
                ++i;
                inputs.add(itemStack);
            }
        }
        return i == inputs.size() && contents.canCraft(this, null);
    }
    
    @Override
    public ItemStack assemble(Container p_44001_, RegistryAccess p_267165_) {
        return null;
    }
    
    @Override
    public ItemStack getResultItem(RegistryAccess registry) {
        return output;
    }
    
    @Override
    public ResourceLocation getId() {
        return id;
    }
    
    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.STONE_CRAFTER_RECIPE;
    }
    
    public static class Serializer implements RecipeSerializer<StoneCrafterRecipe> {
        private static final ResourceLocation NAME = DotVMod.modResourceLocation("stone_crafter_recipe");
    
        @Override
        public StoneCrafterRecipe fromJson(ResourceLocation loc, JsonObject json) {
            return null;
        }
    
        @Override
        public @Nullable StoneCrafterRecipe fromNetwork(ResourceLocation loc, FriendlyByteBuf buffer) {
            return null;
        }
    
        @Override
        public void toNetwork(FriendlyByteBuf buffer, StoneCrafterRecipe recipe) {
        
        }
    }
}
