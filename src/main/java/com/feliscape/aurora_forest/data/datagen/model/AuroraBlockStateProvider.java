package com.feliscape.aurora_forest.data.datagen.model;

import com.feliscape.aurora_forest.Aurora;
import com.feliscape.aurora_forest.content.block.AuroraLogBlock;
import com.feliscape.aurora_forest.registry.AuroraBlockSetTypes;
import com.feliscape.aurora_forest.registry.AuroraBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;

public class AuroraBlockStateProvider extends BlockStateProvider {

    public AuroraBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Aurora.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(AuroraBlocks.AUSTRALITE_BLOCK);
        blockWithItem(AuroraBlocks.BOREALITE_BLOCK);
        leavesBlock(AuroraBlocks.AURORA_LEAVES, "cutout_mipped");

        logBlockWithItem(AuroraBlocks.AURORA_LOG.get());
        logBlockWithItem(AuroraBlocks.STRIPPED_AURORA_LOG.get());
        woodBlockWithItem(AuroraBlocks.AURORA_WOOD.get(), AuroraBlocks.AURORA_LOG.get());
        woodBlockWithItem(AuroraBlocks.STRIPPED_AURORA_WOOD.get(), AuroraBlocks.STRIPPED_AURORA_LOG.get());
        blockWithItem(AuroraBlocks.AURORA_PLANKS);
        stairsBlock(AuroraBlocks.AURORA_STAIRS, AuroraBlocks.AURORA_PLANKS);
        slabBlock(AuroraBlocks.AURORA_SLAB, AuroraBlocks.AURORA_PLANKS);
        fenceBlock(AuroraBlocks.AURORA_FENCE, AuroraBlocks.AURORA_PLANKS);
        fenceGateBlock(AuroraBlocks.AURORA_FENCE_GATE, AuroraBlocks.AURORA_PLANKS);
        buttonBlock(AuroraBlocks.AURORA_BUTTON, AuroraBlocks.AURORA_PLANKS);
        pressurePlateBlock(AuroraBlocks.AURORA_PRESSURE_PLATE, AuroraBlocks.AURORA_PLANKS);
        doorBlock(AuroraBlocks.AURORA_DOOR.get(), Aurora.asResource("block/aurora_door_bottom"), Aurora.asResource("block/aurora_door_top"));
        trapdoorBlock(AuroraBlocks.AURORA_TRAPDOOR, Aurora.asResource("block/aurora_trapdoor"));
    }

    private void makeFurnaceLikeBlock(DeferredBlock<? extends Block> block, Function<BlockState, Boolean> onOffFunc) {
        ResourceLocation baseTexture = blockTexture(block.get());
        ModelFile off = models().orientableWithBottom(name(block.get()),
                extend(baseTexture, "_side"),
                extend(baseTexture, "_front"),
                extend(baseTexture, "_bottom"),
                extend(baseTexture, "_top")
        );
        ModelFile on = models().orientableWithBottom(name(block.get()) + "_on",
                extend(baseTexture, "_side"),
                extend(baseTexture, "_front_on"),
                extend(baseTexture, "_bottom"),
                extend(baseTexture, "_top")
        );
        simpleBlockItem(block.get(), off);

        horizontalBlock(block.get(), state -> onOffFunc.apply(state) ? on : off);
    }

    private void makeArcFurnace(DeferredBlock<? extends Block> block, Function<BlockState, Boolean> onOffFunc) {
        ResourceLocation baseTexture = blockTexture(block.get());
        ModelFile off = models().orientableWithBottom(name(block.get()),
                extend(baseTexture, "_side"),
                extend(baseTexture, "_front"),
                extend(baseTexture, "_bottom"),
                extend(baseTexture, "_top")
        );
        ModelFile on = models().orientableWithBottom(name(block.get()) + "_on",
                extend(baseTexture, "_side"),
                extend(baseTexture, "_front_on"),
                extend(baseTexture, "_bottom"),
                extend(baseTexture, "_top_on")
        );
        simpleBlockItem(block.get(), off);

        horizontalBlock(block.get(), state -> onOffFunc.apply(state) ? on : off);
    }

    public void pillarBlock(RotatedPillarBlock pillarBlock) {
        axisBlock(pillarBlock, blockTexture(pillarBlock), extend(blockTexture(pillarBlock), "_end"));
    }
    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private void blockItem(DeferredBlock<? extends Block> blockRegistryObject){
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(Aurora.MOD_ID +
                ":block/" + key(blockRegistryObject.get()).getPath()));
    }

    public void stairsBlock(DeferredBlock<? extends StairBlock> block, DeferredBlock<? extends Block> baseBlock) {
        stairsBlock(block.get(), blockTexture(baseBlock.get()));
        blockItem(block);
    }

    public void slabBlock(DeferredBlock<? extends SlabBlock> block, DeferredBlock<? extends Block> baseBlock) {
        slabBlock(block.get(), blockTexture(baseBlock.get()), blockTexture(baseBlock.get()));
        blockItem(block);
    }

    public void buttonBlock(DeferredBlock<? extends ButtonBlock> block, DeferredBlock<? extends Block> baseBlock) {
        ResourceLocation texture = blockTexture(baseBlock.get());
        ModelFile button = models().button(name(block.get()), texture);
        ModelFile buttonInventory = models().buttonInventory(name(block.get()), texture);
        ModelFile buttonPressed = models().buttonPressed(name(block.get()) + "_pressed", texture);
        buttonBlock(block.get(), button, buttonPressed);
        simpleBlockItem(block.get(), buttonInventory);
    }

    public void pressurePlateBlock(DeferredBlock<? extends PressurePlateBlock> block, DeferredBlock<? extends Block> baseBlock) {
        ResourceLocation texture = blockTexture(baseBlock.get());
        ModelFile pressurePlate = models().pressurePlate(name(block.get()), texture);
        ModelFile pressurePlateDown = models().pressurePlateDown(name(block.get()) + "_down", texture);
        pressurePlateBlock(block.get(), pressurePlate, pressurePlateDown);
        simpleBlockItem(block.get(), pressurePlate);
    }

    public void trapdoorBlock(DeferredBlock<? extends TrapDoorBlock> block, ResourceLocation texture) {
        String baseName = key(block.get()).toString();

        ModelFile bottom = models().trapdoorOrientableBottom(baseName + "_bottom", texture);
        ModelFile top = models().trapdoorOrientableTop(baseName + "_top", texture);
        ModelFile open = models().trapdoorOrientableOpen(baseName + "_open", texture);
        trapdoorBlock(block.get(), bottom, top, open, true);
        simpleBlockItem(block.get(), bottom);
    }

    public void wallBlockWithItem(DeferredBlock<WallBlock> block, DeferredBlock<? extends Block> baseBlock) {
        super.wallBlock(block.get(), blockTexture(baseBlock.get()));

        ModelFile inventoryModel = models().withExistingParent(block.getId().getPath() + "_inventory", "minecraft:block/wall_inventory")
                .texture("wall", blockTexture(baseBlock.get()));

        simpleBlockItem(block.get(), inventoryModel);
    }
    public void wallBlockWithItem(DeferredBlock<WallBlock> block, ResourceLocation texture) {
        super.wallBlock(block.get(), texture);

        ModelFile inventoryModel = models().withExistingParent(block.getId().getPath() + "_inventory", "minecraft:block/wall_inventory")
                .texture("wall", texture);

        simpleBlockItem(block.get(), inventoryModel);
    }

    public void fenceBlock(DeferredBlock<? extends FenceBlock> block, DeferredBlock<? extends Block> baseBlock) {
        super.fenceBlock(block.get(), blockTexture(baseBlock.get()));

        ModelFile inventoryModel = models().withExistingParent(block.getId().getPath() + "_inventory", "minecraft:block/fence_inventory")
                .texture("texture", blockTexture(baseBlock.get()));

        simpleBlockItem(block.get(), inventoryModel);
    }
    public void fenceGateBlock(DeferredBlock<? extends FenceGateBlock> block, DeferredBlock<? extends Block> baseBlock) {
        String name = key(block.get()).toString();
        ResourceLocation texture = blockTexture(baseBlock.get());

        ModelFile gate = models().fenceGate(name, texture);
        ModelFile gateOpen = models().fenceGateOpen(name + "_open", texture);
        ModelFile gateWall = models().fenceGateWall(name + "_wall", texture);
        ModelFile gateWallOpen = models().fenceGateWallOpen(name + "_wall_open", texture);
        fenceGateBlock(block.get(), gate, gateOpen, gateWall, gateWallOpen);
        simpleBlockItem(block.get(), gate);
    }

    private void blockWithItem(DeferredBlock<? extends Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
    private void blockWithItemAndVariants(DeferredBlock<? extends Block> blockRegistryObject, ConfiguredModel mainModel, ConfiguredModel... variants){
        var builder = getVariantBuilder(blockRegistryObject.get()).partialState().setModels(mainModel);
        for (ConfiguredModel file : variants){
            builder.partialState().addModels(file);
        }
        simpleBlockItem(blockRegistryObject.get(), mainModel.model);
    }
    private void blockWithItemAndRenderType(DeferredBlock<? extends Block> blockRegistryObject, String renderType){
        simpleBlockWithItem(blockRegistryObject.get(), models().cubeAll(name(blockRegistryObject.get()), blockTexture(blockRegistryObject.get())).renderType(renderType));
    }

    private void leavesBlock(DeferredBlock<? extends Block> blockRegistryObject, String renderType){
        ModelFile model = models().withExistingParent(blockRegistryObject.getId().getPath(), "minecraft:block/leaves")
                .texture("all", blockTexture(blockRegistryObject.get())).renderType(renderType);
        getVariantBuilder(blockRegistryObject.get())
                .partialState().setModels( new ConfiguredModel(model));
        simpleBlockItem(blockRegistryObject.get(), model);
    }

    public void logBlockWithItem(RotatedPillarBlock block) {
        axisBlockWithItem(block, blockTexture(block), extend(blockTexture(block), "_top"));
    }
    public void woodBlockWithItem(RotatedPillarBlock block, RotatedPillarBlock log) {
        axisBlockWithItem(block, blockTexture(log), blockTexture(log));
    }

    public void axisBlockWithItem(RotatedPillarBlock block) {
        axisBlockWithItem(block, extend(blockTexture(block), "_side"),
                extend(blockTexture(block), "_end"));
    }

    public void axisBlockWithItem(RotatedPillarBlock block, ResourceLocation side, ResourceLocation end) {
        axisBlockWithItem(block,
                models().cubeColumn(name(block), side, end),
                models().cubeColumnHorizontal(name(block) + "_horizontal", side, end));
    }

    public void axisBlockWithItem(RotatedPillarBlock block, ModelFile vertical, ModelFile horizontal) {
        getVariantBuilder(block)
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(vertical).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(horizontal).rotationX(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(horizontal).rotationX(90).rotationY(90).addModel();
        simpleBlockItem(block, vertical);
    }

    public void crossBlockWithRenderType(Block block, String renderType) {
        getVariantBuilder(block).partialState().setModels(new ConfiguredModel(models().cross(name(block), blockTexture(block)).renderType(renderType)));
    }

    public void horizontalBlockWithItem(DeferredBlock<? extends Block> block, ModelFile model){
        horizontalBlock(block.get(), model);
        simpleBlockItem(block.get(), model);
    }
    public void cubeBottomTop(Block block){
        simpleBlockWithItem(block, models().cubeBottomTop(name(block),
                extend(blockTexture(block), "_side"),
                extend(blockTexture(block), "_bottom"),
                extend(blockTexture(block), "_top")));
    }

    // ::models() Extensions //

    protected ModelFile cubeMirroredAll(String name, ResourceLocation texture){
        return models().withExistingParent(name, "minecraft:block/cube_mirrored_all")
                .texture("all", texture);
    }

    protected ConfiguredModel configuredWithWeight(ModelFile file, int weight){
        return new ConfiguredModel(file, 0, 0, false, weight);
    }

    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
    private String name(Block block) {
        return key(block).getPath();
    }
    private ResourceLocation extend(ResourceLocation rl, String suffix) {
        return ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), rl.getPath() + suffix);
    }
}
