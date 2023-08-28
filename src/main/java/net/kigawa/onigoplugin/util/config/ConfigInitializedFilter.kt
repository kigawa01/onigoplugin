package net.kigawa.onigoplugin.util.config

import net.kigawa.kutil.unitapi.component.InitStack
import net.kigawa.kutil.unitapi.extention.InitializedFilter
import net.kigawa.oyucraft.oyubingo.config.Config

class ConfigInitializedFilter(
  private val configManager: ConfigManager,
) : InitializedFilter {


  override fun <T : Any> filter(obj: T, stack: InitStack): T {
    if (obj !is Config) return obj

    configManager.load(obj)
    configManager.save(obj)

    return obj
  }
}