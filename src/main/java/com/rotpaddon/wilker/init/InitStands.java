package com.rotpaddon.wilker.init;

import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.stand.*;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.init.power.stand.EntityStandRegistryObject;
import com.github.standobyte.jojo.init.power.stand.ModStandsInit;
import com.github.standobyte.jojo.power.impl.stand.StandInstance.StandPart;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import com.github.standobyte.jojo.power.impl.stand.stats.TimeStopperStandStats;
import com.github.standobyte.jojo.power.impl.stand.type.EntityStandType;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;
import com.rotpaddon.wilker.AddonMain;
import com.rotpaddon.wilker.action.WilkerStandThrowPickaxe;
import com.rotpaddon.wilker.entity.WilkerStandEntity;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import org.lwjgl.system.CallbackI;

import java.sql.Time;

public class InitStands {
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<Action<?>> ACTIONS = DeferredRegister.create(
            (Class<Action<?>>) ((Class<?>) Action.class), AddonMain.MOD_ID);
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<StandType<?>> STANDS = DeferredRegister.create(
            (Class<StandType<?>>) ((Class<?>) StandType.class), AddonMain.MOD_ID);
    
 // ======================================== Wilker Stand ========================================
    
    
    // Create all the abilities here...
    public static final RegistryObject<StandEntityAction> WILKER_STAND_PUNCH = ACTIONS.register("wilker_stand_punch", 
            () -> new StandEntityLightAttack(new StandEntityLightAttack.Builder()
                    .punchSound(InitSounds.WILKER_STAND_PUNCH_LIGHT)));
    
    public static final RegistryObject<StandEntityAction> WILKER_STAND_BARRAGE = ACTIONS.register("wilker_stand_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder()
                    .barrageHitSound(InitSounds.WILKER_STAND_PUNCH_BARRAGE)));

    public static final RegistryObject<StandEntityHeavyAttack> WILKER_STAND_FINISHER_PUNCH = ACTIONS.register("wilker_stand_finisher_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder() // TODO finisher ability
                    .punchSound(InitSounds.WILKER_STAND_PUNCH_HEAVY)
                    .partsRequired(StandPart.ARMS)));

    public static final RegistryObject<StandEntityHeavyAttack> WILKER_STAND_HEAVY_PUNCH = ACTIONS.register("wilker_stand_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder()
                    .shiftVariationOf(WILKER_STAND_PUNCH).shiftVariationOf(WILKER_STAND_BARRAGE)
                    .setFinisherVariation(WILKER_STAND_FINISHER_PUNCH)
                    .punchSound(InitSounds.WILKER_STAND_PUNCH_HEAVY)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> WILKER_STAND_BLOCK = ACTIONS.register("wilker_stand_block", 
            () -> new StandEntityBlock());
    
    public static final RegistryObject<StandEntityAction> WILKER_STAND_THROW_PICKAXE = ACTIONS.register("wilker_stand_throw_pickaxe", 
            () -> new WilkerStandThrowPickaxe(new StandEntityAction.Builder()
                    .holdToFire(20, true)
                    .standSound(InitSounds.WILKER_STAND_THROW_PICKAXE)
                    .staminaCost(75)
                    .partsRequired(StandPart.ARMS)));

    public static final RegistryObject<TimeStop> WILKER_STAND_TS = ACTIONS.register("wilker_stand_ts",
            () -> new TimeStop(new TimeStop.Builder()
                    .holdToFire(40, false)
                    .staminaCost(75)
                    .staminaCostTick(2F)
                    .heldWalkSpeed(0)
                    .ignoresPerformerStun().autoSummonStand()
                    .shout(ModSounds.DIO_THE_WORLD) //Mudar pra um customizado
                    .partsRequired(StandPart.MAIN_BODY)
                    .timeStopSound(ModSounds.STAR_PLATINUM_TIME_STOP) //Mudar pra um customizado
                    .timeResumeSound(ModSounds.STAR_PLATINUM_TIME_RESUME))); //Mudar pra um customizado

    public static final RegistryObject<StandAction> WILKER_STAND_TS_BLINK = ACTIONS.register("wilker_stand_ts_blink",
            () -> new TimeStopInstant(new StandAction.Builder()
                    .resolveLevelToUnlock(4).isTrained()
                    .ignoresPerformerStun()
                    .partsRequired(StandPart.MAIN_BODY),
                    WILKER_STAND_TS, ModSounds.STAR_PLATINUM_TIME_STOP_BLINK
            ));
    public static final RegistryObject<TimeResume> WILKER_STAND_TS_RESUME = ACTIONS.register("wilker_stand_time_resume",
            () -> new TimeResume(new StandAction.Builder()
                    .shiftVariationOf(WILKER_STAND_TS)));
    
    

    // ...then create the Stand type instance. Moves, stats, entity sizes, and a few other things are determined here.
    public static final EntityStandRegistryObject<EntityStandType<TimeStopperStandStats>, StandEntityType<WilkerStandEntity>> STAND_WILKER_STAND =
            new EntityStandRegistryObject<>("wilker_stand",
                    STANDS, 
                    () -> new EntityStandType.Builder<TimeStopperStandStats>()
                    .color(0x23D92F)
//                    .storyPartName()
                    .leftClickHotbar(
                            WILKER_STAND_PUNCH.get(),
                            WILKER_STAND_BARRAGE.get()
                            )
                    .rightClickHotbar(
                            WILKER_STAND_BLOCK.get(),
                            WILKER_STAND_THROW_PICKAXE.get(),
                            WILKER_STAND_TS.get()
                            )
                    .defaultStats(TimeStopperStandStats.class, new TimeStopperStandStats.Builder()
                            .tier(6)
                            .power(20)
                            .speed(20)
                            .range(50, 100)
                            .durability(20)
                            .precision(20)
                            .timeStopMaxTicks(800, 1400)
                            .timeStopLearningPerTick(0.1F)
                            .timeStopDecayPerDay(0F)
                            .timeStopCooldownPerTick(0.1F)
                            .build())
                    .addSummonShout(InitSounds.WILKER_STAND_SUMMON_VOICELINE)
                    .addOst(InitSounds.WILKER_STAND_OST)
                    .build(),
                    
                    InitEntities.ENTITIES,
                    () -> new StandEntityType<WilkerStandEntity>(WilkerStandEntity::new, 0.7F, 2.1F)
                    .summonSound(InitSounds.WILKER_STAND_SUMMON_SOUND)
                    .unsummonSound(InitSounds.WILKER_STAND_UNSUMMON_SOUND))
            .withDefaultStandAttributes();
    

    
    // ======================================== ??? ========================================

    // //Modificado após sucesso / "action.myrotpaddon.wilker_stand_ts.invasion": "Invasão de Tempo (%ss)", / "action.myrotpaddon.wilker_stand_ts_blink_shortened": "Dando tp",
    
    
    
}
