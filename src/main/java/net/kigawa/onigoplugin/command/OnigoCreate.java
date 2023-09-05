package net.kigawa.onigoplugin.command;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameManager;
import net.kigawa.onigoplugin.util.plugin.game.onigo.command.crate.*;

public class OnigoCreate extends CreateOnigo {

    public OnigoCreate(OnigoPlugin plugin, GameManager manager) {
        super(plugin,manager);
    }

    @Override
    public String getName() {
        return "onigocreate";
    }

    @Override
    public SetGameType getSetGameTypeCommand(OnigoPlugin plugin, GameManager manager) {
        return new OnigoGameType(plugin,manager);
    }
}