package com.feliscape.aurora_forest.registry;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.Map;

public class AuroraBlockSetTypes {
    private static final Map<String, BlockSetType> TYPES = new Object2ObjectArrayMap<>();

    public static final BlockSetType AURORA = register(new BlockSetType("aurora"));

    public static BlockSetType register(BlockSetType value) {
        TYPES.put(value.name(), value);
        return value;
    }
}
