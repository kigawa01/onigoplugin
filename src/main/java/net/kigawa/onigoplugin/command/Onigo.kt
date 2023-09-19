package net.kigawa.onigoplugin.command

import dev.jorel.commandapi.CommandPermission
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
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameManager
import net.kigawa.onigoplugin.util.plugin.game.stage.StageData
import net.kigawa.onigoplugin.util.plugin.game.stage.StageManager
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Kunit
class Onigo(
  private val gameManager: GameManager,
  private val stageManager: StageManager,
) : RootCommandBase("onigo") {
  init {
    withPermission(CommandPermission.OP)
  }

  @SubCommand
  fun createGame(): Argument<*> = LiteralArgument("create-game")
    .withPermission(CommandPermission.OP)
    .then(StringArgument("name")
      .withPermission(CommandPermission.OP)
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
  fun game(): Argument<String> = OnigoGameCmd()
    .withPermission(CommandPermission.OP)

  @SubCommand
  fun stage(): Argument<String> = OnigoStage()
    .withPermission(CommandPermission.OP)

  @SubCommand
  fun redstone(): Argument<String> = OnigoRedstone()
    .withPermission(CommandPermission.OP)

  @SubCommand
  fun start(): Argument<*> = LiteralArgument("start")
    .withPermission(CommandPermission.OP)
    .then(CustomArgs.game("game")
      .withPermission(CommandPermission.OP)
      .then(CustomArgs.stage("stage")
        .withPermission(CommandPermission.OP)
        .setOptional(true)
        .executesPlayer(PlayerCommandExecutor { player: Player, args: CommandArguments ->
          val game = args.get("game") as net.kigawa.onigoplugin.game.OnigoGame
          val stage = args.get("stage") as StageData?
          game.start(player, stage?.name)
        })
      )
    )

  @SubCommand
  fun end(): Argument<*> = LiteralArgument("end")
    .withPermission(CommandPermission.OP)
    .then(CustomArgs.game("game")
      .withPermission(CommandPermission.OP)
      .executes(CommandExecutor { _: CommandSender, args: CommandArguments ->
        val game = args.get("game") as net.kigawa.onigoplugin.game.OnigoGame
        game.end()
      })
    )

  @SubCommand
  fun list(): Argument<*> = LiteralArgument("list")
    .withPermission(CommandPermission.OP)
    .executes(CommandExecutor { sender: CommandSender, _: CommandArguments ->
      gameManager.games.forEach {
        sender.sendMessage("name " + it.name)
        sender.sendMessage(" world " + it.d.waitRoomWorld)
      }
    })

  @SubCommand
  fun createStage(): Argument<*> = LiteralArgument("create-stage")
    .withPermission(CommandPermission.OP)
    .then(StringArgument("name")
      .withPermission(CommandPermission.OP)
      .executes(CommandExecutor { sender: CommandSender, args: CommandArguments ->
        val name = args.get("name") as String

        stageManager.setStage(name, sender)
        sender.sendMessage("stage $name is created")
      }))


}