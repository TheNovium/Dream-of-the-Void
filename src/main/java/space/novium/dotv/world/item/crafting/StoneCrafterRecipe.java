package space.novium.dotv.world.item.crafting;

import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.novium.dotv.DotVMod;
import space.novium.dotv.setup.registration.ModRecipeSerializer;
import space.novium.dotv.setup.registration.ModRecipeTypes;
import space.novium.dotv.util.recipe.RecipeUtil;
import space.novium.dotv.world.item.crafting.recipe.IStoneCrafterRecipe;

import java.util.ArrayList;
import java.util.List;

public class StoneCrafterRecipe implements IStoneCrafterRecipe {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> inputs;
    private final ItemStack complete;
    public static final int MAX_SIZE = 8;
    
    public StoneCrafterRecipe(ResourceLocation id, ItemStack output, ItemStack complete, NonNullList<Ingredient> inputs){
        Preconditions.checkArgument(inputs.size() <= MAX_SIZE, "Cannot have more than 8 ingredients!");
        this.id = id;
        this.output = output;
        this.inputs = inputs;
        this.complete = complete;
    }
    
    @Override
    public Ingredient getCompleteItem() {
        return Ingredient.of(complete);
    }
    
    @Override
    public boolean matches(Container container, Level level) {
        StackedContents contents = new StackedContents();
        List<ItemStack> inputItems = new ArrayList<>();
        for(int j = 0; j < container.getContainerSize(); ++j){
            ItemStack itemStack = container.getItem(j);
            if(!itemStack.isEmpty()){
                inputItems.add(itemStack);
            }
        }
        return inputs.size() == inputItems.size() && RecipeUtil.canCraftWith(inputs, container);
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
        return ModRecipeSerializer.STONE_CRAFTER_RECIPE.get();
    }
    
    @Override
    public @NotNull RecipeType<?> getType() {
        return ModRecipeTypes.STONE_CRAFTER_TYPE;
    }
    
    public static class Serializer implements RecipeSerializer<StoneCrafterRecipe> {
        private static final ResourceLocation NAME = DotVMod.modResourceLocation("stone_crafter_recipe");
    
        @Override
        public StoneCrafterRecipe fromJson(ResourceLocation loc, JsonObject json) {
            NonNullList<Ingredient> list = itemsFromJson(GsonHelper.getAsJsonArray(json, "ingredients"));
            if(list.isEmpty()){
                throw new JsonParseException("No ingredients for stone crafter recipe");
            } else if (list.size() > StoneCrafterRecipe.MAX_SIZE){
                throw new JsonParseException("Too many ingredients for stone crafter recipe. The maximum is " + StoneCrafterRecipe.MAX_SIZE + ".");
            } else {
                ItemStack itemComplete = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "complete"));
                ItemStack itemResult = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
                return new StoneCrafterRecipe(loc, itemResult, itemComplete, list);
            }
        }
        
        private static NonNullList<Ingredient> itemsFromJson(JsonArray json){
            NonNullList<Ingredient> list = NonNullList.create();
            for(int i = 0; i < json.size(); i++){
                Ingredient ingredient = Ingredient.fromJson(json.get(i), false);
                    if(!ingredient.isEmpty()){
                        list.add(ingredient);
                    }
                }
            return list;
        }
    
        @Override
        public @Nullable StoneCrafterRecipe fromNetwork(ResourceLocation loc, FriendlyByteBuf buffer) {
            int ingredientCount = buffer.readVarInt();
            NonNullList<Ingredient> list = NonNullList.withSize(ingredientCount, Ingredient.EMPTY);
            list.replaceAll(ignored -> Ingredient.fromNetwork(buffer));
            ItemStack itemComplete = buffer.readItem();
            ItemStack itemResult = buffer.readItem();
            return new StoneCrafterRecipe(loc, itemResult, itemComplete, list);
        }
    
        @Override
        public void toNetwork(FriendlyByteBuf buffer, StoneCrafterRecipe recipe) {
            buffer.writeVarInt(recipe.inputs.size());
            for(Ingredient ingredient : recipe.inputs){
                ingredient.toNetwork(buffer);
            }
            buffer.writeItem(recipe.complete);
            buffer.writeItem(recipe.output);
        }
    }
}
