package net.kigawa.onigoplugin.command;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.plugin.game.onigo.command.crate.SetGameType;

import java.util.ArrayList;
import java.util.List;

public class OnigoGameType extends SetGameType {
    public OnigoGameType(KigawaPlugin kigawaPlugin, GameManager manager) {
        super(kigawaPlugin, manager);
    }

    @Override
    public List<String> getGameTypes() {
        List<String> strings=new ArrayList<>();
        strings.add("change");
        strings.add("increase");
        strings.add("ice");
        return strings;
    }


}
