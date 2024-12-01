package com.feliscape.aurora_forest.registry;

import com.feliscape.aurora_forest.Aurora;
import com.feliscape.aurora_forest.content.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class AuroraBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Aurora.MOD_ID);

    public static DeferredBlock<CrystalBlock> AUSTRALITE_BLOCK = registerBlock("australite_block",
            b -> new CrystalBlock(b.lightLevel(state -> 14)));
    public static DeferredBlock<CrystalBlock> BOREALITE_BLOCK = registerBlock("borealite_block",
            b -> new CrystalBlock(b.lightLevel(state -> 14)));

    public static DeferredBlock<LeavesBlock> AURORA_LEAVES = registerBlock("aurora_leaves",
            b -> new LeavesBlock(leavesProperties(b.lightLevel(state -> 7), SoundType.GRASS)));

    public static final DeferredBlock<AuroraLogBlock> AURORA_LOG = registerBlock("aurora_log",
            b -> new AuroraLogBlock(logProperties(b, MapColor.SNOW, MapColor.STONE, SoundType.WOOD)));
    public static final DeferredBlock<AuroraLogBlock> STRIPPED_AURORA_LOG = registerBlock("stripped_aurora_log",
            b -> new AuroraLogBlock(logProperties(b, MapColor.SNOW, MapColor.SNOW, SoundType.WOOD)));
    public static final DeferredBlock<AuroraLogBlock> AURORA_WOOD = registerBlock("aurora_wood",
            b -> new AuroraLogBlock(logProperties(b, MapColor.STONE, MapColor.STONE, SoundType.WOOD)));
    public static final DeferredBlock<AuroraLogBlock> STRIPPED_AURORA_WOOD = registerBlock("stripped_aurora_wood",
            b -> new AuroraLogBlock(logProperties(b, MapColor.SNOW, MapColor.SNOW, SoundType.WOOD)));

    public static final DeferredBlock<FlammableBlock> AURORA_PLANKS = registerBlock("aurora_planks",
            b -> new FlammableBlock(b.mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<FlammableStairBlock> AURORA_STAIRS = registerBlock("aurora_stairs",
            b -> flammableStair(b.mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD),
                    AURORA_PLANKS.get()));
    public static final DeferredBlock<FlammableSlabBlock> AURORA_SLAB = registerBlock("aurora_slab",
            b -> new FlammableSlabBlock(b.mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
            ));
    public static final DeferredBlock<FlammableFenceBlock> AURORA_FENCE = registerBlock("aurora_fence",
            b -> new FlammableFenceBlock(b.mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
            ));
    public static final DeferredBlock<FlammableFenceGateBlock> AURORA_FENCE_GATE = registerBlock("aurora_fence_gate",
            b -> new FlammableFenceGateBlock(AuroraWoodTypes.AURORA, b.mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<ButtonBlock> AURORA_BUTTON = registerBlock("aurora_button",
            b -> new ButtonBlock(AuroraBlockSetTypes.AURORA, 30, buttonProperties(b)));
    public static final DeferredBlock<PressurePlateBlock> AURORA_PRESSURE_PLATE = registerBlock("aurora_pressure_plate",
            b -> new PressurePlateBlock(AuroraBlockSetTypes.AURORA, b.mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .forceSolidOn()
                    .strength(0.5F)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<DoorBlock> AURORA_DOOR = registerBlock("aurora_door",
            b -> new DoorBlock(AuroraBlockSetTypes.AURORA, b
                    .mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F)
                    .noOcclusion()
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<TrapDoorBlock> AURORA_TRAPDOOR = registerBlock("aurora_trapdoor",
            b -> new TrapDoorBlock(AuroraBlockSetTypes.AURORA, b
                    .mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F)
                    .noOcclusion()
                    .isValidSpawn(Blocks::never)
                    .ignitedByLava()
            ));

    private static BlockBehaviour.Properties leavesProperties(BlockBehaviour.Properties properties, SoundType sound) {
        return properties
                .mapColor(MapColor.PLANT)
                .strength(0.2F)
                .randomTicks()
                .sound(sound)
                .noOcclusion()
                .isValidSpawn(Blocks::ocelotOrParrot)
                .isSuffocating(AuroraBlocks::never)
                .isViewBlocking(AuroraBlocks::never)
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY)
                .isRedstoneConductor(AuroraBlocks::never);
    }

    private static BlockBehaviour.Properties logProperties(BlockBehaviour.Properties properties, MapColor topColor, MapColor sideColor, SoundType soundType) {
        return properties
                .mapColor(blockState -> blockState.getValue(AuroraLogBlock.AXIS) == Direction.Axis.Y ? topColor : sideColor)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F)
                .sound(soundType)
                .ignitedByLava();
    }

    private static BlockBehaviour.Properties buttonProperties(BlockBehaviour.Properties properties) {
        return properties.noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);
    }

    private static boolean always(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return true;
    }

    private static boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return false;
    }

    public static Boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entity) {
        return false;
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends T> block)
    {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredItem<Item> registerBlockItem(String name, DeferredBlock<T> block)
    {
        return AuroraItems.ITEMS.registerItem(name, p -> new BlockItem(block.get(), p));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

    private static StairBlock stair(BlockBehaviour.Properties properties, Block baseBlock) {
        return new StairBlock(baseBlock.defaultBlockState(), properties);
    }
    private static FlammableStairBlock flammableStair(BlockBehaviour.Properties properties, Block baseBlock) {
        return new FlammableStairBlock(baseBlock.defaultBlockState(), properties);
    }
}
