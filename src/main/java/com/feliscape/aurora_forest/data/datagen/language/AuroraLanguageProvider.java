package com.feliscape.aurora_forest.data.datagen.language;

import com.feliscape.aurora_forest.Aurora;
import com.feliscape.aurora_forest.registry.AuroraBlocks;
import com.feliscape.aurora_forest.registry.AuroraItems;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public class AuroraLanguageProvider extends LanguageProvider {
    public AuroraLanguageProvider(PackOutput output, String locale) {
        super(output, Aurora.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        this.addItem(AuroraItems.BOREALITE_CRYSTAL, "Borealite Crystal");
        this.addItem(AuroraItems.AUSTRALITE_CRYSTAL, "Australite Crystal");

        this.addBlockAndItem(AuroraBlocks.AUSTRALITE_BLOCK, "Block of Australite");
        this.addBlockAndItem(AuroraBlocks.BOREALITE_BLOCK, "Block of Borealite");
        this.addBlockAndItem(AuroraBlocks.AURORA_LEAVES, "Aurora Leaves");

        this.addBlockAndItem(AuroraBlocks.AURORA_LOG, "Aurora Log");
        this.addBlockAndItem(AuroraBlocks.STRIPPED_AURORA_LOG, "Stripped Aurora Log");
        this.addBlockAndItem(AuroraBlocks.AURORA_WOOD, "Aurora Wood");
        this.addBlockAndItem(AuroraBlocks.STRIPPED_AURORA_WOOD, "Stripped Aurora Wood");
        this.addBlockAndItem(AuroraBlocks.AURORA_PLANKS, "Aurora Planks");
        this.addBlockAndItem(AuroraBlocks.AURORA_STAIRS, "Aurora Stairs");
        this.addBlockAndItem(AuroraBlocks.AURORA_SLAB, "Aurora Slab");
        this.addBlockAndItem(AuroraBlocks.AURORA_FENCE, "Aurora Fence");
        this.addBlockAndItem(AuroraBlocks.AURORA_FENCE_GATE, "Aurora Fence Gate");
        this.addBlockAndItem(AuroraBlocks.AURORA_BUTTON, "Aurora Button");
        this.addBlockAndItem(AuroraBlocks.AURORA_PRESSURE_PLATE, "Aurora Pressure Plate");
        this.addBlockAndItem(AuroraBlocks.AURORA_DOOR, "Aurora Door");
        this.addBlockAndItem(AuroraBlocks.AURORA_TRAPDOOR, "Aurora Trapdoor");

        this.add("creative_tab.aurora.base", "Aurora Forest");
    }

    public void addBlockAndItem(Supplier<? extends Block> key, String name) {
        this.addBlock(key, name);
        this.addItem(key.get()::asItem, name);
    }

    private void addItemTooltip(Supplier<? extends Item> key, String name) {
        add(key.get().getDescriptionId() + ".tooltip", name);
    }
    private void addMobEffect(Supplier<? extends MobEffect> key, String name) {
        add(key.get().getDescriptionId(), name);
    }
    private void addSubtitle(Supplier<SoundEvent> key, String name) {
        add("subtitle.{}.{}".formatted(Aurora.MOD_ID, key.get().location().getPath()), name);
    }
}
