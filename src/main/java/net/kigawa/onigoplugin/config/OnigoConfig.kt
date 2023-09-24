package net.kigawa.onigoplugin.config

import net.kigawa.onigoplugin.util.config.Config

class OnigoConfig : Config() {
  val games: MutableList<OnigoGameData> = mutableListOf()
}