package com.rotpaddon.wilker.init;

import com.rotpaddon.wilker.AddonMain;
import com.rotpaddon.wilker.entity.WilkerPickaxeEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(
            ForgeRegistries.ENTITIES, AddonMain.MOD_ID);
    
    
    
    public static final RegistryObject<EntityType<WilkerPickaxeEntity>> WILKER_PICKAXE = ENTITIES.register("wilker_pickaxe",
            () -> EntityType.Builder.<WilkerPickaxeEntity>of(WilkerPickaxeEntity::new, EntityClassification.MISC)
            .sized(1.0F, 1.0F)
            .build(AddonMain.MOD_ID + ":wilker_pickaxe"));
};
