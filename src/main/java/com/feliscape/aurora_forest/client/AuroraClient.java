package com.feliscape.aurora_forest.client;

import com.feliscape.aurora_forest.Aurora;
import com.feliscape.aurora_forest.util.ColorGradient;
import com.feliscape.aurora_forest.util.ColorUtil;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

public class AuroraClient {
    private static AuroraClient instance;
    private final ColorGradient auroraLeavesGradient = ColorGradient.create(
            new ColorGradient.Node(ColorUtil.getVec3Color("#eb6594"), 0.0F),
            new ColorGradient.Node(ColorUtil.getVec3Color("#7672c9"), 0.25F),
            new ColorGradient.Node(ColorUtil.getVec3Color("#5af966"), 0.5F),
            new ColorGradient.Node(ColorUtil.getVec3Color("#7672c9"), 0.75F),
            new ColorGradient.Node(ColorUtil.getVec3Color("#eb6594"), 1.0F)
    );

    public AuroraClient(RegisterClientReloadListenersEvent event){
        instance = this;
        Aurora.LOGGER.info("AuroraClient instantiated");
    }

    public static AuroraClient getInstance() {
        return instance;
    }

    public ColorGradient getAuroraLeavesGradient(){
        return auroraLeavesGradient;
    }
}
