package net.kigawa.onigoplugin.game.increase;

import net.kigawa.onigoplugin.game.change.OnigoData;
import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.Game;
import org.bukkit.entity.Player;

public class IncreaseGame extends Game {
    public IncreaseGame(KigawaPlugin kigawaPlugin, OnigoData onigoData) {
        super(kigawaPlugin, onigoData);
    }

    @Override
    public String getBordName() {
        return "増え鬼";
    }

    @Override
    public boolean changeOni(Player oni, Player runner) {
        return false;
    }

    @Override
    public void sendEndMessage() {
    }
}
