package net.kigawa.onigoplugin.command;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.game.change.OnigoData;
import net.kigawa.util.plugin.all.command.FirstCommand;
import net.kigawa.util.plugin.all.recorder.RecorderData;
import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.yaml.Yaml;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test extends FirstCommand {
    OnigoPlugin plugin;

    public Test(OnigoPlugin plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @Override
    public String getCommandStr() {
        return "test";
    }

    @Override
    public int getWordNumber() {
        return 0;
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {


        snakeYaml();
        yaml();
        recorder();
        return true;
    }

    public void snakeYaml() {

    }

    public void yaml() {
        logger("");
        logger("Yaml");
        Yaml yaml = new Yaml();
        File file = new File(new File(plugin.getDataFolder(), "test"), "test.yml");

        logger("save");
        yaml.save(new RecorderData(), file);

        logger("load");
        RecorderData recorderData = load(new RecorderData(), file);
        logger(recorderData.getName());

        logger("end");
    }

    public RecorderData load(RecorderData recorderData, File file) {
        org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml(new CustomClassLoaderConstructor(KigawaPlugin.class.getClassLoader()));
        try {
            FileReader reader = new FileReader(file);
            RecorderData data = yaml.loadAs(reader, recorderData.getClass());
            reader.close();
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void recorder() {
        plugin.logger("");
        plugin.logger("recorder");
        plugin.logger("save");
        OnigoData save = new OnigoData();
        save.setName("test");
        plugin.getRecorder().save(save,"test");
        plugin.logger("load");
        OnigoData load = (OnigoData) plugin.getRecorder().load(OnigoData.class, "test", "test");
        plugin.logger(load.getName());
        logger("end");
    }

    public void logger(String message) {
        plugin.logger(message);
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }
}
