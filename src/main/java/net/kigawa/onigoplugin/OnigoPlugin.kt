package net.kigawa.onigoplugin

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import net.kigawa.kutil.unitapi.component.InitializedFilterComponent
import net.kigawa.kutil.unitapi.component.container.UnitContainer
import net.kigawa.kutil.unitapi.component.UnitFinderComponent
import net.kigawa.kutil.unitapi.component.UnitLoggerComponent
import net.kigawa.kutil.unitapi.registrar.ClassRegistrar
import net.kigawa.kutil.unitapi.registrar.InstanceRegistrar
import net.kigawa.kutil.unitapi.registrar.ResourceRegistrar
import net.kigawa.onigoplugin.command.OnigoCreate
import net.kigawa.onigoplugin.game.ChangeManager
import net.kigawa.onigoplugin.util.config.ConfigInitializedFilter
import net.kigawa.onigoplugin.util.config.ConfigManager
import net.kigawa.onigoplugin.util.config.ConfigUtil
import net.kigawa.onigoplugin.util.message.Logger
import net.kigawa.onigoplugin.util.plugin.all.message.Messenger
import net.kigawa.onigoplugin.util.plugin.all.player.PlayerGetter
import net.kigawa.onigoplugin.util.plugin.all.player.Teleporter
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameManager
import net.kigawa.onigoplugin.util.plugin.game.onigo.evnt.OnigoListener
import net.kigawa.onigoplugin.util.unit.BukkitFinder
import net.kigawa.onigoplugin.util.unit.ListenerFilter
import net.kigawa.onigoplugin.util.unit.OyuBingoUnitLogger
import net.kigawa.oyucraft.oyubingo.config.Config
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class OnigoPlugin : JavaPlugin(), Logger {
  companion object {
    @JvmStatic
    val container = UnitContainer.create()
  }

  private var debug = false

  @JvmField
  var playerGetter: PlayerGetter? = null

  @JvmField
  var messenger: Messenger? = null

  @JvmField
  var teleport: Teleporter? = null
  private var gameManagers: MutableList<GameManager> = ArrayList()
  private fun onStart() {
    val changeGame = container.getUnit(ChangeManager::class.java)
    //initialize
    addGameManager(changeGame)
    //new Test(this);
    OnigoCreate(this, changeGame)
    OnigoListener(this)
  }

  fun getGameManagers(): List<GameManager> {
    return gameManagers
  }

  private fun addGameManager(gameManager: GameManager) {
    gameManagers.add(gameManager)
  }

  override fun onEnable() {
    CommandAPI.onEnable()

    container.getUnit(InstanceRegistrar::class.java).apply {
      register(this@OnigoPlugin)
      register(logger)
    }
    container.getUnit(ClassRegistrar::class.java).apply {
      register(ConfigUtil::class.java)
      register(ConfigManager::class.java)
    }
    container.getUnit(UnitFinderComponent::class.java).add(BukkitFinder::class.java)
    container.getUnit(UnitLoggerComponent::class.java).add(OyuBingoUnitLogger::class.java)
    container.getUnit(InitializedFilterComponent::class.java).apply {
      add(ConfigInitializedFilter::class.java)
      add(ListenerFilter::class.java)
    }

    container.getUnit(ResourceRegistrar::class.java).register(javaClass)

    Bukkit.getScheduler().runTaskTimerAsynchronously(this, Runnable {
      container.getUnitList(Config::class.java).forEach {
        it.save()
      }
    }, 5 * 60 * 1000, 5 * 60 * 1000)


    saveDefaultConfig()
    val config = getConfig()
    config.addDefault("debug", false)
    config.addDefault("useDB", false)
    config.options().copyDefaults(true)
    saveConfig()
    debug = config.getBoolean("debug")
    playerGetter = PlayerGetter(this)
    messenger = Messenger(this)
    teleport = Teleporter()
    onStart()
  }

  override fun onDisable() {
    for (manager in gameManagers) {
      manager.endAll()
    }
    container.close()
    CommandAPI.onDisable()
  }

  override fun logger(message: String) {
    if (debug) {
      logger.info(message)
    }
  }

  fun logger(message: Int) {
    if (debug) {
      logger.info(message.toString())
    }
  }

  fun logger(message: Boolean) {
    logger(message.toString())
  }

  val pluginClassLoader: ClassLoader
    get() = super.getClassLoader()

  override fun onLoad() {
    CommandAPI.onLoad(CommandAPIBukkitConfig(this))
  }

}
