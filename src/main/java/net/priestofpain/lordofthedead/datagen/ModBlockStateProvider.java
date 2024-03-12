package net.priestofpain.lordofthedead.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.priestofpain.lordofthedead.LordOfTheDead;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, LordOfTheDead.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        /** This is empty because we have no blocks (yet!)
         *  example:
         *  blockWithItem(ModBlocks.EXAMPLE);
         */
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
