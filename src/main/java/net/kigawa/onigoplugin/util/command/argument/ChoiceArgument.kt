package net.kigawa.onigoplugin.util.command.argument

import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.StringArgument

class ChoiceArgument(nodeName: String, private vararg var choice: String) :
  AbstractArgument<String, String>(StringArgument(nodeName)) {

  init {
    replaceSuggestions(ArgumentSuggestions.strings { choice })
  }

  override fun apply(info: CustomArgumentInfo<String>): String {
    return choice.firstOrNull { it == info.input } ?: throw CustomArgumentException
      .fromMessageBuilder(MessageBuilder("choice not found: ")
        .appendArgInput()
      )
  }

}