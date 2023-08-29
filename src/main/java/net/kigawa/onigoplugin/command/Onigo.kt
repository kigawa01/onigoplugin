package net.kigawa.onigoplugin.command

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.executors.CommandArguments
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import net.kigawa.kutil.unitapi.annotation.Kunit
import net.kigawa.onigoplugin.util.command.AbstractCommand
import net.kigawa.onigoplugin.util.command.SubCommand
import net.kigawa.util.plugin.game.onigo.GameManager
import org.bukkit.entity.Player

@Kunit
class Onigo(
  private val gameManager: GameManager,
) : AbstractCommand("onigo") {
  init {
    withPermission(CommandPermission.OP)
  }

  @SubCommand
  fun create(): CommandAPICommand = CommandAPICommand("create")
    .withArguments(StringArgument("name"))
    .executesPlayer(PlayerCommandExecutor { player: Player, commandArguments: CommandArguments ->
      val name = commandArguments.get("name") as String
      gameManager.createGame(player, name)
    })

  @SubCommand
  fun edit(): CommandAPICommand = OnigoEdit()
}