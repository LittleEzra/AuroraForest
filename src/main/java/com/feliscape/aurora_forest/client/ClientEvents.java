package com.feliscape.aurora_forest.client;

import com.feliscape.aurora_forest.Aurora;
import com.feliscape.aurora_forest.registry.AuroraBlocks;
import com.feliscape.aurora_forest.util.ColorGradient;
import com.feliscape.aurora_forest.util.ColorUtil;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

public class ClientEvents {
    @EventBusSubscriber(modid = Aurora.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }

        @SubscribeEvent
        public static void onRegisterClientReloadListeners(RegisterClientReloadListenersEvent event)
        {
            new AuroraClient(event);
        }

        @SubscribeEvent
        public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event){
            event.register((state, tintGetter, blockPos, tintIndex) -> {
                if (blockPos == null) return ColorUtil.getIntColor("#eb6594");

                int absX = Math.abs(blockPos.getX());
                int absY = Math.abs(blockPos.getY());
                int absZ = Math.abs(blockPos.getZ());

                float f = (float)absX / 16.0F;
                f += Mth.sin((float)absY / 4.0F) * 0.5F;
                f += Mth.sin((float)absZ / 4.0F) * 0.5F;

                return ColorUtil.getIntFromVec3f(AuroraClient.getInstance().getAuroraLeavesGradient()
                        .sampleColor(f % 1F));
            }, AuroraBlocks.AURORA_LEAVES.get());
        }
        @SubscribeEvent
        public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event){
            event.register((itemStack, tintIndex) -> {
                return ColorUtil.getIntColor("#eb6594");
            }, AuroraBlocks.AURORA_LEAVES.get());
        }
    }
}
