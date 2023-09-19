package net.kigawa.onigoplugin.role

import net.kigawa.onigoplugin.player.OnigoPlayer
import net.kigawa.onigoplugin.util.plugin.game.onigo.Game

interface Role<
    ROLE : Role<ROLE, GAME>,
    GAME : Game<ROLE, GAME>
    > {
  fun OnigoPlayer<ROLE, GAME>.become(game: GAME)
}