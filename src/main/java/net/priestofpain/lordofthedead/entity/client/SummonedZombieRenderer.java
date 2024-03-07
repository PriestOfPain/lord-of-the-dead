package net.priestofpain.lordofthedead.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.priestofpain.lordofthedead.LordOfTheDead;
import net.priestofpain.lordofthedead.entity.custom.SummonedZombieEntity;

public class SummonedZombieRenderer extends MobRenderer<SummonedZombieEntity, SummonedZombieModel<SummonedZombieEntity>> {

    public SummonedZombieRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SummonedZombieModel<>(pContext.bakeLayer(ModModelLayers.SUMMONED_ZOMBIE_LAYER)), 2f);
    }

    @Override
    public ResourceLocation getTextureLocation(SummonedZombieEntity summonedZombieEntity) {
        return new ResourceLocation(LordOfTheDead.MOD_ID, "textures/entity/summoned_zombie.png");
    }
}
