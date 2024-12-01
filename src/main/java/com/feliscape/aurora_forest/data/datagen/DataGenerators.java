package com.feliscape.aurora_forest.data.datagen;

import com.feliscape.aurora_forest.Aurora;
import com.feliscape.aurora_forest.data.datagen.language.AuroraLanguageProvider;
import com.feliscape.aurora_forest.data.datagen.loot.AuroraBlockLootTableProvider;
import com.feliscape.aurora_forest.data.datagen.model.AuroraBlockStateProvider;
import com.feliscape.aurora_forest.data.datagen.model.AuroraItemModelProvider;
import com.feliscape.aurora_forest.data.datagen.recipe.AuroraRecipeProvider;
import com.feliscape.aurora_forest.data.datagen.tag.AuroraBlockTagGenerator;
import com.feliscape.aurora_forest.data.datagen.tag.AuroraItemTagGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Aurora.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        if (event.includeServer()){
            AuroraGeneratedEntries generatedEntries = new AuroraGeneratedEntries(packOutput, lookupProvider);
            lookupProvider = generatedEntries.getRegistryProvider();
            generator.addProvider(true, generatedEntries);

            generator.addProvider(true, new AuroraBlockStateProvider(packOutput, existingFileHelper));
            generator.addProvider(true, new AuroraItemModelProvider(packOutput, existingFileHelper));
            generator.addProvider(true, new AuroraRecipeProvider.Runner(packOutput, lookupProvider));

            var blockTags = new AuroraBlockTagGenerator(packOutput, lookupProvider, existingFileHelper);
            generator.addProvider(true, blockTags);
            generator.addProvider(true, new AuroraItemTagGenerator(packOutput, lookupProvider, blockTags.contentsGetter()));

            generator.addProvider(true, new AuroraLanguageProvider(packOutput, "en_us"));

            generator.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(),
                    List.of(new LootTableProvider.SubProviderEntry(AuroraBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
        }
    }
}
