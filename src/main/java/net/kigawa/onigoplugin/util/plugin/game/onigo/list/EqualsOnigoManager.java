package net.kigawa.onigoplugin.util.plugin.game.onigo.list;

import net.kigawa.onigoplugin.util.plugin.game.onigo.Onigo;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

public class EqualsOnigoManager {
    Player runner;
    Player oni;
    HumanEntity player;

    public EqualsOnigoManager(Player oni, Player runner) {
        this.oni = oni;
        this.runner = runner;

    }

    public EqualsOnigoManager(HumanEntity player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object onigo) {
        if (runner!=null&&oni!=null) {
            if (onigo instanceof Onigo) {
                return ((Onigo) onigo).changeOni(oni, runner);
            }
        }
        if (player!=null){
            return ((Onigo)onigo).contain(player);
        }
        return false;
    }
}
