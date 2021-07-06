package net.kigawa.onigoplugin.game.onigo;

import org.bukkit.entity.Player;

public class EqualsOniChange {
    Player runner;
    Player oni;
    public EqualsOniChange(Player oni, Player runner){
        this.oni=oni;
        this.runner=runner;

    }
    public boolean equals(Object o){
        if (o instanceof OnigoGame){
            return  ((OnigoGame)o).changeOni(oni,runner);
        }
        return false;
    }
}
