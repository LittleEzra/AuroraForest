package com.feliscape.aurora_forest.data.datagen.loot;

import com.feliscape.aurora_forest.registry.AuroraBlocks;
import com.feliscape.aurora_forest.registry.AuroraItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class AuroraBlockLootTableProvider extends BlockLootSubProvider {
    public AuroraBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        this.add(AuroraBlocks.AUSTRALITE_BLOCK.get(), b -> createCrystalDrops(b, AuroraItems.AUSTRALITE_CRYSTAL));
        this.add(AuroraBlocks.BOREALITE_BLOCK.get(), b -> createCrystalDrops(b, AuroraItems.BOREALITE_CRYSTAL));
        this.add(AuroraBlocks.AURORA_LEAVES.get(), b -> LootTable.lootTable());

        this.dropSelf(AuroraBlocks.AURORA_LOG.get());
        this.dropSelf(AuroraBlocks.STRIPPED_AURORA_LOG.get());
        this.dropSelf(AuroraBlocks.AURORA_WOOD.get());
        this.dropSelf(AuroraBlocks.STRIPPED_AURORA_WOOD.get());
        this.dropSelf(AuroraBlocks.AURORA_PLANKS.get());

        /*this.add(AuroraBlocks.AURORA_SIGN.get(), block ->
                createSingleItemTable(AuroraItems.AURORA_SIGN.get()));
        this.add(AuroraBlocks.AURORA_WALL_SIGN.get(), block ->
                createSingleItemTable(AuroraItems.AURORA_SIGN.get()));
        this.add(AuroraBlocks.AURORA_HANGING_SIGN.get(), block ->
                createSingleItemTable(AuroraItems.AURORA_HANGING_SIGN.get()));
        this.add(AuroraBlocks.AURORA_WALL_HANGING_SIGN.get(), block ->
                createSingleItemTable(AuroraItems.AURORA_HANGING_SIGN.get()));*/

        dropSelf(AuroraBlocks.AURORA_STAIRS.get());
        dropSelf(AuroraBlocks.AURORA_BUTTON.get());
        dropSelf(AuroraBlocks.AURORA_PRESSURE_PLATE.get());
        dropSelf(AuroraBlocks.AURORA_TRAPDOOR.get());
        dropSelf(AuroraBlocks.AURORA_FENCE.get());
        dropSelf(AuroraBlocks.AURORA_FENCE_GATE.get());

        this.add(AuroraBlocks.AURORA_SLAB.get(),
                block -> createSlabItemTable(AuroraBlocks.AURORA_SLAB.get()));
        this.add(AuroraBlocks.AURORA_DOOR.get(),
                block -> createDoorTable(AuroraBlocks.AURORA_DOOR.get()));
    }

    protected LootTable.Builder createCrystalDrops(Block block, ItemLike crystal){
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block, LootItem.lootTableItem(crystal)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ));
    }

    protected void dropOtherWithoutSilkTouch(Block block, ItemLike other){
        this.add(block, b -> this.createSingleItemTableWithSilkTouch(b, other));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return AuroraBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
