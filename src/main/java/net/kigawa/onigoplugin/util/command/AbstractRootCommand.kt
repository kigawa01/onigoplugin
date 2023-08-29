package net.kigawa.onigoplugin.util.command

abstract class AbstractRootCommand(commandName: String) : AbstractCommand(commandName) {
  init {
    @Suppress("LeakingThis")
    register()
  }
}