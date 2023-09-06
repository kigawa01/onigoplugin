package net.kigawa.onigoplugin.command

import dev.jorel.commandapi.arguments.*
import dev.jorel.commandapi.executors.CommandArguments
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import net.kigawa.onigoplugin.game.GameType
import net.kigawa.onigoplugin.util.command.ArgumentBase
import net.kigawa.onigoplugin.util.command.CustomArgs
import net.kigawa.onigoplugin.util.command.SubCommand
import net.kigawa.onigoplugin.util.plugin.game.onigo.Game
import org.bukkit.Location
import org.bukkit.entity.Player

class OnigoEdit : ArgumentBase("edit", CustomArgs.game("game")) {

  @SubCommand
  fun waitRoom(): Argument<*> = LiteralArgument("wait-room")
    .then(CustomArgs.choice("loc point", "start-loc", "end-loc")
      .then(LocationArgument("location", LocationType.BLOCK_POSITION)
        .executesPlayer(PlayerCommandExecutor { player: Player, commandArguments: CommandArguments ->
          val game = commandArguments.get("game") as Game
          val location = commandArguments.get("location") as Location
          val choice = commandArguments.get("loc point") as String

          if (choice == "start-loc") {
            game.setWaitingRoom1(player.world.name, location.blockX, location.blockY, location.blockZ)
            player.sendMessage("start point of wait room is set")
            return@PlayerCommandExecutor
          }
          if (choice == "end-loc") {
            game.setWaitingRoom2(location.blockX, location.blockY, location.blockZ)
            player.sendMessage("end point of wait room is set")
            return@PlayerCommandExecutor
          }
          player.sendMessage("$choice is not allowed")
        })
      )
    )

  @SubCommand
  fun oniWaitRoom(): Argument<*> = LiteralArgument("oni-wait-room")
    .then(CustomArgs.choice("loc point", "start-loc", "end-loc")
      .then(LocationArgument("location", LocationType.BLOCK_POSITION)
        .executesPlayer(PlayerCommandExecutor { player: Player, commandArguments: CommandArguments ->
          val game = commandArguments.get("game") as Game
          val location = commandArguments.get("location") as Location
          val choice = commandArguments.get("loc point") as String

          if (choice == "start-loc") {
            game.setOniWait1(player.world.name, location.blockX, location.blockY, location.blockZ)
            player.sendMessage("start point of oni wait room is set")
            return@PlayerCommandExecutor
          }
          if (choice == "end-loc") {
            game.setOniWait2(location.blockX, location.blockY, location.blockZ)
            player.sendMessage("end point of oni wait room is set")
            return@PlayerCommandExecutor
          }
          player.sendMessage("$choice is not allowed")
        })
      )
    )

  @SubCommand
  fun oniCount(): Argument<*> = LiteralArgument("oni-count")
    .then(IntegerArgument("count")
      .executesPlayer(PlayerCommandExecutor { player: Player, commandArguments: CommandArguments ->
        val game = commandArguments.get("game") as Game
        val count = commandArguments.get("count") as Int

        game.setOniCount(count)
        player.sendMessage("oni count is set")
      })
    )

  @SubCommand
  fun waitTime(): Argument<*> = LiteralArgument("wait-time")
    .then(IntegerArgument("wait time")
      .executesPlayer(PlayerCommandExecutor { player: Player, commandArguments: CommandArguments ->
        val game = commandArguments.get("game") as Game
        val waitTime = commandArguments.get("wait time") as Int

        game.setWaitTime(waitTime)
        player.sendMessage("wait time is set")
      })
    )

  @SubCommand
  fun gameTime(): Argument<*> = LiteralArgument("game-time")
    .then(IntegerArgument("game time")
      .executesPlayer(PlayerCommandExecutor { player: Player, commandArguments: CommandArguments ->
        val game = commandArguments.get("game") as Game
        val waitTime = commandArguments.get("game time") as Int

        game.setGameTime(waitTime)
        player.sendMessage("game time is set")
      })
    )

  @SubCommand
  fun endLoc(): Argument<*> = LiteralArgument("end-loc")
    .then(LocationArgument("location", LocationType.BLOCK_POSITION)
      .executesPlayer(PlayerCommandExecutor { player: Player, commandArguments: CommandArguments ->
        val game = commandArguments.get("game") as Game
        val location = commandArguments.get("location") as Location

        game.setEndLoc(player.world.name, location.blockX, location.blockY, location.blockZ)
        player.sendMessage("end location is set")
      })
    )

  @SubCommand
  fun gameType(): Argument<*> = LiteralArgument("game-type")
    .then(CustomArgs.choice("type", *GameType.entries.map { it.value }.toTypedArray())
      .executesPlayer(PlayerCommandExecutor { player: Player, commandArguments: CommandArguments ->
        val game = commandArguments.get("game") as Game
        val gameType = commandArguments.get("type") as String

        game.gameType = gameType
        player.sendMessage("game type is set")
      })
    )
}