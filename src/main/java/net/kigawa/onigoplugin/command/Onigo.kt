package net.kigawa.onigoplugin.command

import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.LiteralArgument
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.executors.CommandArguments
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import net.kigawa.kutil.unitapi.annotation.Kunit
import net.kigawa.onigoplugin.util.command.RootCommandBase
import net.kigawa.onigoplugin.util.command.SubCommand
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameManager
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
}