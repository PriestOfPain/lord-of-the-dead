package net.priestofpain.lordofthedead.event;

import net.minecraft.client.model.ZombieModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.priestofpain.lordofthedead.LordOfTheDead;
import net.priestofpain.lordofthedead.entity.client.ModModelLayers;
import net.priestofpain.lordofthedead.entity.client.SummonedZombieModel;

@Mod.EventBusSubscriber(modid = LordOfTheDead.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.SUMMONED_ZOMBIE_LAYER, SummonedZombieModel::createBodyLayer);
    }
}
