package com.feliscape.aurora_forest.data.datagen.tag;

import com.feliscape.aurora_forest.Aurora;
import com.feliscape.aurora_forest.registry.AuroraTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class AuroraItemTagGenerator extends ItemTagsProvider {
    public AuroraItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.copy(AuroraTags.Blocks.AURORA_LOGS, AuroraTags.Items.AURORA_LOGS);
    }
}
