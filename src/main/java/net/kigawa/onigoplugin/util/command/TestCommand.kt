package net.kigawa.onigoplugin.util.command

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.executors.CommandArguments
import dev.jorel.commandapi.executors.CommandExecutor
import net.kigawa.kutil.unitapi.annotation.Kunit
import net.kigawa.kutil.unitapi.component.container.UnitContainer
import net.kigawa.onigoplugin.util.config.ConfigUtil
import net.kigawa.oyucraft.oyubingo.config.Config
import org.bukkit.command.CommandSender

@Kunit
class TestCommand(
  private val container: UnitContainer,
  private val configUtil: ConfigUtil,
) : AbstractCommand(
  CommandAPICommand("test")
    .withPermission(CommandPermission.OP)
) {

  @SubCommand
  fun config(): CommandAPICommand = CommandAPICommand("config")
    .withSubcommand(
      CommandAPICommand("show")

        .executes(CommandExecutor { sender: CommandSender, _: CommandArguments ->
          sender.sendMessage("_____show configs_____")
          container.getUnitList(Config::class.java).forEach { config ->

            sender.sendMessage("_____${config.javaClass.simpleName}_____")
            configUtil.configFields(config).forEach Field@{
              sender.sendMessage("%-10s : %s".format(it.name, it.get()))
            }
          }

        })

    )
}