package net.kigawa.onigoplugin

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import net.kigawa.kutil.unitapi.component.InitializedFilterComponent
import net.kigawa.kutil.unitapi.component.UnitContainer
import net.kigawa.kutil.unitapi.component.UnitFinderComponent
import net.kigawa.kutil.unitapi.component.UnitLoggerComponent
import net.kigawa.kutil.unitapi.registrar.ClassRegistrar
import net.kigawa.kutil.unitapi.registrar.InstanceRegistrar
import net.kigawa.kutil.unitapi.registrar.ResourceRegistrar
import net.kigawa.onigoplugin.command.Onigo
import net.kigawa.onigoplugin.command.OnigoCreate
import net.kigawa.onigoplugin.command.Stage
import net.kigawa.onigoplugin.game.ChangeManager
import net.kigawa.oyucraft.oyubingo.config.Config
import net.kigawa.onigoplugin.util.config.ConfigInitializedFilter
import net.kigawa.onigoplugin.util.config.ConfigManager
import net.kigawa.onigoplugin.util.config.ConfigUtil
import net.kigawa.onigoplugin.util.unit.BukkitFinder
import net.kigawa.onigoplugin.util.unit.ListenerFilter
import net.kigawa.oyucraft.oyubingo.unit.OyuBingoUnitLogger
import net.kigawa.util.message.Logger
import net.kigawa.util.plugin.all.message.Messenger
import net.kigawa.util.plugin.all.player.PlayerGetter
import net.kigawa.util.plugin.all.player.Teleporter
import net.kigawa.util.plugin.all.recorder.Recorder
import net.kigawa.util.plugin.game.onigo.GameManager
import net.kigawa.util.plugin.game.onigo.evnt.OnigoListener
import net.kigawa.util.plugin.game.stage.StageManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class OnigoPlugin : JavaPlugin(), Logger {
  var onigo: Onigo? = null
  private lateinit var changeGame: ChangeManager
  private var debug = false

  @JvmField
  var recorder: Recorder? = null

  @JvmField
  var playerGetter: PlayerGetter? = null

  @JvmField
  var stageManager: StageManager? = null

  @JvmField
  var messenger: Messenger? = null

  @JvmField
  var teleport: Teleporter? = null
  private var gameManagers: MutableList<GameManager> = ArrayList()
  private fun onStart() {
    //initialize
    changeGame = ChangeManager(this, "change")
    onigo = Onigo(this, changeGame)
    addGameManager(changeGame)
    //new Test(this);
    OnigoCreate(this, changeGame)
    Stage(this)
    OnigoListener(this)
  }

  fun getGameManagers(): List<GameManager> {
    return gameManagers
  }

  private fun addGameManager(gameManager: GameManager) {
    gameManagers.add(gameManager)
  }

  override fun onEnable() {
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

    CommandAPI.onEnable()

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
    recorder = Recorder(this)
    playerGetter = PlayerGetter(this)
    stageManager = StageManager(this)
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

  private val container = UnitContainer.create()
  val pluginClassLoader: ClassLoader
    get() = super.getClassLoader()

  override fun onLoad() {
    CommandAPI.onLoad(CommandAPIBukkitConfig(this))
  }


}
