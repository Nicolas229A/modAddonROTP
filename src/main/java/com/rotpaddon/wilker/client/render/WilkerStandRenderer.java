package com.rotpaddon.wilker.client.render;

import com.github.standobyte.jojo.client.render.entity.model.stand.StandEntityModel;
import com.github.standobyte.jojo.client.render.entity.model.stand.StandModelRegistry;
import com.github.standobyte.jojo.client.render.entity.renderer.stand.StandEntityRenderer;
import com.rotpaddon.wilker.AddonMain;
import com.rotpaddon.wilker.entity.WilkerStandEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class WilkerStandRenderer extends StandEntityRenderer<WilkerStandEntity, StandEntityModel<WilkerStandEntity>> {
    
    public WilkerStandRenderer(EntityRendererManager renderManager) {
        super(renderManager, 
                StandModelRegistry.registerModel(new ResourceLocation(AddonMain.MOD_ID, "wilker_stand"), WilkerStandModel::new),
                new ResourceLocation(AddonMain.MOD_ID, "textures/entity/stand/wilker_stand.png"), 0);
    }
}