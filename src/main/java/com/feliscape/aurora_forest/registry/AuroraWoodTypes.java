package com.feliscape.aurora_forest.registry;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.Map;
import java.util.stream.Stream;

public class AuroraWoodTypes {
    private static final Map<String, WoodType> TYPES = new Object2ObjectArrayMap<>();
    public static final Codec<WoodType> CODEC = Codec.stringResolver(WoodType::name, TYPES::get);

    public static final WoodType AURORA = register(new WoodType("aurora", AuroraBlockSetTypes.AURORA));

    public static WoodType register(WoodType woodType) {
        TYPES.put(woodType.name(), woodType);
        return woodType;
    }

    public static Stream<WoodType> values() {
        return TYPES.values().stream();
    }
}
