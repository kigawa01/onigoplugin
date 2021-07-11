package net.kigawa.util.plugin.game.onigo;

import net.kigawa.util.all.Named;
import org.bukkit.entity.Player;

import javax.naming.Name;

public interface Onigo extends Named {
    public boolean changeOni(Player oni, Player runner);
}
