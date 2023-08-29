package net.kigawa.onigoplugin.util.command.argument

import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.StringArgument
import net.kigawa.onigoplugin.OnigoPlugin
import net.kigawa.util.plugin.game.onigo.Game
import net.kigawa.util.plugin.game.onigo.GameManager

class GameArgument(nodeName: String = "game name") : AbstractArgument<Game, String>(StringArgument(nodeName)) {
  private val gameManager = OnigoPlugin.container.getUnit(GameManager::class.java)

  init {
    replaceSuggestions(ArgumentSuggestions.strings {
      gameManager.games.map { it.name }.toTypedArray()
    })
  }

  override fun apply(info: CustomArgumentInfo<String>): Game {
    return gameManager.getGame(info.input)
      ?: throw CustomArgumentException
        .fromMessageBuilder(MessageBuilder("game not found: ")
          .appendArgInput()
        )
  }

}