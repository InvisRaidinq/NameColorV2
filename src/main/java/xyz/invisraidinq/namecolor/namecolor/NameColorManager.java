package xyz.invisraidinq.namecolor.namecolor;

import xyz.invisraidinq.namecolor.NameColorPlugin;
import xyz.invisraidinq.namecolor.utils.ConfigFile;

import java.util.ArrayList;
import java.util.List;

public class NameColorManager {

    private final NameColorPlugin plugin;
    private final List<NameColor> nameColorList = new ArrayList<>();

    public NameColorManager(NameColorPlugin plugin, ConfigFile nameColorFile) {
        this.plugin = plugin;

        for (String colors : nameColorFile.getAsYaml().getConfigurationSection("COLORS").getKeys(false)) {
            NameColor nameColor = new NameColor(nameColorFile.getString(colors + ".COLOR"), nameColorFile.getInt(colors + ".SLOT"),
                    nameColorFile.getString(colors + ".PERMISSION"), nameColorFile.getString(colors + ".MATERIAL"), nameColorFile.getInt(colors + ".DATA"),
                    nameColorFile.getString(colors + ".NAME"), nameColorFile.getStringList(colors + ".LORE"));
            this.nameColorList.add(nameColor);
        }
    }

    public List<NameColor> getNameColorList() {
        return this.nameColorList;
    }
}
