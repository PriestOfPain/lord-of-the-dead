package net.priestofpain.lordofthedead.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.priestofpain.lordofthedead.LordOfTheDead;
import net.priestofpain.lordofthedead.entity.custom.SummonedZombieEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, LordOfTheDead.MOD_ID);

    public static final RegistryObject<EntityType<SummonedZombieEntity>> SUMMONED_ZOMBIE =
            ENTITY_TYPES.register("summoned zombie", () -> EntityType.Builder.of(SummonedZombieEntity::new,
                    MobCategory.MONSTER).sized(1.0f, 1.0f).build("summoned zombie"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
