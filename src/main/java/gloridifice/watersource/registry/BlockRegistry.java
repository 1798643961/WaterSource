package gloridifice.watersource.registry;

import gloridifice.watersource.WaterSource;
import gloridifice.watersource.common.block.*;
import gloridifice.watersource.common.block.grower.CoconutTreeGrower;
import gloridifice.watersource.common.item.EverlastingStrainerBlockItem;
import gloridifice.watersource.common.item.ModNormalBlockItem;
import gloridifice.watersource.common.item.StrainerBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;


public class BlockRegistry {
    public static final DeferredRegister<Block> FLUID_BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS,
                    WaterSource.MODID);
    public final static RegistryObject<Block> WOODEN_WATER_FILTER =
            registerDefault("wooden_water_filter",
                    () -> new WaterFilterBlock(Block.Properties.of(Material.WOOD,
                            MaterialColor.COLOR_BROWN).sound(SoundType.WOOD)
                            .strength(1.0F, 3.0f).noOcclusion()));
    public final static RegistryObject<Block> IRON_WATER_FILTER =
            registerDefault("iron_water_filter",
                    () -> new RedstoneWaterFilterBlock(Block.Properties.of(Material.METAL,
                            MaterialColor.COLOR_GRAY).sound(SoundType.STONE)
                            .strength(2.2F, 3.0f)
                            .noOcclusion().requiresCorrectToolForDrops()));

    public final static RegistryObject<Block> PALM_TREE_LOG =
            registerDefault("palm_tree_log", () -> log(MaterialColor.WOOD, MaterialColor.WOOD));

    public final static RegistryObject<Block> PALM_TREE_HEAD =
            registerDefault("palm_tree_head",
                    () -> new PalmTreeHeadBlock(Block.Properties.of(Material.WOOD,
                            MaterialColor.COLOR_BROWN).sound(SoundType.WOOD)
                            .strength(2.0f, 3.0f)
                            .randomTicks()));

    public final static RegistryObject<Block> PALM_TREE_LEAF =
            registerDefault("palm_tree_leaf",
                    () -> new LeavesBlock(Block.Properties.of(Material.LEAVES)
                    .strength(0.2F).randomTicks()
                    .sound(SoundType.CROP)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()));//todo

    public final static RegistryObject<Block> BLOCK_NATURAL_COCONUT =
            registerDefault("natural_coconut", () ->
            new NaturalCoconutBlock(Block.Properties.of(Material.WOOD)
                    .strength(0.4F)
                    .sound(SoundType.WOOD)
                    .randomTicks().noOcclusion()));

    public final static RegistryObject<Block> BLOCK_COCONUT =
            registerDefault("coconut", () ->
            new CoconutBlock(Block.Properties.of(Material.WOOD,
                    MaterialColor.COLOR_BROWN)
                    .strength(0.4F)
                    .sound(SoundType.WOOD)
                    .randomTicks().noOcclusion()));
    public final static RegistryObject<Block> BLOCK_COCONUT_SAPLING =
            registerDefault("palm_tree_sapling", () ->
            new PalmTreeSaplingBlock(new CoconutTreeGrower(), Block.Properties.of(Material.PLANT).sound(SoundType.CROP).randomTicks().noCollission()));

    //strainers
    public final static RegistryObject<Block> PRIMITIVE_STRAINER =
            registerDefault("primitive_strainer", () ->
            new StrainerBlock(Block.Properties.of(Material.WOOL)
                    .sound(SoundType.WOOL).noOcclusion()
                    .strength(0.6f)));

    public final static RegistryObject<Block> PAPER_STRAINER =
            registerDefault("paper_strainer", () ->
            new StrainerBlock(Block.Properties.of(Material.WOOL)
                    .sound(SoundType.WOOL).noOcclusion().strength(0.4f)));

    public final static RegistryObject<Block> DIRTY_STRAINER =
            registerDefault("dirty_strainer", () ->
            new StrainerBlock(Block.Properties.of(Material.WOOL)
                    .sound(SoundType.WOOL)
                    .noOcclusion().strength(0.6f)));

    public final static RegistryObject<Block> PAPER_SOUL_STRAINER =
            registerDefault("paper_soul_strainer", () ->
            new StrainerBlock(Block.Properties.of(Material.WOOL)
                            .sound(SoundType.WOOL)
                            .noOcclusion().strength(0.4f)));
    public final static RegistryObject<Block> SOUL_STRAINER =
            registerDefault("soul_strainer", () ->
            new StrainerBlock(Block.Properties.of(Material.WOOL).sound(SoundType.WOOL)
                            .noOcclusion().strength(0.6f)));

    public final static RegistryObject<Block> EVERLASTING_STRAINER =
            registerDefault("everlasting_strainer", () ->
            new StrainerBlock(Block.Properties.of(Material.WOOL
            ).sound(SoundType.WOOL).noOcclusion().strength(0.6f)));

    public final static RegistryObject<Block> EVERLASTING_SOUL_STRAINER =
            registerDefault("everlasting_soul_strainer", () ->
            new StrainerBlock(Block.Properties.of(Material.WOOL)
                    .sound(SoundType.WOOL).noOcclusion()
                    .strength(0.6f)));

    public static final RegistryObject<Block> PALM_TREE_PLANKS =
            registerDefault("palm_tree_planks", () ->
            new Block(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> PALM_TREE_DOOR =
            registerDefault("palm_tree_door", () ->
            new PalmTreeDoorBlock(Block.Properties.of(Material.WOOD,
                    MaterialColor.WOOD)
                    .strength(3.0F)
                    .sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> PALM_TREE_TRAPDOOR =
            registerDefault("palm_tree_trapdoor", () ->
            new PalmTreeTrapdoor(Block.
                    Properties.of(Material.WOOD, MaterialColor.WOOD)
                    .strength(3.0F)
                    .sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> PALM_TREE_STAIRS =
            registerDefault("palm_tree_stairs", () ->
            new StairBlock(() -> PALM_TREE_PLANKS.get().getStateDefinition().any(),
                    Block.Properties.copy(PALM_TREE_PLANKS.get())));

    public static final RegistryObject<Block> STRIPPED_PALM_TREE_LOG =
            registerDefault("stripped_palm_tree_log", () ->
            log(MaterialColor.WOOD, MaterialColor.WOOD));

    public static final RegistryObject<Block> PALM_TREE_BUTTON =
            registerDefault("palm_tree_button", () ->
            new WoodButtonBlock(Block.Properties.of(Material.WOOD)
                    .strength(0.5F)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> PALM_TREE_SLAB =
            registerDefault("palm_tree_slab", () ->
            new SlabBlock(Block.Properties.copy(PALM_TREE_PLANKS.get())));
    public static final RegistryObject<Block> PALM_TREE_FENCE =
            registerDefault("palm_tree_fence", () ->
            new FenceBlock(Block.Properties.copy(PALM_TREE_PLANKS.get())));

    public static final RegistryObject<Block> PALM_TREE_FENCE_GATE =
            registerDefault("palm_tree_fence_gate", () ->
            new FenceGateBlock(Block.Properties.copy(PALM_TREE_PLANKS.get())));

    public static final RegistryObject<Block> PALM_TREE_PRESSURE_PLATE =
            registerDefault("palm_tree_pressure_plate", () ->
            new ModPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    Block.Properties.copy(PALM_TREE_PLANKS.get())));

    public static final RegistryObject<Block> STONE_RAIN_COLLECTOR =
            registerDefault("stone_rain_collector", () ->
            new RainCollectorBlock(Block.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0f)
                    .noOcclusion()));

    public static final RegistryObject<Block> BLOCK_WATER_DISPENSER =
            registerDefault("water_dispenser", () ->
            new WaterDispenserBlock(Block.Properties
                    .of(Material.METAL).noOcclusion()));//todo fill properties

    //Fluid
    public static RegistryObject<LiquidBlock> BLOCK_PURIFIED_WATER_FLUID =
            FLUID_BLOCKS.register("purified_water_fluid", () -> {
        return new LiquidBlock(FluidRegistry.PURIFIED_WATER,
                Block.Properties.of(Material.WATER)
                        .noCollission().strength(100.0F).noDrops());
    });
    public static final RegistryObject<LiquidBlock> BLOCK_SOUL_WATER_FLUID =
            FLUID_BLOCKS.register("soul_water_fluid", () ->
                    new LiquidBlock(FluidRegistry.SOUL_WATER,
                            Block.Properties.of(Material.WATER)
                                    .noCollission()
                                    .strength(100.0F).noDrops()));
    public static final RegistryObject<LiquidBlock> BLOCK_COCONUT_JUICE_FLUID =
            FLUID_BLOCKS.register("coconut_juice_fluid", () ->
       new LiquidBlock(FluidRegistry.COCONUT_JUICE, Block.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops()));

    //摘自原版
    private static RotatedPillarBlock log(MaterialColor p_50789_, MaterialColor p_50790_) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, (p_152624_) -> {
            return p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? p_50789_ : p_50790_;
        }).strength(2.0F).sound(SoundType.WOOD));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory) {
        return register(BlockRegistry.FLUID_BLOCKS, ItemRegistry.FLUID_ITEMS, name, blockSupplier, blockItemFactory);
    }

    public static <T extends Block> RegistryObject<T> register(DeferredRegister<Block> blocks, DeferredRegister<Item> items, String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory) {
        final String actualName = name.toLowerCase(Locale.ROOT);
        final RegistryObject<T> block = blocks.register(actualName, blockSupplier);
        if (blockItemFactory != null) {
            items.register(actualName, () -> blockItemFactory.apply(block.get()));
        }
        return block;
    }

    private static <T extends Block> RegistryObject<T> registerDefault(String name, Supplier<T> blockSupplier) {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties().tab(CreativeModeTabRegistry.WATER_SOURCE_TAB)));
    }
}

