package net.priestofpain.lordofthedead.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.priestofpain.lordofthedead.LordOfTheDead;

public class ModModelLayers {
    public static final ModelLayerLocation SUMMONED_ZOMBIE_LAYER =
            new ModelLayerLocation(
                    new ResourceLocation(LordOfTheDead.MOD_ID, "summoned_zombie_layer"), "main");
}
