package net.priestofpain.lordofthedead.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class NecromancerStaffItem extends Item {

    public NecromancerStaffItem(Properties pProperties) {
        super(pProperties);
    }

    // If player right clicks a block with the item
    // spawn a zombie
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Level level = pContext.getLevel();
            Direction direction = pContext.getClickedFace();
            BlockState blockState = level.getBlockState(positionClicked);

            // First choice of spawn placement, otherwise
            // get nearest available spawnpoint
            BlockPos blockPos1;
            if(blockState.getCollisionShape(level, positionClicked).isEmpty()) {
                blockPos1 = positionClicked;
            } else {
                blockPos1 = positionClicked.relative(direction);
            }

            // Spawn our zombie!
            EntityType<Zombie> zombie = EntityType.ZOMBIE;
            if(zombie.spawn((ServerLevel) level, blockPos1, MobSpawnType.MOB_SUMMONED) != null) {
                level.gameEvent(pContext.getPlayer(), GameEvent.ENTITY_PLACE, positionClicked);
            }

            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.FAIL;
        }
    }
}
