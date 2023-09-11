package net.kigawa.onigoplugin.util.command

import dev.jorel.commandapi.CommandTree
import dev.jorel.commandapi.arguments.Argument
import net.kigawa.kutil.kutil.reflection.KutilReflect

abstract class RootCommandBase(commandName: String, vararg arguments: Argument<*>) : CommandTree(commandName) {
  init {
    var arg: Argument<*>? = null
    arguments.forEach {
      arg?.then(it) ?: then(it)
      arg = it
    }

    KutilReflect.getAllExitMethod(javaClass).forEach {
      it.getAnnotation(SubCommand::class.java) ?: return@forEach
      if (!KutilReflect.instanceOf(it.returnType, Argument::class.java)) return@forEach

      it.isAccessible = true

      val subArg = it.invoke(this) as Argument<*>
      arg?.then(subArg) ?: then(subArg)
    }
  }
}