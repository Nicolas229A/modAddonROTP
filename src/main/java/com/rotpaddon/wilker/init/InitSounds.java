package com.rotpaddon.wilker.init;

import java.util.function.Supplier;

import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.util.mc.OstSoundList;
import com.rotpaddon.wilker.AddonMain;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(
            ForgeRegistries.SOUND_EVENTS, AddonMain.MOD_ID); // TODO sounds.json
    
    public static final RegistryObject<SoundEvent> WILKER_STAND_SUMMON_VOICELINE = SOUNDS.register("wilker_stand_summon_voiceline", 
            () -> new SoundEvent(new ResourceLocation(AddonMain.MOD_ID, "wilker_stand_summon_voiceline")));

    public static final RegistryObject<SoundEvent> WILKER_STAND_UNSUMMON_VOICELINE = SOUNDS.register("wilker_stand_unsummon_voiceline",
            () -> new SoundEvent(new ResourceLocation(AddonMain.MOD_ID, "wilker_stand_unsummon_voiceline")));

    public static final Supplier<SoundEvent> WILKER_STAND_SUMMON_SOUND = ModSounds.STAND_SUMMON_DEFAULT;

    public static final Supplier<SoundEvent> WILKER_STAND_UNSUMMON_SOUND = ModSounds.STAND_UNSUMMON_DEFAULT;
    
    public static final Supplier<SoundEvent> WILKER_STAND_PUNCH_LIGHT = ModSounds.STAND_PUNCH_LIGHT;
    
    public static final Supplier<SoundEvent> WILKER_STAND_PUNCH_HEAVY = ModSounds.STAND_PUNCH_HEAVY;
    
    public static final Supplier<SoundEvent> WILKER_STAND_PUNCH_BARRAGE = ModSounds.STAND_PUNCH_LIGHT;
    
    public static final Supplier<SoundEvent> WILKER_STAND_THROW_PICKAXE = ModSounds.STAND_PUNCH_LIGHT;
    
    public static final OstSoundList WILKER_STAND_OST = new OstSoundList(
            new ResourceLocation(AddonMain.MOD_ID, "wilker_stand_ost"), SOUNDS);
}
