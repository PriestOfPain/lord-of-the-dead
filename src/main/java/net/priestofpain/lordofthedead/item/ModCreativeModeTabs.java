package net.priestofpain.lordofthedead.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.priestofpain.lordofthedead.LordOfTheDead;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LordOfTheDead.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB =
            CREATIVE_MODE_TABS.register("lord_of_the_dead_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModItems.NECROMANCER_STAFF.get()))
                            .title(Component.translatable("creativetab.lord_of_the_dead_tab"))
                            .displayItems((pParameters, pOutput) -> {
                                pOutput.accept(ModItems.NECROMANCER_STAFF.get());
                            })
                            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
