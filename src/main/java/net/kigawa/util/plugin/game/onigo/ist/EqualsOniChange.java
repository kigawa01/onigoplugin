package net.kigawa.util.plugin.game.onigo.ist;

import net.kigawa.onigoplugin.game.change.OnigoGame;
import net.kigawa.util.plugin.game.onigo.Onigo;
import org.bukkit.entity.Player;

public class EqualsOniChange {
    Player runner;
    Player oni;
    public EqualsOniChange(Player oni, Player runner){
        this.oni=oni;
        this.runner=runner;

    }
    @Override
    public boolean equals(Object onigo){
        if (onigo instanceof Onigo){
            return  ((Onigo)onigo).changeOni(oni,runner);
        }
        return false;
    }
}
