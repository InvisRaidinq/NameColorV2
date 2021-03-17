package xyz.invisraidinq.namecolor.utils;

import xyz.invisraidinq.namecolor.NameColorPlugin;

public class MessagesManager {

    private final NameColorPlugin plugin;

    public MessagesManager(NameColorPlugin plugin, ConfigFile langFile) {
        this.plugin = plugin;

        for (String messages : langFile.getAsYaml().getConfigurationSection("").getKeys(false)) {
            CC.log("loading configuration");
        }
    }
}
