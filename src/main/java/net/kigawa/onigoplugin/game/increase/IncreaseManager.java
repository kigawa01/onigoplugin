package net.kigawa.onigoplugin.game.increase;

import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;
import org.bukkit.command.CommandSender;

public class IncreaseManager extends GameManager {
    public IncreaseManager(KigawaPlugin kigawaPlugin, String name) {
        super(kigawaPlugin, name);
    }

    @Override
    public void createGame(CommandSender sender, String name) {

    }
}
