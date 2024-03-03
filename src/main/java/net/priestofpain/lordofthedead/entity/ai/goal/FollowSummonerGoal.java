package net.priestofpain.lordofthedead.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

import java.util.EnumSet;

public class FollowSummonerGoal extends Goal {
    public static final int TELEPORT_WHEN_DISTANCE_IS = 12;
    private static final int MIN_HORIZONTAL_DISTANCE_FROM_PLAYER_WHEN_TELEPORTING = 2;
    private static final int MAX_HORIZONTAL_DISTANCE_FROM_PLAYER_WHEN_TELEPORTING = 3;
    private static final int MAX_VERTICAL_DISTANCE_FROM_PLAYER_WHEN_TELEPORTING = 1;
    private final Zombie zombie;
    private LivingEntity summoner;
    private final LevelReader level;
    private final double speedModifier;
    private final PathNavigation navigation;
    private int timeToRecalcPath;
    private final float stopDistance;
    private final float startDistance;
    private float oldWaterCost;
    private final boolean canFly;

    public FollowSummonerGoal(Zombie pZombie, double pSpeedModifier, float pStartDistance, float pStopDistance, boolean pCanFly){
        this.zombie = pZombie;
        this.level = pZombie.level();
        this.speedModifier = pSpeedModifier;
        this.navigation = pZombie.getNavigation();
        this.startDistance = pStartDistance;
        this.stopDistance = pStopDistance;
        this.canFly = pCanFly;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        if (!(pZombie.getNavigation() instanceof GroundPathNavigation) && !(pZombie.getNavigation() instanceof FlyingPathNavigation)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
        }
    }
    @Override
    public boolean canUse() {
        LivingEntity $$0 = this.zombie.getOwner();
        if ($$0 == null) {
            return false;
        } else if ($$0.isSpectator()) {
            return false;
        } else if (this.zombie.distanceToSqr($$0) < (double)(this.startDistance * this.startDistance)) {
            return false;
        } else {
            this.summoner = $$0;
            return true;
        }
    }

    public boolean canContinueToUse() {
        if (this.navigation.isDone()) {
            return false;
        } else {
            return !(this.zombie.distanceToSqr(this.summoner) <= (double)(this.stopDistance * this.stopDistance));
        }
    }

    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.zombie.getPathfindingMalus(BlockPathTypes.WATER);
        this.zombie.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    public void stop() {
        this.summoner = null;
        this.navigation.stop();
        this.zombie.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
    }

    public void tick() {
        this.zombie.getLookControl().setLookAt(this.summoner, 10.0F, (float)this.zombie.getMaxHeadXRot());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            if (this.zombie.distanceToSqr(this.summoner) >= 144.0) {
                this.teleportToOwner();
            } else {
                this.navigation.moveTo(this.summoner, this.speedModifier);
            }

        }
    }

    private void teleportToOwner() {
        BlockPos $$0 = this.summoner.blockPosition();

        for(int $$1 = 0; $$1 < 10; ++$$1) {
            int $$2 = this.randomIntInclusive(-3, 3);
            int $$3 = this.randomIntInclusive(-1, 1);
            int $$4 = this.randomIntInclusive(-3, 3);
            boolean $$5 = this.maybeTeleportTo($$0.getX() + $$2, $$0.getY() + $$3, $$0.getZ() + $$4);
            if ($$5) {
                return;
            }
        }

    }

    private boolean maybeTeleportTo(int pX, int pY, int pZ) {
        if (Math.abs((double)pX - this.summoner.getX()) < 2.0 && Math.abs((double)pZ - this.summoner.getZ()) < 2.0) {
            return false;
        } else if (!this.canTeleportTo(new BlockPos(pX, pY, pZ))) {
            return false;
        } else {
            this.zombie.moveTo((double)pX + 0.5, (double)pY, (double)pZ + 0.5, this.zombie.getYRot(), this.zombie.getXRot());
            this.navigation.stop();
            return true;
        }
    }

    private boolean canTeleportTo(BlockPos pPos) {
        BlockPathTypes $$1 = WalkNodeEvaluator.getBlockPathTypeStatic(this.level, pPos.mutable());
        if ($$1 != BlockPathTypes.WALKABLE) {
            return false;
        } else {
            BlockState $$2 = this.level.getBlockState(pPos.below());
            if (!this.canFly && $$2.getBlock() instanceof LeavesBlock) {
                return false;
            } else {
                BlockPos $$3 = pPos.subtract(this.zombie.blockPosition());
                return this.level.noCollision(this.zombie, this.zombie.getBoundingBox().move($$3));
            }
        }
    }

    private int randomIntInclusive(int pMin, int pMax) {
        return this.zombie.getRandom().nextInt(pMax - pMin + 1) + pMin;
    }
}
