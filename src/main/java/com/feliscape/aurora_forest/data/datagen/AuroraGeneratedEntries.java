package com.feliscape.aurora_forest.data.datagen;

import com.feliscape.aurora_forest.Aurora;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class AuroraGeneratedEntries extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            //.add(Registries.CONFIGURED_FEATURE, DeepwoodConfiguredFeatures::bootstrap)
            //.add(Registries.PLACED_FEATURE, DeepwoodPlacedFeatures::bootstrap)
            //.add(ForgeRegistries.Keys.BIOME_MODIFIERS, DeepwoodBiomeModifiers::bootstrap)
            //.add(Registries.DAMAGE_TYPE, DeepwoodDamageTypes::bootstrap)
            //.add(Registries.NOISE, DeepwoodNoise::bootstrap)
            ;
    public AuroraGeneratedEntries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Aurora.MOD_ID));
    }
}
