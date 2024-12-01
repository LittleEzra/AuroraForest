package com.feliscape.aurora_forest.registry;

import com.feliscape.aurora_forest.Aurora;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AuroraItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Aurora.MOD_ID);

    public static final DeferredItem<Item> AUSTRALITE_CRYSTAL = ITEMS.registerItem("australite_crystal", Item::new);
    public static final DeferredItem<Item> BOREALITE_CRYSTAL = ITEMS.registerItem("borealite_crystal", Item::new);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
