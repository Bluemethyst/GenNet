package dev.bluemethyst.mods.gennet

import dev.bluemethyst.mods.gennet.requests.Get
import net.minecraft.client.Minecraft
import net.minecraft.world.item.Items
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.level.ExplosionEvent
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_CONTEXT;
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod("gennet")
class GenNet {

    private val modId = "gennet"
    private val logger: Logger = LogManager.getLogger()
    private val modBus: IEventBus = MOD_CONTEXT.getKEventBus();
    init {
        modBus.addListener(this::commonSetup)
        modBus.addListener(this::clientSetup)
        MinecraftForge.EVENT_BUS.register(this)
    }

    private fun commonSetup(event: FMLCommonSetupEvent) {
        logger.info("Hello from common setup! This is *after* registries are done, so we can do this:")
        logger.info("Look, I found a {}!", Items.DIAMOND)
    }

    private fun clientSetup(event: FMLClientSetupEvent) {
        logger.info("Hey, we're on Minecraft version {}!", Minecraft.getInstance().launchedVersion)
    }

    @SubscribeEvent
    fun kaboom(event: ExplosionEvent.Detonate) {
        logger.info("Kaboom! Something just blew up in {}!", event.level)
        val get = Get()
        val response = get.get("https://jsonplaceholder.typicode.com/posts/1")
        logger.info(response.body?.string())
    }
}