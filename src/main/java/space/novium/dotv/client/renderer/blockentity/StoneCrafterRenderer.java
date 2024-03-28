package space.novium.dotv.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import space.novium.dotv.DotVMod;
import space.novium.dotv.setup.registration.ModRecipeTypes;
import space.novium.dotv.world.item.crafting.StoneCrafterRecipe;
import space.novium.dotv.world.item.crafting.recipe.IStoneCrafterRecipe;
import space.novium.dotv.world.level.block.entity.StoneCrafterBlockEntity;

import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class StoneCrafterRenderer implements BlockEntityRenderer<StoneCrafterBlockEntity> {
    private static final float SIZE = 0.125f;
    private final ItemRenderer renderer;
    
    public StoneCrafterRenderer(BlockEntityRendererProvider.Context context){
        renderer = context.getItemRenderer();
    }
    
    @Override
    public void render(StoneCrafterBlockEntity entity, float ticks, PoseStack pose, MultiBufferSource buffer, int light, int overlay) {
        int itemCount = entity.itemCount();
        float rotation = (1.0f / (float)itemCount) * (float)Math.PI * 2.0f;
        int loc = (int)entity.getBlockPos().asLong();
        long millis = System.currentTimeMillis();
        for(int i = 0; i < itemCount; ++i){
            ItemStack currentItem = entity.getItemAt(i);
            if(currentItem != ItemStack.EMPTY){
                pose.pushPose();
                float angle = (((millis / 45) + (i * 45)) % 360);
                pose.translate(Math.sin((float)i  * rotation) / 2.5f + 0.5f, 1.1f + Math.sin(Math.toRadians(angle * 4.0f)) * 0.01f, Math.cos((float)i * rotation) / 2.5f + 0.5f);
                pose.scale(SIZE, SIZE, SIZE);
                pose.mulPose(Axis.YP.rotationDegrees(angle));
                renderer.renderStatic(currentItem, ItemDisplayContext.FIXED, LightTexture.FULL_BRIGHT, overlay, pose, buffer, entity.getLevel(), loc);
                pose.popPose();
            }
        }
        Optional<IStoneCrafterRecipe> recipe = entity.getLevel().getRecipeManager().getRecipeFor(ModRecipeTypes.STONE_CRAFTER_TYPE,  entity.getItems(), entity.getLevel());
        if(recipe.isPresent() && recipe.get().matches(entity.getItems(), entity.getLevel())){
            StoneCrafterRecipe r = (StoneCrafterRecipe) recipe.get();
            try {
                ItemStack complete = r.getCompleteItem().getItems()[0];
                ItemStack result = r.getOutputItem().getItems()[0];
                pose.pushPose();
                pose.translate(0.25f, 1.00f, 0.5f);
                pose.scale(0.25f, 0.25f, 0.25f);
                renderer.renderStatic(complete, ItemDisplayContext.FIXED, LightTexture.FULL_BRIGHT, overlay, pose, buffer, entity.getLevel(), loc);
                pose.translate(2.0f, 0.0f, 0.0f);
                renderer.renderStatic(result, ItemDisplayContext.FIXED, LightTexture.FULL_BRIGHT, overlay, pose, buffer, entity.getLevel(), loc);
                pose.popPose();
            } catch(Exception e){
                DotVMod.LOGGER.warning("Failed to find output item for stone crafter!");
            }
        }
    }
}
