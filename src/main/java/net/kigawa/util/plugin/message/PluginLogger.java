package net.kigawa.util.plugin.message;

import net.kigawa.util.message.Logger;
import net.kigawa.util.plugin.KigawaPlugin;

public abstract class PluginLogger implements Logger {
    KigawaPlugin plugin;
    public PluginLogger(KigawaPlugin kigawaPlugin){
        plugin=kigawaPlugin;
    }
    @Override
    public void logger(String message) {
        plugin.logger(message);
    }
}
