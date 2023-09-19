package net.kigawa.onigoplugin.util.command

import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.CustomArgument
import dev.jorel.commandapi.arguments.StringArgument
import net.kigawa.kutil.unitapi.annotation.Inject
import net.kigawa.kutil.unitapi.annotation.Kunit
import net.kigawa.onigoplugin.game.OnigoGame
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameManager
import net.kigawa.onigoplugin.util.plugin.game.stage.StageData
import net.kigawa.onigoplugin.util.plugin.game.stage.StageManager

@Kunit
object CustomArgs {
  @Inject
  private lateinit var gameManager: GameManager

  @Inject
  private lateinit var stageManager: StageManager
  fun game(nodeName: String = "game"): Argument<OnigoGame> = CustomArgument(StringArgument(nodeName)) {
    return@CustomArgument gameManager.getGame(it.input)
      ?: throw CustomArgument.CustomArgumentException
        .fromMessageBuilder(
          CustomArgument.MessageBuilder("game not found: ")
            .appendArgInput()
        )
  }.replaceSuggestions(ArgumentSuggestions.strings {
    gameManager.games.map { it.name }.toTypedArray()
  })

  fun stage(nodeName: String = "stage"): Argument<StageData> = CustomArgument(StringArgument(nodeName)) {
    return@CustomArgument stageManager.getStage(it.input)
      ?: throw CustomArgument.CustomArgumentException
        .fromMessageBuilder(
          CustomArgument.MessageBuilder("game not found: ")
            .appendArgInput()
        )
  }.replaceSuggestions(ArgumentSuggestions.strings {
    stageManager.allStage.map { it.name }.toTypedArray()
  })

  fun choice(nodeName: String, vararg choice: String): Argument<String> =
    CustomArgument(StringArgument(nodeName)) { info ->
      return@CustomArgument choice.firstOrNull { it == info.input } ?: throw CustomArgument.CustomArgumentException
        .fromMessageBuilder(
          CustomArgument
            .MessageBuilder("choice not found: ")
            .appendArgInput()
        )
    }.replaceSuggestions(ArgumentSuggestions.strings { choice })
}