package com.feliscape.aurora_forest.util;

import com.feliscape.aurora_forest.Aurora;
import com.google.common.collect.Lists;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.List;

public class ColorGradient {
    public static class Node {
        protected final Vector3f color;
        private final float position;

        public Node(Vector3f color, float position){
            if (position < 0F || position > 1F){
                Aurora.LOGGER.warn("Position {} out of bounds [0F,1F]! Value will be clamped.", position);
                position = Math.clamp(position, 0F, 1F);
            }

            this.color = color;
            this.position = position;
        }
    }

    private final List<Node> nodes;

    private ColorGradient(List<Node> nodes){
        this.nodes = nodes;
    }

    public static ColorGradient create(Node... nodes){
        List<Node> nodeList = Lists.newArrayList(nodes);
        nodeList.sort((a,b) -> Float.compare(a.position, b.position)); // Sort the nodes in order of first to last
        return new ColorGradient(nodeList);
    }

    public Vector3f sampleColor(float t){
        int bIndex = 0;
        for (int i = 0; i < nodes.size(); i++){
            if (nodes.get(i).position >= t){
                bIndex = i;
                break;
            }
        }
        if (bIndex == 0){
            return nodes.get(bIndex).color;
        }

        Node a = nodes.get(bIndex - 1);
        Node b = nodes.get(bIndex);

        float scaledT = (t - a.position) * (1.0F / (b.position - a.position));
        return ColorUtil.lerp(a.color, b.color, scaledT);
    }
}
