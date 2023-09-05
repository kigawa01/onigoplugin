package net.kigawa.onigoplugin.util.command

abstract class RootCommandBase(commandName: String) : AbstractCommand(commandName) {
  init {
    @Suppress("LeakingThis")
    register()
  }
}