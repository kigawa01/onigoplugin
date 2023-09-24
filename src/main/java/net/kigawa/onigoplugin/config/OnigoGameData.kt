package net.kigawa.onigoplugin.config

data class OnigoGameData(
  val name: String = "",
  val locations: MutableList<BlockLocationData> = mutableListOf(),
)