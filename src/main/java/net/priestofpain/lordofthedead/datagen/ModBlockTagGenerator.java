package net.priestofpain.lordofthedead.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.priestofpain.lordofthedead.LordOfTheDead;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, LordOfTheDead.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        /** We have no block tags (yet!)
         *  example code:
         *  this.tag(ModBlocks.EXAMPLE_TAG)
         *      .add(ModBlocks.EXAMPLE_BLOCK.get()).addTag(Tags.Blocks.EXAMPLE);
         */
    }
}
