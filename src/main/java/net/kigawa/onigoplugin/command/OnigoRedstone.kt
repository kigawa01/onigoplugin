package net.kigawa.onigoplugin.command

import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.LiteralArgument
import dev.jorel.commandapi.arguments.LocationArgument
import dev.jorel.commandapi.arguments.LocationType
import dev.jorel.commandapi.executors.CommandArguments
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import net.kigawa.onigoplugin.util.command.ArgumentBase
import net.kigawa.onigoplugin.util.command.CustomArgs
import net.kigawa.onigoplugin.util.command.SubCommand
import org.bukkit.entity.Player

class OnigoRedstone : ArgumentBase("edit", CustomArgs.game("game")) {

  @SubCommand
  fun startBlock(): Argument<*> = LiteralArgument("start-block")
    .withPermission(CommandPermission.OP)
    .then(CustomArgs.choice("loc point", "start-loc", "end-loc")
      .withPermission(CommandPermission.OP)
      .then(LocationArgument("location", LocationType.BLOCK_POSITION)
        .withPermission(CommandPermission.OP)
        .executesPlayer(PlayerCommandExecutor { player: Player, commandArguments: CommandArguments ->
        })
      )
    )
}