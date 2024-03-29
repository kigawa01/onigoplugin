package net.kigawa.onigoplugin.util.unit

import net.kigawa.kutil.unitapi.component.InitStack
import net.kigawa.kutil.unitapi.extention.InitializedFilter
import net.kigawa.onigoplugin.OnigoPlugin
import org.bukkit.Bukkit
import org.bukkit.event.Listener

class ListenerFilter(
  private val oyuBingo: OnigoPlugin,
) : InitializedFilter {

  override fun <T : Any> filter(obj: T, stack: InitStack): T {
    if (obj !is Listener) return obj

    Bukkit.getServer().pluginManager.registerEvents(obj, oyuBingo)
    return obj
  }
}