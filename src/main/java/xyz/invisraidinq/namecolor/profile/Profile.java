package xyz.invisraidinq.namecolor.profile;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.invisraidinq.namecolor.NameColorPlugin;
import xyz.invisraidinq.namecolor.utils.CC;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Profile {

    private final NameColorPlugin plugin;

    private final OfflinePlayer player;
    private final UUID uuid;

    private String nameColor;
    private boolean italic;
    private boolean bold;
    private boolean underlined;

    /**
     * @param plugin The java plugin object
     * @param player OfflinePlayer object
     */
    public Profile(NameColorPlugin plugin, OfflinePlayer player) {
        this.plugin = plugin;

        this.player = player;
        this.uuid = player.getUniqueId();

        this.loadProfile();
    }

    public void loadProfile() {
        File dataFile = new File(this.plugin.getProfileManager().getDataDirectory() + File.separator, this.uuid.toString() + ".yml");

        if (!dataFile.exists()) {
            this.loadFirstTimeData();
            return;
        }

        YamlConfiguration userData = YamlConfiguration.loadConfiguration(dataFile);
        this.nameColor = userData.getString("nameColor");
        this.italic = userData.getBoolean("italic");
        this.bold = userData.getBoolean("bold");
        this.underlined = userData.getBoolean("underlined");
    }

    public void saveProfile() {
        File dataFile = new File(this.plugin.getProfileManager().getDataDirectory() + File.separator, this.uuid.toString() + ".yml");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                CC.log("Failed to save profile for " + this.player.getName());
                e.printStackTrace();
            }
        }

        YamlConfiguration userData = YamlConfiguration.loadConfiguration(dataFile);
        userData.set("nameColor", this.nameColor);
        userData.set("italic", this.italic);
        userData.set("bold", this.bold);
        userData.set("underlined", this.underlined);

        try {
            userData.save(dataFile);
        } catch (IOException e) {
            CC.log("Failed to save profile for " + this.player.getName());
            e.printStackTrace();
        }

        this.plugin.getProfileManager().getProfileMap().remove(this.uuid);
    }

    public OfflinePlayer getPlayer() {
        return this.player;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public String getNameColor() {
        return this.nameColor;
    }

    public void setNameColor(String color) {
        this.nameColor = color;
    }

    public boolean isItalic() {
        return this.italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public void toggleItalic() {
        this.italic = !this.italic;
    }

    public boolean isBold() {
        return this.bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public void toggleBold() {
        this.bold = !this.bold;
    }

    public boolean isUnderlined() {
        return this.underlined;
    }

    public void setUnderlined(boolean underlined) {
        this.underlined = underlined;
    }

    public void toggleUnderlined() {
        this.underlined = !this.underlined;
    }

    public void resetNameColor() {
        this.loadFirstTimeData();
    }

    private void loadFirstTimeData() {
        this.nameColor = "&8";
        this.italic = false;
        this.bold = false;
        this.underlined = false;
    }
}