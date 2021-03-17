package xyz.invisraidinq.namecolor.namecolor;

import xyz.invisraidinq.namecolor.NameColorPlugin;
import xyz.invisraidinq.namecolor.utils.CC;
import xyz.invisraidinq.namecolor.utils.ConfigFile;

public class SettingsManager {

    private final NameColorPlugin plugin;

    public SettingsManager(NameColorPlugin plugin, ConfigFile settingsFile) {
        this.plugin = plugin;

        for (String messages : settingsFile.getAsYaml().getConfigurationSection("").getKeys(false)) {
            CC.log("loading settings");
        }
    }
}
