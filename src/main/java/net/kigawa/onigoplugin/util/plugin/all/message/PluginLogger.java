package net.kigawa.onigoplugin.util.plugin.all.message;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.util.message.Logger;

public abstract class PluginLogger implements Logger {
    OnigoPlugin plugin;
    public PluginLogger(OnigoPlugin OnigoPlugin){
        plugin=OnigoPlugin;
    }
    @Override
    public void logger(String message) {
        plugin.logger(message);
    }
}
