package net.kigawa.onigoplugin.command

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.IntegerArgument
import dev.jorel.commandapi.arguments.LocationArgument
import dev.jorel.commandapi.arguments.LocationType
import dev.jorel.commandapi.executors.CommandArguments
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import net.kigawa.onigoplugin.util.command.AbstractCommand
import net.kigawa.onigoplugin.util.command.CustomArgs
import net.kigawa.onigoplugin.util.command.SubCommand
import net.kigawa.onigoplugin.util.plugin.game.onigo.Game
import org.bukkit.Location
import org.bukkit.entity.Player

class OnigoEdit : AbstractCommand(
  CommandAPICommand("edit")
    .withPermission(CommandPermission.OP)
    .withArguments(CustomArgs.game("game"))
) {

  @SubCommand
  fun waitRoom(): CommandAPICommand = CommandAPICommand("wait-room")
    .withPermission(CommandPermission.OP)
    .withArguments(CustomArgs.choice("loc point", "start-loc", "end-loc"))
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
      player.sendMessage("$choice is not allowed")
    })

  @SubCommand
  fun oniCount(): CommandAPICommand = CommandAPICommand("oni-count")
    .withPermission(CommandPermission.OP)
    .withArguments(IntegerArgument("count"))
    .executesPlayer(PlayerCommandExecutor { player: Player, commandArguments: CommandArguments ->
      val game = commandArguments.get("game") as Game
      val count = commandArguments.get("count") as Int

      game.setOniCount(count)
      player.sendMessage("oni count is set")
    })

  @SubCommand
  fun waitTime(): CommandAPICommand = CommandAPICommand("wait-time")
    .withPermission(CommandPermission.OP)
    .withArguments(IntegerArgument("wait time"))
    .executesPlayer(PlayerCommandExecutor { player: Player, commandArguments: CommandArguments ->
      val game = commandArguments.get("game") as Game
      val waitTime = commandArguments.get("wait time") as Int

      game.setWaitTime(waitTime)
      player.sendMessage("wait time is set")
    })
}