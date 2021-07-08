package net.kigawa.onigoplugin.game.change;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.yaml.EqualsYamlData;
import org.bukkit.command.CommandSender;

public class ChangeManager extends GameManager {
    public ChangeManager(KigawaPlugin kigawaPlugin, String name) {
        super(kigawaPlugin, name);
    }

    @Override
    public void createGame(CommandSender sender, String name) {
        if (!getGames().contains(new EqualsYamlData(name))) {
            OnigoData data = new OnigoData();
            data.setName(name);
            getGames().add(new OnigoGame(getPlugin(), data,this));
            getPlugin().getRecorder().save(data,getManagerName());
        } else {
            sender.sendMessage("this name can't use");
        }
    }
}
