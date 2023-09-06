package net.kigawa.onigoplugin.util.plugin.game.onigo;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

public interface Onigo
{
  boolean changeOni(Player oni, Player runner);

  boolean contain(HumanEntity player);
}
