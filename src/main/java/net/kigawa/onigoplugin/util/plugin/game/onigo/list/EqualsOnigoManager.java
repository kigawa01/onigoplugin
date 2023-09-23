package net.kigawa.onigoplugin.util.plugin.game.onigo.list;

import net.kigawa.onigoplugin.util.plugin.game.onigo.Onigo;
import org.bukkit.entity.Player;

public class EqualsOnigoManager
{
  Player runner;
  Player oni;

  public EqualsOnigoManager(Player oni, Player runner) {
    this.oni = oni;
    this.runner = runner;

  }

  @Override
  public boolean equals(Object onigo) {
    if (runner != null && oni != null) {
      if (onigo instanceof Onigo) {
        return ((Onigo) onigo).changeOni(oni, runner);
      }
    }
    return false;
  }
}
