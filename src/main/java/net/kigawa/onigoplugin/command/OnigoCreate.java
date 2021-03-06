package net.kigawa.onigoplugin.command;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.all.command.FirstCommand;
import net.kigawa.util.plugin.all.command.SubCommand;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.plugin.game.onigo.command.crate.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class OnigoCreate extends CreateOnigo {

    public OnigoCreate(OnigoPlugin plugin, GameManager manager) {
        super(plugin,manager);
    }

    @Override
    public String getName() {
        return "onigocreate";
    }

    @Override
    public SetGameType getSetGameTypeCommand(KigawaPlugin plugin, GameManager manager) {
        return new OnigoGameType(plugin,manager);
    }
}