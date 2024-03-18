package net.priestofpain.lordofthedead.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * This item can be used to turn an existing undead entity
 * into an ally of the player.
 */
public class TomeOfControlUndeadItem extends Item {
    public TomeOfControlUndeadItem(Properties pProperties) {
        super(pProperties);
    }

    // Purely to test how interactLivingEntity works
    // Right now, this item just heals a zombie
    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        if(pInteractionTarget instanceof Zombie) {
            if(!pPlayer.level().isClientSide && pInteractionTarget.isAlive()) {
                pInteractionTarget.heal(20f);
                pStack.shrink(1);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.FAIL;
    }
}
