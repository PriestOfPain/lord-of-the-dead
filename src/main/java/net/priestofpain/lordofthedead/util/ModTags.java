package net.priestofpain.lordofthedead.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.priestofpain.lordofthedead.LordOfTheDead;

public class ModTags {
    // Block tags
    public static class Blocks {
        /**
         * We do not have any blocks that require tags (yet!)
         * example:
         * public static final TagKey<Block> EXAMPLE = tag("example");
         */


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(LordOfTheDead.MOD_ID, name));
        }
    }

    // Item tags
    public static class Items {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(LordOfTheDead.MOD_ID, name));
        }
    }
}
