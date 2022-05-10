package com.glodblock.github;

import com.glodblock.github.inventory.InventoryHandler;
import com.glodblock.github.loader.ChannelLoader;
import com.glodblock.github.loader.ListenerLoader;
import com.glodblock.github.loader.RenderLoader;
import com.glodblock.github.nei.recipes.DefaultExtractorLoader;
import com.glodblock.github.proxy.CommonProxy;
import com.glodblock.github.util.ModAndClassUtil;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.util.ResourceLocation;

@Mod(modid = FluidCraft.MODID, version = FluidCraft.VERSION)
public class FluidCraft {

    public static final String MODID = "GRADLETOKEN_MODID";
    public static final String VERSION = "GRADLETOKEN_VERSION";
    public static final String MODNAME = "GRADLETOKEN_MODNAME";

    @Mod.Instance(MODID)
    public static FluidCraft INSTANCE;

    @SidedProxy(clientSide = "com.glodblock.github.proxy.ClientProxy", serverSide = "com.glodblock.github.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        (new ChannelLoader()).run();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        (new RenderLoader()).run();
        (new ListenerLoader()).run();
        ModAndClassUtil.init();
        proxy.init(event);
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(FluidCraft.INSTANCE, new InventoryHandler());
        (new DefaultExtractorLoader()).run();
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void onLoadComplete(FMLLoadCompleteEvent event) {

    }

    public static ResourceLocation resource(String path) {
        return new ResourceLocation(MODID, path);
    }

}