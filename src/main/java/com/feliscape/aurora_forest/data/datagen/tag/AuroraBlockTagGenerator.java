package com.feliscape.aurora_forest.data.datagen.tag;

import com.feliscape.aurora_forest.Aurora;
import com.feliscape.aurora_forest.registry.AuroraBlocks;
import com.feliscape.aurora_forest.registry.AuroraTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class AuroraBlockTagGenerator extends BlockTagsProvider {
    public AuroraBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Aurora.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(AuroraBlocks.AUSTRALITE_BLOCK.get())
                .add(AuroraBlocks.BOREALITE_BLOCK.get())
        ;
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(AuroraBlocks.AURORA_LOG.get())
                .add(AuroraBlocks.STRIPPED_AURORA_LOG.get())
                .add(AuroraBlocks.AURORA_WOOD.get())
                .add(AuroraBlocks.STRIPPED_AURORA_WOOD.get())
                .add(AuroraBlocks.AURORA_PLANKS.get())
                .add(AuroraBlocks.AURORA_STAIRS.get())
                .add(AuroraBlocks.AURORA_SLAB.get())
                .add(AuroraBlocks.AURORA_FENCE.get())
                .add(AuroraBlocks.AURORA_FENCE_GATE.get())
                .add(AuroraBlocks.AURORA_BUTTON.get())
                .add(AuroraBlocks.AURORA_PRESSURE_PLATE.get())
                .add(AuroraBlocks.AURORA_DOOR.get())
                .add(AuroraBlocks.AURORA_TRAPDOOR.get())
        ;
        this.tag(BlockTags.LEAVES)
                .add(AuroraBlocks.AURORA_LEAVES.get())
        ;
        this.tag(AuroraTags.Blocks.AURORA_LOGS)
                .add(AuroraBlocks.AURORA_LOG.get())
                .add(AuroraBlocks.STRIPPED_AURORA_LOG.get())
                .add(AuroraBlocks.AURORA_WOOD.get())
                .add(AuroraBlocks.STRIPPED_AURORA_WOOD.get())
        ;
        this.tag(BlockTags.LOGS_THAT_BURN)
                .addTag(AuroraTags.Blocks.AURORA_LOGS)
        ;
        this.tag(BlockTags.WOODEN_FENCES)
                .add(AuroraBlocks.AURORA_FENCE.get())
        ;
        this.tag(BlockTags.FENCE_GATES)
                .add(AuroraBlocks.AURORA_FENCE_GATE.get())
        ;
    }
}
