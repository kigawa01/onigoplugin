package net.kigawa.onigoplugin.util.command.argument

import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.CustomArgument

abstract class AbstractArgument<RETURN, INPUT>(base: Argument<INPUT>)
  : CustomArgument<RETURN, INPUT>(base, Parser()), CustomArgument.CustomArgumentInfoParser<RETURN, INPUT> {
  init {
    @Suppress("LeakingThis")
    parser.argument = this
  }

  private val parser: Parser<RETURN, INPUT>
    get() {
      val parserField = CustomArgument::class.java.getDeclaredField("infoParser")
      parserField.isAccessible = true

      @Suppress("UNCHECKED_CAST")
      return parserField.get(this) as Parser<RETURN, INPUT>
    }

  abstract override fun apply(info: CustomArgumentInfo<INPUT>): RETURN

  class Parser<RETURN, INPUT> : CustomArgumentInfoParser<RETURN, INPUT> {
    internal lateinit var argument: AbstractArgument<RETURN, INPUT>
    override fun apply(info: CustomArgumentInfo<INPUT>): RETURN {
      return argument.apply(info)
    }
  }
}