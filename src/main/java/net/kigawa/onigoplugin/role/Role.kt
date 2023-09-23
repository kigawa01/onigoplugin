package net.kigawa.onigoplugin.role

import net.kigawa.onigoplugin.player.OnigoPlayerImpl
import net.kigawa.onigoplugin.util.plugin.game.onigo.Game

interface Role<ROLE : Role<ROLE, GAME>, GAME : Game<ROLE, GAME>> {
  fun become(player: OnigoPlayerImpl<ROLE, GAME>, game: GAME)
}