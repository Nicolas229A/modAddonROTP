package com.rotpaddon.wilker.action;

import javax.annotation.Nullable;

import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.rotpaddon.wilker.capability.LivingData;
import com.rotpaddon.wilker.capability.LivingDataProvider;
import com.rotpaddon.wilker.entity.WilkerPickaxeEntity;
import com.rotpaddon.wilker.entity.WilkerStandEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

// An ability for our Example Stand.
public class WilkerStandThrowPickaxe extends StandEntityAction {

    public WilkerStandThrowPickaxe(StandEntityAction.Builder builder) {
        super(builder);
    }
    
    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            // Creates a new entity for the projectile, then throw it.
            WilkerPickaxeEntity pickaxe = new WilkerPickaxeEntity(standEntity, world);
            standEntity.shootProjectile(pickaxe, 1.5F, 1.0F);
            
            LivingEntity user = userPower.getUser();
            if (user != null) {
                // Updates the data attached to the user.
                LazyOptional<LivingData> livingDataOptional = user.getCapability(LivingDataProvider.CAPABILITY);
                livingDataOptional.ifPresent(livingData -> {
                    livingData.setPickaxesThrown(livingData.getPickaxesThrown() + 1);
                });
            }
        }
    }
    
    @Override
    public void onTaskSet(World world, StandEntity standEntity, IStandPower standPower, 
            Phase phase, StandEntityTask task, int ticks) {
        if (!world.isClientSide() && standEntity instanceof WilkerStandEntity) {
            // This flag determines if a pickaxe model is rendered in the Stand's hand (see ExampleStandModel).
            ((WilkerStandEntity) standEntity).setHasPickaxe(true);
        }
    }

    @Override
    protected void onTaskStopped(World world, StandEntity standEntity, IStandPower standPower, 
            StandEntityTask task, @Nullable StandEntityAction newAction) {
        if (!world.isClientSide() && standEntity instanceof WilkerStandEntity) {
            ((WilkerStandEntity) standEntity).setHasPickaxe(false);
        }
    }

}
