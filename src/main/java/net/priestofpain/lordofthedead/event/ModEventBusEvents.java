package net.priestofpain.lordofthedead.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.priestofpain.lordofthedead.LordOfTheDead;
import net.priestofpain.lordofthedead.entity.ModEntities;
import net.priestofpain.lordofthedead.entity.custom.SummonedZombieEntity;

@Mod.EventBusSubscriber(modid = LordOfTheDead.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SUMMONED_ZOMBIE.get(), SummonedZombieEntity.createAttributes().build());
    }
}
