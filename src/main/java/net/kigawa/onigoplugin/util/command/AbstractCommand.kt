package net.kigawa.onigoplugin.util.command

import dev.jorel.commandapi.CommandAPICommand
import net.kigawa.kutil.kutil.reflection.KutilReflect

abstract class AbstractCommand(
   val commandAPICommand: CommandAPICommand
) {
  init {
    KutilReflect.getAllExitMethod(javaClass).forEach {
      it.getAnnotation(SubCommand::class.java) ?: return@forEach
      if (it.returnType != CommandAPICommand::class.java) return@forEach

      it.isAccessible = true

      commandAPICommand.withSubcommand(it.invoke(this) as CommandAPICommand)
    }
  }
}