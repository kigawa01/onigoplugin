package net.kigawa.util.plugin.game.onigo;

import net.kigawa.onigoplugin.game.change.OnigoGame;
import org.bukkit.entity.Player;

public class EqualsOniChange {
    Player runner;
    Player oni;
    public EqualsOniChange(Player oni, Player runner){
        this.oni=oni;
        this.runner=runner;

    }
    @Override
    public boolean equals(Object o){
        if (o instanceof OnigoGame){
            return  ((OnigoGame)o).changeOni(oni,runner);
        }
        return false;
    }
}
