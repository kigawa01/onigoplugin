package net.kigawa.onigoplugin.util.unit

import net.kigawa.kutil.unitapi.component.InitStack
import net.kigawa.kutil.unitapi.extention.InitializedFilter
import net.kigawa.onigoplugin.util.command.RootCommandBase

class CommandFilter : InitializedFilter {

  override fun <T : Any> filter(obj: T, stack: InitStack): T {
    if (obj !is RootCommandBase) return obj

    obj.register()
    return obj
  }
}