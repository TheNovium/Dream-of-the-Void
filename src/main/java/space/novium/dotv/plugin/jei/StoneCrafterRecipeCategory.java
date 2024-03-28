package space.novium.dotv.plugin.jei;

import com.blakebr0.cucumber.util.Localizable;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import space.novium.dotv.DotVMod;
import space.novium.dotv.setup.registration.ModBlocks;
import space.novium.dotv.world.item.crafting.recipe.IStoneCrafterRecipe;

public class StoneCrafterRecipeCategory implements IRecipeCategory<IStoneCrafterRecipe> {
    private static final ResourceLocation BG_TEXTURE = DotVMod.modResourceLocation("textures/jei/stone_crafter.png");
    public static final ResourceLocation CATEGORY_ID = DotVMod.modResourceLocation("stone_crafter_recipe_category");
    private final IDrawable background;
    private final IDrawable icon;
    
    public StoneCrafterRecipeCategory(IGuiHelper helper){
        background = helper.createDrawable(BG_TEXTURE, 0, 0, 144, 81);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.STONE_CRAFTER.get()));
    }
    
    @Override
    public RecipeType<IStoneCrafterRecipe> getRecipeType() {
        return DotVRecipeCategory.STONE_CRAFTER_RECIPE_TYPE;
    }
    
    @Override
    public Component getTitle() {
        return Localizable.of("jei.category.dotv.stone_crafter_recipe_category").build();
    }
    
    @Override
    public IDrawable getBackground() {
        return background;
    }
    
    @Override
    public IDrawable getIcon() {
        return icon;
    }
    
    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, IStoneCrafterRecipe recipe, IFocusGroup group) {
        ClientLevel level = Minecraft.getInstance().level;
        
        assert level != null;
    
        NonNullList<Ingredient> inputs = recipe.getIngredients();
        Ingredient complete = recipe.getCompleteItem();
        ItemStack output = recipe.getResultItem(level.registryAccess());
        float totalItems = (float)inputs.size();
        for(int i = 0; i < inputs.size(); i++){
            float deg = 2.0f * (float)Math.PI * (((float) i) / totalItems);
            
            builder.addSlot(RecipeIngredientRole.INPUT, (int)Math.round(Math.sin(deg) * 30.0f) + 32, (int)Math.round(Math.cos(deg) * 30.0f) + 32).addIngredients(inputs.get(i));
        }
        builder.addSlot(RecipeIngredientRole.INPUT, 33, 33).addIngredients(complete);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 123, 33).addItemStack(output);
    }
}
