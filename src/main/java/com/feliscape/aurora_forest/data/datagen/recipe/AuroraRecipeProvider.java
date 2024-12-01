package com.feliscape.aurora_forest.data.datagen.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class AuroraRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public AuroraRecipeProvider(HolderLookup.Provider pRegistries, RecipeOutput pOutput) {
        super(pRegistries, pOutput);
    }

    @Override
    protected void buildRecipes() {

    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(packOutput, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider recipeProvider, RecipeOutput output) {
            return new AuroraRecipeProvider(recipeProvider, output);
        }

        @Override
        public String getName() {
            return "Deepwood Recipes";
        }
    }
}