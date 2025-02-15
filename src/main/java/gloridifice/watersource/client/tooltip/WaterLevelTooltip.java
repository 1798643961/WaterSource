package gloridifice.watersource.client.tooltip;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Either;
import gloridifice.watersource.WaterSource;
import gloridifice.watersource.client.hud.WaterLevelHUD;
import gloridifice.watersource.common.recipe.ModRecipeManager;
import gloridifice.watersource.common.recipe.ThirstRecipe;
import gloridifice.watersource.common.recipe.WaterLevelAndEffectRecipe;
import gloridifice.watersource.registry.ConfigRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = WaterSource.MODID)
public class WaterLevelTooltip {


    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onRenderTooltipEvent(RenderTooltipEvent.GatherComponents event) {
        int x = 1;
        if (ConfigRegistry.OPEN_FOOD_WATER_LEVEL.get()) {
            Minecraft mc = Minecraft.getInstance();
            ItemStack stack= event.getItemStack();
            if (mc.level != null){
                WaterLevelAndEffectRecipe recipe = ModRecipeManager.getWERecipeFromItem(mc.level, stack);
                TagKey<Item> dItemTag = ItemTags.create(new ResourceLocation("watersource:drinkable_containers"));
                if (recipe != null && (event.getItemStack().is(dItemTag) || !FluidUtil.getFluidHandler(stack).isPresent())) {
                    int max = Math.max(recipe.getWaterLevel(), recipe.getWaterSaturationLevel());
                    int width = (int) Math.ceil((double) max / 2) * 9 + 1;
                    //if (max > 8) width = 28;
                    event.getTooltipElements().add(x, Either.right(new WaterLevelComponent(stack, width, 10)));
                }
            }
        }
    }

    public static class WaterLevelComponent implements ClientTooltipComponent, TooltipComponent {
        protected final ItemStack itemStack;
        protected final int width;
        protected final int height;
        private static int tick = 0;

        public WaterLevelComponent(ItemStack itemStack, int width, int height) {
            this.itemStack = itemStack;
            this.width = width;
            this.height = height;
        }

        @Override
        public int getHeight() {
            return height;
        }

        @Override
        public int getWidth(Font font) {
            return width;
        }

        @Override
        public void renderImage(Font font, int tooltipX, int tooltipY, PoseStack pose, ItemRenderer itemRenderer, int something) {
            tick++;
            tick %= 10000;
            Minecraft mc = Minecraft.getInstance();
            WaterLevelAndEffectRecipe wlRecipe = ModRecipeManager.getWERecipeFromItem(mc.level, itemStack);
            ThirstRecipe tRecipe = ModRecipeManager.getThirstRecipeFromItem(mc.level, itemStack);
            if (wlRecipe != null) {
                int max = Math.max(wlRecipe.getWaterLevel(), wlRecipe.getWaterSaturationLevel());
                //if (max <= 8) {
                    pose.pushPose();
                    RenderSystem.setShader(GameRenderer::getPositionTexShader);
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, WaterLevelHUD.OVERLAY_BAR);
                    RenderSystem.enableBlend();
                    int maxBalls = (int) Math.ceil((double) max / 2);
                    int wlBalls = (int) Math.ceil((double) wlRecipe.getWaterLevel() / 2);
                    //render balls' outlines
                    for (int i = 0; i < maxBalls; i++) {
                        int x = tooltipX + i * 9 - 1;
                        int y = tooltipY - 1;
                        int u = 36;
                        int v = 0;
                        if (tRecipe != null) {
                            u += 18;
                        }
                        GuiComponent.blit(pose, x, y, u, v, 9, 9, 256, 256);
                    }
                    //render water level balls
                    for (int i = 0; i < wlBalls; i++) {
                        int x = tooltipX + i * 9 - 1;
                        int y = tooltipY - 1;
                        int u = 0;
                        int v = 18;
                        if (i == wlBalls - 1 && wlRecipe.getWaterLevel() % 2 != 0) {
                            u += 9;
                        }
                        if (tRecipe != null) {
                            u += 18;
                        }
                        GuiComponent.blit(pose, x, y, u, v, 9, 9, 256, 256);
                    }
                    //render water saturation level balls
                    if (tRecipe == null) {
                        for (int i = 0; i < wlRecipe.getWaterSaturationLevel(); i++) {
                            int x = tooltipX + (int) ((double) i / 2) * 9 - 1;
                            int y = tooltipY - 2;
                            int u = 0;
                            int v = 9;
                            if ((i + 1) % 2 == 0) {
                                u += 9;
                                y += 2;
                            }
                            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, (float) Math.abs(Math.sin(tick / 60d)));
                            GuiComponent.blit(pose, x, y, u, v, 9, 9, 256, 256);
                            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                        }
                    }
                    pose.popPose();
                //}
            //todo
                 /*else {
                    int x = tooltipX - 1;
                    int y = tooltipY;
                    int u = 0;
                    int v = 18;
                    if (tRecipe != null) {
                        u += 18;
                    }
                    pose.pushPose();
                    RenderSystem.setShader(GameRenderer::getRendertypeTextShader);
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.enableBlend();
                    font.draw(pose, "*" + (float) wlRecipe.getWaterLevel() / 2, tooltipX + 9, y, 0xFFFFF);
                    font.draw(pose, "*" + (float) wlRecipe.getWaterSaturationLevel() / 2, tooltipX + 30, y, 0xFFFFF);
                    pose.popPose();

                    pose.pushPose();
                    RenderSystem.setShader(GameRenderer::getPositionTexShader);
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, WaterLevelHUD.OVERLAY_BAR);
                    RenderSystem.enableBlend();

                    GuiComponent.blit(pose, x, y, u, v, 9, 9, 256, 256);
                    GuiComponent.blit(pose, x, y, u, 9, 20, 9, 256, 256);
                    GuiComponent.blit(pose, x, y, u + 9, 9, 20, 9, 256, 256);
                    pose.popPose();

                }*/
            }
        }
    }
}
