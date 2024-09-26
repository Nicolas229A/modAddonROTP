package com.rotpaddon.wilker.client.render;

import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.client.render.entity.model.stand.HumanoidStandModel;
import com.github.standobyte.jojo.client.render.entity.pose.IModelPose;
import com.github.standobyte.jojo.client.render.entity.pose.ModelPose;
import com.github.standobyte.jojo.client.render.entity.pose.ModelPoseTransitionMultiple;
import com.github.standobyte.jojo.client.render.entity.pose.RotationAngle;
import com.github.standobyte.jojo.client.render.entity.pose.anim.PosedActionAnimation;
import com.github.standobyte.jojo.entity.stand.StandPose;
import com.rotpaddon.wilker.entity.WilkerStandEntity;

import net.minecraft.client.renderer.model.ModelRenderer;

public class WilkerStandModel extends HumanoidStandModel<WilkerStandEntity> {
    private ModelRenderer pickaxe;

	public WilkerStandModel() {
		super();
	}

	@Override // TODO summon poses
    protected RotationAngle[][] initSummonPoseRotations() {
        return new RotationAngle[][] {
            new RotationAngle[] {
                    
            },
            new RotationAngle[] {
                    
            }
		};
    }
    
    @Override
    protected void initActionPoses() { // TODO pickaxe throwing anim
        actionAnim.put(StandPose.RANGED_ATTACK, new PosedActionAnimation.Builder<WilkerStandEntity>()
                .addPose(StandEntityAction.Phase.BUTTON_HOLD, new ModelPose<>(new RotationAngle[] {
                        new RotationAngle(body, 0.0F, -0.48F, 0.0F),
                        new RotationAngle(leftArm, 0.0F, 0.0F, -0.7854F),
                        new RotationAngle(leftForeArm, 0.0F, 0.0F, 0.6109F),
                        new RotationAngle(rightArm, -1.0908F, 0.0F, 1.5708F), 
                        new RotationAngle(rightForeArm, 0.0F, 0.0F, 0.0F)
                }))
                .build(idlePose));

        //My addition

        ModelPose<WilkerStandEntity> kickPose1 = new ModelPose<>(new RotationAngle[] {
                RotationAngle.fromDegrees(upperPart, -5, 7.5F, 0),
                RotationAngle.fromDegrees(rightLeg, -5, 0, 0),
                RotationAngle.fromDegrees(rightLowerLeg, 5, 0, 0),

        });

        ModelPose<WilkerStandEntity> kickPose2 = new ModelPose<>(new RotationAngle[] {
                RotationAngle.fromDegrees(upperPart, -5, 15, 0),
                RotationAngle.fromDegrees(rightLeg, -120, 0, 0),
                RotationAngle.fromDegrees(rightLowerLeg, 92.5F, 0, 0),

        });

        ModelPose<WilkerStandEntity> kickPose3 = new ModelPose<>(new RotationAngle[] {
                RotationAngle.fromDegrees(upperPart, -5, -15, 0),
                RotationAngle.fromDegrees(rightLeg, -92.5F, 0, 0),
                RotationAngle.fromDegrees(rightLowerLeg, 5, 0, 0),

        });

        actionAnim.put(StandPose.HEAVY_ATTACK_FINISHER, new PosedActionAnimation.Builder<WilkerStandEntity>()
                .addPose(StandEntityAction.Phase.WINDUP, new ModelPoseTransitionMultiple.Builder<>(idlePose)
                        .addPose(0.3F, kickPose1)
                        .addPose(0.9F, kickPose2)
                        .build(kickPose3))
                .build(idlePose));

        //End of my addition
        
        super.initActionPoses();
    }

    @Override
    public void prepareMobModel(WilkerStandEntity entity, float walkAnimPos, float walkAnimSpeed, float partialTick) {
        super.prepareMobModel(entity, walkAnimPos, walkAnimSpeed, partialTick);
        if (pickaxe != null) {
            pickaxe.visible = entity.hasPickaxe();
        }
    }
    
    

    @Override // TODO idle pose
    protected ModelPose<WilkerStandEntity> initIdlePose() {
        return super.initIdlePose();
    }

    @Override
    protected IModelPose<WilkerStandEntity> initIdlePose2Loop() {
        return super.initIdlePose2Loop();
    }
}