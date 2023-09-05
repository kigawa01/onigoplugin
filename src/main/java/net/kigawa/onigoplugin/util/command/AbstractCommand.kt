package net.kigawa.onigoplugin.util.command

import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.LiteralArgument
import net.kigawa.kutil.kutil.reflection.KutilReflect

abstract class AbstractCommand(
  commandName: String, vararg arguments: Argument<*>
) :LiteralArgument(commandName){

  init {
    @Suppress("LeakingThis")
    var arg: Argument<*> = this
    arguments.forEach {
      arg.then(it)
      arg = it
    }

    KutilReflect.getAllExitMethod(javaClass).forEach {
      it.getAnnotation(SubCommand::class.java) ?: return@forEach
      if (!KutilReflect.instanceOf(it.returnType, Argument::class.java)) return@forEach

      it.isAccessible = true

      arg.then(it.invoke(this) as Argument<*>)
    }
  }
}