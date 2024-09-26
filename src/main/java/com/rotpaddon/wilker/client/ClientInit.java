package com.rotpaddon.wilker.client;

import com.rotpaddon.wilker.AddonMain;
import com.rotpaddon.wilker.client.render.WilkerPickaxeRenderer;
import com.rotpaddon.wilker.client.render.WilkerStandRenderer;
import com.rotpaddon.wilker.init.InitEntities;
import com.rotpaddon.wilker.init.InitStands;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = AddonMain.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientInit {
    
    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(
                InitStands.STAND_WILKER_STAND.getEntityType(), WilkerStandRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(
                InitEntities.WILKER_PICKAXE.get(), manager -> new WilkerPickaxeRenderer(manager));
    }
}
