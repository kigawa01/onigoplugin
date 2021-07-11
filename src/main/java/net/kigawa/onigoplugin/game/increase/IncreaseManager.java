package net.kigawa.onigoplugin.game.increase;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.Game;
import net.kigawa.util.plugin.game.onigo.GameData;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.all.EqualsNamed;
import org.bukkit.command.CommandSender;

import java.util.List;


public class IncreaseManager extends GameManager {

    public IncreaseManager(KigawaPlugin kigawaPlugin, String name) {
        super(kigawaPlugin, name);
    }

    @Override
    public List<Game> initializeGame(List<GameData> data) {
        return null;
    }

    @Override
    public void createGame(CommandSender sender, String name) {
        if (!getGames().contains(new EqualsNamed(name))) {
            IncreaseData data = new IncreaseData();
            data.setName(name);
            getGames().add(new IncreaseGame(getPlugin(), data, this));
            getPlugin().getRecorder().save(data, getName());
        } else {
            sender.sendMessage("this name can't use");
        }
    }
}
