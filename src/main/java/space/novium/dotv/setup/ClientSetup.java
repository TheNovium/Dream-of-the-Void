package space.novium.dotv.setup;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import space.novium.dotv.DotVMod;
import space.novium.dotv.client.renderer.blockentity.StoneCrafterRenderer;
import space.novium.dotv.setup.registration.ModBlockEntities;

@Mod.EventBusSubscriber(modid = DotVMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void initClient(EntityRenderersEvent.RegisterRenderers e){
        e.registerBlockEntityRenderer(ModBlockEntities.STONE_CRAFTER_ENTITY.get(), StoneCrafterRenderer::new);
    }
}
