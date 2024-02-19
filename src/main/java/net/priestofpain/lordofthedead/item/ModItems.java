package net.priestofpain.lordofthedead.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.priestofpain.lordofthedead.LordOfTheDead;

public class ModItems {
    // Adds mod items to be registered when forge loads
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, LordOfTheDead.MOD_ID);

    public static final RegistryObject<Item> NECROMANCER_STAFF = ITEMS.register("necromancer staff",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
