package gloridifice.watersource.registry;

import gloridifice.watersource.WaterSource;
import gloridifice.watersource.common.item.*;
import gloridifice.watersource.common.item.food.ModFoods;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemRegistry {
    public static final DeferredRegister<Item> FLUID_ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS,
                    WaterSource.MODID);

    public final static RegistryObject<Item> FLUID_BOTTLE =
            FLUID_ITEMS.register("fluid_bottle", () ->
            new FluidBottleItem(new Item.Properties().stacksTo(16)));

    public final static RegistryObject<Item> WOODEN_CUP =
            FLUID_ITEMS.register("wooden_cup", () ->
            new WoodenCupItem(new Item.Properties(), 250) {
        @Override
        public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable CompoundTag nbt) {
            return super.initCapabilities(new ItemStack(WOODEN_CUP_DRINK.get()), nbt);
        }
    });

    public final static RegistryObject<Item> HALF_COCONUT =
            FLUID_ITEMS.register("half_coconut", ModNormalItem::new);

    public final static RegistryObject<Item> WOODEN_CUP_DRINK =
            FLUID_ITEMS.register("wooden_cup_drink", () ->
            new WoodenCupItem(new Item.Properties().stacksTo(1).tab(CreativeModeTabRegistry.WATER_SOURCE_TAB), 250));
    public final static RegistryObject<Item> LEATHER_WATER_BAG =
            FLUID_ITEMS.register("leather_water_bag", () ->
            new LeatherWaterBagItem(new Item.Properties().stacksTo(1).setNoRepair().tab(CreativeModeTabRegistry.WATER_SOURCE_TAB), 1500));
    public final static RegistryObject<Item> IRON_BOTTLE =
            FLUID_ITEMS.register("iron_bottle", () ->
            new IronBottleItem(new Item.Properties().stacksTo(1)
                            .setNoRepair().tab(CreativeModeTabRegistry.WATER_SOURCE_TAB), 1500));

    //FOOD
    public final static RegistryObject<Item> COCONUT_MILK_BOTTLE =
            FLUID_ITEMS.register("coconut_milk_bottle", () ->
            new CoconutMilkBottleItem(ModFoods.COCONUT_MILK));
    public final static RegistryObject<Item> COCONUT_PIECE =
            FLUID_ITEMS.register("coconut_piece", () ->
            new ModFoodItem( ModFoods.COCONUT_PIECES));
    public final static RegistryObject<Item> COCONUT_CHICKEN =
            FLUID_ITEMS.register("coconut_chicken", () ->
            new ModFoodItem(ModFoods.COCONUT_CHICKEN));
    //Fluids
    public static RegistryObject<Item> itemPurifiedWaterBucket = FLUID_ITEMS.register("purified_water_bucket", () -> {
        return new BucketItem(FluidRegistry.PURIFIED_WATER, new Item.Properties().tab(CreativeModeTabRegistry.WATER_SOURCE_TAB));
    });
    public static RegistryObject<Item> itemSoulWaterBucket = FLUID_ITEMS.register("soul_water_bucket", () -> {
        return new BucketItem(FluidRegistry.SOUL_WATER, new Item.Properties().tab(CreativeModeTabRegistry.WATER_SOURCE_TAB));
    });
    public static RegistryObject<Item> ITEM_COCONUT_MILK_BUCKET = FLUID_ITEMS.register("coconut_juice_bucket", () -> {
        return new BucketItem(FluidRegistry.COCONUT_JUICE, new Item.Properties().tab(CreativeModeTabRegistry.WATER_SOURCE_TAB));
    });
}
