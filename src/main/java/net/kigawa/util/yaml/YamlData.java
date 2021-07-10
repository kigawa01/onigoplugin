package net.kigawa.util.yaml;

import net.kigawa.util.all.Named;

public interface YamlData extends Named {
    String name = null;
    public String getName();
    public void setName(String name);
}
