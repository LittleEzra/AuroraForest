package com.feliscape.aurora_forest.registry;

import com.feliscape.aurora_forest.Aurora;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class AuroraTags {
    public static class Blocks{
        public static final TagKey<Block> AURORA_LOGS = create("aurora_logs");

        private static TagKey<Block> create(String name){
            return TagKey.create(Registries.BLOCK, Aurora.asResource(name));
        }
    }
    public static class Items{
        public static final TagKey<Item> AURORA_LOGS = create("aurora_logs");

        private static TagKey<Item> create(String name){
            return TagKey.create(Registries.ITEM, Aurora.asResource(name));
        }
    }
}
