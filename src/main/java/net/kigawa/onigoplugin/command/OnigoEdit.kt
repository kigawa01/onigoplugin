package net.kigawa.onigoplugin.command

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.StringArgument
import net.kigawa.kutil.unitapi.annotation.Kunit
import net.kigawa.onigoplugin.util.command.AbstractCommand
import net.kigawa.onigoplugin.util.command.SubCommand
import net.kigawa.onigoplugin.util.command.argument.ChoiceArgument
import net.kigawa.onigoplugin.util.command.argument.GameArgument

@Kunit
class OnigoEdit : AbstractCommand("edit") {

  init {
    withPermission(CommandPermission.OP)
    withArguments(GameArgument())
  }

  @SubCommand
  fun waitRoom(): CommandAPICommand = CommandAPICommand("wait-room")
    .withPermission(CommandPermission.OP)
    .withArguments(ChoiceArgument("loc"))
}