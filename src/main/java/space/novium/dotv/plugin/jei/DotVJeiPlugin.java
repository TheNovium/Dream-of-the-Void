package space.novium.dotv.plugin.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;
import space.novium.dotv.DotVMod;
import space.novium.dotv.setup.registration.ModBlocks;
import space.novium.dotv.setup.registration.ModRecipeTypes;

@JeiPlugin
public class DotVJeiPlugin implements IModPlugin {
    public static final ResourceLocation PLUGIN_ID = DotVMod.modResourceLocation("dotv_jei_plugin");
    
    public DotVJeiPlugin(){}
    
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }
    
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new StoneCrafterRecipeCategory(helper));
    }
    
    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.STONE_CRAFTER.get()), DotVRecipeCategory.STONE_CRAFTER_RECIPE_TYPE);
    }
    
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ClientLevel level = Minecraft.getInstance().level;
        if(level != null){
            RecipeManager manager = level.getRecipeManager();
            registration.addRecipes(DotVRecipeCategory.STONE_CRAFTER_RECIPE_TYPE, manager.getAllRecipesFor(ModRecipeTypes.STONE_CRAFTER_TYPE));
        }
    }
}
