package net.kigawa.onigoplugin.util.config

import net.kigawa.kutil.kutil.reflection.KutilReflect
import net.kigawa.onigoplugin.OnigoPlugin
import net.kigawa.onigoplugin.util.caseformat.CaseFormat
import net.kigawa.oyucraft.oyubingo.config.Config
import net.kigawa.oyucraft.oyubingo.config.annotation.ConfigName
import net.kigawa.oyucraft.oyubingo.config.annotation.ConfigValue
import java.io.File

class ConfigUtil(private val onigoPlugin: OnigoPlugin) {

  fun type(config: Config): Class<out Config> {
    return config.javaClass
  }

  fun fileName(config: Config): String {
    val name = type(config).getAnnotation(ConfigName::class.java)?.name ?: ""
    if (name != "") return name

    return CaseFormat.HIGHER_CAMEL_CASE.caseString(type(config).simpleName).toFormatStr(CaseFormat.KEBAB_CASE) + ".yml"
  }

  fun dir(config: Config): File {
    val path = type(config).getAnnotation(ConfigName::class.java)?.dir ?: ""
    if (path != "") return File(path)

    return onigoPlugin.dataFolder
  }

  fun file(config: Config): File {
    return File(dir(config), fileName(config))
  }

  fun configFields(config: Config): List<ConfigField> {
    return KutilReflect.getAllExitFields(type(config)).mapNotNull {
      val configName = it.getAnnotation(ConfigValue::class.java) ?: return@mapNotNull null
      return@mapNotNull ConfigField(config, it, configName)
    }
  }
}