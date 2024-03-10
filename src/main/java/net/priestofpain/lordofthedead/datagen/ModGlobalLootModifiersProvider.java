package net.priestofpain.lordofthedead.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.priestofpain.lordofthedead.LordOfTheDead;
import net.priestofpain.lordofthedead.item.ModItems;
import net.priestofpain.lordofthedead.loot.AddItemModifier;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, LordOfTheDead.MOD_ID);
    }

    @Override
    protected void start() {
        // Necromancer staff has a 1% chance to drop from a zombie
        add("necromancer_staff_from_zombie", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/zombie")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()},
                ModItems.NECROMANCER_STAFF.get()));
    }
}
