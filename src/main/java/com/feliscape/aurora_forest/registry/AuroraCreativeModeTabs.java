package com.feliscape.aurora_forest.registry;

import com.feliscape.aurora_forest.Aurora;
import it.unimi.dsi.fastutil.objects.ReferenceArrayList;
import it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class AuroraCreativeModeTabs {
    private static final DeferredRegister<CreativeModeTab> REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Aurora.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BASE_CREATIVE_TAB = REGISTER.register("base",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creative_tab.aurora.base"))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .icon(() -> new ItemStack(AuroraItems.BOREALITE_CRYSTAL.get()))
                    /*.displayItems(((pParameters, pOutput) -> {
                        pOutput.accept(WinterShineBlocks.CONFECTIONERY_OVEN.get());
                        pOutput.accept(WinterShineBlocks.CANDY_CANE_LOG.get());

                        pOutput.accept(WinterShineItems.CANDY_CANE.get());
                    }))*/
                    .displayItems(new DisplayItemsGenerator())
                    .build());

    public static void register(IEventBus eventBus){
        REGISTER.register(eventBus);
    }

    // BASE
    private static class DisplayItemsGenerator implements CreativeModeTab.DisplayItemsGenerator {

        private static Predicate<Item> makeExclusionPredicate() {
            Set<Item> exclusions = new ReferenceOpenHashSet<>();

            // Items to exclude from all tabs
            List<DeferredItem<?>> simpleExclusions = List.of(

            );

            for (DeferredItem<?> entry : simpleExclusions) {
                exclusions.add(entry.asItem());
            }

            return exclusions::contains;
        }

        private List<Item> collectBlocks(Predicate<Item> exclusionPredicate) {
            List<Item> items = new ReferenceArrayList<>();
            for (DeferredHolder<Block, ?> entry : AuroraBlocks.BLOCKS.getEntries()) {
                Item item = entry.get()
                        .asItem();
                if (item == Items.AIR)
                    continue;
                if (!exclusionPredicate.test(item))
                    items.add(item);
            }
            items = new ReferenceArrayList<>(new ReferenceLinkedOpenHashSet<>(items));
            return items;
        }

        private List<Item> collectItems(Predicate<Item> exclusionPredicate) {
            List<Item> items = new ReferenceArrayList<>();
            for (DeferredHolder<Item, ?> entry : AuroraItems.ITEMS.getEntries()) {
                Item item = entry.get();
                if (item instanceof BlockItem)
                    continue;
                if (!exclusionPredicate.test(item))
                    items.add(item);
            }
            return items;
        }

        @Override
        public void accept(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
            Predicate<Item> exclusionPredicate = makeExclusionPredicate();

            List<Item> items = new LinkedList<>();
            items.addAll(collectItems(exclusionPredicate));
            items.addAll(collectBlocks(exclusionPredicate));

            outputAll(output, items);
        }
        private static void outputAll(CreativeModeTab.Output output, List<Item> items) {
            for (Item item : items) {
                output.accept(item);
            }
        }
    }
}
