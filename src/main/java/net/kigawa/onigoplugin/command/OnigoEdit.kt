package net.kigawa.onigoplugin.command

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.LocationArgument
import dev.jorel.commandapi.arguments.LocationType
import dev.jorel.commandapi.executors.CommandArguments
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import net.kigawa.onigoplugin.OnigoPlugin
import net.kigawa.onigoplugin.util.command.AbstractCommand
import net.kigawa.onigoplugin.util.command.SubCommand
import net.kigawa.onigoplugin.util.command.argument.ChoiceArgument
import net.kigawa.onigoplugin.util.command.argument.GameArgument
import net.kigawa.onigoplugin.util.plugin.game.onigo.Game
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameManager
import org.bukkit.Location
import org.bukkit.entity.Player

class OnigoEdit : AbstractCommand("edit") {
  private val gameManager = OnigoPlugin.container.getUnit(GameManager::class.java)

  init {
    withPermission(CommandPermission.OP)
    withArguments(GameArgument())
  }

  @SubCommand
  fun waitRoom(): CommandAPICommand = CommandAPICommand("wait-room")
    .withPermission(CommandPermission.OP)
    .withArguments(ChoiceArgument("loc point", "start-loc", "end-loc"))
    .withArguments(LocationArgument("location", LocationType.BLOCK_POSITION))
    .executesPlayer(PlayerCommandExecutor { player: Player, commandArguments: CommandArguments ->
      val game = commandArguments.get("game") as Game
      val location = commandArguments.get("location") as Location
      val choice = commandArguments.get("loc point") as String

      if (choice == "start-loc") {
        game.setWaitingRoom1(player.world.name, location.blockX, location.blockY, location.blockZ)
        player.sendMessage("start point of wait room is set")
      }
      if (choice == "end-loc") {
        game.setWaitingRoom2(location.blockX, location.blockY, location.blockZ)
        player.sendMessage("end point of wait room is set")
      }
    })
}