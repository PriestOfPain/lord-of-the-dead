package net.priestofpain.lordofthedead.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.priestofpain.lordofthedead.item.ModItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        // Necromancer staff crafting recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.NECROMANCER_STAFF.get())
                .pattern("GDG")
                .pattern("ISI")
                .pattern("ISI")
                .define('G', Items.GOLD_INGOT)
                .define('D', Items.DIAMOND)
                .define('I', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
    }
}
