package xyz.invisraidinq.namecolor.profile;

import org.bukkit.OfflinePlayer;
import xyz.invisraidinq.namecolor.NameColorPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileManager {

    private final NameColorPlugin plugin;
    private final File dataDirectory;

    private final Map<UUID, Profile> profileMap = new HashMap<>();

    /**
     * @param plugin The JavaPlugin param
     */
    public ProfileManager(NameColorPlugin plugin) {
        this.plugin = plugin;
        this.dataDirectory = new File(plugin.getDataFolder() + File.separator + "users" + File.separator);

        if (!this.dataDirectory.exists()) {
            this.dataDirectory.mkdirs();
        }
    }

    public Profile getProfileByPlayer(OfflinePlayer player) {
        return this.profileMap.getOrDefault(player.getUniqueId(), null);
    }

    /**
     * @param player The OfflinePlayer object
     */
    public void loadProfile(OfflinePlayer player) {
        this.profileMap.put(player.getUniqueId(), new Profile(this.plugin, player));
    }

    /**
     * @param player The OfflinePlayer object
     */
    public void saveProfile(OfflinePlayer player) {
        this.profileMap.get(player.getUniqueId()).saveProfile();
    }

    public File getDataDirectory() {
        return this.dataDirectory;
    }

    public Map<UUID, Profile> getProfileMap() {
        return this.profileMap;
    }
}
