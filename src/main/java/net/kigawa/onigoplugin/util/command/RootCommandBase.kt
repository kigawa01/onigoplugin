package net.kigawa.onigoplugin.util.command

import dev.jorel.commandapi.CommandAPICommand

abstract class RootCommandBase(commandAPICommand: CommandAPICommand) : AbstractCommand(commandAPICommand) {
  init {
    this.commandAPICommand.register()
  }
}