package net.priestofpain.lordofthedead.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.PlayerMainInvWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NecromancerStaffItem extends Item {

    public NecromancerStaffItem(Properties pProperties) {
        super(pProperties);
    }

    // If player right-clicks a block with the item
    // spawn a zombie
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Level level = pContext.getLevel();
            Direction direction = pContext.getClickedFace();
            BlockState blockState = level.getBlockState(positionClicked);
            int minSummonItemAmount = 3;


            // First choice of spawn placement, otherwise
            // get nearest available spawn point
            BlockPos blockPos1;
            if (blockState.getCollisionShape(level, positionClicked).isEmpty()) {
                blockPos1 = positionClicked;
            } else {
                blockPos1 = positionClicked.relative(direction);
            }

            // Check player inventory to see if they have the required components
            // to summon a zombie (3 rotten flesh)
            IItemHandler inventory = new PlayerMainInvWrapper(pContext.getPlayer().getInventory());
            for (int i = 0; i < inventory.getSlots(); i++) {
                if (inventory.getStackInSlot(i).getItem() == Items.ROTTEN_FLESH &&
                    inventory.getStackInSlot(i).getCount() >= minSummonItemAmount) {
                    inventory.getStackInSlot(i).shrink(3);
                    // Spawn our zombie!
                    EntityType<Zombie> zombie = EntityType.ZOMBIE;
                    if (zombie.spawn((ServerLevel) level, blockPos1, MobSpawnType.MOB_SUMMONED) != null) {
                        level.gameEvent(pContext.getPlayer(), GameEvent.ENTITY_PLACE, positionClicked);
                        return InteractionResult.CONSUME;
                    }
                }
            }
        }
        return InteractionResult.FAIL;
    }
    // In game tooltip
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.lord_of_the_dead.necromancer_staff.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}