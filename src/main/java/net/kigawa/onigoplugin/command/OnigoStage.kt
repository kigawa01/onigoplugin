package net.kigawa.onigoplugin.command

import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.LiteralArgument
import dev.jorel.commandapi.arguments.LocationArgument
import dev.jorel.commandapi.arguments.LocationType
import dev.jorel.commandapi.executors.CommandArguments
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import net.kigawa.onigoplugin.OnigoPlugin
import net.kigawa.onigoplugin.util.command.ArgumentBase
import net.kigawa.onigoplugin.util.command.CustomArgs
import net.kigawa.onigoplugin.util.command.SubCommand
import net.kigawa.onigoplugin.util.plugin.game.stage.StageData
import net.kigawa.onigoplugin.util.plugin.game.stage.StageManager
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class OnigoStage : ArgumentBase(
  "stage", CustomArgs.stage("stage").withPermission(CommandPermission.OP)
) {
  private val stageManager = OnigoPlugin.container.getUnit(StageManager::class.java)

  @SubCommand
  fun stageLoc(): Argument<*> = LiteralArgument("stage-room").withPermission(CommandPermission.OP).then(
    CustomArgs.choice("loc point", "start-loc", "end-loc").withPermission(CommandPermission.OP).setOptional(
      true
    ).then(
      LocationArgument("location", LocationType.BLOCK_POSITION).withPermission(CommandPermission.OP).setOptional(
        true
      ).executesPlayer(PlayerCommandExecutor { player: Player, commandArguments: CommandArguments ->
        val stage = commandArguments.get("stage") as StageData
        val location = commandArguments.get("location") as Location?
        val choice = commandArguments.get("loc point") as String?

        if (location == null || choice == null) {
          val world = stage.stageWorld?.let { Bukkit.getServer().getWorld(it) }
          if (world == null) {
            player.sendMessage("world: ${stage.stageWorld} not found")
            return@PlayerCommandExecutor
          }

          player.spigot().sendMessage(
            *ComponentBuilder("stage: ${stage.name}\n").append("world: ${stage.stageWorld}\n").append(
              "start-loc: ${stage.startLoc[0]} ${stage.startLoc[1]} ${stage.startLoc[2]}\n"
            )
              .event(
                ClickEvent(
                  ClickEvent.Action.RUN_COMMAND,
                  "/minecraft:tp ${player.name} ${stage.startLoc[0]} ${stage.startLoc[1]} ${stage.startLoc[2]}"
                )
              )
              .append("stage-loc: ${stage.stageLoc[0]} ${stage.stageLoc[1]} ${stage.stageLoc[2]} - ")
              .event(
                ClickEvent(
                  ClickEvent.Action.RUN_COMMAND,
                  "/minecraft:tp ${player.name} ${stage.stageLoc[0]} ${stage.stageLoc[1]} ${stage.stageLoc[2]}"
                )
              ).append(TextComponent("${stage.stageLoc[3]} ${stage.stageLoc[4]} ${stage.stageLoc[5]}"))
              .event(
                ClickEvent(
                  ClickEvent.Action.RUN_COMMAND,
                  "/minecraft:tp ${player.name} ${stage.stageLoc[3]} ${stage.stageLoc[4]} ${stage.stageLoc[5]}"
                )
              ).create()
          )
        } else {
          if (choice == "start-loc") {
            stage.stageWorld = player.world.name
            stage.setStageStartLoc(location)
            stageManager.save(stage)

            player.sendMessage("start point of stage room is set")
            return@PlayerCommandExecutor
          }
          if (choice == "end-loc") {
            stage.setStageEndLoc(location)
            stageManager.save(stage)

            player.sendMessage("end point of stage room is set")
            return@PlayerCommandExecutor
          }
          player.sendMessage("$choice is not allowed")
        }
      })
    )
  )

  @SubCommand
  fun startLoc(): Argument<*> = LiteralArgument("start-loc").withPermission(CommandPermission.OP).then(
    LocationArgument("location", LocationType.BLOCK_POSITION).withPermission(CommandPermission.OP).executes(
      CommandExecutor { sender: CommandSender, args: CommandArguments ->
        val stage = args.get("stage") as StageData
        val location = args.get("location") as Location

        stage.setStartLoc(location)
        stageManager.save(stage)

        sender.sendMessage("stage loc is set")
      })
  )
}