package net.kigawa.onigoplugin.game.increase;

import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.yaml.EqualsYamlData;
import org.bukkit.command.CommandSender;

public class IncreaseManager extends GameManager {
    public IncreaseManager(KigawaPlugin kigawaPlugin, String name) {
        super(kigawaPlugin, name);
    }

    @Override
    public void createGame(CommandSender sender, String name) {
        if (!getGames().contains(new EqualsYamlData(name))) {
            IncreaseData data = new IncreaseData();
            data.setName(name);
            getGames().add(new IncreaseGame(getPlugin(), data, this));
            getPlugin().getRecorder().save(data, getManagerName());
        } else {
            sender.sendMessage("this name can't use");
        }
    }
}