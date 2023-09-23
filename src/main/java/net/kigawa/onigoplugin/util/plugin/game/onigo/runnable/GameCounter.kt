package net.kigawa.onigoplugin.util.plugin.game.onigo.runnable;

import net.kigawa.onigoplugin.util.plugin.all.timer.Counter;
import net.kigawa.onigoplugin.util.plugin.game.onigo.Game;
import org.bukkit.ChatColor;

import java.util.Objects;

public class GameCounter extends Counter
{
  Game game;

  public GameCounter(String bordName, String bordID, Game onigo) {
    super(bordName, bordID, onigo.getPlugin());
    //replace variable
    this.game = onigo;

    //start count
    startMin(0L, onigo.getD().getGameTime(), 3, onigo.getJoinPlayer(), ChatColor.RED + "END", ChatColor.GREEN);
  }

  @Override
  public void sendLastMessage() {
    //send end
    Objects.requireNonNull(game.getPlugin().messenger)
        .sendTitle(game.getJoinPlayer(), ChatColor.RED + "END", "");
  }
}
