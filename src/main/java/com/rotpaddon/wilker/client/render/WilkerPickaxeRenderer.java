package com.rotpaddon.wilker.client.render;

import com.github.standobyte.jojo.client.render.entity.renderer.SimpleEntityRenderer;
import com.rotpaddon.wilker.AddonMain;
import com.rotpaddon.wilker.entity.WilkerPickaxeEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class WilkerPickaxeRenderer extends SimpleEntityRenderer<WilkerPickaxeEntity, WilkerPickaxeModel> {

    public WilkerPickaxeRenderer(EntityRendererManager renderManager) {
        super(renderManager, new WilkerPickaxeModel(),
                new ResourceLocation(AddonMain.MOD_ID, "textures/entity/wilker_pickaxe.png"));
    }
}
