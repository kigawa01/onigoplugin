package net.kigawa.onigoplugin.event.onigo;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.event.Event;

public class OnigoListener extends Event {
    OnigoPlugin plugin;
    public OnigoListener(OnigoPlugin onigoPlugin) {
        super(onigoPlugin);
        plugin=onigoPlugin;
    }
}
