package net.kigawa.onigoplugin.command

import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.LiteralArgument
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.executors.CommandArguments
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import net.kigawa.kutil.unitapi.annotation.Kunit
import net.kigawa.onigoplugin.util.command.CustomArgs
import net.kigawa.onigoplugin.util.command.RootCommandBase
import net.kigawa.onigoplugin.util.command.SubCommand
import net.kigawa.onigoplugin.util.plugin.game.onigo.Game
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameManager
import net.kigawa.onigoplugin.util.plugin.game.stage.StageData
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Kunit
class Onigo(
  private val gameManager: GameManager,
) : RootCommandBase("onigo") {

  @SubCommand
  fun create(): Argument<*> = LiteralArgument("create")
    .then(StringArgument("name")
      .executesPlayer(PlayerCommandExecutor { player: Player, commandArguments: CommandArguments ->
        val name = commandArguments.get("name") as String

        if (gameManager.containGame(name)) {
          player.sendMessage("$name is already exists")
          return@PlayerCommandExecutor
        }
        gameManager.createGame(player, name)
        player.sendMessage("onigo is created")
      })
    )

  @SubCommand
  fun edit() = OnigoEdit()

  @SubCommand
  fun start(): Argument<*> = LiteralArgument("start")
    .then(CustomArgs.game("game")
      .then(CustomArgs.stage("stage").setOptional(true)
        .executesPlayer(PlayerCommandExecutor { player: Player, args: CommandArguments ->
          val game = args.get("game") as Game
          val stage = args.get("stage") as StageData?
          game.start(player, stage?.name)
        })
      )
    )

  @SubCommand
  fun end(): Argument<*> = LiteralArgument("end")
    .then(CustomArgs.game("game")
      .executes(CommandExecutor { _: CommandSender, args: CommandArguments ->
        val game = args.get("game") as Game
        game.end()
      })
    )

  @SubCommand
  fun list(): Argument<*> = LiteralArgument("list")
    .executes(CommandExecutor { sender: CommandSender, _: CommandArguments ->
      gameManager.games.forEach {
        sender.sendMessage("name " + it.name)
        sender.sendMessage(" world " + it.d.waitRoomWorld)
      }
    })
}